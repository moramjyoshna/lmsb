package com.hcl.lms.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSummaryInfo {
	private String bookName;
	private String author;
	private LocalDate lendDate;
}
