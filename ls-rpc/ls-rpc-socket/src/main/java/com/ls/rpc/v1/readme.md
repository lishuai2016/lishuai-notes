dubbo作者的demo
参考：
https://javatar.iteye.com/blog/1123915


# RPC的核心是动态代理 + socket传输

这个是个很简单的rpc模型
1、服务端接受客户端来的socket流， 接受约定为
     1.1 方法名
     1.2 参数类型
     1.3 方法所需参数

2、客户端动态代理生成代理service,调用该service的方法实则交给invoke方法处理逻辑，在该逻辑中实现远程连接，起多个线程。



备注：
v1版基本参照原理作者的文章，为了查看服务提供者的多线程处理消费端的请求，
在接口的实现类让服务提供者休眠一段时间，模拟耗时长的请求


知识点：
1、服务提供者暴露服务
通过反射机制调用具体实现处理服务消费端的请求

2、消费端引用服务
通过jdk的动态代理来实现把请求包装后发送到服务提供端

# RpcFramework
这个是核心类