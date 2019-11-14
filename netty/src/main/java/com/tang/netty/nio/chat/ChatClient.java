package com.tang.netty.nio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 聊天室客户端程序
 * 
 * @author 17611
 *
 */
public class ChatClient {

	private SocketChannel clientChannel = null;
	private static final String hostname = "127.0.0.1";
	private static final int port = 9999;
	private String username;

	public ChatClient() {
		try {
			clientChannel = SocketChannel.open();
			clientChannel.configureBlocking(false);
			if (!clientChannel.connect(new InetSocketAddress(hostname, port))) {
				while (!clientChannel.finishConnect()) {
					System.out.println("正在连接服务器,请稍候...");
				}
			}
			// 获取当前通道的IP地址
			username = clientChannel.getLocalAddress().toString().substring(1);
			System.out.println("---------------(" + username + ")准备就绪----------------");
		} catch (Exception e) {
			System.out.println("服务器连接失败!!!");
			e.printStackTrace();
		}
	}

	// 发送消息
	public void sendMsg(String msg) throws Exception {
		if ("bye".equals(msg)) {
			clientChannel.close();
			return;
		}
		msg = username + "说:" + msg;
		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
		clientChannel.write(buffer);
	}

	// 接收消息
	public void receiveMsg() throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		if (clientChannel.read(buffer) > 0) {
			System.out.println(new String(buffer.array()).trim());
		}
	}

}
