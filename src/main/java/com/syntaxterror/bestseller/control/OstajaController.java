package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.*;
import com.syntaxterror.bestseller.repository.*;
import com.syntaxterror.bestseller.service.TuomariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@RequestMapping(value = "/ostaja")
@Controller
public class OstajaController {

    @Autowired
    public LohkoRepository lohkoRepository;

    @Autowired
    public KilpailuRepository kilpailuRepository;

    @Autowired
    public KilpailijaRepository kilpailijaRepository;

    @Autowired
    public OstajaRepository ostajaRepository;

    @Autowired
    public OstajaArviointiRepository ostajaArviointiRepository;

    @Autowired
    public TuomariService tuomariService;


    @RequestMapping(value = "/ostajaarviointi/", method = RequestMethod.POST)
    public String palautaOstajaArviointiLuontiSivu(Model model, @RequestParam("lohkoId") Long lohkoId, @RequestParam("kilpailuId") Long kilpailuId, @RequestParam("ostajaId") Long ostajaId){

        Lohko lohko = lohkoRepository.findByLohkoId(lohkoId);
        Ostaja ostaja = ostajaRepository.findByOstajaId(ostajaId);
        model.addAttribute("lohko", lohko);
        model.addAttribute("ostajaArviointi", new OstajaArviointi());
        model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
        model.addAttribute("userostaja", ostaja);

        model.addAttribute("kilpailijat", tuomariService.haeKilpailijatOstajalle(ostaja, lohko));
        return "ostaja";
    }
    
    @RequestMapping(value = "/finaaliarviointi/", method = RequestMethod.POST)
    public String palautaOstajaFinaaliArviointiLuontiSivu(Model model, @RequestParam("lohkoId") Long lohkoId, @RequestParam("kilpailuId") Long kilpailuId, @RequestParam("ostajaId") Long ostajaId){
    	Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        Lohko lohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, "finaali");
        Ostaja ostaja = ostajaRepository.findByOstajaId(ostajaId);
        model.addAttribute("lohko", lohko);
        model.addAttribute("ostajaArviointi", new OstajaArviointi());
        model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
        model.addAttribute("userostaja", ostaja);

        model.addAttribute("kilpailijat", tuomariService.haeFinalistitOstajalle(ostaja, lohko));
        return "ostaja";
    }
    

    @RequestMapping(value = "/tallenna/{kilpailuId}/{lohkoId}/{ostajaId}", method = RequestMethod.POST)
    public String tallennaOstajaArviointi(OstajaArviointi ostajaArviointi, @PathVariable Long kilpailuId, @PathVariable Long lohkoId, @PathVariable Long ostajaId){
        Date ostarviointipvm = new Date();
        ostajaArviointi.setArviointiPvm(ostarviointipvm);
        ostajaArviointi.setKilpailuId(kilpailuId);
        Ostaja ostaja = ostajaRepository.findByOstajaId(ostajaId);
        ostajaArviointi.setOstaja(ostaja);
        Lohko lohko = lohkoRepository.findByLohkoId(lohkoId);
        ostajaArviointi.setLohko(lohko);

        ostajaArviointiRepository.save(ostajaArviointi);
        System.out.println(ostajaArviointi);
        return "redirect:/";
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/poistaostaja/{ostajaId}", method = RequestMethod.GET)
    public String poistaOstaja (@PathVariable Long ostajaId) {
        Ostaja ostaja = ostajaRepository.findByOstajaId(ostajaId);
        ostajaRepository.delete(ostaja);

        String redirect = "redirect:/datat/" + Long.toString(ostaja.getKilpailuId());
        return redirect;
    }

    @Secured("ADMIN")
    @RequestMapping("/editostaja/{ostajaId}")
    public String editOstaja(Model model, @PathVariable Long ostajaId) {
        Ostaja ostaja = ostajaRepository.findByOstajaId(ostajaId);
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(ostaja.getKilpailuId());
        model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
        model.addAttribute("kilpailu", kilpailu);
        model.addAttribute("ostaja", ostaja);
        return "editostaja";
    }

    @Secured("ADMIN")
    @RequestMapping("/luoostaja/{kilpailuId}")
    public String luoOstaja(Model model, @PathVariable Long kilpailuId, Ostaja ostaja) {
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
        model.addAttribute("kilpailu", kilpailu);
        return "luoostaja";

    }

    @Secured("ADMIN")
    @RequestMapping(value = "/tallennaostaja", method = RequestMethod.POST)
    public String tallennaOstaja(Model model, Ostaja ostaja) {
        ostajaRepository.save(ostaja);
        model.addAttribute("ostajaId", ostaja.getOstajaId());
        String redirect = "redirect:/ostaja/luoostuser/"+ Long.toString(ostaja.getOstajaId());
        return redirect;
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/luoostuser/{ostajaId}")
    public String luoOstajalleKayttaja(Model model, @PathVariable Long ostajaId){
        SignupForm siform = new SignupForm();
        String rooli = "ostaja";
        siform.setRooli(rooli);
        siform.setRooliId(ostajaId);
        System.out.println(siform.getRooli());
        System.out.println(siform.getRooliId());
        model.addAttribute("ostajasignupform", siform);
        return "ostajasignup";
    }

}
