package com.kemenkes.epu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.joda.time.Days;

@Entity
@Table(name = "journey")
public class Journey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", unique = true, length = 36)
	private String id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "location", length = 100)
	private String location;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "journey_type", length = 50)
	private JourneyType type;

	@Enumerated(EnumType.STRING)
	@Column(name = "journey_status", length = 50)
	private JourneyStatus status;

	@Column(name = "packet_meeting_amount", precision = 15, scale = 2)
	private BigDecimal packetMeetingAmount;

	@ManyToOne
	@JoinColumn(name = "activity_code")
	private Activity activity;

	@Column(name = "inn_amount", precision = 15, scale = 2)
	private BigDecimal innAmount;

	@ManyToOne
	@JoinColumn(name = "inn_account")
	private Account innAccount;

	@ManyToOne
	@JoinColumn(name = "participant_account")
	private Account participantAccount;

	@ManyToOne
	@JoinColumn(name = "moderator_account")
	private Account moderatorAccount;

	@ManyToOne
	@JoinColumn(name = "informant_account")
	private Account informantAccount;
	
	@ManyToOne
	@JoinColumn(name = "informant_transport_account")
	private Account informantTransportAccount;
	

	@ManyToOne
	@JoinColumn(name = "requirement_account")
	private Account requirementAccount;

	@ManyToOne
	@JoinColumn(name = "return_amount_account")
	private Account returnAmountAccount;

	@ManyToOne
	@JoinColumn(name = "packet_meeting_account")
	private Account packetMeetingAccount;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "journey")
	private Set<ParticipantJourney> participants = new HashSet<ParticipantJourney>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "journey")
	private Set<Informant> informants = new HashSet<Informant>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "journey")
	private Set<Requirement> requirements = new HashSet<Requirement>();

	@Transient
	private Set<ParticipantJourney> moderators = new HashSet<ParticipantJourney>();

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public JourneyType getType() {
		return type;
	}

	public void setType(JourneyType type) {
		this.type = type;
	}

	public BigDecimal getPacketMeetingAmount() {
		return packetMeetingAmount;
	}

	public void setPacketMeetingAmount(BigDecimal packetMeetingAmount) {
		this.packetMeetingAmount = packetMeetingAmount;
	}

	public Account getPacketMeetingAccount() {
		return packetMeetingAccount;
	}

	public void setPacketMeetingAccount(Account packetMeetingAccount) {
		this.packetMeetingAccount = packetMeetingAccount;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Account getParticipantAccount() {
		return participantAccount;
	}

	public void setParticipantAccount(Account participantAccount) {
		this.participantAccount = participantAccount;
	}

	public Account getInformantAccount() {
		return informantAccount;
	}

	public void setInformantAccount(Account informantAccount) {
		this.informantAccount = informantAccount;
	}

	public Account getModeratorAccount() {
		return moderatorAccount;
	}

	public void setModeratorAccount(Account moderatorAccount) {
		this.moderatorAccount = moderatorAccount;
	}

	public Account getRequirementAccount() {
		return requirementAccount;
	}

	public void setRequirementAccount(Account requirementAccount) {
		this.requirementAccount = requirementAccount;
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

	public JourneyStatus getStatus() {
		return status;
	}

	public void setStatus(JourneyStatus status) {
		this.status = status;
	}

	public Set<ParticipantJourney> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<ParticipantJourney> participants) {
		this.participants = participants;
	}

	public Set<ParticipantJourney> getModerators() {
		moderators = new HashSet<ParticipantJourney>();
		for (ParticipantJourney moderator : this.participants) {
			if (moderator.getModerator() != null) {
				if (moderator.getModerator()) {
					moderators.add(moderator);
				}
			}
		}
		return moderators;
	}

	public void setModerators(Set<ParticipantJourney> moderators) {
		this.moderators = moderators;
	}

	public Set<Informant> getInformants() {
		return informants;
	}

	public void setInformants(Set<Informant> informants) {
		this.informants = informants;
	}

	public Set<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}

	public BigDecimal getInnAmount() {
		return innAmount;
	}

	public void setInnAmount(BigDecimal innAmount) {
		this.innAmount = innAmount;
	}

	public Account getInnAccount() {
		return innAccount;
	}

	public void setInnAccount(Account innAccount) {
		this.innAccount = innAccount;
	}

	@Transient
	private Integer days;

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getDays() {
		if (startDate != null && endDate != null) {
			return Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays() + 1;
		}
		return 0;
	}

	@Transient
	private BigDecimal totalAmountParticipant;

	@Transient
	private BigDecimal totalAmountModerator;

	@Transient
	private BigDecimal totalAmountInformant;
	
	@Transient
	private BigDecimal totalAmountTransportInformant;
	
	@Transient
	private BigDecimal totalAmountHonorInformant;

	@Transient
	private BigDecimal totalAmountRequirement;

	@Transient
	private BigDecimal totalAmountInn;
	
	@Transient
	private BigDecimal totalAmountAll;

	public BigDecimal getTotalAmountParticipant() {

		this.participants = getParticipants();
		totalAmountParticipant = BigDecimal.ZERO;

		for (ParticipantJourney participant : this.participants) {
			totalAmountParticipant = totalAmountParticipant.add(participant.getTotalAmount());
		}

		return totalAmountParticipant;
	}

	public void setTotalAmountParticipant(BigDecimal totalAmountParticipant) {
		this.totalAmountParticipant = totalAmountParticipant;
	}

	public BigDecimal getTotalAmountModerator() {

		this.moderators = getModerators();
		totalAmountModerator = BigDecimal.ZERO;

		for (ParticipantJourney participant : this.participants) {
			totalAmountModerator = totalAmountModerator.add(participant.getModeratorTotalAmount());
		}
		return totalAmountModerator;
	}

	public void setTotalAmountModerator(BigDecimal totalAmountModerator) {
		this.totalAmountModerator = totalAmountModerator;
	}

	public BigDecimal getTotalAmountInformant() {

		this.informants = getInformants();
		totalAmountInformant = BigDecimal.ZERO;

		for (Informant informant : this.informants) {
			totalAmountInformant = totalAmountInformant.add(informant.getTotalAmount());
		}
		return totalAmountInformant;
	}
	

	public void setTotalAmountInformant(BigDecimal totalAmountInformant) {
		this.totalAmountInformant = totalAmountInformant;
	}

	public BigDecimal getTotalAmountTransportInformant() {
		this.informants = getInformants();
		totalAmountTransportInformant = BigDecimal.ZERO;

		for (Informant informant : this.informants) {
			totalAmountTransportInformant = totalAmountTransportInformant.add(informant.getTotalTransportAmount());
		}
		
		return totalAmountTransportInformant;
	}

	public void setTotalAmountTransportInformant(BigDecimal totalAmountTransportInformant) {
		this.totalAmountTransportInformant = totalAmountTransportInformant;
	}

	public BigDecimal getTotalAmountHonorInformant() {
		this.informants = getInformants();
		totalAmountHonorInformant = BigDecimal.ZERO;

		for (Informant informant : this.informants) {
			totalAmountHonorInformant = totalAmountHonorInformant.add(informant.getTotalHonorAmount());
		}
		return totalAmountHonorInformant;
	}

	public void setTotalAmountHonorInformant(BigDecimal totalAmountHonorInformant) {
		this.totalAmountHonorInformant = totalAmountHonorInformant;
	}
	
	
	public Account getInformantTransportAccount() {
		return informantTransportAccount;
	}

	public void setInformantTransportAccount(Account informantTransportAccount) {
		this.informantTransportAccount = informantTransportAccount;
	}

	public BigDecimal getTotalAmountRequirement() {
		this.requirements = getRequirements();
		totalAmountRequirement = BigDecimal.ZERO;

		for (Requirement requirement : this.requirements) {
			totalAmountRequirement = totalAmountRequirement.add(requirement.getAmount());
		}
		return totalAmountRequirement;
	}

	public void setTotalAmountRequirement(BigDecimal totalAmountRequirement) {
		this.totalAmountRequirement = totalAmountRequirement;
	}

	public BigDecimal getTotalAmountInn() {

		totalAmountInn = BigDecimal.ZERO;
		
		if(innAmount == null){
			innAmount= BigDecimal.ZERO;
		}
		
		totalAmountInn = totalAmountInn.add(innAmount.multiply(new BigDecimal(getDays())).multiply(new BigDecimal(getInformants().size() + getParticipants().size())));

		return totalAmountInn;
	}

	public void setTotalAmountInn(BigDecimal totalAmountInn) {
		this.totalAmountInn = totalAmountInn;
	}

	public Account getReturnAmountAccount() {
		return returnAmountAccount;
	}

	public void setReturnAmountAccount(Account returnAmountAccount) {
		this.returnAmountAccount = returnAmountAccount;
	}

	public BigDecimal getTotalAmountAll() {
		
		if (packetMeetingAmount == null){
			
			packetMeetingAmount = BigDecimal.ZERO;
		}
		
		return getTotalAmountParticipant().add(getTotalAmountModerator()).add(getTotalAmountInformant()).add(getTotalAmountRequirement()).add(getTotalAmountInn()).add(packetMeetingAmount);
	}

	public void setTotalAmountAll(BigDecimal totalAmountAll) {
		this.totalAmountAll = totalAmountAll;
	}

	
}
