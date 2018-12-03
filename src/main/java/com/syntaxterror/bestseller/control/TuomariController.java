package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.SignupForm;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.model.User;
import com.syntaxterror.bestseller.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@org.springframework.transaction.annotation.Transactional
public class TuomariController {

    @Autowired
    private TuomariRepository tuomariRepository;

    @Autowired
    private LohkoRepository lohkoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArviointiRepository arviointiRepository;

    @Autowired
    private KilpailuRepository kilpailuRepository;


    @Secured("ADMIN")
    @RequestMapping(value = "/poistatuomari/{tuomariId}", method = RequestMethod.GET)
    public String poistaTuomari(@PathVariable Long tuomariId) {
        Tuomari tuomari = tuomariRepository.findByTuomariId(tuomariId);
        arviointiRepository.deleteByTuomari(tuomari);
        String redirect = "redirect:/datat/" + Long.toString(tuomari.getKilpailuId());
        List<User> userit = userRepository.findByRooli("tuomari");

        for (User user : userit) {
            if (user.getrooliId().equals(tuomari.getTuomariId())) {
                userRepository.delete(user);
            }
        }
        tuomariRepository.delete(tuomari);

        return redirect;
    }

    @Secured("ADMIN")
    @RequestMapping("/luotuomari/{kilpailuId}")
    public String luoTuomari(Model model, @PathVariable Long kilpailuId, Tuomari tuomari) {
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
        model.addAttribute("kilpailu", kilpailu);

        return "luotuomari";
    }

    @Secured("ADMIN")
    @RequestMapping("/edittuomari/{tuomariId}")
    public String luoTuomari(Model model, @PathVariable Long tuomariId) {
        Tuomari tuomari = tuomariRepository.findByTuomariId(tuomariId);
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(tuomari.getKilpailuId());
        model.addAttribute("lohkot", lohkoRepository.findByKilpailu(kilpailu));
        model.addAttribute("kilpailu", kilpailu);
        model.addAttribute("tuomari", tuomari);

        return "edittuomari";
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/tallennatuomari", method = RequestMethod.POST)
    public String tallennaTuomari(Model model, Tuomari tuomari) {
    	if(tuomari.getfinaaliin()==0 && tuomari.getLohkoNro().equals("finaali")) {
			tuomari.setfinaaliin(new Long(1));
		}
        tuomariRepository.save(tuomari);
        model.addAttribute("tuomariId", tuomari.getTuomariId());
        String redirect = "redirect:/luotuouser/" + Long.toString(tuomari.getTuomariId());

        return redirect;
    }

    @RequestMapping(value = "/tallennatuomariedit", method = RequestMethod.POST)
    public String tallennaTuomariEdit(Tuomari tuomari) {
    	if(tuomari.getfinaaliin()==0 && tuomari.getLohkoNro().equals("finaali")) {
			tuomari.setfinaaliin(new Long(1));
		}
        tuomariRepository.save(tuomari);
        String redirect = "redirect:/datat/" + Long.toString(tuomari.getKilpailuId());

        return redirect;
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/luotuouser/{tuomariId}")
    public String luoTuomarilleKayttaja(Model model, @PathVariable Long tuomariId) {
        SignupForm siform = new SignupForm();
        String rooli = "tuomari";
        siform.setRooli(rooli);
        siform.setRooliId(tuomariId);
        model.addAttribute("signupform", siform);

        return "signup";
    }
}
