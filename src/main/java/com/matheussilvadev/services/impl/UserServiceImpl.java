package com.matheussilvadev.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheussilvadev.domain.User;
import com.matheussilvadev.repositories.UserRepository;
import com.matheussilvadev.resource.dto.UserDTO;
import com.matheussilvadev.services.UserService;
import com.matheussilvadev.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Integer id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User create(UserDTO userDTO) {
		return userRepository.save(modelMapper.map(userDTO, User.class));

	}

}
