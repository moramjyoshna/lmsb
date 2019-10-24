package com.hcl.lms.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcl.lms.dto.LoginRequestDto;
import com.hcl.lms.dto.LoginResponseDto;
import com.hcl.lms.entity.User;
import com.hcl.lms.exception.CommonException;
import com.hcl.lms.repository.UserRepository;
import com.hcl.lms.service.LoginServiceImpl;

/**
 * @author Jyoshna
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceTest.class);

	@Mock
	UserRepository userRepository;

	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	User user;

	LoginResponseDto loginResponseDto;

	LoginRequestDto loginRequestDto;

	@Before
	public void setup() {

		user = new User();
		user.setUserId(1);
		user.setPasscode("@^+=&");
		user.setFirstName("M");
		user.setLastName("Jyoshna");
		user.setEmailId("jyoshna@gmail.com");
		user.setMobileNumber(7598162327L);

		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setEmailId("jyoshna@gmail.com");
		loginRequestDto.setPasscode("@^+=&");
	}

	@Test
	public void testLogin() {
		logger.info("inside login service test");
		Mockito.when(userRepository.findByEmailIdAndPasscode(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(user);
		LoginResponseDto loginResponseDto = loginServiceImpl.login(loginRequestDto);
		loginResponseDto.setMessage("Login Successfull");
		Assert.assertEquals("Login Successfull", loginResponseDto.getMessage());

	}

	@Test(expected = CommonException.class)
	public void negativeTestLogin() {
		logger.info("inside login test");
		Mockito.when(userRepository.findByEmailIdAndPasscode(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(null);
		LoginResponseDto loginResponseDto = loginServiceImpl.login(loginRequestDto);
		loginResponseDto.setMessage("Login Successfull");
		Assert.assertEquals("Login Successfull", loginResponseDto.getMessage());

	}
}
