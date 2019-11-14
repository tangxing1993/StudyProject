package com.tang.netty.nio.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 客户端
 * @author 17611
 *
 */
public class NIOClient {

	public static void main(String[] args)throws Exception {
		// 服务器地址信息
		InetSocketAddress remote = new InetSocketAddress("127.0.0.1", 9999);
		// 获取客户端通道
		SocketChannel channel = SocketChannel.open();
		// 设置通道为非阻塞的
		channel.configureBlocking(false);
		// 连接服务器
		if(!channel.connect(remote)) {
			while(!channel.finishConnect()) {
				System.out.println("客户端正常尝试连接服务器端...");
			}
		}
		// 发送信息到服务器端
		ByteBuffer buffer = ByteBuffer.wrap("Hello,NIO!".getBytes());
		channel.write(buffer);
		// 关闭客户端
		System.in.read();   // 挂起客户端，不然引起服务器端异常
	}
	
}
