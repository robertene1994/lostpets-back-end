package com.robert.lostpets.business.user.validator.impl;

import org.springframework.stereotype.Component;

import com.robert.lostpets.business.user.validator.UserValidator;
import com.robert.lostpets.entity.User;
import com.robert.lostpets.entity.dto.AccountCredentials;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.UserStatus;

/**
 * Clase que implementa la interfaz UserValidator.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.user.validator.UserValidator
 */
@Component
public class UserValidatorImpl implements UserValidator {

	@Override
	public void findById(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "user.find.id.required");
	}

	@Override
	public void save(User user) throws BusinessException {
		if (user == null)
			throw new BusinessException("user", "user.save.user.required");
	}

	@Override
	public void update(User user) throws BusinessException {
		if (user == null)
			throw new BusinessException("user", "user.update.user.required");
	}

	@Override
	public void deleteById(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "user.delete.id.required");
	}

	@Override
	public void findUserDetails(String email) throws BusinessException {
		if (email == null || email.trim().isEmpty())
			throw new BusinessException("email", "user.find.email.required");
	}

	@Override
	public void checkUniqueEmail(String email) throws BusinessException {
		if (email == null || email.trim().isEmpty())
			throw new BusinessException("email", "user.unique.email.required");
	}

	@Override
	public void checkUserStatus(String email) throws BusinessException {
		if (email == null || email.trim().isEmpty())
			throw new BusinessException("email", "user.status.email.required");
	}

	@Override
	public void checkValidPassword(AccountCredentials account)
			throws BusinessException {
		if (account == null || account.getEmail().trim().isEmpty())
			throw new BusinessException("email",
					"user.valid.account.email.required");
		if (account == null || account.getPassword().trim().isEmpty())
			throw new BusinessException("password",
					"user.valid.account.password.required");
	}

	@Override
	public void updateUserStatus(String email, UserStatus status)
			throws BusinessException {
		if (email == null || email.trim().isEmpty())
			throw new BusinessException("email", "user.update.email.required");
		if (status == null)
			throw new BusinessException("status",
					"user.update.status.required");
	}
}
