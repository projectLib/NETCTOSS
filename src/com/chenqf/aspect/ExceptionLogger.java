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
 *	��¼�쳣��־
 */
@Component
@Aspect
public class ExceptionLogger {
	
	/**
	 * �˴�ע����request���ᵼ��ִ�в��Դ��붼����
	 * ��Ҫ����Ϊ���Դ���ִ��ʱû������tomcat��û��
	 * ������request�����޷�ע�뵼�µġ�
	 * �����ڲ���֮ǰ���˴���@Resource��ʱע�͵���
	 */
	@Resource
	private HttpServletRequest request;
	
	@Around("within(com.chenqf.controller..*)")
	public Object log(ProceedingJoinPoint point) throws Throwable{
		Object obj = null;
		try {
			//����Ŀ�����
			obj = point.proceed();
		} catch (Throwable e) {
			// ��¼������־
			Logger logger = Logger.getLogger(ExceptionLogger.class);
			//�û�����ip���쳣����ʱʱ�䡢�쳣���������뷽��
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			String adminCode = admin.getAdmin_code();
			String ip = request.getRemoteHost();
			String date = new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").format(new Date());
			String className = point.getTarget().getClass().getName();
			String methodName = point.getSignature().getName();
			
			logger.error("�û�["+adminCode+"]," +
					"IP["+ip+"],��["+date+"]," +
					"ִ��["+className+"."+methodName+"]ʱ," +
							"���������쳣��");
			//ѭ����¼ÿһ���쳣��Ϣ
			StackTraceElement[] stacks = e.getStackTrace();
			for(StackTraceElement stack : stacks){
				logger.error("\t"+stack.toString());
			}
			//�׳��쳣
			throw e;
		}
		//��Ҫ����Ŀ������ķ���ֵ
		return obj;
	}
}
