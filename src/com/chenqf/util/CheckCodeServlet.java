package com.chenqf.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 一,绘图
		 */
		//1,创建一个内存映像对象(画布)
		BufferedImage image = new BufferedImage(80, 30,BufferedImage.TYPE_INT_BGR);
		//2,获得画笔
		Graphics g = image.getGraphics(); 
		//3,给笔设置颜色
		g.setColor(new Color(255, 255, 255)); 
		//4,设置画布的背景颜色
		g.fillRect(0, 0, 80,30);
		//5,给笔设置颜色
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255)));
		//6,生成一个随机数
		//7,将number绘制到画布上。
		String number = getNumber(5);
		//Font(字体,风格,大小)
		g.setFont(new Font(null, Font.ITALIC, 20)); 
		g.drawString(number, 3, 24);
		//8,加干扰线
		for(int i=0;i<8;i++){
			g.setColor(new Color(0,0,0));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		/*
		 * 二,将图片压缩，然后输出(发送给浏览器)
		 */
		//设置content-type消息头，告诉浏览器
		//服务器返回的数据类型(是一张jpeg格式的图片)
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		//write方法：将原始图片(image)按照指定的
		//压缩格式(jpeg)进行压缩，然后将得到的字节
		//输出到response。
		javax.imageio.ImageIO.write(image, "jpeg", os);
		os.close();
		
	}
	private String getNumber(int size) {
		String number = "";
		String str = "ABCDEFGHIJKLMNOPQ" +
				"RSTUVWXYZ0123456789";
		Random r = new Random();
		for(int i = 0;i < size;i ++){
			number += str.charAt(r.nextInt(str.length()));
		}
		return number;
	}


}
