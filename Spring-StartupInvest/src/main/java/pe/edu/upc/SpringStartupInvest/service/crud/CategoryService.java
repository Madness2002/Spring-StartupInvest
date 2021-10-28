package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.List;

import pe.edu.upc.SpringStartupInvest.model.entity.Category;



public interface CategoryService extends CrudService<Category, Integer> {
	
	List<Category>findByName(String name) throws Exception;	
	List<Category>findByState (boolean state) throws Exception;
	
}
