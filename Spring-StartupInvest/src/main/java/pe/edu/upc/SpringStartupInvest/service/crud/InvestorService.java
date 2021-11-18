package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.model.repository.InvestorRepository;

public interface InvestorService extends CrudService<Investor, Integer> {



	Optional<Investor> findById(Integer id);


Optional<Investor> findInvestorByInvestorHistoryId(Integer id);


}
