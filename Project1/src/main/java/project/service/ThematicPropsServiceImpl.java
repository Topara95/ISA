package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.ThematicProps;
import project.repository.ThematicPropsRepository;

@Service
@Transactional
public class ThematicPropsServiceImpl implements ThematicPropsService{
	
	@Autowired
	private ThematicPropsRepository thematicPropsRepository;

	@Override
	public ThematicProps save(ThematicProps thematicProps) {
		return thematicPropsRepository.save(thematicProps);
	}

	@Override
	public List<ThematicProps> findAll() {
		return thematicPropsRepository.findAll();
	}

	@Override
	public ThematicProps deleteThematicProps(Long id) {
		ThematicProps thematicProps = thematicPropsRepository.findById(id);
		thematicPropsRepository.delete(thematicProps);
		return thematicProps;
	}

	@Override
	public ThematicProps findById(Long id) {		
		return thematicPropsRepository.findById(id);
	}

}
