package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

@Controller
@RequestMapping(value = "/arviointi")
public class ArviointiController {

    @Autowired
    public ArviointiRepository arviointiRepository;

    @Autowired
    public KilpailijaRepository kilpailijaRepository;

    @Autowired
    public TuomariRepository tuomariRepository;

    @Autowired
    public KilpailuRepository kilpailuRepository;
    @Autowired
    public LohkoRepository lohkoRepository;

    @RequestMapping(value = "/uusi/{kilpailuId}/{lohkoId}", method = RequestMethod.GET)
    public String palautaArviointiLuontiSivu(Model model,@PathVariable Long kilpailuId, @PathVariable Long lohkoId){
        model.addAttribute("arviointi", new Arviointi());
       model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
        model.addAttribute("kilpailijat", kilpailijaRepository.findByKilpailuIdAndLohko(kilpailuId,lohkoRepository.findByLohkoId(lohkoId)));
        model.addAttribute("tuomarit", tuomariRepository.findByKilpailuIdAndLohkoNro(kilpailuId,lohkoRepository.findByLohkoId(lohkoId).getLohkoNro()));
        model.addAttribute("lohko", lohkoRepository.findByLohkoId(lohkoId));

        return "tuomarointi";
    }
    
    @RequestMapping(value = "/uusitest/{kilpailuId}/{lohkoId}", method = RequestMethod.GET)
    public String palautaArviointiLuontiSivuDebug(Model model,@PathVariable Long kilpailuId, @PathVariable Long lohkoId){
        model.addAttribute("arviointi", new Arviointi());
       model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
        model.addAttribute("kilpailijat", kilpailijaRepository.findByKilpailuIdAndLohko(kilpailuId,lohkoRepository.findByLohkoId(lohkoId)));
        model.addAttribute("tuomarit", tuomariRepository.findByKilpailuIdAndLohkoNro(kilpailuId,lohkoRepository.findByLohkoId(lohkoId).getLohkoNro()));
        model.addAttribute("lohko", lohkoRepository.findByLohkoId(lohkoId));
        List<Long> arvot = new ArrayList<Long>();
        for(int i=0; i<16; i++) {
        	int randomNum = ThreadLocalRandom.current().nextInt(0, 6 + 1);
        	Long arvo = new Long(randomNum+1);
        	arvot.add(arvo);
        }
        model.addAttribute("arvot", arvot);
        return "uusitest";
    }

    @RequestMapping(value = "/tallenna/{kilpailuId}/{lohkoId}", method = RequestMethod.POST)
    public String tallennaArviointi(Arviointi arviointi, @PathVariable Long kilpailuId, @PathVariable Long lohkoId){
        Date arviointipvm = new Date();
        arviointi.setArviointiPvm(arviointipvm);
        arviointi.setKilpailuId(kilpailuId);
        Lohko loh = lohkoRepository.findByLohkoId(lohkoId);
        arviointi.setLohko(loh);
        
        double painotettuAloitus = arviointi.getAloitus().getKokonaistulos() * 0.1;
        double painotettuKasittely = arviointi.getKysymystenKasittely().getKokonaistulos() * 0.3;
        double painotettuPaattaminen = arviointi.getPaattaminen().getKokonaistulos() * 0.25;
        double painotettuRatkaisu = arviointi.getRatkaisu().getKokonaistulos() * 0.1;
        double painotettuTarvekartoitus = arviointi.getTarvekartoitus().getKokonaistulos() * 0.1;
        double painotettuYleisvaikutelma = arviointi.getYleisvaikutelma().getKokonaistulos() * 0.1;
        
        double kokonaistulos = painotettuAloitus + painotettuKasittely + painotettuPaattaminen + painotettuRatkaisu + painotettuTarvekartoitus + painotettuYleisvaikutelma;
        arviointi.setKokonaistulos(kokonaistulos);
        System.out.println(arviointi.getLohko());
        System.out.println(arviointi.getKokonaistulos());
        arviointiRepository.save(arviointi);
        return "redirect:/";
    }
    
   
    

}
