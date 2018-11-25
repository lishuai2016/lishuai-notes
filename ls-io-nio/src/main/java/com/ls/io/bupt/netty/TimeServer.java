package com.ls.io.bupt.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;


/**
 * Created by lishuai on 2017/6/17.
 */
public class TimeServer {

    public void bind(int port) {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                       // ChannelPipeline pipeline = socketChannel.pipeline();
                        socketChannel.pipeline().addLast(new TimeServerHandler()); //添加服务端处理的逻辑
                    }
                });
    }

//    private class ChildeChannelHandler extends ChannelInitializer<SocketChannel> {
//
//        @Override
//        protected void initChannel(SocketChannel args0) throws Exception {
//            args0.pipeline().addLast(new TimeServerHandler());
//        }
//    }
    public static void main(String[] ars) throws IOException {
        int port = 8080;
        new TimeServer().bind(port);
    }
}
