package be.lindacare.currency.market.aphonso.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author aphonso
 * 
 * Configuration class that for the swagger endpoint descriptor
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
        		.apis(RequestHandlerSelectors.any())
        		.paths(PathSelectors.any())
        		.build()
        		.apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
    	String title = "Linda Care RestFul API";
    	String description = "Linda Care RESTFul API";
    	String version = "1.0.0-SNAPSHOT";
    	String termsOfServiceUrl = "Terms of service";
    	Contact contact = new Contact("Daniel Afonso", null, "aphonso@gmail.com");
    	String license = "License of API";
    	String licenseUrl = "API license URL";
    	
    	return new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl, Collections.emptyList());
    }
}
