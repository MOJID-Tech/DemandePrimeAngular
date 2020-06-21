package com.gta.remuniration.repository;
import com.gta.remuniration.entity.Equipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EquipeRepository extends JpaRepository<Equipe, Long >,JpaSpecificationExecutor<Equipe> {
    Page<Equipe> findAll(Pageable pageable);
    boolean existsById(Long id);
    Optional< Equipe> findById(Long id);



    @Query(value = "select distinct s.* from equipe s inner join departement u on s.departement_id = u.id WHERE u.id = :departement_id "
            ,nativeQuery = true)
    List<Equipe> findByDepartement(@Param("departement_id") Integer departement_id);

}
