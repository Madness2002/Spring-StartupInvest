package pe.edu.upc.SpringStartupInvest.model.repository;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.TypeCard;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TypeCardRepository extends JpaRepository<TypeCard, Integer> {
List<TypeCard>findByname (String name) throws Exception;


}
