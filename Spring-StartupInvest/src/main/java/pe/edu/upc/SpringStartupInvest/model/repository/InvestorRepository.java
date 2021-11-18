package pe.edu.upc.SpringStartupInvest.model.repository;


import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.Investor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer> {
	
	@Query(value="select *from investors where investor_id=?1",nativeQuery=true)
	Optional<Investor> findById(Integer id);
	
	@Query(value="select distinct i.investor_id,i.investor_dni,i.investor_email,i.investor_image,i.investor_lastname,i.investor_name,i.investor_state,i.user_id \r\n"
			+ "from investors i join Investor_histories ih on ih.investor_id= i.investor_id where ih.investor_history_id=?1",nativeQuery=true)
	Optional<Investor> findInvestorByInvestorHistoryId(Integer id);
}
