package com.bookcart.service;
import com.bookcart.dto.BaseResponse;
import com.bookcart.dto.BuyRequest;

public interface TransactionService {
	public BaseResponse addTransaction(BuyRequest transactionRequest);
}
