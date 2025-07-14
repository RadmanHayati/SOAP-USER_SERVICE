package com.radmanhayati.user.api.soap.mapper;

import com.radmanhayati.user.common.ResultStatus;
import com.radmanhayati.user.service.user.model.UserResult;
import com.radmanhayati.user.soap.generated.GetUserResponse;
import com.radmanhayati.user.soap.generated.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = { ResultStatus.class })
public interface UserSoapResourceMapper {

	@Mapping(target = "user", expression = "java(toUser(result))")
	GetUserResponse toUsersResponse(UserResult result);

	default User toUser(UserResult result) {
		var user = new User();
		user.setId(result.getUser()
				.getId());
		user.setName(result.getUser()
				.getName());
		user.setEmail(result.getUser()
				.getEmail());
		return user;
	}

}
