package com.chenqf.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer account_id;
	private Integer recommender_id;
	private String login_name;
	private String login_passwd;
	private String status;
	private Timestamp create_date;
	private Timestamp pause_date;
	private Timestamp close_date;
	private String real_name;
	private String idcard_no;
	private Date birthdate;
	private String gender;
	private String occupation;
	private String telephone;
	private String email;
	private String mailaddress;
	private String zipcode;
	private String qq;
	private Timestamp last_login_time;
	private String last_login_ip;
	//推荐人身份证号
	private String recommender_idcardno;
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer accountId) {
		account_id = accountId;
	}
	public Integer getRecommender_id() {
		return recommender_id;
	}
	public void setRecommender_id(Integer recommenderId) {
		recommender_id = recommenderId;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String loginName) {
		login_name = loginName;
	}
	
	public String getLogin_passwd() {
		return login_passwd;
	}
	public void setLogin_passwd(String loginPasswd) {
		login_passwd = loginPasswd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp createDate) {
		create_date = createDate;
	}
	public Timestamp getPause_date() {
		return pause_date;
	}
	public void setPause_date(Timestamp pauseDate) {
		pause_date = pauseDate;
	}
	public Timestamp getClose_date() {
		return close_date;
	}
	public void setClose_date(Timestamp closeDate) {
		close_date = closeDate;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String realName) {
		real_name = realName;
	}
	public String getIdcard_no() {
		return idcard_no;
	}
	public void setIdcard_no(String idcardNo) {
		idcard_no = idcardNo;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Timestamp getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Timestamp lastLoginTime) {
		last_login_time = lastLoginTime;
	}
	public String getLast_login_ip() {
		return last_login_ip;
	}
	public void setLast_login_ip(String lastLoginIp) {
		last_login_ip = lastLoginIp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((account_id == null) ? 0 : account_id.hashCode());
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
		Account other = (Account) obj;
		if (account_id == null) {
			if (other.account_id != null)
				return false;
		} else if (!account_id.equals(other.account_id))
			return false;
		return true;
	}
	public void setRecommender_idcardno(String recommender_idcardno) {
		this.recommender_idcardno = recommender_idcardno;
	}
	public String getRecommender_idcardno() {
		return recommender_idcardno;
	}
	
}
