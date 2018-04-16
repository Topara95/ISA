package project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Offer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Long createdBy;
	
	@Column(nullable = false)
	private Long culturalVenueId;
	
	@Column(nullable = false)
	private Integer offeredMoney; 
	
	@Column(nullable = false)
	private Long propId;
	
	@Column(nullable = true)
	private Boolean sent;
	
	@Column(nullable = true)
	private Boolean approved;
	
	public Offer() {}
	
	public Offer(Long cratedBy, Integer offeredMoney, Long propId, Long culturalVenueId, Boolean approved, Boolean sent) {
		this.createdBy = cratedBy;
		this.offeredMoney = offeredMoney;
		this.propId = propId;
		this.culturalVenueId = culturalVenueId;
		this.sent = sent;
		this.approved = approved;
	}

	
	
	public Long getCulturalVenueId() {
		return culturalVenueId;
	}

	public void setCulturalVenueId(Long culturalVenueId) {
		this.culturalVenueId = culturalVenueId;
	}

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public Boolean getSent() {
		return sent;
	}

	public void setSent(Boolean sent) {
		this.sent = sent;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getOfferedMoney() {
		return offeredMoney;
	}

	public void setOfferedMoney(Integer offeredMoney) {
		this.offeredMoney = offeredMoney;
	}

	
}
