package com.syntaxterror.bestseller.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Valmentaja;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.ValmentajaRepository;
import com.syntaxterror.bestseller.service.MailClient;

@Controller
@Transactional
public class EmailController {

	@Autowired
	private KilpailuRepository kilpailuRepository;

	@Autowired
	private KilpailijaRepository kilpailijaRepository;

	@Autowired
	private ValmentajaRepository valmentajaRepository;

	@Autowired
	private MailClient mailClient;

	@RequestMapping("/sendMail/{kilpailijaId}")
	public String sendMail(@PathVariable Long kilpailijaId, Model model, HttpServletRequest request) {
		Kilpailija kilpailija = kilpailijaRepository.findByKilpailijaId(kilpailijaId);
		String recipient = kilpailija.getSposti();
		String message = "Best Seller - Tulokset";
		String osoite = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())
				+ "/tulokset/" + kilpailija.getKilHash();
		mailClient.prepareAndSend(recipient, message, kilpailija, osoite);

		String kilid = kilpailija.getKilpailuId().toString();
		return "redirect:/datat/" + kilid;
	}

	@RequestMapping("/sendMailKilpailijat/{kilpailuId}")
	public String sendMailKilpailijat(@PathVariable Long kilpailuId, Model model, HttpServletRequest request) {
		List<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuId(kilpailuId);
		for (Kilpailija kilpailija : kilpailijat) {
			String recipient = kilpailija.getSposti();
			String message = "Best Seller - Tulokset";
			String osoite = request.getRequestURL().toString().replace(request.getRequestURI(),
					request.getContextPath()) + "/tulokset/" + kilpailija.getKilHash();
			mailClient.prepareAndSend(recipient, message, kilpailija, osoite);
		}

		String kilid = kilpailuId.toString();
		return "redirect:/datat/" + kilid;
	}

	@RequestMapping("/sendMailValmentajat/{kilpailuId}")
	public String sendValmentajatMail(@PathVariable Long kilpailuId, Model model, HttpServletRequest request) {
		List<Valmentaja> valmentajat = valmentajaRepository.findByKilpailuId(kilpailuId);
		for (Valmentaja valmentaja : valmentajat) {
			List<Kilpailija> kilpailijat = kilpailijaRepository.findByKoulu(valmentaja.getKoulu());
			List<String> links = new ArrayList<String>();
			for (Kilpailija kilpailija : kilpailijat) {
				String osoite = request.getRequestURL().toString().replace(request.getRequestURI(),
						request.getContextPath()) + "/tulokset/" + kilpailija.getKilHash();
				links.add(osoite);
			}
			String osoite2 = request.getRequestURL().toString().replace(request.getRequestURI(),
					request.getContextPath()) + "/pisteytys/" + valmentaja.getKilpailuId();
			String recipient = valmentaja.getSposti();
			String message = "Best Seller - Tulokset";
			mailClient.prepareAndSendVal(recipient, message, valmentaja, links, osoite2);
		}
		String kilid = kilpailuId.toString();
		return "redirect:/datat/" + kilid;
	}

	@RequestMapping("/sendMailVal/{valmentajaId}")
	public String sendValmentajaMail(@PathVariable Long valmentajaId, Model model, HttpServletRequest request) {
		Valmentaja valmentaja = valmentajaRepository.findByValmentajaId(valmentajaId);
		List<Kilpailija> kilpailijat = kilpailijaRepository.findByKoulu(valmentaja.getKoulu());
		List<String> links = new ArrayList<String>();
		for (Kilpailija kilpailija : kilpailijat) {
			String osoite = request.getRequestURL().toString().replace(request.getRequestURI(),
					request.getContextPath()) + "/tulokset/" + kilpailija.getKilHash();
			links.add(osoite);
		}
		String osoite2 = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())
				+ "/pisteytys/" + valmentaja.getKilpailuId();
		String recipient = valmentaja.getSposti();
		String message = "Best Seller - Tulokset";
		mailClient.prepareAndSendVal(recipient, message, valmentaja, links, osoite2);
		String kilid = valmentaja.getKilpailuId().toString();
		return "redirect:/datat/" + kilid;
	}

	@RequestMapping("/sendMailAll")
	public String sendAll(@RequestParam("kilpailuId") Long kilpailuId, Model model, @RequestParam("recip") String recip,
			HttpServletRequest request) {
		List<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuId(kilpailuId);
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
		List<String> links = new ArrayList<String>();
		for (Kilpailija kilpailija : kilpailijat) {
			String osoite = request.getRequestURL().toString().replace(request.getRequestURI(),
					request.getContextPath()) + "/tulokset/" + kilpailija.getKilHash();
			links.add(osoite);
		}
		String osoite2 = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())
				+ "/pisteytys/" + kilpailuId;
		String recipient = recip;
		String message = "Best Seller - Tulokset";
		mailClient.prepareAndSendAll(recipient, message, kilpailu, links, osoite2);
		String kilid = kilpailuId.toString();
		return "redirect:/datat/" + kilid;
	}

}
