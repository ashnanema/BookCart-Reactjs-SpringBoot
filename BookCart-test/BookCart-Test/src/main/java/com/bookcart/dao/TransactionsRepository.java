package com.bookcart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookcart.model.Order;

public interface TransactionsRepository extends JpaRepository<Order, Integer> {
	

}
