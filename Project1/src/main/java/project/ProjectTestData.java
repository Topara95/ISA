package project;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.domain.CulturalVenue;
import project.domain.CulturalVenueType;
import project.domain.Event;
import project.domain.EventGenre;
import project.domain.EventType;
import project.domain.Hall;
import project.domain.User;
import project.domain.UserType;
import project.service.CulturalVenueService;
import project.service.EventService;
import project.service.HallService;
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
	
	@PostConstruct
	private void init(){
		
		User user1 = new User("jovantopolic@gmail.com","jova","Jovan","Topolic","Novi Sad","6611632",UserType.REGULAR);
		user1.setVerified(true);
		userservice.save(user1);
		
		User user2 = new User("ciganveliki@gmail.com","jova","Boris","Hadzic","Novi Sad","6611632",UserType.REGULAR);
		user2.setVerified(true);
		userservice.save(user2);
		
		CulturalVenue cv1 = new CulturalVenue(CulturalVenueType.CINEMA,"Arena Cineplex","Novi Sad","dobar");
		cvservice.save(cv1);
		
		CulturalVenue cv2 = new CulturalVenue(CulturalVenueType.CINEMA,"Cinestar","Novi Sad, Big","skup");
		cvservice.save(cv2);
		
		CulturalVenue cv3 = new CulturalVenue(CulturalVenueType.THEATER,"Srpsko narodno pozoriste","Novi Sad","pravi");
		cvservice.save(cv3);
		
		CulturalVenue cv4 = new CulturalVenue(CulturalVenueType.THEATER,"Pozoriste NN","Nis","daleko");
		cvservice.save(cv4);
		
		Event e1 = new Event(EventType.MOVIE,"Kad porastem bicu kengur","Sergej Trifunovic,Nebojsa Glogovac itd",
				EventGenre.COMEDY,"direktor","93 min","poster",5,"extra",200);
		eventservice.save(e1);
		
		Event e2 = new Event(EventType.PLAY,"Seviljski Berberin","Glumac 1, Glumac 2...",
				EventGenre.OPERA,"direktor","138 min","poster",5,"extra",400);
		eventservice.save(e2);
		
		Event e3 = new Event(EventType.MOVIE,"Zoolander","Ben Stiller, Owen Wilson",
				EventGenre.COMEDY,"direktor","98 min","poster",5,"extra",250);
		eventservice.save(e3);
		
		Event e4 = new Event(EventType.PLAY,"Travijata","Glumac 1, Glumac 2",
				EventGenre.COMEDY,"Djuzepe Verdi","144 min","poster",5,"extra",500);
		eventservice.save(e4);
		
		/*Hall h1 = new Hall(1,10,10,cv1);
		hallservice.save(h1);
		
		Hall h2 = new Hall(2,20,15,cv1);
		hallservice.save(h2);
		
		Hall h3 = new Hall(1,25,12,cv3);
		hallservice.save(h3);
		
		Hall h4 = new Hall(1,10,10,cv4);
		hallservice.save(h4);*/
	}
	
}
