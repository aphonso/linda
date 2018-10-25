package be.lindacare.market.aphonso.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.lindacare.market.aphonso.json.serialization.LocalDateTimeDeserializer;
import be.lindacare.market.aphonso.json.serialization.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

@Data // Lombok generate setters/getters, hashCode, equals and toString
@Builder // Builder patternO
public class CurrencyExchangeRate {

	private long userId;
	private String currencyFrom;
	private String currencyTo;
	private float amountSell;
	private float amountBuy;
	private float rate;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timePlaced;

	private String originatingCountry;
}
