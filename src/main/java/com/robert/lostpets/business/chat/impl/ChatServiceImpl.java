package com.robert.lostpets.business.chat.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.robert.lostpets.business.chat.ChatService;
import com.robert.lostpets.business.chat.validator.ChatValidator;
import com.robert.lostpets.business.util.CodeGenerator;
import com.robert.lostpets.entity.Chat;
import com.robert.lostpets.entity.User;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.persistence.ChatRepository;
import com.robert.lostpets.persistence.UserRepository;

/**
 * Clase que implementa la interfaz ChatService.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.chat.ChatService
 */
@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatValidator validator;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Chat find(Long id) throws BusinessException {
		validator.findById(id);
		return chatRepository.findOne(id);
	}

	@Override
	public List<Chat> findAll() {
		return StreamSupport
				.stream(chatRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Chat save(Chat chat) throws BusinessException {
		validator.save(chat);
		String code = null;

		do {
			code = CodeGenerator.random();
		} while (chatRepository.findByCode(code) != null);

		chat.setCode(code);
		return chatRepository.save(chat);
	}

	@Override
	public Chat update(Chat chat) throws BusinessException {
		validator.update(chat);
		return chatRepository.save(chat);
	}

	@Override
	public Boolean deleteById(Long id) throws BusinessException {
		validator.deleteById(id);
		try {
			chatRepository.delete(id);
			return true;
		} catch (DataAccessException e) {
			throw new BusinessException("id", "chat.delete.id.exception", id);
		}
	}

	@Override
	public List<Chat> findByUserId(Long id) throws BusinessException {
		validator.findByUserId(id);
		User user = userRepository.findOne(id);
		List<Chat> chats = new ArrayList<Chat>();

		if (user == null)
			throw new BusinessException("user", "chat.find.id.invalid");

		for (Chat c : findAll()) {
			if (c.getFromUser().equals(user) || c.getToUser().equals(user)) {
				chats.add(c);
				setChatUser(user, c);
				c.setUnreadMessagesByUser(user);
			}
		}

		Collections.sort(chats, Collections.reverseOrder(
				Comparator.comparing(c -> c.getLastMessage().getDate())));
		return chats;
	}

	private void setChatUser(User user, Chat chat) {
		if (chat.getToUser().equals(user)) {
			chat.setToUser(chat.getFromUser());
			chat.setFromUser(user);
		}
	}
}
