package com.kemenkes.epu.ui.validator;

import org.joda.time.DateTime;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kemenkes.epu.common.entity.DateFilter;

public class DateFilterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(DateFilter.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DateFilter dateFilter = (DateFilter) target;

		if (dateFilter.getFromDate() == null && dateFilter.getToDate() != null) {
			errors.rejectValue("fromDate", "field.required");
		}

		if (dateFilter.getFromDate() != null && dateFilter.getToDate() == null) {
			errors.rejectValue("toDate", "field.required");
		}

		DateTime fromDateTime = new DateTime(dateFilter.getFromDate());
		DateTime toDateTime = new DateTime(dateFilter.getToDate());

		DateTime afterDateTime = new DateTime().plusDays(1);

		if (toDateTime.isBefore(fromDateTime)) {
			errors.rejectValue("toDate", "dateFilter.toDate.limit");
			errors.rejectValue("fromDate", "dateFilter.fromDate.limit");
		}

		if (toDateTime.isAfter(afterDateTime)) {
			errors.rejectValue("toDate", "dateFilter.toDate.after");
		}

		if (fromDateTime.isAfter(afterDateTime)) {
			errors.rejectValue("fromDate", "dateFilter.fromDate.after");

		}

	}

}
