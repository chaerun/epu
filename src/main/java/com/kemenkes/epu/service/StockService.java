package com.kemenkes.epu.service;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Stock;

public interface StockService {
	
	public Stock findById(String id);

	public void delete(Stock stock);

	public void create(Stock stock);

	public List<Stock> search(Stock stock);
	
	public PaginatedList search(Stock stock,PaginatedListImpl paginatedList);

}
