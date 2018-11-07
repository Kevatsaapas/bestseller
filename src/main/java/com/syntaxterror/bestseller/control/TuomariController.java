package com.syntaxterror.bestseller.control;

import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tuomarivalikko")
public class TuomariController {

    @Autowired
    public TuomariRepository tuomariRepository;

    @Autowired
    public LohkoRepository lohkoRepository;


}
