package com.hades.login;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hades.annotation.PermitEndpoint;
import com.hades.annotation.Post;
import com.hades.configuration.security.TokenAuthenticationService;
import com.hades.exceptions.LoginFailureException;

@RestController
public class LoginController {

	@Autowired
	private LoginInfoRepository loginInfoRepository;

	@Autowired
	private TokenAuthenticationService tokenService;

	@PermitEndpoint
	@Post("/login")
	public LoginInfoDTO login(@RequestBody @Valid LoginInfo loginInfoToAuthenticate) {

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
