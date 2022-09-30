package com.ctrends.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrends.model.Attendence;
import com.ctrends.model.Employee;
import com.ctrends.service.AttendenceService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RequestMapping(value="/attnd", produces = "application/json")
public class AttendenceController {
	
	private static final Logger log = LogManager.getLogger(AttendenceController.class);
	
	@Autowired
	AttendenceService attendenceService;
	
	@PostMapping(value = "/create/attendence")
	public Attendence createAttendence(@RequestBody Attendence a) {
		log.info("Insert Attendence [{}]", a.getAttndDate());
		
		Attendence res = attendenceService.createAttendence(a);
		return res;
	}
	
	@PostMapping(value = "/create/leave")
	public Attendence createLeave(@RequestBody Attendence a) {
		log.info("Leave Attendence [{}]", a.getAttndId());
		
		Attendence res = attendenceService.leaveAttendence(a);
		return res;
	}
	
	@PostMapping(value = "/get/attendence")
	public List<Attendence> getAttendence(@RequestBody Attendence a){
		log.info("Get Attendence Controller [{}, {}]", a.getAttndDate(), a.getEmpId());
		
		return attendenceService.getAttendence(a);
	}
	
	@GetMapping(value = "/get/today/present")
	public List<Attendence> getTodayPresentForLeave(){
		log.info("Get today present");
		
		return attendenceService.getTodayPresent();
	}
	
	@GetMapping(value = "/get/today/present/list")
	public List<Attendence> getTodayPresent(){
		log.info("Get today present");
		
		return attendenceService.getTodayPresentList();
	}
	
	@PostMapping(value = "/search/by/employee")
	public List<Attendence> getSingleEmployee(@RequestBody Attendence a){
		log.info("Get Single Employee");
		
		return attendenceService.getSingleEmployee(a);
	}

	@PostMapping(value = "/search/by/between/date")
	public List<Attendence> getBetweenDate(@RequestBody Attendence a){
		log.info("Get Between Date");
		
		return attendenceService.getBetweenDate(a);
	}
}
