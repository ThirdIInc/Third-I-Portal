package org.paradyne.bean.payroll.ot;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

/**Created on 29 Feb 2012.
 * @author aa1385
 */

public class OtRegister extends BeanBase {
	/**
	 * fromDate.
	 */
	private String fromDate = "";
	/**
	 * toDate.
	 */
	private String toDate = "";
	/**
	 * managerID.
	 */
	private String managerID = "";
	/**
	 * managerName.
	 */
	private String managerName = "";
	/**
	 * managerToken.
	 */
	private String managerToken = "";
	/**
	 * employeeID.
	 */
	private String employeeID = "";
	/**
	 * employeeName.
	 */
	private String employeeName = "";
	/**
	 * employeeToken.
	 */
	private String employeeToken = "";
	/**
	 * shiftID.
	 */
	private String shiftID = "";
	/**
	 * shiftName.
	 */
	private String shiftName = "";
	/**
	 * otRegisterID.
	 */
	private String otRegisterID = "";
	/**
	 * myPage.
	 */
	private String myPage = "";
	/**
	 * modeLength.
	 */
	private String modeLength = "";
	/**
	 * totalRecords.
	 */
	private String totalRecords = "";
	/**
	 * iteratorlist.
	 */
	private List<OtRegister> iteratorlist;
	/**
	 * ittEmployeeID.
	 */
	private String ittEmployeeID = "";
	/**
	 * ittEmployeeName.
	 */
	private String ittEmployeeName = "";
	/**
	 * ittShiftID.
	 */
	private String ittShiftID = "";
	/**
	 * ittShiftName.
	 */
	private String ittShiftName = "";
	/**
	 * ittFromShiftDate.
	 */
	private String ittFromShiftDate = "";
	/**
	 * ittEmployeeToken.
	 */
	private String ittEmployeeToken = "";
	/**
	 * ittShiftDate.
	 */
	private String ittShiftDate = "";
	/**
	 * rptType.
	 */
	private String rptType = "";
	/**
	 * dateList.
	 */
	private List<OtRegister> dateList;
	/**
	 * dayName.
	 */
	private String dayName = "";
	/**
	 * ittDateList.
	 */
	private List<OtRegister> ittDateList;
	/**
	 * viewOtRegisterDtlFlag.
	 */
	private boolean viewOtRegisterDtlFlag = false;
	/**
	 * ittDate.
	 */
	private String ittDate = "";
	/**
	 * ittShiftIn.
	 */
	private String ittShiftIn = "";
	/**
	 * ittActualIn.
	 */
	private String ittActualIn = "";
	/**
	 * ittShiftOut.
	 */
	private String ittShiftOut = "";
	/**
	 * ittActualOut.
	 */
	private String ittActualOut = "";
	/**
	 * ittSingleOt.
	 */
	private String ittSingleOt = "";
	/**
	 * ittDoubleOt.
	 */
	private String ittDoubleOt = "";
	/**
	 * ittSingleApprOt.
	 */
	private String ittSingleApprOt = "";
	/**
	 * ittDoubleApprOt.
	 */
	private String ittDoubleApprOt = "";
	/**
	 * totalNoEmp.
	 */
	private String totalNoEmp = "";
	/**
	 * totalApprSingleOtHours.
	 */
	private String totalApprSingleOtHours = "";
	/**
	 * totalApprDoubleOtHours.
	 */
	private String totalApprDoubleOtHours = "";
	/**
	 * divisionID.
	 */
	private String divisionID = "";
	/**
	 * divisionName.
	 */
	private String divisionName = "";
	/**
	 * branchCode.
	 */
	private String branchCode = "";
	/**
	 * branchName.
	 */
	private String branchName = "";
	/**
	 * departmentID.
	 */
	private String departmentID = "";
	/**
	 * departmentName.
	 */
	private String departmentName = "";
	/**
	 * paybillId.
	 */
	private String paybillId = "";
	/**
	 * paybillName.
	 */
	private String paybillName = "";
	/**
	 * desgCode.
	 */
	private String desgCode = "";
	/**
	 * desgName.
	 */
	private String desgName = "";
	/**
	 * costCenterId.
	 */
	private String costCenterId = "";
	/**
	 * costCenterName.
	 */
	private String costCenterName = "";
	/**
	 * managerManuallID.
	 */
	private String managerManuallID = "";
	/**
	 * managerManuallToken.
	 */
	private String managerManuallToken = "";
	/**
	 * managerManuallName.
	 */
	private String managerManuallName = "";
	/**
	 * employeeManuallID.
	 */
	private String employeeManuallID = "";
	/**
	 * employeeManuallToken.
	 */
	private String employeeManuallToken = "";
	/**
	 * employeeManuallName.
	 */
	private String employeeManuallName = "";
	/**
	 * fileNameForManuallyCalculatedOtAllowance.
	 */
	private String fileNameForManuallyCalculatedOtAllowance = "";
	/**
	 * fileNameManuallyUploadedOtAllowance.
	 */
	private String fileNameManuallyUploadedOtAllowance = "";
	/**
	 * doubleOTflag.
	 */
	private String doubleOTflag = "";
	/**
	 * systemCalculatedOtCheckBox.
	 */
	private String systemCalculatedOtCheckBox = "";
	/**
	 * manuallyCalculatedOtCheckBox.
	 */
	private String manuallyCalculatedOtCheckBox = "";
	/** dataPath. * */
	private String dataPath = "";
	/** tempFileNameManuallyUploadedOt. * */
	private String tempFileNameManuallyUploadedOt;
	/** note. * */
	private String note = "";
	/** status. * */
	private String status = "Fail";
	/** displayNoteFlag. * */
	private boolean displayNoteFlag;
	/**
	 * hiddenCheckBoxFlag.
	 */
	private String hiddenCheckBoxFlag = "";
	/**
	 * report.
	 */
	private String report = "";
	/**
	 * configOTflag.
	 */
	private String configOTflag = "";
	private String mngDtlLength="";
	/**
	 * @return the mngDtlLength
	 */
	public String getMngDtlLength() {
		return mngDtlLength;
	}
	/**
	 * @param mngDtlLength the mngDtlLength to set
	 */
	public void setMngDtlLength(String mngDtlLength) {
		this.mngDtlLength = mngDtlLength;
	}
	/**
	 * @return the fromDate
	 */
	public final String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public final void setFromDate(final String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public final String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public final void setToDate(final String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the managerID
	 */
	public final String getManagerID() {
		return managerID;
	}
	/**
	 * @param managerID the managerID to set
	 */
	public final void setManagerID(final String managerID) {
		this.managerID = managerID;
	}
	/**
	 * @return the managerName
	 */
	public final String getManagerName() {
		return managerName;
	}
	/**
	 * @param managerName the managerName to set
	 */
	public final void setManagerName(final String managerName) {
		this.managerName = managerName;
	}
	/**
	 * @return the managerToken
	 */
	public final String getManagerToken() {
		return managerToken;
	}
	/**
	 * @param managerToken the managerToken to set
	 */
	public final void setManagerToken(final String managerToken) {
		this.managerToken = managerToken;
	}
	/**
	 * @return the employeeID
	 */
	public final String getEmployeeID() {
		return employeeID;
	}
	/**
	 * @param employeeID the employeeID to set
	 */
	public final void setEmployeeID(final String employeeID) {
		this.employeeID = employeeID;
	}
	/**
	 * @return the employeeName
	 */
	public final String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public final void setEmployeeName(final String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the employeeToken
	 */
	public final String getEmployeeToken() {
		return employeeToken;
	}
	/**
	 * @param employeeToken the employeeToken to set
	 */
	public final void setEmployeeToken(final String employeeToken) {
		this.employeeToken = employeeToken;
	}
	/**
	 * @return the shiftID
	 */
	public final String getShiftID() {
		return shiftID;
	}
	/**
	 * @param shiftID the shiftID to set
	 */
	public final void setShiftID(final String shiftID) {
		this.shiftID = shiftID;
	}
	/**
	 * @return the shiftName
	 */
	public final String getShiftName() {
		return shiftName;
	}
	/**
	 * @param shiftName the shiftName to set
	 */
	public final void setShiftName(final String shiftName) {
		this.shiftName = shiftName;
	}
	/**
	 * @return the otRegisterID
	 */
	public final String getOtRegisterID() {
		return otRegisterID;
	}
	/**
	 * @param otRegisterID the otRegisterID to set
	 */
	public final void setOtRegisterID(final String otRegisterID) {
		this.otRegisterID = otRegisterID;
	}
	/**
	 * @return the myPage
	 */
	public final String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public final void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the modeLength
	 */
	public final String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public final void setModeLength(final String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalRecords
	 */
	public final String getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public final void setTotalRecords(final String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the ittEmployeeID
	 */
	public final String getIttEmployeeID() {
		return ittEmployeeID;
	}
	/**
	 * @param ittEmployeeID the ittEmployeeID to set
	 */
	public final void setIttEmployeeID(final String ittEmployeeID) {
		this.ittEmployeeID = ittEmployeeID;
	}
	/**
	 * @return the ittEmployeeName
	 */
	public final String getIttEmployeeName() {
		return ittEmployeeName;
	}
	/**
	 * @param ittEmployeeName the ittEmployeeName to set
	 */
	public final void setIttEmployeeName(final String ittEmployeeName) {
		this.ittEmployeeName = ittEmployeeName;
	}
	/**
	 * @return the ittShiftID
	 */
	public final String getIttShiftID() {
		return ittShiftID;
	}
	/**
	 * @param ittShiftID the ittShiftID to set
	 */
	public final void setIttShiftID(final String ittShiftID) {
		this.ittShiftID = ittShiftID;
	}
	/**
	 * @return the ittShiftName
	 */
	public final String getIttShiftName() {
		return ittShiftName;
	}
	/**
	 * @param ittShiftName the ittShiftName to set
	 */
	public final void setIttShiftName(final String ittShiftName) {
		this.ittShiftName = ittShiftName;
	}
	/**
	 * @return the ittFromShiftDate
	 */
	public final String getIttFromShiftDate() {
		return ittFromShiftDate;
	}
	/**
	 * @param ittFromShiftDate the ittFromShiftDate to set
	 */
	public final void setIttFromShiftDate(final String ittFromShiftDate) {
		this.ittFromShiftDate = ittFromShiftDate;
	}
	/**
	 * @return the ittEmployeeToken
	 */
	public final String getIttEmployeeToken() {
		return ittEmployeeToken;
	}
	/**
	 * @param ittEmployeeToken the ittEmployeeToken to set
	 */
	public final void setIttEmployeeToken(final String ittEmployeeToken) {
		this.ittEmployeeToken = ittEmployeeToken;
	}
	/**
	 * @return the ittShiftDate
	 */
	public final String getIttShiftDate() {
		return ittShiftDate;
	}
	/**
	 * @param ittShiftDate the ittShiftDate to set
	 */
	public final void setIttShiftDate(final String ittShiftDate) {
		this.ittShiftDate = ittShiftDate;
	}
	/**
	 * @return the rptType
	 */
	public final String getRptType() {
		return rptType;
	}
	/**
	 * @param rptType the rptType to set
	 */
	public final void setRptType(final String rptType) {
		this.rptType = rptType;
	}
	/**
	 * @return the dayName
	 */
	public final String getDayName() {
		return dayName;
	}
	/**
	 * @param dayName the dayName to set
	 */
	public final void setDayName(final String dayName) {
		this.dayName = dayName;
	}
	/**
	 * @return the viewOtRegisterDtlFlag
	 */
	public final boolean isViewOtRegisterDtlFlag() {
		return viewOtRegisterDtlFlag;
	}
	/**
	 * @param viewOtRegisterDtlFlag the viewOtRegisterDtlFlag to set
	 */
	public final void setViewOtRegisterDtlFlag(final boolean viewOtRegisterDtlFlag) {
		this.viewOtRegisterDtlFlag = viewOtRegisterDtlFlag;
	}
	/**
	 * @return the ittDate
	 */
	public final String getIttDate() {
		return ittDate;
	}
	/**
	 * @param ittDate the ittDate to set
	 */
	public final void setIttDate(final String ittDate) {
		this.ittDate = ittDate;
	}
	/**
	 * @return the ittShiftIn
	 */
	public final String getIttShiftIn() {
		return ittShiftIn;
	}
	/**
	 * @param ittShiftIn the ittShiftIn to set
	 */
	public final void setIttShiftIn(final String ittShiftIn) {
		this.ittShiftIn = ittShiftIn;
	}
	/**
	 * @return the ittActualIn
	 */
	public final String getIttActualIn() {
		return ittActualIn;
	}
	/**
	 * @param ittActualIn the ittActualIn to set
	 */
	public final void setIttActualIn(final String ittActualIn) {
		this.ittActualIn = ittActualIn;
	}
	/**
	 * @return the ittShiftOut
	 */
	public final String getIttShiftOut() {
		return ittShiftOut;
	}
	/**
	 * @param ittShiftOut the ittShiftOut to set
	 */
	public final void setIttShiftOut(final String ittShiftOut) {
		this.ittShiftOut = ittShiftOut;
	}
	/**
	 * @return the ittActualOut
	 */
	public final String getIttActualOut() {
		return ittActualOut;
	}
	/**
	 * @param ittActualOut the ittActualOut to set
	 */
	public final void setIttActualOut(final String ittActualOut) {
		this.ittActualOut = ittActualOut;
	}
	/**
	 * @return the ittSingleOt
	 */
	public final String getIttSingleOt() {
		return ittSingleOt;
	}
	/**
	 * @param ittSingleOt the ittSingleOt to set
	 */
	public final void setIttSingleOt(final String ittSingleOt) {
		this.ittSingleOt = ittSingleOt;
	}
	/**
	 * @return the ittDoubleOt
	 */
	public final String getIttDoubleOt() {
		return ittDoubleOt;
	}
	/**
	 * @param ittDoubleOt the ittDoubleOt to set
	 */
	public final void setIttDoubleOt(final String ittDoubleOt) {
		this.ittDoubleOt = ittDoubleOt;
	}
	/**
	 * @return the ittSingleApprOt
	 */
	public final String getIttSingleApprOt() {
		return ittSingleApprOt;
	}
	/**
	 * @param ittSingleApprOt the ittSingleApprOt to set
	 */
	public final void setIttSingleApprOt(final String ittSingleApprOt) {
		this.ittSingleApprOt = ittSingleApprOt;
	}
	/**
	 * @return the ittDoubleApprOt
	 */
	public final String getIttDoubleApprOt() {
		return ittDoubleApprOt;
	}
	/**
	 * @param ittDoubleApprOt the ittDoubleApprOt to set
	 */
	public final void setIttDoubleApprOt(final String ittDoubleApprOt) {
		this.ittDoubleApprOt = ittDoubleApprOt;
	}
	/**
	 * @return the totalNoEmp
	 */
	public final String getTotalNoEmp() {
		return totalNoEmp;
	}
	/**
	 * @param totalNoEmp the totalNoEmp to set
	 */
	public final void setTotalNoEmp(final String totalNoEmp) {
		this.totalNoEmp = totalNoEmp;
	}
	/**
	 * @return the totalApprSingleOtHours
	 */
	public final String getTotalApprSingleOtHours() {
		return totalApprSingleOtHours;
	}
	/**
	 * @param totalApprSingleOtHours the totalApprSingleOtHours to set
	 */
	public final void setTotalApprSingleOtHours(final String totalApprSingleOtHours) {
		this.totalApprSingleOtHours = totalApprSingleOtHours;
	}
	/**
	 * @return the totalApprDoubleOtHours
	 */
	public final String getTotalApprDoubleOtHours() {
		return totalApprDoubleOtHours;
	}
	/**
	 * @param totalApprDoubleOtHours the totalApprDoubleOtHours to set
	 */
	public final void setTotalApprDoubleOtHours(final String totalApprDoubleOtHours) {
		this.totalApprDoubleOtHours = totalApprDoubleOtHours;
	}
	/**
	 * @return the divisionID
	 */
	public final String getDivisionID() {
		return divisionID;
	}
	/**
	 * @param divisionID the divisionID to set
	 */
	public final void setDivisionID(final String divisionID) {
		this.divisionID = divisionID;
	}
	/**
	 * @return the divisionName
	 */
	public final String getDivisionName() {
		return divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	public final void setDivisionName(final String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return the branchCode
	 */
	public final String getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode the branchCode to set
	 */
	public final void setBranchCode(final String branchCode) {
		this.branchCode = branchCode;
	}
	/**
	 * @return the branchName
	 */
	public final String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public final void setBranchName(final String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the departmentID
	 */
	public final String getDepartmentID() {
		return departmentID;
	}
	/**
	 * @param departmentID the departmentID to set
	 */
	public final void setDepartmentID(final String departmentID) {
		this.departmentID = departmentID;
	}
	/**
	 * @return the departmentName
	 */
	public final String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public final void setDepartmentName(final String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the paybillId
	 */
	public final String getPaybillId() {
		return paybillId;
	}
	/**
	 * @param paybillId the paybillId to set
	 */
	public final void setPaybillId(final String paybillId) {
		this.paybillId = paybillId;
	}
	/**
	 * @return the paybillName
	 */
	public final String getPaybillName() {
		return paybillName;
	}
	/**
	 * @param paybillName the paybillName to set
	 */
	public final void setPaybillName(final String paybillName) {
		this.paybillName = paybillName;
	}
	/**
	 * @return the desgCode
	 */
	public final String getDesgCode() {
		return desgCode;
	}
	/**
	 * @param desgCode the desgCode to set
	 */
	public final void setDesgCode(final String desgCode) {
		this.desgCode = desgCode;
	}
	/**
	 * @return the desgName
	 */
	public final String getDesgName() {
		return desgName;
	}
	/**
	 * @param desgName the desgName to set
	 */
	public final void setDesgName(final String desgName) {
		this.desgName = desgName;
	}
	/**
	 * @return the managerManuallID
	 */
	public final String getManagerManuallID() {
		return managerManuallID;
	}
	/**
	 * @param managerManuallID the managerManuallID to set
	 */
	public final void setManagerManuallID(final String managerManuallID) {
		this.managerManuallID = managerManuallID;
	}
	/**
	 * @return the managerManuallToken
	 */
	public final String getManagerManuallToken() {
		return managerManuallToken;
	}
	/**
	 * @param managerManuallToken the managerManuallToken to set
	 */
	public final void setManagerManuallToken(final String managerManuallToken) {
		this.managerManuallToken = managerManuallToken;
	}
	/**
	 * @return the managerManuallName
	 */
	public final String getManagerManuallName() {
		return managerManuallName;
	}
	/**
	 * @param managerManuallName the managerManuallName to set
	 */
	public final void setManagerManuallName(final String managerManuallName) {
		this.managerManuallName = managerManuallName;
	}
	/**
	 * @return the employeeManuallID
	 */
	public final String getEmployeeManuallID() {
		return employeeManuallID;
	}
	/**
	 * @param employeeManuallID the employeeManuallID to set
	 */
	public final void setEmployeeManuallID(final String employeeManuallID) {
		this.employeeManuallID = employeeManuallID;
	}
	/**
	 * @return the employeeManuallToken
	 */
	public final String getEmployeeManuallToken() {
		return employeeManuallToken;
	}
	/**
	 * @param employeeManuallToken the employeeManuallToken to set
	 */
	public final void setEmployeeManuallToken(final String employeeManuallToken) {
		this.employeeManuallToken = employeeManuallToken;
	}
	/**
	 * @return the employeeManuallName
	 */
	public final String getEmployeeManuallName() {
		return employeeManuallName;
	}
	/**
	 * @param employeeManuallName the employeeManuallName to set
	 */
	public final void setEmployeeManuallName(final String employeeManuallName) {
		this.employeeManuallName = employeeManuallName;
	}
	/**
	 * @return the fileNameForManuallyCalculatedOtAllowance
	 */
	public final String getFileNameForManuallyCalculatedOtAllowance() {
		return fileNameForManuallyCalculatedOtAllowance;
	}
	/**
	 * @param fileNameForManuallyCalculatedOtAllowance the fileNameForManuallyCalculatedOtAllowance to set
	 */
	public final void setFileNameForManuallyCalculatedOtAllowance(
			final String fileNameForManuallyCalculatedOtAllowance) {
		this.fileNameForManuallyCalculatedOtAllowance = fileNameForManuallyCalculatedOtAllowance;
	}
	/**
	 * @return the fileNameManuallyUploadedOtAllowance
	 */
	public final String getFileNameManuallyUploadedOtAllowance() {
		return fileNameManuallyUploadedOtAllowance;
	}
	/**
	 * @param fileNameManuallyUploadedOtAllowance the fileNameManuallyUploadedOtAllowance to set
	 */
	public final void setFileNameManuallyUploadedOtAllowance(
			final String fileNameManuallyUploadedOtAllowance) {
		this.fileNameManuallyUploadedOtAllowance = fileNameManuallyUploadedOtAllowance;
	}
	/**
	 * @return the doubleOTflag
	 */
	public final String getDoubleOTflag() {
		return doubleOTflag;
	}
	/**
	 * @param doubleOTflag the doubleOTflag to set
	 */
	public final void setDoubleOTflag(final String doubleOTflag) {
		this.doubleOTflag = doubleOTflag;
	}
	/**
	 * @return the systemCalculatedOtCheckBox
	 */
	public final String getSystemCalculatedOtCheckBox() {
		return systemCalculatedOtCheckBox;
	}
	/**
	 * @param systemCalculatedOtCheckBox the systemCalculatedOtCheckBox to set
	 */
	public final void setSystemCalculatedOtCheckBox(final String systemCalculatedOtCheckBox) {
		this.systemCalculatedOtCheckBox = systemCalculatedOtCheckBox;
	}
	/**
	 * @return the manuallyCalculatedOtCheckBox
	 */
	public final String getManuallyCalculatedOtCheckBox() {
		return manuallyCalculatedOtCheckBox;
	}
	/**
	 * @param manuallyCalculatedOtCheckBox the manuallyCalculatedOtCheckBox to set
	 */
	public final void setManuallyCalculatedOtCheckBox(final String manuallyCalculatedOtCheckBox) {
		this.manuallyCalculatedOtCheckBox = manuallyCalculatedOtCheckBox;
	}
	/**
	 * @return the dataPath
	 */
	public final String getDataPath() {
		return dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public final void setDataPath(final String dataPath) {
		this.dataPath = dataPath;
	}
	/**
	 * @return the tempFileNameManuallyUploadedOt
	 */
	public final String getTempFileNameManuallyUploadedOt() {
		return tempFileNameManuallyUploadedOt;
	}
	/**
	 * @param tempFileNameManuallyUploadedOt the tempFileNameManuallyUploadedOt to set
	 */
	public final void setTempFileNameManuallyUploadedOt(
			final String tempFileNameManuallyUploadedOt) {
		this.tempFileNameManuallyUploadedOt = tempFileNameManuallyUploadedOt;
	}
	/**
	 * @return the note
	 */
	public final String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public final void setNote(final String note) {
		this.note = note;
	}
	/**
	 * @return the status
	 */
	public final String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public final void setStatus(final String status) {
		this.status = status;
	}
	/**
	 * @return the displayNoteFlag
	 */
	public final boolean isDisplayNoteFlag() {
		return displayNoteFlag;
	}
	/**
	 * @param displayNoteFlag the displayNoteFlag to set
	 */
	public final void setDisplayNoteFlag(final boolean displayNoteFlag) {
		this.displayNoteFlag = displayNoteFlag;
	}
	/**
	 * @return the hiddenCheckBoxFlag
	 */
	public final String getHiddenCheckBoxFlag() {
		return hiddenCheckBoxFlag;
	}
	/**
	 * @param hiddenCheckBoxFlag the hiddenCheckBoxFlag to set
	 */
	public final void setHiddenCheckBoxFlag(final String hiddenCheckBoxFlag) {
		this.hiddenCheckBoxFlag = hiddenCheckBoxFlag;
	}
	/**
	 * @return the report
	 */
	public final String getReport() {
		return report;
	}
	/**
	 * @param report the report to set
	 */
	public final void setReport(final String report) {
		this.report = report;
	}
	/**
	 * @return the configOTflag
	 */
	public final String getConfigOTflag() {
		return configOTflag;
	}
	/**
	 * @param configOTflag the configOTflag to set
	 */
	public final void setConfigOTflag(final String configOTflag) {
		this.configOTflag = configOTflag;
	}
	/**
	 * @return the costCenterId
	 */
	public String getCostCenterId() {
		return costCenterId;
	}
	/**
	 * @param costCenterId the costCenterId to set
	 */
	public void setCostCenterId(String costCenterId) {
		this.costCenterId = costCenterId;
	}
	/**
	 * @return the costCenterName
	 */
	public String getCostCenterName() {
		return costCenterName;
	}
	/**
	 * @param costCenterName the costCenterName to set
	 */
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	/**
	 * @return the iteratorlist
	 */
	public List<OtRegister> getIteratorlist() {
		return iteratorlist;
	}
	/**
	 * @param iteratorlist the iteratorlist to set
	 */
	public void setIteratorlist(List<OtRegister> iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	/**
	 * @return the dateList
	 */
	public List<OtRegister> getDateList() {
		return dateList;
	}
	/**
	 * @param dateList the dateList to set
	 */
	public void setDateList(List<OtRegister> dateList) {
		this.dateList = dateList;
	}
	/**
	 * @return the ittDateList
	 */
	public List<OtRegister> getIttDateList() {
		return ittDateList;
	}
	/**
	 * @param ittDateList the ittDateList to set
	 */
	public void setIttDateList(List<OtRegister> ittDateList) {
		this.ittDateList = ittDateList;
	}
	
}
