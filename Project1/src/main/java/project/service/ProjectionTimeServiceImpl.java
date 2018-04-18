package project.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.domain.EventProjection;
import project.domain.ProjectionTime;
import project.domain.Reservation;
import project.domain.Seat;
import project.domain.User;
import project.repository.EventProjectionRepository;
import project.repository.ProjectionTimeRepository;
import project.repository.ReservationRepository;
import project.repository.SeatRepository;
import project.repository.UserRepository;

@Service
public class ProjectionTimeServiceImpl implements ProjectionTimeService{
	
	@Autowired
	private ProjectionTimeRepository ptrepository;
	
	@Autowired
	private EventProjectionRepository eprepository;
	
	@Autowired
	private SeatRepository seatrepository;
	
	@Autowired
	private ReservationRepository reservationrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<ProjectionTime> findByEventProjection(Long eventProjectionId) {
		EventProjection ep = eprepository.findOne(eventProjectionId);
		return ptrepository.findByEventProjection(ep);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
	public ProjectionTime save(ProjectionTime projectionTime) {
		return ptrepository.save(projectionTime);
	}

	@Override
	@Transactional(readOnly = true)
	public ProjectionTime findOne(Long ptId) {
		return ptrepository.findOne(ptId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
	public Reservation reserveSeats(Long projectiontimeId,List<String> seatinfo, Long userId) {
		//adding to taken seats
		ProjectionTime pt = ptrepository.findOne(projectiontimeId);
		Hibernate.initialize(pt.getTakenSeats());
		List<Seat> resSeats = new ArrayList<Seat>();
		for(int i=0;i<seatinfo.size();i++) {
			String arr[] = seatinfo.get(i).split("_");
			Seat seat =seatrepository.findByHallAndRowAndSeatInRow(pt.getHall(), Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
			resSeats.add(seat);
			pt.getTakenSeats().add(seat);
		}
		ptrepository.save(pt);
		//end
		//saving reservation
		User user = userrepository.findOne(userId);
		Reservation reservation = new Reservation(user,pt,resSeats);
		reservationrepository.save(reservation);
		Hibernate.initialize(user.getReservations());
		user.getReservations().add(reservation);
		userrepository.save(user);
		//end
		return reservation;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Seat> getTakenSeats(Long projectiontimeId) {
		ProjectionTime pt = ptrepository.findOne(projectiontimeId);
		Hibernate.initialize(pt.getTakenSeats());
		return pt.getTakenSeats();
	}

}
