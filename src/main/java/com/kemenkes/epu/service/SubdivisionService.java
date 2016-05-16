package com.kemenkes.epu.service;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Subdivision;

public interface SubdivisionService {

	public Subdivision findByCode(String code);
	
	public List<Subdivision> findAll();

	public void delete(Subdivision subdivision);

	public void create(Subdivision subdivision);
	
	public void edit(Subdivision subdivision);

	public PaginatedList search(Subdivision grade,PaginatedListImpl paginatedList);
}
