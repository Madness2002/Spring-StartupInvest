package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.List;

import pe.edu.upc.SpringStartupInvest.model.entity.TypeCard;

public interface TypeCardService extends CrudService<TypeCard, Integer> {
	List<TypeCard>findByname (String name)throws Exception;
}
