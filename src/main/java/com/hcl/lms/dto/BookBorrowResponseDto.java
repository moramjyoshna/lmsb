package com.hcl.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBorrowResponseDto {
	private String bookName;
	private String authorName;
	private Integer statusCode;
	private String message;
	private String status;
 }
