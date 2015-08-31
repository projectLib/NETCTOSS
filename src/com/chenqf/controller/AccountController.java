package com.chenqf.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.chenqf.dao.AccountMapper;
import com.chenqf.dao.ServiceMapper;
import com.chenqf.entity.Account;
import com.chenqf.entity.page.AccountPage;

@Controller
@RequestMapping("/account")
//@SessionAttributes注解的作用是将制定的变量作用域声明为session级别。
@SessionAttributes("accountPage")
public class AccountController extends BaseController {
	
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private ServiceMapper serviceMapper;
	
	/**
	 * 分页查询资费
	 * 
	 * @param page	
	 * 	用于接收页面传入的分页条件
	 * 
	 * Spring MVC在Controller方法调用前，创建参数对象，是不依赖于容器的，
	 * 实际上它是通过类反射,读取的参数类型并创建这个参数对象的。
	 * 因此无论是否将参数放到容器中，Spring MVC都不会通过容器来创建参数对象，
	 * 因此参数对象中的依赖注入在此处是无效的。
	 */
	@RequestMapping("/accountList.do")
	/**
	 * 访问该方法时，Spring不会直接new accountPage，
	 * 而是先尝试从session中取accountPage对象给参数赋值，
	 * 如果从session中没有取到值则才会new。
	 * Spring会以参数类型名(首字母小写)为key从session取值。
	 * 因此这里如果希望能够从session中取出该对象，
	 * 那么当初向session中存对象时，名称应该是accountPage
	 */
	public String findAll(AccountPage page,Model model){
		List<Account> list = accountMapper.findByPage(page);
		model.addAttribute("accounts", list);
		
		int rows = accountMapper.findRows(page);
		page.setRows(rows);
		/*
		 * @SessionAttributes注解声明了accountPage变量,因此这里Spring Mvc
		 * 会使用session来传递该变量的值，而默认情况下是使用request
		 */
		model.addAttribute("accountPage", page);
		return "account/account_list";
	}
	
	@RequestMapping("/toAccountAdd.do")
	public String toAdd(Model model){
		List<Account> accounts = accountMapper.findAll();
		model.addAttribute("accounts", accounts);
		return "account/account_add";
	}
	
	@RequestMapping("/accountAdd.do")
	public String addaccount(Account account){
		//创建则开通，记载创建时间；
		//创建时间默认为系统时间
		//添加自费数据到数据库
		System.out.println(account.getBirthdate());
		account.setStatus("0");
		account.setCreate_date(new Timestamp(System.currentTimeMillis()));
		accountMapper.addAccount(account);
		return "redirect:accountList.do";
	}
	
	@RequestMapping("/toAccountUpdate.do")
	public String toUpdateaccount(Integer account_id,Model model){
		Account account = accountMapper.findById(account_id);
		System.out.println(account);
		model.addAttribute("account", account);
		return "account/account_update";
	}
	
	@RequestMapping("/accountUpdate.do")
	public String updateaccount(Account account){
		accountMapper.updateAccount(account);
		return "redirect:accountList.do";
	}
	
	@RequestMapping("/accountDelete.do")
	public String deleteaccount(Integer account_id){
		serviceMapper.deleteByAccountid(account_id);
		accountMapper.deleteAccount(account_id);
		return "redirect:accountList.do";
	}
	
	@RequestMapping("/accountUpdateByStatus.do")
	public String updateByStatusaccount(Integer account_id,String status){
		Account account = new Account();
		account.setAccount_id(account_id);
		account.setStatus(status);
		if(status.equals("1")){
			serviceMapper.updateByAccountid(account_id);
		}
		accountMapper.updateByStatus(account);
		return "redirect:accountList.do";
	}
	
	@RequestMapping("/toFindById.do")
	public String toFindByIdaccount(Integer account_id,Model model){
		Account account = accountMapper.findById(account_id);
		model.addAttribute("account", account);
		return "account/account_detail";
	}
}
