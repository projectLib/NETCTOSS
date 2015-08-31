package com.chenqf.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chenqf.dao.AdminMapper;
import com.chenqf.entity.Role;

public class TestAdmin {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Test
	public void testAdminList(){
		AdminMapper adminMapper = ctx.getBean(AdminMapper.class);
//		AdminPage page = new AdminPage();
//		page.setCurrentPage(2);
//		List<Admin> list =  adminMapper.findByPage(page);
//		int rows = adminMapper.findRows();
//		System.out.println(rows);
//		System.out.println(page.getTotalPage());
//		System.out.println(page.getCurrentPage());
//		System.out.println(page.getBegin()+","+page.getEnd());
//		for(Admin a : list){
//			System.out.println(a.getName()+""+a.getTelephone());
//		}
		List<Role> roles = adminMapper.findRoleByAdminId(2000);
		System.out.println(roles.size());
		for(Role r : roles){
			System.out.println(r.getName());
		}
		
	}
}
