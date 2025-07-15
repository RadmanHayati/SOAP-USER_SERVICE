package com.radmanhayati.user;

import com.radmanhayati.user.api.rest.user.model.request.RegisterUserRequest;
import com.radmanhayati.user.api.rest.user.model.response.UserResponse;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UserControllerIT extends AbstractContainerBaseTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void whenCreateUser_thenReturns201() {
		RegisterUserRequest request = new RegisterUserRequest();
		request.setEmail("test@example.com");
		request.setName("Test User");

		ResponseEntity<UserResponse> response = restTemplate.postForEntity(
				"/api/users",
				new HttpEntity<>(request),
				UserResponse.class
		);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("test@example.com", response.getBody()
				.getUser()
				.getEmail());
		assertEquals("Test User", response.getBody()
				.getUser()
				.getName());
		assertFalse(response.getBody()
				.getUser()
				.getName()
				.isEmpty(), "User Id should not be empty after save");
	}
}