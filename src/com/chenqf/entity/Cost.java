package com.chenqf.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 1.所有属性都用封装类型，可以为null；
 * 2.属性都与字段同名，可以简化Mapper.xml;
 * 3.日期类型使用java.sql.类型
 * 	java.sql.Date	yyyy-MM-dd
 * 	java.sql.Time	HH:mm:ss
 * 	java.sql.Timestamp	yyyy-MM-dd HH:mm:ss
 * 
 * cost_id			number(4) constraint cost_chenqf_id_pk primary key,
  	name 			varchar(50)  not null,
  	base_duration 	number(11),
  	base_cost 		number(7,2),
  	unit_cost		number(7,4),
  	status 			char(1),
  	descr 			varchar2(100),
  	creatime 		date default sysdate ,
  	startime 		date,
	cost_type		char(1)
 */
public class Cost implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cost_id;
	private String name;
	private Integer base_duration;
	private Double base_cost;
	private Double unit_cost;
	private String status;
	private String descr;
	private Timestamp creatime;
	private Timestamp startime;
	private String cost_type;
	
	public Cost() {
		super();
	}

	public Cost(Integer costId, String name, Integer baseDuration,
			Double baseCost, Double unitCost, String status, String descr,
			Timestamp creatime, Timestamp startime, String costType) {
		super();
		cost_id = costId;
		this.name = name;
		base_duration = baseDuration;
		base_cost = baseCost;
		unit_cost = unitCost;
		this.status = status;
		this.descr = descr;
		this.creatime = creatime;
		this.startime = startime;
		cost_type = costType;
	}

	public Integer getCost_id() {
		return cost_id;
	}

	public void setCost_id(Integer costId) {
		cost_id = costId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBase_duration() {
		return base_duration;
	}

	public void setBase_duration(Integer baseDuration) {
		base_duration = baseDuration;
	}

	public Double getBase_cost() {
		return base_cost;
	}

	public void setBase_cost(Double baseCost) {
		base_cost = baseCost;
	}

	public Double getUnit_cost() {
		return unit_cost;
	}

	public void setUnit_cost(Double unitCost) {
		unit_cost = unitCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Timestamp getCreatime() {
		return creatime;
	}

	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}

	public Timestamp getStartime() {
		return startime;
	}

	public void setStartime(Timestamp startime) {
		this.startime = startime;
	}

	public String getCost_type() {
		return cost_type;
	}

	public void setCost_type(String costType) {
		cost_type = costType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost_id == null) ? 0 : cost_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cost other = (Cost) obj;
		if (cost_id == null) {
			if (other.cost_id != null)
				return false;
		} else if (!cost_id.equals(other.cost_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cost [cost_id=" + cost_id + ", name=" + name
				+ ", base_duration=" + base_duration + ", base_cost="
				+ base_cost + ", unit_cost=" + unit_cost + ", status=" + status
				+ ", descr=" + descr + ", creatime=" + creatime + ", startime="
				+ startime + ", cost_type=" + cost_type + "]";
	}
	
}
