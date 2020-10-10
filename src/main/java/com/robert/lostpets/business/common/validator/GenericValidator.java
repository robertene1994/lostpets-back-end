package com.robert.lostpets.business.common.validator;

import java.io.Serializable;

import com.robert.lostpets.entity.exception.BusinessException;

/**
 * Interfaz genérica del validador de entidades del dominio de la aplicación que
 * define las validaciones de las operaciones más comunes para el mantenimiento
 * de las entidades del dominio.
 * 
 * @author Robert Ene
 * 
 * @param <T> el tipo de la entidad.
 * @param <I> el tipo del id de la entidad.
 */
public interface GenericValidator<T, I extends Serializable> {

	/**
	 * Método que valida la operación de encontrar y devolver una entidad dado
	 * su id.
	 * 
	 * @param id el id de la entidad.
	 */
	void findById(I id) throws BusinessException;

	/**
	 * Método que valida la operación de guardar una entidad en el sistema.
	 * 
	 * @param entity la entidad que se guarda.
	 */
	void save(T entity) throws BusinessException;

	/**
	 * Método que valida la operación de modificar los datos de una entidad del
	 * sistema.
	 * 
	 * @param entity la entidad que se modifica.
	 */
	void update(T entity) throws BusinessException;

	/**
	 * Método que valida la operación de eliminar una entidad del sistema dado
	 * su id.
	 * 
	 * @param id el id de la entidad.
	 */
	void deleteById(I id) throws BusinessException;
}
