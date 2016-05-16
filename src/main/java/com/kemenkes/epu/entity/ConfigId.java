package com.kemenkes.epu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ConfigId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "category", length = 50)
	private ConfigCategory category;

	@Column(name = "key", length = 50)
	private String key;

	public ConfigId() {
	}

	public ConfigCategory getCategory() {
		return category;
	}

	public void setCategory(ConfigCategory category) {
		this.category = category;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
