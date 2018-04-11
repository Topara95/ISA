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
@RequestMapping("/api/reservations")
public class ReservationController {
	
	@Autowired
	private InviteService inviteservice;
	
	@RequestMapping(value="/{resId}/sendInvite/{userId}",method = RequestMethod.GET)
	public ResponseEntity<InviteDTO> sendInvite(@PathVariable Long resId,@PathVariable Long userId){
		Invite inv = inviteservice.sendInvite(resId, userId);
		InviteDTO inviteDTO = new InviteDTO(inv);
		inviteservice.sendInviteMail(userId, inv.getId());
		return new ResponseEntity<InviteDTO>(inviteDTO,HttpStatus.OK);
	}
	
}
