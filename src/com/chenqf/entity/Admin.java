package com.chenqf.entity;

import java.sql.Timestamp;
import java.util.List;

public class Admin {

	private Integer admin_id;
	private String admin_code;
	private String password;
	private String name;
	private String telephone;
	private String email;
	private Timestamp enrolldate;

	/**
	 * 关联属性，是当前管理员对应的一组角色，
	 * 在查询时由MyBatis自动装配进来。
	 */
	private List<Role> roles;
	
	
	/**
	 * 新增/修改保存时，接收用户选择的一组角色ID。
	 */
	private List<Integer> roleIds;
	
	private List<Integer> moduleIds;

	public Integer getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public String getAdmin_code() {
		return admin_code;
	}

	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(Timestamp enrolldate) {
		this.enrolldate = enrolldate;
	}

	@Override
	public String toString() {
		return "Admin [admin_code=" + admin_code + ", admin_id=" + admin_id
				+ ", email=" + email + ", enrolldate=" + enrolldate + ", name="
				+ name + ", password=" + password + ", roleIds=" + roleIds
				+ ", roles=" + roles + ", telephone=" + telephone + "]";
	}

	public void setModuleIds(List<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public List<Integer> getModuleIds() {
		return moduleIds;
	}

}
