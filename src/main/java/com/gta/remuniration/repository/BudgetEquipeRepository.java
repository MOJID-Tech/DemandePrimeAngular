package com.gta.remuniration.repository;

import com.gta.remuniration.entity.BudgetEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BudgetEquipeRepository extends JpaRepository<BudgetEquipe, Integer>, JpaSpecificationExecutor<BudgetEquipe>
{

}

