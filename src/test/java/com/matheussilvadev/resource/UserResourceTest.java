package com.matheussilvadev.resource;

import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
	void whenFindByIdThenReturnSuccess() {
		Mockito.when(service.findById(Mockito.anyInt())).thenReturn(user);
		Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = resource.findById(ID);
		
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(UserDTO.class, response.getBody().getClass());
		Assertions.assertEquals(ID, response.getBody().getId());
		Assertions.assertEquals(NAME, response.getBody().getName());
		Assertions.assertEquals(EMAIL, response.getBody().getEmail());
		Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
		
	}

	@Test
	void whenFindAllReturnAListOfUserDTO() {
		Mockito.when(service.findAll()).thenReturn(List.of(user));
		Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<List<UserDTO>> response = resource.findAll();
		
		//Assegurar que a resposta não é nula
		Assertions.assertNotNull(response);
		//Assegurar que o corpo da resposta não é nulo
		Assertions.assertNotNull(response.getBody());
		//Assegurar que o status da resposta seja OK
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		//Assegurar que o tipo da resposta = ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		//Assegurar que o corpo da resposta possui um ArrayList
		Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
		//Assegurar que o objeto que está dentro do ArrayList seja do tipo UserDTO
		Assertions.assertEquals(UserDTO.class, response.getBody().get(FIRST_INDEX).getClass());
		//Assegura que os dados do objeto sejam iguais aos dados passados como parâmetro
		Assertions.assertEquals(ID, response.getBody().get(FIRST_INDEX).getId());
		Assertions.assertEquals(NAME, response.getBody().get(FIRST_INDEX).getName());
		Assertions.assertEquals(EMAIL, response.getBody().get(FIRST_INDEX).getEmail());
		Assertions.assertEquals(PASSWORD, response.getBody().get(FIRST_INDEX).getPassword());

	}

	@Test
	void whenCreateReturnCreated() {
		Mockito.when(service.create(Mockito.any())).thenReturn(user);
		
		ResponseEntity<UserDTO> response = resource.create(userDTO);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		//Assegurar que o status da resposta seja CREATED
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		//Assegurar que o cabeçalho da resposta tenha a chave location
		Assertions.assertNotNull(response.getHeaders().LOCATION);

	}

	@Test
	void whenUpdateThenReturnSuccess() {
		Mockito.when(service.update(Mockito.any())).thenReturn(user);
		Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = resource.update(ID, userDTO);
		
		//Assegurar que a resposta não é nula
		Assertions.assertNotNull(response);
		//Assegurar que o corpo da resposta não é nulo
		Assertions.assertNotNull(response.getBody());
		//Assegurar que o tipo da resposta = ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		//Assegurar que o status da resposta seja OK
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		//Assegurar que o objeto que está dentro do corpo da resposta seja do tipo UserDTO
		Assertions.assertEquals(UserDTO.class, response.getBody().getClass());
		//Assegura que os dados do objeto sejam iguais aos dados passados como parâmetro
		Assertions.assertEquals(ID, response.getBody().getId());
		Assertions.assertEquals(NAME, response.getBody().getName());
		Assertions.assertEquals(EMAIL, response.getBody().getEmail());
		
	}

	@Test
	void whenDeleteThenReturnSuccess() {
		Mockito.doNothing().when(service).delete(Mockito.anyInt());
		
		ResponseEntity<UserDTO> response = resource.delete(ID);
		
		//Assegurar que a resposta não é nula
		Assertions.assertNotNull(response);
		//Assegurar que o tipo da resposta = ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		//Assegurar que o status da resposta seja NO_CONTENT
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		//Verificar quantas vezes service delete foi executado
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyInt());

	}
	
	//Inicia os valores das instâncias de usuário
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
		
	}

}
