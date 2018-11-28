package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Koulu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.Ostaja;
import com.syntaxterror.bestseller.model.OstajaArviointi;
import com.syntaxterror.bestseller.repository.*;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;
import com.syntaxterror.bestseller.service.ArviointiService;
import com.syntaxterror.bestseller.service.LeaderboardService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
public class DataController {

	@Autowired
	private KilpailuRepository kilpailuRepository;
	
	@Autowired
	private ArviointiService arviointiService;

	@Autowired
	private LeaderboardService leaderboardService;
	
	@Autowired
	private LohkoRepository lohkoRepository;

	@Autowired
	private KilpailijaRepository kilpailijaRepository;

	@Autowired
	private TuomariRepository tuomariRepository;

    @Autowired
	private ArviointiRepository arviointiRepository;
    
    @Autowired
	private KouluRepository kouluRepository;

    @Autowired
	private OstajaRepository ostajaRepository;
    
    @Autowired
	private OstajaArviointiRepository ostajaArviointiRepository;
    
    @Autowired
	private ValmentajaRepository valmentajaRepository;

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

        // Gets data from repositories
        List<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuId(kilpailuId);
        List<Tuomari> tuomarit = tuomariRepository.findByKilpailuId(kilpailuId);
        List<Arviointi> arvioinnit = arviointiRepository.findByKilpailuId(kilpailuId);
        Iterable<Lohko> lohkot = lohkoRepository.findByKilpailu(kilpailu);
        List<Koulu> koulut = kouluRepository.findByKilpailuId(kilpailuId);
        List<Ostaja> ostajat = ostajaRepository.findByKilpailuId(kilpailuId);

