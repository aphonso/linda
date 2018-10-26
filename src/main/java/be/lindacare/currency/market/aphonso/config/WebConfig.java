package be.lindacare.currency.market.aphonso.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.util.concurrent.RateLimiter;


/**
 * 
 * @author aphonso
 * 
 * Configuration class for the Web layer, configures endpoints security user access
 * permissions and resources.
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

	public static final int TOO_MANY_REQUESTS = 429;
	public static final int OK_REQUEST = 200;

	public static final String RESTFUL_API_MARKET_URL = "/api/v1/linda/market";
	public static final String BASE_URL = "/";
	public static final String SWAGGER_URL = "/swagger-ui.html";
	
	public static final String ROLE_ADMIN = "ADMIN";

	@Value("${restful.api.rate.limit:10}")
	public double restfulApiRateLimit;
	
	private RateLimiter restfulApiRateLimiter;
	
	private String exceededRateLimitError;

	@Bean
    public RateLimiter restfulApiRateLimiter() {
		
		this.exceededRateLimitError = "You have exceeded the rate limit (rate limit = " + restfulApiRateLimit + ")";
		this.restfulApiRateLimiter = RateLimiter.create(this.restfulApiRateLimit);

		LOGGER.info("Rate Limiter loaded: {}", this.restfulApiRateLimit);
		
		return this.restfulApiRateLimiter;
    }
	
	/**
	 * 
	 * Allows all gets, but the posts need to be authenticated
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    	.and()
	    	.csrf().disable()
			.httpBasic()
	    	.and()
	    	.authorizeRequests()
	    		.antMatchers(HttpMethod.GET, BASE_URL).permitAll()
	    		.antMatchers(HttpMethod.GET, RESTFUL_API_MARKET_URL).permitAll()
		    	.antMatchers(HttpMethod.POST, RESTFUL_API_MARKET_URL).hasRole(ROLE_ADMIN);
	}

	/**
	 * 
	 * Added swagger-ui resources (has a dependency of the webjars)
	 * 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	    
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
    
    @Bean
    public Filter rateLimitFilder() {
        return new Filter() {

        	@Override
        	public void init(FilterConfig filterConfig) throws ServletException {

        	}

        	@Override
        	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        			throws IOException, ServletException {

        		if (WebConfig.this.restfulApiRateLimiter.tryAcquire()) {
        			filterChain.doFilter(servletRequest, servletResponse);
        		} else {
        			HttpServletResponse response = (HttpServletResponse) servletResponse;

        			response.sendError(WebConfig.TOO_MANY_REQUESTS, WebConfig.this.exceededRateLimitError);
        		}
        	}

        	@Override
        	public void destroy() {

        	}
        };
    }
}

