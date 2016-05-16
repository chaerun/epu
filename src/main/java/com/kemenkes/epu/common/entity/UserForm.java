package com.kemenkes.epu.common.entity;

import com.kemenkes.epu.entity.User;

public class UserForm {
	private User user;
	private String confirmPassword;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
