package com.bookcart.service;

import java.util.List;
import java.util.Optional;

import com.bookcart.dto.BaseResponse;
import com.bookcart.dto.BookDto;
import com.bookcart.dto.BookUpdateRequest;
import com.bookcart.dto.MediaCoverage;
import com.bookcart.model.Book;
import org.springframework.http.ResponseEntity;

public interface BookService {

	void save(BookDto bookDto);
	List<Book> findByIsbn(int isbn);
	List<Book> findByAuthor(String authorname);
	Optional<Book> findById(Integer id);
	List<Book> findByBookTitle(String title);
	List<Book> findAllBooks();
	ResponseEntity<String> deleteBook(Integer id);
	ResponseEntity<BaseResponse> updateBook(Book book, Integer id);
	ResponseEntity<BaseResponse> changeBook(BookUpdateRequest request, Integer id);
	List<Book> findByMultipleParams(String title, String author ,String isbnNo);
	List<MediaCoverage> getMediaCoverageOfBook(String bookTitle);
	
}