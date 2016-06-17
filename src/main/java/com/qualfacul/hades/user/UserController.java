package com.qualfacul.hades.user;

import static com.qualfacul.hades.login.LoginOrigin.USER;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qualfacul.hades.annotation.PermitEndpoint;
import com.qualfacul.hades.annotation.Post;
import com.qualfacul.hades.configuration.security.TokenAuthenticationService;
import com.qualfacul.hades.login.LoginInfo;
import com.qualfacul.hades.login.LoginInfoDTO;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenAuthenticationService tokenService;

	@Transactional
	@PermitEndpoint
	@Post(value = "/register", responseStatus = CREATED)
	public LoginInfoDTO register(@Valid @RequestBody UserDTO userDTO) {
		User user;
		Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());

		if (optionalUser.isPresent() && optionalUser.get().getLoginInfo().isFromFacebook()) {
			user = optionalUser.get();
			user.getLoginInfo().setLoginOrigin(USER);
			user.getLoginInfo().setPassword(userDTO.getPassword());
		} else {
			LoginInfo loginInfo = new LoginInfo(userDTO.getEmail(), userDTO.getPassword(), USER);
			user = new User(userDTO.getName(), userDTO.getEmail(), loginInfo);
		}

		userRepository.save(user);
		LoginInfo loginInfo = user.getLoginInfo();
		tokenService.createTokenFor(loginInfo);
		return new LoginInfoDTO().from(loginInfo);
	}

}