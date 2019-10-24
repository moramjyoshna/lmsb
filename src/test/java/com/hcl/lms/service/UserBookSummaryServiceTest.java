package com.hcl.lms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcl.lms.dto.AddSummaryInfo;
import com.hcl.lms.entity.Book;
import com.hcl.lms.repository.BookRepository;
import com.hcl.lms.repository.BorrowDetailRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserBookSummaryServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(UserBookSummaryServiceTest.class);

	@Mock
	BookRepository bookRepository;
	@Mock
	BorrowDetailRepository borrowDetailRepository;

	@InjectMocks
	UserBookSummaryImpl userBookSummaryImpl;

	AddSummaryInfo addSummaryInfo;
	List<AddSummaryInfo> listAddSummaryInfo;

	Book book;
	List<Book> bookList;

	@Before
	public void setup() {

		book = new Book();
		book.setBookId(1);
		book.setBookCode(101);
		book.setAuthor("Jyoshna");
		book.setBookName("Java");
		book.setUserId(1);

		addSummaryInfo = new AddSummaryInfo();
		addSummaryInfo.setAuthor("Jyoshna");
		addSummaryInfo.setBookName("Java");
		addSummaryInfo.setLendDate(LocalDate.now());

		listAddSummaryInfo = new ArrayList<>();
		listAddSummaryInfo.add(addSummaryInfo);
		bookList = new ArrayList<>();
		bookList.add(book);

	}

	@Test
	public void testAddSummaryInfo() {
		logger.info("inside list of books service test");
		Mockito.when(bookRepository.findByUserId(1)).thenReturn(bookList);
		List<AddSummaryInfo> response = userBookSummaryImpl.addSummaryInfo(1);
		assertEquals(listAddSummaryInfo.get(0).getAuthor(), response.get(0).getAuthor());

	}
}
