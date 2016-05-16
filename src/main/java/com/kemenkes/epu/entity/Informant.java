package com.kemenkes.epu.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.joda.time.Days;

@Entity
@Table(name = "informant")
public class Informant {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", unique = true, length = 36)
	private String id;

	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne
	@JoinColumn(name = "journey_id")
	private Journey journey;

	@Column(name = "nip", length = 40)
	private String nip;

	@Column(name = "position", length = 100)
	private String position;

	@Column(name = "grade", length = 100)
	private String grade;

	@Column(name = "amount", precision = 15, scale = 2)
	private BigDecimal amount = BigDecimal.ZERO;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "hours", length = 2)
	private Integer hours = 0;

	@Column(name = "ppn", length = 2)
	private Integer ppn = 0;

	@Column(name = "transport_amount", precision = 15, scale = 2)
	private BigDecimal transportAmount = BigDecimal.ZERO;;

	@Column(name = "from_location", length = 100)
	private String fromLocation;

	@Column(name = "plane_go_amount", precision = 15, scale = 2)
	private BigDecimal planeGoAmount = BigDecimal.ZERO;

	@Column(name = "plane_go_tax", precision = 15, scale = 2)
	private BigDecimal planeGoTax = BigDecimal.ZERO;

	@Column(name = "plance_back_amount", precision = 15, scale = 2)
	private BigDecimal planeBackAmount = BigDecimal.ZERO;

	@Column(name = "plane_back_tax", precision = 15, scale = 2)
	private BigDecimal planeBackTax = BigDecimal.ZERO;

	@Column(name = "taxi_go_amount", precision = 15, scale = 2)
	private BigDecimal taxiGoAmount = BigDecimal.ZERO;

	@Column(name = "taxi_back_amount", precision = 15, scale = 2)
	private BigDecimal taxiBackAmount = BigDecimal.ZERO;

	@Transient
	private BigDecimal totalAmount = BigDecimal.ZERO;

	@Transient
	private BigDecimal receivedAmount = BigDecimal.ZERO;

	@Transient
	private BigDecimal totalTransportAmount = BigDecimal.ZERO;

	@Transient
	private BigDecimal totalHonorAmount = BigDecimal.ZERO;
	
	@Transient
	private BigDecimal totalPpnAmount = BigDecimal.ZERO;

	@Transient
	private Integer days;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "created_by", length = 50)
	private String createdBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getPpn() {
		return ppn;
	}

	public void setPpn(Integer ppn) {
		this.ppn = ppn;
	}

	public BigDecimal getTransportAmount() {
		return transportAmount;
	}

	public void setTransportAmount(BigDecimal transportAmount) {
		this.transportAmount = transportAmount;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public BigDecimal getPlaneGoAmount() {
		return planeGoAmount;
	}

	public void setPlaneGoAmount(BigDecimal planeGoAmount) {
		this.planeGoAmount = planeGoAmount;
	}

	public BigDecimal getPlaneGoTax() {
		return planeGoTax;
	}

	public void setPlaneGoTax(BigDecimal planeGoTax) {
		this.planeGoTax = planeGoTax;
	}

	public BigDecimal getPlaneBackAmount() {
		return planeBackAmount;
	}

	public void setPlaneBackAmount(BigDecimal planeBackAmount) {
		this.planeBackAmount = planeBackAmount;
	}

	public BigDecimal getPlaneBackTax() {
		return planeBackTax;
	}

	public void setPlaneBackTax(BigDecimal planeBackTax) {
		this.planeBackTax = planeBackTax;
	}

	public BigDecimal getTaxiGoAmount() {
		return taxiGoAmount;
	}

	public void setTaxiGoAmount(BigDecimal taxiGoAmount) {
		this.taxiGoAmount = taxiGoAmount;
	}

	public BigDecimal getTaxiBackAmount() {
		return taxiBackAmount;
	}

	public void setTaxiBackAmount(BigDecimal taxiBackAmount) {
		this.taxiBackAmount = taxiBackAmount;
	}

	public BigDecimal getTotalAmount() {
		totalAmount = amount.multiply(new BigDecimal(hours)).multiply(new BigDecimal(getDays()));
		totalAmount = totalAmount.add(transportAmount).add(planeGoAmount).add(planeGoTax).add(planeBackAmount).add(planeBackTax).add(taxiGoAmount).add(taxiBackAmount);
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public BigDecimal getTotalTransportAmount() {
		totalTransportAmount = BigDecimal.ZERO.add(transportAmount).add(planeGoAmount).add(planeGoTax).add(planeBackAmount).add(planeBackTax).add(taxiGoAmount).add(taxiBackAmount);
		return totalTransportAmount;
	}

	public void setTotalTransportAmount(BigDecimal totalTransportAmount) {
		this.totalTransportAmount = totalTransportAmount;
	}

	public BigDecimal getTotalHonorAmount() {
		totalHonorAmount = amount.multiply(new BigDecimal(hours)).multiply(new BigDecimal(getDays()));
		return totalHonorAmount;
	}

	public void setTotalHonorAmount(BigDecimal totalHonorAmount) {
		this.totalHonorAmount = totalHonorAmount;
	}

	public BigDecimal getReceivedAmount() {
		BigDecimal total = amount.multiply(new BigDecimal(hours)).multiply(new BigDecimal(getDays()));
		BigDecimal totalPpn = total.multiply(new BigDecimal(ppn)).divide(new BigDecimal(100));

		receivedAmount = total.subtract(totalPpn);
		receivedAmount = receivedAmount.add(transportAmount).add(planeGoAmount).add(planeGoTax).add(planeBackAmount).add(planeBackTax).add(taxiGoAmount).add(taxiBackAmount);
		return receivedAmount;
	}
	

	public BigDecimal getTotalPpnAmount() {
		BigDecimal total = amount.multiply(new BigDecimal(hours)).multiply(new BigDecimal(getDays()));
		totalPpnAmount = total.multiply(new BigDecimal(ppn)).divide(new BigDecimal(100));
		return totalPpnAmount;
	}

	public void setTotalPpnAmount(BigDecimal totalPpnAmount) {
		this.totalPpnAmount = totalPpnAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public Integer getDays() {
		if (startDate != null && endDate != null) {
			return Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays() + 1;
		}
		return 0;
	}

	public void setDays(Integer days) {
		this.days = days;
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
