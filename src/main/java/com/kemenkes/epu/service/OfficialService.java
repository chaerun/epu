package com.kemenkes.epu.service;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Official;

public interface OfficialService {

	public Official findById(Integer year);

	public List<Official> findAll();

	public void delete(Official official);

	public void create(Official official);

	public PaginatedList search(Official official,PaginatedListImpl paginatedList);

}
