package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

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

    @RequestMapping(value = "/tallenna/{kilpailuId}", method = RequestMethod.POST)
    public String tallennaArviointi(Arviointi arviointi, @PathVariable Long kilpailuId){
        Date arviointipvm = new Date();
        arviointi.setArviointiPvm(arviointipvm);
        arviointi.setKilpailuId(kilpailuId);
        
        double painotettuAloitus = arviointi.getAloitus().getKokonaistulos() * 0.1;
        double painotettuKasittely = arviointi.getKysymystenKasittely().getKokonaistulos() * 0.3;
        double painotettuPaattaminen = arviointi.getPaattaminen().getKokonaistulos() * 0.25;
        double painotettuRatkaisu = arviointi.getRatkaisu().getKokonaistulos() * 0.1;
        double painotettuTarvekartoitus = arviointi.getTarvekartoitus().getKokonaistulos() * 0.1;
        double painotettuYleisvaikutelma = arviointi.getYleisvaikutelma().getKokonaistulos() * 0.1;
        
        double kokonaistulos = painotettuAloitus + painotettuKasittely + painotettuPaattaminen + painotettuRatkaisu + painotettuTarvekartoitus + painotettuYleisvaikutelma;
        arviointi.setKokonaistulos(kokonaistulos);
        System.out.println(arviointi.getKokonaistulos());
        arviointiRepository.save(arviointi);
        return "redirect:/";
    }
    
   
    

}
