package com.bercea.assigment2.service.query.write;

import javax.validation.Valid;

import com.bercea.assigment2.model.User;

public interface UserCommandService {

	void saveUser(User user);

	boolean isUserAlreadyPresent(@Valid User user);

	User getUserByEmail(String userMail);
}
