package project.service;

import java.util.List;

import project.domain.CulturalVenue;
import project.domain.Event;

public interface CulturalVenueService {
	
	CulturalVenue save(CulturalVenue venue);
	
	List<CulturalVenue> getAllTheaters();
	
	List<CulturalVenue> getAllCinemas();
	
	List<Event> getEvents(Long cvId);
	
	CulturalVenue findOne(Long id);
}
