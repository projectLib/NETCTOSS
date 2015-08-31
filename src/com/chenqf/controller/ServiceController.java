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
//@SessionAttributes注解的作用是将制定的变量作用域声明为session级别。
@SessionAttributes("servicePage")
public class ServiceController extends BaseController {
	
	@Resource
	private ServiceMapper serviceMapper;
	
	@Resource
	private AccountMapper accountMapper;
	
	@Resource
	private CostMapper costMapper;
	
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
	@RequestMapping("/serviceList.do")
	/**
	 * 访问该方法时，Spring不会直接new servicePage，
	 * 而是先尝试从session中取servicePage对象给参数赋值，
	 * 如果从session中没有取到值则才会new。
	 * Spring会以参数类型名(首字母小写)为key从session取值。
	 * 因此这里如果希望能够从session中取出该对象，
	 * 那么当初向session中存对象时，名称应该是servicePage
	 */
	public String findAll(ServicePage page,Model model){
		List<Map<String, Object>> list = serviceMapper.findByPage(page);
		model.addAttribute("services", list);
		
		int rows = serviceMapper.findRows();
		page.setRows(rows);
		/*
		 * @SessionAttributes注解声明了servicePage变量,因此这里Spring Mvc
		 * 会使用session来传递该变量的值，而默认情况下是使用request
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
		//设置默认值
		//新增业务默认是开通状态
		service.setStatus("0");
		//创建时间默认为系统时间
		service.setCreate_date(new Timestamp(System.currentTimeMillis()));
		//添加数据到数据库
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
		//开通业务，要检查财务账号，是否为开通状态
		serviceMapper.deleteService(service_id);
		result.put("success", true);
		result.put("message", "删除成功.");
		return result;
	}
	
	//开通业务
	@RequestMapping("/updateByStatusToStartService.do")
	@ResponseBody
	public Map<String, Object> updateByStatusToStartService(Integer service_id){
		Map<String, Object> result = new HashMap<String, Object>();
		///校验账务账号是否开通
		Service service = serviceMapper.findById(service_id);
		Account account = accountMapper.findById(service.getAccount_id());
		System.out.println(service_id);
		if(account.getStatus().equals("0")) {
			//账务账号开通，允许开通业务账号
			serviceMapper.updateByStatusToStart(service_id);
			result.put("success", true);//执行成功
			result.put("message", "开通成功.");//提示
		} else {
			//账务账号未开通，不允许开通业务账号
			result.put("success", false);
			result.put("message", "开通失败，账务账号未开通.");
		}
		
		//{"success":true,"message":"开通成功."}
		return result;
		
	}
	//暂停业务
	@RequestMapping("/updateByStatusToPauseService.do")
	@ResponseBody
	public Map<String, Object> updateByStatusToPauseService(Integer service_id,String status){
		Map<String, Object> result = new HashMap<String, Object>();
		//开通业务，要检查财务账号，是否为开通状态
		serviceMapper.updateByStatusToPause(service_id);
		result.put("success", true);
		result.put("message", "暂停成功.");
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
