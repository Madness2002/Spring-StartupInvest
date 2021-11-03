package pe.edu.upc.SpringStartupInvest.model.repository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestmentRequest;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface InvestmentRequestRepository extends JpaRepository<InvestmentRequest, Integer> {

	@Query(value = "select p.investment_request_id,p.investment_request_amount,p.investment_request_creation_date,p.investment_request_description,\r\n"
			+ "p.investment_request_expiration_date,p.investment_request_return_date,p.investment_request_state,p.startup_id,p.type_investment_id\r\n"
			+ "from viewInversionStatus p where p.startup_id=?1", nativeQuery = true)
	List<InvestmentRequest> findInvestmentRequestByStartupId(Integer startupId);

	@Query(value = "select p.amounth from viewInversionStatus p where p.startup_id=?1", nativeQuery = true)
	double getAmountColectedByStartupId(Integer id) throws Exception;

	@Query(value = "select p.amounth\r\n"
			+ "from viewInversionStatus p where p.investment_request_id=?1", nativeQuery = true)
	double getAmountColectedByInvestmentRequestId(Integer id) throws Exception;
	
}
