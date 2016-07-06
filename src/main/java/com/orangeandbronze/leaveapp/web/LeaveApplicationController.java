package com.orangeandbronze.leaveapp.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveType;
import com.orangeandbronze.leaveapp.domain.Supervisor;
import com.orangeandbronze.leaveapp.service.EmployeeService;
import com.orangeandbronze.leaveapp.service.LeaveApplicationService;

@Controller
public class LeaveApplicationController{

	EmployeeService employeeService;
	LeaveApplicationService leaveApplicationService;
	Employee user;

	@Autowired
	public LeaveApplicationController(EmployeeService employeeService, LeaveApplicationService leaveApplicationService) {
		this.employeeService = employeeService;
		this.leaveApplicationService = leaveApplicationService;
	}
	
	@ModelAttribute
	public void addingCommonObjects(Model model, HttpSession session) {
		user = (Employee) session.getAttribute("user");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
		model.addAttribute("user", user);
		model.addAttribute("formatter", formatter);
	}
	
	@RequestMapping(value="/loginEmployee", method = RequestMethod.GET)
	public String loginEmployee(HttpServletRequest request, Model model){
		user = employeeService.viewEmployee(4);
		request.getSession().setAttribute("user", user);
		
		model.addAttribute("user", user);
		return "account_info";
	}
	
	@RequestMapping(value="/loginSupervisor", method = RequestMethod.GET)
	public String loginSupervisor(HttpServletRequest request, Model model){
		user = employeeService.viewEmployee(6);
		request.getSession().setAttribute("user", user);
		
		model.addAttribute("user", user);
		return "account_info";
	}

	@RequestMapping(value="/loginAdmin", method = RequestMethod.GET)
	public String loginAdmin(HttpServletRequest request){
		request.getSession().setAttribute("user", user);
		return "account_info";
	}

	@RequestMapping("/apply_leave")
	public String applyLeave(HttpSession session, Model model) {		
		List<Employee> supervisors = employeeService.findAllSupervisor();
		model.addAttribute("supervisors", supervisors);
		return "apply_leave";
	}
	
	@RequestMapping("/process_leave_application")
	public String processLeaveApplication(@RequestParam Map<String, String> reqParam, Model model) {
		boolean startHalfDay = false;
		boolean endHalfDay = false;
		if(reqParam.get("startHalfDay") != null)
			startHalfDay = true;
		if(reqParam.get("endHalfDay") != null)
			endHalfDay = true;
		int ret = employeeService.fileLeave(user.getEmployeeId(), 
				LocalDate.parse(reqParam.get("startDate")), 
				startHalfDay,
				LocalDate.parse(reqParam.get("endDate")), 
				endHalfDay,
				LeaveType.valueOf(reqParam.get("leaveType")),
				reqParam.get("reason"), 
				Long.parseLong(reqParam.get("supervisorId")));

		return "redirect:/view_leave_history";
	}

	@RequestMapping("/view_all_leave_histories")
	public String showAllLeaveHistories(Model model) {	
		List<LeaveApplication> leaveApplications = leaveApplicationService.findAllLeaveApplications();
		model.addAttribute("leaveApplications", leaveApplications);
		return "leave_list";
	}

	@RequestMapping("/view_leave_history")
	public String showOwnLeaveHistory(Model model) {
		List<LeaveApplication> leaveApplications = 
				leaveApplicationService.findLeaveApplicationsByEmployee(user.getEmployeeId());
		model.addAttribute("leaveApplications", leaveApplications);
		return "leave_history";
	}
	
	@RequestMapping("/view_leave_histories_supervisor")
	public String showAllLeaveHistoriesForSupervisor(Model model) {	
		List<LeaveApplication> leaveApplications = 
				leaveApplicationService.findLeaveApplicationsForSupervisor(user.getEmployeeId());
		model.addAttribute("leaveApplications", leaveApplications);
		return "leave_list";
	}

	@RequestMapping("/approve_leave/{leaveId}")
	public String approveLeave(@PathVariable("leaveId") int leaveId, Model model) {
		employeeService.approveLeaveApplication(user.getEmployeeId(), leaveId);
		return "redirect:/view_all_leave_histories";
	}

	@RequestMapping("/disapprove_leave/{leaveId}")
	public String disapproveLeave(@PathVariable("leaveId") int leaveId, Model model) {
		employeeService.disapproveLeaveApplication(user.getEmployeeId(), leaveId);
		return "redirect:/view_all_leave_histories";
	}

	@RequestMapping("/cancel_leave/{leaveId}")
	public String cancelLeave(@PathVariable("leaveId") int leaveId, Model model) {
		employeeService.cancelLeaveApplication(user.getEmployeeId(), leaveId);
		return "redirect:/view_leave_history";
	}

	@RequestMapping("/not_taken_leave/{leaveId}")
	public String changeToNotTakenLeave(@PathVariable("leaveId") int leaveId, Model model) {
		employeeService.changeLeaveApplicationToNotTaken(user.getEmployeeId(), leaveId);
		return "redirect:/view_all_leave_histories";
	}

	/*@ExceptionHandler(value= {.class})
	public String () { }*/
}	