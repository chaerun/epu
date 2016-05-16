package com.kemenkes.epu.service.impl;

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
import com.kemenkes.epu.entity.Official;
import com.kemenkes.epu.service.OfficialService;

@Service("officialService")
@Transactional
public class OfficialServiceImpl implements OfficialService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Official findById(Integer year) {
		return (Official) sessionFactory.getCurrentSession().get(Official.class, year);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Official> findAll() {
		List<Official> searchCriteria = (List<Official>) genericDao.searchCriteria(Official.class, null, null);
		return searchCriteria;
	}

	@Override
	public void delete(Official official) {
		sessionFactory.getCurrentSession().delete(official);

	}

	@Override
	public void create(Official official) {
		sessionFactory.getCurrentSession().save(official);
	}

	@Override
	public PaginatedList search(Official official, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		PaginatedList page = genericDao.searchHQL("official", "Official official", constraint, orderMap, paginatedList);
		return page;
	}

}
