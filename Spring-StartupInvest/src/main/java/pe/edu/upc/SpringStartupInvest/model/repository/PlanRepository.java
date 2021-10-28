package pe.edu.upc.SpringStartupInvest.model.repository;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.Plan;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
List<Plan>findByName (String name)throws Exception ;


}
