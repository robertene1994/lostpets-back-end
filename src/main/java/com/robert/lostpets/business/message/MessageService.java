package com.robert.lostpets.business.message;

import java.util.List;

import com.robert.lostpets.business.common.GenericService;
import com.robert.lostpets.entity.Message;
import com.robert.lostpets.entity.exception.BusinessException;

/**
 * Interfaz que extiende la interfaz GenericService y define los servicios
 * específicos para la entidad "Message" (mensaje).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.common.GenericService
 */
public interface MessageService extends GenericService<Message, Long> {

	/**
	 * Método que devuelve todos los mensajes asociados a un chat dado su código
	 * único y el correo electrónico del destinatario de los mensajes.
	 * 
	 * @param code  el código del chat.
	 * @param email el correo electrónico del destinatario de los mensajes.
	 * @return los mensajes que cumplen los criterios establecidos.
	 */
	List<Message> findAllNotReadByChatCodeAndUserEmail(String code,
			String email) throws BusinessException;

	/**
	 * Método que devuelve todos los mensajes asociados a un chat dado su código
	 * único.
	 * 
	 * @param code el código del chat.
	 * @return los mensajes asociados al chat establecido.
	 */
	List<Message> findAllByChatCode(String code) throws BusinessException;

	/**
	 * Método que procesa un determinado mensaje intercambiado entre los
	 * usuarios.
	 * 
	 * @param message el mensaje que se procesa.
	 * @return el mensaje que ya ha sido procesado.
	 */
	Message exchangeMessage(Message message) throws BusinessException;
}
