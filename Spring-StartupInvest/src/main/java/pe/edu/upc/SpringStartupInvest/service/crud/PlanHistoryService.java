package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.Date;

import pe.edu.upc.SpringStartupInvest.model.entity.PlanHistory;




public interface PlanHistoryService extends CrudService<PlanHistory, Integer> {
	
	Date findLastDateOfPlanValidByStartupId(Integer id) throws Exception;
	Date findActivePlanValidByStartupId(Integer id) throws Exception;
}
