package com.robert.lostpets.business.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Clase de utilidades que permite generar códigos alfanuméricos aleatorios.
 *
 * @author Robert Ene
 */
public class CodeGenerator {

	private static final String CODE_GENERATOR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final Integer CODE_LENGTH_MIN = 5;
	private static final Integer CODE_LENGTH_MAX = 30;
	public static final Integer CODE_LENGTH_DEFAULT = 10;

	public static String random() {
		return random(CODE_LENGTH_DEFAULT);
	}

	/**
	 * Método que genera de forma aleatoria un código alfanumérico basado en una
	 * longitud determinada.
	 *
	 * @param codeLength la longitud del código generado, que tiene que ser un
	 *                   valor comprendido entre los valores mínimo y máximo
	 *                   definidos.
	 * @return el código alfanumérico generado de forma aleatoria.
	 */
	public static String random(Integer codeLength) {
		if (codeLength == null || codeLength < CODE_LENGTH_MIN
				|| codeLength > CODE_LENGTH_MAX)
			codeLength = CODE_LENGTH_DEFAULT;

		Random r = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < codeLength; i++)
			sb.append(
					CODE_GENERATOR.charAt(r.nextInt(CODE_GENERATOR.length())));
		return sb.toString();
	}
}
