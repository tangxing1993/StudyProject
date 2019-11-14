package com.tang.netty.demo.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *  客户端的业务处理
 * @author tangxing
 *
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String>{

	// 读取事件
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(msg);
	}

}
