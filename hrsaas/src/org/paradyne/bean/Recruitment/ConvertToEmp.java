package org.paradyne.bean.Recruitment;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
public class ConvertToEmp extends BeanBase {
	private String candidateName="";
	private String candidateCode="";
	private String position="";
	private String hireMan="";
	private String empType="";
	private String reqsCode="";
	private String reqsName="";
	private String candResume="";
	private String myPage="";
	private String data=""; 
	private String empId="";
	private String desgCode="";
	private String desgName="";
	private String deptName="";
	private String deptCode="";
	private String branchCode="";
	private String branchName="";
	private String divisionName="";
	private String divisionCode="";
	private ArrayList list=null;
	private String emplFlag="false";
	private String srNo="";
	private String empTok="";
	private String empName="";
	private String empBrn="";
	private String empDept="";
	private String empDesg="";
	private String empDiv="";
	private String empTyp="";
	private ArrayList empList=null;
	private String myPage1="";
	private String radioFlag="";
	private String radioChk="";
	private String positionId="";
	private String requisitionId="";
	private String candCode1="";
	private String requisitionName="";
	private String positionName="";
	private String candidateName1="";
	private String employeeName="";
	private String empTypeId="";
	private String modeLength="";
	private String totalRecords="";
	private String chkSerch="";
	private boolean clearFlag=false;
	private String searchFlag="false";
	private boolean enableFilterValue=false;
	private boolean showFilterValue=false;
	private String applyFilterFlag="false";
	private String pageNoField="";
	private String totalPage="";
	private String appointmentCode = "";
	private String appointmentCodeItr = "";
	private String ittrdivisionCode = "";
	private String ittrdesgnCode = "";
	private String ittrbranchCode = "";
	private String ittrdeptCode = "";
	
	public String getIttrdeptCode() {
		return ittrdeptCode;
	}
	public void setIttrdeptCode(String ittrdeptCode) {
		this.ittrdeptCode = ittrdeptCode;
	}
	public String getIttrbranchCode() {
		return ittrbranchCode;
	}
	public void setIttrbranchCode(String ittrbranchCode) {
		this.ittrbranchCode = ittrbranchCode;
	}
	public String getIttrdesgnCode() {
		return ittrdesgnCode;
	}
	public void setIttrdesgnCode(String ittrdesgnCode) {
		this.ittrdesgnCode = ittrdesgnCode;
	}
	public String getAppointmentCodeItr() {
		return appointmentCodeItr;
	}
	public void setAppointmentCodeItr(String appointmentCodeItr) {
		this.appointmentCodeItr = appointmentCodeItr;
	}
	public String getApplyFilterFlag() {
		return applyFilterFlag;
	}
	public void setApplyFilterFlag(String applyFilterFlag) {
		this.applyFilterFlag = applyFilterFlag;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getEmpTypeId() {
		return empTypeId;
	}
	public void setEmpTypeId(String empTypeId) {
		this.empTypeId = empTypeId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getRequisitionId() {
		return requisitionId;
	}
	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}
	public String getCandCode1() {
		return candCode1;
	}
	public void setCandCode1(String candCode1) {
		this.candCode1 = candCode1;
	}
	public String getRequisitionName() {
		return requisitionName;
	}
	public void setRequisitionName(String requisitionName) {
		this.requisitionName = requisitionName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getCandidateName1() {
		return candidateName1;
	}
	public void setCandidateName1(String candidateName1) {
		this.candidateName1 = candidateName1;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public ArrayList getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getHireMan() {
		return hireMan;
	}
	public void setHireMan(String hireMan) {
		this.hireMan = hireMan;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getReqsCode() {
		return reqsCode;
	}
	public void setReqsCode(String reqsCode) {
		this.reqsCode = reqsCode;
	}
	public String getCandResume() {
		return candResume;
	}
	public void setCandResume(String candResume) {
		this.candResume = candResume;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getReqsName() {
		return reqsName;
	}
	public void setReqsName(String reqsName) {
		this.reqsName = reqsName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getEmpTok() {
		return empTok;
	}
	public void setEmpTok(String empTok) {
		this.empTok = empTok;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpBrn() {
		return empBrn;
	}
	public void setEmpBrn(String empBrn) {
		this.empBrn = empBrn;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	public String getEmpDesg() {
		return empDesg;
	}
	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
	}
	public String getEmpDiv() {
		return empDiv;
	}
	public void setEmpDiv(String empDiv) {
		this.empDiv = empDiv;
	}
	public String getEmpTyp() {
		return empTyp;
	}
	public void setEmpTyp(String empTyp) {
		this.empTyp = empTyp;
	}
	public String getEmplFlag() {
		return emplFlag;
	}
	public void setEmplFlag(String emplFlag) {
		this.emplFlag = emplFlag;
	}
	public String getMyPage1() {
		return myPage1;
	}
	public void setMyPage1(String myPage1) {
		this.myPage1 = myPage1;
	}
	public String getRadioFlag() {
		return radioFlag;
	}
	public void setRadioFlag(String radioFlag) {
		this.radioFlag = radioFlag;
	}
	public String getRadioChk() {
		return radioChk;
	}
	public void setRadioChk(String radioChk) {
		this.radioChk = radioChk;
	}
	public boolean isClearFlag() {
		return clearFlag;
	}
	public void setClearFlag(boolean clearFlag) {
		this.clearFlag = clearFlag;
	}
	public boolean isEnableFilterValue() {
		return enableFilterValue;
	}
	public void setEnableFilterValue(boolean enableFilterValue) {
		this.enableFilterValue = enableFilterValue;
	}
	public boolean isShowFilterValue() {
		return showFilterValue;
	}
	public void setShowFilterValue(boolean showFilterValue) {
		this.showFilterValue = showFilterValue;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getChkSerch() {
		return chkSerch;
	}
	public void setChkSerch(String chkSerch) {
		this.chkSerch = chkSerch;
	}
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getAppointmentCode() {
		return appointmentCode;
	}
	public void setAppointmentCode(String appointmentCode) {
		this.appointmentCode = appointmentCode;
	}
	public String getIttrdivisionCode() {
		return ittrdivisionCode;
	}
	public void setIttrdivisionCode(String ittrdivisionCode) {
		this.ittrdivisionCode = ittrdivisionCode;
	}
	
}


