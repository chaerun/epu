package com.kemenkes.epu.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemenkes.epu.common.dao.GenericDao;
import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.common.util.Constraint;
import com.kemenkes.epu.common.util.OrderMap;
import com.kemenkes.epu.common.util.SearchResult;
import com.kemenkes.epu.entity.AccountDetail;
import com.kemenkes.epu.entity.Activity;
import com.kemenkes.epu.entity.Balance;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.entity.Stock;
import com.kemenkes.epu.service.StockService;

@Service("stockService")
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Stock findById(String id) {
		Stock stock = (Stock) sessionFactory.getCurrentSession().get(Stock.class, id);
		return stock;
	}

	@Override
	public void delete(Stock stock) {
		sessionFactory.getCurrentSession().delete(stock);

	}

	@Override
	public void create(Stock stock) {
		Session session = sessionFactory.getCurrentSession();
		String id = (String) session.save(stock);

		String desc = "Pengadaan " + stock.getActivity().getName() + "," + stock.getDescription();
		Balance balance = new Balance();
		balance.setAmount(stock.getAmount());
		balance.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
		balance.setReferenceId(stock.getActivity().getCode());
		balance.setCreatedBy(Constant.USER_SYSTEM);
		balance.setCreatedDate(stock.getCreatedDate());
		balance.setType(BalanceType.CR);
		balance.setDescription(desc);

		AccountDetail detail = new AccountDetail();
		detail.setAmount(stock.getAmount());
		detail.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
		detail.setReferenceId(stock.getActivity().getCode());
		detail.setAdditionalReferenceTable(Constant.REFF_TABLE_STOCK);
		detail.setAdditionalReferenceId(id);
		detail.setCreatedBy(Constant.USER_SYSTEM);
		detail.setCreatedDate(stock.getCreatedDate());
		detail.setType(BalanceType.CR);
		detail.setDescription(desc);
		detail.setAccount(stock.getAccount());

		session.save(balance);
		session.save(detail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> search(Stock stock) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		constraint.setParameters(parameters);
		if (stock != null) {

			Activity activity = stock.getActivity();
			if (stock.getActivity() != null) {
				if (StringUtils.isNotBlank(activity.getCode())) {
					constraint.appendAnd("activity.code = :activitycode");
					parameters.put("activitycode", activity.getCode());
				}

			}

		}

		SearchResult result = genericDao.searchHQL("stock", "Stock stock", constraint, orderMap);
		return (List<Stock>) result.getRecs();
	}

	@Override
	public PaginatedList search(Stock stock, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		constraint.setParameters(parameters);

		if (stock != null) {

			Activity activity = stock.getActivity();
			if (stock.getActivity() != null) {
				if (StringUtils.isNotBlank(activity.getCode())) {
					constraint.appendAnd("activity.code = :activitycode");
					parameters.put("activitycode", activity.getCode());
				}

			}

		}

		PaginatedList page = genericDao.searchHQL("stock", "Stock stock", constraint, orderMap, paginatedList);
		return page;
	}

}
