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
import com.kemenkes.epu.entity.Position;
import com.kemenkes.epu.service.PositionService;

@Service("positionService")
@Transactional
public class PositionServiceImpl implements PositionService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Position findById(String id) {
		return (Position) sessionFactory.getCurrentSession().get(Position.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Position> findAll() {
		List<Position> searchCriteria = (List<Position>) genericDao.searchCriteria(Position.class, null, null);
		return searchCriteria;
	}

	@Override
	public void delete(Position position) {
		sessionFactory.getCurrentSession().delete(position);

	}

	@Override
	public void create(Position position) {
		sessionFactory.getCurrentSession().save(position);
	}

	@Override
	public void edit(Position position) {
		sessionFactory.getCurrentSession().update(position);
	}

	@Override
	public PaginatedList search(Position position, PaginatedListImpl paginatedList) {

		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		PaginatedList page = genericDao.searchHQL("position", "Position position", constraint, orderMap, paginatedList);
		return page;
	}

}
