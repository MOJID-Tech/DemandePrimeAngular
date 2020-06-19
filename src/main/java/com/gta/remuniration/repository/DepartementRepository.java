package com.gta.remuniration.repository;

import com.gta.remuniration.entity.Departement;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.entity.Salarie;
import com.gta.remuniration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Integer>, JpaSpecificationExecutor<Departement>
{

/*
    @Query(value = "select distinct s.* from departement s inner join equipe u on u.departement_id = s.id WHERE s.id = :departement_id "
            ,nativeQuery = true)
    List<Departement> findByDepartement(@Param("departement_id") Integer departement_id);
*/




}


