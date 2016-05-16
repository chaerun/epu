package com.kemenkes.epu.entity.view;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Subselect("SELECT sum(if(balance_type = 'CR',amount,-amount )) as amount,additional_reference_id,additional_reference_table  FROM balance group by additional_reference_id")
@Synchronize("balance")
public class ViewBalanceAddRefId {
	@Id
	@Column(name = "additional_reference_id", length = 32)
	private String additionalReferenceId;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "additional_reference_table", length = 50)
	private String additionalReferenceTable;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAdditionalReferenceId() {
		return additionalReferenceId;
	}

	public void setAdditionalReferenceId(String additionalReferenceId) {
		this.additionalReferenceId = additionalReferenceId;
	}

	public String getAdditionalReferenceTable() {
		return additionalReferenceTable;
	}

	public void setAdditionalReferenceTable(String additionalReferenceTable) {
		this.additionalReferenceTable = additionalReferenceTable;
	}

}
