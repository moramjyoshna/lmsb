package com.hcl.lms.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.hcl.lms.dto.RegistrationRequestDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.service.RegistrationServiceImpl;

/**
 * @author Subashri
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationControllerTest.class);

	@Mock
	RegistrationServiceImpl registrationService;

	@InjectMocks
	RegistrationController registrationController;

	RegistrationRequestDto requestDto;

	String message = "";

	@Before
	public void initData() {

		requestDto = new RegistrationRequestDto();
		requestDto.setEmailId("subashri@gmail.com");
		requestDto.setFirstName("Subashri");
		requestDto.setLastName("Sridharan");
		requestDto.setMobileNumber(98765432109L);
		message = "Successfully Registered";

	}

	@Test
	public void testRegisterUser() {
		logger.info("inside user registration controller test");
		Mockito.when(registrationService.registerUser(Mockito.any())).thenReturn(message);
		ResponseEntity<ResponseDto> response = registrationController.registerUser(requestDto);
		assertEquals(201, response.getStatusCode().value());
	}
}
