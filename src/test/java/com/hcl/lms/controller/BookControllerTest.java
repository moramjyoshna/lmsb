package com.hcl.lms.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import com.hcl.lms.dto.BookBorrowResponseDto;
import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.BookRequestDto;
import com.hcl.lms.dto.BookResponseDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.service.BookServiceImpl;
import com.hcl.lms.service.UserBookSummary;

/**
 * @author Jyoshna, Subashri, Shilendra
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(BookControllerTest.class);

	@Mock
	BookServiceImpl bookServiceImpl;
	@Mock
	UserBookSummary userBookSummary;
	@InjectMocks
	BookController bookController;
	Book book;

	BookDto bookDto;
	ResponseDto responseDto;
	BookBorrowResponseDto bookBorrowResponseDto;
	BookResponseDto bookResponse;
	BookRequestDto bookRequestDto;

	@Before
	public void initiateData() {

		book = new Book();
		book.setBookId(1);
		book.setBookCode(101);
		book.setAuthor("Jyoshna");
		book.setBookName("Java");
		book.setUserId(1);

		bookDto = new BookDto();
		bookDto.setAuthor("xyz");
		bookDto.setBookName("Java");
		bookDto.setUserId(101);

		responseDto = new ResponseDto();
		responseDto.setMessage("Added");
		responseDto.setStatusCode(201);

		bookRequestDto = new BookRequestDto();
		bookRequestDto.setBookId(1);
		bookRequestDto.setUserId(1);

		bookBorrowResponseDto = new BookBorrowResponseDto();
		bookBorrowResponseDto.setMessage("Books Borrowed Sucessfully");
		bookBorrowResponseDto.setStatusCode(201);
		bookBorrowResponseDto.setAuthorName("Jyoshna");
		bookBorrowResponseDto.setBookName("Java");
		bookResponse = new BookResponseDto();
		bookResponse.setMessage("List of Books");
		bookResponse.setStatusCode(201);
		bookResponse.setAuthorName("Jyoshna");
		bookResponse.setBookName("Java");
		bookResponse.setStatus("Availed");
	}

	@Test
	public void testGetBookList() {
		List<Book> bookList = new ArrayList<>();
		logger.info("inside book list controller test");
		bookList.add(book);
		Mockito.when(bookServiceImpl.getBookList()).thenReturn(bookList);
		ResponseEntity<List<Book>> bookListResponseDto = bookController.getBookList();
		assertNotNull(bookListResponseDto);
		assertEquals(200, bookListResponseDto.getStatusCode().value());

	}

	@Test
	public void testSave() {
		logger.info("inside add book controller test");
		Mockito.when(bookServiceImpl.save(bookDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> actualResult = bookController.save(bookDto);
		assertEquals(201, actualResult.getStatusCode().value());
	}

	@Test
	public void testBorrow() {
		logger.info("inside borrow book controller test");
		Mockito.when(bookServiceImpl.borrow(bookRequestDto)).thenReturn(bookBorrowResponseDto);
		ResponseEntity<BookBorrowResponseDto> actualResult = bookController.borrow(bookRequestDto);
		assertEquals(201, actualResult.getStatusCode().value());
	}

	@Test
	public void testRequestBook() {
		logger.info("inside request book controller test");
		Mockito.when(bookServiceImpl.requestBook(Mockito.any())).thenReturn(book);
		ResponseEntity<BookResponseDto> response = bookController.requestBook(bookRequestDto);
		assertEquals(201, response.getStatusCode().value());
	}

}
