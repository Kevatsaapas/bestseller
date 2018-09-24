package com.syntaxterror.bestseller;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.OsaAlue;
import com.syntaxterror.bestseller.model.Ostaja;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KriteeriRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.OsaAlueRepository;
import com.syntaxterror.bestseller.repository.OstajaRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

@SpringBootApplication
public class BestsellerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BestsellerApplication.class, args);
		
	}
	@Bean
	public CommandLineRunner best(KilpailuRepository repo, KilpailijaRepository kilpailijaRepo, KriteeriRepository kripo, TuomariRepository tuore, LohkoRepository lohkoRepo, OsaAlueRepository osaalueRepo, ArviointiRepository are, OstajaRepository ostajaRepo) {
		return args ->{
			Date pvm = new Date();
			
			//Kilpailu
			Kilpailu bestseller = new Kilpailu("Bestseller 2019",pvm, "Turku" );
			repo.save(bestseller);
			System.out.println(repo.findAll());
			
			//Ostaja
			Ostaja ostaja = new Ostaja("Tarja Halonen");
			ostajaRepo.save(ostaja);
			System.out.println(ostajaRepo.findAll());
			
			//Lohkot
			Lohko lohko1 = new Lohko("1", bestseller, ostaja);
			lohkoRepo.save(lohko1);
			Lohko lohko2 = new Lohko("2", bestseller, ostaja);
			lohkoRepo.save(lohko2);
			Lohko lohko3 = new Lohko("3", bestseller, ostaja);
			lohkoRepo.save(lohko3);
			Lohko lohko4 = new Lohko("4", bestseller, ostaja);
			lohkoRepo.save(lohko4);
			Lohko finaali = new Lohko("5", bestseller, ostaja);
			lohkoRepo.save(finaali);
			System.out.println(lohkoRepo.findAll());
			
			//Osa-alueet
			OsaAlue aloitus= new OsaAlue("Aloitus", "Myyntitapaamisen hyvä haltuunotto ja keskustelusuhteen luominen.", new Long(10));
			osaalueRepo.save(aloitus);
			OsaAlue tarvekartoitus= new OsaAlue("Tarvekartoitus", "Saada tietoa asiakkaan tilanteesta ja tarpeista niin, että myyjä pystyy esittämään oman ratkaisunsa linkittyen asiakkaan tarpeisiin.", new Long(30));
			osaalueRepo.save(tarvekartoitus);
			OsaAlue ratkaisu= new OsaAlue("Ratkaisun esittäminen", "Ratkaisun ja sen hyötyjen esittäminen.", new Long(25));
			osaalueRepo.save(ratkaisu);
			OsaAlue asiakaskys= new OsaAlue("Asiakkaan kysymysten käsittely", "Asiakkaan esittämien kysymyksien käsittely sekä mahdollisten huolien ja epäilyjen poistaminen.", new Long(10));
			osaalueRepo.save(asiakaskys);
			OsaAlue paattaminen= new OsaAlue("Päättäminen", "Ymmärtää, miten asian käsittely etenee ja missä päätöksenteon kannalta ollaan sekä sopia jatkosta.", new Long(10));
			osaalueRepo.save(paattaminen);
			OsaAlue yleisvaikutelma= new OsaAlue("Yleisvaikutelma. Viestintä- ja vuorovaikutustaidot.", "", new Long(10));
			osaalueRepo.save(yleisvaikutelma);
			System.out.println(osaalueRepo.findAll());
			
			//kilpailijat
			Kilpailija kil1 = new Kilpailija("Tiivi", "Taavi", 1, "Haaga-Helia", lohko1, new Long(1));
			kilpailijaRepo.save(kil1);
			Kilpailija kil2 = new Kilpailija("Hip", "Su", 2, "Haaga-Helia", lohko1, new Long(1));
			kilpailijaRepo.save(kil2);
			Kilpailija kil3 = new Kilpailija("Laa", "Laaa", 3, "Haaga-Helia", lohko2, new Long(1));
			kilpailijaRepo.save(kil3);
			Kilpailija kil4 = new Kilpailija("Pai", "Ai", 4, "Haaga-Helia", lohko2, new Long(1));
			kilpailijaRepo.save(kil4);
			System.out.println(kilpailijaRepo.findAll());
		};
	}
}
