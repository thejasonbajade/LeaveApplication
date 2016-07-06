package com.orangeandbronze.leaveapp.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.orangeandbronze.leaveapp.domain.Department;
import com.orangeandbronze.leaveapp.domain.Employee;
import com.orangeandbronze.leaveapp.domain.EmployeeRecord;
import com.orangeandbronze.leaveapp.domain.EmploymentStatus;
import com.orangeandbronze.leaveapp.service.EmployeeService;


@Controller
@Component
public class EmployeeController{
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public EmployeeController() { }
	
	@RequestMapping("/add_employee")
	public String addEmployee(Model model) {
		List<Department> departments = employeeService.findAllDepartments();
		model.addAttribute("departments", departments);
		return "add_employee";
	}
	
	@RequestMapping(value = "/submit_add_employee", method = RequestMethod.POST)
	public String submitAddEmployee(@RequestParam Map<String, String> reqParam, Model model) {
		EmploymentStatus status = reqParam.get("employeestatus") == null ? 
				EmploymentStatus.PROBATIONARY : EmploymentStatus.REGULAR;
		LocalDate regularizationDate = reqParam.get("regularizationdate").equals("") ?
				LocalDate.ofEpochDay(0) : LocalDate.parse(reqParam.get("regularizationdate"));
		EmployeeRecord record = new EmployeeRecord.Builder(
				reqParam.get("firstName"), 
				reqParam.get("lastName"), 
				LocalDate.parse(reqParam.get("employmentdate")), 
				new Department(Long.parseLong(reqParam.get("department")), ""), 
				reqParam.get("email"), 
				reqParam.get("employeeposition"))
				.contactNumber(reqParam.get("contactnumber"))
				.employmentStatus(status)
				.isSoloParent(reqParam.get("isSoloParent") == null)
				.regularizationDate(regularizationDate)
				.build();
		Employee employee = new Employee(1, record);
		checkPrivileges(reqParam, employee);
		int ret = employeeService.addEmployee(employee);
		model.addAttribute("message", "Employee " + employee + "has been successfully added!");
		return "submit_add_employee";
	}
	
	
	private void checkPrivileges(Map<String, String> reqParam, Employee employee) {
		if(reqParam.get("isadmin") != null)
			employee.grantAdminPrivelages();
		if(reqParam.get("issupervisor") != null)
			employee.grantSupervisorPrivileges();
		if(reqParam.get("ishr") != null)
			employee.grantHRPrivileges();
	}

	@RequestMapping("/view_all_employees")
	public String viewAllEmployees(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("employees", employees);
		
		return "employee_list";
	}
}