package com.matheussilvadev.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheussilvadev.domain.User;
import com.matheussilvadev.resource.dto.UserDTO;
import com.matheussilvadev.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable(value = "id") Integer id){
		
		return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> userList = service.findAll();
		List<UserDTO> userListDTO = userList.stream().map( x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(userListDTO);
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO){
		
		System.out.println(userDTO);

		User newObj = service.create(userDTO);
		//Retorna URI de acesso ao novo objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO userDTO){
		//Garantir que o usuário tem o id recebido na url
		userDTO.setId(id);
		User newUser = service.update(userDTO);
		
		return ResponseEntity.ok().body(mapper.map(newUser, UserDTO.class));
		
	}

}
