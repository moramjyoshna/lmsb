package com.hcl.lms.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer statusCode;
	private String message;

}
