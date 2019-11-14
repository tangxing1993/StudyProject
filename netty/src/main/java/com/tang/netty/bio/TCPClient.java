package com.tang.netty.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 * @author 17611
 *
 */
public class TCPClient {

	public static void main(String[] args) throws Exception{
		while(true) {
			// 创建连接socket
			Socket socket = new Socket("127.0.0.1", 9999);
			OutputStream os = socket.getOutputStream();
			System.out.println("请输入:");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String msg = scanner.nextLine();
			os.write(msg.getBytes());
			InputStream in = socket.getInputStream();
			byte[] buffer = new byte[1024];
			in.read(buffer);
			System.out.println("老板说:" + new String(buffer).trim());
			socket.close();
		}
		
		
	}
	
}
