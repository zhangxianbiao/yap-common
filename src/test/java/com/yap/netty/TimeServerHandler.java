package com.yap.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;
  /*  @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf buf = (ByteBuf)msg;
        //byte[] req = new byte[buf.readableBytes()];
        //buf.readBytes(req);

        //String body = new String(req, "UTF-8");
        //String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
        String body = (String) msg;// 经过StringDecoder进行解析了

        System.out.println("The time server receive order:" + body + " ; the counter is : " + ++counter);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
                System.currentTimeMillis()).toString() : "BAD ORDER";

        currentTime = currentTime + System.getProperty("line.separator");

        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //ctx.write(resp);
        ctx.writeAndFlush(resp);
    }*/


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        ctx.close();
    }
}
