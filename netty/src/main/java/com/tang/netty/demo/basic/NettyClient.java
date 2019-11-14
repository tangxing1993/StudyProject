package com.tang.netty.demo.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
   *    客户端程序
 * @author 17611
 *
 */
public class NettyClient {

	public static void main(String[] args) throws Exception {
		// 创建一个客户端启动助手
		Bootstrap bootStrap = new Bootstrap();
		// 配置启动助手
		NioEventLoopGroup group = new NioEventLoopGroup(); // 配置线程组
		bootStrap.group(group)
				 .channel(NioSocketChannel.class)       // 客户端通道的实现
				 .handler(new ChannelInitializer<Channel>() {  // 创建通道的初始化实现
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new NettyClientHandler());
					}
				 });
		System.out.println("......Client is ready......");
		ChannelFuture future = bootStrap.connect("127.0.0.1", 9999).sync();
		System.out.println("......Client is start......");
		// 关闭通道 关闭线程组
		future.channel().closeFuture().sync();
		group.shutdownGracefully();
	}
	
}
