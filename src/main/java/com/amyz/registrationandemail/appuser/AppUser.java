package com.amyz.registrationandemail.appuser;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class AppUser implements UserDetails{

	@SequenceGenerator(
			name = "app_user_sequence",
			sequenceName = "app_user_sequence",
			allocationSize = 1
			)
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE )
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Boolean locked= false;
	private Boolean enabled= false;
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	public AppUser(String name, String username, String email, String password, AppUserRole appUserRole
			) {
		super();
		this.firstName = name;
		this.lastName = username;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
		
	}

}
