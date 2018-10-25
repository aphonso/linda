package be.lindacare.currency.market.aphonso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.lindacare.currency.market.aphonso.domain.ExchangeRate;
import be.lindacare.currency.market.aphonso.repository.ExchangeRateRepository;

/**
 * 
 * @author aphonso
 * 
 * Exchange Rate Service Implementation
 *
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	@Override
	public ExchangeRate save(ExchangeRate entity) {
		return this.exchangeRateRepository.save(entity);
	}

	@Override
	public Iterable<ExchangeRate> findAll() {
		return this.exchangeRateRepository.findAll();
	}

	@Override
	public long count() {
		return this.exchangeRateRepository.count();
	}
}
