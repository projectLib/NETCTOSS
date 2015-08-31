package com.chenqf.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@RequestMapping("/report")
public class ReportController {
	
	@RequestMapping("/reportList.do")
	public String reportList(){
		return "report/report_list";
	}
}
