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
import com.kemenkes.epu.entity.JourneyStatus;
import com.kemenkes.epu.entity.Requirement;
import com.kemenkes.epu.service.RequirementService;

@Service("requirementService")
@Transactional
public class RequirementServiceImpl implements RequirementService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Requirement findById(String id) {
		Requirement requirement = (Requirement) sessionFactory.getCurrentSession().get(Requirement.class, id);
		return requirement;
	}

	@Override
	public void delete(Requirement requirement) {
		if (JourneyStatus.CREATE_NEW.equals(requirement.getJourney().getStatus()) || JourneyStatus.IN_PROGRESS.equals(requirement.getJourney().getStatus())) {
			sessionFactory.getCurrentSession().delete(requirement);
		}

	}

	@Override
	public void create(Requirement requirement) {
		if (JourneyStatus.CREATE_NEW.equals(requirement.getJourney().getStatus()) || JourneyStatus.IN_PROGRESS.equals(requirement.getJourney().getStatus())) {
			sessionFactory.getCurrentSession().save(requirement);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Requirement> search(Requirement requirement) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		SearchResult result = genericDao.searchHQL("requirement", "Requirement requirement", constraint, orderMap);
		return (List<Requirement>) result.getRecs();
	}

}
