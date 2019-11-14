package com.tang.netty.demo.rpc.serverStub;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyService {

	private final int port;
	
	public NettyService(int port) {
		this.port = port;
	}
	
	public void run() {
		NioEventLoopGroup parentGroup = new NioEventLoopGroup();
		NioEventLoopGroup childGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootStrap = new ServerBootstrap();
			bootStrap.group(parentGroup, childGroup)
					 .channel(NioServerSocketChannel.class)
					 .option(ChannelOption.SO_BACKLOG, 128)
					 .childOption(ChannelOption.SO_KEEPALIVE, true)
					 .childHandler(new ChannelInitializer<Channel>() {

						@Override
						protected void initChannel(Channel ch) throws Exception {
							ch.pipeline().addLast("encoder", new ObjectEncoder());
							ch.pipeline().addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
							ch.pipeline().addLast(new InvokerHandler());
						}
						 
					 });
			ChannelFuture future = bootStrap.bind(port).sync();
			String ipAddr = future.channel().localAddress().toString().substring(1);
			System.out.println("----------服务端启动成功[" + ipAddr + "]----------");
			future.channel().closeFuture().sync();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("服务启动异常");
		}finally {
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		new NettyService(9999).run();
	}
}
