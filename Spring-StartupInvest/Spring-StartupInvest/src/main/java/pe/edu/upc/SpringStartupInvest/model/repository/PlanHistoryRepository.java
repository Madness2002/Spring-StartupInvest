package pe.edu.upc.SpringStartupInvest.model.repository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.PlanHistory;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PlanHistoryRepository extends JpaRepository<PlanHistory, Integer> {

}
