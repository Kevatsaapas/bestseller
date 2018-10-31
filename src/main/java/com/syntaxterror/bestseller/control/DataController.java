package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataController {

	@Autowired
	public KilpailuRepository kilpailuRepository;

	@Autowired
	public LohkoRepository lohkoRepository;

	@Autowired
	public KilpailijaRepository kilpailijaRepository;

	@Autowired
	public TuomariRepository tuomariRepository;

    @Autowired
    public ArviointiRepository arviointiRepository;

	private String[] etunimet = { "Juhani", "Maria", "Johannes", "Helena", "Olavi", "Johanna", "Antero", "Anneli",
			"Tapani", "Kaarina", "Kalevi", "Marjatta", "Tapio", "Anna", "Matti", "Liisa", "Mikael", "Annikki", "Ilmari",
			"Sofia", "Tupu", "Hupu", "Lupu", "Aku" };
	private String[] sukunimet = { "Korhonen", "Virtanen", "Mäkinen", "Nieminen", "Mäkelä", "Hämäläinen", "Laine",
			"Heikkinen", "Koskinen", "Järvinen", "Lehtonen", "Lehtinen", "Saarinen", "Salminen", "Heinonen", "Niemi",
			"Heikkilä", "Kinnunen", "Salonen", "Turunen", "Salo", "Laitinen", "Tuominen", "Rantanen" };
	private String[] koulut = { "Haaga-Helia Ammattikorkeakoulu", "Metropolia Ammattikorkeakoulu",
			"Laurea Ammattikorkeakoulu", "Helsinki Business College" };
	private String[] spostit = { "haaga.helia@haaga-helia.com", "metro@polia.com",
			"laurea.ammatti@korkea.com", "business.college@helsinki.com" };

	@RequestMapping("/datat/{kilpailuId}")
	public String dataa(@PathVariable Long kilpailuId, Model model) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		model.addAttribute("kilpailu", kilpailu);
		Iterable<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuId(kilpailuId);
		model.addAttribute("kilpailijat", kilpailijat);
		Iterable<Lohko> lohkot = lohkoRepository.findByKilpailu(kilpailu);
		model.addAttribute("lohkot", lohkot);
		model.addAttribute("tuomarit", tuomariRepository.findByKilpailuId(kilpailuId));
		model.addAttribute("arvioinnit", arviointiRepository.findByKilpailuId(kilpailuId));
		return "datat";
	}

	@RequestMapping("/tarkastelu/{arviointiId}")
	public String tarkastelu(@PathVariable Long arviointiId, Model model) {
		model.addAttribute("arviointi", arviointiRepository.findByArviointiId(arviointiId));
		return "tarkastelu";
	}
	
	
	 @RequestMapping("/poistaarviointi/{arviointiId}")
	    public String poistaArviointi(@PathVariable Long arviointiId){
		 	Long kilpailuId=arviointiRepository.findByArviointiId(arviointiId).getKilpailuId();
		 	String kilid = kilpailuId.toString();
	        arviointiRepository.deleteById(arviointiId);
	        return "redirect:/datat/"+kilid;
	    }

	@RequestMapping("/luodatat/{kilpailuId}")
	public String luoDataa(@PathVariable Long kilpailuId, Model model) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		model.addAttribute("kilpailu", kilpailu);
		luoDatat(kilpailu);
		Iterable<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuId(kilpailuId);
		model.addAttribute("kilpailijat", kilpailijat);
		Iterable<Lohko> lohkot = lohkoRepository.findByKilpailu(kilpailu);
		model.addAttribute("lohkot", lohkot);
		model.addAttribute("tuomarit", tuomariRepository.findByKilpailuId(kilpailuId));
		model.addAttribute("arvioinnit", arviointiRepository.findByKilpailuId(kilpailuId));
		return "datat";
	}

	public void luoDatat(Kilpailu kilpailu) {
		Long kilpailuId = kilpailu.getkilpailuId();
		int indeksi = 0;
		for(int u=1; u<5; u++){
		String lohkonro = String.valueOf(u);
		Lohko lohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, lohkonro);
		for (int i = 0; i < 6; i++) {
			int kohta = indeksi+i;
			int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
			String koulu = koulut[randomNum];
			String posti = spostit[randomNum];
			Kilpailija kilpailija = new Kilpailija(etunimet[kohta], sukunimet[kohta], 0, koulu, posti, lohko, kilpailuId);
			kilpailijaRepository.save(kilpailija);
		}
		for(int luku=1; luku<3; luku++){
			Tuomari tuomari = new Tuomari("Tuomari "+luku, lohkonro, kilpailuId);
			tuomariRepository.save(tuomari);
		}
		indeksi+=5;
		}

	}

}
