package com.chenqf.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.chenqf.dao.CostMapper;
import com.chenqf.entity.Cost;
import com.chenqf.entity.page.CostPage;

@Controller
@RequestMapping("/cost")
//@SessionAttributes注解的作用是将制定的变量作用域声明为session级别。
@SessionAttributes("costPage")
public class CostController extends BaseController {
	
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
	@RequestMapping("/costList.do")
	/**
	 * 访问该方法时，Spring不会直接new CostPage，
	 * 而是先尝试从session中取costPage对象给参数赋值，
	 * 如果从session中没有取到值则才会new。
	 * Spring会以参数类型名(首字母小写)为key从session取值。
	 * 因此这里如果希望能够从session中取出该对象，
	 * 那么当初向session中存对象时，名称应该是costPage
	 */
	public String findAll(CostPage page,Model model){
		List<Cost> list = costMapper.findByPage(page);
		model.addAttribute("costs", list);
		
		int rows = costMapper.findRows();
		page.setRows(rows);
		/*
		 * @SessionAttributes注解声明了costPage变量,因此这里Spring Mvc
		 * 会使用session来传递该变量的值，而默认情况下是使用request
		 */
		model.addAttribute("costPage", page);
		return "cost/cost_list";
	}
	
	@RequestMapping("/toAddCost.do")
	public String toAdd(){
		Integer.valueOf("adc");
		return "cost/add_cost";
	}
	
	@RequestMapping("/addCost.do")
	public String addCost(Cost cost){
		//设置默认值
		//新增资费状态默认是暂停态
		cost.setStatus("1");
		//创建时间默认为系统时间
		cost.setCreatime(new Timestamp(System.currentTimeMillis()));
		//添加自费数据到数据库
		costMapper.addCost(cost);
		return "redirect:costList.do";
	}
	
	@RequestMapping("/toUpdateCost.do")
	public String toUpdateCost(Integer cost_id,Model model){
		Cost cost = costMapper.findById(cost_id);
		model.addAttribute("cost", cost);
		return "cost/cost_update";
	}
	
	@RequestMapping("/updateCost.do")
	public String updateCost(Cost cost){
		costMapper.updateCost(cost);
		return "redirect:costList.do";
	}
	
	@RequestMapping("/deleteCost.do")
	public String deleteCost(Integer cost_id){
		costMapper.deleteCost(cost_id);
		return "redirect:costList.do";
	}
	
	@RequestMapping("/updateByStatusCost.do")
	public String updateByStatusCost(Integer cost_id,String status){
		Cost cost = new Cost();
		cost.setCost_id(cost_id);
		cost.setStatus(status);
		costMapper.updateByStatus(cost);
		return "redirect:costList.do";
	}
	
	@RequestMapping("/toFindById.do")
	public String toFindByIdCost(Integer cost_id,Model model){
		Cost cost = costMapper.findById(cost_id);
		model.addAttribute("cost", cost);
		return "cost/cost_detail";
	}
}
