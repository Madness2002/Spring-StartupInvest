package pe.edu.upc.SpringStartupInvest.model.repository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface InvestorHistoryRepository extends JpaRepository<InvestorHistory, Integer> {

	@Query(value = "select * from listInvestments",nativeQuery = true)
	List<InvestorHistory> listPortafolio();

}
