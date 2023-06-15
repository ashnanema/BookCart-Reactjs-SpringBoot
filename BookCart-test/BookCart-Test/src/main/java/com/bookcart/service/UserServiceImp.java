package com.bookcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookcart.dao.UserRepository;
import com.bookcart.exceptions.ResourceAlreadyExistsException;
import com.bookcart.model.User;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		if (userRepository.existsById(user.getId())) {
            throw new ResourceAlreadyExistsException();
        }
        User saveUser =userRepository.save(user);
        return saveUser;
	
	
	}


	@Override
	public ResponseEntity<User> updateUser(User user, Integer id) {
		User existingUser = userRepository.findById(id).orElse(null);
		existingUser.setEmail(user.getEmail());
		existingUser.setName(user.getName());
		userRepository.save(existingUser);
		return ResponseEntity.ok().body(user);
		
	}


	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	

}
