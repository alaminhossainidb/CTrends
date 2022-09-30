package com.ctrends.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_attendence")
public class Attendence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_attnd_key")
	private Long attndId;
	
	@Column(name = "id_emp_key")
	private Long empId;
	
	@Column(name = "dtt_attnd")
	private String attndDate;
	
	@Column(name = "tx_entry_time")
	private String entryTime;
	
	@Column(name = "tx_leave_time")
	private String leaveTime;
	
	@Column(name = "tx_entry_status")
	private String entryStatus;
	
	@Column(name = "tx_leave_status")
	private String leaveStatus;
	
	@Column(name = "tx_present_status")
	private String presentStatus;
	
	@Transient
	private String fromDate;
	
	@Transient
	private String toDate;

	public Long getAttndId() {
		return attndId;
	}

	public void setAttndId(Long attndId) {
		this.attndId = attndId;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getAttndDate() {
		return attndDate;
	}

	public void setAttndDate(String attndDate) {
		this.attndDate = attndDate;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getEntryStatus() {
		return entryStatus;
	}

	public void setEntryStatus(String entryStatus) {
		this.entryStatus = entryStatus;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getPresentStatus() {
		return presentStatus;
	}

	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	
	
	
	
}
