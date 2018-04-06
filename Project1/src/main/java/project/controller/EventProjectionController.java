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

import project.domain.EventProjection;
import project.dto.EventProjectionDTO;
import project.service.EventProjectionService;

@RestController
@RequestMapping("/api/events/{eventId}/eventProjections")
public class EventProjectionController {

	@Autowired
	private EventProjectionService epservice;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EventProjectionDTO>> getProjectionsForEvent(@PathVariable Long eventId){
		List<EventProjection> projections = epservice.getProjectionsForEvent(eventId);
		List<EventProjectionDTO> projectionsDTO = new ArrayList<EventProjectionDTO>();
		for(EventProjection projection : projections){
			projectionsDTO.add(new EventProjectionDTO(projection));
		}
		return new ResponseEntity<List<EventProjectionDTO>>(projectionsDTO,HttpStatus.OK);
	}
	
}
