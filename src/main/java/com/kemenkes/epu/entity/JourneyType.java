package com.kemenkes.epu.entity;

public enum JourneyType {
	FULLDAY("FULLDAY", "FullDay"), FULLBOARD("FULLBOARD", "FullBoard"), JOURNEY_OUT("JOURNEY_OUT", "Perjalanan Dinas Luar Kota"), JOURNEY_IN("JOURNEY_IN", "Perjalanan Dinas Dalam Kota");

	private String id;
	private String description;

	private JourneyType(String id, String description) {
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
