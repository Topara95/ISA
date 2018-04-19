package project.service;

import java.util.List;

import project.domain.CulturalVenue;
import project.domain.Event;

public interface CulturalVenueService {
	
	CulturalVenue save(CulturalVenue venue);
	
	CulturalVenue modify(CulturalVenue venue,Long id);
	
	List<CulturalVenue> getAllTheaters();
	
	List<CulturalVenue> getAllCinemas();
	
	List<CulturalVenue> searchCinemas(String name, String address);
	
	List<CulturalVenue> searchTheaters(String name, String address);
	
	List<Event> getEvents(Long cvId);
	
	CulturalVenue findOne(Long id);
	
	List<CulturalVenue> getAll();
}
