package org.paradyne.bean.helpdesk;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class HelpDeskTechRep extends BeanBase {
	
	private String myPage ;
	private String totalRecords ;
	private boolean modeLength = false ; 
	private boolean technicianLength = false ; 
	private String rowId ;
	private String managerCode ;
	private String managerToken ;
	private String managerName ;
	private String technicianCode ;
	private String technicianToken ;
	private String technicianName ;
	private String technicianIdItt ;
	private String technicianTokenItt ;
	private String technicianNameItt ;
	
	private ArrayList managerList=null;
	private String managerIdItt ;
	private String managerTokenItt ;
	private String managerNameItt ;
	private String hiddenManagerId ;
	
	private ArrayList techniciansList=null;

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getTechnicianNameItt() {
		return technicianNameItt;
	}

	public void setTechnicianNameItt(String technicianNameItt) {
		this.technicianNameItt = technicianNameItt;
	}

	public String getTechnicianIdItt() {
		return technicianIdItt;
	}

	public void setTechnicianIdItt(String technicianIdItt) {
		this.technicianIdItt = technicianIdItt;
	}

	public String getManagerToken() {
		return managerToken;
	}

	public void setManagerToken(String managerToken) {
		this.managerToken = managerToken;
	}

	public String getTechnicianTokenItt() {
		return technicianTokenItt;
	}

	public void setTechnicianTokenItt(String technicianTokenItt) {
		this.technicianTokenItt = technicianTokenItt;
	}

	public ArrayList getTechniciansList() {
		return techniciansList;
	}

	public void setTechniciansList(ArrayList techniciansList) {
		this.techniciansList = techniciansList;
	}

	public ArrayList getManagerList() {
		return managerList;
	}

	public void setManagerList(ArrayList managerList) {
		this.managerList = managerList;
	}

	public String getManagerIdItt() {
		return managerIdItt;
	}

	public void setManagerIdItt(String managerIdItt) {
		this.managerIdItt = managerIdItt;
	}

	public String getManagerNameItt() {
		return managerNameItt;
	}

	public void setManagerNameItt(String managerNameItt) {
		this.managerNameItt = managerNameItt;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public boolean isModeLength() {
		return modeLength;
	}

	public void setModeLength(boolean modeLength) {
		this.modeLength = modeLength;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getManagerTokenItt() {
		return managerTokenItt;
	}

	public void setManagerTokenItt(String managerTokenItt) {
		this.managerTokenItt = managerTokenItt;
	}

	public String getTechnicianCode() {
		return technicianCode;
	}

	public void setTechnicianCode(String technicianCode) {
		this.technicianCode = technicianCode;
	}

	public String getTechnicianToken() {
		return technicianToken;
	}

	public void setTechnicianToken(String technicianToken) {
		this.technicianToken = technicianToken;
	}

	public String getTechnicianName() {
		return technicianName;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

	public String getHiddenManagerId() {
		return hiddenManagerId;
	}

	public void setHiddenManagerId(String hiddenManagerId) {
		this.hiddenManagerId = hiddenManagerId;
	}

	public boolean isTechnicianLength() {
		return technicianLength;
	}

	public void setTechnicianLength(boolean technicianLength) {
		this.technicianLength = technicianLength;
	}
}
