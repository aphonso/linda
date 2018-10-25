package be.lindacare.market.aphonso.service;

import java.util.List;

import be.lindacare.market.aphonso.domain.CurrencyExchangeRate;

public interface CurrencyExchangeRateService {

	public CurrencyExchangeRate save(CurrencyExchangeRate entity);
	
	public List<CurrencyExchangeRate> findAll();
}
