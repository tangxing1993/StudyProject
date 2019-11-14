package com.tang.netty.nio.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * NIO 文件测试类
 * @author 17611
 *
 */
public class TestNIO {

	// 使用NIO写入内容到basic.txt中
	@Test
	public void testWriteFile() throws Exception {
		try(FileOutputStream fos = new FileOutputStream("basic.txt")){
			// 获取通道
			FileChannel channel = fos.getChannel();
			// 创建字节缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.put("Hello NIO!".getBytes());
			// 反转缓冲区
			buffer.flip();
			channel.write(buffer);
		}
	}
	
	// 使用NIO读取文件内的数据并打印
	@Test
	public void testReadFile()throws Exception {
		File file  = new File("basic.txt");
		try(FileInputStream fin = new FileInputStream(file)){
			FileChannel channel = fin.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
			channel.read(buffer);
			System.out.println(new String(buffer.array()));
		}
	}
	
    // 使用NIO复制数据
	@Test
	public void testCopyData() {
		try(FileInputStream fis = new FileInputStream("basic.txt");FileOutputStream fos = new FileOutputStream("d:\\basic.txt")){
			FileChannel readChannel = fis.getChannel();
			FileChannel writeChannel = fos.getChannel();
			writeChannel.transferFrom(readChannel, 0, readChannel.size());
			System.out.println("文件复制成功！");
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
