package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.SpringStartupInvest.model.entity.Startup;

public interface StartupService extends CrudService<Startup, Integer> {
	List<Startup> findByName(String name) throws Exception;

	@Transactional(readOnly = true)
	List<Startup> findByState(Boolean state) throws Exception;

	@Transactional(readOnly = true)
	List<Startup> findByDateRecently() throws Exception; // Startups registradas recientemente

	@Transactional(readOnly = true)
	List<Startup> findByDateBetween(Date date1, Date date2) throws Exception; // Filtrar startups entre fechas de

	@Transactional(readOnly = true)
	List<Startup> listStartupsMostPopular() throws Exception;
	// registro

	double getAmounthInvestedById(int id) throws Exception;

	Integer getPositionStartupById(int id) throws Exception;

	// List<Startup> findByPopular() throws Exception; // Buscar Startups mas
	// populares FALTA EL TOP

}
