package com.kemenkes.epu.entity;

public enum ConfigCategory {
	OFFICIAL("OFFICIAL", "Pejabat Pembuat Komitmen"), TREASURER("TREASURER", "Bendahara");
	private String id;
	private String description;

	private ConfigCategory(String id, String description) {
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
