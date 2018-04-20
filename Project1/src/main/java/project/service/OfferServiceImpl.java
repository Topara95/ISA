package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.Offer;
import project.domain.ThematicProps;
import project.domain.User;
import project.repository.OfferRepository;
import project.repository.ThematicPropsRepository;
import project.repository.UserRepository;

@Service
@Transactional
public class OfferServiceImpl implements OfferService{
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ThematicPropsRepository thematicPropsRepository;

	@Override
	public Offer save(Offer offer) {
		return offerRepository.save(offer);
	}

	@Override
	public List<Offer> findAll() {
		return offerRepository.findAll();
	}

	@Override
	public Offer findById(Long id) {
		return offerRepository.findById(id);
	}

	@Override
	public Offer deleteOffer(Long id) {
		Offer offer = offerRepository.findById(id);
		offerRepository.delete(offer);
		return offer;
	}

	@Override
	public List<Offer> findByPropId(Long propId) {
		return offerRepository.findByPropId(propId);
	}

	@Override
	public Offer findByCreatedByAndPropId(Long createdBy, Long propId) {
		return offerRepository.findByCreatedByAndPropId(createdBy, propId);
	}

	@Override
	public Offer acceptOffer(Long id) {

		Offer offer = offerRepository.findById(id);
		ThematicProps prop = thematicPropsRepository.findById(offer.getPropId());
		User user = userRepository.findById(offer.getCreatedBy());
		prop.setReserved("YES");
		offer.setApproved(true);
		System.out.println("nasao pravog!");
		

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Informacija o ponudi");
		mail.setText("Pozdrav " + user.getName() + ", vasa ponuda za rekvizit "+prop.getName()+" je prihvacena. Cestitamo!");
		javaMailSender.send(mail);
		
		List<Offer> others =  offerRepository.findByIdNot(id);
		for(int i=0;i<others.size();i++) {		
			if(others.get(i).getPropId()==offer.getPropId()) {
				User user1 = userRepository.findById(others.get(i).getCreatedBy());
				SimpleMailMessage mail1 = new SimpleMailMessage();
				mail1.setTo(user1.getEmail());
				mail1.setFrom(env.getProperty("spring.mail.username"));
				mail1.setSubject("Informacija o ponudi");
				mail1.setText("Pozdrav " + user1.getName() + ", vasa ponuda za rekvizit "+prop.getName()+" je odbijena.");
				javaMailSender.send(mail1);
				//System.out.println("brisem "+others.get(i).getId());
				offerRepository.delete(others.get(i));
			}
		}
		thematicPropsRepository.save(prop);
		offerRepository.save(offer);
		return offer;
	}

	@Override
	public List<Offer> findByIdNot(Long id) {
		return offerRepository.findByIdNot(id);
	}

}
