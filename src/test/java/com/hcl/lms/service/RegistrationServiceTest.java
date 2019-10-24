package com.hcl.lms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.hcl.lms.dto.RegistrationRequestDto;
import com.hcl.lms.entity.User;
import com.hcl.lms.exception.CommonException;
import com.hcl.lms.repository.UserRepository;
import com.hcl.lms.service.RegistrationServiceImpl;

/**
 * @author Subashri
 *
 */

@RunWith(MockitoJUnitRunner.class)

public class RegistrationServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceTest.class);

	@Mock
	UserRepository userRepository;

	@InjectMocks
	RegistrationServiceImpl registrationService;

	User user = new User();
	RegistrationRequestDto requestDto;

	@Before
	public void initData() {
		requestDto = new RegistrationRequestDto();
		requestDto.setEmailId("subashri@gmail.com");
		requestDto.setFirstName("Subashri");
		requestDto.setLastName("Sridharan");
		requestDto.setMobileNumber(98765432109L);
		BeanUtils.copyProperties(requestDto, user);
		user.setDateOfRegistration(LocalDate.now());
		user.setPasscode("sdne6");
		user.setUserId(1);

	}

	@Test
	public void testRegisterUser() {
		logger.info("inside register user service test");
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		String info = registrationService.registerUser(requestDto);
		assertNotNull(info);
		assertEquals("Successfully Registered", info);
	}

	@Test(expected = CommonException.class)
	public void mobileTest() {

		requestDto.setMobileNumber(2345L);
		String info = registrationService.registerUser(requestDto);
		assertEquals("Successfully Registered", info);

	}

	@Test(expected = CommonException.class)
	public void emailTest() {

		requestDto.setEmailId("subashri.com");
		String info = registrationService.registerUser(requestDto);
		assertNotNull(info);
		assertEquals("Successfully Registered", info);

	}

}
