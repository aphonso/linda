package be.lindacare.currency.market.aphonso.json.serialization;

import java.io.IOException;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import be.lindacare.currency.market.aphonso.config.JsonConfig;

/**
 * 
 * @author aphonso
 * 
 * Allows the JSON Deserialization to have LocalDateTime custom formatter
 *
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		return LocalDateTime.parse(p.getValueAsString(), JsonConfig.DATE_TIME_FORMATTER);
	}
}
