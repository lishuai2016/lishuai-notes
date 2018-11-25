package com.ls.io.bupt.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;


/**
 * Created by lishuai on 2017/6/17.
 */
public class TimeClientHadnler extends ChannelHandlerAdapter {
    private final ByteBuf firstMessage;
    public TimeClientHadnler() {
        byte[] req = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(firstMessage);  //发送客户端的请求
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);   //把响应数据读入到字节数组中
        String body = new String(req,"UTF-8");
        System.out.println("now is :" + body);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
        ctx.close();
    }
}
