package com.syntaxterror.bestseller;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Kriteeri;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.OsaAlue;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KriteeriRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.OsaAlueRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

@SpringBootApplication
public class BestsellerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BestsellerApplication.class, args);
		
	}
	@Bean
	public CommandLineRunner best(KilpailuRepository repo, KilpailijaRepository kilrepo, KriteeriRepository kripo, TuomariRepository tuore, LohkoRepository lore, OsaAlueRepository ore, ArviointiRepository are) {
		return args ->{
			
	};
	}
}
