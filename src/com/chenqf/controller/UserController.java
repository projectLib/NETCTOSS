package com.chenqf.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenqf.dao.AdminMapper;
import com.chenqf.entity.Admin;
import com.chenqf.entity.Role;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource
	private AdminMapper adminMapper;
	
	@RequestMapping("/userInfo.do")
	public String toUserInfo(HttpSession session,Model model){
		Admin admin = (Admin) session.getAttribute("admin");
		List<Role> roles = adminMapper.findRoleByAdminId(admin.getAdmin_id());
		model.addAttribute("admin", admin);
		model.addAttribute("roles", roles);
		return "user/user_info";
	}
	@RequestMapping("/updateUserInfo.do")
	public String userInfo(Admin admin,HttpSession session,Model model){
		adminMapper.updateAdmin(admin);
		admin = adminMapper.findById(admin.getAdmin_id());
		session.setAttribute("admin", admin);
		model.addAttribute("admin", admin);
		return "main/index";
	}
	@RequestMapping("/userPwd.do")
	public String toUserUpdatePwd(){
		return "user/user_update_pwd";
	}
	@RequestMapping("/updateUserPwd.do")
	public String userUpdatePwd(String pwd,String password,HttpSession session,Model model){
		Admin admin = (Admin) session.getAttribute("admin");
		model.addAttribute("admin", admin);
		return "user/user_info";
	}
}
