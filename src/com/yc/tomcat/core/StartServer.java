package com.yc.tomcat.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartServer {
	public static void main(String[] args) {
		try {
			new StartServer().start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	private void start() throws IOException {
		int port=Integer.parseInt(ReadConfig.getInstance().getProperty("port"));
		//System.out.println(port);
		
		//启动服务器
		ServerSocket ssk=new ServerSocket(port);
		System.out.println("启动服务器成功，端口号为："+port);
		new ParseXml();
		
		//创建一个线程池
		ExecutorService serviceThread=Executors.newFixedThreadPool(20);
		
		//监听客户端的请求
		Socket sk=null;
		while(true) {
			sk=ssk.accept();
		
			serviceThread.submit(new ServerSession(sk));
		}
		
	}

}
