package be.lindacare.currency.market.aphonso.web.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.lindacare.currency.market.aphonso.config.WebConfig;
import be.lindacare.currency.market.aphonso.domain.ExchangeRate;
import be.lindacare.currency.market.aphonso.service.ExchangeRateService;

/**
 * 
 * @author aphonso
 * 
 * Class that turns available the application endpoints
 *
 */
@RestController
@RequestMapping(WebConfig.RESTFUL_API_MARKET_URL)
public class RestfulApiController {
	
	@Autowired
	private ExchangeRateService exchangeRateService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<ExchangeRate> findAllExchangeRates() {
    	return StreamSupport.stream(exchangeRateService.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ExchangeRate addExchangeRate(@RequestBody ExchangeRate entity) {
		return this.exchangeRateService.save(entity);
	}
}
