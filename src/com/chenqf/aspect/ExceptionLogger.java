package com.chenqf.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.chenqf.entity.Admin;

/**
 *	记录异常日志
 */
@Component
@Aspect
public class ExceptionLogger {
	
	/**
	 * 此处注入了request，会导致执行测试代码都报错。
	 * 主要是以为测试代码执行时没有启动tomcat，没有
	 * 创建出request对象，无法注入导致的。
	 * 可以在测试之前将此处的@Resource暂时注释掉。
	 */
	@Resource
	private HttpServletRequest request;
	
	@Around("within(com.chenqf.controller..*)")
	public Object log(ProceedingJoinPoint point) throws Throwable{
		Object obj = null;
		try {
			//调用目标组件
			obj = point.proceed();
		} catch (Throwable e) {
			// 记录错误日志
			Logger logger = Logger.getLogger(ExceptionLogger.class);
			//用户名、ip、异常发生时时间、异常发生的类与方法
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			String adminCode = admin.getAdmin_code();
			String ip = request.getRemoteHost();
			String date = new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").format(new Date());
			String className = point.getTarget().getClass().getName();
			String methodName = point.getSignature().getName();
			
			logger.error("用户["+adminCode+"]," +
					"IP["+ip+"],在["+date+"]," +
					"执行["+className+"."+methodName+"]时," +
							"发生如下异常：");
			//循环记录每一行异常信息
			StackTraceElement[] stacks = e.getStackTrace();
			for(StackTraceElement stack : stacks){
				logger.error("\t"+stack.toString());
			}
			//抛出异常
			throw e;
		}
		//不要丢掉目标组件的返回值
		return obj;
	}
}
