package com.gta.remuniration.repository;
import com.gta.remuniration.entity.Etat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EtatRepository extends  JpaRepository< Etat, Integer>, JpaSpecificationExecutor< Etat>  {
    Page< Etat> findAll(Pageable pageable);
    boolean existsById(Integer id);
    Optional< Etat> findById(Integer id);
}
