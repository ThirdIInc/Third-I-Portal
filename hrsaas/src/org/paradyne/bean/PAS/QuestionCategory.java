package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class QuestionCategory extends BeanBase {
	private String sQstCategoryCode;
	private String sQstCategoryName;
	private String sCategoryStatus;
	private Boolean flgStatus = false;
	private String sCategoryStatusDesc;
	private String sCategoryDescription;
	private ArrayList lstQuestionCategory; 
	private String sMode = null;
	private String myPage;
	
	private String hiddencode;
	
	private String readFlag="true";
	
	
	
	public String getSQstCategoryCode() {
		return sQstCategoryCode;
	}
	public void setSQstCategoryCode(String qstCategoryCode) {
		sQstCategoryCode = qstCategoryCode;
	}
	public String getSQstCategoryName() {
		return sQstCategoryName;
	}
	public void setSQstCategoryName(String qstCategoryName) {
		sQstCategoryName = qstCategoryName;
	}
	public String getSCategoryStatus() {
		return sCategoryStatus;
	}
	public void setSCategoryStatus(String categoryStatus) {
		sCategoryStatus = categoryStatus;
	}
	public String getSCategoryDescription() {
		return sCategoryDescription;
	}
	public void setSCategoryDescription(String categoryDescription) {
		sCategoryDescription = categoryDescription;
	}
	public ArrayList getLstQuestionCategory() {
		return lstQuestionCategory;
	}
	public void setLstQuestionCategory(ArrayList lstQuestionCategory) {
		this.lstQuestionCategory = lstQuestionCategory;
	}
	public String getSMode() {
		return sMode;
	}
	public void setSMode(String mode) {
		sMode = mode;
	}
	public String getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public Boolean getFlgStatus() {
		return flgStatus;
	}
	public void setFlgStatus(Boolean flgStatus) {
		this.flgStatus = flgStatus;
	}
	public String getSCategoryStatusDesc() {
		return sCategoryStatusDesc;
	}
	public void setSCategoryStatusDesc(String categoryStatusDesc) {
		sCategoryStatusDesc = categoryStatusDesc;
	}
	
}
