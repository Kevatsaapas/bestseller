package com.syntaxterror.bestseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Valmentaja;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;

@Service
public class MailClient {

    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;
    
    @Autowired
    private KilpailijaRepository kilpailijaRepository;
    
    @Autowired
    private KilpailijaRepository kilpailuRepository;

    @Autowired
    public MailClient(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

   /* public void prepareAndSend(String recipient, String message, Valmentaja val) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("bestsellertesteri@gmail.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Koulu");
            String content = mailContentBuilder.kilpailijanSuoritukset(message, kilpailuId, kilpailijaId);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }*/
    
    public void prepareAndSendVal(String recipient, String message, Long kilpailuId, Long kilpailijaId) {
    	Kilpailija kilpailija = kilpailijaRepository.findByKilpailijaId(kilpailijaId);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("bestsellertesteri@gmail.com");
            messageHelper.setTo(recipient);
            String sub = "Best Seller Competition - Suoritukset Henkilölle "+kilpailija.getEtunimi()+" "+kilpailija.getSukunimi();
            messageHelper.setSubject(sub);
            String content = mailContentBuilder.kilpailijanSuoritukset(message, kilpailuId, kilpailijaId);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }

}
