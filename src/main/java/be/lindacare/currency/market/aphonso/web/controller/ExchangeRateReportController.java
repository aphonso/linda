package be.lindacare.currency.market.aphonso.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import be.lindacare.currency.market.aphonso.config.WebConfig;

@RestController(WebConfig.BASE_URL)
public class ExchangeRateReportController {
	
    @GetMapping(WebConfig.BASE_URL)
    public String root() {
        return "Should Point To Report!";
    }
	
    @GetMapping(WebConfig.MARKET_REPORT_URL)
    public String report() {
        return "Report!";
    }
}
