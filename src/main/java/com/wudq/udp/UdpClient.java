package com.wudq.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * udp协议客户端
 * @author wudq
 *
 */
public class UdpClient extends Thread{
	
	private DatagramSocket client;
	private static final int port = 12345;
	private static final String serverName = "127.0.0.1";
	
	/**
	 * 构造方法创建DatagramSocket实例
	 * @throws IOException
	 */
	public UdpClient() throws IOException {
		client = new DatagramSocket();
		client.setSoTimeout(10000);
	}
	
	/**
	 * 接收服务器响应信息
	 * @throws IOException
	 */
	public void recive() throws IOException {
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		//此方法会阻塞线程
		client.receive(dp);
		String info = new String(dp.getData(), 0, dp.getLength());    
		System.out.println("服务器响应信息：" + info); 
	}
	
	/**
	 * 向服务器发送信息
	 * @param buf
	 * @throws IOException
	 */
	public void send(byte[] buf) throws IOException {
		DatagramPacket dp = new DatagramPacket(buf, 
				buf.length, InetAddress.getByName(serverName), port);
		dp.setData(buf);
		client.send(dp);
	}
	
	public static void main(String[] args) throws IOException {
		UdpClient udpClient = new UdpClient();
		String str = "客户端发送请求...";
		udpClient.send(str.getBytes());
		System.out.println("服务器连接成功");
		udpClient.recive();
	}

}
