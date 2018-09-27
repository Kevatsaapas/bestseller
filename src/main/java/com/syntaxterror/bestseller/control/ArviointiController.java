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

    @RequestMapping(value = "/uusi", method = RequestMethod.GET)
    public String palautaArviointiLuontiSivu(Model model, Long kilpailuId){
        model.addAttribute("arviointi", new Arviointi());
        model.addAttribute("kilpailijat", kilpailijaRepository.findByKilpailuId(new Long(22)));
        model.addAttribute("tuomarit", tuomariRepository.findByKilpailuId(new Long(22)));
        return "tuomarointi";
    }

    @RequestMapping(value = "/tallenna", method = RequestMethod.POST)
    public String tallennaArviointi(Arviointi arviointi){
        Date arviointipvm = new Date();
        arviointi.setArviointiPvm(arviointipvm);
        arviointiRepository.save(arviointi);

        return "redirect:/";
    }

    public String poistaArviointi(@PathVariable Long arviointiId){
        arviointiRepository.deleteById(arviointiId);

        return "";
    }

}
