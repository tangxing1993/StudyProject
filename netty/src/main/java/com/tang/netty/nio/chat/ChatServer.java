package com.tang.netty.nio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

/**
 * 聊天室服务端
 * 
 * @author 17611
 *
 */
public class ChatServer {

	private ServerSocketChannel listenChannel = null;
	private Selector selector = null;
	private static final int PORT = 9999;

	public ChatServer() {
		try {
			listenChannel = ServerSocketChannel.open();
			selector = Selector.open();
			listenChannel.bind(new InetSocketAddress(PORT));
			listenChannel.configureBlocking(false);
			listenChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("服务器初始化成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 处理业务
	public void start() {
		try {
			while (true) {
				if (selector.select(2000) == 0) {
					System.out.println("没有人接入聊天室~");
					continue;
				}
				// 处理事件
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()) {
					SelectionKey key = iterator.next();
					if(key.isAcceptable()) {
						SocketChannel socketChannel = listenChannel.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
						System.out.println(socketChannel.getRemoteAddress().toString().substring(1) + "进入聊天室!");
					}
					if(key.isReadable()) {
						// 读取客户端发来的信息
						SocketChannel socketChannel = (SocketChannel)key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						socketChannel.read(buffer);
						String msg = new String(buffer.array());
						printInfo(new String(msg));
						// 给所有的客户端发送广播
						brocast(socketChannel,msg);
					}
					// 移除当前已处理完的事件
					iterator.remove();
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 给所有的客户端发送广播
	 * @param socketChannel 被排除的客户端
	 * @param msg 要发送的消息
	 */
	private void brocast(SocketChannel socketChannel, String msg)throws Exception {
		// 所有连接就绪的客户端
		Set<SelectionKey> keys = selector.keys();
		for(SelectionKey key : keys) {
			Channel channel = key.channel();
			if(channel instanceof SocketChannel && channel!=socketChannel) {
				ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
				((SocketChannel)channel).write(buffer);
			}
		}
	}

	// 打印信息
	public  void printInfo(String msg) {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateTime.format(formatter) + " --> " + msg);
	}	
	
	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	
}
