package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.CulturalVenue;
import project.domain.Event;
import project.domain.ThematicProps;
import project.dto.CulturalVenueDTO;
import project.dto.EventDTO;
import project.dto.ThematicPropsDTO;
import project.service.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {
	
	@Autowired
	private EventService eventservice;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EventDTO>> getAllEvents(){
		List<Event> events = eventservice.findAll();
		List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
		for(Event event : events){
			eventsDTO.add(new EventDTO(event));
		}
		return new ResponseEntity<List<EventDTO>>(eventsDTO,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EventDTO> save(@RequestBody Event event){
		EventDTO eventDTO = new EventDTO(eventservice.save(event));
		return new ResponseEntity<EventDTO>(eventDTO,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/rate/{id}/{grade}", method = RequestMethod.GET)
	public ResponseEntity<EventDTO> rateEvent(@PathVariable Long id,@PathVariable int grade) {
		Event event = eventservice.rateEvent(id, grade);
		EventDTO event1 = new EventDTO(event);
		return new ResponseEntity<EventDTO>(event1,HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<EventDTO> deleteEvents(@PathVariable Long id) {
		Event event = eventservice.deleteEvent(id);
		EventDTO eventDTO = new EventDTO(event);	
		return new ResponseEntity<EventDTO>(eventDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{evId}",
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EventDTO> modify(@RequestBody Event event,@PathVariable Long evId) {
		//System.out.println(venue.getCvtype().name());
		Event ev = eventservice.modify(event, evId);
		EventDTO evDTO = new EventDTO(ev);
		return new ResponseEntity<EventDTO>(evDTO,HttpStatus.OK);
	}
}
