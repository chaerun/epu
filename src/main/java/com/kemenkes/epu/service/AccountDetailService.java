package com.kemenkes.epu.service;

import java.math.BigDecimal;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.common.entity.DateFilter;
import com.kemenkes.epu.entity.AccountDetail;
import com.kemenkes.epu.entity.BalanceType;

public interface AccountDetailService {

	public PaginatedList search(AccountDetail accountDetail, DateFilter dateFilter, PaginatedListImpl paginatedList);

	public BigDecimal sum(BalanceType type, AccountDetail accountDetail, DateFilter dateFilter);
	
	public void create(AccountDetail accountDetail);

}
