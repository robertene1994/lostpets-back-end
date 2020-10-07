package com.robert.lostpets.business.user.validator;

import com.robert.lostpets.business.common.validator.GenericValidator;
import com.robert.lostpets.entity.User;
import com.robert.lostpets.entity.dto.AccountCredentials;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.UserStatus;

/**
 * Interfaz que extiende la interfaz GenericValidator y define las validaciones
 * de las operaciones específicas para la entidad "User" (usuario).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.common.validator.GenericValidator
 */
public interface UserValidator extends GenericValidator<User, Long> {

	/**
	 * Método que valida la operación de encontrar y devolver un usuario del
	 * sistema dado su correo electrónico.
	 * 
	 * @param email el correo electróncio del usuario.
	 */
	void findUserDetails(String email) throws BusinessException;

	/**
	 * Método que valida la operación de comprobar si un determinado correo
	 * electrónico es único en el sistema.
	 * 
	 * @param email el correo electrónico que se comprueba.
	 */
	void checkUniqueEmail(String email) throws BusinessException;

	/**
	 * Método que valida la operación de encontrar y devolver el estado de la
	 * cuenta de un determinado usuario del sistema.
	 * 
	 * @param email el correo electrónico del usuario.
	 */
	void checkUserStatus(String email) throws BusinessException;

	/**
	 * Método que valida la operación de comprobar si una determinada contraseña
	 * está asociada a la cuenta de un usuario.
	 * 
	 * @param account las credenciales del usuario.
	 */
	void checkValidPassword(AccountCredentials account)
			throws BusinessException;

	/**
	 * Método que valida la operación de modificar el estado de la cuenta de un
	 * determinado usuario.
	 * 
	 * @param email  el correo electrónico del usuario.
	 * @param status el nuevo estado de la cuenta del usuario.
	 */
	void updateUserStatus(String email, UserStatus status)
			throws BusinessException;
}
