package com.gta.remuniration.repository;
import com.gta.remuniration.entity.Appartient;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.entity.Salarie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppartientRepository extends JpaRepository<Appartient,Long>,JpaSpecificationExecutor<Appartient>{
    Page<Appartient> findAll(Pageable pageable);
    boolean existsById(Integer id);
    @Query(value = " SELECT equipe_id FROM `appartient` WHERE salarie_id= :id and(( :datedebut >= datedebut and :datefin <= datefin) or(:datedebut >= datedebut and datefin is null ))"


            ,nativeQuery = true)
    List<Long> findactiveEquipe(@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)@Param("datedebut")   Date date1 ,@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) @Param("datefin")  Date date2 , @Param("id") Long idsalarie);


    //boolean isEstrespo(Salarie salarie , Equipe equipe);
    Optional< Appartient> findByEstrespoAndSalarieIdAndEquipeId(boolean Estrespo , Long SalarieId , Long EquipeId);

    @Query(value = "select distinct a.* from appartient a inner join salarie s on a.salarie_id = s.id WHERE a.equipe_id = :equipe_id AND a.estrespo=0"

            ,nativeQuery = true)
    List<Appartient> findsalaries(@Param("equipe_id") Long equipe_id);

    @Query(value = "select distinct a.* from appartient a inner join salarie s on a.salarie_id = s.id WHERE a.equipe_id = :equipe_id AND a.estrespo=1 "
            ,nativeQuery = true)
    List<Appartient> findManager(@Param("equipe_id") Long equipe_id);



}
