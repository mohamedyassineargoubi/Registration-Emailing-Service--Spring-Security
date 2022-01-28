package com.amyz.registrationandemail.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.amyz.registrationandemail.appuser.AppUser;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken {
	

	@SequenceGenerator(
			name = "confirmation_token_sequence",
			sequenceName = "confirmation_token_sequence",
			allocationSize = 1
			)
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE )
	private Long id;
	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = false)
	private LocalDateTime expiredAt;
	private LocalDateTime confirmedAt;
	@ManyToOne
	@JoinColumn(nullable = false, name = "app_user_id")
	private AppUser appUser;
	
	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt,
			AppUser appUser) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
		this.appUser = appUser;
	}
	
	
	

}
