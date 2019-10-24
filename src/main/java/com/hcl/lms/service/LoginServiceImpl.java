package com.hcl.lms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.LoginRequestDto;
import com.hcl.lms.dto.LoginResponseDto;
import com.hcl.lms.entity.User;
import com.hcl.lms.exception.CommonException;
import com.hcl.lms.repository.UserRepository;
import com.hcl.lms.util.ExceptionConstants;

/**
 * @author Jyoshna
 *
 */

@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	UserRepository customerRepository;

	/**
	 * This method is used to login the customer by providing valid credentials.
	 * 
	 * @param parameters emailId, password
	 * @return This method returns success message on login
	 */

	@Override
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {

		logger.info("inside login service");
		User customer = customerRepository.findByEmailIdAndPasscode(loginRequestDto.getEmailId(),
				loginRequestDto.getPasscode());
		if (customer == null) {
			throw new CommonException(ExceptionConstants.USER_NOT_FOUND);
		}

		customer.setEmailId(loginRequestDto.getEmailId());
		customer.setPasscode(loginRequestDto.getPasscode());

		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setUserId(customer.getUserId());
		loginResponseDto.setStatusCode(201);
		loginResponseDto.setMessage("Login is successfull...");
		return loginResponseDto;
	}

}
