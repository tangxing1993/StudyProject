package com.tang.netty.demo.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/**
 * 服务端程序
 * @author tangxing
 *
 */
public class ChatServer {
	
	private final int port;

	public ChatServer(int port) {
		this.port = port;
	}

	public void run() {
		NioEventLoopGroup parentGroup = new NioEventLoopGroup();
		NioEventLoopGroup childGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(parentGroup, childGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							// 添加解码器
							pipeline.addLast("decoder", new StringDecoder());
							// 添加编码器
							pipeline.addLast("encoder", new StringEncoder());
							// 添加自定义的处理器
							pipeline.addLast(new ChatServerHandler());
						}

					});
			System.out.println("Chat server is ready...");
			ChannelFuture future = bootstrap.bind(port).sync();
			System.out.println("Chat server is start...");
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Chat server is error!!!");
		} finally {
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
			System.out.println("Chat server shut down!");
		}
	}
	
	public static void main(String[] args) {
		new ChatServer(9999).run();
	}
	
}
