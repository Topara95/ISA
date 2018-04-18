package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.MembershipThreshold;
import project.service.MembershipThresholdService;

@RestController
@RequestMapping("/api/threshold")
public class MembershipThresholdController {
	
	@Autowired
	private MembershipThresholdService mtService;
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<MembershipThreshold> modify(@RequestBody MembershipThreshold mt){
		//System.out.println(mt.getGoldThreshold() + " " + mt.getSilverThreshold() + " " + mt.getBronzeThreshold());
		MembershipThreshold mt1 = mtService.modify(mt);
		return new ResponseEntity<MembershipThreshold>(mt1,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<MembershipThreshold> getAll(){
		List<MembershipThreshold> mt1 = mtService.findAll();
		MembershipThreshold mt2 = mt1.get(0);
		return new ResponseEntity<MembershipThreshold>(mt2,HttpStatus.OK);
	}

}
