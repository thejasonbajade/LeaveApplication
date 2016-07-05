package com.orangeandbronze.leaveapp.web;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.LeaveApplication;
import com.orangeandbronze.leaveapp.domain.LeaveType;
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
	
	@RequestMapping(value="/loginEmployee", method = RequestMethod.GET)
	public String loginEmployee(HttpServletRequest request, Model model){
		user = employeeService.viewEmployee(4);
		request.getSession().setAttribute("user", user);
		
		model.addAttribute("user", user);
		return "account_info";
	}
	
	@RequestMapping(value="/loginSupervisor", method = RequestMethod.GET)
	public String loginSupervisor(HttpServletRequest request){
		//user = new Employee(2, "Rochelle", "Sisa");
		request.getSession().setAttribute("user", user);
		return "account_info";
	}

	@RequestMapping(value="/loginAdmin", method = RequestMethod.GET)
	public String loginAdmin(HttpServletRequest request){
		//user = new Employee(3, "Jerome", "Gonzalvo");
		request.getSession().setAttribute("user", user);
		return "account_info";
	}

	@RequestMapping("/apply_leave")
	public String applyLeave(HttpSession session, Model model) {
		Employee user = (Employee) session.getAttribute("user");
		
		//Employee s1 = new Employee(4, "Angelo", "Guiam");
		//Employee s2 = new Employee(5, "Joshua", "Pabilona");
		
		List<Employee> supervisors = employeeService.findAllSupervisor();
		
		model.addAttribute("user", user);
		model.addAttribute("supervisors", supervisors);
		return "apply_leave";
	}
	
	@RequestMapping("/process_leave_application")
	public String processLeaveApplication(@RequestParam Map<String, String> reqParam, Model model) throws ParseException {


		//Employee employee = employeeRepository.findBy(
		//		Integer.parseInt(reqParam.map("employeeId")));
		//Supervisor approver = (Supervisor) employeeRepository.findBy(
		//		Integer.parseInt(reqParam.map("approverId")));
		//reqParam.get();
		//Employee employee = new Employee();
		//Supervisor approver = new Supervisor();
		/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd");
		Date start = simpleDateFormat.parse(reqParam.get("startDate"));
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(start);
		Date end = simpleDateFormat.parse(reqParam.get("startDate"));
		Calendar endDate = Calendar.getInstance();*/
		
		LocalDate startDate = LocalDate.parse(reqParam.get("startDate"));
		LocalDate endDate = LocalDate.parse(reqParam.get("endDate"));
		LeaveType leaveType = LeaveType.valueOf(reqParam.get("leaveType"));

		employeeService.fileLeave(1, startDate, endDate, leaveType, reqParam.get("reason"), 2);
		//		model.addAttribute("leaveApplication", leaveApplication);
		//model.addAttribute("leaveApplication", leaveApplication);

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
		List<LeaveApplication> leaveApplications = leaveApplicationService.findLeaveApplicationsByEmployee(1);
		model.addAttribute("leaveApplications", leaveApplications);
		return "leave_history";
	}

	@RequestMapping("/approve_leave/{leaveId}")
	public String approveLeave(@PathVariable("leaveId") int leaveId, HttpSession session, Model model) {
		user = (Employee) session.getAttribute("user");
		System.out.println(user.getEmployeeId());
		employeeService.approveLeaveApplication(1, leaveId);
		return "redirect:/view_all_leave_histories";
	}

	@RequestMapping("/disapprove_leave/{leaveId}")
	public String disapproveLeave(@PathVariable("leaveId") int leaveId, HttpSession session, Model model) {
		//user = (Employee) session.getAttribute("user");
		//employeeService.disapproveLeaveApplication(user.getEmployeeId(), leaveId);
		return "redirect:/view_all_leave_histories";
	}

	@RequestMapping("/cancel_leave/{leaveId}")
	public String cancelLeave(@PathVariable("leaveId") int leaveId, HttpSession session, Model model) {
		//user = (Employee) session.getAttribute("user");
		//employeeService.cancelLeaveApplication(user.getEmployeeId(), leaveId);
		return "redirect:/view_all_leave_histories";
	}

	@RequestMapping("/not_taken_leave/{leaveId}")
	public String changeToNotTakenLeave(@PathVariable("leaveId") int leaveId, HttpSession session, Model model) {
		//user = (Employee) session.getAttribute("user");
		//employeeService.changeLeaveApplicationToNotTaken(user.getEmployeeId(), leaveId);
		return "redirect:/view_all_leave_histories";
	}

	/*@ExceptionHandler(value= {.class})
	public String () { }*/
}	