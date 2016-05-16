package com.kemenkes.epu.entity;

public enum JourneyStatus {
	CREATE_NEW("CREATE_NEW", "CREATE_NEW"), PENDING("PENDING", "PENDING"), IN_PROGRESS("IN_PROGRESS", "IN_PROGRESS"), PENDING_COMPLETE("PENDING_COMPLETE", "PENDING_COMPLETE"), COMPLETED("COMPLETED", "COMPLETED");
	private String id;
	private String description;

	private JourneyStatus(String id, String description) {
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
