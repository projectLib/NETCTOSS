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
		 * һ,��ͼ
		 */
		//1,����һ���ڴ�ӳ�����(����)
		BufferedImage image = new BufferedImage(80, 30,BufferedImage.TYPE_INT_BGR);
		//2,��û���
		Graphics g = image.getGraphics(); 
		//3,����������ɫ
		g.setColor(new Color(255, 255, 255)); 
		//4,���û����ı�����ɫ
		g.fillRect(0, 0, 80,30);
		//5,����������ɫ
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255)));
		//6,����һ�������
		//7,��number���Ƶ������ϡ�
		String number = getNumber(5);
		//Font(����,���,��С)
		g.setFont(new Font(null, Font.ITALIC, 20)); 
		g.drawString(number, 3, 24);
		//8,�Ӹ�����
		for(int i=0;i<8;i++){
			g.setColor(new Color(0,0,0));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		/*
		 * ��,��ͼƬѹ����Ȼ�����(���͸������)
		 */
		//����content-type��Ϣͷ�����������
		//���������ص���������(��һ��jpeg��ʽ��ͼƬ)
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		//write��������ԭʼͼƬ(image)����ָ����
		//ѹ����ʽ(jpeg)����ѹ����Ȼ�󽫵õ����ֽ�
		//�����response��
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
