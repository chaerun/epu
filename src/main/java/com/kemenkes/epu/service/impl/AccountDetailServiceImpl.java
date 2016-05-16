package com.kemenkes.epu.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemenkes.epu.common.dao.GenericDao;
import com.kemenkes.epu.common.entity.DateFilter;
import com.kemenkes.epu.common.util.Constraint;
import com.kemenkes.epu.common.util.OrderMap;
import com.kemenkes.epu.entity.AccountDetail;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.service.AccountDetailService;

@Service("accountDetailService")
@Transactional
public class AccountDetailServiceImpl implements AccountDetailService {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private GenericDao genericDao;
	
	@Override
	public void create(AccountDetail accountDetail) {
		sessionFactory.getCurrentSession().save(accountDetail);
	}

	public PaginatedList search(AccountDetail accountDetail, DateFilter dateFilter, PaginatedListImpl paginatedList) {

		Constraint constraint = new Constraint();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		if (dateFilter != null) {
			if (dateFilter.getFromDate() != null && dateFilter.getToDate() != null) {
				constraint.setWhereClause("(createdDate between :fromDate and :toDate)");

				parameters.put("fromDate", dateFilter.getFromDate());
				parameters.put("toDate", dateFilter.getToDate());

			}
		}

		if (accountDetail != null) {
			if (accountDetail.getAccount() != null && StringUtils.isNotBlank(accountDetail.getAccount().getAccountNumber())) {
				if (StringUtils.isNotBlank(constraint.getWhereClause())) {
					constraint.setWhereClause(constraint.getWhereClause() + " and account.accountNumber=:accountNumber");
				} else {
					constraint.setWhereClause("account.accountNumber=:accountNumber");
				}

				parameters.put("accountNumber", accountDetail.getAccount().getAccountNumber());
			}
		}

		constraint.setParameters(parameters);

		OrderMap orderMap = new OrderMap();
		orderMap.put("createdDate", OrderMap.DESCENDING);
		PaginatedList page = genericDao.searchHQL("ad", "AccountDetail ad", constraint, orderMap, paginatedList);
		return page;
	}

	@Override
	public BigDecimal sum(BalanceType type, AccountDetail accountDetail, DateFilter dateFilter) {
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder builder = new StringBuilder("select sum(amount) from AccountDetail where type =:type");
		parameters.put("type", type);

		if (dateFilter != null) {
			if (dateFilter.getFromDate() != null && dateFilter.getToDate() != null) {
				parameters.put("fromDate", dateFilter.getFromDate());
				parameters.put("toDate", dateFilter.getToDate());
				builder.append(" and (createdDate between :fromDate and :toDate)");
			}
		}

		if (accountDetail != null) {
			if (accountDetail.getAccount() != null && StringUtils.isNotBlank(accountDetail.getAccount().getAccountNumber())) {
				builder.append(" and account.accountNumber=:accountNumber");
				parameters.put("accountNumber", accountDetail.getAccount().getAccountNumber());
			}

		}

		BigDecimal object = (BigDecimal) genericDao.runHQLUniqueResult(builder.toString(), parameters);
		return object;
	}

}
