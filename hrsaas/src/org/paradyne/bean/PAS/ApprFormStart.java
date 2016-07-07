package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ApprFormStart extends BeanBase {
	
	private ArrayList dataList;
	private ArrayList finalizedList;
	
	private String finalApprCodeItt;
	private String finalApprEmpIdItt;
	private String finalApprPromoCodeItt;
	private String finalTemplateCodeItt;
	private String finalStartDateItt;
	private String finalEndDateItt;
	private String finalData="false";
	private String finalApprEmpNameItt="";

	private String apprCode;
	private String apprId;
	private String apprStartDate;
	private String apprEndDate;
	private String happrId;
	private String apprValidTillDate;
	private String noData="false";
	
	
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getApprValidTillDate() {
		return apprValidTillDate;
	}
	public void setApprValidTillDate(String apprValidTillDate) {
		this.apprValidTillDate = apprValidTillDate;
	}
	public String getHapprId() {
		return happrId;
	}
	public void setHapprId(String happrId) {
		this.happrId = happrId;
	}
	public ArrayList getDataList() {
		return dataList;
	}
	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getApprStartDate() {
		return apprStartDate;
	}
	public void setApprStartDate(String apprStartDate) {
		this.apprStartDate = apprStartDate;
	}
	public String getApprEndDate() {
		return apprEndDate;
	}
	public void setApprEndDate(String apprEndDate) {
		this.apprEndDate = apprEndDate;
	}
	public ArrayList getFinalizedList() {
		return finalizedList;
	}
	public void setFinalizedList(ArrayList finalizedList) {
		this.finalizedList = finalizedList;
	}
	public String getFinalApprCodeItt() {
		return finalApprCodeItt;
	}
	public void setFinalApprCodeItt(String finalApprCodeItt) {
		this.finalApprCodeItt = finalApprCodeItt;
	}
	public String getFinalApprEmpIdItt() {
		return finalApprEmpIdItt;
	}
	public void setFinalApprEmpIdItt(String finalApprEmpIdItt) {
		this.finalApprEmpIdItt = finalApprEmpIdItt;
	}
	public String getFinalApprPromoCodeItt() {
		return finalApprPromoCodeItt;
	}
	public void setFinalApprPromoCodeItt(String finalApprPromoCodeItt) {
		this.finalApprPromoCodeItt = finalApprPromoCodeItt;
	}
	public String getFinalData() {
		return finalData;
	}
	public void setFinalData(String finalData) {
		this.finalData = finalData;
	}
	public String getFinalTemplateCodeItt() {
		return finalTemplateCodeItt;
	}
	public void setFinalTemplateCodeItt(String finalTemplateCodeItt) {
		this.finalTemplateCodeItt = finalTemplateCodeItt;
	}
	public String getFinalApprEmpNameItt() {
		return finalApprEmpNameItt;
	}
	public void setFinalApprEmpNameItt(String finalApprEmpNameItt) {
		this.finalApprEmpNameItt = finalApprEmpNameItt;
	}
	public String getFinalStartDateItt() {
		return finalStartDateItt;
	}
	public void setFinalStartDateItt(String finalStartDateItt) {
		this.finalStartDateItt = finalStartDateItt;
	}
	public String getFinalEndDateItt() {
		return finalEndDateItt;
	}
	public void setFinalEndDateItt(String finalEndDateItt) {
		this.finalEndDateItt = finalEndDateItt;
	}
	
	
	
	

}
