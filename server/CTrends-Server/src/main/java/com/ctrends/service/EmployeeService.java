package com.ctrends.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ctrends.model.Employee;
import com.ctrends.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	private static final Logger log = LogManager.getLogger(EmployeeService.class);
	
	@Autowired
	EmployeeRepo employeeRepo;

	public Employee insertEmployee(Employee e) {
		log.info("Employee Insert Service [{}]", e.getEmpName());
		
		Employee res = null;
		if (e != null) {
			res = employeeRepo.save(e);
		}
		
		return res;
	}

	public Employee deleteEmployee(Employee e) {
		log.info("Employee Delete Service [{}]", e.getEmpId());
		
		Employee emp = employeeRepo.findByEmpId(e.getEmpId());
		
		if (emp != null) {
			emp.setIsActive(0);
			employeeRepo.save(emp);
		}
		return emp;
	}

	public List<Employee> getAllEmployee() {
		log.info("Get All Employee List Service");
		
		List<Employee> res = employeeRepo.findByIsActive(1);
		log.info("Employee List found [{}]", res.size());
		
		return res;
	}

}
