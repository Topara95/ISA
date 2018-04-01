package project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CulturalVenue implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private CulturalVenueType cvtype;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = true)
	private String description;
	
	@OneToMany(targetEntity = Hall.class)
	private List<Hall> halls;
	
	@OneToMany(targetEntity = EventProjection.class)
	private List<Event> events;
	
	public CulturalVenue(){
		
	}
	
	public CulturalVenue(CulturalVenueType cvtype, String name, String address, String description){
		this.cvtype = cvtype;
		this.name = name;
		this.address = address;
		this.description = description;
		this.halls = new ArrayList<Hall>();
		this.events = new ArrayList<Event>();
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

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> eventProjections) {
		this.events = eventProjections;
	}
}
