package com.robert.lostpets.rest.chat;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robert.lostpets.entity.Chat;
import com.robert.lostpets.entity.exception.BusinessException;

/**
 * Interfaz que define las operaciones REST específicas para la entidad "Chat"
 * (conversación).
 * 
 * @author Robert Ene
 */
@RequestMapping("chat")
public interface ChatRestService {

	/**
	 * Método que devuelve los chats del sistema dado el id de un usuario.
	 * 
	 * @param userId el id del usuario.
	 * @return todos los chats del usuario establecido.
	 */
	@GetMapping("user/{userId}")
	@ResponseBody
	List<Chat> findByUserId(@PathVariable("userId") Long userId)
			throws BusinessException;
}
