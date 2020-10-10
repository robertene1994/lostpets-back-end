package com.robert.lostpets.rest.common.exception;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.robert.lostpets.business.common.i18n.I18nMessageResolver;
import com.robert.lostpets.entity.exception.BusinessException;
import com.robert.lostpets.entity.exception.ExceptionResponse;

/**
 * Clase que representa el manejador de excepciones para toda la aplicación. Es
 * el punto central en el que se atrapan y devuelven las excepciones producidas
 * en el sistema.
 * 
 * @author Robert Ene
 */
@ControllerAdvice
public class LostPetsExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LostPetsExceptionHandler.class);

	@Autowired
	private I18nMessageResolver messageResolver;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> exception(Exception e) {
		ExceptionResponse eR = new ExceptionResponse();
		eR.setField("");
		eR.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		eR.setException(e.getClass().getSimpleName());
		eR.setMessage(e.getMessage());
		LOGGER.error("ERROR: {0}", e);
		return new ResponseEntity<>(eR, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ServletException.class)
	public @ResponseBody ResponseEntity<ExceptionResponse> servletException(ServletException e) {
		ExceptionResponse eR = new ExceptionResponse();
		eR.setField("");
		eR.setCode(HttpStatus.BAD_REQUEST.toString());
		eR.setException(e.getClass().getSimpleName());
		eR.setMessage(e.getLocalizedMessage());
		LOGGER.error("ERROR: {0}", e);
		return new ResponseEntity<>(eR, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionResponse> httpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		ExceptionResponse eR = new ExceptionResponse();
		eR.setField("");
		eR.setCode(HttpStatus.METHOD_NOT_ALLOWED.toString());
		eR.setException(e.getClass().getSimpleName());
		eR.setMessage(e.getMessage());
		return new ResponseEntity<>(eR, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public @ResponseBody ResponseEntity<ExceptionResponse> typeMismatchException(
			MethodArgumentTypeMismatchException e) {
		ExceptionResponse eR = new ExceptionResponse();
		eR.setField(e.getName());
		eR.setCode(HttpStatus.NOT_ACCEPTABLE.toString());
		eR.setException(e.getClass().getSimpleName());
		eR.setMessage(e.getMessage());
		return new ResponseEntity<>(eR, HttpStatus.OK);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionResponse> businessException(BusinessException e) {
		ExceptionResponse eR = new ExceptionResponse();
		eR.setField(e.getField());
		eR.setCode(HttpStatus.BAD_REQUEST.toString());
		eR.setException(e.getClass().getSimpleName());
		eR.setMessage(messageResolver.getMessage(e.getKey(), e.getArgs()));
		return new ResponseEntity<>(eR, HttpStatus.BAD_REQUEST);
	}
}
