package be.lindacare.currency.market.aphonso.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.lindacare.currency.market.aphonso.json.serialization.LocalDateTimeDeserializer;
import be.lindacare.currency.market.aphonso.json.serialization.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author aphonso
 * 
 * POJO to store the currency market exchange rate
 *
 */
@Data // Lombok generate setters/getters, hashCode, equals and toString
@Builder // Builder pattern
@Document(indexName = "currencymarket", type = "exchangeRate")
public class ExchangeRate {

	@Id
	@JsonIgnore
	private final String id = UUID.randomUUID().toString();
	
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
