package com.bercea.assigment2.service.query.write.impl;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bercea.assigment2.model.Role;
import com.bercea.assigment2.model.User;
import com.bercea.assigment2.repository.RoleRepository;
import com.bercea.assigment2.repository.UserRepository;
import com.bercea.assigment2.service.query.write.UserCommandService;

public class UserCommandServiceImpl implements UserCommandService{
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	
}
