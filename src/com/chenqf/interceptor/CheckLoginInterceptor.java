package com.chenqf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chenqf.entity.Admin;


/**
 * ��¼��������������������Controllerҵ�񷽷�
 * ֮ǰ�жϣ���ǰ�û��Ƿ��¼�ɹ�����û�е�¼��
 * ǿ���߻ص���¼ҳ�档
 */
public class CheckLoginInterceptor implements HandlerInterceptor {
	
	//�������ʱ����
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}
	
	//Controllerҵ�񷽷�ִ�к����
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}
	//Controllerҵ�񷽷�ִ��ǰ����
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		//��session�ж�ȡ�û���Ϣ
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		//�ж��û��Ƿ��¼�ɹ�
		if(admin == null){
			//�û�û�е�¼���߻ص���¼ҳ
			response.sendRedirect(request.getContextPath()+"/main/toLogin.do");
			//����false��������������ֹ
			return false;
		}else{
			//�û��ѵ�¼����������
			return true;
		}
	}

}
