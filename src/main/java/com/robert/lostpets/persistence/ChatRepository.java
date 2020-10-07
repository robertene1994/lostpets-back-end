package com.robert.lostpets.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.robert.lostpets.entity.Chat;
import com.robert.lostpets.persistence.common.GenericRepository;

/**
 * Interfaz que extiende la interfaz GenericRepository y define las operaciones
 * de acceso a datos específicas para la entidad "Chat" (chat).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.persistence.common.GenericRepository
 */
@Repository
@Transactional(readOnly = true)
public interface ChatRepository extends GenericRepository<Chat, Long> {

	/**
	 * Método que devuelve los datos de un determinado chat dado su código
	 * único.
	 * 
	 * @param code el código del chat.
	 * @return los datos del chat.
	 */
	Chat findByCode(String code);
}
