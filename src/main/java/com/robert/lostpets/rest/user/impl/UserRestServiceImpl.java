package com.robert.lostpets.rest.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.robert.lostpets.business.user.UserService;
import com.robert.lostpets.entity.User;
import com.robert.lostpets.entity.dto.AccountCredentials;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.UserStatus;
import com.robert.lostpets.rest.user.UserRestService;

/**
 * Clase que implementa la interfaz UserRestService.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.rest.user.UserRestService
 */
@RestController
public class UserRestServiceImpl implements UserRestService {

	@Autowired
	private UserService userService;

	@Override
	public User findUserById(@PathVariable("id") Long id) throws BusinessException {
		return userService.find(id);
	}
	@Override
	public User findUserDetailsByEmail(String email) throws BusinessException {
		return userService.findUserDetails(email);
	}

	@Override
	public Boolean checkUniqueEmail(String email) throws BusinessException {
		return userService.checkUniqueEmail(email);
	}

	@Override
	public Boolean checkValidPassword(@RequestBody AccountCredentials account)
			throws BusinessException {
		return userService.checkValidPassword(account);
	}
	
	@Override
	public UserStatus checkUserStatusByEmail(String email) throws BusinessException {
		return userService.checkUserStatus(email);
	}
	
	@Override
	public User signUp(@RequestBody User user) throws BusinessException {
		return userService.save(user);
	}

	@Override
	public User updateUser(@RequestBody User user) throws BusinessException {
		return userService.update(user);
	}
	
	@Override
	public Boolean updateUserStatus(String email, UserStatus status)
			throws BusinessException {
		return userService.updateUserStatus(email, status);
	}

	@Override
	public List<User> findAll() {
		return userService.findAll();
	}
}
