package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

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
}
