package com.kemenkes.epu.entity.view;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import com.kemenkes.epu.entity.ActivitySource;
import com.kemenkes.epu.entity.ActivityType;
import com.kemenkes.epu.entity.Subdivision;

@Entity
@Subselect("SELECT a.*,sum(if(b.balance_type = 'DB',-b.amount,b.amount )) as used_amount from activity a left join balance b on a.code=b.reference_id and b.reference_table='ACTIVITY' group by a.code")
@Synchronize({ "account", "account_detail" })
public class ViewActivity {
	@Id
	@Column(name = "code", length = 25)
	private String code;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "amount", precision = 15, scale = 2)
	private BigDecimal amount;

	@Column(name = "year", length = 4)
	private Integer year;

	@ManyToOne
	@JoinColumn(name = "subdivision_code")
	private Subdivision subdivision;

	@Enumerated(EnumType.STRING)
	@Column(name = "activity_source", length = 10)
	private ActivitySource source;

	@Enumerated(EnumType.STRING)
	@Column(name = "activity_type", length = 10)
	private ActivityType type;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "created_by", length = 50)
	private String createdBy;

	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "updated_by", length = 50)
	private String updatedBy;

	@Column(name = "used_amount", precision = 15, scale = 2)
	private BigDecimal usedAmount;

	@Transient
	private BigDecimal balance;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Subdivision getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(Subdivision subdivision) {
		this.subdivision = subdivision;
	}

	public ActivitySource getSource() {
		return source;
	}

	public void setSource(ActivitySource source) {
		this.source = source;
	}

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public BigDecimal getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(BigDecimal usedAmount) {
		this.usedAmount = usedAmount;
	}

	public BigDecimal getBalance() {

		if (getUsedAmount() != null && getAmount() != null) {

			return getAmount().subtract(getUsedAmount());
		}

		return getAmount();
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
