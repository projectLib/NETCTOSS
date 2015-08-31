package com.chenqf.dao;

import java.util.List;

import com.chenqf.annotation.MyBatisRepository;
import com.chenqf.entity.Cost;
import com.chenqf.entity.page.CostPage;

@MyBatisRepository
public interface CostMapper {
	List<Cost> findAll();
	Cost findById(Integer Cost_id);
	void addCost(Cost cost);
	void updateCost(Cost cost);
	void updateByStatus(Cost cost);
	void deleteCost(Integer cost_id);
	List<Cost> findByPage(CostPage page);
	Integer findRows();
}
