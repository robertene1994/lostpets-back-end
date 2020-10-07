package com.robert.lostpets.rest.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robert.lostpets.entity.User;
import com.robert.lostpets.entity.dto.AccountCredentials;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.UserStatus;

/**
 * Interfaz que define las operaciones REST específicas para la entidad "User"
 * (usuario).
 * 
 * @author Robert Ene
 */
@RequestMapping("user")
public interface UserRestService {

	/**
	 * Método que devuelve los datos de un usuario dado su id.
	 * 
	 * @param id el id del usuario.
	 * @return el usuario asociado al id proporcionado.
	 */
	@GetMapping("{id}")
	@ResponseBody
	User findUserById(@RequestParam("id") Long id) throws BusinessException;

	/**
	 * Método que devuelve los datos de un usuario tras haber iniciado sesión en
	 * el sistema.
	 * 
	 * @param email el correo electrónico del usuario para el que se solicita
	 *              los datos.
	 * @return el usuario que contiene los detalles asociados al nombre de
	 *         usuario establecido.
	 */
	@GetMapping("userDetails")
	@ResponseBody
	User findUserDetailsByEmail(@RequestParam("email") String email)
			throws BusinessException;

	/**
	 * Método que comprueba si un determinado correo electrónico es único en el
	 * sistema.
	 * 
	 * @param email el correo electrónico a comprobar.
	 * @return true si el correo electrónico es único en el sistema, false de lo
	 *         contrario.
	 */
	@GetMapping("uniqueEmail")
	@ResponseBody
	Boolean checkUniqueEmail(@RequestParam("email") String email)
			throws BusinessException;

	/**
	 * Método que comprueba el estado de la cuenta de un determinado usuario.
	 * 
	 * @param email el correo electrónico del usuario para el que se comprueba
	 *              el estado de la cuenta.
	 * @return true si la cuenta del usuario está activada, false de lo
	 *         contrario.
	 */
	@GetMapping("userStatus")
	@ResponseBody
	UserStatus checkUserStatusByEmail(@RequestParam("email") String email)
			throws BusinessException;

	/**
	 * Método que comprueba si una contraseña está asociada a una determinada
	 * cuenta de usuario.
	 * 
	 * @param account las credenciales que se comprueban.
	 * @return true si la contraseña es válida, false de lo contrario.
	 */
	@PostMapping("validPassword")
	@ResponseBody
	Boolean checkValidPassword(@RequestBody AccountCredentials account)
			throws BusinessException;

	/**
	 * Método que registra en el sistema la cuenta de un nuevo usuario.
	 * 
	 * @param user el usuario que se registra en el sistema.
	 * @return el usuario que ya ha sido registrado en el sistema.
	 */
	@PostMapping
	@ResponseBody
	User signUp(@RequestBody User user) throws BusinessException;

	/**
	 * Método que actualiza en el sistema los datos de un determinado usuario.
	 * 
	 * @param user el usuario que se actualiza en el sistema.
	 * @return el usuario que ya ha sido actualizado en el sistema.
	 */
	@PutMapping
	@ResponseBody
	User updateUser(@RequestBody User user) throws BusinessException;

	/**
	 * Método que modifica el estado de un usuario.
	 * 
	 * @param email  el correo electrónico del usuario.
	 * @param status el nuevo estado del usuario.
	 * @return true si el estado del usuario se ha sido modificado, false de lo
	 *         contrario.
	 */
	@PutMapping("userStatus")
	@ResponseBody
	Boolean updateUserStatus(@RequestParam("email") String email,
			@RequestParam("status") UserStatus status) throws BusinessException;

	/**
	 * Método que devuelve todos los usuarios del sistema.
	 * 
	 * @return todas los usuarios del sistema.
	 */
	@GetMapping
	@ResponseBody
	List<User> findAll();
}
