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
List<Publication> findByStartupName (String startupName);

@Query(value="select p.publication_id,p.publication_date,p.publication_description,p.publication_image,p.publication_name,p.publication_url,p.startup_id\r\n"
		+ "from publications p join startups s on p.startup_id=s.startup_id where s.startup_id=?1",
nativeQuery = true)
List<Publication> findByStartupId (Integer id) throws Exception;
}
