package com.robert.lostpets.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.robert.lostpets.entity.Message;
import com.robert.lostpets.entity.types.MessageStatus;
import com.robert.lostpets.persistence.common.GenericRepository;

/**
 * Interfaz que extiende la interfaz GenericRepository y define las operaciones
 * de acceso a datos específicas para la entidad "Message" (mensaje).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.persistence.common.GenericRepository
 */
@Repository
@Transactional(readOnly = true)
public interface MessageRepository extends GenericRepository<Message, Long> {

	/**
	 * Método que devuelve todos los mensajes asociados a un chat dado su código
	 * único, el correo electrónico del destinatario de los mensajes y el estado
	 * de estos últimos.
	 * 
	 * @param code          el código del chat.
	 * @param email         el correo electrónico del destinatario de los
	 *                      mensajes.
	 * @param messageStatus el estado de los mensajes asociados al chat.
	 * @return los mensajes que cumplen los criterios establecidos.
	 */
	List<Message> findByChatCodeAndToUserEmailAndMessageStatusNot(String code,
			String email, MessageStatus messageStatus);

	/**
	 * Método que devuelve todos los mensajes asociados a un determinado chat,
	 * dado su código único. Además, los mensajes se ordenan de forma ascendente
	 * por la fecha en la que fueron enviados.
	 * 
	 * @param code el código del chat.
	 * @return los mensajes ordenados por fecha, asociados al chat establecido.
	 */
	List<Message> findByChatCodeOrderByDateAsc(String code);
}
