package com.kemenkes.epu.service;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Receipt;

public interface ReceiptService {

	public Receipt findById(String id);

	public void delete(Receipt receipt);

	public void create(Receipt receipt);

	public List<Receipt> search(Receipt receipt);

	public PaginatedList search(Receipt receipt,PaginatedListImpl paginatedList);
}
