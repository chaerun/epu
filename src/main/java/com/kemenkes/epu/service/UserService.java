package com.kemenkes.epu.service;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.User;

public interface UserService {
	

	public User findByUsername(String username);

	public void create(User user);
	
	public void edit(User user);

	public void delete(User user);
	
	public PaginatedList search(User user,PaginatedListImpl paginatedList);
	

}
