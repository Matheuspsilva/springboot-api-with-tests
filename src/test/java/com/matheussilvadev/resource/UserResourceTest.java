package com.matheussilvadev.resource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.matheussilvadev.domain.User;
import com.matheussilvadev.repositories.UserRepository;
import com.matheussilvadev.resource.dto.UserDTO;
import com.matheussilvadev.services.impl.UserServiceImpl;

@SpringBootTest
class UserResourceTest {
	
	private static final String E_MAIL_JA_CADASTRADO = "E-mail já cadastrado no sistema";

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

	private static final int FIRST_INDEX = 0;

	private static final Integer ID = 1;

	private static final String NAME = "Matheus";
	
	private static final String EMAIL = "matheus@mail.com";
	
	private static final String PASSWORD = "1234";
	
	private User user;
	private UserDTO userDTO;
	private Optional<User> optionalUser;
	
	@InjectMocks
	private UserResource resource;
	
	@Mock
	private UserServiceImpl service;
	
	@Mock
	private ModelMapper mapper;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		this.startUser();
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
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
