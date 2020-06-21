package com.gta.remuniration.repository;

import com.gta.remuniration.entity.BudgetDepartement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BudgetDepartementRepository extends JpaRepository<BudgetDepartement, Integer>, JpaSpecificationExecutor<BudgetDepartement>
{
    @Query(value = "select distinct s.* from budget_departement s inner join departement u on s.departement_id = u.id WHERE u.id = :departement_id"
            ,nativeQuery = true)
    Optional<BudgetDepartement> findByDepartement(@Param("departement_id") Long departement_id);
  // AND YEAR(date_debut)=YEAR(GETDATE())

}
