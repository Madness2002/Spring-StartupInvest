package pe.edu.upc.SpringStartupInvest.model.repository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface InvestorHistoryRepository extends JpaRepository<InvestorHistory, Integer> {

	@Query(value = "select ih.investor_history_id,ih.investor_history_amount,ih.investor_history_date,ih.investor_history_state,ih.investment_request_id,ih.investor_id,ih.type_card_id from investors i join investor_histories ih on i.investor_id= ih.investor_id\r\n"
			+ "join investment_requests ir on ir.investment_request_id= ih.investment_request_id \r\n"
			+ "join startups s on s.startup_id= ir.startup_id\r\n" + "where ih.investor_id=?1", nativeQuery = true)
	List<InvestorHistory> listPortafolioByInvestorId(Integer id);

}
