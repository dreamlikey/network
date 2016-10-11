package com.wudq.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.wudq.utils.IoUtil;

/**
 * udp协议服务端
 * @author wudq
 *
 */
public class UdpServer {

	private DatagramSocket ds = null;

	private DatagramPacket dp = null;

	/**
	 * 构造方法，初始化DatagramSocket
	 * @param port
	 * @throws SocketException
	 */
	public UdpServer(int port) throws SocketException {
		ds = new DatagramSocket(port);
		ds.setSoTimeout(90000);
		System.out.println("服务端启动!"); 
	}

	/**
	 * 接收数据包，该方法会造成线程阻塞
	 * @return 返回接收的数据串信息
	 * @throws IOException
	 */
	public String recive() throws IOException {
		byte[] buffer = new byte[1024];  
		dp = new DatagramPacket(buffer, buffer.length); 
		ds.receive(dp);
		String orgIp = dp.getAddress().getHostAddress();    
		String info = new String(dp.getData(), 0, dp.getLength());    
		System.out.println("服务端接收信息：" + info); 
		return info;
	}

	/**
	 * 包装、发送数据
	 * @param buf
	 * @param address
	 * @throws IOException
	 */
	public void response(byte[] buf) throws IOException {
		DatagramPacket dPacket = 
				new DatagramPacket(buf, buf.length, dp.getAddress(),dp.getPort());
		dp.setData(buf);
		ds.send(dPacket);
	}

	/**  
	 * 关闭udp监听口.  
	 */    
	public final void close() {    
		try {    
			ds.close();    
		} catch (Exception ex) {    
			ex.printStackTrace();    
		}    
	}  

	public static void main(String[] args) throws Exception {
		int port = 12345;
		UdpServer server = new UdpServer(port);
		while (true) {
			String info = server.recive();
			if (IoUtil.isEmpty(info)) {
				server.close();
			} 
			String str = "测试链接";
			server.response(str.getBytes());
		}
	}

}
