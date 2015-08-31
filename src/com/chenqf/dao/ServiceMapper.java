package com.chenqf.dao;

import java.util.List;
import java.util.Map;

import com.chenqf.annotation.MyBatisRepository;
import com.chenqf.entity.Service;
import com.chenqf.entity.page.ServicePage;

@MyBatisRepository
public interface ServiceMapper {
	Service findById(Integer service_id);
	void addService(Service service);
	void updateService(Service service);
	void updateByStatusToStart(Integer service_id);
	void updateByStatusToPause(Integer service_id);
	void deleteService(Integer service_id);
	List<Map<String, Object>>  findByPage(ServicePage page);
	Integer findRows();
	void updateByAccountid(Integer account_id);
	void deleteByAccountid(Integer account_id);
	
}
