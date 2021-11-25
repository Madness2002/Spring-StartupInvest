package pe.edu.upc.SpringStartupInvest.model.repository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.PlanHistory;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PlanHistoryRepository extends JpaRepository<PlanHistory, Integer> {

	@Query(value="select  ph.plan_history_expiration_date  from  startups s join plans_history ph on  s.startup_id= ph.startup_id\r\n"
			+ "where s.startup_id=?1 and current_date between ph.plan_history_acquisition_date and  ph.plan_history_expiration_date\r\n"
			+ "order by 1 desc limit 1", nativeQuery = true)
	Date findActivePlanValidByStartupId(Integer id) throws Exception;

	
	@Query(value="select  ph.plan_history_expiration_date  from  startups s join plans_history ph on  s.startup_id= ph.startup_id\r\n"
			+ "where s.startup_id=?1 \r\n"
			+ "order by 1 desc limit 1", nativeQuery = true)
	Date findLastDateOfPlanValidByStartupId(Integer id) throws Exception;
}
