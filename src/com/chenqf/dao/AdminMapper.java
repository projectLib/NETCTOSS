package com.chenqf.dao;

import java.util.List;
import java.util.Map;

import com.chenqf.annotation.MyBatisRepository;
import com.chenqf.entity.Admin;
import com.chenqf.entity.Module;
import com.chenqf.entity.Role;
import com.chenqf.entity.page.AdminPage;

@MyBatisRepository
public interface AdminMapper {
	
	List<Admin> findByPage(AdminPage page);
	
	Integer findRows();
	
	/**
	 * 通常情况下，一张表应该有自己独立的Mapper接口和实现，但是module_info
	 * 表没有独立的功能，因此我们写在了一张AdminMapper中
	 */ 
	List<Role> findAllRole();
	
	void addAdmin(Admin admin);
	
	void addAdminRole(Map<String, Object> moduleMap);
	
	//Admin findById(Integer admin_id);
	Admin findById(Integer admin_id);
	
	/** 通过name查找Admin */
	Admin findByName(String name);
	/** 通过name查找Admin */
	Admin findByAdminCode(String adminCode);
	
	void updateAdmin(Admin admin);
	
	void deleteAdmin(Integer admin_id);
	
	void deleteAdminRole(Integer admin_id);
	
	List<Admin> findAll();
	
	/**
	 * 修改密码
	 * Map中封装了2个参数
	 * 
	 * key				value
	 * adminIds			List<Integer>
	 * defaultPassword	String
	 * 
	 */
	void updatePassword(Map<String, Object> param);
	
	/**
	 * 查询指定的管理员对应的模块
	 */
	List<Module> findModuleByAdmin(Integer admin_id);
	
	List<Role> findRoleByAdminId(Integer adminId);
}
