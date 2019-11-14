package com.tang.netty.nio.chat;

import java.util.Scanner;

/**
 * 测试聊天客户端程序
 * 
 * @author 17611
 *
 */
public class TestChatClient {

	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		// 接收消息
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						client.receiveMsg();
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		// 发送消息
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String msg = scanner.next();
			try {
				client.sendMsg(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 scanner.close();
	}

}
