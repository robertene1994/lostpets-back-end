package com.robert.lostpets.business.chat;

import java.util.List;

import com.robert.lostpets.business.common.GenericService;
import com.robert.lostpets.entity.Chat;
import com.robert.lostpets.entity.exception.BusinessException;

/**
 * Interfaz que extiende la interfaz GenericService y define los servicios
 * específicos para la entidad "Chat" (conversación).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.common.GenericService
 */
public interface ChatService extends GenericService<Chat, Long> {

	/**
	 * Método que devuelve todos los chats pertenecientes a un determinado
	 * usuario dado su id.
	 * 
	 * @param id el id del usuario.
	 * @return los chats asociados al usuario establecido.
	 * @throws BusinessException
	 */
	List<Chat> findByUserId(Long id) throws BusinessException;
}
