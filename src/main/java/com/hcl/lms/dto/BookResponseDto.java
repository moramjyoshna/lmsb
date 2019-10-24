package com.hcl.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDto {
	private String bookName;
	private String authorName;
	private String message;
	private Integer statusCode;
	private String status;

}
