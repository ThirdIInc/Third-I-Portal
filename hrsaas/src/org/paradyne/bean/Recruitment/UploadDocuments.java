package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class UploadDocuments extends BeanBase {
	
	private String requisitionCode  ="";
	private String hiddenCode  ="";
	private String uploadLocFileName  ="";
	private String  dataPath ="";
	private String noData  ="";
	private String chkLength  ="";
	ArrayList ChkList=null; 
	private String checkListitemcode ="";
	private String  checkListName ="";
	private String  checkListresponce ="";
	private String  checkListComments ="";
	public String getCheckListitemcode() {
		return checkListitemcode;
	}
	public void setCheckListitemcode(String checkListitemcode) {
		this.checkListitemcode = checkListitemcode;
	}
	public String getCheckListName() {
		return checkListName;
	}
	public void setCheckListName(String checkListName) {
		this.checkListName = checkListName;
	}
	public String getCheckListresponce() {
		return checkListresponce;
	}
	public void setCheckListresponce(String checkListresponce) {
		this.checkListresponce = checkListresponce;
	}
	public String getCheckListComments() {
		return checkListComments;
	}
	public void setCheckListComments(String checkListComments) {
		this.checkListComments = checkListComments;
	}
	public ArrayList getChkList() {
		return ChkList;
	}
	public void setChkList(ArrayList chkList) {
		ChkList = chkList;
	}
	public String getChkLength() {
		return chkLength;
	}
	public void setChkLength(String chkLength) {
		this.chkLength = chkLength;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getUploadLocFileName() {
		return uploadLocFileName;
	}
	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getRequisitionCode() {
		return requisitionCode;
	}
	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}
}
