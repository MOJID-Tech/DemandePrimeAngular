package com.gta.remuniration.repository;

import com.gta.remuniration.entity.user_role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface user_roleRepository extends  JpaRepository< user_role, Integer>, JpaSpecificationExecutor< user_role>  {

}
