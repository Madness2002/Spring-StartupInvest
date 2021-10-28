package pe.edu.upc.SpringStartupInvest.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

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
																				// registro
	/*
	 * @Query("") List<Startup> findByPopular()throws Exception; // Buscar Startups
	 * mas populares FALTA EL TOP
	 */
}
