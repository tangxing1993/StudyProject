package com.tang.netty.demo.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端程序
 * @author 17611
 *
 */
public class NettyServer {

	public static void main(String[] args)throws Exception {
		// 创建启动助手
		ServerBootstrap bootStrap = new ServerBootstrap();
		// 配置启动助手 (NIO的两个线程组  一个用于接收  一个用于读写处理)
		NioEventLoopGroup parentGroup = new NioEventLoopGroup(); // 接收
		NioEventLoopGroup childGroup  = new NioEventLoopGroup(); // 读写处理
		bootStrap
		.group(parentGroup, childGroup)
		.channel(NioServerSocketChannel.class)  			// 使用NioServerSocketChannel作为服务端通道的实现
		.option(ChannelOption.SO_BACKLOG, 128)  			// 设置线程队列中等待连接的个数
		.childOption(ChannelOption.SO_KEEPALIVE, true)		// 保持连接处于激活状态
		.childHandler(new ChannelInitializer<SocketChannel>() {  // 创建通道初始化对象
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new NettyServerHandler());   // 向pipeLine链中添加自定义的handler处理器
			}
		});
		System.out.println("...... server is ready ......");
		// 绑定端口
		ChannelFuture future = bootStrap.bind(9999).sync();
		System.out.println("...... server is start ......");
		
		// 服务停止的时候关闭通道
		future.channel().closeFuture().sync();
		// 关闭线程组
		parentGroup.shutdownGracefully();
		childGroup.shutdownGracefully();
	}
	
	
}
