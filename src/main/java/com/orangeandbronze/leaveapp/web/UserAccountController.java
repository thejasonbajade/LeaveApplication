package com.orangeandbronze.leaveapp.web;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.EmployeeRecord;
import com.orangeandbronze.leaveapp.domain.EmploymentStatus;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserAccountController{
	
	@RequestMapping("/account_info")
	public String displayAccountInfo() {
		
		ModelAndView model = new ModelAndView("account_info");
		
		return "account_info";
	}
}
