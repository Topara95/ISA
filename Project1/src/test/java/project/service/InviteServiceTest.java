package project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static project.constants.ReservationConstants.OWNER_EMAIL;
import static project.constants.ReservationConstants.TEST_EMAIL;
import static project.constants.ReservationConstants.PROJECTION_TIME_ID;
import static project.constants.ReservationConstants.SEAT_IN_ROW1;
import static project.constants.ReservationConstants.SEAT_IN_ROW2;
import static project.constants.ReservationConstants.SEAT_ROW;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import project.domain.Invite;
import project.domain.ProjectionTime;
import project.domain.Reservation;
import project.domain.Seat;
import project.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InviteServiceTest {
	
	@Autowired
	InviteService inviteservice;
	
	@Autowired
	UserService userservice;
	
	@Autowired
	ProjectionTimeService ptservice;
	
	@Autowired
	SeatService seatservice;
	
	@Autowired
	ReservationService resservice;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSendInvite() {
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
		
		assertThat(invite).isNotNull();
		assertThat(invite.getReservation()).isEqualTo(reservation);
		assertThat(invite.getInvitedUser()).isEqualTo(user2);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptInvite() {
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
		
		Invite inv =inviteservice.acceptInvite(invite.getId());
		assertThat(inv).isNotNull();
		assertThat(inv.getReservation()).isEqualTo(reservation);
		assertThat(inv.getId()).isEqualTo(invite.getId());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeclineInvite() {
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
		
		Invite inv =inviteservice.declineInvite(invite.getId());
		
		assertThat(inv).isNotNull();
		assertThat(inv.getReservation()).isEqualTo(reservation);
		assertThat(inv.getId()).isEqualTo(invite.getId());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindOne() {
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
		
		Invite inv =inviteservice.findOne(invite.getId());
		
		assertThat(inv).isNotNull();
		assertThat(inv.getReservation()).isEqualTo(reservation);
		assertThat(inv.getId()).isEqualTo(invite.getId());
		
	}
}
