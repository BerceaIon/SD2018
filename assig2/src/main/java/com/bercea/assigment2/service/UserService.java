package com.bercea.assigment2.service;

import com.bercea.assigment2.model.User;

public interface UserService {

	public void saveUser(User user);
	
	public boolean isUserAlreadyPresent(User user);
}
