package org.paradyne.bean.payroll.tablerecovery;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Venkatesh
 *
 */
public class TableRecoveryConf extends BeanBase {
	
	
	
	private String empID="";
	private String empName="";
	private String department="";
	private String center="";
	
	private String empTokenNo="";
	private String debitCode="";
	private String debitAbbr="";
	private String display="";
	
	
	ArrayList tabRecList=null;

	
	

	

	public TableRecoveryConf()
	{
		
	}
	
	public TableRecoveryConf(String invCode,String empID,String empName,String department,String center,String fromYear,String toYear,String invAmount,String invName,boolean chkEdit)
	{
		super();
		
		this.empID=empID;
		this.empName=empName;
		this.center=center;
		this.department=department;
		
	}
	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpTokenNo() {
		return empTokenNo;
	}

	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}

	public String getDebitAbbr() {
		return debitAbbr;
	}

	public void setDebitAbbr(String debitAbbr) {
		this.debitAbbr = debitAbbr;
	}

	public String getDebitCode() {
		return debitCode;
	}

	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}

	public ArrayList getTabRecList() {
		return tabRecList;
	}

	public void setTabRecList(ArrayList tabRecList) {
		this.tabRecList = tabRecList;
	}

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	
	
	
}