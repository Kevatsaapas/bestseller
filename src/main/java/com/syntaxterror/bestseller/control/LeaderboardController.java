package com.syntaxterror.bestseller.control;


import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
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

@Controller
public class LeaderboardController {

    @Autowired
    public LeaderboardService leaderboardService;
    @Autowired
    public KilpailuRepository kilpailuRepository;
    @Autowired
    public KilpailijaRepository kilpailijaRepository;


    @RequestMapping("/pisteet/{kilpailuId}")
    private String luoLeaderboard(@PathVariable Long kilpailuId, Model model){
    	model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
    	List<List<Kilpailija>> tulokset = jaaTulokset(kilpailuId);
    	int i=1;
    	for (List<Kilpailija> lohko : tulokset) {
    		if(i<5) {
    		String nimi = "lohko"+Integer.toString(i);
    		model.addAttribute(nimi, lohko);
    		System.out.println(nimi);
    		i++;
    		}
    	}
    	model.addAttribute("finaali", tulokset.get(4));
        return "pisteet";
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
