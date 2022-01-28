package com.amyz.registrationandemail.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {
	
	@Autowired 
	RegistrationService registrationService;
	
	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
	
	@GetMapping("/confirm")
	public String confirm(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
	}

}
