package project;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.domain.CulturalVenue;
import project.domain.CulturalVenueType;
import project.domain.Event;
import project.domain.EventGenre;
import project.domain.EventProjection;
import project.domain.EventType;
import project.domain.Hall;
import project.domain.MembershipThreshold;
import project.domain.Offer;
import project.domain.ProjectionTime;
import project.domain.Seat;
import project.domain.ThematicProps;
import project.domain.ThematicPropsType;
import project.domain.User;
import project.domain.UserType;
import project.repository.MembershipThresholdRepository;
import project.service.CulturalVenueService;
import project.service.EventProjectionService;
import project.service.EventService;
import project.service.HallService;
import project.service.OfferService;
import project.service.ProjectionTimeService;
import project.service.SeatService;
import project.service.ThematicPropsService;
import project.service.UserService;

@Component
public class ProjectTestData {
	
	@Autowired
	private CulturalVenueService cvservice;
	
	@Autowired
	private EventService eventservice;
	
	@Autowired
	private HallService hallservice;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private EventProjectionService epservice;
	
	@Autowired
	private ProjectionTimeService ptservice;
	
	@Autowired 
	private SeatService seatservice;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired 
	private ThematicPropsService thematicPropsService;
	
	@Autowired
	private MembershipThresholdRepository mtrepository;
	
