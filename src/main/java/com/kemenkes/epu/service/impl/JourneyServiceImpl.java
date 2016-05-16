package com.kemenkes.epu.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.hibernate.Hibernate;
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
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.service.JourneyService;

@Service("journeyService")
@Transactional
public class JourneyServiceImpl implements JourneyService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@Override
	public Journey findById(String id) {
		Journey journey = (Journey) sessionFactory.getCurrentSession().get(Journey.class, id);
		Hibernate.initialize(journey.getActivity().getAccounts());
		Hibernate.initialize(journey.getParticipants());
		Hibernate.initialize(journey.getInformants());
		Hibernate.initialize(journey.getRequirements());
		return journey;
	}

	@Override
	public void create(Journey journey) {
		sessionFactory.getCurrentSession().save(journey);
	}

	@Override
	public void update(Journey journey) {
		sessionFactory.getCurrentSession().update(journey);
	}

	@Override
	public void approve(Journey journey) {

		Session session = sessionFactory.getCurrentSession();
		session.update(journey);

		if (BigDecimal.ZERO.compareTo(journey.getTotalAmountAll()) == -1) {
			String desc = "Perjalanan " + journey.getActivity().getName() + "," + journey.getName();
			Balance balance = new Balance();
			balance.setAmount(journey.getTotalAmountAll());
			balance.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			balance.setReferenceId(journey.getActivity().getCode());
			balance.setAdditionalReferenceId(journey.getId());
			balance.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			balance.setCreatedBy(Constant.USER_SYSTEM);
			balance.setCreatedDate(journey.getUpdatedDate());
			balance.setType(BalanceType.CR);
			balance.setDescription(desc);
			session.save(balance);
		}
		if (BigDecimal.ZERO.compareTo(journey.getTotalAmountParticipant()) == -1) {
			AccountDetail participant = new AccountDetail();
			participant.setAmount(journey.getTotalAmountParticipant());
			participant.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			participant.setReferenceId(journey.getActivity().getCode());
			participant.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			participant.setAdditionalReferenceId(journey.getId());
			participant.setCreatedBy(Constant.USER_SYSTEM);
			participant.setCreatedDate(journey.getUpdatedDate());
			participant.setType(BalanceType.CR);
			participant.setDescription("Pembayaran Peserta, Perjalanan " + journey.getActivity().getName() + "," + journey.getName());
			participant.setAccount(journey.getParticipantAccount());
			session.save(participant);
		}
		if (BigDecimal.ZERO.compareTo(journey.getTotalAmountModerator()) == -1) {
			AccountDetail moderator = new AccountDetail();
			moderator.setAmount(journey.getTotalAmountModerator());
			moderator.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			moderator.setReferenceId(journey.getActivity().getCode());
			moderator.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			moderator.setAdditionalReferenceId(journey.getId());
			moderator.setCreatedBy(Constant.USER_SYSTEM);
			moderator.setCreatedDate(journey.getUpdatedDate());
			moderator.setType(BalanceType.CR);
			moderator.setDescription("Pembayaran Moderator, Perjalanan " + journey.getActivity().getName() + "," + journey.getName());
			moderator.setAccount(journey.getModeratorAccount());
			session.save(moderator);
		}
		if (BigDecimal.ZERO.compareTo(journey.getTotalAmountHonorInformant()) == -1) {
			AccountDetail informant = new AccountDetail();
			informant.setAmount(journey.getTotalAmountHonorInformant());
			informant.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			informant.setReferenceId(journey.getActivity().getCode());
			informant.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			informant.setAdditionalReferenceId(journey.getId());
			informant.setCreatedBy(Constant.USER_SYSTEM);
			informant.setCreatedDate(journey.getUpdatedDate());
			informant.setType(BalanceType.CR);
			informant.setDescription("Pembayaran Narasumber, Perjalanan " + journey.getActivity().getName() + "," + journey.getName());
			informant.setAccount(journey.getInformantAccount());
			session.save(informant);
		}
		
		if (BigDecimal.ZERO.compareTo(journey.getTotalAmountTransportInformant()) == -1) {
			AccountDetail informant = new AccountDetail();
			informant.setAmount(journey.getTotalAmountTransportInformant());
			informant.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			informant.setReferenceId(journey.getActivity().getCode());
			informant.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			informant.setAdditionalReferenceId(journey.getId());
			informant.setCreatedBy(Constant.USER_SYSTEM);
			informant.setCreatedDate(journey.getUpdatedDate());
			informant.setType(BalanceType.CR);
			informant.setDescription("Pembayaran Transport Narasumber, Perjalanan " + journey.getActivity().getName() + "," + journey.getName());
			informant.setAccount(journey.getInformantTransportAccount());
			session.save(informant);
		}
		
		if (BigDecimal.ZERO.compareTo(journey.getTotalAmountRequirement()) == -1) {
			AccountDetail requirement = new AccountDetail();
			requirement.setAmount(journey.getTotalAmountRequirement());
			requirement.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			requirement.setReferenceId(journey.getActivity().getCode());
			requirement.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			requirement.setAdditionalReferenceId(journey.getId());
			requirement.setCreatedBy(Constant.USER_SYSTEM);
			requirement.setCreatedDate(journey.getUpdatedDate());
			requirement.setType(BalanceType.CR);
			requirement.setDescription("Pembayaran Kebutuhan Lain, Perjalanan " + journey.getActivity().getName() + "," + journey.getName());
			requirement.setAccount(journey.getRequirementAccount());
			session.save(requirement);
		}
		if (BigDecimal.ZERO.compareTo(journey.getTotalAmountInn()) == -1) {
			AccountDetail inn = new AccountDetail();
			inn.setAmount(journey.getTotalAmountInn());
			inn.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			inn.setReferenceId(journey.getActivity().getCode());
			inn.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			inn.setAdditionalReferenceId(journey.getId());
			inn.setCreatedBy(Constant.USER_SYSTEM);
			inn.setCreatedDate(journey.getUpdatedDate());
			inn.setType(BalanceType.CR);
			inn.setDescription("Pembayaran Penginapan, Perjalanan " + journey.getActivity().getName() + "," + journey.getName());
			inn.setAccount(journey.getInnAccount());
			session.save(inn);
		}
		
		if (BigDecimal.ZERO.compareTo(journey.getPacketMeetingAmount()) == -1) {
			AccountDetail inn = new AccountDetail();
			inn.setAmount(journey.getPacketMeetingAmount());
			inn.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			inn.setReferenceId(journey.getActivity().getCode());
			inn.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			inn.setAdditionalReferenceId(journey.getId());
			inn.setCreatedBy(Constant.USER_SYSTEM);
			inn.setCreatedDate(journey.getUpdatedDate());
			inn.setType(BalanceType.CR);
			inn.setDescription("Paket Meeting, Perjalanan " + journey.getActivity().getName() + "," + journey.getName());
			inn.setAccount(journey.getPacketMeetingAccount());
			session.save(inn);
		}

	}

	@Override
	public PaginatedList search(Journey journey, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		constraint.setParameters(parameters);

		if (journey != null) {

			Activity activity = journey.getActivity();
			if (journey.getActivity() != null) {
				if (StringUtils.isNotBlank(activity.getCode())) {
					constraint.appendAnd("activity.code = :activitycode");
					parameters.put("activitycode", activity.getCode());
				}
			}

		}

		if (journey.getStatus() != null) {
			constraint.appendAnd("status = :status");
			parameters.put("status", journey.getStatus());
		}
		orderMap.put("createdDate", OrderMap.DESCENDING);
		PaginatedList page = genericDao.searchHQL("journey", "Journey journey", constraint, orderMap, paginatedList);
		return page;
	}

	@Override
	public List<Journey> search(Journey journey) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		constraint.setParameters(parameters);

		if (journey != null) {

			Activity activity = journey.getActivity();
			if (journey.getActivity() != null) {
				if (StringUtils.isNotBlank(activity.getCode())) {
					constraint.appendAnd("activity.code = :activitycode");
					parameters.put("activitycode", activity.getCode());
				}
			}

		}

		if (journey.getStatus() != null) {
			constraint.appendAnd("status = :status");
			parameters.put("status", journey.getStatus());
		}
		System.out.println(constraint.getWhereClause() + "------------------------------------------------------");
		orderMap.put("createdDate", OrderMap.DESCENDING);
		SearchResult result = genericDao.searchHQL("journey", "Journey journey", constraint, orderMap);
		return (List<Journey>) result.getRecs();
	}

	@Override
	public void approveComplete(Journey journey, BigDecimal returnAmount) {

		Session session = sessionFactory.getCurrentSession();
		session.update(journey);

		if (BigDecimal.ZERO.compareTo(returnAmount) == -1) {
			String desc = "Pengembalian -> Perjalanan " + journey.getActivity().getName() + "," + journey.getName();
			Balance balance = new Balance();
			balance.setAmount(returnAmount);
			balance.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			balance.setReferenceId(journey.getActivity().getCode());
			balance.setAdditionalReferenceId(journey.getId());
			balance.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			balance.setCreatedBy(Constant.USER_SYSTEM);
			balance.setCreatedDate(journey.getUpdatedDate());
			balance.setType(BalanceType.DB);
			balance.setDescription(desc);
			session.save(balance);
		}

		if (BigDecimal.ZERO.compareTo(returnAmount) == -1) {
			AccountDetail inn = new AccountDetail();
			inn.setAmount(returnAmount);
			inn.setReferenceTable(Constant.REFF_TABLE_ACTIVITY);
			inn.setReferenceId(journey.getActivity().getCode());
			inn.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
			inn.setAdditionalReferenceId(journey.getId());
			inn.setCreatedBy(Constant.USER_SYSTEM);
			inn.setCreatedDate(journey.getUpdatedDate());
			inn.setType(BalanceType.DB);
			inn.setDescription("Pengembalian -> Perjalanan " + journey.getActivity().getName() + "," + journey.getName());
			inn.setAccount(journey.getReturnAmountAccount());
			session.save(inn);
		}

	}

}
