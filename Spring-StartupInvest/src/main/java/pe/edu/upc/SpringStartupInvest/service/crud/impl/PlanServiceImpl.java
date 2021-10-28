package pe.edu.upc.SpringStartupInvest.service.crud.impl;

import java.util.List;

import pe.edu.upc.SpringStartupInvest.model.entity.Plan;
import pe.edu.upc.SpringStartupInvest.model.repository.PlanRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.PlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepository planRepository;

	@Override
	public JpaRepository<Plan, Integer> getJpaRepository() {
		return planRepository;
	}

	@Override
	public List<Plan> findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return planRepository.findByName(name);
	}


	
	
	
}
