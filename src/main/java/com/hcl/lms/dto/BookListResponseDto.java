package com.hcl.lms.dto;

import java.util.List;

import com.hcl.lms.entity.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookListResponseDto {

	private List<Book> bookList;
	private Integer statusCode;
	private String message;
}
