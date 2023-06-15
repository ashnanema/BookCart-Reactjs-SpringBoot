package com.bookcart.common;

import org.springframework.http.HttpStatus;

public final class Constants {

	public static final int SUCCESS_CODE = HttpStatus.OK.value();
	public static final String SUCCESS_MSG_BUYBOOK = "This transaction is completed successfully";
	public static final String book_Title_Api = "https://jsonplaceholder.typicode.com/posts?title_like=";
	public static final String book_body_Api = "https://jsonplaceholder.typicode.com/posts?body_like=";
	public static final String SUCCESS_MSG_SAVEBOOK = "Book is saved successfully";
	public static final String SUCCESS_MSG_SAVEUSER = "User is saved successfully";
	public static final String SUCCESS_MSG_UPDATEBOOK = "Book is saved successfully";
	public static final String SUCCESS_MSG_MEDIAQUERTY= "Media query if fetched successfully";
	public static final String BAD_REQUEST = "Bad Request";
	public static final String SERVER_ERROR = "Internal Server Error";
	public static final String NOT_FOUND = "resource not found";
	public static final String CONFLICT_ERROR = "resource already exists";
	public static final String SUCCESS_BOOK_UPDATE = "Book is updated successfully";
	public static final String SUCCESS_BOOK_DELETE = "Book is deleted successfully";
	public static final String SUCCESS_BOOK_BUY = "Books are bought successfully";
	public static final String SUCCESS_BOOK_FOUND = "Books fetched successfully";
	
}
