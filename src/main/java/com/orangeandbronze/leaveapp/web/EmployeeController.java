package com.orangeandbronze.leaveapp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.service.EmployeeService;


@Controller
@Component
public class EmployeeController{
	/*EmployeeRepository employeeRepository;
	LeaveApplicationRepository leaveApplicationRepository;

	@Autowired
	public EmployeeController(
			EmployeeRepository employeeRepository, 
			LeaveApplicationRepository leaveApplicationRepository) {
		this.employeeRepository = employeeRepository;
		this.leaveApplicationRepository = leaveApplicationRepository;
	}	*/

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public EmployeeController() { }
	
	@RequestMapping("/add_employee")
	public String addEmployee(Model model) {
		
		//model.addAttribute(arg0, arg1);
		
		return "add_employee";
	}
	
	@RequestMapping(value = "/submit_add_employee", method = RequestMethod.POST)
	public String submitAddEmployee(@RequestParam Map<String, String> reqParam, Model model) {
		//Employee employee = new Employee(reqParam.get("firstName"), reqParam.get("lastName"));
		
		//int ret = employeeService.addEmployee(employee);
		//Collection<Employee> employees = employeeService.viewAllEmployee();
		
		//model.addAttribute("ret", ret);
		return "employee_list";
	}
	
	
	@RequestMapping("/view_all_employees")
	public String viewAllEmployees(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("employees", employees);
		
		return "employee_list";
	}
}