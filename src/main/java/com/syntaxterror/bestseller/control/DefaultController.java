package com.syntaxterror.bestseller.control;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

@Controller
public class DefaultController {
    @Autowired
    public KilpailuRepository kilpailuRepository;
    @Autowired
    public KilpailijaRepository kilpailijarepository;
    @Autowired
    public TuomariRepository tuomarirepository;
    @Autowired
    public LohkoRepository lohkoRepository;

    @RequestMapping("/")
    public String index(Model model) {
    	model.addAttribute("kilpailut", kilpailuRepository.findAll());
        return "index";
    }
    
    @RequestMapping("/valitselohko/{kilpailuId}")
    public String dataa(@PathVariable Long kilpailuId, Model model) {
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        model.addAttribute("kilpailu", kilpailu);
        Iterable<Lohko> lohkot = lohkoRepository.findByKilpailu(kilpailu);
        model.addAttribute("lohkot", lohkot);
        return "valitselohko";
    }
    
    @RequestMapping("/valikko/{kilpailuId}/{lohkoId}")
    public String valikko(Model model, @PathVariable Long kilpailuId, @PathVariable Long lohkoId) {
        model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
        Lohko valittulohko=lohkoRepository.findByLohkoId(lohkoId);
        model.addAttribute("lohko", valittulohko);
    	return "valikko";
    }
    
    @RequestMapping("/testaus")
    public String testaus(Model model) {
        model.addAttribute("kilpailut", kilpailuRepository.findAll());
        return "testaus";
    }

    @RequestMapping("/tuomarointi")
    public String tuomarointi(Model model){
    	model.addAttribute("kilpailijat", kilpailijarepository.findByKilpailuId(new Long(1)));
    	model.addAttribute("tuomarit", tuomarirepository.findByKilpailuId(new Long(1)));
        return "tuomarointi";
    }

    @RequestMapping("/ostaja")
    public String ostaja(){
        return "ostaja";
    }

}
