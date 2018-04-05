package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.EventProjection;
import project.repository.EventProjectionRepository;

@Service
@Transactional
public class EventProjectionServiceImpl implements EventProjectionService{
	
	@Autowired
	private EventProjectionRepository eprepository;
	
	
	@Override
	public EventProjection save(EventProjection eventProjection) {
		return eprepository.save(eventProjection);
	}

}
