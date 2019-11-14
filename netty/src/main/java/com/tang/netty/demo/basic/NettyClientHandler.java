package com.tang.netty.demo.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
   *    客户端处理器
 * @author tangxing
 *
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter{

	// 连接事件
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer("老板换钱吧！", CharsetUtil.UTF_8));
	}
	// 读取服务端数据事件
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buffer = (ByteBuf) msg;
		System.out.println("服务器说: " + buffer.toString(CharsetUtil.UTF_8));
	}

	
	
}
