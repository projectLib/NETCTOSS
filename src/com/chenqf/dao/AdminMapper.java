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
	 * ͨ������£�һ�ű�Ӧ�����Լ�������Mapper�ӿں�ʵ�֣�����module_info
	 * ��û�ж����Ĺ��ܣ��������д����һ��AdminMapper��
	 */ 
	List<Role> findAllRole();
	
	void addAdmin(Admin admin);
	
	void addAdminRole(Map<String, Object> moduleMap);
	
	//Admin findById(Integer admin_id);
	Admin findById(Integer admin_id);
	
	/** ͨ��name����Admin */
	Admin findByName(String name);
	/** ͨ��name����Admin */
	Admin findByAdminCode(String adminCode);
	
	void updateAdmin(Admin admin);
	
	void deleteAdmin(Integer admin_id);
	
	void deleteAdminRole(Integer admin_id);
	
	List<Admin> findAll();
	
	/**
	 * �޸�����
	 * Map�з�װ��2������
	 * 
	 * key				value
	 * adminIds			List<Integer>
	 * defaultPassword	String
	 * 
	 */
	void updatePassword(Map<String, Object> param);
	
	/**
	 * ��ѯָ���Ĺ���Ա��Ӧ��ģ��
	 */
	List<Module> findModuleByAdmin(Integer admin_id);
	
	List<Role> findRoleByAdminId(Integer adminId);
}
