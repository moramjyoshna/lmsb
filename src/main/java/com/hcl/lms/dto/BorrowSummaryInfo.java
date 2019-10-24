package com.hcl.lms.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowSummaryInfo {
	private String bookName;
	private String author;
	private LocalDate borrowDate;
	private LocalDate releaseDate;
}
