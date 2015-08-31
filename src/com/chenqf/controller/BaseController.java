package com.chenqf.controller;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.chenqf.util.DateEditor;

/**
 * ����Controller�ĸ���
 * @author Administrator
 *
 */
public class BaseController implements WebBindingInitializer{

	/* 
	 * binder�������Ը�ָ�����������Ͱ�����ת������
	 * ���ǿ����������޸���������ת������
	 */
	//@InitBinderע�����ø÷���������ʼʱ����һ�Σ�
	//����Controller����ִ��ǰ���õġ�
	@InitBinder
	public void initBinder(WebDataBinder builder, WebRequest request) {
		//��java.sql.Date���Ͱ��Զ���ת����CustomDateEditor��
		//��ת������Spring�ṩ��Ҳ�����Լ�д��
		//��ת������2����������1��������SimpleDateFormat��
		//����ָ�����ڵĸ�ʽ����2���������Ƿ�Ϊ�ա�
		builder.registerCustomEditor(
				java.sql.Date.class, new DateEditor()
				);
	}

}
