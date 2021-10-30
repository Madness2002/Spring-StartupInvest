package pe.edu.upc.SpringStartupInvest.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.var;
import pe.edu.upc.SpringStartupInvest.model.entity.Startup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface StartupRepository extends JpaRepository<Startup, Integer> {
	List<Startup> findByName(String name) throws Exception;

	@Query("select a from Startup a where a.state=:state")
	List<Startup> findByState(Boolean state) throws Exception;

	@Query("select a from Startup a where datediff(currentDate,a.registerDate)<=7")
	List<Startup> findByDateRecently() throws Exception; // Startups registradas recientemente (una semana antes)

	@Query("select a from Startup a where a.registerDate between :date1 and :date2")
	List<Startup> findByDateBetween(Date date1, Date date2) throws Exception; // Filtrar startups entre fechas de

	@Query(value = "select p.* from prueba p", nativeQuery = true)
	List<Startup> listStartupsWithTopAndAmount() throws Exception;

	@Query(value = "select p.amounth from viewStartupPositionAmounth p where p.startup_id=?1", nativeQuery = true)
	double getAmounthInvestedById(int id) throws Exception;

	@Query(value = "select p.position from viewStartupPositionAmounth p where p.startup_id=?1", nativeQuery = true)
	Integer getPositionStartupById(int id) throws Exception;

	// registro
	/*
	 * @Query("") List<Startup> findByPopular()throws Exception; // Buscar Startups
	 * mas populares FALTA EL TOP
	 */
}
