package com.kemenkes.epu.ui.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.entity.Activity;
import com.kemenkes.epu.entity.ActivityType;

public class ActivityValidator implements Validator {
	public boolean supports(Class<?> clazz) {
		return Activity.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Activity activity = (Activity) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source", "field.required", "field.required");
		if (ActivityType.JOURNEY.equals(activity.getType())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subdivision.code", "field.required", "field.required");
		} else {
			activity.setSubdivision(null);
		}
		
		if (activity.getYear() != null) {
			if (activity.getYear() < Constant.YEAR_MIN) {
				errors.rejectValue("year", "year.min");
			}

			if (activity.getYear() > Constant.YEAR_MAX) {
				errors.rejectValue("year", "year.max");
			}
		}

	}
}
