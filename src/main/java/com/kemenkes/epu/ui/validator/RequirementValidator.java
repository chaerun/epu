package com.kemenkes.epu.ui.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.entity.Requirement;

public class RequirementValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Requirement.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "field.required");
	}

}
