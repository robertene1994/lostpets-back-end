package com.robert.lostpets.business.chat.validator.impl;

import org.springframework.stereotype.Component;

import com.robert.lostpets.business.chat.validator.ChatValidator;
import com.robert.lostpets.entity.Chat;
import com.robert.lostpets.entity.exception.BusinessException;

/**
 * Clase que implementa la interfaz ChatValidator.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.chat.validator.ChatValidator
 */
@Component
public class ChatValidatorImpl implements ChatValidator {

	@Override
	public void findById(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "chat.find.id.required");
	}

	@Override
	public void save(Chat chat) throws BusinessException {
		if (chat == null)
			throw new BusinessException("chat", "chat.save.chat.required");
	}

	@Override
	public void update(Chat chat) throws BusinessException {
		if (chat == null)
			throw new BusinessException("chat", "chat.update.chat.required");
	}

	@Override
	public void deleteById(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "chat.delete.id.required");
	}

	@Override
	public void findByUserId(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "chat.findByUser.id.required");
	}
}
