package be.lindacare.currency.market.aphonso.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import be.lindacare.currency.market.aphonso.domain.ExchangeRate;

/**
 * 
 * @author aphonso
 * 
 * Elastic Search Repository for the exchange rate
 *
 */
@Repository
public interface ExchangeRateRepository extends ElasticsearchRepository<ExchangeRate, String> {
	
}
