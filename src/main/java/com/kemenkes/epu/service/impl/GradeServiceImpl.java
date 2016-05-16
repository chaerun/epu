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
import com.kemenkes.epu.entity.Grade;
import com.kemenkes.epu.service.GradeService;

@Service("gradeService")
@Transactional
public class GradeServiceImpl implements GradeService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;


	@Override
	public Grade findById(String id) {
		return (Grade) sessionFactory.getCurrentSession().get(Grade.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Grade> findAll() {
		List<Grade> searchCriteria = (List<Grade>) genericDao.searchCriteria(Grade.class, null, null);
		return searchCriteria;
	}

	@Override
	public void delete(Grade grade) {
		sessionFactory.getCurrentSession().delete(grade);

	}

	@Override
	public void create(Grade grade) {
		sessionFactory.getCurrentSession().save(grade);
	}

	@Override
	public void edit(Grade grade) {
		sessionFactory.getCurrentSession().update(grade);
	}

	@Override
	public PaginatedList search(Grade grade, PaginatedListImpl paginatedList) {

		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		PaginatedList page = genericDao.searchHQL("grade", "Grade grade", constraint, orderMap, paginatedList);
		return page;
	}


}
