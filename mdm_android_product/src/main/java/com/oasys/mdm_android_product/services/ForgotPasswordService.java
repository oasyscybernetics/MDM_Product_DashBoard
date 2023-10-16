package com.oasys.mdm_android_product.services;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oasys.mdm_android_product.entity.Login;
import com.oasys.mdm_android_product.entity.PasswordResetToken;
import com.oasys.mdm_android_product.repository.LoginRepository;
import com.oasys.mdm_android_product.repository.PasswordResetTokenRepository;
import com.oasys.mdm_android_product.request.ForgotPasswordRequest;
import com.oasys.mdm_android_product.request.ResetPasswordRequest;
import com.oasys.mdm_android_product.response.DeviceMasterResponse;
import com.oasys.mdm_android_product.response.ForgotPasswordResponse;

@Service
public class ForgotPasswordService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	public DeviceMasterResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
		DeviceMasterResponse response = null;

		// Find the reset token in the database
		PasswordResetToken resetToken = tokenRepository.findByToken(resetPasswordRequest.getToken());

		if (resetToken != null && !resetToken.isExpired()) {
			// Find the user by email
			Login user = loginRepository.findByEmail(resetToken.getLogin().getEmail());

			if (user != null) {
				// Update the user's password
				String hashedPassword = passwordEncoder.encode(resetPasswordRequest.getPassword());
				user.setPassword(hashedPassword);
				loginRepository.save(user);
			}
			response = new DeviceMasterResponse();
			response.setMsg("Password reset successfully");
		} else {
			response = new DeviceMasterResponse();
			response.setMsg("Invalid or expired reset token");
		}

		return response;
	}

	public Date getTokenExpirationTime(String token) {
		PasswordResetToken resetToken = tokenRepository.findByToken(token);
		if (resetToken != null) {
			return resetToken.getExpiryDate();
		}
		return null;
	}

	public void sendPasswordResetEmail(String email, String resetLink) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Password Reset Request");
		message.setText("To reset your password, click the link below:\n" + resetLink);
		javaMailSender.send(message);
	}

	public ForgotPasswordResponse createPasswordResetToken(ForgotPasswordRequest forgotPasswordRequest) {
		ForgotPasswordResponse response = null;
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		String resetToken = UUID.randomUUID().toString();
		try {
			Login user = loginRepository.findByEmail(forgotPasswordRequest.getEmail());

			Date expirationTime = new Date(System.currentTimeMillis() + 900000); // 15mins
			Optional<Login> optionalUser = Optional.ofNullable(user);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();

				if (user.getEmail().equals(forgotPasswordRequest.getEmail())) {
					PasswordResetToken updateToken = tokenRepository.findByLoginId(user.getId());
					Optional<PasswordResetToken> optionalToken = Optional.ofNullable(updateToken);
					if (optionalToken.isPresent()) {
						updateToken = optionalToken.get();
						if (updateToken.getLogin().getId() == user.getId()) {
							updateToken.setToken(resetToken);
							updateToken.setExpiryDate(expirationTime);
							tokenRepository.save(updateToken);

							String resetLink = "http://192.168.2.106:4200/#/auth/change-password?token=" + resetToken;

							sendPasswordResetEmail(forgotPasswordRequest.getEmail(), resetLink);
							response = new ForgotPasswordResponse();
							response.setMsg("Password reset email sent successfully.");
							response.setToken(updateToken.getToken());
						}
					} else {
						passwordResetToken.setLogin(user);
						passwordResetToken.setToken(resetToken);
						System.out.println(resetToken);
						passwordResetToken.setExpiryDate(expirationTime);
						tokenRepository.save(passwordResetToken);

						String resetLink = "http://192.168.6.90:4200/#/auth/change-password?token=" + resetToken;

						sendPasswordResetEmail(forgotPasswordRequest.getEmail(), resetLink);
						response = new ForgotPasswordResponse();
						response.setMsg("Password reset email sent successfully.");
						response.setToken(passwordResetToken.getToken());
					}
				} else {
					response = new ForgotPasswordResponse();
					response.setMsg("user not found");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
