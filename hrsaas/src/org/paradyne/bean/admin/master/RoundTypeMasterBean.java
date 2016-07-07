package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class RoundTypeMasterBean extends BeanBase {

	
	private String roundType="";
	private String roundCode="";
	private String roundId="";
	private String hiddencode="";
	private String myPage="";
	
	ArrayList roundTypeMasterItt=null ;
	
	private String ittSrN0="";
	private String ittRoundTypeCode="";
	private String ittRoundType="";
	private boolean recordsAvailable = false;
	private String totalRecords = "";
	
	
	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getRoundId() {
		return roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public String getIttRoundTypeCode() {
		return ittRoundTypeCode;
	}

	public void setIttRoundTypeCode(String ittRoundTypeCode) {
		this.ittRoundTypeCode = ittRoundTypeCode;
	}

	public String getRoundType() {
		return roundType;
	}

	public void setRoundType(String roundType) {
		this.roundType = roundType;
	}

	public String getRoundCode() {
		return roundCode;
	}

	public void setRoundCode(String roundCode) {
		this.roundCode = roundCode;
	}

	public String getIttSrN0() {
		return ittSrN0;
	}

	public void setIttSrN0(String ittSrN0) {
		this.ittSrN0 = ittSrN0;
	}

	public String getIttRoundType() {
		return ittRoundType;
	}

	public ArrayList getRoundTypeMasterItt() {
		return roundTypeMasterItt;
	}

	public void setRoundTypeMasterItt(ArrayList roundTypeMasterItt) {
		this.roundTypeMasterItt = roundTypeMasterItt;
	}

	public void setIttRoundType(String ittRoundType) {
		this.ittRoundType = ittRoundType;
	}

	public boolean isRecordsAvailable() {
		return recordsAvailable;
	}

	public void setRecordsAvailable(boolean recordsAvailable) {
		this.recordsAvailable = recordsAvailable;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	
	 
	
	
	
}
