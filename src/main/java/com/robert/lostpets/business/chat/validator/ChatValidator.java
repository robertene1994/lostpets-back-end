package com.robert.lostpets.business.chat.validator;

import com.robert.lostpets.business.common.validator.GenericValidator;
import com.robert.lostpets.entity.Chat;
import com.robert.lostpets.entity.exception.BusinessException;

/**
 * Interfaz que extiende la interfaz GenericValidator y define las validaciones
 * de las operaciones específicas para la entidad "Chat" (conversación).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.common.validator.GenericValidator
 */
public interface ChatValidator extends GenericValidator<Chat, Long> {

	/**
	 * Método que valida la operación de encontrar y devolver todos los chats
	 * pertenecientes a un determinado usuario dado su id.
	 * 
	 * @param id el id del usuario.
	 */
	void findByUserId(Long id) throws BusinessException;
}
