package com.tang.netty.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞IO  服务端
 * @author 17611
 *
 */
public class TCPServer {

	public static void main(String[] args)throws Exception {
		// 创建服务端socket
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(9999);
		while(true) {
			// 监听客户端
			Socket socket = server.accept();  // 阻塞IO
			// 获取客户端IP
			String clientIP = socket.getInetAddress().getHostAddress();
			byte[] buffer = new byte[1024];
			InputStream in = socket.getInputStream();
			in.read(buffer);
			System.out.println(clientIP + ":" + new String(buffer));
		    OutputStream os = socket.getOutputStream();
		    os.write("没钱".getBytes());
		    socket.close();
		}
	}
	
}
