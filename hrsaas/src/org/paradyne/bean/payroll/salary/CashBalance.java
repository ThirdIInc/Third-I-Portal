package org.paradyne.bean.payroll.salary;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep 
 * Date:04-09-2008
 */

public class CashBalance extends BeanBase {
	private String empId="";
	private String empName="";
	private String empDept="";
	private String empBrn="";
	private String creditType="";
	private String balanceAmt="";
	private String totBalAmt="";
	private ArrayList balanceList = null;
	private String creditCode="";
	private String empToken="";
	private String onHoldAmt="";
	private String totAmt="";
	
	
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	public String getEmpBrn() {
		return empBrn;
	}
	public void setEmpBrn(String empBrn) {
		this.empBrn = empBrn;
	}
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	public String getBalanceAmt() {
		return balanceAmt;
	}
	public void setBalanceAmt(String balanceAmt) {
		this.balanceAmt = balanceAmt;
	}
	public String getTotBalAmt() {
		return totBalAmt;
	}
	public void setTotBalAmt(String totBalAmt) {
		this.totBalAmt = totBalAmt;
	}
	public ArrayList getBalanceList() {
		return balanceList;
	}
	public void setBalanceList(ArrayList balanceList) {
		this.balanceList = balanceList;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getOnHoldAmt() {
		return onHoldAmt;
	}
	public void setOnHoldAmt(String onHoldAmt) {
		this.onHoldAmt = onHoldAmt;
	}
	
	
}
