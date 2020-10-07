package com.robert.lostpets.business.user.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.robert.lostpets.business.user.UserService;
import com.robert.lostpets.business.user.validator.UserValidator;
import com.robert.lostpets.entity.User;
import com.robert.lostpets.entity.dto.AccountCredentials;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.UserStatus;
import com.robert.lostpets.persistence.UserRepository;

/**
 * Clase que implementa la interfaz UserService.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.user.UserService
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserValidator validator;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User find(Long id) throws BusinessException {
		validator.findById(id);
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return StreamSupport
				.stream(userRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public User save(User user) throws BusinessException {
		validator.save(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User update(User user) throws BusinessException {
		validator.update(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Boolean deleteById(Long id) throws BusinessException {
		validator.deleteById(id);
		try {
			userRepository.delete(id);
			return true;
		} catch (DataAccessException e) {
			throw new BusinessException("id", "user.delete.id.exception", id);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String userName) {
		return userRepository.findByEmail(userName);
	}

	@Override
	public User findUserDetails(String email) throws BusinessException {
		validator.findUserDetails(email);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new BusinessException("email", "user.find.email.invalid");
		return user;
	}

	@Override
	public Boolean checkUniqueEmail(String email) throws BusinessException {
		validator.checkUniqueEmail(email);
		return userRepository.findByEmail(email) == null;
	}

	@Override
	public UserStatus checkUserStatus(String email) throws BusinessException {
		validator.checkUserStatus(email);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new BusinessException("email", "user.status.email.invalid");
		return user.getStatus();
	}

	@Override
	public Boolean checkValidPassword(AccountCredentials account)
			throws BusinessException {
		validator.checkValidPassword(account);
		User user = userRepository.findByEmail(account.getEmail());
		if (user == null)
			throw new BusinessException("email", "user.valid.email.invalid");
		return passwordEncoder.matches(account.getPassword(), user.getPassword());
	}

	@Override
	public Boolean updateUserStatus(String email, UserStatus status)
			throws BusinessException {
		validator.updateUserStatus(email, status);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new BusinessException("user", "user.update.email.invalid");
		user.setStatus(status);
		userRepository.save(user);
		return true;
	}
}
