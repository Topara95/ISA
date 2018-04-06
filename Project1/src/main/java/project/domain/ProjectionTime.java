package project.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProjectionTime implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private EventProjection eventProjection;
	
	@ManyToOne
	private Hall hall;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date time;
	
	@Column(nullable = false)
	private double price;
	
	@OneToMany
	private List<Seat> takenSeats;
	
	public ProjectionTime() {
		
	}
	
	public ProjectionTime(EventProjection projection,Hall hall,Date time, double price) {
		this.eventProjection = projection;
		this.hall = hall;
		this.time = time;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventProjection getEventProjection() {
		return eventProjection;
	}

	public void setEventProjection(EventProjection eventProjection) {
		this.eventProjection = eventProjection;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Seat> getTakenSeats() {
		return takenSeats;
	}

	public void setTakenSeats(List<Seat> takenSeats) {
		this.takenSeats = takenSeats;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

}
