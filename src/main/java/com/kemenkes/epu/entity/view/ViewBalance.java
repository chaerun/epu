package com.kemenkes.epu.entity.view;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Subselect("SELECT sum(if(balance_type = 'DB',amount,-amount )) as amount FROM balance")
@Synchronize("balance")
public class ViewBalance {

	@Id
	@Column(name = "amount")
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
