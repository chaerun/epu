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
import com.kemenkes.epu.entity.Subdivision;
import com.kemenkes.epu.service.SubdivisionService;

@Service("subdivisionService")
@Transactional
public class SubdivisionServiceImpl implements SubdivisionService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@SuppressWarnings("unchecked")
	public List<Subdivision> findAll() {
		List<Subdivision> searchCriteria = (List<Subdivision>) genericDao.searchCriteria(Subdivision.class, null, null);
		return searchCriteria;
	}

	@Override
	public Subdivision findByCode(String code) {
		return (Subdivision) sessionFactory.getCurrentSession().get(Subdivision.class, code);
	}

	@Override
	public void delete(Subdivision subdivision) {

		sessionFactory.getCurrentSession().delete(subdivision);
	}

	@Override
	public void create(Subdivision subdivision) {

		sessionFactory.getCurrentSession().save(subdivision);
	}

	@Override
	public void edit(Subdivision subdivision) {

		sessionFactory.getCurrentSession().update(subdivision);
	}

	@Override
	public PaginatedList search(Subdivision grade, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		PaginatedList page = genericDao.searchHQL("subdivision", "Subdivision subdivision", constraint, orderMap, paginatedList);
		return page;
	}

}
