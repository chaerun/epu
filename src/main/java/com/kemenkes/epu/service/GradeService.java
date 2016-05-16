package com.kemenkes.epu.service;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Grade;

public interface GradeService {

	public Grade findById(String id);

	public List<Grade> findAll();

	public void delete(Grade grade);

	public void create(Grade grade);
	
	public void edit(Grade grade);

	public PaginatedList search(Grade grade,PaginatedListImpl paginatedList);
}
