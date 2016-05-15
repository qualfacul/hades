package com.hades.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.hades.configuration.security.TokenAuthenticationService;
import com.hades.exceptions.LoginFailureException;

@Component
@RestController
public class LoginFacade {
	@Autowired
	private LoginInfoRepository loginInfoRepository;
	
	@Autowired
	private TokenAuthenticationService tokenService;
	
	public LoginInfoDTO login(LoginInfo loginInfoToAuthenticate){
		Optional<LoginInfo> optionalLoginInfo = loginInfoRepository.findByLogin(loginInfoToAuthenticate.getLogin());

		if (optionalLoginInfo.isPresent()) {
			LoginInfo loginInfo = optionalLoginInfo.get();
			if (BCrypt.checkpw(loginInfoToAuthenticate.getPassword(), loginInfo.getPassword())) {
				tokenService.createTokenFor(loginInfo);
				return new LoginInfoDTO(loginInfo);
			}
		}
		
		throw new LoginFailureException();
	}
}
