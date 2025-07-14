package com.radmanhayati.user.api.rest.user.model.response;

import java.util.List;

import com.radmanhayati.user.common.ResponseService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class UsersResponse extends ResponseService {

	private List<UserDto> users;

}
