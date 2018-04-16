package project.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import project.domain.Reservation;

public class ReservationDTO {
	
	private Long id;
	private UserDTO owner;
	private ProjectionTimeDTO projectionTime;
	private List<SeatDTO> seats;
	private double totalprice;
	
	public ReservationDTO(Reservation reservation) {
		this.id = reservation.getId();
		this.owner = new UserDTO(reservation.getOwner());
		this.projectionTime = new ProjectionTimeDTO(reservation.getProjectionTime());
		this.seats = new ArrayList<SeatDTO>();
		Hibernate.initialize(reservation.getSeats());
		for(int i=0;i<reservation.getSeats().size();i++){
			this.seats.add(new SeatDTO(reservation.getSeats().get(i)));
		}
		this.totalprice = reservation.getTotalprice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}

	public ProjectionTimeDTO getProjectionTime() {
		return projectionTime;
	}

	public void setProjectionTime(ProjectionTimeDTO projectionTime) {
		this.projectionTime = projectionTime;
	}

	public List<SeatDTO> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatDTO> seats) {
		this.seats = seats;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	
}
