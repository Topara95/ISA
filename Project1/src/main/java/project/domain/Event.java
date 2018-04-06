package project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Event implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private EventType eventType;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String actors;
	
	@Column(nullable = true)
	private EventGenre genre;
	
	@Column(nullable = true)
	private String director;
	
	@Column(nullable = true)
	private String duration;
	
	@Column(nullable = true)
	private String poster;
	
	@Column(nullable = true)
	private float averageRating;
	
	@Column(nullable = true)
	private String description;
	
	@OneToMany
	private List<EventProjection> projections;
	
	@ManyToOne
	private CulturalVenue culturalVenue;
	
	public Event(){}
	
	public Event(EventType eventType, String name, String actors, EventGenre genre, String director, String duration, String poster, 
			float averageRating, String description, CulturalVenue culturalVenue) {
		this.eventType = eventType;
		this.name = name;
		this.actors = actors;
		this.genre = genre;
		this.director = director;
		this.duration = duration;
		this.poster = poster;
		this.averageRating = averageRating;
		this.description = description;
		projections = new ArrayList<EventProjection>();
		this.culturalVenue = culturalVenue;
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

	public List<EventProjection> getProjections() {
		return projections;
	}

	public void setProjections(List<EventProjection> projections) {
		this.projections = projections;
	}

	public CulturalVenue getCulturalVenue() {
		return culturalVenue;
	}

	public void setCulturalVenue(CulturalVenue culturalVenue) {
		this.culturalVenue = culturalVenue;
	}
	
	
}
