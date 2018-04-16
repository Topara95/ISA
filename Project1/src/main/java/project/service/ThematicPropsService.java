package project.service;

import java.util.List;

import project.domain.ThematicProps;
import project.domain.ThematicPropsType;

public interface ThematicPropsService {

	ThematicProps save(ThematicProps thematicProps);
	
	List<ThematicProps> findAll();
	
	ThematicProps deleteThematicProps(Long id);
	
	ThematicProps findById(Long id);
	
	ThematicProps modifyThematicProps(ThematicProps thematicProps,Long id);
	
	List<ThematicProps> findByCulturalVenueId(Long culturalVenueId);
	
	List<ThematicProps> findByCulturalVenueIdAndTptype(Long culturalVenueId,ThematicPropsType tptype);
	
	List<ThematicProps> findByCulturalVenueIdAndTptypeAndCreatedBy(Long culturalVenueId,ThematicPropsType tptype,Long createdBy);
	
	List<ThematicProps> findByCulturalVenueIdAndTptypeAndCreatedByNot(Long culturalVenueId,ThematicPropsType tptype,Long createdBy);
}
