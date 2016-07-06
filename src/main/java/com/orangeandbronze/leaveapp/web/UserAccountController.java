package com.orangeandbronze.leaveapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAccountController{
	
	@RequestMapping("/account_info")
	public String displayAccountInfo() {
		
		ModelAndView model = new ModelAndView("account_info");
		
		return "account_info";
	}
	
	@RequestMapping("/edit_profile")
	public ModelAndView editProfile() {
		
		ModelAndView model = new ModelAndView("edit_profile");
		
		return model;
	}
}
