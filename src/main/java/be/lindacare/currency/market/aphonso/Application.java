package be.lindacare.currency.market.aphonso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * 
 * @author aphonso
 * 
 * Application Entry-point
 *
 */
@ServletComponentScan
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	public static void main(String[] args) {
	    System.setProperty("server.servlet.context-path", "/lindacare");
		SpringApplication.run(Application.class, args);
	}
}
