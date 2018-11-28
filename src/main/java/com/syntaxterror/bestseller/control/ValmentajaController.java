package com.syntaxterror.bestseller.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Valmentaja;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import com.syntaxterror.bestseller.repository.ValmentajaRepository;

@Controller
@Transactional
public class ValmentajaController {

	@Autowired
	private KilpailuRepository kilpailuRepository;

	@Autowired
	private KouluRepository kouluRepository;

	@Autowired
	private ValmentajaRepository valmentajaRepository;

	@RequestMapping("/luovalmentaja/{kilpailuId}")
	public String luoValmentaja(Model model, @PathVariable Long kilpailuId, Valmentaja valmentaja) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		model.addAttribute("kilpailu", kilpailu);
		model.addAttribute("koulut", kouluRepository.findByKilpailuId(kilpailuId));
		return "luovalmentaja";

	}

	@RequestMapping(value = "/tallennavalmentaja", method = RequestMethod.POST)
	public String tallennaValmentaja(Valmentaja valmentaja) {
		valmentajaRepository.save(valmentaja);
		String redirect = "redirect:/datat/" + Long.toString(valmentaja.getKilpailuId());
		return redirect;
	}

	@RequestMapping("/editvalmentaja/{valmentajaId}")
	public String editValmentaja(Model model, @PathVariable Long valmentajaId) {
		Valmentaja valmentaja = valmentajaRepository.findByValmentajaId(valmentajaId);
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(valmentaja.getKilpailuId());
		model.addAttribute("kilpailu", kilpailu);
		model.addAttribute("valmentaja", valmentaja);
		model.addAttribute("koulut", kouluRepository.findByKilpailuId(valmentaja.getKilpailuId()));
		return "editvalmentaja";

	}

	@RequestMapping(value = "/poistavalmentaja/{valmentajaId}", method = RequestMethod.GET)
	public String poistaKilpailija(@PathVariable Long valmentajaId) {
		Valmentaja valmentaja = valmentajaRepository.findByValmentajaId(valmentajaId);
		valmentajaRepository.deleteById(valmentajaId);
		String redirect = "redirect:/datat/" + Long.toString(valmentaja.getKilpailuId());
		return redirect;
	}

}
