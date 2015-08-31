package com.chenqf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chenqf.entity.Admin;


/**
 * 登录检查拦截器，在请求访问Controller业务方法
 * 之前判断，当前用户是否登录成功，若没有登录则
 * 强制踢回到登录页面。
 */
public class CheckLoginInterceptor implements HandlerInterceptor {
	
	//请求结束时调用
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}
	
	//Controller业务方法执行后调用
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}
	//Controller业务方法执行前调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		//从session中读取用户信息
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		//判断用户是否登录成功
		if(admin == null){
			//用户没有登录，踢回到登录页
			response.sendRedirect(request.getContextPath()+"/main/toLogin.do");
			//返回false，将本次请求终止
			return false;
		}else{
			//用户已登录，继续访问
			return true;
		}
	}

}
