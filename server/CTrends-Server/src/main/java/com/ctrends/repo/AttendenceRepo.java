package com.ctrends.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctrends.model.Attendence;

public interface AttendenceRepo extends JpaRepository<Attendence, Long>{

	Attendence findByAttndId(Long attndId);

	List<Attendence> findByAttndDateAndEmpId(String attndDate, Long empId);

	List<Attendence> findByAttndDate(String date);

	List<Attendence> findByPresentStatusAndAttndDateAndLeaveTime(String string, String date, Object object);

	List<Attendence> findByEmpIdAndAttndDateBetween(Long empId, String fromDate, String toDate);

	List<Attendence> findByAttndDateBetween(String fromDate, String toDate);

}
