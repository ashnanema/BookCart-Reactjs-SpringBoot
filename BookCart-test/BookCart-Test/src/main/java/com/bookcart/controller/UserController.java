package com.bookcart.controller;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookcart.exceptions.ResourceAlreadyExistsException;
import com.bookcart.model.User;
import com.bookcart.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@Api(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, tags = "users")
public class UserController {
	@Autowired
	UserService userService;
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
  @ApiOperation(value = "Save a user details")
	 @PostMapping("/users")
	 public ResponseEntity saveUser(@RequestBody User user) throws ResourceAlreadyExistsException {
	     User saveUser = userService.saveUser(user);
	     return new ResponseEntity(saveUser, HttpStatus.CREATED);
	    }


	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		logger.info("Request to update user in controller layer" + id);
		return userService.updateUser(user, id);
	}
	
	@GetMapping("/users")
	public List<User> getAllUser(){
		logger.info("Run");
		logger.info("Running");
		return userService.getAllUser();


}
	
}