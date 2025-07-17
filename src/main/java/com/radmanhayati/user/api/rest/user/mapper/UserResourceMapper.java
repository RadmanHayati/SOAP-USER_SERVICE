package com.radmanhayati.user.api.rest.user.mapper;

import com.radmanhayati.user.api.rest.user.model.request.RegisterUserRequest;
import com.radmanhayati.user.api.rest.user.model.response.UserDto;
import com.radmanhayati.user.api.rest.user.model.response.UserResponse;
import com.radmanhayati.user.api.rest.user.model.response.UsersResponse;
import com.radmanhayati.user.common.ResultStatus;
import com.radmanhayati.user.service.user.model.AddUserModel;
import com.radmanhayati.user.service.user.model.UserInfo;
import com.radmanhayati.user.service.user.model.UserResult;
import com.radmanhayati.user.service.user.model.UsersResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = { ResultStatus.class })
public interface UserResourceMapper {
	@Mapping(target = "users", source = "users")
	@Mapping(target = "result", expression = "java(ResultStatus.SUCCESS)")
	UsersResponse toUsersResponse(UsersResult result);

	@Mapping(target = "name", source = "name")
	@Mapping(target = "email", source = "email")
	@Mapping(target = "id", source = "id")
	UserDto toUserDto(UserInfo user);

	@Mapping(target = "user", source = "user")
	@Mapping(target = "result", expression = "java(ResultStatus.SUCCESS)")
	UserResponse toUserResponse(UserResult result);

	@Mapping(target = "name", source = "name")
	@Mapping(target = "email", source = "email")
	AddUserModel toAddUserModel(RegisterUserRequest request);
}
