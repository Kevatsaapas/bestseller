package com.syntaxterror.bestseller.control;



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
		model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
		model.addAttribute("kilpailu", kilpailu);
		model.addAttribute("koulut", kouluRepository.findByKilpailuId(kilpailuId));

		return "luokilpailija";

	}

	@Secured("ADMIN")
	@RequestMapping("/editkilpailija/{kilpailijaId}")
	public String editKilpailija(Model model, @PathVariable Long kilpailijaId) {
		Kilpailija kilpailija = kilpailijaRepository.findByKilpailijaId(kilpailijaId);
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailija.getKilpailuId());
		model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
		model.addAttribute("kilpailu", kilpailu);
		model.addAttribute("kilpailija", kilpailija);
		model.addAttribute("koulut", kouluRepository.findByKilpailuId(kilpailija.getKilpailuId()));

		return "editkilpailija";

	}

	@Secured("ADMIN")
	@RequestMapping(value = "/tallennakilpailija", method = RequestMethod.POST)
	public String tallennaKilpailija(Kilpailija kilpailija) {
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
