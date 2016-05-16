package com.kemenkes.epu.entity;

public enum ActivitySource {
	RM("RM", "RM"), PNBP("PNBP", "PNBP");

	private String id;
	private String description;

	private ActivitySource(String id, String description) {
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
