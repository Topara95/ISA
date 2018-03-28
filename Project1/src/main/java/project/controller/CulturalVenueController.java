package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.CulturalVenue;
import project.service.CulturalVenueService;

@RestController
@RequestMapping("/api/culturalVenues")
public class CulturalVenueController {
	
	@Autowired
	private CulturalVenueService cvservice;
	
	@RequestMapping(value="/getTheaters", method = RequestMethod.GET)
	public ResponseEntity<List<CulturalVenue>> getTheaters(){
		return new ResponseEntity<List<CulturalVenue>>(cvservice.getAllTheaters(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/getCinemas", method = RequestMethod.GET)
	public ResponseEntity<List<CulturalVenue>> getCinemas(){
		return new ResponseEntity<List<CulturalVenue>>(cvservice.getAllCinemas(),HttpStatus.OK);
	}
	
}
