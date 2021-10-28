package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.List;

import pe.edu.upc.SpringStartupInvest.model.entity.TypeInvestment;


public interface TypeInvestmentService extends CrudService<TypeInvestment, Integer> {
	List<TypeInvestment> findByName(String name) throws Exception;
}
