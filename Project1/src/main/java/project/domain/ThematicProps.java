package project.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ThematicProps implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = true)
	private Long createdBy;
	
	@Column(nullable = false)
	private Long culturalVenueId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String reserved;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String date;
	
	@Column(nullable = true)
	private String picture;
	
	@Column(nullable = false)
	private ThematicPropsType tptype;
	
	@Column(nullable = false)
	private Boolean approved;
	
	@OneToMany
	private List<Offer> offers;

	public ThematicProps() 
	{}
	
	public ThematicProps(Long culturalVenueId, ThematicPropsType tptype,Long createdBy,String reserved, String name,String description,String date,String picture,Boolean approved) {
		this.tptype = tptype;
		this.name = name;
		this.description = description;
		this.date = date;
		this.picture = picture;	
		this.createdBy = createdBy;
		this.reserved = reserved;
		this.culturalVenueId = culturalVenueId;
		this.approved = approved;
	}

	
	
	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Long getCulturalVenueId() {
		return culturalVenueId;
	}

	public void setCulturalVenueId(Long culturalVenueId) {
		this.culturalVenueId = culturalVenueId;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public ThematicPropsType getTptype() {
		return tptype;
	}

	public void setTptype(ThematicPropsType tptype) {
		this.tptype = tptype;
	}
	
	
}
