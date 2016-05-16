package com.kemenkes.epu.service;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Activity;
import com.kemenkes.epu.entity.view.ViewActivity;

public interface ActivityService {

	public Activity findByCode(String code);

	public void create(Activity activity);

	public void update(Activity activity);

	public PaginatedList search(Activity activity, PaginatedListImpl paginatedList);

	public List<Activity> search(Activity activity);

	public PaginatedList searchView(Activity activity, PaginatedListImpl paginatedList);

	public ViewActivity findViewByCode(String code);

}
