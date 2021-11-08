package pe.edu.upc.SpringStartupInvest.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.TypeInvestment;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TypeInvestmentRepository extends JpaRepository<TypeInvestment, Integer> {

	List<TypeInvestment> findByName(String name) throws Exception;
}
