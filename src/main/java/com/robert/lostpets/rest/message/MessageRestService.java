package com.robert.lostpets.rest.message;

import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robert.lostpets.entity.Message;
import com.robert.lostpets.entity.exception.BusinessException;

/**
 * Interfaz que define las operaciones REST específicas para la entidad
 * "Message" (mensaje).
 * 
 * @author Robert Ene
 */
@RequestMapping("message")
public interface MessageRestService {

	/**
	 * Método que devuelve los mensajes del sistema dado el código de un
	 * determinado chat y el correo electrónico del usuario emisor. Además,
	 * durante el proceso de recuperación de los mensajes, se marcan como
	 * leídos.
	 * 
	 * @param chatCode  el código del chat.
	 * @param userEmail el correo electrónico del usuario emisor.
	 * @return todos los mensajes del chat establecido.
	 */
	@GetMapping("/markAsRead/{chatCode}")
	@ResponseBody
	List<Message> findAllAndMarkAsReadByChatCodeAndUserEmail(
			@PathVariable("chatCode") String chatCode,
			@RequestParam("userEmail") String userEmail)
			throws BusinessException;

	/**
	 * Método que intercambia mensajes entre los usuarios, y los almacena en la
	 * base de datos.
	 * 
	 * @param userEmail el correo electrónico del destinatario del mensaje.
	 * @param message   el mensaje que se intercambia.
	 * @return el mensaje ya procesado.
	 */
	@MessageMapping("/send/chatMessage/{userEmail}")
	@SendTo("/exchange/chatMessage/{userEmail}")
	Message exchangeMessage(@DestinationVariable("userEmail") String userEmail,
			@Payload Message message) throws BusinessException;
}
