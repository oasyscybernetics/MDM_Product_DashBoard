package com.oasys.mdm_android_product.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class PasswordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long token_id;
	private String token;

	@OneToOne(targetEntity = Login.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false,name = "id", referencedColumnName = "id")
	private Login login;

	private Date expiryDate;

	public boolean isExpired() {
		// Compare the current date with the expiration date
		return expiryDate != null && expiryDate.before(new Date());
	}

}