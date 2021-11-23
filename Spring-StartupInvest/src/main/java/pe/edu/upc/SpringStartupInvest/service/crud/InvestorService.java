package pe.edu.upc.SpringStartupInvest.service.crud;


import java.util.Optional;



import pe.edu.upc.SpringStartupInvest.model.entity.Investor;


public interface InvestorService extends CrudService<Investor, Integer> {



	Optional<Investor> findById(Integer id);


Optional<Investor> findInvestorByInvestorHistoryId(Integer id);

Integer getTotalTimesInvestedByInvestorId(Integer id);
double getAmountTotalInvestedByInvestorId (Integer id);
Optional<Investor> findByName(String name);



double getAmountTotalToReclaimByInvestorId(Integer id);

double getAmountTotalToReclaimedByInvestorId(Integer id);
}
