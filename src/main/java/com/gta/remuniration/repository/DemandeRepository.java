package com.gta.remuniration.repository;
import com.gta.remuniration.entity.Demande;
import com.gta.remuniration.entity.EtatDemande;
import com.gta.remuniration.entity.Salarie;
import com.gta.remuniration.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface DemandeRepository extends JpaRepository<Demande, Integer>, JpaSpecificationExecutor<Demande>{


    boolean existsById(Integer id);
   /// Page<Demande> findAll(Pageable pageable);
    Optional<Demande> findById(Integer id);
    List<Demande> findByValideM(boolean valide_manager);

    @Query(value = "select distinct d.* from  demande as d WHERE   (cast( :d.date_fin as timestamp ) is not null) "
            ,nativeQuery = true)
    List<Demande> findDemandes();

   // List<Demande> findByDate_finIsNotNull();




}
