package com.kemenkes.epu.service;

import java.math.BigDecimal;
import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Journey;

public interface JourneyService {
	public Journey findById(String id);

	public void create(Journey journey);

	public void update(Journey journey);

	public PaginatedList search(Journey journey, PaginatedListImpl paginatedList);

	public List<Journey> search(Journey journey);

	public void approve(Journey journey);
	
	public void approveComplete(Journey journey,BigDecimal returnAmount);
}
