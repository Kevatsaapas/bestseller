package com.syntaxterror.bestseller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Koulu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.OstajaArviointi;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.OstajaArviointiRepository;

@Service
public class MailContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private KilpailuRepository kilpailuRepository;
    
    @Autowired
    private KilpailijaRepository kilpailijaRepository;
    
    @Autowired
    private ArviointiRepository arviointiRepository;
    
    @Autowired
    private OstajaArviointiRepository ostajaArviointiRepository;
    
    @Autowired
    private KouluRepository kouluRepository;
    
    @Autowired
    private LohkoRepository lohkoRepository;
    
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
    
    public String kilpailijanSuoritukset(String message, Long kilpailuId, Long kilpailijaId) {
    	Context context = new Context();

    	Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
    	context.setVariable("kilpailu", kilpailu);

    	Kilpailija kilpailija = kilpailijaRepository.findByKilpailijaId(kilpailijaId);
    	context.setVariable("kilpailija", kilpailija);

    	Lohko lohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, kilpailija.getLohko().getLohkoNro());

    	List<Arviointi> arvioinnit = arviointiRepository.findByKilpailijaAndLohko(kilpailija, lohko);
    	context.setVariable("arvioinnit", arvioinnit);

    	Lohko finaalilohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, "finaali");

    	List<Arviointi> finaaliArvioinnit = arviointiRepository.findByKilpailijaAndLohko(kilpailija, finaalilohko);
    	context.setVariable("finaaliArvioinnit", finaaliArvioinnit);

    	List<OstajaArviointi> ostajaArvioinnit = ostajaArviointiRepository.findByKilpailijaAndLohko(kilpailija, lohko);
    	context.setVariable("ostajaArvioinnit", ostajaArvioinnit);
    	
    	List<OstajaArviointi> ostajaFinaaliArvioinnit = ostajaArviointiRepository.findByKilpailijaAndLohko(kilpailija, finaalilohko);
    	context.setVariable("ostajaFinaaliArvioinnit", ostajaFinaaliArvioinnit);

    	return templateEngine.process("sahkoposti_kilpailija", context);
    }

}
