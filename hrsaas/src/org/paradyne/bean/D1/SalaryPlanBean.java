package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**Nilesh D**/

public class SalaryPlanBean extends BeanBase {
	
	public String zipCode="";
	public String salaryPlan="";

	public String hdeleteCode="";
	public String salCode="";

	private String myPage  = "" ;
	private String modeLength  = "" ;
	private String totalNoOfRecords  = "" ;
	private ArrayList salaryPlanList=null;
	
	
	
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the salaryPlan
	 */
	public String getSalaryPlan() {
		return salaryPlan;
	}
	/**
	 * @param salaryPlan the salaryPlan to set
	 */
	public void setSalaryPlan(String salaryPlan) {
		this.salaryPlan = salaryPlan;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @return the salCode
	 */
	public String getSalCode() {
		return salCode;
	}
	/**
	 * @param salCode the salCode to set
	 */
	public void setSalCode(String salCode) {
		this.salCode = salCode;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	/**
	 * @param totalNoOfRecords the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	/**
	 * @return the salaryPlanList
	 */
	public ArrayList getSalaryPlanList() {
		return salaryPlanList;
	}
	/**
	 * @param salaryPlanList the salaryPlanList to set
	 */
	public void setSalaryPlanList(ArrayList salaryPlanList) {
		this.salaryPlanList = salaryPlanList;
	}

}
