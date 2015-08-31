package com.chenqf.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenqf.dao.AdminMapper;
import com.chenqf.dao.RoleMapper;
import com.chenqf.entity.Admin;
import com.chenqf.entity.Module;
import com.chenqf.entity.Role;
import com.chenqf.entity.page.AdminPage;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
	@Resource
	private AdminMapper adminMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	public static final String DEFAULT_PASSWORD = "123456";
	
	@RequestMapping("/adminList.do")
	public String findPage(AdminPage page,Model model){
		List<Admin> admins = adminMapper.findByPage(page);
		model.addAttribute("admins", admins);
		
		int rows = adminMapper.findRows();
		page.setRows(rows);
		model.addAttribute("adminPage", page);
		
		List<Module> modules = roleMapper.findAllModule();
		model.addAttribute("modules", modules);
		
		return "admin/admin_list";
	}
	
	@RequestMapping("/toAddAdmin.do")
	public String toAdd(Model model){
		List<Role> roles = roleMapper.findAll();
		model.addAttribute("roles", roles);
		return "admin/admin_add";
	}
	
	@RequestMapping("/addAdmin.do")
	public String add(Admin admin){
		admin.setEnrolldate(new Timestamp(System.currentTimeMillis()));
		adminMapper.addAdmin(admin);
		List<Integer> roleIds = admin.getRoleIds();
		if(roleIds!=null&&roleIds.size()>0){
			for(Integer role_id : roleIds){
				Map<String, Object> roleMap = new HashMap<String, Object>();
				roleMap.put("admin_id", admin.getAdmin_id());
				roleMap.put("role_id", role_id);
				adminMapper.addAdminRole(roleMap);
			}
		}
		return "redirect:adminList.do"; 
	}
	
	@RequestMapping("/toUpdateAdmin.do")
	public String toUpdateAdmin(Integer admin_id,Model model){
		//查询全部的模块，用来创建checkbox
		List<Role> list = adminMapper.findAllRole();
		model.addAttribute("roles", list);
		//查询要修改的角色
		Admin admin = adminMapper.findById(admin_id);
		model.addAttribute("admin", admin);
		
		
		return "admin/admin_update";
	}
	
	@RequestMapping("/updateAdmin.do")
	public String updateAdmin(Admin admin){
		adminMapper.updateAdmin(admin);
		adminMapper.deleteAdminRole(admin.getAdmin_id());
		List<Integer> roleIds = admin.getRoleIds();
		if(roleIds!=null&&roleIds.size()>0){
			for(Integer role_id : roleIds){
				Map<String, Object> roleMap = new HashMap<String, Object>();
				roleMap.put("admin_id", admin.getAdmin_id());
				roleMap.put("role_id", role_id);
				adminMapper.addAdminRole(roleMap);
			}
		}
		return "redirect:adminList.do";
	}
	
	@RequestMapping("/checkName.do")
	@ResponseBody
	public boolean checkName(String name){
		Admin admin = adminMapper.findByName(name);
		if(admin==null){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping("/deleteAdmin.do")
	public String deleteAdmin(Integer admin_id){
		adminMapper.deleteAdminRole(admin_id);
		adminMapper.deleteAdmin(admin_id);
		return "redirect:adminList.do";
	}
	
	@RequestMapping("/resetPassword.do")
	@ResponseBody
	public Map<String, Object> updatePassword(String ids) {
		//将页面传入的id字符串，切割并转换成id的集合
		List<Integer> idList = 
			new ArrayList<Integer>();
		String[] idArray = ids.split(",");
		for(String id : idArray) {
			idList.add(Integer.valueOf(id));
		}
		
		//构造参数
		Map<String, Object> param = 
			new HashMap<String, Object>();
		param.put("adminIds", idList);
		param.put("defaultPassword", DEFAULT_PASSWORD);
		
		adminMapper.updatePassword(param);
		
		Map<String, Object> result = 
			new HashMap<String, Object>();
		result.put("success", true);
		result.put("message", "密码重置成功.");
		return result;
	}
	
}
