package be.lindacare.currency.market.aphonso.service;

import be.lindacare.currency.market.aphonso.domain.ExchangeRate;

/**
 * 
 * @author aphonso
 * 
 * Exchange Rate Service interface
 *
 */
public interface ExchangeRateService {

	public ExchangeRate save(ExchangeRate entity);
	
	public Iterable<ExchangeRate> findAll();
	
	public long count();
}
