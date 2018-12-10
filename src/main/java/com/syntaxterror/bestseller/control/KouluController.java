package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Koulu;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.KouluRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
public class KouluController {

    @Autowired
    private KouluRepository kouluRepository;

    @Autowired
    private KilpailuRepository kilpailuRepository;

    @Secured("ADMIN")
    @RequestMapping("/luokoulu/{kilpailuId}")
    public String luoKoulu(Model model, Koulu koulu) {
        model.addAttribute(koulu);
        return "luokoulu";
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/tallennakoulu", method = RequestMethod.POST)
    public String tallennaKoulu(Koulu koulu) {
        kouluRepository.save(koulu);
        String redirect = "redirect:/datat/" + Long.toString(koulu.getKilpailuId());
        return redirect;
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/poistakoulu/{kouluId}", method = RequestMethod.GET)
    public String poistaKoulu(@PathVariable Long kouluId) {
        Koulu koulu = kouluRepository.findByKouluId(kouluId);
        kouluRepository.deleteById(kouluId);
        String redirect = "redirect:/datat/" + Long.toString(koulu.getKilpailuId());
        return redirect;
    }

    @Secured("ADMIN")
    @RequestMapping("/editkoulu/{kouluId}")
    public String luoKoulu(Model model, @PathVariable Long kouluId) {
        Koulu koulu = kouluRepository.findByKouluId(kouluId);
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(koulu.getKilpailuId());
        model.addAttribute("kilpailu", kilpailu);
        model.addAttribute("koulu", koulu);
        return "editkoulu";
    }
}