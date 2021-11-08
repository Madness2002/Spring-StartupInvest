package pe.edu.upc.SpringStartupInvest.model.repository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.TypeCardHistory;


import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TypeCardHistoryRepository extends JpaRepository<TypeCardHistory, Integer> {

}
