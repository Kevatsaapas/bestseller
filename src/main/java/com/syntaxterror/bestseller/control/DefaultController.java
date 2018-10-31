package com.syntaxterror.bestseller.control;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;
import com.syntaxterror.bestseller.repository.UserRepository;

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
    @Autowired
    private UserRepository urepository;

    @RequestMapping("/")
    public String index(Model model) {
    	model.addAttribute("kilpailut", kilpailuRepository.findAll());
    	model.addAttribute("users", urepository.findAll());
        return "index";
    }
    
    @RequestMapping(value = "/login")
	public String showLogin() {
		return "login";
	}
    
    @RequestMapping(value="/kilpailuvalittu/", method=RequestMethod.POST)
    public String dataa(@RequestParam("kilpailuId")Long kilpailuId, Model model) {
    	System.out.println(kilpailuId);
    	Kilpailu valittukilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        model.addAttribute("kilpailu", valittukilpailu);
        Iterable<Lohko> lohkot = lohkoRepository.findByKilpailu(valittukilpailu);
        model.addAttribute("lohkot", lohkot);
        return "valitselohko";
    }
    
    @RequestMapping(value="/lohkovalittu/", method=RequestMethod.POST)
    public String valikko(Model model, @RequestParam("kilpailuId") Long kilpailuId,@RequestParam("lohkoId") Long lohkoId) {
        model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
        Lohko valittulohko=lohkoRepository.findByLohkoId(lohkoId);
        System.out.println(valittulohko);
        model.addAttribute("lohko", valittulohko);
    	return "valikko";
    }
    
    @RequestMapping("/testaus")
    public String testaus(Model model) {
        model.addAttribute("kilpailut", kilpailuRepository.findAll());
        return "testaus";
    }
    
    
    @RequestMapping(value = "/tuomarointi/", method = RequestMethod.POST)
    public String palautaArviointiLuontiSivu(Model model, @RequestParam("lohkoId") Long lohkoId, @RequestParam("kilpailuId") Long kilpailuId){
    	Lohko lohko=lohkoRepository.findByLohkoId(lohkoId);
    	System.out.println(lohkoId);
        model.addAttribute("lohko", lohko);
    	model.addAttribute("arviointi", new Arviointi());
    	model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
        model.addAttribute("kilpailijat", kilpailijarepository.findByLohko(lohko));
        model.addAttribute("tuomarit", tuomarirepository.findByKilpailuIdAndLohkoNro(kilpailuId, lohko.getLohkoNro()));
        

        return "tuomarointisivu";
    }

    @RequestMapping("/ostaja")
    public String ostaja(){
        return "ostaja";
    }

}
