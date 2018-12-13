Cloud-Admin是国内首个基于Spring Cloud微服务化开发平台，核心技术采用Spring Boot2以及Spring Cloud Gateway相关核心组件，
前端采用vue-element-admin组件。具有统一授权、认证后台管理系统，其中包含具备用户管理、资源权限管理、网关API管理等多个模块，
支持多业务系统并行开发，可以作为后端服务的开发脚手架。代码简洁，架构清晰，适合学习和直接项目中使用。

架构摘要
服务鉴权
老A独有的通过JWT的方式来加强服务之间调度的权限验证，保证内部服务的安全性。

监控
利用Spring Boot Admin 来监控各个独立Service的运行状态；利用Hystrix Dashboard来实时查看接口的运行状态和调用频率等。

负载均衡
将服务保留的rest进行代理和网关控制，除了平常经常使用的node.js、nginx外，Spring Cloud系列的zuul和ribbon，
可以帮我们进行正常的网关管控和负载均衡。其中扩展和借鉴国外项目的扩展基于JWT的Zuul限流插件，方面进行限流。

服务注册与调用
基于Eureka来实现的服务注册与调用，在Spring Cloud中使用Feign, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，
开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

熔断机制
因为采取了服务的分布，为了避免服务之间的调用“雪崩”，采用了Hystrix的作为熔断器，避免了服务之间的“雪崩”。




https://gitee.com/2016shuai/ace-security.git