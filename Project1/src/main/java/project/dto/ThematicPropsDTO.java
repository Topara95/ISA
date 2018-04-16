package project.dto;

import project.domain.ThematicProps;
import project.domain.ThematicPropsType;

public class ThematicPropsDTO {
	
	private Long id;
	private ThematicPropsType tptype;
	private String name;
	private String description;
	private String date;
	private String picture;
	private Long createdBy;
	private String reserved;
	private Long culturalVenueId;
	
	public ThematicPropsDTO(ThematicProps thematicProps) {
		this.id = thematicProps.getId();
		this.tptype = thematicProps.getTptype();
		this.name = thematicProps.getName();
		this.description = thematicProps.getDescription();
		this.date = thematicProps.getDate();
		this.picture = thematicProps.getPicture();	
		this.createdBy = thematicProps.getCreatedBy();
		this.reserved = thematicProps.getReserved();
		this.culturalVenueId = thematicProps.getCulturalVenueId();
	}

	
	public Long getCulturalVenueId() {
		return culturalVenueId;
	}

	public void setCulturalVenueId(Long culturalVenueId) {
		this.culturalVenueId = culturalVenueId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ThematicPropsType getTptype() {
		return tptype;
	}

	public void setTptype(ThematicPropsType tptype) {
		this.tptype = tptype;
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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	
}
