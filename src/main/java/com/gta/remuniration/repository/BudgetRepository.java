package com.gta.remuniration.repository;

import com.gta.remuniration.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BudgetRepository extends JpaRepository<Budget, Integer>, JpaSpecificationExecutor<Budget>
{

}

