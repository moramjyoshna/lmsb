package com.hcl.lms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByBookNameAndAuthor(String bookName, String author);

	List<Book> findByUserId(Integer userId);

	Book findByBookId(Integer bookId);
	
	Page<Book> findAll(Pageable pageable);

}
