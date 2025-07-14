package com.radmanhayati.user.exception;


import com.radmanhayati.user.common.ResultStatus;

public class UserNotFoundException extends BusinessException {

	public UserNotFoundException(String message) {
		super(message);
	}

	@Override
	public ResultStatus getResultStatus() {
		return ResultStatus.USER_NOT_FOUND;
	}
}