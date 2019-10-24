package com.hcl.lms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.lms.dto.AddSummaryInfo;
import com.hcl.lms.dto.AddSummaryResponse;
import com.hcl.lms.dto.BorrowSummaryInfo;
import com.hcl.lms.dto.BorrowSummaryResponseDto;
import com.hcl.lms.service.UserBookSummary;


/**
 * @author Jyoshna, Subashri
 *
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UserBookSummaryController {

	private static final Logger logger = LoggerFactory.getLogger(UserBookSummaryController.class);

	@Autowired
	UserBookSummary userBookSummary;
	
	/**
	 * This method is to get the user lend info.
	 * 
	 * @param parameter userId
	 * @return This method returns user lend info
	 */

	@GetMapping("/book/{userId}/lendSummary")
	public ResponseEntity<AddSummaryResponse> addSummary(@PathVariable("userId") Integer userId) {
		logger.info("inside add book summary controller");
		AddSummaryResponse addSummaryResponse = new AddSummaryResponse();
		List<AddSummaryInfo> summaryInfo = userBookSummary.addSummaryInfo(userId);
		addSummaryResponse.setAddedBookInfo(summaryInfo);
		addSummaryResponse.setMessage("Lended Books");
		addSummaryResponse.setStatusCode(200);
		return new ResponseEntity<>(addSummaryResponse, HttpStatus.OK);
	}
	
	/**
	 * This method is to get the user borrow info.
	 * 
	 * @param parameter userId
	 * @return This method returns user borrow info
	 */

	@GetMapping("/book/{userId}/borrowSummary")
	public ResponseEntity<BorrowSummaryResponseDto> borrowSummary(@PathVariable("userId") Integer userId) {
		logger.info("inside borrow book summary controller");
		BorrowSummaryResponseDto borrowSummaryResponseDto = new BorrowSummaryResponseDto();
		List<BorrowSummaryInfo> borrowSummaryInfo = userBookSummary.borrowSummaryInfo(userId);
		borrowSummaryResponseDto.setBorrowSummaryInfo(borrowSummaryInfo);
		borrowSummaryResponseDto.setMessage("Borrowed Books");
		borrowSummaryResponseDto.setStatusCode(201);
		return new ResponseEntity<>(borrowSummaryResponseDto, HttpStatus.CREATED);
	}

}
