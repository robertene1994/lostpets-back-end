package com.robert.lostpets.entity.dto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateDeserializer extends JsonDeserializer<Date> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MMM dd, yyyy HH:mm:ss", Locale.ENGLISH);

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String str = null;
		try {
			str = p.getText().trim();
			return (str != null && !str.isEmpty()) ? dateFormat.parse(str)
					: null;
		} catch (Exception e) {
			try {
				return new Date(Long.parseLong(str));
			} catch (Exception ex) {
				return null;
			}
		}
	}
}
