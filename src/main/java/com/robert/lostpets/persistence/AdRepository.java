package com.robert.lostpets.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.robert.lostpets.entity.Ad;
import com.robert.lostpets.persistence.common.GenericRepository;

/**
 * Interfaz que extiende la interfaz GenericRepository y define las operaciones
 * de acceso a datos específicas para la entidad "Ad" (anuncio).
 * 
 * @author Robert Ene
 * @see com.robert.lostpets.persistence.common.GenericRepository
 */
@Repository
@Transactional(readOnly = true)
public interface AdRepository extends GenericRepository<Ad, Long> {

	/**
	 * Método que devuelve los datos de un determinado anuncio de mascota
	 * perdida dado su código único.
	 * 
	 * @param code el código del anuncio.
	 * @return los datos del anuncio de la masctoa perdida.
	 */
	Ad findByCode(String code);

	/**
	 * Método que devuelve todos los anuncios de mascotas perdidas asociados al
	 * id de un determinado usuario.
	 * 
	 * @param id el id del usuario.
	 * @return los anuncios de mascotas perdidas asociados al usuario
	 *         establecido.
	 */
	List<Ad> findByUserId(Long id);
}
