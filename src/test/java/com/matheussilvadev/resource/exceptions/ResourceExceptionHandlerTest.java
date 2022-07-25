package com.matheussilvadev.resource.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.matheussilvadev.services.exceptions.DataIntegrityViolationException;
import com.matheussilvadev.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ResourceExceptionHandlerTest {
	
	@InjectMocks
	private ResourceExceptionHandler resourceExceptionHandler;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
		ResponseEntity<StandardError> response = resourceExceptionHandler.objectNotFound(new ObjectNotFoundException("Objeto não encontrado"),
				new MockHttpServletRequest());
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals("Objeto não encontrado", response.getBody().getError());
		assertEquals(404, response.getBody().getStatus());
	}

	@Test
	void whenDataIntegrityViolationExceptionThenReturnAResponseEntity() {
		ResponseEntity<StandardError> response = resourceExceptionHandler.dataIntegrityViolationException(new DataIntegrityViolationException("E-mail já cadastrado"),
				new MockHttpServletRequest());
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals("E-mail já cadastrado", response.getBody().getError());
		assertEquals(400, response.getBody().getStatus());
	}

}
