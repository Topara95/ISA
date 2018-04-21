package project.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.CulturalVenue;
import project.domain.Event;
import project.domain.EventProjection;
import project.domain.ProjectionTime;
import project.domain.Seat;
import project.repository.CulturalVenueRepository;
import project.repository.EventProjectionRepository;
import project.repository.EventRepository;
import project.repository.ProjectionTimeRepository;
import project.repository.SeatRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventProjectionRepository projectionRepository;
	
	@Autowired
	private CulturalVenueRepository cvRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private ProjectionTimeRepository timeRepository;

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
		CulturalVenue cv = cvRepository.findOne(event.getCulturalVenue().getId());
		Hibernate.initialize(cv.getEvents());
		Hibernate.initialize(event.getProjections());
		List<EventProjection> projection = event.getProjections();
		for(int i=0;i<projection.size();i++) {
			Hibernate.initialize(projection.get(i).getProjectionTimes());
			List<ProjectionTime> time = projection.get(i).getProjectionTimes();
			for(int j=0;j<time.size();j++)  {
				Hibernate.initialize(time.get(j).getTakenSeats());
				List<Seat> seats = time.get(j).getTakenSeats();
				for(int k=0;k<seats.size();k++) {
					seats.remove(seats.get(k));
				}
				time.remove(time.get(j));
				timeRepository.delete(time.get(j));
			}
			projection.remove(projection.get(i));
			projectionRepository.delete(projection.get(i));
		}
		cv.getEvents().remove(event);
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

	@Override
	public Event findOne(Long id) {
		return eventRepository.findOne(id);
	}

	@Override
	public Event rateEvent(Long id, int grade) {
		Event event = eventRepository.findOne(id);
	    float zbir = 0;
		event.getGrades().add(grade);
		for(int i=0;i<event.getGrades().size();i++) {
			zbir += event.getGrades().get(i);
		}
		System.out.println(event.getGrades().size());
		float rezultat = (float) zbir/event.getGrades().size();
		System.out.println(zbir);
		System.out.println(rezultat);
		event.setAverageRating(rezultat);
		eventRepository.save(event);
		return event;
	}

}
