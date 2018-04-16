package project.dto;

import project.domain.Event;
import project.domain.EventGenre;
import project.domain.EventType;

public class EventDTO {
	
	private Long id;
	private EventType eventType;
	private String name;
	private String actors;
	private EventGenre genre;
	private String director;
	private String duration;
	private String poster;
	private float averageRating;
	private String description;
	private CulturalVenueDTO culturalVenue;
	
	public EventDTO(Event event) {
		this.id = event.getId();
		this.eventType = event.getEventType();
		this.name = event.getName();
		this.actors = event.getActors();
		this.genre = event.getGenre();
		this.director = event.getDirector();
		this.duration = event.getDuration();
		this.poster = event.getPoster();
		this.averageRating = event.getAverageRating();
		this.description = event.getDescription();
		this.culturalVenue = new CulturalVenueDTO(event.getCulturalVenue());
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public EventType getEventType() {
		return eventType;
	}


	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getActors() {
		return actors;
	}


	public void setActors(String actors) {
		this.actors = actors;
	}


	public EventGenre getGenre() {
		return genre;
	}


	public void setGenre(EventGenre genre) {
		this.genre = genre;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
	}


	public float getAverageRating() {
		return averageRating;
	}


	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public CulturalVenueDTO getCulturalVenue() {
		return culturalVenue;
	}


	public void setCulturalVenue(CulturalVenueDTO culturalVenue) {
		this.culturalVenue = culturalVenue;
	}
}