	@PostConstruct
	private void init() throws ParseException{
		
		if(userservice.findAll().size() == 0) {
			
			MembershipThreshold mt = new MembershipThreshold(100,200,300,true);
			mtrepository.save(mt);
		
			User user1 = new User("jovantopolic@gmail.com","jova","Jovan","Topolic","Novi Sad","6611632",UserType.REGULAR);
			user1.setVerified(true);
			userservice.save(user1);
			
			User user2 = new User("ciganveliki@gmail.com","jova","Boris","Hadzic","Novi Sad","6611632",UserType.REGULAR);
			user2.setVerified(true);
			userservice.save(user2);
			
			User user3 = new User("dzenanlo@gmail.com","jova","Dino","Pokas","Novi Sad","6611632",UserType.REGULAR);
			user3.setVerified(true);
			userservice.save(user3);
			
			User user4 = new User("cigann95@gmail.com","jova","Darko","Kirin","Novi Sad","6611632",UserType.REGULAR);
			user4.setVerified(true);
			userservice.save(user4);
						
			CulturalVenue cv1 = new CulturalVenue(CulturalVenueType.CINEMA,"Arena Cineplex","Novi Sad","dobar","2.4");
			cvservice.save(cv1);
			
			CulturalVenue cv2 = new CulturalVenue(CulturalVenueType.CINEMA,"Cinestar","Novi Sad, Big","skup","4.2");
			cvservice.save(cv2);
			
			CulturalVenue cv3 = new CulturalVenue(CulturalVenueType.THEATER,"Srpsko narodno pozoriste","Novi Sad","pravi","1.5");
			cvservice.save(cv3);
			
			CulturalVenue cv4 = new CulturalVenue(CulturalVenueType.THEATER,"Pozoriste NN","Nis","daleko","2.4");
			cvservice.save(cv4);
			
			Event e1 = new Event(EventType.MOVIE,"Kad porastem bicu kengur","Sergej Trifunovic,Nebojsa Glogovac itd",
					EventGenre.COMEDY,"direktor","93 min","poster",5,"extra",cv1);
			Hibernate.initialize(e1.getProjections());
			
			eventservice.save(e1);
			
			Event e2 = new Event(EventType.PLAY,"Seviljski Berberin","Glumac 1, Glumac 2...",
					EventGenre.OPERA,"direktor","138 min","poster",5,"extra",cv3);
			eventservice.save(e2);
			
			Event e3 = new Event(EventType.MOVIE,"Zoolander","Ben Stiller, Owen Wilson",
					EventGenre.COMEDY,"direktor","98 min","poster",5,"extra",cv1);
			eventservice.save(e3);
			
			Event e4 = new Event(EventType.PLAY,"Travijata","Glumac 1, Glumac 2",
					EventGenre.COMEDY,"Djuzepe Verdi","144 min","poster",5,"extra",cv3);
			eventservice.save(e4);
			
			
			//add to culturalvenue
			Hibernate.initialize(cv1.getEvents());
			Hibernate.initialize(cv3.getEvents());
			
			cv1.getEvents().add(e1);
			cv1.getEvents().add(e3);
			cv3.getEvents().add(e2);
			cv3.getEvents().add(e4);
			
			
			//halls
			Hall h1 = new Hall(1,8,8,cv1);
			hallservice.save(h1);
			Hibernate.initialize(h1.getSeats());
			for(int i=1 ; i<=h1.getRows();i++){
				for(int j=1;j<=h1.getSeatsPerRow();j++){
					Seat seat = new Seat(i,j,h1);
					seatservice.save(seat);
					h1.getSeats().add(seat);
				}
			}
			hallservice.save(h1);
			Hibernate.initialize(cv1.getHalls());
			cv1.getHalls().add(h1);
			
			Hall h2 = new Hall(2,8,10,cv1);
			hallservice.save(h2);
			Hibernate.initialize(h2.getSeats());
			for(int i=1 ; i<=h2.getRows();i++){
				for(int j=1;j<=h2.getSeatsPerRow();j++){
					Seat seat = new Seat(i,j,h2);
					seatservice.save(seat);
					h2.getSeats().add(seat);
				}
			}
			hallservice.save(h2);
			Hibernate.initialize(cv1.getHalls());
			cv1.getHalls().add(h2);
			cvservice.save(cv1);
			
			Hall h3 = new Hall(1,10,12,cv3);
			hallservice.save(h3);
			Hibernate.initialize(h3.getSeats());
			for(int i=1 ; i<=h3.getRows();i++){
				for(int j=1;j<=h3.getSeatsPerRow();j++){
					Seat seat = new Seat(i,j,h3);
					seatservice.save(seat);
					h3.getSeats().add(seat);
				}
			}
			hallservice.save(h3);
			Hibernate.initialize(cv3.getHalls());
			cv3.getHalls().add(h3);
			cvservice.save(cv3);
			
			Hall h4 = new Hall(1,10,10,cv4);
			hallservice.save(h4);
			Hibernate.initialize(h4.getSeats());
			for(int i=1 ; i<=h4.getRows();i++){
				for(int j=1;j<=h4.getSeatsPerRow();j++){
					Seat seat = new Seat(i,j,h4);
					seatservice.save(seat);
					h4.getSeats().add(seat);
				}
			}
			hallservice.save(h4);
			Hibernate.initialize(cv4.getHalls());
			cv1.getHalls().add(h4);
			cvservice.save(cv4);
			
			 //event projections
			EventProjection ep1 = new EventProjection(e1,new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-27"));
			EventProjection ep2 = new EventProjection(e1,new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-17"));
			
			EventProjection ep3 = new EventProjection(e2,new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-11"));
			EventProjection ep4 = new EventProjection(e2,new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-15"));
			
			epservice.save(ep1);
			epservice.save(ep2);
			epservice.save(ep3);
			epservice.save(ep4);
			
			Hibernate.initialize(e1.getProjections());
			Hibernate.initialize(e2.getProjections());
			
			e1.getProjections().add(ep1);
			e1.getProjections().add(ep2);
			
			e2.getProjections().add(ep3);
			e2.getProjections().add(ep4);
			
			eventservice.save(e1);
			eventservice.save(e2);
			
			//projectionTimes
			ProjectionTime pt1 = new ProjectionTime(ep1,h1,new SimpleDateFormat("HH:mm").parse("18:00"),200);
			ProjectionTime pt2 = new ProjectionTime(ep1,h1,new SimpleDateFormat("HH:mm").parse("21:00"),350);
			ProjectionTime pt3 = new ProjectionTime(ep1,h2,new SimpleDateFormat("HH:mm").parse("18:00"),200);
			// dodato za testiranje cancela
			ProjectionTime pt4 = new ProjectionTime(ep2,h2,new SimpleDateFormat("HH:mm").parse("13:20"),500);
			
			ptservice.save(pt1);
			ptservice.save(pt2);
			ptservice.save(pt3);
			ptservice.save(pt4);
			
			Hibernate.initialize(ep1.getProjectionTimes());
			ep1.getProjectionTimes().add(pt1);
			ep1.getProjectionTimes().add(pt2);
			ep1.getProjectionTimes().add(pt3);
			
			Hibernate.initialize(ep2.getProjectionTimes());
			ep2.getProjectionTimes().add(pt4);
			
			epservice.save(ep1);
			epservice.save(ep2);
			
			//------------------Nebojsa-------------------
			
			User user5 = new User("nijacic95@gmail.com","sone","Nebojsa","Ijacic","Novi Sad","62311632",UserType.FANZONEADMIN);
			user5.setVerified(true);
			userservice.save(user5);
			
			User user6 = new User("neznam1230@gmail.com","sone","Kosta","Kostic","Novi Sad","12311632",UserType.SYSTEMADMIN);
			user6.setVerified(true);
			userservice.save(user6);
			
			User user7 = new User("neznam0321@gmail.com","sone","Marko","Markovic","Novi Sad","154541632",UserType.FANZONEADMIN);
			user7.setVerified(true);
			userservice.save(user7);
			
			ThematicProps prop1 = new ThematicProps(cv1.getId(),ThematicPropsType.NEW,user5.getId(),"NO","Prop1","Description1","2016-01-01T01:01","cinema.jpg",true);
			ThematicProps prop2 = new ThematicProps(cv1.getId(),ThematicPropsType.USED,user6.getId(),"NO","Prop2","Description2","2016-01-01T01:01","loginicon.png",true);
			ThematicProps prop3 = new ThematicProps(cv1.getId(),ThematicPropsType.USED,user1.getId(),"NO","Prop3","Description3","2016-01-01T01:01","remove.png",true);
			ThematicProps prop4 = new ThematicProps(cv2.getId(),ThematicPropsType.NEW,user5.getId(),"NO","Prop4","Description4","2016-01-01T01:01","",true);
			ThematicProps prop5 = new ThematicProps(cv3.getId(),ThematicPropsType.USED,user5.getId(),"NO","Prop5","Description5","2016-01-01T01:01","",true);
			
			thematicPropsService.save(prop1);
			thematicPropsService.save(prop2);
			thematicPropsService.save(prop3);
			thematicPropsService.save(prop4);
			thematicPropsService.save(prop5);
			
			Offer offer1 = new Offer(user1.getId(),200,prop1.getId(),cv1.getId(),false,false);
			Offer offer2 = new Offer(user6.getId(),210,prop1.getId(),cv1.getId(),false,false);
			Offer offer3 = new Offer(user5.getId(),1000,prop2.getId(),cv1.getId(),false,false);
			Offer offer4 = new Offer(user1.getId(),800,prop2.getId(),cv1.getId(),false,false);
			Offer offer5 = new Offer(user6.getId(),760,prop3.getId(),cv1.getId(),false,false);
			Offer offer6 = new Offer(user5.getId(),750,prop3.getId(),cv1.getId(),false,false);
			Offer offer7 = new Offer(user1.getId(),340,prop5.getId(),cv3.getId(),false,false);
			
			offerService.save(offer1);
			offerService.save(offer2);
			offerService.save(offer3);
			offerService.save(offer4);
			offerService.save(offer5);
			offerService.save(offer6);
			offerService.save(offer7);
		}
	}
	
}
