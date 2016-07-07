package org.paradyne.bean.payroll.ot;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

/**Created on 8 Mar 2012.
 * @author aa1385
 */
public class OtMISReport extends BeanBase {
	
	/** * divisionID */
	private String divisionID = "";
	/**	 * divisionName	 */
	private String divisionName = "";
	/**	 * myPage	 */
	private String myPage = null;
	/**	 * modeLength	 */
	private String modeLength="false";
	/**	 * totalRecords	 */
	private String totalRecords="";
	  /**	 * iteratorlist	 */
	ArrayList iteratorlist;
	private String month = "";
	private String year = "";
	private String centerId = "";
	private String centerName = "";
	private String deptCode = "";
	private String deptName = "";
	private String desgCode = "";
	private String desgName = "";
	private String paybillCode = "";
	private String paybillName = "";
	private String costCenterCode = "";
	private String costCenterName = "";
	private String shiftCode = "";
	private String shiftName = "";
	
	
	private String otCalculationId = "";
	private String totalEmpCount = "";
	private String totalOtAmount = "";
	
	private String status = "";
	private String report = "";
	
	private String checkBrn = "";
	private String checkDept = "";
	private String checkEmpGrade = "";
	private String checkAccount = "";
	private String checkBank = "";
	
	private String managerID ="";
	private String managerName ="";
	private String doubleOTflag = "";
	
	public String getDoubleOTflag() {
		return doubleOTflag;
	}
	public void setDoubleOTflag(String doubleOTflag) {
		this.doubleOTflag = doubleOTflag;
	}
	public String getCheckBrn() {
		return checkBrn;
	}
	public void setCheckBrn(String checkBrn) {
		this.checkBrn = checkBrn;
	}
	public String getCheckDept() {
		return checkDept;
	}
	public void setCheckDept(String checkDept) {
		this.checkDept = checkDept;
	}
	public String getCheckEmpGrade() {
		return checkEmpGrade;
	}
	public void setCheckEmpGrade(String checkEmpGrade) {
		this.checkEmpGrade = checkEmpGrade;
	}
	public String getCheckAccount() {
		return checkAccount;
	}
	public void setCheckAccount(String checkAccount) {
		this.checkAccount = checkAccount;
	}
	public String getCheckBank() {
		return checkBank;
	}
	public void setCheckBank(String checkBank) {
		this.checkBank = checkBank;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
	public String getTotalEmpCount() {
		return totalEmpCount;
	}
	public void setTotalEmpCount(String totalEmpCount) {
		this.totalEmpCount = totalEmpCount;
	}
	public String getTotalOtAmount() {
		return totalOtAmount;
	}
	public void setTotalOtAmount(String totalOtAmount) {
		this.totalOtAmount = totalOtAmount;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	public String getPaybillCode() {
		return paybillCode;
	}
	public void setPaybillCode(String paybillCode) {
		this.paybillCode = paybillCode;
	}
	public String getPaybillName() {
		return paybillName;
	}
	public void setPaybillName(String paybillName) {
		this.paybillName = paybillName;
	}
	public String getCostCenterCode() {
		return costCenterCode;
	}
	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	
	public String getOtCalculationId() {
		return otCalculationId;
	}
	public void setOtCalculationId(String otCalculationId) {
		this.otCalculationId = otCalculationId;
	}
	/**getMyPage
	 * @return myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/** setMyPage.
	 * @param myPage
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/** getModeLength.
	 * @return modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/** setModeLength.
	 * @param modeLength
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/** getTotalRecords
	 * @return totalRecords.
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	/** setTotalRecords.
	 * @param totalRecords
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/** getIteratorlist.
	 * @return iteratorlist
	 */
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	
	/** getDivisionID.
	 * @return divisionID
	 */
	public String getDivisionID() {
		return divisionID;
	}
	/**setDivisionID.
	 * @param divisionID
	 */
	public void setDivisionID(String divisionID) {
		this.divisionID = divisionID;
	}
	/** getDivisionName.
	 * @return divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/** setDivisionName.
	 * @param divisionName
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	
	
	

}
