package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1381
 *
 */
public class BusinessUnitBean extends BeanBase {
/**
 * Unit Code.*/ 
	private String unitCode = "";
/**
 * Unit Name.*/ 

	private String unitName = "";
/**
 * Unit Division.*/ 

	private String unitDivision = "";
/**
 * Unit Hdelete Code hidden field.*/ 

	private String hdeleteCode = "";
/**
 * Business Code unique hidden field. */ 
	
	private String businessCode = "";
/**
 * Division ID.*/ 
	private String divId = "";
/**
 *Paging field.*/ 

	private String myPage  = "";
/**
 * Mode Length Paging purpose.*/ 

	private String modeLength  = "";
/**
 * Total No of Records.*/ 

	private String totalNoOfRecords  = "";
/**
 * Displaying List.*/ 

	private List<BusinessUnitBean> businessUnitList;	

/**
 * Name of Employee*/
	private String empName="";
	
/**
 * Token of Employee*/
	private String empToken="";
	
/**
 *ID of Employee*/
	private String empID="";
	
/**
 * @return the unitCode
 */
	public String getUnitCode() {
		return this.unitCode;
	}
/**
 * @param unitCode the unitCode to set
 */
	public void setUnitCode(final String unitCode) {
		this.unitCode = unitCode;
	}
/**
 * @return the unitName
 */
	public String getUnitName() {
		return this.unitName;
	}
/**
 * @param unitName the unitName to set
 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}
/**
 * @return the unitDivision
 */
	public String getUnitDivision() {
		return this.unitDivision;
	}
/**
 * @param unitDivision the unitDivision to set
 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}
/**
 * @return the hdeleteCode
 */
	public String getHdeleteCode() {
		return this.hdeleteCode;
	}
/**
 * @param hdeleteCode the hdeleteCode to set
 */
	public void setHdeleteCode(final String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
/**
 * @return the myPage
 */
	public String getMyPage() {
		return this.myPage;
	}
/**
 * @param myPage the myPage to set
 */
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
/**
 * @return the modeLength
 */
	public String getModeLength() {
		return this.modeLength;
	}
/**
 * @param modeLength the modeLength to set
 */
	public void setModeLength(final String modeLength) {
		this.modeLength = modeLength;
	}
/**
 * @return the totalNoOfRecords
 */
	public String getTotalNoOfRecords() {
		return this.totalNoOfRecords;
	}
/**
 * @param totalNoOfRecords the totalNoOfRecords to set
 */	
	public void setTotalNoOfRecords(final String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
/**
 * @return the businessUnitList
 */
	public List<BusinessUnitBean> getBusinessUnitList() {
		return this.businessUnitList;
	}
/**
 * @param businessUnitList the businessUnitList to set
 */
	public void setBusinessUnitList(final List<BusinessUnitBean> businessUnitList) {
		this.businessUnitList = businessUnitList;
	}
/**
 * @return the businessCode
 */
	public String getBusinessCode() {
		return this.businessCode;
	}
/**
 * @param businessCode the businessCode to set
 */
	public void setBusinessCode(final String businessCode) {
		this.businessCode = businessCode;	
	}
/**
 * @return the divId
 */
	public String getDivId() {
		return this.divId;
	}
/**
 * @param divId the divId to set
 */
	public void setDivId(final String divId) {
		this.divId = divId;
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
 * @return the empID
 */
	public String getEmpID() {
		return empID;
	}
/**
 * @param empID the empID to set
 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}
}
