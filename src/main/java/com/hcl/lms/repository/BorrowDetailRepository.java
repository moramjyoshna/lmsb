package com.hcl.lms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.lms.entity.BorrowDetail;

@Repository
public interface BorrowDetailRepository extends JpaRepository<BorrowDetail, Integer> {
	List<BorrowDetail> findByReleaseDate(LocalDate date);
	List<BorrowDetail> findByUserId(Integer userId);
	BorrowDetail findByBookIdAndUserId(Integer bookId, Integer userId);
	List<BorrowDetail> findByBookId(Integer bookId);
	 
}
