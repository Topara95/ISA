package project.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static project.constants.ReservationConstants.*;

import java.util.ArrayList;
import java.util.List;

import project.domain.ProjectionTime;
import project.domain.Reservation;
import project.domain.Seat;
import project.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {
	
	@Autowired
	ReservationService resservice;
	
	@Autowired
	UserService userservice;
	
	@Autowired
	ProjectionTimeService ptservice;
	
	@Autowired
	SeatService seatservice;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		User user = userservice.findByEmail(OWNER_EMAIL);
		ProjectionTime pt = ptservice.findOne(PROJECTION_TIME_ID);
		Seat seat1 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW1);
		Seat seat2 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW2);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(seat1);
		seats.add(seat2);
		Reservation reservation = new Reservation(user,pt,seats);
		Reservation rese = resservice.save(reservation);
		assertThat(rese).isNotNull();
		assertThat(rese.getSeats()).hasSize(2);
		assertThat(rese.getOwner()).isEqualTo(user);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetReservationsForUser() {
		User user = userservice.findByEmail(TEST_EMAIL);
		ProjectionTime pt = ptservice.findOne(PROJECTION_TIME_ID);
		Seat seat1 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW1);
		Seat seat2 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW2);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(seat1);
		seats.add(seat2);
		Reservation reservation = new Reservation(user,pt,seats);
		resservice.save(reservation);
		List<Reservation> reservations = resservice.getReservationsForUser(user.getId());
		assertThat(reservations).isNotNull();
		assertThat(reservations).hasSize(1);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetReservation() {
		User user = userservice.findByEmail(TEST_EMAIL);
		ProjectionTime pt = ptservice.findOne(PROJECTION_TIME_ID);
		Seat seat1 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW1);
		Seat seat2 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW2);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(seat1);
		seats.add(seat2);
		Reservation reservation = new Reservation(user,pt,seats);
		resservice.save(reservation);
		
		Reservation res = resservice.getReservation(reservation.getId());
		assertThat(res).isNotNull();
		assertThat(res.getOwner()).isEqualTo(user);
		assertThat(res.getProjectionTime()).isEqualTo(pt);
		assertThat(res.getSeats()).hasSize(2);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCancelReservation() {
		User user = userservice.findByEmail(TEST_EMAIL);
		ProjectionTime pt = ptservice.findOne(PROJECTION_TIME_ID);
		Seat seat1 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW1);
		Seat seat2 = seatservice.findByHallAndRowAndSeatInRow(pt.getHall().getId(), SEAT_ROW, SEAT_IN_ROW2);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(seat1);
		seats.add(seat2);
		Reservation reservation = new Reservation(user,pt,seats);
		resservice.save(reservation);
		
		Reservation res = resservice.cancelReservation(reservation.getId());
		assertThat(res).isNotNull();
		assertThat(res.getId()).isEqualTo(reservation.getId());
	}
	
	@Test
	public void testGetVisitedReservationsForUser() {
		User user = userservice.findByEmail(TEST_EMAIL);
		List<Reservation> visited = resservice.getVisitedReservationsForUser(user.getId());
		assertThat(visited).isNotNull();
		assertThat(visited).hasSize(0);
	}
}
