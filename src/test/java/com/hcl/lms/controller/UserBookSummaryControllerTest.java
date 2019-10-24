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

import com.hcl.lms.dto.AddSummaryInfo;
import com.hcl.lms.dto.AddSummaryResponse;
import com.hcl.lms.dto.BookBorrowResponseDto;
import com.hcl.lms.dto.BookDto;

import com.hcl.lms.dto.BookRequestDto;

import com.hcl.lms.dto.BorrowSummaryInfo;
import com.hcl.lms.dto.BorrowSummaryResponseDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.service.BookServiceImpl;
import com.hcl.lms.service.UserBookSummary;

/**
 * @author Subashri, Jyoshna
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserBookSummaryControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(UserBookSummaryControllerTest.class);

	@Mock
	BookServiceImpl bookServiceImpl;

	@Mock
	UserBookSummary userBookSummary;

	@InjectMocks
	UserBookSummaryController bookController;

	Book book;
	BookDto bookDto;
	ResponseDto responseDto;
	BookBorrowResponseDto bookBorrowResponseDto;

	BookRequestDto bookRequestDto;
	AddSummaryInfo addSummaryInfo;
	List<AddSummaryInfo> addSummaryDetail;
	BorrowSummaryInfo borrowSummaryInfo;
	List<BorrowSummaryInfo> borrowSummarydetail;

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

		addSummaryInfo = new AddSummaryInfo();
		addSummaryDetail = new ArrayList<>();
		addSummaryInfo.setAuthor(book.getAuthor());
		addSummaryInfo.setBookName(book.getBookName());
		addSummaryInfo.setLendDate(book.getLendDate());
		addSummaryDetail.add(addSummaryInfo);

		borrowSummaryInfo = new BorrowSummaryInfo();
		borrowSummarydetail = new ArrayList<>();
		borrowSummaryInfo.setBookName("Java");
		borrowSummaryInfo.setAuthor("Jyoshna");
		borrowSummarydetail.add(borrowSummaryInfo);

		bookBorrowResponseDto = new BookBorrowResponseDto();
		bookBorrowResponseDto.setMessage("Books Borrowed Sucessfully");
		bookBorrowResponseDto.setStatusCode(201);
	}

	@Test
	public void testAddSummary() {
		logger.info("inside add book summary controller test");
		Mockito.when(userBookSummary.addSummaryInfo(Mockito.anyInt())).thenReturn(addSummaryDetail);
		ResponseEntity<AddSummaryResponse> response = bookController.addSummary(1);
		assertNotNull(response);
		assertEquals(200, response.getStatusCode().value());

	}

	@Test
	public void testBorrowSummary() {
		logger.info("inside borrow book summary controller test");
		Mockito.when(userBookSummary.borrowSummaryInfo(Mockito.anyInt())).thenReturn(borrowSummarydetail);
		ResponseEntity<BorrowSummaryResponseDto> response = bookController.borrowSummary(1);
		assertNotNull(response);
		assertEquals(201, response.getStatusCode().value());

	}

}
