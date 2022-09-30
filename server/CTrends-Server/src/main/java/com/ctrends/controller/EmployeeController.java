package com.ctrends.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctrends.model.Employee;
import com.ctrends.service.EmployeeService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class EmployeeController {

private static final Logger log = LogManager.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeService;
	
	Date date = new Date();
	
	SimpleDateFormat df = new SimpleDateFormat("h:mm a");
	SimpleDateFormat hour = new SimpleDateFormat("h");
	SimpleDateFormat min = new SimpleDateFormat("mm");
	
	String time = df.format(date).toString();
	
	int hr = Integer.valueOf(hour.format(date).toString());
	int mint = Integer.valueOf(min.format(date).toString());
	
	@GetMapping(value = "/test")
	public String testMethod() {
		
	   if(hr < 3) {
		   System.out.println("In time 42");
	   }else if(hr == 2) {
			if(mint > 0) {
				System.out.println("Late 45");
			}
		}else {
			System.out.println("Late 48");
		}
		System.out.println("Time : "+time);
		return "OK";
	}
	
	
	@PostMapping(value = "/insert/update/employee")
	public Employee insertEmployee(@RequestBody Employee e) {
		log.info("Insert Employee [{}]", e.getEmpName());
		
		Employee res = employeeService.insertEmployee(e);
		return res;
	}
	
	@PostMapping(value = "/delete/employee")
	public Employee deleteEmployee(@RequestBody Employee e) {
		log.info("Delete Employee [{}]", e.getEmpId());
		
		Employee res = employeeService.deleteEmployee(e);
		return res;
	}
	
	@GetMapping(value = "/get/all/employee")
	public List<Employee> getAllEmployee() {
		log.info("Get Employees Controller");
		
		return employeeService.getAllEmployee();
	}
	
	
}
