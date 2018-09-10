package com.syntaxterror.bestseller.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;

@Controller
public class DefaultController {
	
	@Autowired
	public KilpailuRepository krepo;
	
	@Autowired
	public KilpailijaRepository kilrepo;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/datat/{kilpailuId}")
	public String dataa(@PathVariable Long kilpailuId, Model model) {
		Kilpailu kilpailu = krepo.findByKilpailuId(kilpailuId);
		model.addAttribute("kilpailu",kilpailu);
		Iterable<Kilpailija> kilpailijat = kilrepo.findByKilpailuId(kilpailuId);
		model.addAttribute("kilpailijat", kilpailijat);
		return "datat";
	}
}
