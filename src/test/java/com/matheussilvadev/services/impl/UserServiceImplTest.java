package com.matheussilvadev.services.impl;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.matheussilvadev.domain.User;
import com.matheussilvadev.repositories.UserRepository;
import com.matheussilvadev.resource.dto.UserDTO;
import com.matheussilvadev.services.exceptions.ObjectNotFoundException;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

@SpringBootTest
class UserServiceImplTest {
	
	private static final int FIRST_INDEX = 0;

	private static final Integer ID = 1;

	private static final String NAME = "Matheus";
	
	private static final String EMAIL = "matheus@mail.com";
	
	private static final String PASSWORD = "1234";

	//Instância real da classe
	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;
	private UserDTO userDTO;
	private Optional<User> optionalUser;


	@BeforeEach
	void setUp() throws Exception {
		//Inicia mocks da classe
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void whenFindByIdThenReturnAnUserInstance() {
		//Mock da resposta do repository.findById utilizando no UserServiceImpl
		//Quando o método for chamado retorna optionalUSer
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		
		User response = service.findById(ID);
		
		Assertions.assertNotNull(response);
		//Classe esperada/Classe atual
		Assertions.assertEquals(User.class, response.getClass());
		Assertions.assertEquals(ID, response.getId());
		Assertions.assertEquals(NAME, response.getName());
		Assertions.assertEquals(EMAIL, response.getEmail());
	}
	
	@Test
	void whenFindByIdThenReturnAnObjectNotFoundException() {
		//Mock da resposta do repository.findById utilizando no UserServiceImpl
		//Quando o método for chamado retorna ObjectNotFoundException
		Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
		
		try {
			service.findById(ID);
		}catch (Exception ex) {
			Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
			Assertions.assertEquals("Objeto não encontrado", ex.getMessage());
		}
		
	}

	@Test
	void whenFindAllReturnAnListOfUsers() {	
		Mockito.when(repository.findAll()).thenReturn(List.of(user));
		
		List<User> response = service.findAll();
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(1, response.size());
		Assertions.assertEquals(User.class, response.get(FIRST_INDEX).getClass());
		Assertions.assertEquals(ID, response.get(FIRST_INDEX).getId());
		Assertions.assertEquals(NAME, response.get(FIRST_INDEX).getName());
		Assertions.assertEquals(EMAIL, response.get(FIRST_INDEX).getEmail());
		Assertions.assertEquals(PASSWORD, response.get(FIRST_INDEX).getPassword());
		
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}
	
	//Inicia os valores das instâncias de usuário
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
		
	}

}
