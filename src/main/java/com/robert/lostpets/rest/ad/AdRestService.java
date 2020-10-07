package com.robert.lostpets.rest.ad;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.robert.lostpets.entity.Ad;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.types.AdStatus;

/**
 * Interfaz que define las operaciones REST específicas para la entidad "Ad"
 * (anuncio).
 * 
 * @author Robert Ene
 */
@RequestMapping("ad")
public interface AdRestService {

	/**
	 * Método que devuelve los datos de un anuncio en base a su código único.
	 * 
	 * @param code el código del anuncio.
	 * @return los datos del anuncio.
	 */
	@GetMapping("{code}")
	@ResponseBody
	Ad findByCode(@PathVariable("code") String code) throws BusinessException;

	/**
	 * Método que devuelve los anuncios de mascotas perdidas del sistema dado el
	 * id de un usuario.
	 * 
	 * @param userId el id del usuario.
	 * @return todos los anuncios del usuario establecido.
	 */
	@GetMapping("user/{userId}")
	@ResponseBody
	List<Ad> findByUserId(@PathVariable("userId") Long userId) throws BusinessException;

	/**
	 * Método que devuelve todos los anuncios de mascotas perdidas.
	 * 
	 * @return todas los anuncios de mascotas perdidas del sistema.
	 */
	@GetMapping
	@ResponseBody
	List<Ad> findAll();

	/**
	 * Método que almacena un anuncio de una mascota perdida
	 * 
	 * @param ad    el anuncio de la mascota perdida.
	 * @param image la imagen de la mascota perdida.
	 * @return true si el anuncio ha sido procesado correctamente, false de lo
	 *         contrario.
	 */
	@PostMapping
	@ResponseBody
	Boolean save(@RequestPart("ad") Ad ad,
			@RequestPart("image") MultipartFile image) throws BusinessException;

	/**
	 * Método que modifica un anuncio de una mascota perdida.
	 * 
	 * @param ad    el anuncio de la mascota perdida.
	 * @param image la imagen de la mascota perdida.
	 * @return true si el anuncio ha sido modificado correctamente, false de lo
	 *         contrario.
	 */
	@PutMapping
	@ResponseBody
	Boolean update(@RequestPart("ad") Ad ad,
			@RequestPart("image") MultipartFile image) throws BusinessException;

	/**
	 * Método que modifica el estado de un anuncio de una mascota perdida.
	 * 
	 * @param code   el código del anuncio.
	 * @param status el nuevo estado del anuncio.
	 * @return true si el estado del anuncio se ha sido modificado
	 *         correctamente, false de lo contrario.
	 */
	@PutMapping("adStatus/{code}")
	@ResponseBody
	Boolean updateAdStatus(@PathVariable("code") String code,
			@RequestParam("status") AdStatus status) throws BusinessException;
}
