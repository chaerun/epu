package com.kemenkes.epu.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil {
	
	private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");
	
	public static String convertBigDecimalToString(BigDecimal bigDecimal) {
		if(bigDecimal == null) return "";
		
		return decimalFormat.format(bigDecimal);
	}

}
