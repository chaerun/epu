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
import com.kemenkes.epu.entity.Receipt;
import com.kemenkes.epu.service.ReceiptService;

@Service("receiptService")
@Transactional
public class ReceiptServiceImpl implements ReceiptService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Receipt findById(String id) {
		Receipt receipt = (Receipt) sessionFactory.getCurrentSession().get(Receipt.class, id);
		return receipt;
	}

	@Override
	public void delete(Receipt receipt) {
		sessionFactory.getCurrentSession().delete(receipt);

	}

	@Override
	public void create(Receipt receipt) {

		Session session = sessionFactory.getCurrentSession();
		String id = (String) session.save(receipt);

		String desc = "Kwitansi Lansung " + receipt.getActivity().getName() + "," + receipt.getDescription();
		Balance balance = new Balance();
		balance.setAmount(receipt.getAmount());
		balance.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
		balance.setReferenceId(receipt.getActivity().getCode());
		balance.setCreatedBy(Constant.USER_SYSTEM);
		balance.setCreatedDate(receipt.getCreatedDate());
		balance.setType(BalanceType.CR);
		balance.setDescription(desc);

		AccountDetail detail = new AccountDetail();
		detail.setAmount(receipt.getAmount());
		detail.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
		detail.setReferenceId(receipt.getActivity().getCode());
		detail.setAdditionalReferenceTable(Constant.REFF_TABLE_RECEIPT);
		detail.setAdditionalReferenceId(id);
		detail.setCreatedBy(Constant.USER_SYSTEM);
		detail.setCreatedDate(receipt.getCreatedDate());
		detail.setType(BalanceType.CR);
		detail.setDescription(desc);
		detail.setAccount(receipt.getAccount());

		session.save(balance);
		session.save(detail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Receipt> search(Receipt receipt) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		constraint.setParameters(parameters);
		if (receipt != null) {

			Activity activity = receipt.getActivity();
			if (receipt.getActivity() != null) {
				if (StringUtils.isNotBlank(activity.getCode())) {
					constraint.appendAnd("activity.code = :activitycode");
					parameters.put("activitycode", activity.getCode());
				}

			}

		}

		SearchResult result = genericDao.searchHQL("receipt", "Receipt receipt", constraint, orderMap);
		return (List<Receipt>) result.getRecs();
	}

	@Override
	public PaginatedList search(Receipt receipt, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		constraint.setParameters(parameters);

		if (receipt != null) {

			Activity activity = receipt.getActivity();
			if (receipt.getActivity() != null) {
				if (StringUtils.isNotBlank(activity.getCode())) {
					constraint.appendAnd("activity.code = :activitycode");
					parameters.put("activitycode", activity.getCode());
				}

			}

		}

		PaginatedList page = genericDao.searchHQL("receipt", "Receipt receipt", constraint, orderMap, paginatedList);
		return page;
	}

}
