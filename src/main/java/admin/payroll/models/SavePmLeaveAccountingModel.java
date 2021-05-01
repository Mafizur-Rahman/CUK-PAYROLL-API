package admin.payroll.models;

import java.time.LocalDate;

//@Data
public class SavePmLeaveAccountingModel {

	private String empNo;
	private String empName;

	private String leaveCode;

	private String leaveDescr;

	private String leaveYear;

	private Integer totalLnd;

	private Integer remainLnd;

	private Integer iHalfLeaveAccumulated;

	private Integer iHalfLeaveEligible;

	private Integer iHalfLeaveAvailed;

	private Integer iHalfHalfDays;

	private Integer iiHalfLeaveAccumulated;

	private Integer iiHalfLeaveEligible;

	private Integer iiHalfLeaveAvailed;

	private Integer iiHalfHalfDays;

	private String iiHalfCreadit;

	private String logUser;

	private LocalDate logDate;

	private String logTime;

	private String logIp;

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getLeaveYear() {
		return leaveYear;
	}

	public void setLeaveYear(String leaveYear) {
		this.leaveYear = leaveYear;
	}

	public Integer getTotalLnd() {
		return totalLnd;
	}

	public void setTotalLnd(Integer totalLnd) {
		this.totalLnd = totalLnd;
	}

	public Integer getRemainLnd() {
		return remainLnd;
	}

	public void setRemainLnd(Integer remainLnd) {
		this.remainLnd = remainLnd;
	}

	public Integer getiHalfLeaveAccumulated() {
		return iHalfLeaveAccumulated;
	}

	public void setiHalfLeaveAccumulated(Integer iHalfLeaveAccumulated) {
		this.iHalfLeaveAccumulated = iHalfLeaveAccumulated;
	}

	public Integer getiHalfLeaveEligible() {
		return iHalfLeaveEligible;
	}

	public void setiHalfLeaveEligible(Integer iHalfLeaveEligible) {
		this.iHalfLeaveEligible = iHalfLeaveEligible;
	}

	public Integer getiHalfLeaveAvailed() {
		return iHalfLeaveAvailed;
	}

	public void setiHalfLeaveAvailed(Integer iHalfLeaveAvailed) {
		this.iHalfLeaveAvailed = iHalfLeaveAvailed;
	}

	public Integer getiHalfHalfDays() {
		return iHalfHalfDays;
	}

	public void setiHalfHalfDays(Integer iHalfHalfDays) {
		this.iHalfHalfDays = iHalfHalfDays;
	}

	public Integer getIiHalfLeaveAccumulated() {
		return iiHalfLeaveAccumulated;
	}

	public void setIiHalfLeaveAccumulated(Integer iiHalfLeaveAccumulated) {
		this.iiHalfLeaveAccumulated = iiHalfLeaveAccumulated;
	}

	public Integer getIiHalfLeaveEligible() {
		return iiHalfLeaveEligible;
	}

	public void setIiHalfLeaveEligible(Integer iiHalfLeaveEligible) {
		this.iiHalfLeaveEligible = iiHalfLeaveEligible;
	}

	public Integer getIiHalfLeaveAvailed() {
		return iiHalfLeaveAvailed;
	}

	public void setIiHalfLeaveAvailed(Integer iiHalfLeaveAvailed) {
		this.iiHalfLeaveAvailed = iiHalfLeaveAvailed;
	}

	public Integer getIiHalfHalfDays() {
		return iiHalfHalfDays;
	}

	public void setIiHalfHalfDays(Integer iiHalfHalfDays) {
		this.iiHalfHalfDays = iiHalfHalfDays;
	}

	public String getIiHalfCreadit() {
		return iiHalfCreadit;
	}

	public void setIiHalfCreadit(String iiHalfCreadit) {
		this.iiHalfCreadit = iiHalfCreadit;
	}

	public String getLogUser() {
		return logUser;
	}

	public void setLogUser(String logUser) {
		this.logUser = logUser;
	}

	public LocalDate getLogDate() {
		return logDate;
	}

	public void setLogDate(LocalDate logDate) {
		this.logDate = logDate;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getLogIp() {
		return logIp;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getLeaveDescr() {
		return leaveDescr;
	}

	public void setLeaveDescr(String leaveDescr) {
		this.leaveDescr = leaveDescr;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

}
