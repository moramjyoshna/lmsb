package com.hcl.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDto {
	
	private String message;
	private Integer statusCode;
}
