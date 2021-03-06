[参考](https://www.cnblogs.com/yunfeifei/p/4482495.html)

1、MarkDown简介

　　Markdown 是一种轻量级的「标记语言」，它的优点很多，目前也被越来越多的写作爱好者，撰稿者广泛使用。看到这里请不要被「标记」、「语言」所迷惑，Markdown 的语法十分简单。常用的标记符号也不超过十个，这种相对于更为复杂的 HTML 标记语言来说，Markdown 可谓是十分轻量的，学习成本也不需要太多，且一旦熟悉这种语法规则，会有一劳永逸的效果。

　　用markdown编写完后，我们可以导出到html发布到网站或者导出pdf保存到本地，十分的方便。最重要的是markdown源文件是纯文本文件，也就是意味着可以跨平台，使用 Markdown 的优点如下：

专注你的文字内容而不是排版样式，安心写作。
轻松的导出 HTML、PDF 和本身的 .md 文件。
纯文本内容，兼容所有的文本编辑器与字处理软件。
随时修改你的文章版本，不必像字处理软件生成若干文件版本导致混乱。
可读、直观、学习成本低。
 这里先给大家推荐一款我最喜欢的MarkDown在线编辑器，CmdMarkDown,网址为：https://www.zybuluo.com/mdeditor，大家可以先在这里学习基本语法。

 

2.MarkDown基本语法

1) 标题

标题是每篇文章都需要也是最常用的格式，在 Markdown 中，如果一段文字被定义为标题，只要在这段文字前加 # 号即可。

# 一级标题     //对应html标签<h1>

## 二级标题   //对应html标签<h2>

### 三级标题 //对应html标签<h3>

以此类推，总共六级标题，建议在井号后加一个空格，这是最标准的 Markdown 语法。

这里要注意的是#和后面的内容之间要有空格隔开。

 

2)粗体和斜体

Markdown 的粗体和斜体也非常简单，用两个 * 包含一段文本就是粗体的语法，用一个 * 包含一段文本就是斜体的语法。

*Markdow*  //斜体

**Markdow**  //粗体

 

3)分割线

分割线也比较简单，markdown语法为：

---

或者 

***

 

4) 列表

这里的列表指的就是我们html中常用的有序列表和无序列表，及<ul>和<ol>.这里不再给出截图，大家可以自己动手实践。

其语法如下：

无序列表：

* 1  或者 - 1  或者 + 1 

* 2  或者 - 2  或者 + 2 

* 3  或者 - 3  或者 + 3 

这里要注意的是有使用*就都是用*号，使用 '-' 就都用 '-' 号，混合使用会出错的。还有就是* 和内容之间要加一个空格。

有序列表比较简单，写法如下：

1. Item1

2. Item2

3. Item3

 当然，这里前面的序号可以随便写，总之后面会得到与之相同的输出。

 

5）引用

如果你需要引用一小段别处的句子，那么就要用引用的格式。引用的语法就是在文字前面加 > ,如：

> 这里是引用

输出如下：



当然，不同的markdown编辑器输出的显示略有不同.

 

6) 超链接

超链接的写法比较特殊，但是也很好记忆，如我们加一个百度的超链接，markdown语法如下：

[百度](http://www.baidu.com)

当我们导出到html中时，就会得到一个<a>标签的输出。

如果我们要加入一张图片，其语法如下：

![百度](https://www.baidu.com/img/bdlogo.png)

跟文字链接相比，也就是前面多了一个感叹号！。

 

7) 表格

markdown的表格我感觉写起来并不是那么简便，我们先来看一下表格的写法：

| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

显示结果如下：




这里值得注意的是第二行，|:------------:|,这里两边加冒号就是居中，如果只有一边加冒号就是哪边对齐，如Cool那一列就是右对齐。默认左对齐。

 

 8) 代码框

 程序员写东西，难免会插入一些代码，在 Markdown下实现也非常简单，只需要用两个 ` 把中间的代码包裹起来，代码的语法如下：

· -- 代码内容-- ·

 注意这里的`不是单引号，而是键盘左边那个~上的`。