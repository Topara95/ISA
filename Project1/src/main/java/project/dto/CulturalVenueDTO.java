package project.dto;

import project.domain.CulturalVenue;
import project.domain.CulturalVenueType;

public class CulturalVenueDTO {
	
	private Long id;
	private CulturalVenueType cvtype;
	private String name;
	private String address;
	private String description;
	
	public CulturalVenueDTO(CulturalVenue venue){
		this.id = venue.getId();
		this.cvtype = venue.getCvtype();
		this.name = venue.getName();
		this.address = venue.getAddress();
		this.description = venue.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CulturalVenueType getCvtype() {
		return cvtype;
	}

	public void setCvtype(CulturalVenueType cvtype) {
		this.cvtype = cvtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
