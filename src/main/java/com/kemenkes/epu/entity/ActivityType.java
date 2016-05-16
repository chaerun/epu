package com.kemenkes.epu.entity;

public enum ActivityType {
	JOURNEY("JOURNEY", "Perjalanan Dinas"), STOCK("STOCK", "Pengadaan"), RECEIPT("RECEIPT", "Kwitansi Langsung");

	private String id;
	private String description;

	private ActivityType(String id, String description) {
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
