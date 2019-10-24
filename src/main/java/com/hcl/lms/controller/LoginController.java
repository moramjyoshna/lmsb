package com.hcl.lms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.lms.dto.LoginRequestDto;
import com.hcl.lms.dto.LoginResponseDto;
import com.hcl.lms.service.LoginService;

/**
 * @author Jyoshna
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")

public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	/**
	 * This method is used to login the customer by providing valid credentials.
	 * 
	 * @param parameters emailId, password
	 * @return This method returns success message on login
	 */

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> verify(@RequestBody LoginRequestDto loginRequestDto) {
		logger.info("inside login controller");
		LoginResponseDto loginResponseDto = loginService.login(loginRequestDto);
		return new ResponseEntity<>(loginResponseDto, HttpStatus.CREATED);
	}
}
