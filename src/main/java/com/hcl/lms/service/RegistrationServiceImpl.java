package com.hcl.lms.service;

import java.time.LocalDate;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.RegistrationRequestDto;
import com.hcl.lms.entity.User;
import com.hcl.lms.exception.CommonException;
import com.hcl.lms.repository.UserRepository;
import com.hcl.lms.util.ExceptionConstants;

/**
 * @author Subashri Sridharan
 *
 */

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	/**
	 * This method is used to register user by providing user info.
	 * 
	 * @param parameters firstName,lastName,emailId,password
	 * @return This method returns success message on registration
	 */

	public String registerUser(RegistrationRequestDto registrationRequestDto) {
		logger.info("inside registration service");
		User checkCustomerEmail = userRepository.findByEmailId(registrationRequestDto.getEmailId());
		if (checkCustomerEmail != null) {
			throw new CommonException(ExceptionConstants.EXIST_EMAIL);
		}
		User user = new User();
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (!registrationRequestDto.getEmailId().matches(regex)) {
			throw new CommonException(ExceptionConstants.EMAIL_INVALID);
		}
		if (Long.toString(registrationRequestDto.getMobileNumber()).length() < 10) {
			throw new CommonException(ExceptionConstants.MOBILE_INVALID);
		}
		String randomPasscode = RandomStringUtils.randomAlphanumeric(5);
		BeanUtils.copyProperties(registrationRequestDto, user);
		user.setDateOfRegistration(LocalDate.now());
		user.setPasscode(randomPasscode);
		userRepository.save(user);
		return "Successfully Registered";
	}
}
