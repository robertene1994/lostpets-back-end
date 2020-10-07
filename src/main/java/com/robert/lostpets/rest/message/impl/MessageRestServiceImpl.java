package com.robert.lostpets.rest.message.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.robert.lostpets.business.message.MessageService;
import com.robert.lostpets.entity.Message;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.rest.message.MessageRestService;

/**
 * Clase que implementa la interfaz MessageRestService.
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.rest.message.MessageRestService
 */
@RestController
public class MessageRestServiceImpl implements MessageRestService {

	@Autowired
	private MessageService messageService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Override
	public List<Message> findAllAndMarkAsReadByChatCodeAndUserEmail(
			@PathVariable("chatCode") String chatCode, String userEmail)
			throws BusinessException {
		sendReadMessages(chatCode, userEmail);
		return messageService.findAllByChatCode(chatCode);
	}

	@Override
	public Message exchangeMessage(
			@DestinationVariable("userEmail") String userEmail,
			@Payload Message message) throws BusinessException {
		return messageService.exchangeMessage(message);
	}

	private void sendReadMessages(String chatCode, String userEmail)
			throws BusinessException {
		messageService.findAllNotReadByChatCodeAndUserEmail(chatCode, userEmail)
				.forEach(m -> {
					String email = m.getFromUser().getEmail();
					String destination = "/exchange/chatMessage/" + email;
					messagingTemplate.convertAndSend(destination, m);
				});
	}
}
