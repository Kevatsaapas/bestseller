package com.syntaxterror.bestseller.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

@Controller
@RequestMapping("/tuomarivalikko")
public class TuomariController {

    @Autowired
    public TuomariRepository tuomariRepository;

    @Autowired
    public LohkoRepository lohkoRepository;


}
