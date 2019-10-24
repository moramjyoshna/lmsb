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

import com.hcl.lms.dto.RegistrationRequestDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.service.RegistrationService;

/**
 * @author Subashri
 *
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	RegistrationService registrationService;

	/**
	 * This method is used to register user by providing user info.
	 * 
	 * @param parameters firstName,lastName,emailId,password
	 * @return This method returns success message on registration
	 */

	@PostMapping("/register")
	public ResponseEntity<ResponseDto> registerUser(@RequestBody RegistrationRequestDto registrationRequestDto) {
		logger.info("inside user registration controller");
		ResponseDto responseDto = new ResponseDto();
		String message = registrationService.registerUser(registrationRequestDto);
		responseDto.setMessage(message);
		responseDto.setStatusCode(200);

		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}
}
