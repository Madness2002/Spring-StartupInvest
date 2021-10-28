package pe.edu.upc.SpringStartupInvest.model.repository;


import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestmentRequest;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface InvestmentRequestRepository extends JpaRepository<InvestmentRequest, Integer> {
	
	
	
}
