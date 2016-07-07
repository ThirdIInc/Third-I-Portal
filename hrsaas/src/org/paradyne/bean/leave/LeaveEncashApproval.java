package org.paradyne.bean.leave;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LeaveEncashApproval extends BeanBase {

	private String status;
	private String tokenNo = "";
	private String date = "";
	private String level = "";
	private String empCode = "";
	private String empName = "";
	private String checkStatus = "";
	private String app = "false";
	private String pen = "false";
	private String rej = "false";
	private String hol = "false";
	private String noData = "false";
	private String apprflag = "false";
	private String listLength = "0";
	private String encashAppNo = "";
	private String statusRemark = "";
	private String statusNew = "";
	
	private String myPage;
	private String show;
	private String selectname;

	private ArrayList list = null;

	// fields for payment details
	private String salarymonth = "";
	private String salaryyear = "";
	private String creditCode = "";
	private String creditType = "";
	private String salaryCheck = "";
	
	private String approverComments = "";
	private String approverCode = "";
	
	public String getApproverCode() {
		return approverCode;
	}

	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getPen() {
		return pen;
	}

	public void setPen(String pen) {
		this.pen = pen;
	}

	public String getRej() {
		return rej;
	}

	public void setRej(String rej) {
		this.rej = rej;
	}

	public String getHol() {
		return hol;
	}

	public void setHol(String hol) {
		this.hol = hol;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getApprflag() {
		return apprflag;
	}

	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}

	public String getListLength() {
		return listLength;
	}

	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	public String getEncashAppNo() {
		return encashAppNo;
	}

	public void setEncashAppNo(String encashAppNo) {
		this.encashAppNo = encashAppNo;
	}

	public String getStatusRemark() {
		return statusRemark;
	}

	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}

	public String getStatusNew() {
		return statusNew;
	}

	public void setStatusNew(String statusNew) {
		this.statusNew = statusNew;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @return the selectname
	 */
	public String getSelectname() {
		return selectname;
	}

	/**
	 * @param selectname the selectname to set
	 */
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}

	/**
	 * @return the salarymonth
	 */
	public String getSalarymonth() {
		return salarymonth;
	}

	/**
	 * @param salarymonth the salarymonth to set
	 */
	public void setSalarymonth(String salarymonth) {
		this.salarymonth = salarymonth;
	}

	/**
	 * @return the salaryyear
	 */
	public String getSalaryyear() {
		return salaryyear;
	}

	/**
	 * @param salaryyear the salaryyear to set
	 */
	public void setSalaryyear(String salaryyear) {
		this.salaryyear = salaryyear;
	}

	/**
	 * @return the creditCode
	 */
	public String getCreditCode() {
		return creditCode;
	}

	/**
	 * @param creditCode the creditCode to set
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	/**
	 * @return the creditType
	 */
	public String getCreditType() {
		return creditType;
	}

	/**
	 * @param creditType the creditType to set
	 */
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	/**
	 * @return the salaryCheck
	 */
	public String getSalaryCheck() {
		return salaryCheck;
	}

	/**
	 * @param salaryCheck the salaryCheck to set
	 */
	public void setSalaryCheck(String salaryCheck) {
		this.salaryCheck = salaryCheck;
	}

	/**
	 * @return the approverComments
	 */
	public String getApproverComments() {
		return approverComments;
	}

	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	
	
}
