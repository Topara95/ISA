package project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.constants.ReservationConstants.OWNER_EMAIL;
import static project.constants.ReservationConstants.PROJECTION_TIME_ID;
import static project.constants.ReservationConstants.SEAT_IN_ROW1;
import static project.constants.ReservationConstants.SEAT_IN_ROW2;
import static project.constants.ReservationConstants.SEAT_ROW;
import static project.constants.ReservationConstants.TEST_EMAIL;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import project.domain.Invite;
import project.domain.ProjectionTime;
import project.domain.Reservation;
import project.domain.Seat;
import project.domain.User;
import project.service.InviteService;
import project.service.ProjectionTimeService;
import project.service.ReservationService;
import project.service.SeatService;
import project.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InviteControllerTest {
	
	private static final String URL_PREFIX = "/api/invites";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ReservationService resservice;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private ProjectionTimeService ptservice;
	
	@Autowired
	private SeatService seatservice;
	
	@Autowired
	private InviteService inviteservice;
	
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptInvite() throws Exception{
		User user = userservice.findByEmail(OWNER_EMAIL);
		User user2 = userservice.findByEmail(TEST_EMAIL);
		ProjectionTime pt = ptservice.findOne(PROJECTION_TIME_ID);
		Seat seat1 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW1);
		Seat seat2 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW2);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(seat1);
		seats.add(seat2);
		Reservation reservation = new Reservation(user,pt,seats);
		resservice.save(reservation);
		
		Invite invite = inviteservice.sendInvite(reservation.getId(), user2.getId());
		
		mockMvc.perform(get(URL_PREFIX+"/"+invite.getId()+"/accept")).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(invite.getId()))
		.andExpect(jsonPath("$.accepted").value(true));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeclineInvite() throws Exception{
		User user = userservice.findByEmail(OWNER_EMAIL);
		User user2 = userservice.findByEmail(TEST_EMAIL);
		ProjectionTime pt = ptservice.findOne(PROJECTION_TIME_ID);
		Seat seat1 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW1);
		Seat seat2 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW2);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(seat1);
		seats.add(seat2);
		Reservation reservation = new Reservation(user,pt,seats);
		resservice.save(reservation);
		
		Invite invite = inviteservice.sendInvite(reservation.getId(), user2.getId());
		
		mockMvc.perform(get(URL_PREFIX+"/"+invite.getId()+"/decline")).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(invite.getId()))
		.andExpect(jsonPath("$.accepted").value(false));
	}
	
}
