package com.yc.tomcat.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSession implements Runnable{
	private Socket sk=null;
	private OutputStream os=null;

	public ServerSession(Socket sk) {
	this.sk=sk;
	}

	@Override
	public void run() {
		try(InputStream is=sk.getInputStream()){
			Request request=new Request(is); //处理请求
			
			//获取请求的资源地址
			
			String url=request.getUrl();
			System.out.println("ServerSession--"+url);
			this.os=sk.getOutputStream();
			
			new Response(os).sendRedirect(url);
		} catch (IOException e) {
			send500(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 发送500的错误消息
	 */
private void send500(IOException e) {
	
}
}
