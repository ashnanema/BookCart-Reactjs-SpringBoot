package com.bookcart.service;

import com.bookcart.common.Constants;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookcart.dao.BookRepository;
import com.bookcart.dao.TransactionsRepository;
import com.bookcart.dto.BaseResponse;
import com.bookcart.dto.BuyRequest;
import com.bookcart.model.Book;
import com.bookcart.model.Order;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    /*
     * Add transaction to the transaction table, if noOfCopies = requested copies,deletion from book table takes place, otherwise book table
     * will be updated with remaining copies
     */

    @Transactional
    @Override
    public BaseResponse addTransaction(BuyRequest buyRequest) {

        int copiesAsked = buyRequest.getCopies();

        List<Book> bookList = bookRepository.searchByIsbn(buyRequest.getIsbnNo());
        Book book = bookList.get(0);

        book.setNoOfCopies(book.getNoOfCopies() - copiesAsked);
        bookService.updateBook(book, book.getId());

        double totalPrice = copiesAsked * book.getPrice();

        Order newTransaction = new Order();
        newTransaction.setIsbnNO(book.getIsbnNo());
        newTransaction.setNoOfCopies(copiesAsked);
        newTransaction.setPrice(totalPrice);

        transactionsRepository.save(newTransaction);

        return new BaseResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_BOOK_BUY);

    }

}
