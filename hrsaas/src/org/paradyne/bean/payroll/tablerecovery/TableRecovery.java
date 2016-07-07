package org.paradyne.bean.payroll.tablerecovery;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Venkatesh
 *
 */
public class TableRecovery extends BeanBase {
	
	
	private String debitCode="";
	private String debitName="";
	private String empId="";
	private String empToken="";
	private String empName="";
	private String empDebAmt="";
	private String salDebAmt="";
	private String total="";
	private String loanAmt="";
	private String month="";
	private String year="";
	private String display="";
	private String payBillNo="";
	private String uploadFileName="";
	
	private ArrayList recList=null;
	
	/**
	 * @return the debitCode
	 */
	public String getDebitCode() {
		return debitCode;
	}

	/**
	 * @param debitCode the debitCode to set
	 */
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}

	/**
	 * @return the debitName
	 */
	public String getDebitName() {
		return debitName;
	}

	/**
	 * @param debitName the debitName to set
	 */
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}

	/**
	 * @return the empDebAmt
	 */
	public String getEmpDebAmt() {
		return empDebAmt;
	}

	/**
	 * @param empDebAmt the empDebAmt to set
	 */
	public void setEmpDebAmt(String empDebAmt) {
		this.empDebAmt = empDebAmt;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the salDebAmt
	 */
	public String getSalDebAmt() {
		return salDebAmt;
	}

	/**
	 * @param salDebAmt the salDebAmt to set
	 */
	public void setSalDebAmt(String salDebAmt) {
		this.salDebAmt = salDebAmt;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	public TableRecovery()
	{
		
	}
	
	public TableRecovery(String x)
	{
		super();
		
			
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the recList
	 */
	public ArrayList getRecList() {
		return recList;
	}

	/**
	 * @param recList the recList to set
	 */
	public void setRecList(ArrayList recList) {
		this.recList = recList;
	}

	/**
	 * @return the loanAmt
	 */
	public String getLoanAmt() {
		return loanAmt;
	}

	/**
	 * @param loanAmt the loanAmt to set
	 */
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
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

	/**
	 * @return the payBillNo
	 */
	public String getPayBillNo() {
		return payBillNo;
	}

	/**
	 * @param payBillNo the payBillNo to set
	 */
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	
	
	
}