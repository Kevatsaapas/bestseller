package com.syntaxterror.bestseller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BestsellerApplication extends SpringBootServletInitializer {
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BestsellerApplication.class);
	}

	public static void main(String[] args)throws Exception {
		SpringApplication.run(BestsellerApplication.class, args);

	}
	
}
