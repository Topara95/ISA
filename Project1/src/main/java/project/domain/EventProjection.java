package project.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="projection")
public class EventProjection implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Event event;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date projectionDate;
	
	@OneToMany
	private List<ProjectionTime> projectionTimes;
	
	/*@ManyToMany
    private List<Sediste> zauzetaSedista;

    @OneToMany
    private List<Rezervacija> rezervacije;*/
	
	public EventProjection(){}
	
	public EventProjection(Event event, Date pdate) {
		this.event = event;
		this.projectionDate = pdate;
		this.projectionTimes = new ArrayList<ProjectionTime>();
	}

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

	public List<ProjectionTime> getProjectionTimes() {
		return projectionTimes;
	}

	public void setProjectionTimes(List<ProjectionTime> projectionTimes) {
		this.projectionTimes = projectionTimes;
	}

	public Date getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(Date projectionDate) {
		this.projectionDate = projectionDate;
	}

}
