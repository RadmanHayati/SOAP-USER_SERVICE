package com.radmanhayati.user.api.soap;

import com.radmanhayati.user.api.soap.mapper.UserSoapResourceMapper;
import com.radmanhayati.user.exception.UserNotFoundException;
import com.radmanhayati.user.service.user.UserService;
import com.radmanhayati.user.service.user.model.UserResult;
import com.radmanhayati.user.soap.generated.GetUserRequest;
import com.radmanhayati.user.soap.generated.GetUserResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserSoapResource {
	private static final String NAMESPACE_URI = "http://example.com/userservice";

	private final UserService userService;

	private final UserSoapResourceMapper mapper;

	public UserSoapResource(UserService userService, UserSoapResourceMapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest request) throws UserNotFoundException {
		UserResult result = userService.getUserById(request.getUserId());
		return mapper.toUsersResponse(result);
	}
}
