package be.lindacare.market.aphonso.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import be.lindacare.market.aphonso.domain.CurrencyExchangeRate;

@Service
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {

	public CurrencyExchangeRate save(CurrencyExchangeRate entity) {
		return entity;
	}

	public List<CurrencyExchangeRate> findAll() {
		
		List<CurrencyExchangeRate> marketExchangeRates =  new ArrayList<>();

		CurrencyExchangeRate curExchangeRate1 = CurrencyExchangeRate.builder().userId(1).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
		CurrencyExchangeRate curExchangeRate2 = CurrencyExchangeRate.builder().userId(2).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
		CurrencyExchangeRate curExchangeRate3 = CurrencyExchangeRate.builder().userId(3).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
		CurrencyExchangeRate curExchangeRate4 = CurrencyExchangeRate.builder().userId(4).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
		CurrencyExchangeRate curExchangeRate5 = CurrencyExchangeRate.builder().userId(5).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();

		marketExchangeRates.add(curExchangeRate1);		
		marketExchangeRates.add(curExchangeRate2);		
		marketExchangeRates.add(curExchangeRate3);		
		marketExchangeRates.add(curExchangeRate4);		
		marketExchangeRates.add(curExchangeRate5);
		
		return marketExchangeRates;		
	}
}
