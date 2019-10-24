package com.hcl.lms.service;

import java.util.List;

import com.hcl.lms.dto.AddSummaryInfo;
import com.hcl.lms.dto.BorrowSummaryInfo;

public interface UserBookSummary {
	List<AddSummaryInfo> addSummaryInfo(Integer userId);

	List<BorrowSummaryInfo> borrowSummaryInfo(Integer userId);
}
