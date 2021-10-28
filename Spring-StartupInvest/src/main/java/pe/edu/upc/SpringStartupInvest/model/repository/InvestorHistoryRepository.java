package pe.edu.upc.SpringStartupInvest.model.repository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface InvestorHistoryRepository extends JpaRepository<InvestorHistory, Integer> {
		
}
