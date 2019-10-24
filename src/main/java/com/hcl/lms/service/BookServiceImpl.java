package com.hcl.lms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.BookBorrowResponseDto;
import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.BookRequestDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.entity.BookRequestDetail;
import com.hcl.lms.entity.BorrowDetail;
import com.hcl.lms.exception.CommonException;
import com.hcl.lms.repository.BookRepository;
import com.hcl.lms.repository.BookRequestDetailRepository;
import com.hcl.lms.repository.BorrowDetailRepository;
import com.hcl.lms.repository.UserRepository;
import com.hcl.lms.util.ExceptionConstants;

/**
 * @author Jyoshna, Subashri, Shilendra
 *
 */

@Service
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	BookRepository bookRepository;
	@Autowired
	BookRequestDetailRepository bookRequestDetailRepository;
	@Autowired
	BorrowDetailRepository borrowDetailRepository;
	@Autowired
	UserRepository userRepository;

	Random random = new Random();

	/**
	 * This method is used to get the list of books.
	 * 
	 * @param no parameters taken
	 * @return This method will return list of books
	 */

	@Override
	public List<Book> getBookList() {
		LOGGER.info("inside list of books service");
		return bookRepository.findAll();
	}

	/**
	 * This method is used to add the book.
	 * 
	 * @param parameters userId, bookName, author
	 * @return This method returns success message on adding the book
	 */

	@Override
	public ResponseDto save(BookDto bookDto) {
		LOGGER.info("inside add book service");
		Book book = new Book();
		ResponseDto responseDto = new ResponseDto();
		BeanUtils.copyProperties(bookDto, book);
		book.setLendDate(LocalDate.now());
		book.setBookCode(random.nextInt(1000));
		book.setStatus("Available");
		bookRepository.save(book);
		responseDto.setMessage("Book Added Successfully");
		responseDto.setStatusCode(200);
		return responseDto;
	}

	/**
	 * This method is used to borrow the book.
	 * 
	 * @param parameters userId, bookId
	 * @return This method returns success message on borrowing the book
	 */

	@Override
	public BookBorrowResponseDto borrow(BookRequestDto bookRequestDto) {
		LOGGER.info("inside borrow book service");
		BorrowDetail borrow = borrowDetailRepository.findByBookIdAndUserId(bookRequestDto.getBookId(),
				bookRequestDto.getUserId());
		Book bookData = bookRepository.findByBookId(bookRequestDto.getBookId());
		if (borrow != null) {
			throw new CommonException(ExceptionConstants.ALREADY_AVAILED);
		}
		BookBorrowResponseDto bookBorrowResponseDto = new BookBorrowResponseDto();
		BorrowDetail borrowDetail = new BorrowDetail();
		Book bookInfo = null;
		BookBorrowResponseDto borrowResponseDto = new BookBorrowResponseDto();
		BeanUtils.copyProperties(borrowResponseDto, borrowDetail);
		borrowDetail.setBookId(bookRequestDto.getBookId());
		borrowDetail.setUserId(bookRequestDto.getUserId());
		borrowDetail.setDateOfBorrow(LocalDate.now());
		bookData.setStatus("Availed");
		borrowDetail.setReleaseDate(LocalDate.now().plusDays(3));
		bookRepository.save(bookData);
		borrowDetailRepository.save(borrowDetail);
		Optional<Book> book = bookRepository.findById(bookRequestDto.getBookId());
		if (!book.isPresent()) {
			throw new CommonException(ExceptionConstants.BOOK_NOT_AVAILABLE);
		}
		bookInfo = book.get();
		bookBorrowResponseDto.setAuthorName(bookInfo.getAuthor());
		bookBorrowResponseDto.setBookName(bookInfo.getBookName());
		bookBorrowResponseDto.setMessage("You have borrowed a book");
		bookBorrowResponseDto.setStatusCode(201);
		bookBorrowResponseDto.setStatus(bookInfo.getStatus());
		return bookBorrowResponseDto;
	}

	/**
	 * This method is used to send request for the book.
	 * 
	 * @param parameters userId, bookId
	 * @return This method returns success message on requesting to avail the book
	 */

	@Override
	public Book requestBook(BookRequestDto bookRequestDto) {
		LOGGER.info("inside book request service");

		BookRequestDetail bookRequestrequestDetail = new BookRequestDetail();
		BeanUtils.copyProperties(bookRequestDto, bookRequestrequestDetail);
		bookRequestDetailRepository.save(bookRequestrequestDetail);
		Optional<Book> book = bookRepository.findById(bookRequestDto.getBookId());
		if (!book.isPresent()) {
			throw new CommonException(ExceptionConstants.BOOK_NOT_AVAILABLE);
		}
		Book bookInfo;
		bookInfo = book.get();
		return bookInfo;
	}

}
