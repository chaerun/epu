package com.kemenkes.epu.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "balance")
public class Balance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", unique = true, length = 36)
	private String id;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "amount", precision = 15, scale = 2)
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "balance_type", length = 2)
	private BalanceType type;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "created_by", length = 50)
	private String createdBy;

	@Column(name = "reference_id", length = 32)
	private String referenceId;

	@Column(name = "reference_table", length = 50)
	private String referenceTable;

	@Column(name = "additional_reference_id", length = 32)
	private String additionalReferenceId;

	@Column(name = "additional_reference_table", length = 50)
	private String additionalReferenceTable;

	public Balance() {
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BalanceType getType() {
		return type;
	}

	public void setType(BalanceType type) {
		this.type = type;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceTable() {
		return referenceTable;
	}

	public void setReferenceTable(String referenceTable) {
		this.referenceTable = referenceTable;
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
