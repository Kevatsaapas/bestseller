package com.syntaxterror.bestseller.control;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;

@Controller
public class DefaultController {

	@Autowired
	public KilpailuRepository krepo;

	@Autowired
	public LohkoRepository lohrepo;

	@Autowired
	public KilpailijaRepository kilrepo;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("kilpailut", krepo.findAll());
		return "index";
	}

	@RequestMapping("/luokilpailu")
	public String luoKilpailu(Model model, Kilpailu kilpailu) {
		model.addAttribute(kilpailu);
		return "luonti";
	}

	@RequestMapping("/luokilpailija/{kilpailuId}")
	public String luoKilpailija(Model model, @PathVariable Long kilpailuId, Kilpailija kilpailija) {
		Kilpailu kilpailu = krepo.findByKilpailuId(kilpailuId);
		model.addAttribute("lohkot", lohrepo.findByKilpailu(kilpailu));
		model.addAttribute("kilpailu", kilpailu);
		return "luokilpailija";
	}

	@RequestMapping(value = "/tallennakilpailu", method = RequestMethod.POST)
	public String tallennaKilpailu(Kilpailu kilpailu) {
		Date pvm = new Date();
		kilpailu.setPvm(pvm);
		krepo.save(kilpailu);
		luoLohkot(kilpailu.getKilpailuId());
		System.out.println(kilpailu);
		return "redirect:/";
	}

	@RequestMapping(value = "/tallennakilpailija", method = RequestMethod.POST)
	public String tallennaKilpailija(Kilpailija kilpailija) {
		kilrepo.save(kilpailija);
		System.out.println(kilpailija);
		String redirect = "redirect:/datat/"+Long.toString(kilpailija.getKilpailuId());
		return redirect;
	}
	
	@RequestMapping(value = "/poistakilpailija/{kilpailijaId}", method = RequestMethod.GET)
	public String poistaKilpailija(@PathVariable Long kilpailijaId) {
		Kilpailija kilpailija = kilrepo.findByKilpailijaId(kilpailijaId);
		kilrepo.deleteById(kilpailijaId);
		String redirect = "redirect:/datat/"+Long.toString(kilpailija.getKilpailuId());
		return redirect;
	}
	
	
	@RequestMapping(value = "/poistakilpailu/{kilpailuId}", method = RequestMethod.GET)
	public String poistaKilpailu(@PathVariable Long kilpailuId) {
		kilrepo.deleteByKilpailuId(kilpailuId);
		Kilpailu kilpailu= krepo.findByKilpailuId(kilpailuId);
		lohrepo.deleteByKilpailu(kilpailu);
		krepo.deleteById(kilpailuId);
		return "redirect:/";
	}

	@RequestMapping("/datat/{kilpailuId}")
	public String dataa(@PathVariable Long kilpailuId, Model model) {
		Kilpailu kilpailu = krepo.findByKilpailuId(kilpailuId);
		model.addAttribute("kilpailu", kilpailu);
		Iterable<Kilpailija> kilpailijat = kilrepo.findByKilpailuId(kilpailuId);
		model.addAttribute("kilpailijat", kilpailijat);
		Iterable<Lohko> lohkot = lohrepo.findByKilpailu(kilpailu);
		model.addAttribute("lohkot", lohkot);
		return "datat";
	}

	public void luoLohkot(Long kilpailuId) {
		Kilpailu kilpailu = krepo.findByKilpailuId(kilpailuId);
		for (int i = 1; i < 5; i++) {
			Lohko loh = new Lohko((Integer.toString(i)), kilpailu, null);
			lohrepo.save(loh);
			System.out.println(loh);
		}
		Lohko loh = new Lohko("finaali", kilpailu, null);
		lohrepo.save(loh);
		System.out.println(loh);
		krepo.save(kilpailu);

	}
}
