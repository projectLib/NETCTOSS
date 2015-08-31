package com.chenqf.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.chenqf.dao.AccountMapper;
import com.chenqf.dao.CostMapper;
import com.chenqf.dao.ServiceMapper;
import com.chenqf.entity.Account;
import com.chenqf.entity.Cost;
import com.chenqf.entity.Service;
import com.chenqf.entity.page.ServicePage;

@Controller
@RequestMapping("/service")
//@SessionAttributesע��������ǽ��ƶ��ı�������������Ϊsession����
@SessionAttributes("servicePage")
public class ServiceController extends BaseController {
	
	@Resource
	private ServiceMapper serviceMapper;
	
	@Resource
	private AccountMapper accountMapper;
	
	@Resource
	private CostMapper costMapper;
	
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
	@RequestMapping("/serviceList.do")
	/**
	 * ���ʸ÷���ʱ��Spring����ֱ��new servicePage��
	 * �����ȳ��Դ�session��ȡservicePage�����������ֵ��
	 * �����session��û��ȡ��ֵ��Ż�new��
	 * Spring���Բ���������(����ĸСд)Ϊkey��sessionȡֵ��
	 * ����������ϣ���ܹ���session��ȡ���ö���
	 * ��ô������session�д����ʱ������Ӧ����servicePage
	 */
	public String findAll(ServicePage page,Model model){
		List<Map<String, Object>> list = serviceMapper.findByPage(page);
		model.addAttribute("services", list);
		
		int rows = serviceMapper.findRows();
		page.setRows(rows);
		/*
		 * @SessionAttributesע��������servicePage����,�������Spring Mvc
		 * ��ʹ��session�����ݸñ�����ֵ����Ĭ���������ʹ��request
		 */
		model.addAttribute("servicePage", page);
		return "service/service_list";
	}
	
	@RequestMapping("/toAddService.do")
	public String toAdd(Model model){
		List<Cost> costs = costMapper.findAll();
		model.addAttribute("costs", costs);
		return "service/service_add";
	}
	
	@RequestMapping("/addService.do")
	public String addservice(Service service){
		//����Ĭ��ֵ
		//����ҵ��Ĭ���ǿ�ͨ״̬
		service.setStatus("0");
		//����ʱ��Ĭ��Ϊϵͳʱ��
		service.setCreate_date(new Timestamp(System.currentTimeMillis()));
		//������ݵ����ݿ�
		serviceMapper.addService(service);
		return "redirect:serviceList.do";
	}
	
	@RequestMapping("/toUpdateService.do")
	public String toUpdateservice(Integer service_id,Model model){
		Service service = serviceMapper.findById(service_id);
		List<Cost> costs = costMapper.findAll();
		model.addAttribute("service", service);
		model.addAttribute("costs", costs);
		return "service/service_update";
	}
	
	@RequestMapping("/updateService.do")
	public String updateservice(Service service){
		System.out.println(service.getService_id()+","+service.getCost_id());
		serviceMapper.updateService(service);
		return "redirect:serviceList.do";
	}
	
	@RequestMapping("/deleteService.do")
	@ResponseBody
	public Map<String, Object> deleteservice(Integer service_id){
		Map<String, Object> result = new HashMap<String, Object>();
		//��ͨҵ��Ҫ�������˺ţ��Ƿ�Ϊ��ͨ״̬
		serviceMapper.deleteService(service_id);
		result.put("success", true);
		result.put("message", "ɾ���ɹ�.");
		return result;
	}
	
	//��ͨҵ��
	@RequestMapping("/updateByStatusToStartService.do")
	@ResponseBody
	public Map<String, Object> updateByStatusToStartService(Integer service_id){
		Map<String, Object> result = new HashMap<String, Object>();
		///У�������˺��Ƿ�ͨ
		Service service = serviceMapper.findById(service_id);
		Account account = accountMapper.findById(service.getAccount_id());
		System.out.println(service_id);
		if(account.getStatus().equals("0")) {
			//�����˺ſ�ͨ������ͨҵ���˺�
			serviceMapper.updateByStatusToStart(service_id);
			result.put("success", true);//ִ�гɹ�
			result.put("message", "��ͨ�ɹ�.");//��ʾ
		} else {
			//�����˺�δ��ͨ��������ͨҵ���˺�
			result.put("success", false);
			result.put("message", "��ͨʧ�ܣ������˺�δ��ͨ.");
		}
		
		//{"success":true,"message":"��ͨ�ɹ�."}
		return result;
		
	}
	//��ͣҵ��
	@RequestMapping("/updateByStatusToPauseService.do")
	@ResponseBody
	public Map<String, Object> updateByStatusToPauseService(Integer service_id,String status){
		Map<String, Object> result = new HashMap<String, Object>();
		//��ͨҵ��Ҫ�������˺ţ��Ƿ�Ϊ��ͨ״̬
		serviceMapper.updateByStatusToPause(service_id);
		result.put("success", true);
		result.put("message", "��ͣ�ɹ�.");
		return result;
	}
	
	@RequestMapping("/toFindById.do")
	public String toFindByIdservice(Integer service_id,Model model){
		Service service = serviceMapper.findById(service_id);
		Cost cost = costMapper.findById(service.getCost_id());
		Account account = accountMapper.findById(service.getAccount_id());
		model.addAttribute("service", service);
		model.addAttribute("cost", cost);
		model.addAttribute("account", account);
		return "service/service_detail";
	}
	
	@RequestMapping("/searchAccount.do")
	@ResponseBody
	public Account searchAccount(String idcardNo) {
		return accountMapper.findByIdcardNo(idcardNo);
	}
}
