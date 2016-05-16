package com.kemenkes.epu.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Constant {

	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final String ORDER_ASC = "asc";
	public static final String ORDER_DESC = "desc";

	public static final String SESSION_USER = "user";

	public static final String DATE_FORMAT = "dd/MM/yyyy";

	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	

	public static final SimpleDateFormat LONG_SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy");

	public static final String DATE_PICKER_FORMAT = "dd/mm/yyyy";

	public static final Integer YEAR_MIN = 2014;
	public static final Integer YEAR_MAX = 2020;

	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

	public static final String REFF_TABLE_ACTIVITY = "ACTIVITY";
	
	public static final String REFF_TABLE_JOURNEY = "JOURNEY";

	public static final String REFF_TABLE_STOCK = "STOCK";

	public static final String REFF_TABLE_RECEIPT = "RECEIPT";

	public static final String USER_SYSTEM = "SYSTEM";

	public static String getFormatCurrency(BigDecimal value) {
		return "Rp. " + DECIMAL_FORMAT.format(value);
	}

}
