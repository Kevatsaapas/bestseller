package com.syntaxterror.bestseller.control;


import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Koulu;
import com.syntaxterror.bestseller.model.OstajaArviointi;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import com.syntaxterror.bestseller.repository.OstajaArviointiRepository;
import com.syntaxterror.bestseller.service.ArviointiService;
import com.syntaxterror.bestseller.service.LeaderboardService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LeaderboardController {

    @Autowired
    public LeaderboardService leaderboardService;
    @Autowired
    public ArviointiService arviointiService;
    @Autowired
    public KilpailuRepository kilpailuRepository;
    @Autowired
    public KilpailijaRepository kilpailijaRepository;
    @Autowired
    public ArviointiRepository arviointiRepository;
    @Autowired
    public OstajaArviointiRepository ostajaArviointiRepository;
    @Autowired
    public KouluRepository kouluRepository;


    @RequestMapping("/pisteet/{kilpailuId}")
    private String luoLeaderboard(@PathVariable Long kilpailuId, Model model){
    	model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
    	List<List<Kilpailija>> tulokset = jaaTulokset(kilpailuId);
    	int i=1;
    	for (List<Kilpailija> lohko : tulokset) {
    		if(i<5) {
    		String nimi = "lohko"+Integer.toString(i);
    		model.addAttribute(nimi, lohko);
    		i++;
    		}
    	}
    	model.addAttribute("finaali", tulokset.get(4));
        return "pisteet";
    }
    
    
    @RequestMapping("/seuranta")
    private String Seuranta(Model model){
    	List<Kilpailu> kilpailut = kilpailuRepository.findByAuki(new Long(1));
    	if(kilpailut.size()>0) {
    		model.addAttribute("kilpailut", kilpailut);
            return "seurantavalikko";
    	}else {
        return "eiseurattavaa";
    	}
    }
    
    @RequestMapping("/sahkopostivalmentaja/{kilpailuId}")
    private String Seuranta(@PathVariable Long kilpailuId, Model model){
    	Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
    	Koulu koulu = kouluRepository.findByKouluId(new Long(297));
    	List<Kilpailija> kilpailijat = kilpailijaRepository.findByKoulu(koulu);
    	model.addAttribute("kilpailu", kilpailu);
    	model.addAttribute("kilpailijat", kilpailijat);
        return "sahkoposti_valmentaja";
    	
    }
    
    @RequestMapping("/seuraa")
    private String Seuraaa(@RequestParam("kilpailuId")Long kilpailuId, Model model){
    	Kilpailu kilpailu =  kilpailuRepository.findByKilpailuId(kilpailuId);
    	model.addAttribute("kilpailu",kilpailu);
    	if(kilpailu.getFinaali().equals(new Long(0))) {
    	List<Arviointi> arvioinnit = arviointiRepository.findByKilpailuId(kilpailuId);
    	int arviointiLkm = arvioinnit.size();
    	int arviointiTotal = arviointiService.laskeArviointienSumma(kilpailuId);
    	model.addAttribute("arviointiLkm", arviointiLkm);
    	model.addAttribute("arviointiTotal", arviointiTotal);
    	int arpro = (arviointiLkm/arviointiTotal)*100;
    	String arviointiProsentti = arpro+"%";
    	model.addAttribute("arviointiProsentti", arviointiProsentti);
    	
    	List<OstajaArviointi> ostajaArvioinnit = ostajaArviointiRepository.findByKilpailuId(kilpailuId);
    	int ostajaArviointiLkm = ostajaArvioinnit.size();
    	int ostajaArviointiTotal = arviointiService.laskeOstajaArviointienSumma(kilpailuId);
    	model.addAttribute("ostajaArviointiLkm", ostajaArviointiLkm);
    	model.addAttribute("ostajaArviointiTotal", ostajaArviointiTotal);
    	int ostajaArviointiProsenttii = (ostajaArviointiLkm/ostajaArviointiTotal)*100;
    	String ostajaArviointiProsentti = ostajaArviointiProsenttii+"%";
    	model.addAttribute("ostajaArviointiProsentti", ostajaArviointiProsentti);
    	
    	model.addAttribute("totalProsentti", (arviointiLkm+ostajaArviointiLkm)/(arviointiTotal+ostajaArviointiTotal)*100+"%");
    	}
        return "seuranta";
    }
    
    private List<List<Kilpailija>> jaaTulokset(Long kilpailuId){
    	List<Kilpailija> kaikki = leaderboardService.palautaParhaastaHuonoimpaan(kilpailuId);
    	List<Kilpailija> eka = new ArrayList<Kilpailija>();
    	List<Kilpailija> toka = new ArrayList<Kilpailija>();
    	List<Kilpailija> kolmas = new ArrayList<Kilpailija>();
    	List<Kilpailija> neljas = new ArrayList<Kilpailija>();
    	List<Kilpailija> finaali = new ArrayList<Kilpailija>();
    	for (Kilpailija kilpailija : kaikki) {
			int lohkoNro = Integer.parseInt(kilpailija.getLohko().getLohkoNro());
			switch(lohkoNro) {
			case 1:
				eka.add(kilpailija);
				break;
			case 2:
				toka.add(kilpailija);
				break;
    		case 3:
    			kolmas.add(kilpailija);
    			break;
    		case 4:
    			neljas.add(kilpailija);
    			break;
			}
		}
    	finaali.add(eka.get(0)); finaali.add(toka.get(0)); finaali.add(kolmas.get(0)); finaali.add(neljas.get(0));
    	Collections.sort(finaali, new Comparator<Kilpailija>() {
            @Override
            public int compare(Kilpailija kilpailija2, Kilpailija kilpailija1)
            {

                return  Double.compare(kilpailija1.getKokonaistulos(), kilpailija2.getKokonaistulos());
            }
        });
    	List<List<Kilpailija>> jaettu = new ArrayList<List<Kilpailija>>();
    	jaettu.add(eka); jaettu.add(toka); jaettu.add(kolmas); jaettu.add(neljas); jaettu.add(finaali);
    	return jaettu;
    }
}
