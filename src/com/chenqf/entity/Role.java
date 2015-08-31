package com.chenqf.entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer role_id;
	private String name;
	
	/**
	 * 关联属性，用来封装当前角色对应的一组模块，
	 * 该组数据由MyBatis关联映射自动装配。
	 */
	private List<Module> modules;
	
	private List<Integer> moduleIds;
	
	public Role() {
		super();
	}
	
	public Role(Integer roleId, String name, List<Module> modules) {
		super();
		role_id = roleId;
		this.name = name;
		this.modules = modules;
	}


	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public List<Module> getModules() {
		return modules;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role_id == null) ? 0 : role_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (role_id == null) {
			if (other.role_id != null)
				return false;
		} else if (!role_id.equals(other.role_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Role [modules=" + modules + ", name=" + name + ", role_id="
				+ role_id + "]";
	}

	public void setModuleIds(List<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public List<Integer> getModuleIds() {
		return moduleIds;
	}
	
}
