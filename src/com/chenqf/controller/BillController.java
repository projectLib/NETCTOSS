package com.chenqf.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@RequestMapping("/bill")
public class BillController {
	
	@RequestMapping("/billList.do")
	public String billList(){
		return "bill/bill_list";
	}
}
