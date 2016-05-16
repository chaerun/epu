package com.kemenkes.epu.ui.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.entity.Account;

public class AccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Account.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNumber", "field.required", "field.required");
	}

}
