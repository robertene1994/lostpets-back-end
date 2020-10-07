package com.robert.lostpets.business.ad;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.robert.lostpets.business.common.GenericService;
import com.robert.lostpets.entity.Ad;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.AdStatus;

/**
 * Interfaz que extiende la interfaz GenericService y define los servicios
 * específicos para la entidad "Ad" (anuncio).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.business.common.GenericService
 */
public interface AdService extends GenericService<Ad, Long> {

	/**
	 * Método que devuelve los datos de un anuncio en base a su código único.
	 * 
	 * @param code el código del anuncio.
	 * @return los datos del anuncio.
	 */
	Ad findByCode(String code) throws BusinessException;

	/**
	 * Método que devuelve los anuncios de mascotas perdidas del sistema dado el
	 * id de un usuario.
	 * 
	 * @param id el id del usuario.
	 * @return todos los anuncios del usuario establecido.
	 */
	List<Ad> findByUserId(Long id) throws BusinessException;

	/**
	 * Método que almacena un anuncio de una mascota perdida.
	 * 
	 * @param ad    el anuncio de la mascota perdida.
	 * @param image la imagen de la mascota perdida.
	 * @return true si el anuncio ha sido procesado correctamente, false de lo
	 *         contrario.
	 */
	Boolean save(Ad ad, MultipartFile image) throws BusinessException;

	/**
	 * Método que modifica un anuncio de una mascota perdida.
	 * 
	 * @param ad    el anuncio de la mascota perdida.
	 * @param image la imagen de la mascota perdida.
	 * @return true si el anuncio ha sido modificado correctamente, false de lo
	 *         contrario.
	 */
	Boolean update(Ad ad, MultipartFile image) throws BusinessException;

	/**
	 * Método que modifica el estado de un anuncio de una mascota perdida.
	 * 
	 * @param code   el código del anuncio.
	 * @param status el nuevo estado del anuncio.
	 * @return true si el estado del anuncio se ha sido modificado
	 *         correctamente, false de lo contrario.
	 */
	Boolean updateAdStatus(String code, AdStatus status)
			throws BusinessException;
}
