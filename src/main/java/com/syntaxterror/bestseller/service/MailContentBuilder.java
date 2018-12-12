package com.syntaxterror.bestseller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Koulu;
import com.syntaxterror.bestseller.model.Valmentaja;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;

@Service
public class MailContentBuilder {

	private TemplateEngine templateEngine;
	@Autowired
	private KilpailuRepository kilpailuRepository;

	@Autowired
	private KilpailijaRepository kilpailijaRepository;


	@Autowired
	private KouluRepository kouluRepository;


	@Autowired
	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String build(String message) {
		Context context = new Context();
		context.setVariable("message", message);
		context.setVariable("kilpailu", kilpailuRepository.findByKilpailuId(new Long(121)));
		Koulu koulu = kouluRepository.findByKouluId(new Long(350));
		context.setVariable("kilpailijat", kilpailijaRepository.findByKoulu(koulu));
		return templateEngine.process("sahkoposti", context);
	}

	public String kilpailijanSuoritukset(String message, Kilpailija kil, String osoite) {
		Context context = new Context();
		context.setVariable("message", message);
		context.setVariable("kilpailu", kilpailuRepository.findByKilpailuId(kil.getKilpailuId()));
		context.setVariable("kilpailija", kil);
		context.setVariable("linkki", osoite);
		return templateEngine.process("sahkoposti_kilpailija", context);
	}

	public String koulunSuoritukset(String message, Valmentaja val, List<String> links, String lb) {
		Context context = new Context();
		context.setVariable("valmentaja", val);
		context.setVariable("lb", lb);
		context.setVariable("links", links);
		List<Kilpailija> kilpailijat = kilpailijaRepository.findByKoulu(val.getKoulu());
		context.setVariable("kilpailijat", kilpailijat);
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(val.getKilpailuId());
		context.setVariable("kilpailu", kilpailu);

		return templateEngine.process("sahkoposti_valmentaja", context);
	}

	public String kaikkiSuoritukset(String message, Kilpailu kil, List<String> links, String lb) {
		Context context = new Context();
		context.setVariable("lb", lb);
		context.setVariable("links", links);
		List<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuId(kil.getkilpailuId());
		context.setVariable("kilpailijat", kilpailijat);
		context.setVariable("kilpailu", kil);

		return templateEngine.process("sahkoposti_jarjestaja", context);
	}

}
