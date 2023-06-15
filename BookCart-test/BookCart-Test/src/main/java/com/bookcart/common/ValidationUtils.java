package com.bookcart.common;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bookcart.dto.BookDto;
import com.bookcart.dto.BookUpdateRequest;
import com.bookcart.dto.BuyRequest;
import com.bookcart.exceptions.InvalidRequestException;
import com.bookcart.exceptions.ResourceAlreadyExistsException;
import com.bookcart.exceptions.ResourceNotFoundException;
import com.bookcart.model.Book;
import com.bookcart.service.BookService;

@Component
public class ValidationUtils {

    private static BookService bookService;

    private ValidationUtils(BookService bookService) {
        this.bookService = bookService;
    }

    public static void ValidateBuyRequest(BuyRequest buyRequest) throws InvalidRequestException {

        List<Book> bookList = bookService.findByIsbn(buyRequest.getIsbnNo());

        if (bookList.isEmpty()) {
            throw new InvalidRequestException("Book with the given Id is not present");
        } else {
            if (bookList.get(0)
                    .getNoOfCopies() < buyRequest.getCopies()) {
                throw new InvalidRequestException("No of copies wanted are more than copies present");
            }
        }

    }

    public static void ValidateBookId(int bookId) throws ResourceNotFoundException {

        Optional<Book> book = bookService.findById(bookId);

        if (!book.isPresent()) {
            throw new ResourceNotFoundException("Book with this bookId is not present");
        }
    }

    public static void ValidateISBN(BookDto bookDto) throws ResourceAlreadyExistsException {

        List<Book> book = bookService.findByIsbn(bookDto.getIsbnNo());

        if (!book.isEmpty()) {
            throw new ResourceAlreadyExistsException("Book with the given ISBN no already present");
        }
    }

  
}
