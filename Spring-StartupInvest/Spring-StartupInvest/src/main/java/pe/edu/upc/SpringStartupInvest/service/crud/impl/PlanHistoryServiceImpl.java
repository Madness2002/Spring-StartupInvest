package pe.edu.upc.SpringStartupInvest.service.crud.impl;



import pe.edu.upc.SpringStartupInvest.model.entity.PlanHistory;
import pe.edu.upc.SpringStartupInvest.model.repository.PlanHistoryRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.PlanHistoryService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanHistoryServiceImpl implements PlanHistoryService {

	@Autowired
	private PlanHistoryRepository planHistoryRepository;

	@Override
	public JpaRepository<PlanHistory, Integer> getJpaRepository() {
		return planHistoryRepository;
	}

	@Override
	public Date findLastDateOfPlanValidByStartupId(Integer id) throws Exception {
		
		return planHistoryRepository.findLastDateOfPlanValidByStartupId(id);
	}

	@Override
	public Date findActivePlanValidByStartupId(Integer id) throws Exception {
		
		return planHistoryRepository.findActivePlanValidByStartupId(id);
	}


}
