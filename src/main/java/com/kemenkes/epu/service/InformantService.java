package com.kemenkes.epu.service;

import java.util.List;

import com.kemenkes.epu.entity.Informant;

public interface InformantService {

	public Informant findById(String id);

	public void delete(Informant informant);

	public void create(Informant informant);

	public List<Informant> search(Informant informant);
}
