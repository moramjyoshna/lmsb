package com.hcl.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.lms.entity.BookRequestDetail;

@Repository
public interface BookRequestDetailRepository extends JpaRepository<BookRequestDetail, Integer> {

}
