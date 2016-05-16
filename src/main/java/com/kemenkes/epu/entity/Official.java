package com.kemenkes.epu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "official")
public class Official {

	@Id
	@Column(name = "year", length = 4)
	private Integer year;
	@ManyToOne
	@JoinColumn(name = "official_code")
	private Participant official;
	@ManyToOne
	@JoinColumn(name = "treasurer_code")
	private Participant treasurer;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "created_by", length = 50)
	private String createdBy;

	public Official() {

	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Participant getOfficial() {
		return official;
	}

	public void setOfficial(Participant official) {
		this.official = official;
	}

	public Participant getTreasurer() {
		return treasurer;
	}

	public void setTreasurer(Participant treasurer) {
		this.treasurer = treasurer;
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
	
	

}
