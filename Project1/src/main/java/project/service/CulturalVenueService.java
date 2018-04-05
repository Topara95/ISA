package project.service;

import java.util.List;

import project.domain.CulturalVenue;

public interface CulturalVenueService {
	
	CulturalVenue save(CulturalVenue venue);
	
	List<CulturalVenue> getAllTheaters();
	
	List<CulturalVenue> getAllCinemas();
	
	CulturalVenue findOne(Long id);
}
