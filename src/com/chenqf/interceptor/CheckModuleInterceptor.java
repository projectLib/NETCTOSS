package com.chenqf.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chenqf.entity.Module;

/**
 *	判断当前访问的是哪一个模块，将结果module_id
 *	存入session。
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
		//获取用户可以访问的模块
		HttpSession session = request.getSession();
		List<Module> modules = (List<Module>) session.getAttribute("adminModules");
		//获取当前访问的模块
		Integer moduleId = (Integer) session.getAttribute("currentModuleId");
		//判断用户有权限的模块是否包含当前模块
		for(Module mo : modules){
			if(moduleId==mo.getModule_id()){
				//有当前访问模块的权限
				return true;
			}
		}
		//没有当前访问模块的权限
		response.sendRedirect(request.getContextPath() +"/main/nopower.do");
		return false;
	}

}
