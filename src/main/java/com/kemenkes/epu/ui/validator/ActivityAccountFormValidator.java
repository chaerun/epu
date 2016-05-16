package com.kemenkes.epu.ui.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.common.entity.ActivityAccountForm;

public class ActivityAccountFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ActivityAccountForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activity.code", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.accountNumber", "field.required", "field.required");

	}

}
