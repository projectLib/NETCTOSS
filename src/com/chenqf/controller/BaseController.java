package com.chenqf.controller;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.chenqf.util.DateEditor;

/**
 * 所有Controller的父类
 * @author Administrator
 *
 */
public class BaseController implements WebBindingInitializer{

	/* 
	 * binder参数可以给指定的数据类型绑定类型转换器；
	 * 我们可以利用它修改日期类型转换器。
	 */
	//@InitBinder注解是让该方法在请求开始时调用一次，
	//是在Controller方法执行前调用的。
	@InitBinder
	public void initBinder(WebDataBinder builder, WebRequest request) {
		//给java.sql.Date类型绑定自定义转换器CustomDateEditor，
		//该转换器由Spring提供，也可以自己写。
		//该转换器有2个参数，第1个参数是SimpleDateFormat，
		//用于指定日期的格式，第2个参数是是否为空。
		builder.registerCustomEditor(
				java.sql.Date.class, new DateEditor()
				);
	}

}
