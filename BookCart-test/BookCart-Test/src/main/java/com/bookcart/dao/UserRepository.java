package com.bookcart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookcart.model.User;



public interface UserRepository extends JpaRepository<User, Integer>{

}
