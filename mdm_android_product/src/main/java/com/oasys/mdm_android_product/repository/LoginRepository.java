package com.oasys.mdm_android_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.oasys.mdm_android_product.entity.Login;

@Repository
@EnableJpaRepositories
public interface LoginRepository extends JpaRepository<Login, Integer> {

	Login findByUsername(String userName);

	Login findByEmail(String email);

	Login findFirstByUsername(String name);

//	Login findByLoginId(String id);

//	Optional<User> findByUsername(String username);

}
