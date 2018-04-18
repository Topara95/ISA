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
import project.dto.CulturalVenueDTO;
import project.dto.EventDTO;
import project.service.CulturalVenueService;

@RestController
@RequestMapping("/api/culturalVenues")
public class CulturalVenueController {
	
	@Autowired
	private CulturalVenueService cvservice;
	
	@RequestMapping(method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CulturalVenueDTO> save(@RequestBody CulturalVenue venue) {
		System.out.println(venue.getCvtype().name());
		CulturalVenueDTO culVenDTO = new CulturalVenueDTO(cvservice.save(venue));
		return new ResponseEntity<CulturalVenueDTO>(culVenDTO,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CulturalVenueDTO>> getAll(){
		List<CulturalVenueDTO> cvDTO = new ArrayList<CulturalVenueDTO>();
		for(CulturalVenue theater : cvservice.getAll()){
			cvDTO.add(new CulturalVenueDTO(theater));
		}
		return new ResponseEntity<List<CulturalVenueDTO>>(cvDTO,HttpStatus.OK);
	}
	
	
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
	
	@RequestMapping(value="/searchCinemas/{name}/{address}",method = RequestMethod.GET)
	public ResponseEntity<List<CulturalVenueDTO>> searchCinemas(@PathVariable String name, @PathVariable String address){
		List<CulturalVenueDTO> cinemasDTO = new ArrayList<CulturalVenueDTO>();
		for(CulturalVenue cinema : cvservice.searchCinemas(name, address)){
			cinemasDTO.add(new CulturalVenueDTO(cinema));
		}
		return new ResponseEntity<List<CulturalVenueDTO>>(cinemasDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchTheaters/{name}/{address}",method = RequestMethod.GET)
	public ResponseEntity<List<CulturalVenueDTO>> searchTheates(@PathVariable String name, @PathVariable String address){
		List<CulturalVenueDTO> theatersDTO = new ArrayList<CulturalVenueDTO>();
		for(CulturalVenue theater : cvservice.searchTheaters(name, address)){
			theatersDTO.add(new CulturalVenueDTO(theater));
		}
		return new ResponseEntity<List<CulturalVenueDTO>>(theatersDTO,HttpStatus.OK);
	}
	
}
