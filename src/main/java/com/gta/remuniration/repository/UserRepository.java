package com.gta.remuniration.repository;

import com.gta.remuniration.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{
    boolean existsById(Integer id);

    Optional<User> findById(Integer id);

    Optional<User> findByLogin(String id);


    //Optional<Salarie> findByEmailAndPhoneNumber(String email, String phoneNumber);
    Page<User> findAll(Pageable pageable);
 //   User user  findByRoleIdAndActive(USER, true);
}
