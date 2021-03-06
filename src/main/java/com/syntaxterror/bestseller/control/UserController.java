package com.syntaxterror.bestseller.control;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.syntaxterror.bestseller.model.SignupForm;
import com.syntaxterror.bestseller.model.User;
import com.syntaxterror.bestseller.repository.UserRepository;

@Controller
@Transactional
public class UserController {

	@Autowired
	private UserRepository repository;


	@RequestMapping(value = "signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	@RequestMapping(value = "adminSignUp")
	public String addAdmin(Model model) {
		List<User> uu = repository.findByRole("ADMIN");
		if(uu.size()>0) {		
			return "redirect:/login";
		}else {
		
		model.addAttribute("signupform", new SignupForm());
		return "adminsignup";
		}
	}

	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) { // validation errors

            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRooli(signupForm.getRooli());
				newUser.setRole("USER");
				newUser.setrooliId(signupForm.getRooliId());

				if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
					repository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}

			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}

		return "redirect:/testaus";
	}
	
	@RequestMapping(value = "saveadmin", method = RequestMethod.POST)
	public String save2(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) { // validation errors

            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRooli("");
				newUser.setRole("ADMIN");
				newUser.setrooliId(null);

				if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
					repository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "adminsignup";
				}

			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "adminsignup";
			}
		} else {
			return "adminsignup";
		}
		return "redirect:/";
	}
	
}
