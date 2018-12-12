package com.syntaxterror.bestseller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Valmentaja;

@Service
public class MailClient {

	private JavaMailSender mailSender;
	private MailContentBuilder mailContentBuilder;


	@Autowired
	public MailClient(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
		this.mailSender = mailSender;
		this.mailContentBuilder = mailContentBuilder;
	}

	public void prepareAndSend(String recipient, String message, Kilpailija kil, String osoite) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("bestsellertesteri@gmail.com");
			messageHelper.setTo(recipient);
			messageHelper.setSubject("Best Seller competition - Arvioinnit");
			String content = mailContentBuilder.kilpailijanSuoritukset(message, kil, osoite);
			messageHelper.setText(content, true);
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			// runtime exception; compiler will not force you to handle it
		}
	}

	public void prepareAndSendVal(String recipient, String message, Valmentaja val, List<String> links, String lb) {

		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("bestsellertesteri@gmail.com");
			messageHelper.setTo(recipient);
			String sub = "Best Seller Competition - Koulun " + val.getKoulu().getKouluNimi() + " suoritukset.";
			messageHelper.setSubject(sub);
			String content = mailContentBuilder.koulunSuoritukset(message, val, links, lb);
			messageHelper.setText(content, true);
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			// runtime exception; compiler will not force you to handle it
		}
	}

	public void prepareAndSendAll(String recipient, String message, Kilpailu kil, List<String> links, String lb) {

		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("bestsellertesteri@gmail.com");
			messageHelper.setTo(recipient);
			String sub = "Best Seller Competition - Suoritukset";
			messageHelper.setSubject(sub);
			String content = mailContentBuilder.kaikkiSuoritukset(message, kil, links, lb);
			messageHelper.setText(content, true);
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			// runtime exception; compiler will not force you to handle it
		}
	}

}
