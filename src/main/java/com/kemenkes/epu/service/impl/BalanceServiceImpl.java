package com.kemenkes.epu.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

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
import com.kemenkes.epu.common.util.SearchResult;
import com.kemenkes.epu.entity.Balance;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.entity.view.ViewBalance;
import com.kemenkes.epu.entity.view.ViewBalanceAddRefId;
import com.kemenkes.epu.service.BalanceService;

@Service("balanceService")
@Transactional
public class BalanceServiceImpl implements BalanceService {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private GenericDao genericDao;

	@Override
	public void create(Balance balance) {
		sessionFactory.getCurrentSession().save(balance);
	}

	@Override
	public PaginatedList search(Balance balance, DateFilter dateFilter, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();

		if (dateFilter != null) {
			if (dateFilter.getFromDate() != null && dateFilter.getToDate() != null) {
				constraint.setWhereClause("createdDate between :fromDate and :toDate");
				HashMap<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("fromDate", dateFilter.getFromDate());
				parameters.put("toDate", dateFilter.getToDate());
				constraint.setParameters(parameters);

			}
		}

		OrderMap orderMap = new OrderMap();
		orderMap.put("createdDate", OrderMap.DESCENDING);
		PaginatedList page = genericDao.searchHQL("balance", "Balance balance", constraint, orderMap, paginatedList);
		return page;
	}

	@Override
	public BigDecimal sum(BalanceType type, DateFilter dateFilter) {
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder builder = new StringBuilder("select sum(amount) from Balance where type =:type");
		parameters.put("type", type);

		if (dateFilter != null) {
			if (dateFilter.getFromDate() != null && dateFilter.getToDate() != null) {
				parameters.put("fromDate", dateFilter.getFromDate());
				parameters.put("toDate", dateFilter.getToDate());
				builder.append(" and createdDate between :fromDate and :toDate");
			}
		}

		BigDecimal object = (BigDecimal) genericDao.runHQLUniqueResult(builder.toString(), parameters);
		return object;
	}

	@Override
	public BigDecimal getTotalBalance() {

		ViewBalance view = (ViewBalance) sessionFactory.getCurrentSession().createCriteria(ViewBalance.class).uniqueResult();
		if (view != null) {
			return view.getAmount();
		}
		return BigDecimal.ZERO;
	}

	@Override
	public List<Balance> search(Balance balance) {

		Constraint constraint = new Constraint();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		if (balance != null) {
			if (balance.getAdditionalReferenceId() != null && balance.getAdditionalReferenceTable() != null) {
				constraint.appendAnd("additionalReferenceId = :additionalReferenceId and additionalReferenceTable = :additionalReferenceTable");
				parameters.put("additionalReferenceId", balance.getAdditionalReferenceId());
				parameters.put("additionalReferenceTable", balance.getAdditionalReferenceTable());
			}
			if (balance.getType() != null) {
				constraint.appendAnd(" type = :type");
				parameters.put("type", balance.getType());
			}
		}
		constraint.setParameters(parameters);
		OrderMap orderMap = new OrderMap();
		SearchResult result = genericDao.searchHQL("balance", "Balance balance", constraint, orderMap);
		return (List<Balance>) result.getRecs();
	}

	@Override
	public BigDecimal getTotalBalance(String addRefId, String addRefTable) {
		ViewBalanceAddRefId view = (ViewBalanceAddRefId) sessionFactory.getCurrentSession().get(ViewBalanceAddRefId.class, addRefId);
		if (view != null && StringUtils.equals(addRefTable, view.getAdditionalReferenceTable())) {
			return view.getAmount();
		}
		return null;
	}
}
