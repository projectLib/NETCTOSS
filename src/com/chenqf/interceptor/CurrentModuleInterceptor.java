package com.chenqf.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *	�жϵ�ǰ���ʵ�����һ��ģ�飬�����module_id
 *	����session��
 */
public class CurrentModuleInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response,
			Object obj) throws Exception {
		//��ȡ��������url
		String url = request.getRequestURL().toString();
		int m = 0;//index
		if(url.contains("role")){
			m = 1;
		} else if (url.contains("admin")) {
			m = 2;
		} else if (url.contains("cost")) {
			m = 3;
		} else if (url.contains("account")) {
			m = 4;
		} else if (url.contains("service")) {
			m = 5;
		} else if (url.contains("bill")) {
			m = 6;
		} else if (url.contains("report")) {
			m = 7;
		} else if (url.contains("userInfo")) {
			m = 8;
		} else if (url.contains("userPwd")) {
			m = 9;
		}
		
		//���жϽ������ǰģ��id����session
		request.getSession().setAttribute("currentModuleId", m);
		
		return true;
	}

}
