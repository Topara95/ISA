package project.dto;

import project.domain.Offer;

public class OfferDTO {

	private Long id;

	private Long createdBy;
	
	private Long culturalVenueId;
	
	private Integer offeredMoney;

	private Long propId;

	private Boolean sent;

	private Boolean approved;
	
	public OfferDTO() {
		
	}
	
	public OfferDTO(Offer offer) {
		this.id = offer.getId();
		this.offeredMoney = offer.getOfferedMoney();
		this.approved = offer.getApproved();
		this.createdBy = offer.getCreatedBy();
		this.culturalVenueId = offer.getCulturalVenueId();
		this.propId = offer.getPropId();
		this.sent = offer.getSent();
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

	public Long getCulturalVenueId() {
		return culturalVenueId;
	}

	public void setCulturalVenueId(Long culturalVenueId) {
		this.culturalVenueId = culturalVenueId;
	}

	public Integer getOfferedMoney() {
		return offeredMoney;
	}

	public void setOfferedMoney(Integer offeredMoney) {
		this.offeredMoney = offeredMoney;
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
	
	
}
