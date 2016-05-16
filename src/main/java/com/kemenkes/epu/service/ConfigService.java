package com.kemenkes.epu.service;

import java.util.List;

import com.kemenkes.epu.entity.Config;
import com.kemenkes.epu.entity.ConfigId;

public interface ConfigService {

	public Config findById(ConfigId id);

	public List<Config> findAll();

	public void create(Config config);


}
