package com.wudq.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientTest {
	
	private static Socket client;
	private static final int port = 6066;
	private static final String serverName = "127.0.0.1";
	public static void connectServer() {
		try {
			client = new Socket(serverName,port);
			System.out.print("连接服务器成功!" + "\n");  
			DataInputStream dis = new DataInputStream(
					new BufferedInputStream(client.getInputStream()));  
			saveFile(dis);
		} catch (IOException e) {
			System.out.println("文件接收异常...");
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//接收并保存文件
	public static void saveFile(DataInputStream dis) throws IOException {
		String savePath = "F:\\test\\";
		String fileName = dis.readUTF();
		long fileLen = dis.readLong();
		int readLen = 0;
		DataOutputStream dos = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(savePath+fileName)));
		System.out.println("File Size()：" + fileLen + "bytes");  
		System.out.println("开始接收文件!" + "\n");
		
		byte[] buf = new byte[10000];
		while (true) {
			int read = 0;
			if (dos != null ) {
				read = dis.read(buf);
			}
			readLen += read;  
			if (read == -1) {  
				break;  
			}
			System.out.println("文件接收了" + (readLen * 100 / fileLen) + "%\n");
			//传输byte数组
			dos.write(buf, 0, read); 
		}
		System.out.println("接收完成，文件存为" + savePath + "\n");  
		dos.close(); 
	}
	
	public static void main(String[] args) {
		connectServer();
	}
}
