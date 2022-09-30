package com.ctrends.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctrends.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

	Employee findByEmpId(Long empId);

	List<Employee> findByIsActive(int i);

}
