package project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private ProjectionTime projectionTime;
	
	@ManyToMany
	private List<Seat> seats;
	
	@OneToMany
	private List<Invite> invites;
	
	@Column
	private double totalprice;
	
	@Column
	private boolean visited;
	
	public Reservation(){};
	
	public Reservation(User owner, ProjectionTime projection, List<Seat> seats){
		this.owner = owner;
		this.projectionTime = projection;
		this.seats = seats;
		this.invites = new ArrayList<Invite>();
		this.visited = false;
		this.totalprice = seats.size() * projection.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public ProjectionTime getProjectionTime() {
		return projectionTime;
	}

	public void setProjectionTime(ProjectionTime projectionTime) {
		this.projectionTime = projectionTime;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Invite> getInvites() {
		return invites;
	}

	public void setInvites(List<Invite> invites) {
		this.invites = invites;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
