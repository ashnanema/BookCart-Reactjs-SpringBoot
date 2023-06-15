package com.bookcart.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookcart.common.Constants;
import com.bookcart.dao.BookRepository;
import com.bookcart.dto.BaseResponse;
import com.bookcart.dto.BookDto;
import com.bookcart.dto.BookUpdateRequest;
import com.bookcart.dto.MediaCoverage;
import com.bookcart.dto.MediaCoverageComparator;
//import com.bookcart.dto.MediaCoverageComparator;
import com.bookcart.model.Book;

import lombok.NonNull;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Override
    public void save(BookDto bookDto) {
        // TODO Auto-generated method stub
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setImage(bookDto.getImage());
        book.setIsbnNo(bookDto.getIsbnNo());
        book.setTitle(bookDto.getTitle());
        book.setNoOfCopies(bookDto.getNoOfCopies());
        book.setPrice(bookDto.getPrice());
        bookRepository.save(book);
    }

    @Override
    public List<Book> findByIsbn(int isbn) {

        return bookRepository.searchByIsbn(isbn);
    }

    @Override
    public List<Book> findByAuthor(String authorname) {

        return bookRepository.searchByAuthorLike(authorname);
    }

    @Override
    public List<Book> findByBookTitle(String title) {

        return bookRepository.searchByNameLike(title);
    }

    @Override
    public ResponseEntity<String> deleteBook(Integer id) {

        String response;
        if (!bookRepository.findById(id)
                .isPresent()) {

            response = "No book with id: " + id + " is present!!";
            logger.error(response);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            bookRepository.deleteById(id);
            response = "Book with id: " + id + " is deleted";
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<BaseResponse> updateBook(Book book, Integer id) {
        Book existingbook = bookRepository.findById(id)
                .orElse(null);
        existingbook.setAuthor(book.getAuthor());
        existingbook.setImage(book.getImage());
        existingbook.setIsbnNo(book.getIsbnNo());
        existingbook.setNoOfCopies(book.getNoOfCopies());
        existingbook.setPrice(book.getPrice());
        existingbook.setTitle(book.getTitle());
        bookRepository.save(existingbook);
        return ResponseEntity.ok()
                .body(new BaseResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_BOOK_UPDATE));

    }

    @Override
    public List<Book> findByMultipleParams(String title, String author, String isbnNo) {

        if (title != null && author != null && isbnNo != null) {
            return bookRepository.searchByMultipleParams(author, title, Integer.parseInt(isbnNo));
        } else if (title != null && author == null && isbnNo == null) {
            return bookRepository.searchByNameLike(title);
        } else if (author != null && title == null && isbnNo == null) {
            return bookRepository.searchByAuthorLike(author);
        } else if (isbnNo != null && author == null && title == null) {
            return bookRepository.searchByIsbn(Integer.parseInt(isbnNo));
        } else if (author != null && isbnNo != null && title == null) {
            return bookRepository.searchByAuthorAndIsbnNo(author, Integer.parseInt(isbnNo));
        } else if (author != null && title != null && isbnNo == null) {
            return bookRepository.searchByAuthorAndTitle(author, title);
        } else if (isbnNo != null && title != null && author == null) {
            return bookRepository.searchByIsbnNoAndTitle(Integer.parseInt(isbnNo), title);
        }
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Integer id) {

        return bookRepository.findById(id);
    }

    @Override
    public List<MediaCoverage> getMediaCoverageOfBook(String booktitle) {
        String url1 = Constants.book_Title_Api + booktitle; // java
        String url2 = Constants.book_body_Api + booktitle;
        MediaCoverage[] dataBasedOnTitleArr = restTemplate.getForObject(url1, MediaCoverage[].class);
        MediaCoverage[] dataBasedOnBodyArr = restTemplate.getForObject(url2, MediaCoverage[].class);
        List<MediaCoverage> dataBasedOnTitleList = convertArrayToList(dataBasedOnTitleArr);
        List<MediaCoverage> dataBasedOnBodyList = convertArrayToList(dataBasedOnBodyArr);
        List<MediaCoverage> union = Stream.concat(dataBasedOnTitleList.stream(), dataBasedOnBodyList.stream())
                .distinct()
                .collect(Collectors.toList());
        Collections.sort(union, new MediaCoverageComparator(booktitle));
        return union;
    }

    public static <T> List<T> convertArrayToList(T array[]) {
        List<T> list = Arrays.asList(array);
        return list;
    }

    @Override
    public List<Book> findAllBooks() {

        return bookRepository.findAll();
    }

    @Override
    public ResponseEntity<BaseResponse> changeBook(BookUpdateRequest request, Integer id) {
        Book existingbook = bookRepository.findById(id)
                .orElse(null);
        int newCopies, oldCopies;
        oldCopies = existingbook.getNoOfCopies();
        newCopies = Objects.nonNull(request.getAddCopies()) ? oldCopies + request.getAddCopies() : oldCopies;

        if (!request.getAuthor()
                .equals("string")) {
            existingbook.setAuthor(request.getAuthor());
        }
        if (!request.getImage()
                .equals("string")) {
            existingbook.setAuthor(request.getAuthor());
        }
        if (!request.getTitle()
                .equals("string")) {
            existingbook.setAuthor(request.getTitle());
        }
        if (request.getPrice() > 0) {
            existingbook.setPrice(request.getPrice());
        }
        existingbook.setNoOfCopies(newCopies);

        updateBook(existingbook, id);
        
        return ResponseEntity.ok()
                .body(new BaseResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_BOOK_UPDATE));
    }

}
