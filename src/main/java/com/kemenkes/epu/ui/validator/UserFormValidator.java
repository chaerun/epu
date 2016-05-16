package com.kemenkes.epu.ui.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.common.entity.UserForm;
import com.kemenkes.epu.entity.User;

public class UserFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm userForm = (UserForm) target;

		User user = userForm.getUser();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.username", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.password", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.subdivision.code", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.role", "field.required", "field.required");
		

		if (user != null) {

			if (!StringUtils.equals(userForm.getConfirmPassword(), user.getPassword())) {
				errors.rejectValue("confirmPassword", "user.password.confirm.not.match");
			}

		}
	}

}
