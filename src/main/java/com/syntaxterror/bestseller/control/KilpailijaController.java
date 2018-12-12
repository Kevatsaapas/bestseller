package com.syntaxterror.bestseller.control;



import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;

@Controller
@Transactional
public class KilpailijaController {

	@Autowired
	private KilpailijaRepository kilpailijaRepository;

	@Autowired
	private KilpailuRepository kilpailuRepository;

	@Autowired
	private LohkoRepository lohkoRepository;

	@Autowired
	private KouluRepository kouluRepository;

	@Secured("ADMIN")
	@RequestMapping("/luokilpailija/{kilpailuId}")
	public String luoKilpailija(Model model, @PathVariable Long kilpailuId, Kilpailija kilpailija) {
		
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		List<Lohko> lohkot = lohkoRepository.findByKilpailu(kilpailu);
		for(int i=0; i<lohkot.size(); i++) {
			if(lohkot.get(i).getLohkoNro().equals("finaali")) {
				lohkot.remove(i);
			}
		}
		model.addAttribute("lohkot", lohkot);
		model.addAttribute("kilpailu", kilpailu);
		model.addAttribute("koulut", kouluRepository.findByKilpailuId(kilpailuId));

		return "luokilpailija";

	}

	@Secured("ADMIN")
	@RequestMapping("/editkilpailija/{kilpailijaId}")
	public String editKilpailija(Model model, @PathVariable Long kilpailijaId) {
		Kilpailija kilpailija = kilpailijaRepository.findByKilpailijaId(kilpailijaId);
		System.out.println(kilpailija.getKilHash());
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailija.getKilpailuId());
		List<Lohko> lohkot = lohkoRepository.findByKilpailu(kilpailu);
		for(int i=0; i<lohkot.size(); i++) {
			if(lohkot.get(i).getLohkoNro().equals("finaali")) {
				lohkot.remove(i);
			}
		}
		model.addAttribute("lohkot", lohkot);
		model.addAttribute("kilpailu", kilpailu);
		model.addAttribute("kilpailija", kilpailija);
		model.addAttribute("koulut", kouluRepository.findByKilpailuId(kilpailija.getKilpailuId()));

		return "editkilpailija";

	}

	@Secured("ADMIN")
	@RequestMapping(value = "/tallennakilpailija", method = RequestMethod.POST)
	public String tallennaKilpailija(Kilpailija kilpailija) {
		String kilHash = UUID.randomUUID().toString();
		kilHash.replace("-", "");
		kilpailija.setKilHash(kilHash);
		kilpailijaRepository.save(kilpailija);
		String redirect = "redirect:/datat/" + Long.toString(kilpailija.getKilpailuId());

		return redirect;
	}

	@Secured("ADMIN")
	@RequestMapping(value = "/poistakilpailija/{kilpailijaId}", method = RequestMethod.GET)
	public String poistaKilpailija(@PathVariable Long kilpailijaId) {
		Kilpailija kilpailija = kilpailijaRepository.findByKilpailijaId(kilpailijaId);
		kilpailijaRepository.deleteById(kilpailijaId);
		String redirect = "redirect:/datat/" + Long.toString(kilpailija.getKilpailuId());

		return redirect;
	}
}
