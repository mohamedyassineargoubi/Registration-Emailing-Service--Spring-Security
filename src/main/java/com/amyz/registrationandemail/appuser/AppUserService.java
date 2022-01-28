package com.amyz.registrationandemail.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amyz.registrationandemail.registration.token.ConfirmationToken;
import com.amyz.registrationandemail.registration.token.ConfirmationTokenRepo;
import com.amyz.registrationandemail.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {


private final AppUserRepo appUserRepo;
private final ConfirmationTokenService confirmationTokenService;
private final static String USER_NOT_FOUND_MSG ="user with email %s was not found"; 
private final BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return appUserRepo.findByEmail(email)
				.orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}
	
	public String signUpUser(AppUser appUser) {
		Boolean userExist = appUserRepo.findByEmail(appUser.getEmail()).isPresent();
		
		if(userExist) {
			throw new IllegalStateException("email already taken");
		}
		String encodedPassword=bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encodedPassword);
		appUserRepo.save(appUser);
		
		// send confirmation token
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(
				
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appUser
				
				);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		//send email
		return token;
	}


    public int enableAppUser(String email) {
        return appUserRepo.enableAppUser(email);
    }
	

}
