package com.bookcart.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bookcart.exceptions.ResourceAlreadyExistsException;
import com.bookcart.model.Book;
import com.bookcart.model.User;

public interface UserService {
	
	User saveUser(User user) throws ResourceAlreadyExistsException;;
	
	ResponseEntity<User> updateUser(User user, Integer id);
	
	List<User> getAllUser();

}
