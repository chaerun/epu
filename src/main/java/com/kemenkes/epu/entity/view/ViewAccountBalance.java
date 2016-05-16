package com.kemenkes.epu.entity.view;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Subselect("SELECT sum(if(balance_type = 'DB',amount,-amount )) as amount,a.account_number,a.name,a.description FROM account a left join account_detail ad on ad.account_number = a.account_number  group by a.account_number")
@Synchronize({ "account", "account_detail" })
public class ViewAccountBalance {
	@Id
	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount==null?BigDecimal.ZERO:amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
