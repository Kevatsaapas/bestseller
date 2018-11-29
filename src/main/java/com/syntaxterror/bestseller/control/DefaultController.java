package com.syntaxterror.bestseller.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.Ostaja;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.model.User;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.OstajaRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;
import com.syntaxterror.bestseller.repository.UserRepository;
import com.syntaxterror.bestseller.service.TuomariService;

@Controller
@Transactional
public class DefaultController {

	@Autowired
	private KilpailuRepository kilpailuRepository;

	@Autowired
	private TuomariRepository tuomarirepository;

	@Autowired
	private LohkoRepository lohkoRepository;
	
	@Autowired
	private ArviointiRepository arviointiRepository;

	@Autowired
	private UserRepository urepository;

	@Autowired
	private TuomariService tuomariService;

	@Autowired
	private OstajaRepository ostajaRepository;

	@RequestMapping("/")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = urepository.findByUsername(username);
		Long rooliId = user.getrooliId();
		String rooli = user.getRooli();
		String role = user.getRole();
		String admin = "ADMIN";

		if (role.equals(admin)) {

			model.addAttribute("kilpailut", kilpailuRepository.findAll());
			model.addAttribute("users", urepository.findAll());
			return "redirect:/testaus";

		} else if (rooliId != null && rooli.equals("tuomari")) {

			Tuomari tuo = tuomarirepository.findByTuomariId(rooliId);
			Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(tuo.getKilpailuId());
			model.addAttribute("kilpailu", kilpailu);
			model.addAttribute("tuomari", tuo);
			Lohko lohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, tuo.getLohkoNro());
			Lohko finaalilohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, "finaali");
			model.addAttribute("lohko", lohko);
			Boolean valmis = tuomariService.onkotuomariValmis(tuo, lohko);
			if(kilpailu.getFinaali()==0) {
			List<Arviointi> arvioinnit = arviointiRepository.findByTuomariAndLohko(tuo, lohko);
			model.addAttribute("arvioinnit", arvioinnit);
			}else {
				List<Arviointi> arvioinnit = arviointiRepository.findByTuomariAndLohko(tuo, finaalilohko);
				model.addAttribute("arvioinnit", arvioinnit);
			}
			if (valmis) {
				model.addAttribute("valmis", 1);
			} else {
				model.addAttribute("valmis", 0);
			}

			return "index";

		} else if (rooliId != null && rooli.equals("ostaja")) {
			Ostaja ost = ostajaRepository.findByOstajaId(rooliId);
			Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(ost.getKilpailuId());
			model.addAttribute("kilpailu", kilpailu);
			model.addAttribute("ostaja", ost);
			Lohko lohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, ost.getLohkoNro());
			model.addAttribute("lohko", lohko);
			Boolean valmis = tuomariService.onkoOstajaValmis(ost, lohko);

			if (valmis) {
				model.addAttribute("valmis", 1);
			} else {
				model.addAttribute("valmis", 0);
			}
			return "ostajaindex";
		} else {
			return "norole";
		}
	}

	@RequestMapping(value = "/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/testaus")
	public String testaus(Model model) {
		model.addAttribute("kilpailut", kilpailuRepository.findAll());
		model.addAttribute("users", urepository.findAll());

		return "testaus";
	}

	@RequestMapping(value = "/tuomarointi/", method = RequestMethod.POST)
	public String palautaArviointiLuontiSivu(Model model, @RequestParam("lohkoId") Long lohkoId,
			@RequestParam("kilpailuId") Long kilpailuId, @RequestParam("tuomariId") Long tuomariId) {
		Lohko lohko = lohkoRepository.findByLohkoId(lohkoId);
		Tuomari tuomari = tuomarirepository.findByTuomariId(tuomariId);
		model.addAttribute("lohko", lohko);
		model.addAttribute("arviointi", new Arviointi());
		model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
		model.addAttribute("usertuomari", tuomari);

		model.addAttribute("kilpailijat", tuomariService.haeKilpailijatTuomarille(tuomari, lohko));

		return "tuomarointisivu";
	}
	
	
	@RequestMapping(value = "/editArviointi/", method = RequestMethod.POST)
	public String palautaArviointiLuontiSivu(Model model, @RequestParam("arviointiId") Long arviointiId) {
		Arviointi arviointi = arviointiRepository.findByArviointiId(arviointiId);
		model.addAttribute("lohko", arviointi.getLohko());
		model.addAttribute("arviointi", arviointi);
		model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(arviointi.getKilpailuId()));
		model.addAttribute("usertuomari", arviointi.getTuomari());

		model.addAttribute("kilpailija", arviointi.getKilpailija());

		return "tuomarointieditsivu";
	}
	

	@RequestMapping(value = "/finaalituomarointi/", method = RequestMethod.POST)
	public String palautaFinaalinArviointiLuontiSivu(Model model, @RequestParam("lohkoId") Long lohkoId,
			@RequestParam("kilpailuId") Long kilpailuId, @RequestParam("tuomariId") Long tuomariId) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		Lohko lohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, "finaali");
		Tuomari tuomari = tuomarirepository.findByTuomariId(tuomariId);
		model.addAttribute("lohko", lohko);
		model.addAttribute("arviointi", new Arviointi());
		model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
		model.addAttribute("usertuomari", tuomari);
		model.addAttribute("kilpailijat", tuomariService.haeFinalistitTuomarille(tuomari, lohko));

		return "tuomarointisivu";
	}

}
