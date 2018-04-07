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

import project.domain.ProjectionTime;
import project.dto.ProjectionTimeDTO;
import project.service.ProjectionTimeService;

@RestController
@RequestMapping("/api/events/{eventId}/eventProjections/{projectionId}/projectionTimes")
public class ProjectionTimeController {

	@Autowired
	private ProjectionTimeService ptservice;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProjectionTimeDTO>> getTimesForProjection(@PathVariable Long eventId, @PathVariable Long projectionId){
		List<ProjectionTimeDTO> projectiontimesDTO = new ArrayList<ProjectionTimeDTO>();
		for(ProjectionTime pt : ptservice.findByEventProjection(projectionId)){
			projectiontimesDTO.add(new ProjectionTimeDTO(pt));
		}
		return new ResponseEntity<List<ProjectionTimeDTO>>(projectiontimesDTO,HttpStatus.OK);
	}
}
