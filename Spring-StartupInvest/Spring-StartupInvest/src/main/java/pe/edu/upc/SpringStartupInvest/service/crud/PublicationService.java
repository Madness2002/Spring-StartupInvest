package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.List;

import pe.edu.upc.SpringStartupInvest.model.entity.Publication;


public interface PublicationService extends CrudService<Publication, Integer> {
	List<Publication> findByName (String name) throws Exception;
	List<Publication> findByStartup (String startupName) throws Exception;
}
