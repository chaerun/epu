package com.kemenkes.epu.service.impl;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemenkes.epu.common.dao.GenericDao;
import com.kemenkes.epu.common.util.Constraint;
import com.kemenkes.epu.common.util.OrderMap;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.User;
import com.kemenkes.epu.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	public User findByUsername(String username) {
		return (User) sessionFactory.getCurrentSession().get(User.class, username);
	}

	@Override
	public PaginatedList search(User user, PaginatedListImpl paginatedList) {

		Constraint constraint = new Constraint();
		OrderMap orderMap = new OrderMap();

		if (SpringSecurityUtil.haveAuthority("ROLE_SA")) {
			constraint.setWhereClause("role in ('ROLE_SA','ROLE_ADMIN') and enabled = true");
		} else if (SpringSecurityUtil.haveAuthority("ROLE_ADMIN")) {
			constraint.setWhereClause("role in ('ROLE_ADMIN','ROLE_USER') and enabled = true");
		}

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		constraint.setParameters(parameters);
		if (user.getSubdivision() != null) {

			constraint.appendAnd("subdivision.code=:subdivisioncode");
			parameters.put("subdivisioncode", user.getSubdivision().getCode());

		}

		PaginatedList page = genericDao.searchHQL("user", "User user", constraint, orderMap, paginatedList);
		return page;
	}

	@Override
	public void create(User user) {
		user.setEnabled(true);
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void edit(User user) {
		user.setEnabled(true);
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void delete(User user) {
		user.setEnabled(false);
		sessionFactory.getCurrentSession().update(user);
	}

}
