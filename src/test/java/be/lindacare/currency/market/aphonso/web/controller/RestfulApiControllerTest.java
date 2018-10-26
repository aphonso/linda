package be.lindacare.currency.market.aphonso.web.controller;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.lindacare.currency.market.aphonso.AbstractTests;
import be.lindacare.currency.market.aphonso.config.WebConfig;
import be.lindacare.currency.market.aphonso.domain.ExchangeRate;

/**
 * 
 * @author aphonso
 * 
 * Web integration tests.
 *
 */
@AutoConfigureMockMvc
public class RestfulApiControllerTest extends AbstractTests {

	private static final String TEXT_HTML_UTF8 = "text/html;charset=UTF-8";
	
    @Autowired
    private ElasticsearchTemplate esTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();

	private ExchangeRate expectedExchangeRate1;
	private ExchangeRate expectedExchangeRate2;
	private ExchangeRate expectedExchangeRate3;
	
	private List<ExchangeRate> expectedAllCurExchangeRates;

	private String expectedExchangeRate1Json;
	private String expectedExchangeRate2Json;
	private String expectedExchangeRate3Json;
	
	public RestfulApiControllerTest() throws JsonProcessingException {

		this.expectedExchangeRate1 = ExchangeRate.builder().userId(1).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
		this.expectedExchangeRate2 = ExchangeRate.builder().userId(2).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
		this.expectedExchangeRate3 = ExchangeRate.builder().userId(3).currencyFrom("EUR").currencyTo("GBP").amountSell(1000f).amountBuy(747.10f).rate(0.7471f).timePlaced(LocalDateTime.of(2015, 1, 24, 10, 27, 44)).originatingCountry("FR").build();
		
		this.expectedAllCurExchangeRates = new ArrayList<>();		
		this.expectedAllCurExchangeRates.add(this.expectedExchangeRate1);
		this.expectedAllCurExchangeRates.add(this.expectedExchangeRate2);
		this.expectedAllCurExchangeRates.add(this.expectedExchangeRate3);
		
		this.expectedExchangeRate1Json = this.mapper.writeValueAsString(this.expectedExchangeRate1);
		this.expectedExchangeRate2Json = this.mapper.writeValueAsString(this.expectedExchangeRate2);
		this.expectedExchangeRate3Json = this.mapper.writeValueAsString(this.expectedExchangeRate3);
	}

