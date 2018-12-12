package com.syntaxterror.bestseller.control;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Koulu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.service.ArviointiService;
import com.syntaxterror.bestseller.service.LeaderboardService;

@Controller
@Transactional
public class KilpailuController {

	@Autowired
	private KilpailuRepository kilpailuRepository;

	@Autowired
	private LohkoRepository lohkoRepository;
	
	@Autowired
	private KouluRepository kouluRepository;

	@Autowired
	private KilpailijaRepository kilpailijaRepository;


	@Autowired
	private ArviointiService arviointiservice;

	@Autowired
	private LeaderboardService leaderboardService;

	@Secured("ADMIN")
	@RequestMapping("/luokilpailu")
	public String luoKilpailu(Model model, Kilpailu kilpailu) {
		model.addAttribute(kilpailu);
		return "luonti";
	}

	@Secured("ADMIN")
	@RequestMapping("/avaa/{kilpailuId}")
	public String avaaKilpailu(@PathVariable Long kilpailuId) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		kilpailu.setAuki(new Long(1));
		kilpailuRepository.save(kilpailu);
		return "redirect:/testaus/";
	}

	@Secured("ADMIN")
	@RequestMapping("/sulje/{kilpailuId}")
	public String suljeKilpailu(@PathVariable Long kilpailuId) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		kilpailu.setAuki(new Long(0));
		kilpailuRepository.save(kilpailu);
		return "redirect:/testaus/";
	}

	@Secured("ADMIN")
	@RequestMapping("/editkilpailu/{kilpailuId}")
	public String editKilpailu(Model model, @PathVariable Long kilpailuId) {
		model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
		return "editkilpailu";
	}

	@Secured("ADMIN")
	@RequestMapping("/updatekilpailu")
	public String updateKilpailu(Model model, Kilpailu kilpailu) {
		kilpailuRepository.save(kilpailu);
		return "redirect:testaus/";
	}

	@RequestMapping("/siirryfinaaliin/{kilpailuId}")
	public String siirryFinaaliin(@PathVariable Long kilpailuId) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		leaderboardService.laskeLopputulokset(kilpailuId);
		List<Kilpailija> kilpailijat = leaderboardService.palautaFinalistit(kilpailuId);
		for (Kilpailija kilpailija : kilpailijat) {
			kilpailija.setFinaalissa(new Long(1));
			kilpailijaRepository.save(kilpailija);
		}
		kilpailu.setFinaali(new Long(1));
		kilpailu.setAuki(new Long(0));
		kilpailuRepository.save(kilpailu);
		return "redirect:/datat/" + kilpailuId.toString();
	}

	@RequestMapping("/arvioifinaali/{kilpailuId}")
	public String arvioiFinaali(@PathVariable Long kilpailuId) {
		leaderboardService.laskeFinaalinLopputulokset(kilpailuId);
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		kilpailu.setAuki(new Long(0));
		kilpailuRepository.save(kilpailu);
		return "redirect:/finaalidatat/" + kilpailuId.toString();
	}

	private void luoLohkot(Long kilpailuId) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		for (int i = 1; i < 5; i++) {
			Lohko loh = new Lohko((Integer.toString(i)), kilpailu, null);
			lohkoRepository.save(loh);
		}
		Lohko loh = new Lohko("finaali", kilpailu, null);
		lohkoRepository.save(loh);
		
		Koulu hh1 = new Koulu("Haaga-Helia AMK","", kilpailuId);
		kouluRepository.save(hh1);
		
		Koulu l1 = new Koulu("Laurea AMK", "", kilpailuId);
		kouluRepository.save(l1);
		
		Koulu tamk = new Koulu("Tampereen AMK", "", kilpailuId);
		kouluRepository.save(tamk);
		
		Koulu jamk = new Koulu("Jyväskylän AMK", "", kilpailuId);
		kouluRepository.save(jamk);
		
		Koulu xamk = new Koulu("Kaakkois-Suomen AMK", "", kilpailuId);
		kouluRepository.save(xamk);
		
		Koulu tuamk = new Koulu("Turku AMK", "", kilpailuId);
		kouluRepository.save(tuamk);
		
	}

	@RequestMapping(value = "/tallennakilpailu", method = RequestMethod.POST)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss.SSSZ")
	public String tallennaKilpailu(Kilpailu kilpailu) throws ParseException {
		Long nolla = new Long(0);
		kilpailu.setAuki(nolla);
		kilpailu.setFinaali(nolla);
		kilpailu.setValmis(nolla);
		kilpailuRepository.save(kilpailu);
		luoLohkot(kilpailu.getKilpailuId());
		return "redirect:/testaus";
	}

	@RequestMapping(value = "/poistakilpailu/{kilpailuId}", method = RequestMethod.GET)
	public String poistaKilpailu(@PathVariable Long kilpailuId) {
		arviointiservice.nuke(kilpailuId);
		return "redirect:/testaus";
	}

}
