package com.syntaxterror.bestseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.syntaxterror.bestseller.model.Koulu;
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
        context.setVariable("kilpailu", kilpailuRepository.findByKilpailuId(new Long(104)));
        Koulu koulu = kouluRepository.findByKouluId(new Long(297));
        context.setVariable("kilpailijat", kilpailijaRepository.findByKoulu(koulu));
        return templateEngine.process("sahkoposti", context);
    }

}
