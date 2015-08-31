package com.chenqf.entity.page;

import java.io.Serializable;

/**
 * 所有分页组件的父类，
 * 封装了分页相关的公共参数。
 */
public abstract class Page implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 每页的行数，是固定值  */
	private int pageSize=5;
	/** 当前页数  */
	private int currentPage=1;
	
	/** 每页开始的行数（不取当前值，及上一页最后一行）  */
	private int begin;
	/** 每页结束的行数（不取当前值，及下一页第一行）  */
	private int end;
	
	/** 查询到的记录的总行数  */
	private int rows;
	/** 查询到的记录按照每页pageSize条数据，得到总页数  */
	private int totalPage;
	
	/**
	 * 页面调用totalPage属性时，是通过该get方法调用的。
	 * 在调用该方法时，计算出totalPage的值。
	 * 此计算需要依赖rows条件，而这个条件需要在页面使用
	 * totalPage属性之前传入到当前对象。
	 */
	public int getTotalPage() {
		if(rows%pageSize==0){
			totalPage = rows/pageSize;
		}else{
			totalPage = rows/pageSize+1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	public int getBegin() {
		begin = pageSize*(currentPage-1);
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		end = pageSize*currentPage+1;
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		return "Page [begin=" + begin + ", currentPage=" + currentPage
				+ ", end=" + end + ", pageSize=" + pageSize + ", rows=" + rows
				+ ", totalPage=" + totalPage + "]";
	}
	
}
