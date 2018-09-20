package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.OsaAlue;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.OsaAlueRepository;
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
    public OsaAlueRepository osaaluerepository;

    @RequestMapping("/datat/{kilpailuId}")
    public String dataa(@PathVariable Long kilpailuId, Model model) {
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        model.addAttribute("kilpailu", kilpailu);
        Iterable<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuId(kilpailuId);
        model.addAttribute("kilpailijat", kilpailijat);
        Iterable<Lohko> lohkot = lohkoRepository.findByKilpailu(kilpailu);
        model.addAttribute("lohkot", lohkot);
        model.addAttribute("tuomarit", tuomariRepository.findByKilpailuId(kilpailuId));
        Iterable<OsaAlue> alueet = osaaluerepository.findByKilpailuId(kilpailuId);
        model.addAttribute("osaalueet", alueet);
        return "datat";
    }
}
