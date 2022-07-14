package com.matheussilvadev.services;

import java.util.List;

import com.matheussilvadev.domain.User;
import com.matheussilvadev.resource.dto.UserDTO;

public interface UserService {

	User findById(Integer id);
	
	List<User> findAll();
	
	User create(UserDTO userDTO);
	
	User update(UserDTO userDTO);
	
}
