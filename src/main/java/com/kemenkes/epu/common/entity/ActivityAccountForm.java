package com.kemenkes.epu.common.entity;

import com.kemenkes.epu.entity.Account;
import com.kemenkes.epu.entity.Activity;

public class ActivityAccountForm {

	private Activity activity;
	private Account account;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
