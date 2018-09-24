package com.syntaxterror.bestseller.control;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;

@Controller
public class DefaultController {
    @Autowired
    public KilpailuRepository kilpailuRepository;
    @Autowired
    public KilpailijaRepository kilpailijarepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("login")
    public String login() {
        return "login";
    }
    
    
    @RequestMapping("/testaus")
    public String testaus(Model model) {
        model.addAttribute("kilpailut", kilpailuRepository.findAll());
        return "testaus";
    }

    @RequestMapping("/tuomarointi")
    public String tuomarointi(Model model){
    	model.addAttribute("kilpailijat", kilpailijarepository.findByKilpailuId(new Long(1)));
    	System.out.println(kilpailijarepository.findByKilpailuId(new Long(1)));
    	System.out.println("joo");
        return "tuomarointi";
    }

    @RequestMapping("/ostaja")
    public String ostaja(){
        return "ostaja";
    }

}
