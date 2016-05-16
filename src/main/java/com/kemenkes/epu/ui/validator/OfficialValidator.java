package com.kemenkes.epu.ui.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.entity.Official;

public class OfficialValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Official.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Official official = (Official) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "official.code", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "treasurer.code", "field.required", "field.required");

		if (official.getYear() != null) {
			if (official.getYear() < Constant.YEAR_MIN) {
				errors.rejectValue("year", "year.min");
			}

			if (official.getYear() > Constant.YEAR_MAX) {
				errors.rejectValue("year", "year.max");
			}
		}

	}

}
