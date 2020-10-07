package com.robert.lostpets.rest.chat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.robert.lostpets.business.chat.ChatService;
import com.robert.lostpets.entity.Chat;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.rest.chat.ChatRestService;

/**
 * Clase que implementa la interfaz ChatRestService.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.rest.chat.ChatRestService
 */
@RestController
public class ChatRestServiceImpl implements ChatRestService {

	@Autowired
	private ChatService chatService;

	@Override
	public List<Chat> findByUserId(@PathVariable("userId") Long userId)
			throws BusinessException {
		return chatService.findByUserId(userId);
	}
}
