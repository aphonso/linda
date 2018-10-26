package be.lindacare.currency.market.aphonso.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import be.lindacare.currency.market.aphonso.config.JsonConfig;
import be.lindacare.currency.market.aphonso.config.WebConfig;
import be.lindacare.currency.market.aphonso.service.ExchangeRateService;

/**
 * 
 * @author aphonso
 * 
 * Very simple class to display the total number of requests using Thymeleaf and bootstrap
 *
 */
@RestController(WebConfig.BASE_URL)
public class ExchangeRateGuiController {
	
	@Autowired
	private ExchangeRateService exchangeRateService;

	@GetMapping(WebConfig.BASE_URL)
	public ModelAndView root() {
		
		ModelAndView modelAndView = new ModelAndView("report");

		modelAndView.addObject("timestamp", JsonConfig.DATE_TIME_FORMATTER.format(LocalDateTime.now()));
		modelAndView.addObject("totalExchangeRates", this.exchangeRateService.count());

		return modelAndView;
	}
}
