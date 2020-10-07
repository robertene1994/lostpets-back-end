package com.robert.lostpets.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.robert.lostpets.entity.User;
import com.robert.lostpets.persistence.common.GenericRepository;

/**
 * Interfaz que extiende la interfaz GenericRepository y define las operaciones
 * de acceso a datos específicas para la entidad "User" (usuario).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.persistence.common.GenericRepository
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends GenericRepository<User, Long> {

	/**
	 * Método que devuelve los datos de un determinado usuario dado su correo
	 * electrónico.
	 * 
	 * @param email el correo electrónico del usuario.
	 * @return los datos del usuario.
	 */
	User findByEmail(String email);
}
