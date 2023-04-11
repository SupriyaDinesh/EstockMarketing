package com.stock.marketing.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket config() {
		///return a prepared Docket instance
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.stock.marketing"))
				.build()
				.pathMapping("/")
				.apiInfo(apiInfo()) ;
	}
	private ApiInfo apiInfo() {
        return new ApiInfo(
                "EStockMarketing REST API", //title
                "Below are the API endpoints for \n\n-->Company registration, Get all company and Delete, Get company by company code and Update company\n", //description
                "Version 1.0", //version
                "Terms of service", //terms of service URL
                new Contact("Supriya Dinesh", "www.example.com", "Supriya.D2@cognizant.com"),
                "License of API", "API license URL", Collections.emptyList()); // contact info
    }

}
