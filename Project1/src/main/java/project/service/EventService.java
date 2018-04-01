package project.service;

import java.util.List;

import project.domain.Event;

public interface EventService {
	
	Event save(Event event);
	
	List<Event> findAll();
	
}
