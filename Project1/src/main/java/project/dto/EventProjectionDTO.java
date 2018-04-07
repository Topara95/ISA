package project.dto;

import java.util.Date;

import project.domain.EventProjection;

public class EventProjectionDTO {
	
	private Long id;
	private EventDTO event;
	private Date projectionDate;
	
	public EventProjectionDTO(EventProjection eventProjection) {
		this.id = eventProjection.getId();
		this.event = new EventDTO(eventProjection.getEvent());
		this.projectionDate = eventProjection.getProjectionDate();
	}

	public EventDTO getEvent() {
		return event;
	}

	public void setEvent(EventDTO event) {
		this.event = event;
	}

	public Date getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(Date projectionDate) {
		this.projectionDate = projectionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
