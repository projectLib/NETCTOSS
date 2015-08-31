package com.chenqf.entity.page;

import java.io.Serializable;

/**
 * ���з�ҳ����ĸ��࣬
 * ��װ�˷�ҳ��صĹ���������
 */
public abstract class Page implements Serializable {

	private static final long serialVersionUID = 1L;
	/** ÿҳ���������ǹ̶�ֵ  */
	private int pageSize=5;
	/** ��ǰҳ��  */
	private int currentPage=1;
	
	/** ÿҳ��ʼ����������ȡ��ǰֵ������һҳ���һ�У�  */
	private int begin;
	/** ÿҳ��������������ȡ��ǰֵ������һҳ��һ�У�  */
	private int end;
	
	/** ��ѯ���ļ�¼��������  */
	private int rows;
	/** ��ѯ���ļ�¼����ÿҳpageSize�����ݣ��õ���ҳ��  */
	private int totalPage;
	
	/**
	 * ҳ�����totalPage����ʱ����ͨ����get�������õġ�
	 * �ڵ��ø÷���ʱ�������totalPage��ֵ��
	 * �˼�����Ҫ����rows�����������������Ҫ��ҳ��ʹ��
	 * totalPage����֮ǰ���뵽��ǰ����
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
