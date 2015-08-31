package com.chenqf.dao;

import java.util.List;
import java.util.Map;

import com.chenqf.annotation.MyBatisRepository;
import com.chenqf.entity.Module;
import com.chenqf.entity.Role;
import com.chenqf.entity.page.RolePage;

@MyBatisRepository
public interface RoleMapper {
	
	List<Role> findByPage(RolePage page);
	
	Integer findRows();
	
	/**
	 * 通常情况下，一张表应该有自己独立的Mapper接口和实现，但是module_info
	 * 表没有独立的功能，因此我们写在了一张RoleMapper中
	 */ 
	List<Module> findAllModule();
	
	void addRole(Role role);
	
	void addRoleModule(Map<String, Object> moduleMap);
	
	Role findById(Integer role_id);
	
	/** 通过name查找Role */
	Role findByName(String name);
	
	void updateRole(Role role);
	
	void deleteRole(Integer role_id);
	
	void deleteRoleModule(Integer role_id);
	
	List<Role> findAll();
}
