package com.kemenkes.epu.service;

import java.util.List;

import com.kemenkes.epu.entity.Requirement;

public interface RequirementService {
	public Requirement findById(String id);

	public void delete(Requirement requirement);

	public void create(Requirement requirement);

	public List<Requirement> search(Requirement requirement);

}
