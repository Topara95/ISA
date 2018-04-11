package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.Invite;
import project.dto.InviteDTO;
import project.service.InviteService;

@RestController
@RequestMapping("/api/invites")
public class InviteController {
	
	@Autowired
	private InviteService inviteservice;
	
	@RequestMapping(value="/{invId}/accept",method = RequestMethod.GET)
	public ResponseEntity<InviteDTO> acceptInvite(@PathVariable Long invId){
		Invite inv = inviteservice.acceptInvite(invId);
		InviteDTO inviteDTO = new InviteDTO(inv);
		return new ResponseEntity<InviteDTO>(inviteDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{invId}/decline",method = RequestMethod.GET)
	public ResponseEntity<InviteDTO> declineInvite(@PathVariable Long invId){
		Invite inv = inviteservice.declineInvite(invId);
		InviteDTO inviteDTO = new InviteDTO(inv);
		return new ResponseEntity<InviteDTO>(inviteDTO,HttpStatus.OK);
	}
	
}
