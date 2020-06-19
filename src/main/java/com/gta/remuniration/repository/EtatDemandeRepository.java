package com.gta.remuniration.repository;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.entity.EtatDemande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

public interface EtatDemandeRepository extends JpaRepository<EtatDemande,Integer>, JpaSpecificationExecutor<EtatDemande>{
    boolean existsById(Integer id);
    Page<EtatDemande> findAll(Pageable pageable);
    Optional<EtatDemande> findById(Integer id);
    @Query(value = "select distinct de.* from etat_demande de inner join demande d on de.demande_id = d.id WHERE d.id = :demande_id "
            ,nativeQuery = true)
    List<EtatDemande> findByDemande(@Param("demande_id") Long demande_id);

}
