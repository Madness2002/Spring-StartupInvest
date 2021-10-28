package pe.edu.upc.SpringStartupInvest.model.repository;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.Publication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {
List<Publication> findByName (String name) throws Exception;

@Query(value="select p from publications p join startups s on p.startup_id=s.startup_id where s.startup_name like CONCAT('%',(:startupName),'%')",
		nativeQuery = true)
List<Publication> findByStartup (String startupName);
}
