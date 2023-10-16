package com.oasys.mdm_android_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oasys.mdm_android_product.entity.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);

	PasswordResetToken findByLoginId(Integer id);

//	@Query(value = "UPDATE password_reset_token p, tbl_login l SET p.reset_token= :token WHERE p.tbl_login_email = l.email", nativeQuery = true)
//	PasswordResetToken findByEmail(String token);

//	PasswordResetToken findByLoginId(String id);

//	@Transactional
//	PasswordResetToken deleteByResetTokenCreatedBefore(Date expirationTime);

}