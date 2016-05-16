package com.kemenkes.epu.common.datatables;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;

import com.kemenkes.epu.common.util.OrderMap;

public class DataTablesRequest {

	private int draw;
	private int start;
	private int length;
	private String searchValue;

	private String orderColumn;
	private String orderDir;

	private OrderMap orderMap;

	public DataTablesRequest(HttpServletRequest servletRequest) {

		start = NumberUtils.toInt((String) servletRequest.getParameter("start"));
		length = NumberUtils.toInt((String) servletRequest.getParameter("length"));
		searchValue = servletRequest.getParameter("search[value]");

		orderColumn = servletRequest.getParameter("order[0][column]");
		orderDir = servletRequest.getParameter("order[0][dir]");
		orderColumn = servletRequest.getParameter(String.format("columns[%s][data]", orderColumn));

		orderMap = new OrderMap();
		orderMap.put(orderColumn, orderDir);
		System.out.println(orderMap);

	}

	public String getSearchValue() {
		return searchValue;
	}

	public OrderMap getOrderMap() {

		return orderMap;
	}

	public int getDraw() {
		return draw;
	}

	public int getStart() {
		return start;
	}

	public int getLength() {
		return length;
	}

}
