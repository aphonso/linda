package be.lindacare.currency.market.aphonso.service.load;

import static org.assertj.core.api.BDDAssertions.then;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import be.lindacare.currency.market.aphonso.AbstractTests;
import be.lindacare.currency.market.aphonso.domain.ExchangeRate;
import be.lindacare.currency.market.aphonso.service.ExchangeRateService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoadServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoadServiceTest.class);
	
	private static final long LOAD_NUMBER = 1000;
	
    @Autowired
    private ElasticsearchTemplate esTemplate;
    
    @Autowired
    private ExchangeRateService exchangeRateService;
	
	@Test
	public void test_service() {

		// Clears Elastic Search Test
		AbstractTests.setUpElasticSearch(this.esTemplate);

		long start = System.currentTimeMillis();
		for(long i = 0; i < LOAD_NUMBER; i++) {
			
			ExchangeRate newExchangeRate = ExchangeRate.builder().userId(1).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
			
			this.exchangeRateService.save(newExchangeRate);
		}
		
		long finish = System.currentTimeMillis();
		long timeElapsed = (finish - start) / 1000;
		
		LOGGER.info("Time to load {} records: {}", LOAD_NUMBER, timeElapsed);
		
		then(this.exchangeRateService.count()).isEqualTo(LOAD_NUMBER);
	}

}
