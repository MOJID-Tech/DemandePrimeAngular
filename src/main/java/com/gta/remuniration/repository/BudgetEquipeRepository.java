package com.gta.remuniration.repository;
import com.gta.remuniration.entity.BudgetEquipe;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BudgetEquipeRepository extends JpaRepository<BudgetEquipe,Integer> , JpaSpecificationExecutor<BudgetEquipe> {

    boolean existsById(Long id);

    Optional<BudgetEquipe> findById(Integer id);

    @Query(value = "SELECT id FROM `budget_equipe` WHERE  :datedebut <= date_debut  and  datefin is null and equipe_id= :id"
            ,nativeQuery = true)
    Integer findActiveBudgetEquipe(@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)@Param("datedebut")   Date date1  , @Param("id") Long idequipe);

    //Optional<Salarie> findByEmailAndPhoneNumber(String email, String phoneNumber);
    Page<BudgetEquipe> findAll(Pageable pageable);

    //BudgetEquipe findByDatefinAndEquipeId(Date date_fin , Long  EquipeID);
}
