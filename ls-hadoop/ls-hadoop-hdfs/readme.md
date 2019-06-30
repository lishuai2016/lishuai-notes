1、包com.ls.hadoop.hdfs.rpc下为hdfs的rpc测试
服务端和客户端在启动的时候都会报错如下，可以不关注

```java
19/03/13 13:36:29 WARN util.Shell: Did not find winutils.exe: {}
java.io.FileNotFoundException: java.io.FileNotFoundException: HADOOP_HOME and hadoop.home.dir are unset. -see https://wiki.apache.org/hadoop/WindowsProblems
	at org.apache.hadoop.util.Shell.fileNotFoundException(Shell.java:528)
	at org.apache.hadoop.util.Shell.getHadoopHomeDir(Shell.java:549)
	at org.apache.hadoop.util.Shell.getQualifiedBin(Shell.java:572)
	at org.apache.hadoop.util.Shell.<clinit>(Shell.java:669)
	at org.apache.hadoop.util.StringUtils.<clinit>(StringUtils.java:79)
	at org.apache.hadoop.conf.Configuration.getBoolean(Configuration.java:1445)
	at org.apache.hadoop.ipc.Server.<init>(Server.java:2579)
	at org.apache.hadoop.ipc.RPC$Server.<init>(RPC.java:958)
	at org.apache.hadoop.ipc.WritableRpcEngine$Server.<init>(WritableRpcEngine.java:405)
	at org.apache.hadoop.ipc.WritableRpcEngine.getServer(WritableRpcEngine.java:327)
	at org.apache.hadoop.ipc.RPC$Builder.build(RPC.java:800)
	at com.ls.hadoop.hdfs.rpc.RPCServer.main(RPCServer.java:25)
Caused by: java.io.FileNotFoundException: HADOOP_HOME and hadoop.home.dir are unset.
	at org.apache.hadoop.util.Shell.checkHadoopHomeInner(Shell.java:448)
	at org.apache.hadoop.util.Shell.checkHadoopHome(Shell.java:419)
	at org.apache.hadoop.util.Shell.<clinit>(Shell.java:496)
	... 8 more
```










# pom配置
```xml
<properties>
		<zookeeper.version>3.4.10</zookeeper.version>
		<hadoop.version>2.7.3</hadoop.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.apache.zookeeper</groupId>
			<artifactId>zookeeper</artifactId>
			<version>${zookeeper.version}</version>
		</dependency>
		<dependency>
			<groupId>org.apache.hadoop</groupId>
			<artifactId>hadoop-mapreduce-client-core</artifactId>
			<version>${hadoop.version}</version>
		</dependency>
		<dependency>
			<groupId>org.apache.hadoop</groupId>
			<artifactId>hadoop-mapreduce-client-common</artifactId>
			<version>${hadoop.version}</version>
		</dependency>
		<dependency>
			<groupId>org.apache.hadoop</groupId>
			<artifactId>hadoop-mapreduce-client-app</artifactId>
			<version>${hadoop.version}</version>
		</dependency>
		<dependency>
			<groupId>org.apache.hadoop</groupId>
			<artifactId>hadoop-mapreduce-client-hs</artifactId>
			<version>${hadoop.version}</version>
		</dependency>
		<dependency>
			<groupId>org.apache.hadoop</groupId>
			<artifactId>hadoop-hdfs</artifactId>
			<version>${hadoop.version}</version>
		</dependency>	
		<dependency>
			<groupId>org.apache.hadoop</groupId>
			<artifactId>hadoop-hdfs-nfs</artifactId>
			<version>${hadoop.version}</version>
		</dependency>	
		<dependency>
			<groupId>org.apache.hadoop</groupId>
			<artifactId>hadoop-common</artifactId>
			<version>${hadoop.version}</version>
		</dependency>	
		
	</dependencies>
```







# 参考
https://blog.csdn.net/l1394049664/article/details/82719533
https://www.cnblogs.com/qq503665965/p/6696675.html
https://www.cnblogs.com/qq503665965/p/6740992.html
https://www.cnblogs.com/qq503665965/p/6708644.html