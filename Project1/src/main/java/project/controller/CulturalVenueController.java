package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.CulturalVenue;
import project.domain.Event;
import project.dto.CulturalVenueDTO;
import project.dto.EventDTO;
import project.service.CulturalVenueService;

@RestController
@RequestMapping("/api/culturalVenues")
public class CulturalVenueController {
	
	@Autowired
	private CulturalVenueService cvservice;
	
	@RequestMapping(value="/getTheaters", method = RequestMethod.GET)
	public ResponseEntity<List<CulturalVenueDTO>> getTheaters(){
		List<CulturalVenueDTO> theatersDTO = new ArrayList<CulturalVenueDTO>();
		for(CulturalVenue theater : cvservice.getAllTheaters()){
			theatersDTO.add(new CulturalVenueDTO(theater));
		}
		return new ResponseEntity<List<CulturalVenueDTO>>(theatersDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getCinemas", method = RequestMethod.GET)
	public ResponseEntity<List<CulturalVenueDTO>> getCinemas(){
		List<CulturalVenueDTO> cinemasDTO = new ArrayList<CulturalVenueDTO>();
		for(CulturalVenue cinema : cvservice.getAllCinemas()){
			cinemasDTO.add(new CulturalVenueDTO(cinema));
		}
		return new ResponseEntity<List<CulturalVenueDTO>>(cinemasDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{cvId}/getEvents",method = RequestMethod.GET)
	public ResponseEntity<List<EventDTO>> getEventsForVenue(@PathVariable Long cvId){
		List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
		for(Event event : cvservice.getEvents(cvId)){
			eventsDTO.add(new EventDTO(event));
		}
		return new ResponseEntity<List<EventDTO>>(eventsDTO,HttpStatus.OK);
	}
	
}
