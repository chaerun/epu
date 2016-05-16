package com.kemenkes.epu.entity;

import java.io.Serializable;
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
@Table(name = "participant_journey")
public class ParticipantJourney implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", unique = true, length = 36)
	private String id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "flag", length = 100)
	private boolean flag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "journey_id")
	private Journey journey;

	@ManyToOne
	@JoinColumn(name = "participant_code")
	private Participant participant;

	@Column(name = "daily_amount", precision = 15, scale = 2)
	private BigDecimal dailyAmount = BigDecimal.ZERO;

	@Column(name = "transport_amount", precision = 15, scale = 2)
	private BigDecimal transportAmount = BigDecimal.ZERO;

	// Transport
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

	// untuk moderator bro
	@Column(name = "moderator")
	private Boolean moderator = false;

	@Column(name = "moderator_amount", precision = 15, scale = 2)
	private BigDecimal moderatorAmount = BigDecimal.ZERO;

	@Column(name = "moderator_hours", length = 1)
	private Integer moderatorHours = 0;

	@Column(name = "moderator_ppn", length = 2)
	private Integer moderatorPpn = 0;

	@Transient
	private Integer days;

	@Transient
	private BigDecimal totalAmount = BigDecimal.ZERO;

	@Transient
	private BigDecimal moderatorTotalAmount = BigDecimal.ZERO;

	@Transient
	private BigDecimal moderatorReceivedAmount = BigDecimal.ZERO;

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

	public BigDecimal getModeratorTotalAmount() {
		this.moderatorTotalAmount = BigDecimal.ZERO.add(moderatorAmount.multiply(new BigDecimal(getDays())).multiply(new BigDecimal(moderatorHours)));
		return moderatorTotalAmount;
	}

	public void setModeratorTotalAmount(BigDecimal moderatorTotalAmount) {
		this.moderatorTotalAmount = moderatorTotalAmount;
	}

	public BigDecimal getModeratorReceivedAmount() {
		BigDecimal totalPpn = getModeratorTotalAmount().multiply(new BigDecimal(moderatorPpn)).divide(new BigDecimal(100));
		this.moderatorReceivedAmount = getModeratorTotalAmount().subtract(totalPpn);
		return moderatorReceivedAmount;
	}

	public void setModeratorReceivedAmount(BigDecimal moderatorReceivedAmount) {
		this.moderatorReceivedAmount = moderatorReceivedAmount;
	}

	public ParticipantJourney() {

	}

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

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public BigDecimal getDailyAmount() {
		return dailyAmount;
	}

	public void setDailyAmount(BigDecimal dailyAmount) {
		this.dailyAmount = dailyAmount;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public BigDecimal getTotalAmount() {
		this.totalAmount = BigDecimal.ZERO.add(transportAmount).add(planeGoAmount).add(planeGoTax).add(planeBackAmount).add(planeBackTax).add(taxiGoAmount).add(taxiBackAmount).add(dailyAmount.multiply(new BigDecimal(getDays())));
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		ParticipantJourney other = (ParticipantJourney) obj;

		if (!flag) {
			if (participant == null) {
				if (other.participant != null)
					return false;
			} else if (!participant.equals(other.participant))
				return false;
			return true;
		}
		return false;

	}

	public BigDecimal getModeratorAmount() {
		return moderatorAmount;
	}

	public void setModeratorAmount(BigDecimal moderatorAmount) {
		this.moderatorAmount = moderatorAmount;
	}

	public Boolean getModerator() {
		return moderator;
	}

	public void setModerator(Boolean moderator) {
		this.moderator = moderator;
	}

	public Integer getModeratorHours() {
		return moderatorHours;
	}

	public void setModeratorHours(Integer moderatorHours) {
		this.moderatorHours = moderatorHours;
	}

	public Integer getModeratorPpn() {
		return moderatorPpn;
	}

	public void setModeratorPpn(Integer moderatorPpn) {
		this.moderatorPpn = moderatorPpn;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getDays() {
		if (startDate != null && endDate != null) {
			return Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays() + 1;
		}
		return 0;
	}

}
