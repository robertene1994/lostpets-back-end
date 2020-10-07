package com.robert.lostpets.business.message.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.robert.lostpets.business.message.MessageService;
import com.robert.lostpets.business.message.validator.MessageValidator;
import com.robert.lostpets.entity.Chat;
import com.robert.lostpets.entity.Message;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.MessageStatus;
import com.robert.lostpets.persistence.ChatRepository;
import com.robert.lostpets.persistence.MessageRepository;

/**
 * Clase que implementa la interfaz MessageService.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.message.MessageService
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageValidator validator;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Message find(Long id) throws BusinessException {
		validator.findById(id);
		return messageRepository.findOne(id);
	}

	@Override
	public List<Message> findAll() {
		return StreamSupport
				.stream(messageRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Message save(Message message) throws BusinessException {
		validator.save(message);
		return messageRepository.save(message);
	}

	@Override
	public Message update(Message message) throws BusinessException {
		validator.update(message);
		return messageRepository.save(message);
	}

	@Override
	public Boolean deleteById(Long id) throws BusinessException {
		validator.deleteById(id);
		try {
			messageRepository.delete(id);
			return true;
		} catch (DataAccessException e) {
			throw new BusinessException("id",
					"message.delete.id.exception", id);
		}
	}

	@Override
	public List<Message> findAllByChatCode(String code)
			throws BusinessException {
		validator.findAllByChatCode(code);
		return messageRepository.findByChatCodeOrderByDateAsc(code);
	}

	@Override
	public List<Message> findAllNotReadByChatCodeAndUserEmail(String code,
			String email) throws BusinessException {
		validator.findAllNotReadByChatCodeAndUserEmail(code, email);
		Iterable<Message> messages = messageRepository
				.findByChatCodeAndToUserEmailAndMessageStatusNot(code, email,
						MessageStatus.READ);

		messages.forEach(m -> m.setMessageStatus(MessageStatus.READ));
		if (messages.iterator().hasNext())
			messageRepository.save(messages);

		return StreamSupport
				.stream(messages.spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Message exchangeMessage(Message message) throws BusinessException {
		validator.exchangeMessage(message);
		Chat chat = chatRepository.findByCode(message.getChat().getCode());
		if (chat == null)
			message.setChat(chatRepository.save(message.getChat()));
		else
			message.getChat().setId(chat.getId());

		message.getChat().setLastMessageCopy(message);
		return messageRepository.save(message);
	}
}
