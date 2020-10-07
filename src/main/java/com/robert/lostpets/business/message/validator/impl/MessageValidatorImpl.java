package com.robert.lostpets.business.message.validator.impl;

import org.springframework.stereotype.Component;

import com.robert.lostpets.business.message.validator.MessageValidator;
import com.robert.lostpets.entity.Message;
import com.robert.lostpets.entity.exception.BusinessException;

/**
 * Clase que implementa la interfaz MessageValidator.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.message.validator.MessageValidator
 */
@Component
public class MessageValidatorImpl implements MessageValidator {

	@Override
	public void findById(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "message.find.id.required");
	}

	@Override
	public void save(Message message) throws BusinessException {
		if (message == null)
			throw new BusinessException("message",
					"message.save.message.required");
	}

	@Override
	public void update(Message message) throws BusinessException {
		if (message == null)
			throw new BusinessException("message",
					"message.update.message.required");
	}

	@Override
	public void deleteById(Long id) throws BusinessException {
		if (id == null)
			throw new BusinessException("id", "message.delete.id.required");
	}

	@Override
	public void findAllByChatCode(String code) throws BusinessException {
		if (code == null || code.trim().isEmpty())
			throw new BusinessException("code",
					"message.findAll.code.required");
	}

	@Override
	public void findAllNotReadByChatCodeAndUserEmail(String code, String email)
			throws BusinessException {
		if (code == null || code.trim().isEmpty())
			throw new BusinessException("code",
					"message.findAllNotRead.code.required");
		if (email == null || email.trim().isEmpty())
			throw new BusinessException("email",
					"message.findAllNotRead.email.required");
	}

	@Override
	public void exchangeMessage(Message message) throws BusinessException {
		if (message == null)
			throw new BusinessException("message",
					"message.exchange.message.required");
		if (message.getChat() == null)
			throw new BusinessException("message",
					"message.exchange.message.chat.required");
		if (message.getChat().getCode() == null
				|| message.getChat().getCode().isEmpty())
			throw new BusinessException("message",
					"message.exchange.message.chat.code.required");
	}
}
