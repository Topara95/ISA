package project.service;

import java.util.List;

import project.domain.ThematicProps;

public interface ThematicPropsService {

	ThematicProps save(ThematicProps thematicProps);
	
	List<ThematicProps> findAll();
}
