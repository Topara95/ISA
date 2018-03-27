package project.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EventProjection implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private Event event;
	
	@ManyToOne
	@JoinColumn
	private Hall hall;
	
	@Column(nullable = false)
	private Date projectionDate;
	
	@Column(nullable = false)
	private float price;
	
	/*@ManyToMany
    private List<Sediste> zauzetaSedista;

    @OneToMany
    private List<Rezervacija> rezervacije;*/
	
	public EventProjection(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Date getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(Date projectionDate) {
		this.projectionDate = projectionDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
