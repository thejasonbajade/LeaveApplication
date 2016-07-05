package com.orangeandbronze.leaveapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotificationController{
	
	@RequestMapping("/notify_lwop")
	public ModelAndView notifyLWOP() {
		
		ModelAndView model = new ModelAndView("notify_lwop");
		
		return model;
	}
	
	@RequestMapping("/notify_awol")
	public ModelAndView notifyAWOL() {
		
		ModelAndView model = new ModelAndView("notify_awol");
		
		return model;
	}
}