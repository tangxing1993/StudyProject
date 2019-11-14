package com.tang.netty.demo.chat;

import java.util.Optional;
import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 客户端程序
 * 
 * @author tangxing
 *
 */
public class ChatClient {

	private final String addr;
	private final int port;

	public ChatClient(String addr, int port) {
		this.addr = addr;
		this.port = port;
	}

	public void run() {
		NioEventLoopGroup group = new NioEventLoopGroup();
		Scanner scanner = new Scanner(System.in);
		ChannelFuture future = null;
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					// 添加解码器
					pipeline.addLast("decoder", new StringDecoder());
					// 添加编码器
					pipeline.addLast("encoder", new StringEncoder());
					// 添加自定义的业务处理器
					pipeline.addLast(new ChatClientHandler());
				}
			});
			System.out.println("Chat client is ready...");
			future = bootstrap.connect(addr, port).sync();
			Channel channel = future.channel();
			String localIpAddr = channel.localAddress().toString().substring(1);
			System.out.println("Chat client[" + localIpAddr + "] is start...");
			while(true) {
				if(scanner.hasNextLine()) {
					String msg = scanner.nextLine();
					channel.writeAndFlush(msg + "\r\n");
				}
			}
			// future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Chat client is error!!!");
		} finally {
			Optional<ChannelFuture> futureOpt = Optional.ofNullable(future);
			futureOpt.ifPresent(channelFuture -> {
				try {
					channelFuture.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			scanner.close();
			group.shutdownGracefully();
			System.out.println("Chat client shut down!");
		}
	}
	
	public static void main(String[] args) {
		new ChatClient("127.0.0.1", 9999).run();
	}

}
