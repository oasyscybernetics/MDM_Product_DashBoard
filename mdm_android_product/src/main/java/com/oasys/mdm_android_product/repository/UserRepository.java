package com.oasys.mdm_android_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasys.mdm_android_product.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

	User findByEmail(String email);
}
