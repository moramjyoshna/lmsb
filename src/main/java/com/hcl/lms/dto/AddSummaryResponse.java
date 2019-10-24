package com.hcl.lms.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSummaryResponse {
	private List<AddSummaryInfo> addedBookInfo;
	private String message;
	private Integer statusCode;
}
