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

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("kilpailut", kilpailuRepository.findAll());
        return "index";
    }

    @RequestMapping("/tuomarointi")
    public String tuomarointi(){
        return "tuomarointi";
    }

    @RequestMapping("/ostaja")
    public String ostaja(){
        return "ostaja";
    }

}
