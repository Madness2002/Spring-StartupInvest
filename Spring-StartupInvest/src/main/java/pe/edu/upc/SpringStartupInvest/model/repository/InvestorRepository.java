package pe.edu.upc.SpringStartupInvest.model.repository;


import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.Investor;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer> {
	
}
