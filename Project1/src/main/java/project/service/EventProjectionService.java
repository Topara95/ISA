package project.service;

import java.util.List;

import project.domain.EventProjection;

public interface EventProjectionService {
	
	EventProjection save(EventProjection eventProjection);
	
	List<EventProjection> getProjectionsForEvent(Long eventId);
}
