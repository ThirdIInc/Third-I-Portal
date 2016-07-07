package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AppraisalBellCurve extends BeanBase 
{
	private String sAppId = null;
	private String sAppCode = null;
	private String sAppStartDate = null;
	private String sAppEndDate = null;
	
	private String sNoOfEmployess = null;
	private String sFinalScore = null;
	private String sApprScoreValue = null;
	private String sApprScoreFrom = null;
	private String sApprScoreTo = null;
	
	private ArrayList lstBellCurveData = null;
	private boolean flgData = false;
	
	
	private String divName;
	private String divCode;
	private String branchName;
	private String branchCode;
	private String deptNameUp;
	private String deptCode;
	private String apprEmpCode;
	private String apprEmpName;

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDeptNameUp() {
		return deptNameUp;
	}

	public void setDeptNameUp(String deptNameUp) {
		this.deptNameUp = deptNameUp;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getApprEmpCode() {
		return apprEmpCode;
	}

	public void setApprEmpCode(String apprEmpCode) {
		this.apprEmpCode = apprEmpCode;
	}

	public String getApprEmpName() {
		return apprEmpName;
	}

	public void setApprEmpName(String apprEmpName) {
		this.apprEmpName = apprEmpName;
	}

	public String getSNoOfEmployess() {
		return sNoOfEmployess;
	}

	public void setSNoOfEmployess(String noOfEmployess) {
		sNoOfEmployess = noOfEmployess;
	}

	public String getSAppId() {
		return sAppId;
	}

	public void setSAppId(String appId) {
		sAppId = appId;
	}

	public String getSAppStartDate() {
		return sAppStartDate;
	}

	public void setSAppStartDate(String appStartDate) {
		sAppStartDate = appStartDate;
	}

	public String getSAppEndDate() {
		return sAppEndDate;
	}

	public void setSAppEndDate(String appEndDate) {
		sAppEndDate = appEndDate;
	}

	public String getSAppCode() {
		return sAppCode;
	}

	public void setSAppCode(String appCode) {
		sAppCode = appCode;
	}

	public String getSApprScoreValue() {
		return sApprScoreValue;
	}

	public void setSApprScoreValue(String apprScoreValue) {
		sApprScoreValue = apprScoreValue;
	}

	public ArrayList getLstBellCurveData() {
		return lstBellCurveData;
	}

	public void setLstBellCurveData(ArrayList lstBellCurveData) {
		this.lstBellCurveData = lstBellCurveData;
	}

	public String getSApprScoreFrom() {
		return sApprScoreFrom;
	}

	public void setSApprScoreFrom(String apprScoreFrom) {
		sApprScoreFrom = apprScoreFrom;
	}

	public String getSApprScoreTo() {
		return sApprScoreTo;
	}

	public void setSApprScoreTo(String apprScoreTo) {
		sApprScoreTo = apprScoreTo;
	}

	public String getSFinalScore() {
		return sFinalScore;
	}

	public void setSFinalScore(String finalScore) {
		sFinalScore = finalScore;
	}

	public boolean isFlgData() {
		return flgData;
	}

	public void setFlgData(boolean flgData) {
		this.flgData = flgData;
	}
}
