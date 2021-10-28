package pe.edu.upc.SpringStartupInvest.model.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category>findByName(String name) throws Exception;	
	
	@Query("select a from Category a where a.state=:state")
	List<Category>findByState (boolean state) throws Exception;
	
}
