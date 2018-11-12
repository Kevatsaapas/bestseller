package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Koulu;
import com.syntaxterror.bestseller.model.SignupForm;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;

@Controller
public class KilpailijaController {

    @Autowired
    public KilpailijaRepository kilpailijaRepository;
    
    @Autowired
    public TuomariRepository tuomariRepository;

    @Autowired
    public KilpailuRepository kilpailuRepository;

    @Autowired
    public LohkoRepository lohkoRepository;
    
    @Autowired
    public KouluRepository kouluRepository;


    @RequestMapping("/luokilpailija/{kilpailuId}")
    @Transactional
    public String luoKilpailija(Model model, @PathVariable Long kilpailuId, Kilpailija kilpailija) {
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
        model.addAttribute("kilpailu", kilpailu);
        model.addAttribute("koulut",kouluRepository.findByKilpailuId(kilpailuId));
        return "luokilpailija";

    }


    @RequestMapping("/editkilpailija/{kilpailijaId}")
    @Transactional
    public String editKilpailija(Model model, @PathVariable Long kilpailijaId) {
    	Kilpailija kilpailija = kilpailijaRepository.findByKilpailijaId(kilpailijaId);
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailija.getKilpailuId());
        model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
        model.addAttribute("kilpailu", kilpailu);
        model.addAttribute("kilpailija", kilpailija);
        model.addAttribute("koulut",kouluRepository.findByKilpailuId(kilpailija.getKilpailuId()));
        return "editkilpailija";

    }

    @RequestMapping(value = "/tallennakilpailija", method = RequestMethod.POST)
    @Transactional
    public String tallennaKilpailija(Kilpailija kilpailija) {
        kilpailijaRepository.save(kilpailija);
        System.out.println(kilpailija);
        String redirect = "redirect:/datat/"+ Long.toString(kilpailija.getKilpailuId());
        return redirect;
    }

    @RequestMapping(value = "/poistakilpailija/{kilpailijaId}", method = RequestMethod.GET)
    @Transactional
    public String poistaKilpailija(@PathVariable Long kilpailijaId) {
        Kilpailija kilpailija = kilpailijaRepository.findByKilpailijaId(kilpailijaId);
        kilpailijaRepository.deleteById(kilpailijaId);
        String redirect = "redirect:/datat/"+Long.toString(kilpailija.getKilpailuId());
        return redirect;
    }
    
    @RequestMapping(value = "/poistatuomari/{tuomariId}", method = RequestMethod.GET)
    public String poistaTuomari(@PathVariable Long tuomariId) {
        Tuomari tuomari = tuomariRepository.findByTuomariId(tuomariId);
        tuomariRepository.deleteById(tuomariId);
        String redirect = "redirect:/datat/"+Long.toString(tuomari.getKilpailuId());
        return redirect;
    }
    
    @RequestMapping("/luotuomari/{kilpailuId}")
    public String luoTuomari(Model model, @PathVariable Long kilpailuId, Tuomari tuomari) {
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
        model.addAttribute("kilpailu", kilpailu);
        return "luotuomari";
    }

    @RequestMapping("/edittuomari/{tuomariId}")
    public String luoTuomari(Model model, @PathVariable Long tuomariId) {
       Tuomari tuomari=tuomariRepository.findByTuomariId(tuomariId);
       Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(tuomari.getKilpailuId());
        model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
        model.addAttribute("kilpailu", kilpailu);
        model.addAttribute("tuomari", tuomari);
        return "edittuomari";
    }

    @RequestMapping(value = "/tallennatuomari", method = RequestMethod.POST)
    public String tallennaTuomari(Model model, Tuomari tuomari) {
        tuomariRepository.save(tuomari);
        model.addAttribute("tuomariId", tuomari.getTuomariId());
        System.out.println(tuomari);
        String redirect = "redirect:/luotuouser/"+ Long.toString(tuomari.getTuomariId());
        return redirect;
    }
    
    @RequestMapping(value = "/luotuouser/{tuomariId}")
    public String luoTuomarilleKayttaja(Model model, @PathVariable Long tuomariId){
    	SignupForm siform = new SignupForm();
    	String rooli="tuomari";
    	siform.setRooli(rooli);
    	siform.setRooliId(tuomariId);
    	System.out.println(siform.getRooli());
    	System.out.println(siform.getRooliId());
        model.addAttribute("signupform",siform);
        return "signup";
    }
    
    @RequestMapping("/luokoulu/{kilpailuId}")
    public String luoKoulu(Model model, Koulu koulu) {
    	model.addAttribute(koulu);
    	return "luokoulu";
    }
    
    @RequestMapping(value = "/tallennakoulu", method = RequestMethod.POST)
    public String tallennaKoulu(Koulu koulu) {
    	kouluRepository.save(koulu);
    	String redirect = "redirect:/datat/"+ Long.toString(koulu.getKilpailuId()); 
    	return redirect;
    }
    @RequestMapping(value = "/poistakoulu/{kouluId}", method = RequestMethod.GET)
    public String poistaKoulu(@PathVariable Long kouluId) {
        Koulu koulu = kouluRepository.findByKouluId(kouluId);
        kouluRepository.deleteById(kouluId);
        String redirect = "redirect:/datat/"+Long.toString(koulu.getKilpailuId());
        return redirect;
    }
    
    @RequestMapping("/editkoulu/{kouluId}")
    public String luoKoulu(Model model, @PathVariable Long kouluId) {
       Koulu koulu=kouluRepository.findByKouluId(kouluId);
       Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(koulu.getKilpailuId());
       model.addAttribute("kilpailu", kilpailu);
        model.addAttribute("koulu", koulu);
        return "editkoulu";
    }
}
