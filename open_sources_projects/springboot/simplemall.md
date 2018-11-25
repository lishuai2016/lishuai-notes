https://github.com/lishuai2016/simplemall


基于SpringCloud的微服务架构实战案例项目，以一个简单的购物流程为示例，融合spring cloud 相关组件，
如spring-cloud-netflix、swagger等 https://github.com/backkoms/simplemal…


基于SpringCloud体系实现，简单购物流程实现，满足基本功能：注册、登录、商品列表展示、商品详情展示、订单创建、
详情查看、订单支付、库存更新等等。

每个业务服务采用独立的MYSQL数据库，初期考虑用到如下组件：

服务注册、发现: eureka
配置管理:spring config , spring security
集群容错: hystrix
API网关: zuul
服务负载:feign+ribbon
api文档输出:swagger2
代码简化:lombok
消息队列:rabbitmq
分布式锁: redis （待实现）
链路跟踪:spring cloud sletuh ->zipkin
安全认证:oauth2/JWT(通过JWT轻量级的实现)
服务监控:spring-boot-admin