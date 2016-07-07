/**
 * 
 */
package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author MuzaffarS
 *
 */
public class NdaCalculateBean extends BeanBase implements Serializable {

	
	private static final long serialVersionUID = 1L;
	String pbId;
	String pbgrp;
	String flag;
	String month;
	String srtEmp;
	String Year;
	String srtCenter;
	String empToken;
	String empName;
	String ndaHrs;
	String ndaAmount;
	ArrayList att;
	String empType;
	String empid;
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public ArrayList getAtt() {
		return att;
	}
	public void setAtt(ArrayList att) {
		this.att = att;
	}
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getNdaAmount() {
		return ndaAmount;
	}
	public void setNdaAmount(String ndaAmount) {
		this.ndaAmount = ndaAmount;
	}
	public String getNdaHrs() {
		return ndaHrs;
	}
	public void setNdaHrs(String ndaHrs) {
		this.ndaHrs = ndaHrs;
	}
	public String getSrtCenter() {
		return srtCenter;
	}
	public void setSrtCenter(String srtCenter) {
		this.srtCenter = srtCenter;
	}
	public String getSrtEmp() {
		return srtEmp;
	}
	public void setSrtEmp(String srtEmp) {
		this.srtEmp = srtEmp;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getPbgrp() {
		return pbgrp;
	}
	public void setPbgrp(String pbgrp) {
		this.pbgrp = pbgrp;
	}
	public String getPbId() {
		return pbId;
	}
	public void setPbId(String pbId) {
		this.pbId = pbId;
	}

}
