/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.ls.io.bupt.netty.msgpack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class EchoClient {
	
	private final String host;
	private final int port;
	private final int sendNumber;
	
	public EchoClient(String host,int port,int sendNumber){
		this.host=host;
		this.port=port;
		this.sendNumber=sendNumber;
	}

	public void connect(int port, String host) throws Exception {
		// 配置客户端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast("msgpack decoder",new MsgpackDecoder());
						ch.pipeline().addLast("msgpack encoder",new MsgpackEncoder());
						ch.pipeline().addLast(new EchoClientHandler(sendNumber));
					}
				});

			// 发起异步连接操作
			ChannelFuture f = b.connect(host, port).sync();

			// 当代客户端链路关闭
			f.channel().closeFuture().sync();
		} finally {
			// 优雅退出，释放NIO线程组
			group.shutdownGracefully();
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String host="127.0.0.1";
		int port = 8080;
		int sendNumber=100;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// 采用默认值
			}
		}
		new EchoClient("127.0.0.1",port,sendNumber).connect(port, host);
	}
}
