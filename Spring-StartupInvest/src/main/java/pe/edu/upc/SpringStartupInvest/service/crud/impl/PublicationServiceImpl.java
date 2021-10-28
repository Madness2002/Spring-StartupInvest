package pe.edu.upc.SpringStartupInvest.service.crud.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upc.SpringStartupInvest.model.entity.Publication;
import pe.edu.upc.SpringStartupInvest.model.repository.PublicationRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.PublicationService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private PublicationRepository publicationRepository;

	@Override
	public JpaRepository<Publication, Integer> getJpaRepository() {
		return publicationRepository;
	}

	@Override
	public List<Publication> findByName(String name)throws Exception {
		
		return publicationRepository.findByName(name);
	}

	@Override
	public List<Publication> findByStartup(String startupName) throws Exception {
		
		return publicationRepository.findByStartup(startupName);
	}
	
}
