package com.radmanhayati.user.service.user.impl;

import java.util.stream.Collectors;

import com.radmanhayati.user.exception.UserAlreadyExistsException;
import com.radmanhayati.user.exception.UserNotFoundException;
import com.radmanhayati.user.model.user.User;
import com.radmanhayati.user.model.user.dao.UserDao;
import com.radmanhayati.user.service.user.UserService;
import com.radmanhayati.user.service.user.mapper.UserServiceMapper;
import com.radmanhayati.user.service.user.model.AddUserModel;
import com.radmanhayati.user.service.user.model.UserResult;
import com.radmanhayati.user.service.user.model.UsersResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	private final UserServiceMapper mapper;

	@Override
	public UserResult registerUser(AddUserModel model) throws UserAlreadyExistsException {
		if (userDao.existsByEmail(model.getEmail())) {
			throw new UserAlreadyExistsException("User with email " + model.getEmail() + " already exists");
		}
		var user = mapper.toUser(model);
		User savedUser = userDao.save(user);
		return mapper.toUserResult(savedUser);
	}

	@Override
	public UserResult getUserById(Long userId) throws UserNotFoundException {
		User user = userDao.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
		return mapper.toUserResult(user);
	}

	@Override
	public UsersResult getAllUsers() {
		return mapper.toUsersResult(userDao.findAll()
				.stream()
				.map(mapper::toUserInfo)
				.collect(Collectors.toList()));
	}
}
