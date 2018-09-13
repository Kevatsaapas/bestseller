package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class KilpailuController {

    @Autowired
    public KilpailuRepository kilpailuRepository;

    @Autowired
    public LohkoRepository lohkoRepository;

    @Autowired
    public KilpailijaRepository kilpailijaRepository;

    @RequestMapping("/luokilpailu")
    public String luoKilpailu(Model model, Kilpailu kilpailu) {
        model.addAttribute(kilpailu);
        return "luonti";
    }

    private void luoLohkot(Long kilpailuId) {
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        for (int i = 1; i < 5; i++) {
            Lohko loh = new Lohko((Integer.toString(i)), kilpailu, null);
            lohkoRepository.save(loh);
            System.out.println(loh);
        }

        Lohko loh = new Lohko("finaali", kilpailu, null);
        lohkoRepository.save(loh);
        System.out.println(loh);
        kilpailuRepository.save(kilpailu);
    }

    @RequestMapping(value = "/tallennakilpailu", method = RequestMethod.POST)
    public String tallennaKilpailu(Kilpailu kilpailu) {
        Date pvm = new Date();
        kilpailu.setPvm(pvm);
        kilpailuRepository.save(kilpailu);
        luoLohkot(kilpailu.getKilpailuId());
        System.out.println(kilpailu);
        return "redirect:/testaus";
    }

    @RequestMapping(value = "/poistakilpailu/{kilpailuId}", method = RequestMethod.GET)
    public String poistaKilpailu(@PathVariable Long kilpailuId) {
        kilpailijaRepository.deleteByKilpailuId(kilpailuId);
        Kilpailu kilpailu= kilpailuRepository.findByKilpailuId(kilpailuId);
        lohkoRepository.deleteByKilpailu(kilpailu);
        kilpailuRepository.deleteById(kilpailuId);
        return "redirect:/testaus";
    }

}
