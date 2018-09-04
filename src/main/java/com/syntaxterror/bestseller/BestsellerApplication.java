package com.syntaxterror.bestseller;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.repository.KilpailuRepository;

@SpringBootApplication
public class BestsellerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BestsellerApplication.class, args);
		
	}
	@Bean
	public CommandLineRunner best(KilpailuRepository repo) {
		return args ->{
			Date deit = new Date();
			Kilpailu kilpailu = new Kilpailu("Parasmyynti", deit, "stadi");
			repo.save(kilpailu);
			System.out.println(repo.findAll());
		};
	}
}
