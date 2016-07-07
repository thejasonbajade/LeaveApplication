package com.orangeandbronze.leaveapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAccountController{
	
	@RequestMapping("/account_info")
	public String displayAccountInfo() {
		return "account_info";
	}
}
