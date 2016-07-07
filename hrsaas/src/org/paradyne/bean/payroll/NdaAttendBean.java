/**
 * 
 */
package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;


import org.paradyne.lib.BeanBase;

import sun.util.calendar.BaseCalendar.Date;

/**
 * @author MuzaffarS
 *
 */
public class NdaAttendBean extends BeanBase implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ndaDate;
	String payGrp;
	String empToken;
	String empName;
	String shift;
	String ndaHrs;
	String ndaApprove;
	ArrayList iterat;
	String pbId;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getNdaApprove() {
		return ndaApprove;
	}
	public void setNdaApprove(String ndaApprove) {
		this.ndaApprove = ndaApprove;
	}
	public String getNdaDate() {
		return ndaDate;
	}
	public void setNdaDate(String ndaDate) {
		this.ndaDate = ndaDate;
	}
	public String getNdaHrs() {
		return ndaHrs;
	}
	public void setNdaHrs(String ndaHrs) {
		this.ndaHrs = ndaHrs;
	}
	public String getPayGrp() {
		return payGrp;
	}
	public void setPayGrp(String payGrp) {
		this.payGrp = payGrp;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public ArrayList getIterat() {
		return iterat;
	}
	public void setIterat(ArrayList iterat) {
		this.iterat = iterat;
	}
	public String getPbId() {
		return pbId;
	}
	public void setPbId(String pbId) {
		this.pbId = pbId;
	}

}
