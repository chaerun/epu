package com.kemenkes.epu.service;

import java.math.BigDecimal;
import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Account;
import com.kemenkes.epu.entity.view.ViewAccountBalance;

public interface AccountService {

	public void create(Account account);

	public void edit(Account account);

	public PaginatedList search(Account account, PaginatedListImpl paginatedList);
	
	public PaginatedList searchView(Account account, PaginatedListImpl paginatedList);

	public Account findById(String id);

	public List<Account> findAll();

	public BigDecimal getTotalBalance(String accountNumber);

	public ViewAccountBalance getViewAccountBalance(String accountNumber);

}
