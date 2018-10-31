package com.syntaxterror.bestseller.control;


import com.syntaxterror.bestseller.model.SignupForm;
import com.syntaxterror.bestseller.model.Account;
import com.syntaxterror.bestseller.repository.AccountRepository;
import com.syntaxterror.bestseller.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountService accountService;
    

    @RequestMapping(value = "signup")
    public String addStudent(Model model){
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }
    

    /**
     * Create new user
     * Check if user already exists & form validation
     *
     * @param signupForm
     * @param bindingResult
     * @return
*/
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        return "";
    }

}
