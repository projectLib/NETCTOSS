package com.chenqf.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chenqf.entity.Module;

/**
 *	�жϵ�ǰ���ʵ�����һ��ģ�飬�����module_id
 *	����session��
 */
public class CheckModuleInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView mView) throws Exception {
		request.getSession();
	}

	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response,
			Object obj) throws Exception {
		//��ȡ�û����Է��ʵ�ģ��
		HttpSession session = request.getSession();
		List<Module> modules = (List<Module>) session.getAttribute("adminModules");
		//��ȡ��ǰ���ʵ�ģ��
		Integer moduleId = (Integer) session.getAttribute("currentModuleId");
		//�ж��û���Ȩ�޵�ģ���Ƿ������ǰģ��
		for(Module mo : modules){
			if(moduleId==mo.getModule_id()){
				//�е�ǰ����ģ���Ȩ��
				return true;
			}
		}
		//û�е�ǰ����ģ���Ȩ��
		response.sendRedirect(request.getContextPath() +"/main/nopower.do");
		return false;
	}

}
