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
//@SessionAttributesע��������ǽ��ƶ��ı�������������Ϊsession����
@SessionAttributes("costPage")
public class CostController extends BaseController {
	
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
	@RequestMapping("/costList.do")
	/**
	 * ���ʸ÷���ʱ��Spring����ֱ��new CostPage��
	 * �����ȳ��Դ�session��ȡcostPage�����������ֵ��
	 * �����session��û��ȡ��ֵ��Ż�new��
	 * Spring���Բ���������(����ĸСд)Ϊkey��sessionȡֵ��
	 * ����������ϣ���ܹ���session��ȡ���ö���
	 * ��ô������session�д����ʱ������Ӧ����costPage
	 */
	public String findAll(CostPage page,Model model){
		List<Cost> list = costMapper.findByPage(page);
		model.addAttribute("costs", list);
		
		int rows = costMapper.findRows();
		page.setRows(rows);
		/*
		 * @SessionAttributesע��������costPage����,�������Spring Mvc
		 * ��ʹ��session�����ݸñ�����ֵ����Ĭ���������ʹ��request
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
		//����Ĭ��ֵ
		//�����ʷ�״̬Ĭ������̬ͣ
		cost.setStatus("1");
		//����ʱ��Ĭ��Ϊϵͳʱ��
		cost.setCreatime(new Timestamp(System.currentTimeMillis()));
		//����Է����ݵ����ݿ�
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
