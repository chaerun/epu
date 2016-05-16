package com.kemenkes.epu.entity;

public enum UserRole {
	ROLE_ADMIN("ROLE_ADMIN", "Admin"), ROLE_SA("ROLE_SA", "Super Admin"), ROLE_USER("ROLE_USER", "User");

	private String id;
	private String description;

	private UserRole(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
