package pe.edu.upc.SpringStartupInvest.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.SpringStartupInvest.model.entity.TypeCardHistory;
import pe.edu.upc.SpringStartupInvest.model.repository.TypeCardHistoryRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeCardHistoryService;
@Service
public class TypeCardHistoryServiceImpl implements TypeCardHistoryService {
	@Autowired
	private TypeCardHistoryRepository typeCardHistoryRepository;

	@Override
	public JpaRepository<TypeCardHistory, Integer> getJpaRepository() {

		return typeCardHistoryRepository;
	}

}
