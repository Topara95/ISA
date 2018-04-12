package project.service;



import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.CulturalVenue;
import project.domain.CulturalVenueType;
import project.domain.Event;
import project.repository.CulturalVenueRepository;

@Service
public class CulturalVenueServiceImpl implements CulturalVenueService{

	@Autowired
	private CulturalVenueRepository cvrepository;
	
	@Override
	public CulturalVenue save(CulturalVenue venue) {
		return cvrepository.save(venue);
	}

	@Override
	public List<CulturalVenue> getAllTheaters() {
		return cvrepository.findByCvtype(CulturalVenueType.THEATER);
	}

	@Override
	public List<CulturalVenue> getAllCinemas() {
		return cvrepository.findByCvtype(CulturalVenueType.CINEMA);
	}

	@Override
	public CulturalVenue findOne(Long id) {
		return cvrepository.findOne(id);
	}

	@Override
	public List<Event> getEvents(Long cvId) {
		CulturalVenue cv = cvrepository.findOne(cvId);
		Hibernate.initialize(cv.getEvents());
		return cv.getEvents();
	}

}
