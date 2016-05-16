package com.kemenkes.epu.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemenkes.epu.common.dao.GenericDao;
import com.kemenkes.epu.common.util.Constraint;
import com.kemenkes.epu.common.util.OrderMap;
import com.kemenkes.epu.common.util.SearchResult;
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.JourneyStatus;
import com.kemenkes.epu.entity.ParticipantJourney;
import com.kemenkes.epu.service.ParticipantJourneyService;

@Service("participantJourneyService")
@Transactional
public class ParticipantJourneyServiceImpl implements ParticipantJourneyService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@Override
	public ParticipantJourney findById(String id) {
		ParticipantJourney participantJourney = (ParticipantJourney) sessionFactory.getCurrentSession().get(ParticipantJourney.class, id);
		return participantJourney;
	}

	@Override
	public void delete(ParticipantJourney participantJourney) {
		if (JourneyStatus.CREATE_NEW.equals(participantJourney.getJourney().getStatus()) || JourneyStatus.IN_PROGRESS.equals(participantJourney.getJourney().getStatus())) {
			sessionFactory.getCurrentSession().delete(participantJourney);
		}

	}

	@Override
	public void create(ParticipantJourney participantJourney) {
		if (JourneyStatus.CREATE_NEW.equals(participantJourney.getJourney().getStatus()) || JourneyStatus.IN_PROGRESS.equals(participantJourney.getJourney().getStatus())) {
			System.out.println("tai kucing lah"+ 	participantJourney.getId());
			sessionFactory.getCurrentSession().save(participantJourney);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParticipantJourney> search(ParticipantJourney participantJourney) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		constraint.setParameters(parameters);
		if (participantJourney != null) {
			if (participantJourney.getJourney() != null) {
				Journey journey = participantJourney.getJourney();
				constraint.appendAnd("journey.id = :journeyid");
				parameters.put("journeyid", journey.getId());

			}
		}

		SearchResult result = genericDao.searchHQL("participantJourney", "ParticipantJourney participantJourney", constraint, orderMap);
		return (List<ParticipantJourney>) result.getRecs();
	}

	@Override
	public ParticipantJourney used(ParticipantJourney participantJourney) {

		ParticipantJourney result = null;

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("startDate", participantJourney.getStartDate());
		parameters.put("endDate", participantJourney.getEndDate());
		parameters.put("participantCode", participantJourney.getParticipant().getCode());
		Object object = genericDao.runHQLUniqueResult("from ParticipantJourney where ((startDate between :startDate and :endDate) or (endDate between :startDate and :endDate)) and participant.code=:participantCode", parameters);
		if (object != null) {
			result = (ParticipantJourney) object;
		}

		return result;
	}

	@Override
	public void update(ParticipantJourney participantJourney) {
		if (JourneyStatus.CREATE_NEW.equals(participantJourney.getJourney().getStatus()) || JourneyStatus.IN_PROGRESS.equals(participantJourney.getJourney().getStatus())) {
			sessionFactory.getCurrentSession().update(participantJourney);
		}
	}
}
