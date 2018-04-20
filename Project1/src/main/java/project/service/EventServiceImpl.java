package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.CulturalVenue;
import project.domain.Event;
import project.domain.ThematicProps;
import project.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public Event save(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public Event deleteEvent(Long id) {
		Event event = eventRepository.findById(id);
		eventRepository.delete(event);
		return event;
	}

	@Override
	public Event modify(Event event, Long id) {
		Event ev = eventRepository.findById(id);
		if(event.getName() != null) {
			ev.setName(event.getName());
		}
		if(event.getActors() != null) {
			ev.setActors(event.getActors());
		}
		if(event.getGenre() != null) {
			ev.setGenre(event.getGenre());
		}
		if(event.getDirector() != null) {
			ev.setDirector(event.getDirector());
		}
		if(event.getDuration() != null) {
			ev.setDuration(event.getDuration());
		}
		if(event.getPoster() != null) {
			ev.setPoster(event.getPoster());
		}
		if(event.getAverageRating() != 0.0) {
			ev.setAverageRating(event.getAverageRating());
		}
		if(event.getDescription() != null) {
			ev.setDescription(event.getDescription());
		}
		
		return eventRepository.save(ev);
	}

}
