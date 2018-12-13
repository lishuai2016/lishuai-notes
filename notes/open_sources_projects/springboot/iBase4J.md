SpringBoot 2.0，SpringMVC，Mybatis，mybatis-plus，motan/dubbo分布式，Redis缓存，
Shiro权限管理，redis管理Session，Quartz分布式集群调度，Restful服务，
QQ/微信登录，App token登录，微信/支付宝支付；日期转换、数据类型转换、
序列化、汉字转拼音、身份证号码验证、数字转人民币、发送短信、发送邮件、
加密解密、图片处理、excel导入导出、FTP/SFTP/fastDFS上传下载、二维码、
XML读写、高精度计算、系统配置工具类等等。


iBase4J项目简介
iBase4J是Java语言的分布式系统架构。 使用Spring整合开源框架。
使用Maven对项目进行模块化管理，提高项目的易开发性、扩展性。
系统包括4个子系统：系统管理Service、系统管理Web、业务Service、业务Web。
系统管理：包括用户管理、权限管理、数据字典、系统参数管理等等。
业务相关：您的业务开发。
可以无限的扩展子系统，子系统之间使用Dubbo或MQ进行通信。
主要功能
数据库：Druid数据库连接池，监控数据库访问性能，统计SQL的执行性能。 数据库密码加密，加密方式请查看PropertiesUtil，
decryptProperties属性配置需要解密的key。
持久层：mybatis持久化，使用MyBatis-Plus优化，减少sql开发量；aop切换数据库实现读写分离。Transtraction注解事务。
MVC： 基于spring mvc注解,Rest风格Controller。Exception统一管理。
调度：Spring+quartz, 可以查询、修改周期、暂停、删除、新增、立即执行，查询执行记录等。
基于session的国际化提示信息，职责链模式的本地语言拦截器,Shiro登录、URL权限管理。会话管理，强制结束会话。
缓存和Session：注解redis缓存数据；shiro实现redis分布式session同步，重启服务会话不丢失。
多系统交互：Dubbo,ActiveMQ多系统交互，ftp/sftp/fastdafs发送文件到独立服务器，使文件服务分离。
前后端分离：没有权限的文件只用nginx代理即可。
日志：log4j2打印日志，业务日志和调试日志分开打印。同时基于时间和文件大小分割日志文件。
QQ、微信、新浪微博第三方登录。
工具类：excel导入导出，汉字转拼音，身份证号码验证，数字转大写人民币，FTP/SFTP/fastDFS上传下载，发送邮件，redis缓存，加密等等。
技术选型
● 核心框架：Sring boot + Spring Framework + Dubbo + ibase4j-common
● 安全框架：Apache Shiro
● 任务调度：Spring + Quartz
● 持久层框架：MyBatis + MyBatis-Plus
● 数据库连接池：Alibaba Druid
● 缓存框架：Redis
● 会话管理：Spring-Session
● 日志管理：SLF4J、Log4j2
● 前端框架：Angular JS + Bootstrap + Jquery


https://gitee.com/2016shuai/iBase4J.git