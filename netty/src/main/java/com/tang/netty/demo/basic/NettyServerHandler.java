package com.tang.netty.demo.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
   *    服务端处理器
 * @author tangxing
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter{

	// 读取数据处理
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(ctx);
		ByteBuf buffer = (ByteBuf) msg;
		System.out.println("客户端【" + ctx.channel().remoteAddress().toString().substring(1) + "】发来的消息:" + buffer.toString(CharsetUtil.UTF_8));
	}
	// 读取完成处理
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ByteBuf buffer = Unpooled.copiedBuffer("没钱", CharsetUtil.UTF_8);
		ctx.writeAndFlush(buffer);
	}
	// 异常处理
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	
	
}
