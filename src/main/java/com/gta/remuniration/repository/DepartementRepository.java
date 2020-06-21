package com.gta.remuniration.repository;

import com.gta.remuniration.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartementRepository extends JpaRepository<Departement, Integer>, JpaSpecificationExecutor<Departement>
{

/*
    @Query(value = "select distinct s.* from departement s inner join equipe u on u.departement_id = s.id WHERE s.id = :departement_id "
            ,nativeQuery = true)
    List<Departement> findByDepartement(@Param("departement_id") Integer departement_id);
*/




}


