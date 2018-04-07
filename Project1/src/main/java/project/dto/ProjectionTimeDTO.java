package project.dto;

import java.util.Date;

import project.domain.ProjectionTime;

public class ProjectionTimeDTO {
	
	private Long id;
	private EventProjectionDTO eventProjection;
	private HallDTO hall;
	private Date time;
	private double price;
	
	public ProjectionTimeDTO(ProjectionTime pt){
		this.id = pt.getId();
		this.eventProjection = new EventProjectionDTO(pt.getEventProjection());
		this.hall= new HallDTO(pt.getHall());
		this.time = pt.getTime();
		this.price = pt.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventProjectionDTO getEventProjection() {
		return eventProjection;
	}

	public void setEventProjection(EventProjectionDTO eventProjection) {
		this.eventProjection = eventProjection;
	}

	public HallDTO getHall() {
		return hall;
	}

	public void setHall(HallDTO hall) {
		this.hall = hall;
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
}
