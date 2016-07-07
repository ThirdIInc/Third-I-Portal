/**
 * 
 */
package org.paradyne.bean.employeeSurvey;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba_Joseph
 *
 */
public class EmployeeSurveyMaster extends BeanBase {
	private String surveyCode="";
	private String surveyName="";
	private String frmDate;
	private String toDate;
	private String sectionName="";
	private ArrayList sectionList;
	private String sectionNameItt="";
	private String sectionCodeItt="";
	private String srNo="";
	private String sectionFlag="false";
	private String paraId="";
	private String sectionId;
	
	/**
	 * @return the surveyCode
	 */
	public String getSurveyCode() {
		return surveyCode;
	}
	/**
	 * @param surveyCode the surveyCode to set
	 */
	public void setSurveyCode(String surveyCode) {
		this.surveyCode = surveyCode;
	}
	/**
	 * @return the surveyName
	 */
	public String getSurveyName() {
		return surveyName;
	}
	/**
	 * @param surveyName the surveyName to set
	 */
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}
	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	/**
	 * @return the frmDate
	 */
	public String getFrmDate() {
		return frmDate;
	}
	/**
	 * @param frmDate the frmDate to set
	 */
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the sectionList
	 */
	public ArrayList getSectionList() {
		return sectionList;
	}
	/**
	 * @param sectionList the sectionList to set
	 */
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	/**
	 * @return the sectionNameItt
	 */
	public String getSectionNameItt() {
		return sectionNameItt;
	}
	/**
	 * @param sectionNameItt the sectionNameItt to set
	 */
	public void setSectionNameItt(String sectionNameItt) {
		this.sectionNameItt = sectionNameItt;
	}
	/**
	 * @return the sectionCodeItt
	 */
	public String getSectionCodeItt() {
		return sectionCodeItt;
	}
	/**
	 * @param sectionCodeItt the sectionCodeItt to set
	 */
	public void setSectionCodeItt(String sectionCodeItt) {
		this.sectionCodeItt = sectionCodeItt;
	}
	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return srNo;
	}
	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	/**
	 * @return the sectionFlag
	 */
	public String getSectionFlag() {
		return sectionFlag;
	}
	/**
	 * @param sectionFlag the sectionFlag to set
	 */
	public void setSectionFlag(String sectionFlag) {
		this.sectionFlag = sectionFlag;
	}
	/**
	 * @return the sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}
	/**
	 * @param sectionName the sectionName to set
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	/**
	 * @return the sectionId
	 */
	public String getSectionId() {
		return sectionId;
	}
	/**
	 * @param sectionId the sectionId to set
	 */
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
}
