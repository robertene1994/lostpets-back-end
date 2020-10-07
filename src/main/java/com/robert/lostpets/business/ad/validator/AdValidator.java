package com.robert.lostpets.business.ad.validator;

import org.springframework.web.multipart.MultipartFile;

import com.robert.lostpets.business.common.validator.GenericValidator;
import com.robert.lostpets.entity.Ad;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.AdStatus;

/**
 * Interfaz que extiende la interfaz GenericValidator y define las validaciones
 * de las operaciones específicas para la entidad "Ad" (anuncio).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.common.validator.GenericValidator
 */
public interface AdValidator extends GenericValidator<Ad, Long> {

	/**
	 * Método que valida la operación de encontrar y devolver los datos de un
	 * anuncio en base a su código único
	 * 
	 * @param code el código del anuncio.
	 */
	void findByCode(String code) throws BusinessException;

	/**
	 * Método que valida la operación de encontrar y devolver los anuncios de
	 * mascotas perdidas del sistema dado el id de un usuario.
	 * 
	 * @param id el id del usuario.
	 */
	void findByUserId(Long id) throws BusinessException;

	/**
	 * Método que valida la operación de almacenar un anuncio de una mascota
	 * perdida.
	 * 
	 * @param ad    el anuncio de la mascota perdida.
	 * @param image la imagen de la mascota perdida.
	 */
	void save(Ad ad, MultipartFile image) throws BusinessException;

	/**
	 * Método que valida la operación de modificar un anuncio de una mascota
	 * perdida.
	 * 
	 * @param ad    el anuncio de la mascota perdida.
	 * @param image la imagen de la mascota perdida.
	 */
	void update(Ad ad, MultipartFile image) throws BusinessException;

	/**
	 * Método que valida la operación de modificar el estado de un anuncio de
	 * una mascota perdida.
	 * 
	 * @param code   el código del anuncio.
	 * @param status el nuevo estado del anuncio.
	 */
	void updateAdStatus(String code, AdStatus status) throws BusinessException;
}
