package com.kemenkes.epu.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemenkes.epu.common.dao.GenericDao;
import com.kemenkes.epu.common.util.Constraint;
import com.kemenkes.epu.common.util.OrderMap;
import com.kemenkes.epu.entity.Account;
import com.kemenkes.epu.entity.view.ViewAccountBalance;
import com.kemenkes.epu.service.AccountService;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private GenericDao genericDao;

	@Override
	public void create(Account account) {
		sessionFactory.getCurrentSession().save(account);
	}

	@Override
	public void edit(Account account) {
		sessionFactory.getCurrentSession().update(account);
	}

	@Override
	public PaginatedList search(Account account, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		orderMap.put("accountNumber", OrderMap.ASCENDING);
		PaginatedList page = genericDao.searchHQL("account", "Account account", constraint, orderMap, paginatedList);
		return page;
	}
	
	@Override
	public PaginatedList searchView(Account account, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		orderMap.put("accountNumber", OrderMap.ASCENDING);
		PaginatedList page = genericDao.searchHQL("view", "ViewAccountBalance view", constraint, orderMap, paginatedList);
		return page;
	}

	@Override
	public Account findById(String id) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Account> findAll() {
		List<Account> searchCriteria = (List<Account>) genericDao.searchCriteria(Account.class, null, null);
		return searchCriteria;
	}

	@Override
	public BigDecimal getTotalBalance(String accountNumber) {
		ViewAccountBalance view = (ViewAccountBalance) sessionFactory.getCurrentSession().get(ViewAccountBalance.class, accountNumber);
		return view.getAmount() == null ? BigDecimal.ZERO : view.getAmount();
	}

	@Override
	public ViewAccountBalance getViewAccountBalance(String accountNumber) {
		ViewAccountBalance view = (ViewAccountBalance) sessionFactory.getCurrentSession().get(ViewAccountBalance.class, accountNumber);
		return view;
	}

}
