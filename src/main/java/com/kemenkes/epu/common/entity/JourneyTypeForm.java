package com.kemenkes.epu.common.entity;

import com.kemenkes.epu.entity.Activity;
import com.kemenkes.epu.entity.JourneyType;

public class JourneyTypeForm {

	private Activity activity;
	private JourneyType type;

	public JourneyTypeForm() {
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public JourneyType getType() {
		return type;
	}

	public void setType(JourneyType type) {
		this.type = type;
	}

}
