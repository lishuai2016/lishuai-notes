#Java线上问题排查工具单


##1、jps

jps -mlvV

##2、jstack
jstack 2815

native+java栈:
jstack -m 2815

jstack 7972 > 1.txt  输出到文件中

##3、jinfo

可看系统启动的参数，如下
jinfo -flags 2815

##4、jmap
两个用途
4.1、查看堆的情况
jmap -heap 2815

4.2、dump
jmap -dump:live,format=b,file=/tmp/heap2.bin 2815
或者
jmap -dump:format=b,file=/tmp/heap3.bin 2815

4.3、看看堆都被谁占了? 再配合zprofiler和btrace，排查问题简直是如虎添翼

jmap -histo 2815 | head -10


##5、jstat
jstat参数众多，但是使用一个就够了

jstat -gcutil 2815 1000 

##6、jdb
时至今日，jdb也是经常使用的。 
jdb可以用来预发debug,假设你预发的java_home是/opt/taobao/java/，远程调试端口是8000.那么
jdb -attach 8000

screenshot.png
出现以上代表jdb启动成功。后续可以进行设置断点进行调试。
具体参数可见oracle官方说明http://docs.oracle.com/javase/7/docs/technotes/tools/windows/jdb.html

##7、CHLSDB
CHLSDB感觉很多情况下可以看到更好玩的东西，不详细叙述了。 查询资料听说jstack和jmap等工具就是基于它的。

sudo -u admin /opt/taobao/java/bin/java -classpath /opt/taobao/java/lib/sa-jdi.jar sun.jvm.hotspot.CLHSDB
更详细的可见R大此贴
http://rednaxelafx.iteye.com/blog/1847971







其他

dmesg

如果发现自己的java进程悄无声息的消失了，几乎没有留下任何线索，那么dmesg一发，很有可能有你想要的。

sudo dmesg|grep -i kill|less
去找关键字oom_killer。找到的结果类似如下:

[6710782.021013] java invoked oom-killer: gfp_mask=0xd0, order=0, oom_adj=0, oom_scoe_adj=0
[6710782.070639] [<ffffffff81118898>] ? oom_kill_process+0x68/0x140 
[6710782.257588] Task in /LXC011175068174 killed as a result of limit of /LXC011175068174 
[6710784.698347] Memory cgroup out of memory: Kill process 215701 (java) score 854 or sacrifice child 
[6710784.707978] Killed process 215701, UID 679, (java) total-vm:11017300kB, anon-rss:7152432kB, file-rss:1232kB
以上表明，对应的java进程被系统的OOM Killer给干掉了，得分为854.
解释一下OOM killer（Out-Of-Memory killer），该机制会监控机器的内存资源消耗。当机器内存耗尽前，该机制会扫描所有的进程（按照一定规则计算，内存占用，时间等），挑选出得分最高的进程，然后杀死，从而保护机器。

dmesg日志时间转换公式:
log实际时间=格林威治1970-01-01+(当前时间秒数-系统启动至今的秒数+dmesg打印的log时间)秒数：

date -d "1970-01-01 UTC `echo "$(date +%s)-$(cat /proc/uptime|cut -f 1 -d' ')+12288812.926194"|bc ` seconds"
剩下的，就是看看为什么内存这么大，触发了OOM-Killer了。

