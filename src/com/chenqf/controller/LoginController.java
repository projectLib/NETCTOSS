package com.chenqf.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenqf.dao.AdminMapper;
import com.chenqf.entity.Admin;
import com.chenqf.entity.Module;
import com.chenqf.util.ImageUtil;

@Controller
@RequestMapping("/main")
public class LoginController extends BaseController{
	
	private static final int SUCCESS_MSG = 0;
	private static final int LOGIN_NAME_MSG_ERR = 1;
	private static final int PASSWORD_MSG_ERR = 2;
	private static final int IMAGE_MSG_ERR = 3;
	
	@Resource
	private AdminMapper adminMapper;
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "main/login";
	}

	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "main/index";
	}
	
	@RequestMapping("/nopower.do")
	public String toNopower(){
		return "main/nopower";
	}
	@RequestMapping("/checkLogin.do")
	@ResponseBody
	public Integer check_login(String admin_code,String password,String userCode,HttpSession session){
		Admin admin = adminMapper.findByAdminCode(admin_code);
		String code = (String)session.getAttribute("imageCode");
		if(userCode==null || !userCode.equalsIgnoreCase(code)){
			//验证码错误
			return IMAGE_MSG_ERR;
		}
		if(admin == null){
			return LOGIN_NAME_MSG_ERR;
		}else if(!admin.getPassword().equals(password)){
			return PASSWORD_MSG_ERR;
		}else{
			//验证通过，将信息存入session
			session.setAttribute("admin", admin);
			List<Module> modules = adminMapper.findModuleByAdmin(admin.getAdmin_id());
			session.setAttribute("adminModules", modules);
			return SUCCESS_MSG;
		}
	}
	
	
	//创建图片
	@RequestMapping("/createImage.do")
	public void createImage(HttpSession session,HttpServletResponse response) throws IOException{
		//创建验证码图片
		Map<String, BufferedImage> map = ImageUtil.createImage();
		//将验证码存入session，方便登录时校验
		/*
		 * Set<K> 	   keySet()   返回此映射中包含的键的 Set 视图。 
		 * Iterator<E> iterator() 返回在此 set 中的元素上进行迭代的迭代器。 
		 * E           next()     返回迭代的下一个元素。 
		 */
		String code = map.keySet().iterator().next();
		session.setAttribute("imageCode", code);
		//将图片输出到页面
		BufferedImage image = map.get(code);
		OutputStream os = response.getOutputStream();
		response.setContentType("image/jpeg");
		ImageIO.write(image, "jpeg", os);
		os.close();
	}
	
	@RequestMapping("/exit.do")
	public String exit(HttpSession session){
		//使session无效，解除所有对象的绑定。
		session.invalidate();
		//void removeAttribute(java.lang.String name)  从session中删除指定名称绑定的对象。
		return "redirect:toLogin.do";
	}
}
