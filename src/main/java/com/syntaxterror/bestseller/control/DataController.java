package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Koulu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;
import com.syntaxterror.bestseller.repository.UserRepository;
import com.syntaxterror.bestseller.service.ArviointiService;
import com.syntaxterror.bestseller.service.LeaderboardService;

import java.util.ArrayList;
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
	public ArviointiService arviointiService;

	@Autowired
	public LeaderboardService leaderboardService;
	
	@Autowired
	public LohkoRepository lohkoRepository;

	@Autowired
	public KilpailijaRepository kilpailijaRepository;

	@Autowired
	public TuomariRepository tuomariRepository;

    @Autowired
    public ArviointiRepository arviointiRepository;
    
    @Autowired
    public KouluRepository kouluRepository;
    
    @Autowired
    public UserRepository userRepository;
    
	private String[] etunimet = { "Juhani", "Maria", "Johannes", "Helena", "Olavi", "Johanna", "Antero", "Anneli",
			"Tapani", "Kaarina", "Kalevi", "Marjatta", "Tapio", "Anna", "Matti", "Liisa", "Mikael", "Annikki", "Ilmari",
			"Sofia", "Tupu", "Hupu", "Lupu", "Aku" };
	private String[] sukunimet = { "Korhonen", "Virtanen", "Mäkinen", "Nieminen", "Mäkelä", "Hämäläinen", "Laine",
			"Heikkinen", "Koskinen", "Järvinen", "Lehtonen", "Lehtinen", "Saarinen", "Salminen", "Heinonen", "Niemi",
			"Heikkilä", "Kinnunen", "Salonen", "Turunen", "Salo", "Laitinen", "Tuominen", "Rantanen" };
	private String[] spostit = { "haaga.helia@haaga-helia.com", "metro@polia.com",
			"laurea.ammatti@korkea.com", "business.college@helsinki.com" };
	private String[] koulunimet = {"Haaga-Helia", "Metropolia", "Laurea", "Turun AMK"};

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
		model.addAttribute("koulut", kouluRepository.findByKilpailuId(kilpailuId));
		System.out.println(kouluRepository.findByKilpailuId(kilpailuId));
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

	 @RequestMapping("/luoarviot/{kilpailuId}")
		public String luoArvioinnit(@PathVariable Long kilpailuId, Model model) {
		 arviointiService.arvioi(kilpailuId);
		 String kilid = kilpailuId.toString();
		 return "redirect:/datat/"+kilid;
	 }
	 
	 @RequestMapping("/kokonaistulokset/{kilpailuId}")
		public String luoKokonaistulokset(@PathVariable Long kilpailuId, Model model) {
		 leaderboardService.laskeLopputulokset(kilpailuId);
		 String kilid = kilpailuId.toString();
		 return "redirect:/datat/"+kilid;
	 }
	 
	@RequestMapping("/luodatat/{kilpailuId}")
	public String luoDataa(@PathVariable Long kilpailuId, Model model) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		luoDatat(kilpailu);
		String kilid = kilpailuId.toString();
		return "redirect:/datat/"+kilid;
	}

	public void luoDatat(Kilpailu kilpailu) {
		Long kilpailuId = kilpailu.getkilpailuId();
		int indeksi = 0;
		List<Koulu> koulut = new ArrayList<Koulu>();
		
		for(int luku=0; luku<koulunimet.length; luku++) {
			Koulu uusikoulu = new Koulu(koulunimet[luku], "kaupunki", kilpailuId);
			System.out.println(uusikoulu);
			koulut.add(uusikoulu);
			kouluRepository.save(uusikoulu);
		}
		
		for(int u=1; u<5; u++){
		String lohkonro = String.valueOf(u);
		Lohko lohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, lohkonro);
		
		for (int i = 0; i < 6; i++) {
			int kohta = indeksi+i;
			int randomNum = ThreadLocalRandom.current().nextInt(0, koulunimet.length);
			Koulu koulu = koulut.get(randomNum);
			String posti = spostit[randomNum];
			Kilpailija kilpailija = new Kilpailija(etunimet[kohta], sukunimet[kohta], 0, koulu, posti, lohko, kilpailuId,0);
			kilpailijaRepository.save(kilpailija);
		}
		for(int luku=1; luku<3; luku++){
			int randomNum = ThreadLocalRandom.current().nextInt(0, 15 + 1);
			int randomNumm = ThreadLocalRandom.current().nextInt(0, 15 + 1);
			Tuomari tuomari = new Tuomari("Tuomari "+luku,etunimet[randomNum],sukunimet[randomNumm], lohkonro, kilpailuId, new Long(0));
			tuomariRepository.save(tuomari);
		}
		
		indeksi+=5;
		}

	}

}
