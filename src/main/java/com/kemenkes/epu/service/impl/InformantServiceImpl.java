package com.kemenkes.epu.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemenkes.epu.common.dao.GenericDao;
import com.kemenkes.epu.common.util.Constraint;
import com.kemenkes.epu.common.util.OrderMap;
import com.kemenkes.epu.common.util.SearchResult;
import com.kemenkes.epu.entity.Informant;
import com.kemenkes.epu.entity.JourneyStatus;
import com.kemenkes.epu.service.InformantService;

@Service("informantService")
@Transactional
public class InformantServiceImpl implements InformantService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Informant findById(String id) {
		Informant informant = (Informant) sessionFactory.getCurrentSession().get(Informant.class, id);
		return informant;
	}

	@Override
	public void delete(Informant informant) {
		if (JourneyStatus.CREATE_NEW.equals(informant.getJourney().getStatus()) || JourneyStatus.IN_PROGRESS.equals(informant.getJourney().getStatus())) {
			sessionFactory.getCurrentSession().delete(informant);
		}

	}

	@Override
	public void create(Informant informant) {
		if (JourneyStatus.CREATE_NEW.equals(informant.getJourney().getStatus()) || JourneyStatus.IN_PROGRESS.equals(informant.getJourney().getStatus())) {
			sessionFactory.getCurrentSession().save(informant);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Informant> search(Informant informant) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		SearchResult result = genericDao.searchHQL("informant", "Informant informant", constraint, orderMap);
		return (List<Informant>) result.getRecs();
	}

}
