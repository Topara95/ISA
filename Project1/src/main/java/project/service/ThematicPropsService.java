package project.service;

import java.util.List;

import project.domain.ThematicProps;

public interface ThematicPropsService {

	ThematicProps save(ThematicProps thematicProps);
	
	List<ThematicProps> findAll();
	
	ThematicProps deleteThematicProps(Long id);
	
	ThematicProps findById(Long id);
	
	ThematicProps modifyThematicProps(ThematicProps thematicProps,Long id);
}
