package com.hcl.lms.util;

public class ExceptionConstants {

	private ExceptionConstants() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final String EXIST_EMAIL = "Email already exists";
	public static final String USER_NOT_FOUND = "Invalid credentials";
	public static final String USER_NOT_EXIST = "User id not found";
	public static final String BOOK_NOT_AVAILABLE = "No Books Available";
	public static final String EMAIL_INVALID = "Invalid emailId";
	public static final String MOBILE_INVALID = "Invalid Mobile Number";
	public static final String NO_ADDED_BOOKS_FOUND = "No Lended Books";
	public static final String BOOK_EXIST = "Book already exists";
	public static final String ALREADY_AVAILED = "You have already borrowed this book";
}
