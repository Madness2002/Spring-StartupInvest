package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.List;

import pe.edu.upc.SpringStartupInvest.model.entity.Plan;


public interface PlanService extends CrudService<Plan, Integer> {
	List<Plan> findByName(String name) throws Exception;
	List<Plan>findByState (Boolean state)throws Exception ;
}
