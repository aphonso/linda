package be.lindacare.currency.market.aphonso;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import be.lindacare.currency.market.aphonso.domain.ExchangeRate;

/**
 * 
 * @author aphonso
 * 
 * Abstract test method
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstractTests {

    public static void setUpElasticSearch(ElasticsearchTemplate esTemplate) {
    	
        esTemplate.deleteIndex(ExchangeRate.class);
        esTemplate.createIndex(ExchangeRate.class);
        esTemplate.putMapping(ExchangeRate.class);
        esTemplate.refresh(ExchangeRate.class);
    }
    
    public static void compareRateAttributes(ExchangeRate actual, ExchangeRate expected) {
    	then(actual.getUserId()).isEqualTo(expected.getUserId());
    	then(actual.getCurrencyFrom()).isEqualTo(expected.getCurrencyFrom());
    	then(actual.getCurrencyTo()).isEqualTo(expected.getCurrencyTo());
    	then(actual.getAmountSell()).isEqualTo(expected.getAmountSell());
    	then(actual.getAmountBuy()).isEqualTo(expected.getAmountBuy());
    	then(actual.getRate()).isEqualTo(expected.getRate());
    	then(actual.getTimePlaced()).isEqualTo(expected.getTimePlaced());
    	then(actual.getOriginatingCountry()).isEqualTo(expected.getOriginatingCountry());    	
    }
}
