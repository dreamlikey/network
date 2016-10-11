package com.wudq.tcp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.wudq.utils.IoUtil;

/**
 * DataInputStream继承InputStream同时实现了DataInput接口，比普通的InputStream多一些方法
 * 文件传输中需要这些方法
 * 
 * @author wudq
 *
 */
public class ServerTest extends Thread{

	private Socket socket;

	private ServerSocket server;

	public ServerTest(int port) throws IOException {
		server = new ServerSocket(port);
		server.setSoTimeout(90000);
	}
	
	@Override
	public void run() {
		//while循环让服务器一直处于监听状态，等待链接
		while (true) {
			try {
				File file = IoUtil.getFile();
				if (file == null) {
					System.out.println("server: 文件不存在...");
					return ;
				}
				System.out.println("文件名：" + file.getName() + ";\t文件大小：" + (int) file.length() + "bytes");

				System.out.println("等待客户端连接，连接端口：" + server.getLocalPort()); 
				//accept会阻塞线程
				socket = server.accept();
				System.out.println("建立socket链接"); 

				DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
				DataOutputStream os = new DataOutputStream(socket.getOutputStream());

				//写入输出流，客户端读取输出
				os.writeUTF(file.getName());  
				os.flush();  
				os.writeLong((long) file.length());  
				os.flush(); 

				byte[] buf = new byte[10000];
				while (true) {
					int read = 0;
					if (fis != null ) {
						read = fis.read(buf);
					}
					if (read == -1) {  
						break;  
					}
					//传输byte数组
					os.write(buf, 0, read); 
				}
				os.flush();
				fis.close();
				socket.close();
				System.out.println("文件传输完成\n"); 
			} catch (IOException e) {
				System.out.println("连接异常...");
				e.printStackTrace();
				break;
			}
		}

	}

	public static void main(String[] args) {
		try {
			Thread t = new ServerTest(6066);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
