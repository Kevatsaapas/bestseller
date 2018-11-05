package com.syntaxterror.bestseller.service;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.model.util.Tarvekartoitus;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TuomariService {

    @Autowired
    private TuomariRepository tuomariRepository;
    
    @Autowired
    private KilpailijaRepository kilpailijaRepository;
    
    @Autowired
    private ArviointiRepository arviointiRepository;

    public List<Kilpailija> haeKilpailijatTuomarille(Tuomari tuomari, Lohko lohko){

        Iterable<Kilpailija> kilp = kilpailijaRepository.findByKilpailuIdAndLohko(tuomari.getKilpailuId(), lohko);
        List<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
        List<Arviointi> arvioinnit= arviointiRepository.findByTuomari(tuomari);
        kilp.forEach(kilpailija ->{
        	Boolean match = false;
        	for(int i=0; i<arvioinnit.size(); i++) {
        		Arviointi arviointi = arvioinnit.get(i);
        		if(kilpailija.equals(arviointi.getKilpailija())) {
        			match=true;
        		}
        	}
        	if(!match) {
        		kilpailijat.add(kilpailija);
        	}
        });
        return kilpailijat;
    }

}
