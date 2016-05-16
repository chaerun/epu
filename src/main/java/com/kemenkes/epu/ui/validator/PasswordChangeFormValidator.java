package com.kemenkes.epu.ui.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.common.entity.PasswordChangeForm;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.User;
import com.kemenkes.epu.service.UserService;

@Component
public class PasswordChangeFormValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordChangeForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PasswordChangeForm form = (PasswordChangeForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "field.required", "field.required");

		User user = userService.findByUsername(SpringSecurityUtil.getUsername());

		String oldPassword = SpringSecurityUtil.encodePassword(form.getOldPassword());

		if (!StringUtils.equals(oldPassword, user.getPassword())) {
			errors.rejectValue("oldPassword", "user.password.new.wrong");
		}

		if (!StringUtils.equals(form.getConfirmPassword(), form.getNewPassword())) {
			errors.rejectValue("confirmPassword", "user.password.confirm.not.match");
		}

		String newPassword = SpringSecurityUtil.encodePassword(form.getNewPassword());
		user.setPassword(newPassword);
		form.setUser(user);
	}

}
