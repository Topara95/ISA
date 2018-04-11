package project.dto;

import project.domain.Reservation;

public class ReservationDTO {
	
	private Long id;
	private UserDTO owner;
	private ProjectionTimeDTO projectionTime;
	
	public ReservationDTO(Reservation reservation) {
		this.id = reservation.getId();
		this.owner = new UserDTO(reservation.getOwner());
		this.projectionTime = new ProjectionTimeDTO(reservation.getProjectionTime());
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
	
}
