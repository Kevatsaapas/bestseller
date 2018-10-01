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
        return "tuomarointi";
    }

    @RequestMapping(value = "/tallenna/{kilpailuId}", method = RequestMethod.POST)
    public String tallennaArviointi(Arviointi arviointi, @PathVariable Long kilpailuId){
        Date arviointipvm = new Date();
        arviointi.setArviointiPvm(arviointipvm);
        arviointi.setKilpailuId(kilpailuId);
        System.out.println(arviointi);
        arviointiRepository.save(arviointi);
        return "redirect:/";
    }

    public String poistaArviointi(@PathVariable Long arviointiId){
        arviointiRepository.deleteById(arviointiId);
        return "";
    }

}
