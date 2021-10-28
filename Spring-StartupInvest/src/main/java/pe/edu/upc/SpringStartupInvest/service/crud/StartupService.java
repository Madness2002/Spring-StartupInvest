package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.Date;
import java.util.List;

import pe.edu.upc.SpringStartupInvest.model.entity.Startup;


public interface StartupService extends CrudService<Startup, Integer> {
	List<Startup> findByName(String name) throws Exception;

	List<Startup> findByState(Boolean state) throws Exception;

	List<Startup> findByDateRecently() throws Exception; // Startups registradas recientemente

	List<Startup> findByDateBetween(Date date1, Date date2) throws Exception; // Filtrar startups entre fechas de
																				// registro

	//List<Startup> findByPopular() throws Exception; // Buscar Startups mas populares FALTA EL TOP

}
