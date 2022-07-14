package com.matheussilvadev.services;

import java.util.List;

import com.matheussilvadev.domain.User;

public interface UserService {

	User findById(Integer id);
	
	List<User> findAll();
	
}
