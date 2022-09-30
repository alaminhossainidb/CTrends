package com.ctrends.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctrends.model.Attendence;
import com.ctrends.repo.AttendenceRepo;
import com.ctrends.util.Status;

@Service
public class AttendenceService {

	private static final Logger log = LogManager.getLogger(AttendenceService.class);
	
	@Autowired
	AttendenceRepo attendenceRepo;
	
	Date date = new Date();
	
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat df = new SimpleDateFormat("h:mm a");
	SimpleDateFormat hour = new SimpleDateFormat("h");
	SimpleDateFormat min = new SimpleDateFormat("mm");
	SimpleDateFormat ap = new SimpleDateFormat("a");
	
	String ddmmyyyy = dt.format(date);
	
	String time = df.format(date).toString();
	
	int hr = Integer.valueOf(hour.format(date).toString());
	int mint = Integer.valueOf(min.format(date).toString());
	String ampm = ap.format(date).toString();

	public Attendence createAttendence(Attendence a) {
		log.info("Create Attendence Service [{}, {}]", a.getPresentStatus(), ampm);
		
		Attendence res = null;
		
		if (a != null) {
			a.setAttndDate(ddmmyyyy);
			
			if (a.getPresentStatus().equals(Status.PRESENT.toString()) ) {
				a.setEntryTime(time);
				
				if(ampm.equals("AM")) {
					if(hr < 9) {
						a.setEntryStatus(Status.IN_TIME.toString());
					}else if(hr == 9) {
						if(mint > 0) {
							a.setEntryStatus(Status.LATE.toString());
						}else {
							a.setEntryStatus(Status.IN_TIME.toString());
						}
					}else {
						a.setEntryStatus(Status.LATE.toString());
					}
				}else {
					System.out.println("61");
					a.setEntryStatus(Status.LATE.toString());
				}
				
			}
			
			
			
			res = attendenceRepo.save(a);
		}
		return res;
	}

	public Attendence leaveAttendence(Attendence a) {
		log.info("Leave Attendence Service [{}]", a.getAttndId());
		
		Attendence val = attendenceRepo.findByAttndId(a.getAttndId());
		if (a.getAttndId() > 0 && val != null) {
			val.setLeaveTime(time);
			
			if(hr < 6) {
				val.setLeaveStatus(Status.EARLY_LEAVE.toString());
			}else if(hr == 6) {
				if(mint > 0) {
					val.setLeaveStatus(Status.IN_TIME.toString());
				}else {
					val.setLeaveStatus(Status.EARLY_LEAVE.toString());
				}
			}else {
				val.setLeaveStatus(Status.IN_TIME.toString());
			}
			
			attendenceRepo.save(val);
		}
		return val;
	}

	public List<Attendence> getAttendence(Attendence a) {
		log.info("Get Attendence Service [{}, {}]", a.getAttndDate(), a.getEmpId());
		
		List<Attendence> res = null;
		if (a != null) {
			res = attendenceRepo.findByAttndDateAndEmpId(a.getAttndDate(), a.getEmpId());
		}
		return res;
	}

	public List<Attendence> getTodayPresent() {
		log.info("Get Today Present Service");
		
		List<Attendence> res = attendenceRepo.findByPresentStatusAndAttndDateAndLeaveTime(Status.PRESENT.toString(), ddmmyyyy, null);
		log.info("Get Today Present found [{}]", res.size());
		return res;
	}

	public List<Attendence> getTodayPresentList() {
		
		List<Attendence> res = attendenceRepo.findByAttndDate(ddmmyyyy);
		log.info("Get Today Present List found [{}, {}]", res.size(), ddmmyyyy);
		return res;
	}

	public List<Attendence> getSingleEmployee(Attendence a) {
		log.info("Get Single Employee Service [{}, {}, {}]", a.getEmpId(), a.getFromDate(), a.getToDate());
		List<Attendence> res = attendenceRepo.findByEmpIdAndAttndDateBetween(a.getEmpId(), a.getFromDate(), a.getToDate());
		log.info("Get Single Employee List found [{}]", res.size());
		return res;
	}

	public List<Attendence> getBetweenDate(Attendence a) {
		log.info("Get Between Date Service [{}, {}]", a.getFromDate(), a.getToDate());
		List<Attendence> res = attendenceRepo.findByAttndDateBetween(a.getFromDate(), a.getToDate());
		log.info("Get Between Date List found [{}]", res.size());
		return res;
	}
}
