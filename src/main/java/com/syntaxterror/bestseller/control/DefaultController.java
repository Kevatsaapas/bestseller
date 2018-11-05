package com.syntaxterror.bestseller.control;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.model.User;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;
import com.syntaxterror.bestseller.repository.UserRepository;
import com.syntaxterror.bestseller.service.TuomariService;

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

    @Autowired
    private TuomariService tuomariService;



    @RequestMapping("/")
    public String index(Model model) {
    	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
    	User user = urepository.findByUsername(username);
    	Long rooliId = user.getrooliId();
    	String role = user.getRole();
    	String admin = "ADMIN";
    	if(role.equals(admin)) {
    	model.addAttribute("kilpailut", kilpailuRepository.findAll());
    	model.addAttribute("users", urepository.findAll());
        return "adminindex";
    	}else if(rooliId!=null){
    		Tuomari tuo = tuomarirepository.findByTuomariId(rooliId);
    		Kilpailu kilpailu=kilpailuRepository.findByKilpailuId(tuo.getKilpailuId());
    		model.addAttribute("kilpailu", kilpailu);
        	model.addAttribute("tuomari", tuo);
        	model.addAttribute("lohko", lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, tuo.getLohkoNro()));
            return "index";
    	}else {
    		return "norole";

    	}
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
		model.addAttribute("users", urepository.findAll());
        return "testaus";
    }
    
    
    @RequestMapping(value = "/tuomarointi/", method = RequestMethod.POST)
    public String palautaArviointiLuontiSivu(Model model, @RequestParam("lohkoId") Long lohkoId, @RequestParam("kilpailuId") Long kilpailuId, @RequestParam("tuomariId") Long tuomariId){
    	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
    	User user = urepository.findByUsername(username);
    	String role = user.getRole();
    	String admin = "ADMIN";

    	Lohko lohko=lohkoRepository.findByLohkoId(lohkoId);
    	Tuomari tuomari = tuomarirepository.findByTuomariId(tuomariId);
    	System.out.println(lohkoId);
        model.addAttribute("lohko", lohko);
    	model.addAttribute("arviointi", new Arviointi());
    	model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
        model.addAttribute("usertuomari", tuomari);


    	if(role.equals(admin)) {
    		List<Long> arvot = new ArrayList<Long>();
            for(int i=0; i<16; i++) {
            	int randomNum = ThreadLocalRandom.current().nextInt(0, 6 + 1);
            	Long arvo = new Long(randomNum+1);
            	arvot.add(arvo);
            }
            model.addAttribute("kilpailijat", kilpailijarepository.findByKilpailuIdAndLohko(kilpailuId, lohko));
            model.addAttribute("arvot", arvot);
            model.addAttribute("tuomarit", tuomarirepository.findByKilpailuIdAndLohkoNro(kilpailuId, lohko.getLohkoNro()));
            return "uusitest";
    	}else {
    		model.addAttribute("kilpailijat", tuomariService.haeKilpailijatTuomarille(tuomari, lohko));
    		return "tuomarointisivu";
    	}
    }

    @RequestMapping("/ostaja")
    public String ostaja(){
        return "ostaja";
    }

}
