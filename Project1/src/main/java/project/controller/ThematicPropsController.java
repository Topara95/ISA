package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.ThematicProps;
import project.dto.ThematicPropsDTO;
import project.service.ThematicPropsService;

@RestController
@RequestMapping("/api/props")
public class ThematicPropsController {
	
	@Autowired
	private ThematicPropsService thematicPropsService;	
	
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ThematicPropsDTO> save(@RequestBody ThematicProps thematicProps){
		ThematicPropsDTO thematicPropsDTO = new ThematicPropsDTO(thematicPropsService.save(thematicProps));
		return new ResponseEntity<ThematicPropsDTO>(thematicPropsDTO,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ThematicPropsDTO>> getAllThematicProps(){
		List<ThematicProps> thematicPropsList = thematicPropsService.findAll();
		List<ThematicPropsDTO> thematicPropsDTO = new ArrayList<ThematicPropsDTO>();
		for(ThematicProps thematicProps : thematicPropsList){
			thematicPropsDTO.add(new ThematicPropsDTO(thematicProps));
		}
		return new ResponseEntity<List<ThematicPropsDTO>>(thematicPropsDTO,HttpStatus.OK);
	}

}
