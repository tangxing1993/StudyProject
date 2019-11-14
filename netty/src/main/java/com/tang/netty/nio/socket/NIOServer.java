package com.tang.netty.nio.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务端
 * 
 * @author 17611
 *
 */
public class NIOServer {

	public static void main(String[] args) throws Exception {
		// 创建服务端通道
		ServerSocketChannel channel = ServerSocketChannel.open();
		// 设置通道非阻塞
		channel.configureBlocking(false);
		// 绑定到本地端口
		InetSocketAddress local = new InetSocketAddress("127.0.0.1", 9999);
		channel.bind(local);
		// 创建selector
		Selector selector = Selector.open();
		// 把通道注册到selector上
		channel.register(selector, SelectionKey.OP_ACCEPT); // 监听通道的建立
		// 监听
		while (true) {
			if (selector.select(2000) == 0) { // 没有连接进来
				System.out.println("Server:没有客户端连接...");
				continue;
			}
			// 得到SelectionKey,判断通道内的事件
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			// Set<SelectionKey> selectedKeys = selector.selectedKeys();
			// for(SelectionKey key : selectedKeys) {
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				// 判断事件
				if (key.isAcceptable()) {
					System.out.println("客户端接入事件...");
					SocketChannel socketChannel = (SocketChannel) channel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024)); // 监听通道的读事件
				}
				if (key.isReadable()) {
					System.out.println("读取客户端信息事件...");
					SocketChannel socketChannel = (SocketChannel) key.channel();
					ByteBuffer buffer = (ByteBuffer) key.attachment();
					socketChannel.read(buffer);
					System.out.println("Server:" + new String(buffer.array()));
				}
				// 移除已经处理完毕的事件
				iterator.remove();
			}

		}

	}

}
