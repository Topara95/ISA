package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.Offer;
import project.domain.ThematicProps;
import project.dto.OfferDTO;
import project.dto.ThematicPropsDTO;
import project.service.OfferService;

@RestController
@RequestMapping("/api/offer")
public class OfferController {
	
	@Autowired
	private OfferService offerService;
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> save(@RequestBody Offer offer) {
		System.out.println("usao u kreiranje!");
		Offer offer1 = offerService.findByCreatedByAndPropId(offer.getCreatedBy(), offer.getPropId());
		//System.out.println("nasao sam " + offer1.getId());
		if(offer1!=null) {
			return new ResponseEntity<Offer>(offer1, HttpStatus.OK);
		}
		
		Offer offer2 = offerService.save(offer);
		System.out.println("sacuvao" + offer2.getId());
		return new ResponseEntity<Offer>(offer2, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> modify(@RequestBody Offer offer) {
		Offer offer1 = offerService.findByCreatedByAndPropId(offer.getCreatedBy(), offer.getPropId());
		offer1.setOfferedMoney(offer.getOfferedMoney());
		offer1 = offerService.save(offer1);
		return new ResponseEntity<Offer>(offer1, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/{propId}",
            method   = RequestMethod.GET
    )
    public ResponseEntity<List<OfferDTO>> getOffersForProp(@PathVariable Long propId) {
		List<OfferDTO> offerListDTO = new ArrayList<OfferDTO>();
		List<Offer> offerList = offerService.findByPropId(propId);
		for(Offer offer : offerList) {
			offerListDTO.add(new OfferDTO(offer));
		}
		return new ResponseEntity<List<OfferDTO>>(offerListDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<Offer> deleteOffer(@PathVariable Long id) {
		Offer offer = offerService.deleteOffer(id);	
		return new ResponseEntity<Offer>(offer,HttpStatus.OK);
	}
	
	@RequestMapping(value="/accept/{id}", method = RequestMethod.GET)
	public ResponseEntity<Offer> acceptOffer(@PathVariable Long id) {
		Offer offer = offerService.acceptOffer(id);
		return new ResponseEntity<Offer>(offer,HttpStatus.OK);
		
	}
}
