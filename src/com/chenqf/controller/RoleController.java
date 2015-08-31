package com.chenqf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenqf.dao.RoleMapper;
import com.chenqf.entity.Module;
import com.chenqf.entity.Role;
import com.chenqf.entity.page.RolePage;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	@Resource
	private RoleMapper roleMapper;
	
	
	@RequestMapping("/roleList.do")
	public String findPage(RolePage page,Model model){
		List<Role> roles = roleMapper.findByPage(page);
		model.addAttribute("roles", roles);
		
		int rows = roleMapper.findRows();
		page.setRows(rows);
		model.addAttribute("rolePage", page);
		
		return "role/role_list";
	}
	
	@RequestMapping("/toAddRole.do")
	public String toAdd(Model model){
		List<Module> modules = roleMapper.findAllModule();
		model.addAttribute("modules", modules);
		return "role/role_add";
	}
	
	@RequestMapping("/addRole.do")
	public String add(Role role){
		roleMapper.addRole(role);
		List<Integer> moduleIds = role.getModuleIds();
		if(moduleIds!=null&&moduleIds.size()>0){
			for(Integer module_id : moduleIds){
				Map<String, Object> moduleMap = new HashMap<String, Object>();
				moduleMap.put("role_id", role.getRole_id());
				moduleMap.put("module_id", module_id);
				roleMapper.addRoleModule(moduleMap);
			}
		}
		return "redirect:roleList.do"; 
	}
	
	@RequestMapping("/toUpdateRole.do")
	public String toUpdateRole(Integer role_id,Model model){
		//查询全部的模块，用来创建checkbox
		List<Module> list = roleMapper.findAllModule();
		model.addAttribute("modules", list);
		//查询要修改的角色
		Role role = roleMapper.findById(role_id);
		model.addAttribute("role", role);
		return "role/role_update";
	}
	
	@RequestMapping("/updateRole.do")
	public String updateRole(Role role){
		roleMapper.updateRole(role);
		roleMapper.deleteRoleModule(role.getRole_id());
		List<Integer> moduleIds = role.getModuleIds();
		if(moduleIds!=null&&moduleIds.size()>0){
			for(Integer module_id : moduleIds){
				Map<String, Object> moduleMap = new HashMap<String, Object>();
				moduleMap.put("role_id", role.getRole_id());
				moduleMap.put("module_id", module_id);
				roleMapper.addRoleModule(moduleMap);
			}
		}
		return "redirect:roleList.do";
	}
	
	@RequestMapping("/checkName.do")
	@ResponseBody
	public boolean checkName(String name){
		Role role = roleMapper.findByName(name);
		if(role==null){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping("/deleteRole.do")
	public String deleteRole(Integer role_id){
		roleMapper.deleteRoleModule(role_id);
		roleMapper.deleteRole(role_id);
		return "redirect:roleList.do";
	}
	
}
