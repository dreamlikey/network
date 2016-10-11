package com.wudq.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端程序
 * 通过socket连接到服务器并发送一个请求，然后等待一个响应
 * @author wudq
 *
 */
public class GreetingClient {
	
	private static Socket client;

	public static void main(String[] args) {
//		String serverName = args[0];
//		int port = Integer.parseInt(args[1]);
		String serverName = "127.0.0.1";
		int port = 6066;
		System.out.println("Client: Connecting to " + serverName + " on port " + port);
		try {
			client = new Socket(serverName,port);
			System.out.println("Client: Just connected to " + client.getRemoteSocketAddress());
			OutputStream os = client.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF("Client: Hello from " + client.getLocalSocketAddress());
			InputStream is = client.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			System.out.println("Client: Server says[" + dis.readUTF()+"]");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
