package com.chenqf.dao;

import java.util.List;

import com.chenqf.annotation.MyBatisRepository;
import com.chenqf.entity.Account;
import com.chenqf.entity.page.AccountPage;

@MyBatisRepository
public interface AccountMapper {
	List<Account> findAll();
	Account findById(Integer account_id);
	void addAccount(Account account);
	void updateAccount(Account account);
	void updateByStatus(Account account);
	void deleteAccount(Integer account_id);
	List<Account> findByPage(AccountPage page);
	Integer findRows(AccountPage page);
	
	Account findByIdcardNo(String idcardNo);
}
