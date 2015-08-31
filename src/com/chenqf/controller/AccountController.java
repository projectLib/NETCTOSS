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
//@SessionAttributesע��������ǽ��ƶ��ı�������������Ϊsession����
@SessionAttributes("accountPage")
public class AccountController extends BaseController {
	
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private ServiceMapper serviceMapper;
	
	/**
	 * ��ҳ��ѯ�ʷ�
	 * 
	 * @param page	
	 * 	���ڽ���ҳ�洫��ķ�ҳ����
	 * 
	 * Spring MVC��Controller��������ǰ���������������ǲ������������ģ�
	 * ʵ��������ͨ���෴��,��ȡ�Ĳ������Ͳ����������������ġ�
	 * ��������Ƿ񽫲����ŵ������У�Spring MVC������ͨ��������������������
	 * ��˲��������е�����ע���ڴ˴�����Ч�ġ�
	 */
	@RequestMapping("/accountList.do")
	/**
	 * ���ʸ÷���ʱ��Spring����ֱ��new accountPage��
	 * �����ȳ��Դ�session��ȡaccountPage�����������ֵ��
	 * �����session��û��ȡ��ֵ��Ż�new��
	 * Spring���Բ���������(����ĸСд)Ϊkey��sessionȡֵ��
	 * ����������ϣ���ܹ���session��ȡ���ö���
	 * ��ô������session�д����ʱ������Ӧ����accountPage
	 */
	public String findAll(AccountPage page,Model model){
		List<Account> list = accountMapper.findByPage(page);
		model.addAttribute("accounts", list);
		
		int rows = accountMapper.findRows(page);
		page.setRows(rows);
		/*
		 * @SessionAttributesע��������accountPage����,�������Spring Mvc
		 * ��ʹ��session�����ݸñ�����ֵ����Ĭ���������ʹ��request
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
		//������ͨ�����ش���ʱ�䣻
		//����ʱ��Ĭ��Ϊϵͳʱ��
		//����Է����ݵ����ݿ�
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
