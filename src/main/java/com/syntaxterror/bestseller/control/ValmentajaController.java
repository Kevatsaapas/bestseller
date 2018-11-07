package com.syntaxterror.bestseller.control;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Valmentaja;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import com.syntaxterror.bestseller.repository.ValmentajaRepository;

@Controller
public class ValmentajaController {

	 @Autowired
	    public KilpailuRepository kilpailuRepository;
	 
	 @Autowired
	  public KouluRepository kouluRepository;
	 
	 @Autowired
	    public ValmentajaRepository valmentajaRepository;
	 
	 @RequestMapping("/luovalmentaja/{kilpailuId}")
	 @Transactional
	 
	 public String luoValmentaja(Model model, @PathVariable Long kilpailuId, Valmentaja valmentaja) {
	        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
	        model.addAttribute("kilpailu", kilpailu);
	        model.addAttribute("koulut",kouluRepository.findByKilpailuId(kilpailuId));
	        return "luovalmentaja";

	    }
	 
	 
	 @RequestMapping(value = "/tallennavalmentaja", method = RequestMethod.POST)
	    @Transactional
	    public String tallennaValmentaja(Valmentaja valmentaja) {
	        valmentajaRepository.save(valmentaja);
	        System.out.println(valmentaja);
	        String redirect = "redirect:/datat/"+ Long.toString(valmentaja.getKilpailuId());
	        return redirect;
	    }
	 
	 
	 @RequestMapping("/editvalmentaja/{valmentajaId}")
	    @Transactional
	    public String editValmentaja(Model model, @PathVariable Long valmentajaId) {
	    	Valmentaja valmentaja = valmentajaRepository.findByValmentajaId(valmentajaId);
	        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(valmentaja.getKilpailuId());
	        model.addAttribute("kilpailu", kilpailu);
	        model.addAttribute("valmentaja", valmentaja);
	        model.addAttribute("koulut",kouluRepository.findByKilpailuId(valmentaja.getKilpailuId()));
	        return "editvalmentaja";

	    }
	 
	 @RequestMapping(value = "/poistavalmentaja/{valmentajaId}", method = RequestMethod.GET)
	    @Transactional
	    public String poistaKilpailija(@PathVariable Long valmentajaId) {
		 Valmentaja valmentaja = valmentajaRepository.findByValmentajaId(valmentajaId);
	        valmentajaRepository.deleteById(valmentajaId);
	        String redirect = "redirect:/datat/"+Long.toString(valmentaja.getKilpailuId());
	        return redirect;
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
