package com.hcl.lms.util;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcl.lms.entity.Book;
import com.hcl.lms.entity.BorrowDetail;
import com.hcl.lms.repository.BookRepository;
import com.hcl.lms.repository.BorrowDetailRepository;

/**
 * @author Subashri Sridharan
 *
 */

@Component
public class Scheduler {
	@Autowired
	BorrowDetailRepository borrowDetailRepository;
	@Autowired
	BookRepository bookRepository;

	/**
	 * Book will be released automatically on the releaseDate
	 *
	 */

	@Scheduled(cron = "0 0/1 * * * *")
	public void bookReleaseSchedule() {
		List<BorrowDetail> borrowList = borrowDetailRepository.findByReleaseDate(LocalDate.now());
		borrowList.stream().forEach(borrowInfo -> {
			Book book = bookRepository.findByBookId(borrowInfo.getBookId());
			book.setStatus("Available");
			bookRepository.save(book);

		});
	}
}
