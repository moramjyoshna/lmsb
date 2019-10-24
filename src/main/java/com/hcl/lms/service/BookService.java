package com.hcl.lms.service;

import java.util.List;

import com.hcl.lms.dto.BookBorrowResponseDto;
import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.BookRequestDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.entity.Book;


public interface BookService {

	public List<Book> getBookList();
	public  BookBorrowResponseDto borrow(BookRequestDto bookRequestDto);
	public  ResponseDto save (BookDto bookDto);
	public Book requestBook(BookRequestDto bookRequestDto);

}
