package project.dto;

import java.util.Date;

import project.domain.EventProjection;

public class EventProjectionDTO {
	
	private EventDTO event;
	private Date projectionDate;
	
	public EventProjectionDTO(EventProjection eventProjection) {
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
	
}
