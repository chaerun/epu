package com.kemenkes.epu.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemenkes.epu.common.dao.GenericDao;
import com.kemenkes.epu.entity.Config;
import com.kemenkes.epu.entity.ConfigId;
import com.kemenkes.epu.service.ConfigService;

@Service("configService")
@Transactional
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GenericDao genericDao;

	@SuppressWarnings("unchecked")
	public List<Config> findAll() {
		return (List<Config>) genericDao.searchCriteria(Config.class, null, null);
	}

	@Override
	public Config findById(ConfigId id) {
		return (Config) sessionFactory.getCurrentSession().get(Config.class, (Serializable) id);
	}

	@Override
	public void create(Config config) {
		sessionFactory.getCurrentSession().saveOrUpdate(config);
	}

}
