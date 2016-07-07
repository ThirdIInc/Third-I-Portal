package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class EvaluatorPanel extends BeanBase {

	private ArrayList dataList;
	private ArrayList eligibleList;
	private String myPage;
	private String show;
	
	private String apprId;
	private String apprStartDate;
	private String apprEndDate;
	private String empId;
	private String empName;
	private String empDeptName;
	private String empDesgName;
	private String phaseCode;
	private String phaseName;
	private String apprCode;
	
	private String noData="false";
	private String noData1="false";
	
	private String listType;
	private ArrayList processedList;
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public ArrayList getDataList() {
		return dataList;
	}
	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDeptName() {
		return empDeptName;
	}
	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}
	public String getEmpDesgName() {
		return empDesgName;
	}
	public void setEmpDesgName(String empDesgName) {
		this.empDesgName = empDesgName;
	}
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public ArrayList getProcessedList() {
		return processedList;
	}
	public void setProcessedList(ArrayList processedList) {
		this.processedList = processedList;
	}
	public ArrayList getEligibleList() {
		return eligibleList;
	}
	public void setEligibleList(ArrayList eligibleList) {
		this.eligibleList = eligibleList;
	}
	public String getNoData1() {
		return noData1;
	}
	public void setNoData1(String noData1) {
		this.noData1 = noData1;
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
	
}
