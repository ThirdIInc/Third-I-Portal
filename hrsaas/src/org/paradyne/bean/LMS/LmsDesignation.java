package org.paradyne.bean.LMS;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class LmsDesignation extends BeanBase {
	private ArrayList mappedDesignationlist ;
	private ArrayList pendingDesignationlist ;
	 private String designation = "";
	 private String employeeRole = "";
	 private String designationID = "";	
	 private int abbrevitaionCodeCheck=0;
	 private String hiddenDesignationAbbreviation = "";
	private String empRole ="";
	private ArrayList designationTypeItterator;
	private String designationListTypeID = "";
	private String designationAbbreviation ="";
	private String designationListTypeName = "";
	private boolean showList = false;
	private boolean showDetails = false;
	private String listLength="false";	
	private String mapListLength = "false";
	
	private String pendingDesignationID = "";
	private String pendingDesignation = "";
	private String pendingEmpRole = ""; 	
	private String pendingDropdown = "";
	private String hiddenDesignationTypeCode = "";
	private String confChk = "";
	private String confDelChk = ""; 
	private String hiddenMappedDesignationTypeCode = "";
	public String getHiddenMappedDesignationTypeCode() {
		return hiddenMappedDesignationTypeCode;
	}
	public void setHiddenMappedDesignationTypeCode(
			String hiddenMappedDesignationTypeCode) {
		this.hiddenMappedDesignationTypeCode = hiddenMappedDesignationTypeCode;
	}
	public String getConfDelChk() {
		return confDelChk;
	}
	public void setConfDelChk(String confDelChk) {
		this.confDelChk = confDelChk;
	}
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	public String getPendingDesignationID() {
		return pendingDesignationID;
	}
	public void setPendingDesignationID(String pendingDesignationID) {
		this.pendingDesignationID = pendingDesignationID;
	}
	public String getPendingDesignation() {
		return pendingDesignation;
	}
	public void setPendingDesignation(String pendingDesignation) {
		this.pendingDesignation = pendingDesignation;
	}
	public String getPendingEmpRole() {
		return pendingEmpRole;
	}
	public void setPendingEmpRole(String pendingEmpRole) {
		this.pendingEmpRole = pendingEmpRole;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public ArrayList getDesignationTypeItterator() {
		return designationTypeItterator;
	}
	public void setDesignationTypeItterator(ArrayList designationTypeItterator) {
		this.designationTypeItterator = designationTypeItterator;
	}
	public String getDesignationListTypeID() {
		return designationListTypeID;
	}
	public void setDesignationListTypeID(String designationListTypeID) {
		this.designationListTypeID = designationListTypeID;
	}
	
	public String getDesignationListTypeName() {
		return designationListTypeName;
	}
	public void setDesignationListTypeName(String designationListTypeName) {
		this.designationListTypeName = designationListTypeName;
	}
	public String getEmpRole() {
		return empRole;
	}
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}
	
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public String getDesignationID() {
		return designationID;
	}
	public void setDesignationID(String designationID) {
		this.designationID = designationID;
	}
	public boolean isShowList() {
		return showList;
	}
	public void setShowList(boolean showList) {
		this.showList = showList;
	}
	public boolean isShowDetails() {
		return showDetails;
	}
	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}
	public ArrayList getMappedDesignationlist() {
		return mappedDesignationlist;
	}
	public void setMappedDesignationlist(ArrayList mappedDesignationlist) {
		this.mappedDesignationlist = mappedDesignationlist;
	}
	public ArrayList getPendingDesignationlist() {
		return pendingDesignationlist;
	}
	public void setPendingDesignationlist(ArrayList pendingDesignationlist) {
		this.pendingDesignationlist = pendingDesignationlist;
	}
	public int getAbbrevitaionCodeCheck() {
		return abbrevitaionCodeCheck;
	}
	public void setAbbrevitaionCodeCheck(int abbrevitaionCodeCheck) {
		this.abbrevitaionCodeCheck = abbrevitaionCodeCheck;
	}
	public String getPendingDropdown() {
		return pendingDropdown;
	}
	public void setPendingDropdown(String pendingDropdown) {
		this.pendingDropdown = pendingDropdown;
	}
	public String getHiddenDesignationTypeCode() {
		return hiddenDesignationTypeCode;
	}
	public void setHiddenDesignationTypeCode(String hiddenDesignationTypeCode) {
		this.hiddenDesignationTypeCode = hiddenDesignationTypeCode;
	}
	public String getDesignationAbbreviation() {
		return designationAbbreviation;
	}
	public void setDesignationAbbreviation(String designationAbbreviation) {
		this.designationAbbreviation = designationAbbreviation;
	}
	public String getHiddenDesignationAbbreviation() {
		return hiddenDesignationAbbreviation;
	}
	public void setHiddenDesignationAbbreviation(
			String hiddenDesignationAbbreviation) {
		this.hiddenDesignationAbbreviation = hiddenDesignationAbbreviation;
	}
	public String getMapListLength() {
		return mapListLength;
	}
	public void setMapListLength(String mapListLength) {
		this.mapListLength = mapListLength;
	}
	
	
	
}
