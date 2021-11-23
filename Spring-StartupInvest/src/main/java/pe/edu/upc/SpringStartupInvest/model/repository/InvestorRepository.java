package pe.edu.upc.SpringStartupInvest.model.repository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer> {

	@Query(value = "select *from investors where investor_id=?1", nativeQuery = true)
	Optional<Investor> findById(Integer id);

	@Query(value = "select distinct i.investor_id,i.investor_dni,i.investor_email,i.investor_image,i.investor_lastname,i.investor_name,i.investor_state,i.user_id \r\n"
			+ "from investors i join Investor_histories ih on ih.investor_id= i.investor_id where ih.investor_history_id=?1", nativeQuery = true)
	Optional<Investor> findInvestorByInvestorHistoryId(Integer id);

	@Query(value = "select sum(ih.investor_history_amount) as amounthInvested  from investor_histories ih where ih.investor_id=?1 \r\n"
			+ "group by ih.investor_id ", nativeQuery = true)
	double getAmountTotalInvestedByInvestorId(Integer id);

	@Query(value = "select count(ih.investor_id) as totalTimesInvested  from investor_histories ih where ih.investor_id=?1 \r\n"
			+ "group by ih.investor_id", nativeQuery = true)
	Integer getTotalTimesInvestedByInvestorId(Integer id);

	@Query(value = "select *from investors where investor_name=?1", nativeQuery = true)
	Optional<Investor> findByName(String name);

	

	@Query(value = "select CASE  WHEN sum(a.investor_history_amount) IS NULL THEN 0\r\n"
			+ " ELSE sum(a.investor_history_amount)\r\n"
			+ " END AS \"amountToReclaim\"\r\n"
			+ " from \r\n"
			+ "(select ih.investor_history_id,ih.investor_history_amount,ih.investor_history_date,ih.investor_history_state,ih.investment_request_id,ih.investor_id,ih.type_card_id \r\n"
			+ "from investor_histories ih join investment_requests ir on ih.investment_request_id= ir.investment_request_id\r\n"
			+ "where ir.investment_request_return_date<current_date and ih.investor_id=?1 and ih.investor_history_state=true) as a", nativeQuery = true)
	double getAmountTotalToReclaimByInvestorId(Integer id);
	
	@Query(value = "select CASE  WHEN sum(a.investor_history_amount) IS NULL THEN 0\r\n"
			+ " ELSE sum(a.investor_history_amount) END AS \"amountToReclaimed\"\r\n"
			+ " from \r\n"
			+ "(select ih.investor_history_id,ih.investor_history_amount,ih.investor_history_date,ih.investor_history_state,ih.investment_request_id,ih.investor_id,ih.type_card_id \r\n"
			+ "from investor_histories ih join investment_requests ir on ih.investment_request_id= ir.investment_request_id\r\n"
			+ "where ir.investment_request_return_date<current_date and ih.investor_id=?1 and ih.investor_history_state=false) as a", nativeQuery = true)
	double getAmountTotalToReclaimedByInvestorId(Integer id);

}
