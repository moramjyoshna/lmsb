package com.hcl.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {
	
	private String bookName;
	private String author;
	private Integer userId;
}
