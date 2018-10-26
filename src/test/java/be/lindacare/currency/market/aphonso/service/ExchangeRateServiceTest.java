package be.lindacare.currency.market.aphonso.service;

import static org.assertj.core.api.BDDAssertions.then;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import be.lindacare.currency.market.aphonso.AbstractTests;
import be.lindacare.currency.market.aphonso.domain.ExchangeRate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExchangeRateServiceTest {
	
    @Autowired
    private ElasticsearchTemplate esTemplate;
    
    @Autowired
    private ExchangeRateService exchangeRateService;

	private ExchangeRate expectedExchangeRate1;
	private ExchangeRate expectedExchangeRate2;
	private ExchangeRate expectedExchangeRate3;
	
	public ExchangeRateServiceTest() {
		
		this.expectedExchangeRate1 = ExchangeRate.builder().userId(1).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
		this.expectedExchangeRate2 = ExchangeRate.builder().userId(2).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
		this.expectedExchangeRate3 = ExchangeRate.builder().userId(3).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
	}
	
	@Test
	public void test_service() {

		// Clears Elastic Search Test
		AbstractTests.setUpElasticSearch(this.esTemplate);
		
		then(this.exchangeRateService.count()).isEqualTo(0);

		this.exchangeRateService.save(this.expectedExchangeRate1);
		this.exchangeRateService.save(this.expectedExchangeRate2);
		this.exchangeRateService.save(this.expectedExchangeRate3);
		
		then(this.exchangeRateService.count()).isEqualTo(3);
		
		then(StreamSupport.stream(this.exchangeRateService.findAll().spliterator(), false).collect(Collectors.toList()).size()).isEqualTo(3);
		
	}

}
