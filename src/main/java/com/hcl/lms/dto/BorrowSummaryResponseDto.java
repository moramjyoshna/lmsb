package com.hcl.lms.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowSummaryResponseDto {

	private List<BorrowSummaryInfo> borrowSummaryInfo;
	private String message;
	private Integer statusCode;
}