	// Validates all endpoints with no login credentials
	@Test
	public void test_no_login_urls() throws Exception {

		// Checks that the swagger ui works without login
		this.mockMvc.perform(get(WebConfig.SWAGGER_URL)).andExpect(content().contentType(MediaType.TEXT_HTML)).andExpect(status().isOk()).andDo(log());
		
		// Checks that the total number of requests works without login
		this.mockMvc.perform(get(WebConfig.BASE_URL)).andExpect(content().contentType(TEXT_HTML_UTF8)).andExpect(status().isOk()).andDo(log());
		
		// Checks that post is not allowed without credentials
		this.mockMvc.perform(post(WebConfig.RESTFUL_API_MARKET_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(this.expectedExchangeRate1Json)).andExpect(status().isUnauthorized()).andDo(log());
	}

	// Validates all endpoints with mocked user but no roles
	@Test
	@WithMockUser(username = "user" )
	public void test_mocked_user_no_roles_urls() throws Exception {

		// Checks that the swagger ui works with mocked user but no roles
		this.mockMvc.perform(get(WebConfig.SWAGGER_URL)).andExpect(content().contentType(MediaType.TEXT_HTML)).andExpect(status().isOk()).andDo(log());
		
		// Checks that the total number of requests works with mocked user but no roles
		this.mockMvc.perform(get(WebConfig.BASE_URL)).andExpect(content().contentType(TEXT_HTML_UTF8)).andExpect(status().isOk()).andDo(log());
		
		// Checks that post is not allowed with mocked user but no roles
		this.mockMvc.perform(post(WebConfig.RESTFUL_API_MARKET_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(this.expectedExchangeRate1Json)).andExpect(status().isForbidden()).andDo(log());
	}
	
	// Validates all endpoints with mocked user but with a non used role
	@Test
	@WithMockUser(username = "user", roles = { "WRONG_ROLE" } )
	public void test_mocked_user_wrong_roles_urls() throws Exception {

		// Checks that the swagger ui works with mocked user but with a non used role
		this.mockMvc.perform(get(WebConfig.SWAGGER_URL)).andExpect(content().contentType(MediaType.TEXT_HTML)).andExpect(status().isOk()).andDo(log());
		
		// Checks that the total number of requests works with mocked user but with a non used role
		this.mockMvc.perform(get(WebConfig.BASE_URL)).andExpect(content().contentType(TEXT_HTML_UTF8)).andExpect(status().isOk()).andDo(log());
		
		// Checks that post is not allowed with mocked user but with a non used role
		this.mockMvc.perform(post(WebConfig.RESTFUL_API_MARKET_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(this.expectedExchangeRate1Json)).andExpect(status().isForbidden()).andDo(log());
	}
	
	@Test
	@WithMockUser(username = "user", roles = { WebConfig.ROLE_ADMIN })
	@SuppressWarnings("unchecked")
	public void test_mocked_user_right_roles_urls() throws Exception {

		// Checks that the swagger ui works with mocked user with the right role
		this.mockMvc.perform(get(WebConfig.SWAGGER_URL)).andExpect(content().contentType(MediaType.TEXT_HTML)).andExpect(status().isOk()).andDo(log());
		
		// Checks that the total number of requests works with mocked user with the right role
		this.mockMvc.perform(get(WebConfig.BASE_URL)).andExpect(content().contentType(TEXT_HTML_UTF8)).andExpect(status().isOk()).andDo(log());

		// Clears Elastic Search Test
		AbstractTests.setUpElasticSearch(this.esTemplate);
		
		// Adds 3 exchange rates
		MvcResult actualExchangeRate1Response = this.mockMvc.perform(post(WebConfig.RESTFUL_API_MARKET_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(this.expectedExchangeRate1Json)).andExpect(status().isOk()).andDo(log()).andReturn();
		MvcResult actualExchangeRate2Response = this.mockMvc.perform(post(WebConfig.RESTFUL_API_MARKET_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(this.expectedExchangeRate2Json)).andExpect(status().isOk()).andDo(log()).andReturn();
		MvcResult actualExchangeRate3Response = this.mockMvc.perform(post(WebConfig.RESTFUL_API_MARKET_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(this.expectedExchangeRate3Json)).andExpect(status().isOk()).andDo(log()).andReturn();

		String actualExchangeRate1Json = actualExchangeRate1Response.getResponse().getContentAsString();
		String actualExchangeRate2Json = actualExchangeRate2Response.getResponse().getContentAsString();
		String actualExchangeRate3Json = actualExchangeRate3Response.getResponse().getContentAsString();

		ExchangeRate actualExchangeRate1 = this.mapper.readValue(actualExchangeRate1Json, ExchangeRate.class);
		ExchangeRate actualExchangeRate2 = this.mapper.readValue(actualExchangeRate2Json, ExchangeRate.class);
		ExchangeRate actualExchangeRate3 = this.mapper.readValue(actualExchangeRate3Json, ExchangeRate.class);

		// Checks that the returned rates have the same attribute as the one provided
		AbstractTests.compareRateAttributes(actualExchangeRate1, this.expectedExchangeRate1);
		AbstractTests.compareRateAttributes(actualExchangeRate2, this.expectedExchangeRate2);
		AbstractTests.compareRateAttributes(actualExchangeRate3, this.expectedExchangeRate3);
		
		// Gets all the 3 added exchange rates
		MvcResult actualExchangeRatesResponse = this.mockMvc.perform(get(WebConfig.RESTFUL_API_MARKET_URL)).andExpect(status().isOk()).andDo(log()).andReturn();

		String actualExchangeRatesJson = actualExchangeRatesResponse.getResponse().getContentAsString();
		
		List<ExchangeRate> actualExchangeRates = this.mapper.readValue(actualExchangeRatesJson, List.class);
		
		// Checks that they are indeed 3
		then(actualExchangeRates.size()).isEqualTo(3);		
	}
	
	@Test
	@WithMockUser(username = "user", roles = { WebConfig.ROLE_ADMIN })
	public void test_rate_limit() throws Exception {

		for(int i = 0; i < 20; i++) {
			int status = RestfulApiControllerTest.this.mockMvc.perform(post(WebConfig.RESTFUL_API_MARKET_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(this.expectedExchangeRate1Json)).andReturn().getResponse().getStatus();
			
			if(status == WebConfig.TOO_MANY_REQUESTS) {
				then(status).isEqualTo(WebConfig.TOO_MANY_REQUESTS);
			}
			else {
				then(status).isEqualTo(WebConfig.OK_REQUEST);
			}
		}
	}
}
