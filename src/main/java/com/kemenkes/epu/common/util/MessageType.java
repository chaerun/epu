package com.kemenkes.epu.common.util;

public enum MessageType {
	WARNING("warning"), ERROR("danger"), SUCCESS("success"), INFO("info");

	private String description;

	private MessageType(String description) {
		this.description = description;
	}

	public String toString() {
		return description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
