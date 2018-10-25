package be.lindacare.market.aphonso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.lindacare.market.aphonso.domain.CurrencyExchangeRate;
import be.lindacare.market.aphonso.service.CurrencyExchangeRateService;

/**
 * 
 * @author aphonso
 * 
 * Class that turns available the application endpoints
 *
 */
@RestController
@RequestMapping("/api/v1/linda/market")
public class RestfulApiController {
	
	@Autowired
	private CurrencyExchangeRateService currencyExchangeRateService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CurrencyExchangeRate> findAllExchangeRates() {
		return this.currencyExchangeRateService.findAll();
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CurrencyExchangeRate addExchangeRate(@RequestBody CurrencyExchangeRate entity) {
		return this.currencyExchangeRateService.save(entity);
	}
}
