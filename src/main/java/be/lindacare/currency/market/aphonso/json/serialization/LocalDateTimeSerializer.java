package be.lindacare.currency.market.aphonso.json.serialization;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import be.lindacare.currency.market.aphonso.config.JsonConfig;

/**
 * 
 * @author aphonso
 * 
 * Allows the JSON Serialization to have LocalDateTime custom formatter
 *
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
	
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.format(JsonConfig.DATE_TIME_FORMATTER));
    }
}
