package com.syntaxterror.bestseller.control;


        import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.service.LeaderboardService;

import java.util.ArrayList;
import java.util.Collections;
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


    @RequestMapping("/pisteet/{kilpailuId}")
    private String luoLeaderboard(@PathVariable Long kilpailuId, Model model){
    	model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
    	List<List<Arviointi>> tulokset = jaaTulokset(kilpailuId);
    	int i=1;
    	for (List<Arviointi> lohko : tulokset) {
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
    
    private List<List<Arviointi>> jaaTulokset(Long kilpailuId){
    	List<Arviointi> kaikki = leaderboardService.palautaParhaastaHuonoimpaan(kilpailuId);
    	List<Arviointi> eka = new ArrayList<Arviointi>();
    	List<Arviointi> toka = new ArrayList<Arviointi>();
    	List<Arviointi> kolmas = new ArrayList<Arviointi>();
    	List<Arviointi> neljas = new ArrayList<Arviointi>();
    	List<Arviointi> finaali = new ArrayList<Arviointi>();
    	for (Arviointi arviointi : kaikki) {
			int lohkoNro = Integer.parseInt(arviointi.getLohko().getLohkoNro());
			switch(lohkoNro) {
			case 1:
				eka.add(arviointi);
				break;
			case 2:
				toka.add(arviointi);
				break;
    		case 3:
    			kolmas.add(arviointi);
    			break;
    		case 4:
    			neljas.add(arviointi);
    			break;
			}
		}
    	finaali.add(eka.get(0)); finaali.add(toka.get(0)); finaali.add(kolmas.get(0)); finaali.add(neljas.get(0));
    	Collections.sort(finaali);
    	Collections.reverse(finaali);
    	List<List<Arviointi>> jaettu = new ArrayList<List<Arviointi>>();
    	jaettu.add(eka); jaettu.add(toka); jaettu.add(kolmas); jaettu.add(neljas); jaettu.add(finaali);
    	return jaettu;
    }
}
