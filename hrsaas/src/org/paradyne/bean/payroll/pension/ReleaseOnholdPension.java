package org.paradyne.bean.payroll.pension;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author REEBA_JOSEPH
 * 25 OCTOBER 2010
 *
 */

public class ReleaseOnholdPension extends BeanBase {
	private String month = "";
	private String year = "";
	private String divCode = "";
	private String divName = "";
	private String empId = "";
	private String empToken = "";
	private String empName = "";
	private String ledgerCode = "";
	ArrayList empList;
	private boolean recordsAvailable = false;
	private String viewEmp = "false";
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public ArrayList getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}
	public boolean isRecordsAvailable() {
		return recordsAvailable;
	}
	public void setRecordsAvailable(boolean recordsAvailable) {
		this.recordsAvailable = recordsAvailable;
	}
	public String getViewEmp() {
		return viewEmp;
	}
	public void setViewEmp(String viewEmp) {
		this.viewEmp = viewEmp;
	}
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
}
