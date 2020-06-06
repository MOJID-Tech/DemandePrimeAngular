package com.gta.remuniration.repository;
import com.gta.remuniration.entity.EtatDemande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.Optional;

public interface EtatDemandeRepository extends JpaRepository<EtatDemande,Integer>, JpaSpecificationExecutor<EtatDemande>{
    boolean existsById(Integer id);
    Page<EtatDemande> findAll(Pageable pageable);
    Optional<EtatDemande> findById(Integer id);

}
