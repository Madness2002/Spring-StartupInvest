package pe.edu.upc.SpringStartupInvest.model.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.Startup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface StartupRepository extends JpaRepository<Startup, Integer> {
	List<Startup> findByName(String name) throws Exception;

	@Query("select a from Startup a where a.state=:state")
	List<Startup> findByState(Boolean state) throws Exception;

	@Query(value = "select * from startups  where (current_date - startup_register_date)<=60", nativeQuery = true)
	List<Startup> findByDateRecently() throws Exception; // Startups registradas recientemente (2 meses antes)

	@Query("select a from Startup a where a.registerDate between ?1 and ?2")
	List<Startup> findByDateBetween(Date date1, Date date2) throws Exception; // Filtrar startups entre fechas de

	@Query(value = "select p.startup_id,p.startup_description,p.startup_email,p.startup_image,p.startup_name,p.startup_register_date,p.startup_ruc,p.startup_state,p.category_id\r\n"
			+ ", p.user_id from viewstartuppositionamounth p order by position limit 5", nativeQuery = true)
	List<Startup> listStartupsMostPopular() throws Exception;

	@Query(value = "select p.amounth from viewStartupPositionAmounth p where p.startup_id=?1", nativeQuery = true)
	double getAmounthInvestedById(int id) throws Exception;

	@Query(value = "select p.position from viewStartupPositionAmounth p where p.startup_id=?1", nativeQuery = true)
	Integer getPositionStartupById(int id) throws Exception;

	@Query(value = "select*from startups where upper(startup_name) like upper(CONCAT('%',?1,'%'))", nativeQuery = true)
	List<Startup> findByNameStartup(String name);

	@Query(value = "select s.startup_id,s.startup_description,s.startup_email,s.startup_image,s.startup_name,s.startup_register_date,s.startup_ruc,s.startup_state,s.category_id\r\n"
			+ "	, s.user_id from startups s join plans_history ph on  s.startup_id= ph.startup_id\r\n"
			+ "	 where current_date between ph.plan_history_acquisition_date and ph.plan_history_expiration_date", nativeQuery = true)
	List<Startup> findStartupsByActivePlan()throws Exception;

	@Query(value="select *from startups where startup_id=?1",nativeQuery=true)
	Optional<Startup> findById(Integer id);
	
	
	
	
}
