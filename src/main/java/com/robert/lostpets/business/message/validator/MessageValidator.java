package com.robert.lostpets.business.message.validator;

import com.robert.lostpets.business.common.validator.GenericValidator;
import com.robert.lostpets.entity.Message;
import com.robert.lostpets.entity.exception.BusinessException;

/**
 * Interfaz que extiende la interfaz GenericValidator y define las validaciones
 * de las operaciones específicas para la entidad "Message" (mensaje).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.common.validator.GenericValidator
 */
public interface MessageValidator extends GenericValidator<Message, Long> {

	/**
	 * Método que valida la operación de encontrar y devolver todos los mensajes
	 * del sistema asociado al código de un determinado chat.
	 * 
	 * @param code el código del chat.
	 */
	void findAllByChatCode(String code) throws BusinessException;

	/**
	 * Método que valida la operación de encontrar y devolver todos los mensajes
	 * asociados a un chat dado su código único y el correo electrónico del
	 * destinatario de los mensajes.
	 * 
	 * @param code  el código del chat.
	 * @param email el correo electrónico del destinatario de los mensajes.
	 */
	void findAllNotReadByChatCodeAndUserEmail(String code, String email)
			throws BusinessException;

	/**
	 * Método que valida la operación de procesar un determinado mensaje
	 * intercambiado entre los usuarios.
	 * 
	 * @param message el mensaje que se procesa.
	 */
	void exchangeMessage(Message message) throws BusinessException;
}
