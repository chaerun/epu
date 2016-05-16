package com.kemenkes.epu.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemenkes.epu.common.dao.GenericDao;
import com.kemenkes.epu.common.util.Constraint;
import com.kemenkes.epu.common.util.OrderMap;
import com.kemenkes.epu.common.util.SearchResult;
import com.kemenkes.epu.entity.Activity;
import com.kemenkes.epu.entity.view.ViewActivity;
import com.kemenkes.epu.service.ActivityService;

@Service("activityService")
@Transactional
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private GenericDao genericDao;

	public Activity findByCode(String code) {
		Activity activity = (Activity) sessionFactory.getCurrentSession().get(Activity.class, code);
		Hibernate.initialize(activity.getAccounts());
		return activity;
	}

	public void create(Activity activity) {
		sessionFactory.getCurrentSession().save(activity);
	}

	public PaginatedList search(Activity activity, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		if (activity != null) {
			if (activity.getType() != null) {
				constraint.appendAnd("type = :type");
				parameters.put("type", activity.getType());
			}
			if (activity.getYear() != null) {
				constraint.appendAnd("year = :year");
				parameters.put("year", activity.getYear());
			}
			if (activity.getSubdivision() != null) {
				constraint.appendAnd("subdivision.code = :subcode");
				parameters.put("subcode", activity.getSubdivision().getCode());
			}
		}
		constraint.setParameters(parameters);

		PaginatedList page = genericDao.searchHQL("activity", "Activity activity", constraint, orderMap, paginatedList);
		return page;
	}

	@Override
	public void update(Activity activity) {
		sessionFactory.getCurrentSession().update(activity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> search(Activity activity) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();
		SearchResult result = this.genericDao.searchHQL("activity", "Activity activity", constraint, orderMap);
		return (List<Activity>) result.getRecs();
	}

	@Override
	public PaginatedList searchView(Activity activity, PaginatedListImpl paginatedList) {
		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		if (activity != null) {
			if (activity.getType() != null) {
				constraint.appendAnd("type = :type");
				parameters.put("type", activity.getType());
			}
			if (activity.getYear() != null) {
				constraint.appendAnd("year = :year");
				parameters.put("year", activity.getYear());
			}
		}
		constraint.setParameters(parameters);
		PaginatedList page = genericDao.searchHQL("viewActivity", "ViewActivity viewActivity", constraint, orderMap, paginatedList);
		return page;
	}

	@Override
	public ViewActivity findViewByCode(String code) {
		ViewActivity viewActivity = (ViewActivity) sessionFactory.getCurrentSession().get(ViewActivity.class, code);
		return viewActivity;
	}
}
