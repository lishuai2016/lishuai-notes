模仿国内知名B2C网站,实现的一个分布式B2C商城 使用Spring Boot 
自动配置 Dubbox / MVC / MyBatis / Druid / Solr / Redis 等。使用Spring Cloud版本请查看
http://git.oschina.net/binu/xbin-store-cloud

使用技术:

后台
使用Spring Boot 构建整个项目 去除 XML 配置
Maven构建项目
Jenkins作为持续集成
采用Dubbox作为RPC框架
kryo序列化
使用 Apollo 配置中心
使用Spring+Spring MVC+MyBatisSSM框架
数据库连接池使用druid
数据库使用MySQL和Redis
页面引擎采用 Beetl
网页采用freemarker生成静态化页面
存储采用FastDFS存储图片等文件
采用Solr实现搜索服务
Swagger2 生成 RESTful Apis文档
负载均衡使用Nginx、keepalived实现高可用
采用Spring Scheduled做任务调度
消息中间件采用RabbitMQ
在分布式事务上则采用了TCC解决订单支付方面时效性要求性高的分布式事务,可靠的消息服务则来解决如会计记录等时效性要求低的分布式事务.
前台
采用基于AdminLTE的roncoo-adminLTE(主要增加了Ajax的布局模式)
AdminLTE集成太多Js这里就不一一列举了


学习分支：
https://gitee.com/2016shuai/xbin-store/tree/20181124_ls