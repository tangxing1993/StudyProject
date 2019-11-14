package com.tang.netty.demo.chat;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务器端业务处理器
 * 
 * @author tangxing
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

	private static List<Channel> channels = new ArrayList<>();

	// 通道就绪
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channels.add(channel);
		String ipAddr = channel.remoteAddress().toString().substring(1);
		System.out.println("【" + ipAddr + "】上线了");
	}

	// 通道未就绪
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channels.remove(channel);
		String ipAddr = channel.remoteAddress().toString().substring(1);
		System.out.println("【" + ipAddr + "】离线了");
	}

	// 异常事件
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			Channel channel = ctx.channel();
			String ipAddr = channel.remoteAddress().toString().substring(1);
			channel.closeFuture().sync();
			System.out.println("【" + ipAddr + "】异常关闭");
	}

	// 读取数据
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		 	Channel channel = ctx.channel();
		 	String ipAddr = channel.remoteAddress().toString().substring(1);
		 	msg = ipAddr + " : " + msg;
		 	// 广播数据
		 	for(Channel curChannel : channels) {
		 		if(curChannel != channel) {
		 			curChannel.writeAndFlush(msg).sync();
		 		}
		 	}
	}

}