        Lohko finaalilohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, "finaali");
        List<Arviointi> finaaliarvioinnit = arviointiRepository.findByKilpailuIdAndLohko(kilpailuId, finaalilohko);
        List<OstajaArviointi> finaaliostajaarvioinnit = ostajaArviointiRepository.findByKilpailuIdAndLohko(kilpailuId, finaalilohko);
        List<OstajaArviointi> ostajaArvioinnit = ostajaArviointiRepository.findByKilpailuId(kilpailuId);

        // Adds data to model
		model.addAttribute("kilpailu", kilpailu);
		model.addAttribute("kilpailijat", kilpailijat);
		model.addAttribute("kilpailijaLkm", kilpailijat.size());

		model.addAttribute("lohkot", lohkot);

		model.addAttribute("tuomarit", tuomarit);
		model.addAttribute("tuomariLkm", tuomarit.size());
		model.addAttribute("arvioinnit", arvioinnit);
        model.addAttribute("arviointiLkm", arvioinnit.size()-finaaliarvioinnit.size());

        model.addAttribute("ostajaLkm", ostajat.size());
        model.addAttribute("ostajat", ostajat);
		model.addAttribute("ostajaArvioinnit", ostajaArvioinnit);
		model.addAttribute("ostajaArviointiLkm", ostajaArvioinnit.size()-finaaliostajaarvioinnit.size());

		model.addAttribute("koulut", koulut);
		model.addAttribute("kouluLkm", koulut.size());
        model.addAttribute("valmentajat", valmentajaRepository.findByKilpailuId(kilpailuId));

        // Calculates total amount of reviews per competition
        int ostajaArviointiTotal = arviointiService.laskeOstajaArviointienSumma(kilpailuId);
        int arviointiTotal = arviointiService.laskeArviointienSumma(kilpailuId);

        // Adds total amount of reviews to model
		model.addAttribute("arviointiTotal", arviointiTotal);
		model.addAttribute("ostajaArviointiTotal", ostajaArviointiTotal);

		if(kilpailu.getFinaali() == 0 && arviointiTotal == arvioinnit.size() && ostajaArvioinnit.size() > 0 && ostajaArviointiTotal==ostajaArvioinnit.size() &&  ostajaArvioinnit.size() > 0) {
			model.addAttribute("luofinaali", 1);
		}else {
			model.addAttribute("luofinaali", 0);
		}

		return "datat";
	}
	
	@RequestMapping("/finaalidatat/{kilpailuId}")
	public String finaalidataa(@PathVariable Long kilpailuId, Model model) {

		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);

		model.addAttribute("kilpailu", kilpailu);
		List<Kilpailija> kilpailijat = leaderboardService.palautaFinalistitParhaastaHuonoimpaan(kilpailuId);

		model.addAttribute("kilpailijat", kilpailijat);
		model.addAttribute("kilpailijaLkm", kilpailijat.size());

		List<Tuomari> tuomarit=tuomariRepository.findByKilpailuIdAndFinaaliin(kilpailuId, new Long(1));

		model.addAttribute("tuomarit", tuomarit);
		model.addAttribute("tuomariLkm", tuomarit.size());
		
		List<Ostaja> ostajat=ostajaRepository.findByKilpailuIdAndFinaaliin(kilpailuId, new Long(1));

		model.addAttribute("ostajat", ostajat);
		model.addAttribute("ostajaLkm", ostajat.size());
		
		Lohko finaalilohko=lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, "finaali");

		List<Arviointi> arvioinnit = arviointiRepository.findByKilpailuIdAndLohko(kilpailuId, finaalilohko);

		int arviointiTotal=arviointiService.laskeFinaaliArviointienSumma(kilpailuId);

		model.addAttribute("arvioinnit", arvioinnit);
		model.addAttribute("arviointiLkm", arvioinnit.size());
		model.addAttribute("arviointiTotal", arviointiTotal);
		
		List<OstajaArviointi> ostajaArvioinnit = ostajaArviointiRepository.findByKilpailuIdAndLohko(kilpailuId, finaalilohko);

		int ostajaArviointiTotal=arviointiService.laskeFinaaliOstajaArviointienSumma(kilpailuId);

		model.addAttribute("ostajaArvioinnit", ostajaArvioinnit);
		model.addAttribute("ostajaArviointiLkm", ostajaArvioinnit.size());
		model.addAttribute("ostajaArviointiTotal", ostajaArviointiTotal);
		
		if(kilpailu.getFinaali() == 1 && kilpailu.getValmis() == 0 && arviointiTotal == arvioinnit.size() &&  arvioinnit.size() > 0 && ostajaArviointiTotal == ostajaArvioinnit.size() &&  ostajaArvioinnit.size() > 0) {
			model.addAttribute("arvioifinaali", 1);
		}else {
			model.addAttribute("arvioifinaali", 0);
		}

		return "finaalidatat";
	}

	@RequestMapping("/tarkastelu/{arviointiId}")
	public String tarkastelu(@PathVariable Long arviointiId, Model model) {

		Arviointi arviointi = arviointiRepository.findByArviointiId(arviointiId);

		model.addAttribute("arviointi", arviointi);
		model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(arviointi.getKilpailuId()));
		
		return "tarkastelu";
	}
	
	
	 @RequestMapping("/poistaarviointi/{arviointiId}")
	    public String poistaArviointi(@PathVariable Long arviointiId){

		 	Long kilpailuId=arviointiRepository.findByArviointiId(arviointiId).getKilpailuId();
		 	String kilid = kilpailuId.toString();
	        arviointiRepository.deleteById(arviointiId);

	        return "redirect:/datat/" + kilid;
	    }

	 @RequestMapping("/luoarviot/{kilpailuId}")
		public String luoArvioinnit(@PathVariable Long kilpailuId, Model model) {

		 arviointiService.arvioi(kilpailuId);
		 String kilid = kilpailuId.toString();

		 return "redirect:/datat/" + kilid;
	 }
	 
	 @RequestMapping("/luofinaaliarviot/{kilpailuId}")
		public String luoFinaaliArvioinnit(@PathVariable Long kilpailuId, Model model) {

		 arviointiService.arvioiFinaali(kilpailuId);
		 String kilid = kilpailuId.toString();

		 return "redirect:/finaalidatat/" + kilid;
	 }
	 
	 @RequestMapping("/kokonaistulokset/{kilpailuId}")
		public String luoKokonaistulokset(@PathVariable Long kilpailuId, Model model) {

	    leaderboardService.laskeLopputulokset(kilpailuId);
		 String kilid = kilpailuId.toString();

		 return "redirect:/datat/" + kilid;
	 }
	 
	@RequestMapping("/luodatat/{kilpailuId}")
	public String luoDataa(@PathVariable Long kilpailuId, Model model) {

	    Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		luoDatat(kilpailu);
		String kilid = kilpailuId.toString();

		return "redirect:/datat/" + kilid;
	}

	public void luoDatat(Kilpailu kilpailu) {

		Long finaaliin=new Long(0);
		Long kilpailuId = kilpailu.getkilpailuId();

		int indeksi = 0;

		List<Koulu> koulut = new ArrayList<Koulu>();
		
		for(int luku = 0; luku<koulunimet.length; luku++) {

			Koulu uusikoulu = new Koulu(koulunimet[luku], "kaupunki", kilpailuId);
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
		for(int luku = 1; luku < 3; luku++){
			int randomNum = ThreadLocalRandom.current().nextInt(0, 15 + 1);
			int randomNumm = ThreadLocalRandom.current().nextInt(0, 15 + 1);

			if(randomNum > 7) {
				finaaliin = new Long(1);
			} else {
				finaaliin = new Long(0);
			}
			Tuomari tuomari = new Tuomari("Tuomari " + luku, etunimet[randomNum], sukunimet[randomNumm], lohkonro, kilpailuId, finaaliin);
			tuomariRepository.save(tuomari);
		}
		
		    int randomNum = ThreadLocalRandom.current().nextInt(0, 15 + 1);
		    int randomNumm = ThreadLocalRandom.current().nextInt(0, 15 + 1);

			if(randomNum > 7) {
				finaaliin=new Long(1);
			} else {
				finaaliin=new Long(0);
			}

			Ostaja ostaja = new Ostaja(etunimet[randomNum], sukunimet[randomNumm], lohkonro, kilpailuId, finaaliin);
			ostajaRepository.save(ostaja);

		    indeksi += 5;

		}
	}
}
