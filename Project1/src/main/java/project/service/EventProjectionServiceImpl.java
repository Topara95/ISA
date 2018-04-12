package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.Event;
import project.domain.EventProjection;
import project.repository.EventProjectionRepository;
import project.repository.EventRepository;

@Service
public class EventProjectionServiceImpl implements EventProjectionService{
	
	@Autowired
	private EventProjectionRepository eprepository;
	
	@Autowired
	private EventRepository eventrepository;
	
	
	@Override
	public EventProjection save(EventProjection eventProjection) {
		return eprepository.save(eventProjection);
	}


	@Override
	public List<EventProjection> getProjectionsForEvent(Long eventId) {
		Event event = eventrepository.findOne(eventId);
		return eprepository.findByEvent(event);
	}

}
