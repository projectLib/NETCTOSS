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
	 * ͨ������£�һ�ű�Ӧ�����Լ�������Mapper�ӿں�ʵ�֣�����module_info
	 * ��û�ж����Ĺ��ܣ��������д����һ��RoleMapper��
	 */ 
	List<Module> findAllModule();
	
	void addRole(Role role);
	
	void addRoleModule(Map<String, Object> moduleMap);
	
	Role findById(Integer role_id);
	
	/** ͨ��name����Role */
	Role findByName(String name);
	
	void updateRole(Role role);
	
	void deleteRole(Integer role_id);
	
	void deleteRoleModule(Integer role_id);
	
	List<Role> findAll();
}
