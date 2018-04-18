package project.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import project.domain.ProjectionTime;
import project.domain.Reservation;
import project.domain.Seat;
import project.domain.User;
import project.service.InviteService;
import project.service.ProjectionTimeService;
import project.service.ReservationService;
import project.service.SeatService;
import project.service.UserService;

import static project.constants.ReservationConstants.*;
import static project.constants.UserConstants.DB_USER_COUNT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTest {
	
	private static final String URL_PREFIX = "/api/reservations";

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
	
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSendInvite() throws Exception {
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
		
		mockMvc.perform(get(URL_PREFIX+"/"+reservation.getId()+"/sendInvite/"+user2.getId())).andExpect(status().isOk())
		.andExpect(jsonPath("$.reservation.id").value(reservation.getId()))
		.andExpect(jsonPath("$.invitedUser.id").value(user2.getId()));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetReservationsforLoggedUser() throws Exception {
		User user = userservice.findByEmail(TEST_EMAIL);
		ProjectionTime pt = ptservice.findOne(PROJECTION_TIME_ID);
		Seat seat1 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW1);
		Seat seat2 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW2);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(seat1);
		seats.add(seat2);
		Reservation reservation = new Reservation(user,pt,seats);
		resservice.save(reservation);
		
		MockHttpSession mockHttpSession = new MockHttpSession(); 
		mockHttpSession.setAttribute("loggedUser", user);
		
		mockMvc.perform(get(URL_PREFIX+"/getAllForLogged").session(mockHttpSession).sessionAttr("loggedUser", user)).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetVisitsforLoggedUser() throws Exception {
		User user = userservice.findByEmail(TEST_EMAIL);
		MockHttpSession mockHttpSession = new MockHttpSession(); 
		mockHttpSession.setAttribute("loggedUser", user);
		
		mockMvc.perform(get(URL_PREFIX+"/getVisitsForLogged").session(mockHttpSession).sessionAttr("loggedUser", user)).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetReservation() throws Exception {
		User user = userservice.findByEmail(TEST_EMAIL);
		ProjectionTime pt = ptservice.findOne(PROJECTION_TIME_ID);
		Seat seat1 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW1);
		Seat seat2 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW2);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(seat1);
		seats.add(seat2);
		Reservation reservation = new Reservation(user,pt,seats);
		resservice.save(reservation);
		
		mockMvc.perform(get(URL_PREFIX+"/"+reservation.getId())).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(reservation.getId()));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCancelReservation() throws Exception{
		User user = userservice.findByEmail(TEST_EMAIL);
		ProjectionTime pt = ptservice.findOne(PROJECTION_TIME_ID);
		Seat seat1 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW1);
		Seat seat2 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW2);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(seat1);
		seats.add(seat2);
		Reservation reservation = new Reservation(user,pt,seats);
		resservice.save(reservation);
		
		mockMvc.perform(delete(URL_PREFIX+"/cancel/"+reservation.getId())).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(reservation.getId()));
	}
}
