package com.wudq.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class GreetingServer extends Thread{
	private ServerSocket serverSocket;

	public GreetingServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(300000);
	}
	
	public void run() {
		while (true) {
			System.out.println("Wait for client on port " + serverSocket.getLocalPort() + "...");
			try {
				Socket server = serverSocket.accept();
				System.out.println("Server: Just connected to " + server.getRemoteSocketAddress());
				DataInputStream dis = new DataInputStream(server.getInputStream());
				System.out.println(dis.readUTF());
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
	            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
	            server.close();
			} catch (SocketTimeoutException s) {
	        	System.out.println("Server: Socket timed out!");
	            break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void main(String[] args) {
//		int port = Integer.parseInt(args[0]);
		try {
			int port = 6066;
			Thread t = new GreetingServer(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
