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
import com.kemenkes.epu.entity.Participant;
import com.kemenkes.epu.service.ParticipantService;

@Service("participantService")
@Transactional
public class ParticipantServiceImpl implements ParticipantService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Participant findByCode(String id) {
		return (Participant) sessionFactory.getCurrentSession().get(Participant.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Participant> findAll() {
		List<Participant> searchCriteria = (List<Participant>) genericDao.searchCriteria(Participant.class, null, null);
		return searchCriteria;
	}

	@Override
	public void delete(Participant participant) {
		sessionFactory.getCurrentSession().delete(participant);

	}

	@Override
	public void create(Participant participant) {
		sessionFactory.getCurrentSession().save(participant);
	}

	@Override
	public void edit(Participant participant) {
		sessionFactory.getCurrentSession().update(participant);
	}

	@Override
	public PaginatedList search(Participant participant, PaginatedListImpl paginatedList) {

		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		PaginatedList page = genericDao.searchHQL("participant", "Participant participant", constraint, orderMap, paginatedList);
		return page;
	}
}
