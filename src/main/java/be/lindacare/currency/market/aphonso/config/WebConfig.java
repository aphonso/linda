package be.lindacare.currency.market.aphonso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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

	public static final String RESTFUL_API_MARKET_URL = "/api/v1/linda/market";
	public static final String BASE_URL = "/"; 
	
	public static final String ROLE_ADMIN = "ADMIN";
	
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
}

