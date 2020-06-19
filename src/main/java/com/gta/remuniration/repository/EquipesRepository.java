package com.gta.remuniration.repository;

import com.gta.remuniration.entity.Departement;
import com.gta.remuniration.entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipesRepository  extends JpaRepository<Equipe, Integer>, JpaSpecificationExecutor<Equipe> {


    @Query(value = "select distinct s.* from equipe s inner join departement u on s.departement_id = u.id WHERE u.id = :departement_id "
            ,nativeQuery = true)
    List<Equipe> findByDepartement(@Param("departement_id") Integer departement_id);



}