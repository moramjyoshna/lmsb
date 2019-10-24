package com.hcl.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequestDto {
		private String firstName;
		private String lastName;
		private String emailId;
		private Long mobileNumber;
}
