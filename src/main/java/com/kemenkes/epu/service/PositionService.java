package com.kemenkes.epu.service;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Position;

public interface PositionService {

	public Position findById(String id);

	public List<Position> findAll();

	public void delete(Position position);

	public void create(Position position);
	
	public void edit(Position position);

	public PaginatedList search(Position position,PaginatedListImpl paginatedList);

}
