package com.kemenkes.epu.common.entity;

import java.util.Date;

import org.joda.time.DateTime;

public class DateFilter {

	private Date fromDate;
	private Date toDate;

	public DateFilter() {
	}

	public Date getFromDate() {

		if (fromDate == null) {
			return null;
		}

		DateTime dateTime = new DateTime(fromDate);
		return dateTime.withTime(0, 0, 0, 0).toDate();
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {

		if (toDate == null) {
			return null;
		}
		DateTime dateTime = new DateTime(toDate);
		return 	dateTime.withTime(23, 59, 59, 999).toDate();
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "DateFilter [fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

}
