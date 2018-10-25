package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
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

    @RequestMapping("/luokilpailija/{kilpailuId}")
    @Transactional
    public String luoKilpailija(Model model, @PathVariable Long kilpailuId, Kilpailija kilpailija) {
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
        model.addAttribute("kilpailu", kilpailu);
        return "luokilpailija";

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
    @RequestMapping(value = "/tallennatuomari", method = RequestMethod.POST)
    public String tallennaTuomari(Tuomari tuomari) {
    	tuomariRepository.save(tuomari);
        System.out.println(tuomari);
        String redirect = "redirect:/datat/"+ Long.toString(tuomari.getKilpailuId());
        return redirect;
    }
}
