package com.kemenkes.epu.service;

import java.math.BigDecimal;
import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.common.entity.DateFilter;
import com.kemenkes.epu.entity.Balance;
import com.kemenkes.epu.entity.BalanceType;

public interface BalanceService {

	public void create(Balance balance);

	public PaginatedList search(Balance balance, DateFilter dateFilter, PaginatedListImpl paginatedList);

	public List<Balance> search(Balance balance);

	public BigDecimal sum(BalanceType type, DateFilter dateFilter);

	public BigDecimal getTotalBalance();

	public BigDecimal getTotalBalance(String addRefId, String addRefTable);
}
