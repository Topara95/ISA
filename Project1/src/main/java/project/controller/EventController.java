package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.Event;
import project.dto.EventDTO;
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
}
