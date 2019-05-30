package com.bercea.assigment2.service.read;

import com.bercea.assigment2.model.User;

public interface UserQueryService {

	User getUserByEmail(String email);
		
	boolean isUserAlreadyPresent(User user);
}
