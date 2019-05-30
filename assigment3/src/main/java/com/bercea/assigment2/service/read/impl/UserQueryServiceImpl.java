package com.bercea.assigment2.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bercea.assigment2.model.User;
import com.bercea.assigment2.repository.UserRepository;
import com.bercea.assigment2.service.read.UserQueryService;

@Service
@Transactional
public class UserQueryServiceImpl implements UserQueryService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email).get();
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
