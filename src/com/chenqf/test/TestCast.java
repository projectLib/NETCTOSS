package com.chenqf.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chenqf.dao.AccountMapper;
import com.chenqf.dao.CostMapper;
import com.chenqf.dao.ServiceMapper;
import com.chenqf.entity.Account;
import com.chenqf.entity.Cost;
import com.chenqf.entity.Service;
import com.chenqf.entity.page.AccountPage;



public class TestCast {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//@Test
	public void testAddCost(){
		CostMapper mapper = ctx.getBean(CostMapper.class);
		Cost cost = new Cost();
		cost.setName("Testss");
		cost.setBase_duration(20);
		cost.setCost_type("1");
		mapper.addCost(cost);
	}
	//@Test
	public void testFindByPage(){
		AccountMapper mapper = ctx.getBean(AccountMapper.class);
		AccountPage page = new AccountPage();
		List<Account> list = mapper.findByPage(page);
		for(Account a : list){
			System.out.println(a.getAccount_id()+","+a.getReal_name()+","+a.getIdcard_no());
		}
	}
	@Test
	public void testServiceUpdate(){
		ServiceMapper mapper = ctx.getBean(ServiceMapper.class);
		Service service = new Service();
		service.setService_id(2002);
		service.setCost_id(2);
		mapper.updateService(service);
		System.out.println(service);
	}
}
