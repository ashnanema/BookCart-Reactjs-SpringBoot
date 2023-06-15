package com.bookcart.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bookcart.common.Constants;
import com.bookcart.common.ValidationUtils;
import com.bookcart.dto.BaseResponse;
import com.bookcart.dto.BookDto;
import com.bookcart.dto.BookUpdateRequest;
import com.bookcart.dto.BuyRequest;
import com.bookcart.dto.MediaCoverage;
import com.bookcart.exceptions.InvalidRequestException;
import com.bookcart.exceptions.ResourceNotFoundException;
import com.bookcart.model.Book;
import com.bookcart.service.BookService;
import com.bookcart.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")

@RestController
@RequestMapping("/v1")
@Api(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, tags = "books")
public class BookController {

    @Autowired
    BookService bookservice;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    TransactionService transactionService;

    Logger logger = LoggerFactory.getLogger(BookService.class);

    @ApiOperation(value = "Add the book")
    @ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS_MSG_SAVEBOOK, response = BaseResponse.class),
            @ApiResponse(code = 400, message = Constants.BAD_REQUEST, response = BaseResponse.class),
            @ApiResponse(code = 500, message = Constants.SERVER_ERROR, response = BaseResponse.class),
            @ApiResponse(code = 409, message = Constants.CONFLICT_ERROR, response = BaseResponse.class), })
    @PostMapping("/books")
    public ResponseEntity<BaseResponse> addBookDetails(@RequestBody BookDto bookDto) throws ResourceNotFoundException {
        logger.info(bookDto.toString());

        ValidationUtils.ValidateISBN(bookDto);

        bookservice.save(bookDto);
        return ResponseEntity.ok()
                .body(new BaseResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG_SAVEBOOK));
    }

    @ApiOperation(value = "Update the book based on given book Id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS_BOOK_UPDATE, response = Book.class),
            @ApiResponse(code = 400, message = Constants.BAD_REQUEST, response = BaseResponse.class),
            @ApiResponse(code = 500, message = Constants.SERVER_ERROR, response = BaseResponse.class),
            @ApiResponse(code = 404, message = Constants.NOT_FOUND, response = BaseResponse.class) })
    @PutMapping("/books/{id}")
    public ResponseEntity<BaseResponse> editBook(@PathVariable("id") Integer id, @RequestBody BookUpdateRequest book)
            throws ResourceNotFoundException, InvalidRequestException {
        logger.info("Request to update book is in controller layer");

        ValidationUtils.ValidateBookId(id);
        
        return bookservice.changeBook(book, id);
    }

    @ApiOperation(value = "Delete the book based on given book Id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS_BOOK_DELETE, response = BaseResponse.class),
            @ApiResponse(code = 400, message = Constants.BAD_REQUEST, response = BaseResponse.class),
            @ApiResponse(code = 500, message = Constants.SERVER_ERROR, response = BaseResponse.class),
            @ApiResponse(code = 404, message = Constants.NOT_FOUND, response = BaseResponse.class) })
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Integer id) throws ResourceNotFoundException {

        ValidationUtils.ValidateBookId(id);

        return bookservice.deleteBook(id);

    }

    @ApiOperation(value = "Finds the Media Coverage of the book from an External Api")
    @ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS_MSG_MEDIAQUERTY, response = MediaCoverage.class),
            @ApiResponse(code = 400, message = Constants.BAD_REQUEST, response = BaseResponse.class),
            @ApiResponse(code = 500, message = Constants.SERVER_ERROR, response = BaseResponse.class),
            @ApiResponse(code = 404, message = Constants.NOT_FOUND, response = BaseResponse.class), })
    @GetMapping("/books/mediacoverage/{booktitle}")
    public List<MediaCoverage> getMediaCoverage(@PathVariable String booktitle) throws ResourceNotFoundException {
        List<MediaCoverage> books = bookservice.getMediaCoverageOfBook(booktitle);
        logger.info("Fetching Media Coverage details for Book" + booktitle);
        if (books.isEmpty())
            throw new ResourceNotFoundException("Book with given Title is Not present in Media Coverage");
        return books;

    }

    @ApiOperation(value = " Api for buying a book")
    @ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS_BOOK_BUY, response = BaseResponse.class),
            @ApiResponse(code = 400, message = Constants.BAD_REQUEST, response = BaseResponse.class),
            @ApiResponse(code = 500, message = Constants.SERVER_ERROR, response = BaseResponse.class) })
    @PostMapping("/books:buy")
    public ResponseEntity<BaseResponse> buyBook(@RequestBody BuyRequest buyRequest) throws InvalidRequestException {

        ValidationUtils.ValidateBuyRequest(buyRequest);

        // logger.info(buyRequest.toString());

        BaseResponse baseResponse = transactionService.addTransaction(buyRequest);

        return ResponseEntity.ok()
                .body(baseResponse);

    }

    @ApiOperation(value = "Search the books based on Title, author or IsbnNo as well as on multiple Input Paramaeters")
    @ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS_BOOK_FOUND, response = Book.class),
            @ApiResponse(code = 400, message = Constants.BAD_REQUEST, response = BaseResponse.class),
            @ApiResponse(code = 500, message = Constants.SERVER_ERROR, response = BaseResponse.class),
            @ApiResponse(code = 404, message = Constants.NOT_FOUND, response = BaseResponse.class) })
    @GetMapping("/books")
    public List<Book> searchBooks(@RequestParam(required = false) String authorName, @RequestParam(required = false) String bookTitle,
            @RequestParam(required = false) String isbnNo) throws ResourceNotFoundException {

        List<Book> books = bookservice.findByMultipleParams(bookTitle, authorName, isbnNo);
        logger.info("Fetching books for the given Parameters");
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("There is no book present for this given input");
        }

        return books;

    }

}
