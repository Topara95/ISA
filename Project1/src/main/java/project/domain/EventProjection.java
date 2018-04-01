package project.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private Date projectionStartDate;
	
	@Column(nullable = false)
	private Date projectionEndDate;
	
	@Column(nullable = false)
	private float price;
	
	@OneToMany
	private List<ProjectionTime> projectionTimes;
	
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<ProjectionTime> getProjectionTimes() {
		return projectionTimes;
	}

	public void setProjectionTimes(List<ProjectionTime> projectionTimes) {
		this.projectionTimes = projectionTimes;
	}

	public Date getProjectionStartDate() {
		return projectionStartDate;
	}

	public void setProjectionStartDate(Date projectionStartDate) {
		this.projectionStartDate = projectionStartDate;
	}

	public Date getProjectionEndDate() {
		return projectionEndDate;
	}

	public void setProjectionEndDate(Date projectionEndDate) {
		this.projectionEndDate = projectionEndDate;
	}
}
