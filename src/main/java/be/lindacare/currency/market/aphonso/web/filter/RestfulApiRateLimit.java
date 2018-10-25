package be.lindacare.currency.market.aphonso.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.google.common.util.concurrent.RateLimiter;

import be.lindacare.currency.market.aphonso.config.WebConfig;

/**
 * 
 * @author aphonso
 * 
 * Creates a filter only for the RESTful API and limits the request rate
 *
 */
@WebFilter(urlPatterns = WebConfig.RESTFUL_API_MARKET_URL)
@PropertySource("classpath:application.properties")
public class RestfulApiRateLimit implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestfulApiRateLimit.class);

	private static final int TOO_MANY_REQUESTS = 429;

	private double restfulApiRateLimit;
	private RateLimiter restfulApiRateLimiter;
	private String exceededRateLimitError;

	@Value("${restful.api.rate.limit:10}")
	public void setUpRateLimiter(double restfulApiRateLimit) {

		this.restfulApiRateLimit = restfulApiRateLimit;
		this.exceededRateLimitError = "You have exceeded the rate limit (rate limit = " + restfulApiRateLimit + ")";
		this.restfulApiRateLimiter = RateLimiter.create(this.restfulApiRateLimit);

		LOGGER.info("RESTful API Rate Limiter loaded: {}", this.restfulApiRateLimit);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		if (this.restfulApiRateLimiter.tryAcquire()) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			HttpServletResponse response = (HttpServletResponse) servletResponse;

			response.sendError(TOO_MANY_REQUESTS, this.exceededRateLimitError);
		}
	}

	@Override
	public void destroy() {

	}

}