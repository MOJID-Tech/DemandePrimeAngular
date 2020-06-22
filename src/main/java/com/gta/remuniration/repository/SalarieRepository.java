package com.gta.remuniration.repository;

import com.gta.remuniration.entity.Salarie;
import com.gta.remuniration.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

public interface SalarieRepository  extends  JpaRepository<Salarie, Long>, JpaSpecificationExecutor<Salarie> {

    boolean existsById(Long id);

    Optional<Salarie> findById(Long id);
    Optional<Salarie> findByEmailsalarie(String email);


    Page<Salarie> findAll(Pageable pageable);


    @Query(value = "select distinct s.* from salarie s left join user u on s.id = u.salarie_id WHERE u.login = :login "
            ,nativeQuery = true)
    Optional<Salarie> findByLogin(@Param("login") String login);


}
