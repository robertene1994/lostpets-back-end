package com.robert.lostpets.business.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.robert.lostpets.business.common.GenericService;
import com.robert.lostpets.entity.User;
import com.robert.lostpets.entity.dto.AccountCredentials;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.UserStatus;

/**
 * Interfaz que extiende las interfaces GenericService y UserDetailsService, y
 * define los servicios específicos para la entidad "User" (usuario).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.common.GenericService
 * @see org.springframework.security.core.userdetails.UserDetailsService
 */
public interface UserService
		extends GenericService<User, Long>, UserDetailsService {

	/**
	 * Método que devuelve los datos de un usuario tras haber iniciado sesión en
	 * el sistema.
	 * 
	 * @param email el correo electrónico de usuario para el que se solicita los
	 *              datos.
	 * @return el usuario que contiene los detalles asociados al nombre de
	 *         usuario establecido.
	 */
	User findUserDetails(String email) throws BusinessException;

	/**
	 * Método que comprueba si un determinado correo electrónico es único en el
	 * sistema.
	 * 
	 * @param email el correo electrónico a comprobar.
	 * @return true si el correo electrónico es único en el sistema, false de lo
	 *         contrario.
	 */
	Boolean checkUniqueEmail(String email) throws BusinessException;

	/**
	 * Método que comprueba si una contraseña está asociada a una determinada
	 * cuenta de usuario.
	 * 
	 * @param account las credenciales que se comprueban.
	 * @return true si la contraseña es válida, false de lo contrario.
	 */
	Boolean checkValidPassword(AccountCredentials account)
			throws BusinessException;

	/**
	 * Método que comprueba el estado de la cuenta de un determinado usuario.
	 * 
	 * @param email el correo electrónico del usuario para el que se comprueba
	 *              el estado de la cuenta.
	 * @return true si la cuenta del usuario está activada, false de lo
	 *         contrario.
	 */
	UserStatus checkUserStatus(String email) throws BusinessException;

	/**
	 * Método que modifica el estado de un usuario.
	 * 
	 * @param email  el correo electrónico del usuario.
	 * @param status el nuevo estado del usuario.
	 * @return true si el estado del usuario se ha sido modificado, false de lo
	 *         contrario.
	 * @throws BusinessException
	 */
	Boolean updateUserStatus(String email, UserStatus status)
			throws BusinessException;
}
