package com.kemenkes.epu.entity;

public enum BalanceType {
	CR("Credit"), DB("Debit");
	private String description;

	private BalanceType(String description) {
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
