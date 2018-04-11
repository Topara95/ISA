package project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.ProjectionTime;
import project.domain.Reservation;
import project.domain.Seat;
import project.domain.User;
import project.dto.ProjectionTimeDTO;
import project.dto.ReservationDTO;
import project.dto.SeatDTO;
import project.service.ProjectionTimeService;
import project.service.SeatService;

@RestController
@RequestMapping("/api/events/{eventId}/eventProjections/{projectionId}/projectionTimes")
public class ProjectionTimeController {

	@Autowired
	private ProjectionTimeService ptservice;
	
	@Autowired
	private SeatService seatservice;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProjectionTimeDTO>> getTimesForProjection(@PathVariable Long eventId, @PathVariable Long projectionId){
		List<ProjectionTimeDTO> projectiontimesDTO = new ArrayList<ProjectionTimeDTO>();
		for(ProjectionTime pt : ptservice.findByEventProjection(projectionId)){
			projectiontimesDTO.add(new ProjectionTimeDTO(pt));
		}
		return new ResponseEntity<List<ProjectionTimeDTO>>(projectiontimesDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{projectionId}/seats",method = RequestMethod.GET)
	public ResponseEntity<List<SeatDTO>> getSeatsForProjection(@PathVariable Long projectionId){
		ProjectionTime pt = ptservice.findOne(projectionId);
		List<Seat> seats = seatservice.findByHall(pt.getHall().getId());
		List<SeatDTO> seatsDTO = new ArrayList<SeatDTO>();
		for(Seat seat : seats){
			seatsDTO.add(new SeatDTO(seat));
		}
		return new ResponseEntity<List<SeatDTO>>(seatsDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{projectionId}/seats",method = RequestMethod.POST)
	public ResponseEntity<ReservationDTO> reserveSeats(@RequestBody List<String> seatInfo, @PathVariable Long projectionId, HttpServletRequest request){
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		Reservation reservation = ptservice.reserveSeats(projectionId, seatInfo, loggedUser.getId());
		ReservationDTO reservationDTO = new ReservationDTO(reservation);
		return new ResponseEntity<ReservationDTO>(reservationDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{projectionId}/takenSeats",method = RequestMethod.GET)
	public ResponseEntity<List<SeatDTO>> getTakenSeats(@PathVariable Long projectionId){
		List<Seat> takenSeats = ptservice.getTakenSeats(projectionId);
		List<SeatDTO> tekenSeatsDTO = new ArrayList<SeatDTO>();
		for(Seat seat : takenSeats) {
			tekenSeatsDTO.add(new SeatDTO(seat));
		}
		return new ResponseEntity<List<SeatDTO>>(tekenSeatsDTO,HttpStatus.OK);
	}
}
