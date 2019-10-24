package com.hcl.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.AddSummaryInfo;
import com.hcl.lms.dto.BorrowSummaryInfo;
import com.hcl.lms.entity.Book;
import com.hcl.lms.entity.BorrowDetail;
import com.hcl.lms.exception.CommonException;
import com.hcl.lms.repository.BookRepository;
import com.hcl.lms.repository.BorrowDetailRepository;
import com.hcl.lms.repository.UserRepository;
import com.hcl.lms.util.ExceptionConstants;

/**
 * @author Subashri, Jyoshna
 *
 */

@Service
public class UserBookSummaryImpl implements UserBookSummary {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	BorrowDetailRepository borrowDetailRepository;

	public List<AddSummaryInfo> addSummaryInfo(Integer userId) {
		logger.info("inside add summary service");
		List<AddSummaryInfo> listAddSummaryInfo = new ArrayList<>();
		List<Book> bookList = bookRepository.findByUserId(userId);
		if (bookList != null) {
			bookList.stream().forEach(a -> {
				AddSummaryInfo addSummaryInfo = new AddSummaryInfo();
				addSummaryInfo.setAuthor(a.getAuthor());
				addSummaryInfo.setBookName(a.getBookName());
				addSummaryInfo.setLendDate(a.getLendDate());
				listAddSummaryInfo.add(addSummaryInfo);

			});
			return listAddSummaryInfo;
		} else {
			throw new CommonException(ExceptionConstants.NO_ADDED_BOOKS_FOUND);
		}
	}

	@Override
	public List<BorrowSummaryInfo> borrowSummaryInfo(Integer userId) {
		logger.info("inside borrow summary service");
		List<BorrowSummaryInfo> listBorrowSummaryInfo = new ArrayList<>();
		List<BorrowDetail> borrowDetailList = borrowDetailRepository.findByUserId(userId);
		borrowDetailList.stream().forEach(a -> {
			BorrowSummaryInfo borrowSummaryInfo = new BorrowSummaryInfo();
			borrowSummaryInfo.setBorrowDate(a.getDateOfBorrow());
			borrowSummaryInfo.setReleaseDate(a.getReleaseDate());
			Book bookList = bookRepository.findByBookId(a.getBookId());
			borrowSummaryInfo.setAuthor(bookList.getAuthor());
			borrowSummaryInfo.setBookName(bookList.getBookName());
			listBorrowSummaryInfo.add(borrowSummaryInfo);
		});
		return listBorrowSummaryInfo;
	}
}
