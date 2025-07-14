package com.radmanhayati.user.service.user;

import com.radmanhayati.user.exception.UserAlreadyExistsException;
import com.radmanhayati.user.exception.UserNotFoundException;
import com.radmanhayati.user.service.user.model.AddUserModel;
import com.radmanhayati.user.service.user.model.UserResult;
import com.radmanhayati.user.service.user.model.UsersResult;

public interface UserService {
	UserResult registerUser(AddUserModel model) throws UserAlreadyExistsException;

	UserResult getUserById(Long userId) throws UserNotFoundException;

	UsersResult getAllUsers();
}
