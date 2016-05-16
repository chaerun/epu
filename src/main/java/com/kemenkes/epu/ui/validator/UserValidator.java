package com.kemenkes.epu.ui.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.entity.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subdivision.code", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "field.required", "field.required");
	}

}
