package project.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Invite;
import project.domain.ProjectionTime;
import project.domain.Reservation;
import project.domain.User;
import project.repository.InviteRepository;
import project.repository.ProjectionTimeRepository;
import project.repository.ReservationRepository;
import project.repository.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationRepository reservationrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ProjectionTimeRepository ptrepository;
	
	@Autowired
	private InviteRepository inviterepository;
	
	@Override
	public Reservation save(Reservation reservation) {
		return reservationrepository.save(reservation);
	}

	@Override
	public List<Reservation> getReservationsForUser(Long userId) {
		User user = userrepository.findOne(userId);
		return reservationrepository.findByOwner(user);
	}

	@Override
	public Reservation getReservation(Long resId) {
		return reservationrepository.findOne(resId);
	}

	@Override
	public Reservation cancelReservation(Long resId) {
		Date date = new Date();
		boolean invalid = false;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date);
		java.sql.Date sqlDateNow = new java.sql.Date(date.getTime());
		//
		Reservation res = reservationrepository.findOne(resId);
		java.sql.Date sqlDatePtime = new java.sql.Date(res.getProjectionTime().getTime().getTime());
		java.sql.Date sqlDatePdate = new java.sql.Date(res.getProjectionTime().getEventProjection().getProjectionDate().getTime());
		cal2.setTime(sqlDatePtime);
		if(sqlDatePdate.compareTo(sqlDateNow) >= 0) {
			System.out.println("Datum projekcije je posle danasnjeg dana");
			if(sqlDatePdate.compareTo(sqlDateNow) == 0) {
				if(cal1.get(Calendar.HOUR_OF_DAY) > cal2.get(Calendar.HOUR_OF_DAY)) {
					invalid = true;
				}else if(cal2.get(Calendar.HOUR_OF_DAY) == cal1.get(Calendar.HOUR_OF_DAY)) {
					if(cal2.get(Calendar.MINUTE)-30 < cal1.get(Calendar.MINUTE)) {
						invalid = true;
					}
				}
			}
		}else {
			invalid = true;
		}
		
		if(!invalid) {
			ProjectionTime pt = res.getProjectionTime();
			User user = userrepository.findOne(res.getOwner().getId());
			Hibernate.initialize(user.getReservations());
			Hibernate.initialize(res.getSeats());
			Hibernate.initialize(res.getInvites());
			Hibernate.initialize(pt.getTakenSeats());
			res.getInvites().clear();
			List<Invite> invites = inviterepository.findByReservation(res);
			inviterepository.delete(invites);
			pt.getTakenSeats().removeAll(res.getSeats());
			user.getReservations().remove(res);
			userrepository.save(user);
			reservationrepository.delete(resId);
			ptrepository.save(pt);
			return res;
		}else {
			return null;
		}
	}

}
