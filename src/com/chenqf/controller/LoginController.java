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
			//��֤�����
			return IMAGE_MSG_ERR;
		}
		if(admin == null){
			return LOGIN_NAME_MSG_ERR;
		}else if(!admin.getPassword().equals(password)){
			return PASSWORD_MSG_ERR;
		}else{
			//��֤ͨ��������Ϣ����session
			session.setAttribute("admin", admin);
			List<Module> modules = adminMapper.findModuleByAdmin(admin.getAdmin_id());
			session.setAttribute("adminModules", modules);
			return SUCCESS_MSG;
		}
	}
	
	
	//����ͼƬ
	@RequestMapping("/createImage.do")
	public void createImage(HttpSession session,HttpServletResponse response) throws IOException{
		//������֤��ͼƬ
		Map<String, BufferedImage> map = ImageUtil.createImage();
		//����֤�����session�������¼ʱУ��
		/*
		 * Set<K> 	   keySet()   ���ش�ӳ���а����ļ��� Set ��ͼ�� 
		 * Iterator<E> iterator() �����ڴ� set �е�Ԫ���Ͻ��е����ĵ������� 
		 * E           next()     ���ص�������һ��Ԫ�ء� 
		 */
		String code = map.keySet().iterator().next();
		session.setAttribute("imageCode", code);
		//��ͼƬ�����ҳ��
		BufferedImage image = map.get(code);
		OutputStream os = response.getOutputStream();
		response.setContentType("image/jpeg");
		ImageIO.write(image, "jpeg", os);
		os.close();
	}
	
	@RequestMapping("/exit.do")
	public String exit(HttpSession session){
		//ʹsession��Ч��������ж���İ󶨡�
		session.invalidate();
		//void removeAttribute(java.lang.String name)  ��session��ɾ��ָ�����ư󶨵Ķ���
		return "redirect:toLogin.do";
	}
}
