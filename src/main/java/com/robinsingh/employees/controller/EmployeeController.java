package com.robinsingh.employees.controller;

import com.robinsingh.employees.entity.Employee;
import com.robinsingh.employees.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService theEmployeeService){
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// load employee data
		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		Employee employee = new Employee();
		theModel.addAttribute(employee);
		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForAdd(@RequestParam("employeeId") int theId, Model theModel){

		Employee employee = employeeService.findById(theId);
		theModel.addAttribute(employee);
		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String showFormForAdd(@RequestParam("employeeId") int theId){

		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee theEmployee){
		employeeService.save(theEmployee);
		return "redirect:/employees/list";
	}
}









