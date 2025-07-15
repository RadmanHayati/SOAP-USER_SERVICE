package com.radmanhayati.user;

import java.util.List;

import com.radmanhayati.user.model.user.User;
import com.radmanhayati.user.model.user.dao.UserDao;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class UserRepositoryIT extends AbstractContainerBaseTest {

	@Autowired
	private UserDao userDao;

	@Test
	void whenSaveUser_thenCanRetrieve() {
		User user = new User();
		user.setEmail("test@example.com");
		user.setName("Test User");

		User savedUser = userDao.save(user);
		List<User> users = userDao.findAll();

		assertFalse(users.isEmpty(), "User list should not be empty after save");
		assertEquals("Retrieved user email should match saved user email", "test@example.com", users.get(0)
				.getEmail());
		assertEquals("Retrieved user ID should match saved user ID", savedUser.getId(), users.get(0).getId());
	}
}