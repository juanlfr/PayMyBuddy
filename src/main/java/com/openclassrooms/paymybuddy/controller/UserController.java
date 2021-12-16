package com.openclassrooms.paymybuddy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.security.IAuthenticationFacade;
import com.openclassrooms.paymybuddy.service.UserService;

@Controller
public class UserController {

	private static final Logger log = LogManager.getLogger(UserController.class);

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/signUpForm")
	public ModelAndView showSignUpForm() {

		ModelAndView signUpForm = new ModelAndView("signUpForm");

		signUpForm.addObject("user", new User());

		return signUpForm;

	}

	@PostMapping("/signUpForm")
	public ModelAndView submitUserForm(User user) {

		RedirectView redirectView = new RedirectView();
		try {

			userService.createUser(user);
			log.info("Creating user " + user);

			redirectView.setUrl("/");
		} catch (Exception e) {
			log.error("Error creating user: " + e);
			redirectView.setUrl("/error");
		}

		return new ModelAndView(redirectView);

	}

	@GetMapping("/login")
	public String getLoginView() {
		return "login";
	}

	@GetMapping("/welcome")
	public ModelAndView welcomeRegisteredUser() {

		String userEmail = currentUserNameSimple();
		User user = userService.getUserInfo(userEmail).get();
		ModelAndView welcomeMAV = new ModelAndView("welcome");
		welcomeMAV.addObject("user", user);

		return welcomeMAV;
	}

	private String currentUserNameSimple() {
		Authentication authentication = authenticationFacade.getAuthentication();
		UserDetails user = (UserDetails) authentication.getPrincipal();
		return user.getUsername();

	}

}
