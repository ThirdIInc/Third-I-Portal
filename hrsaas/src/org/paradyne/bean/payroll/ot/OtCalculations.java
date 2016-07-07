package org.paradyne.bean.payroll.ot;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

/**Created on 5 Mar 2012.
 * @author aa1385
 */
public class OtCalculations extends BeanBase {
	/** * divisionID. */
	private String divisionID = "";
	/**	 * divisionName.	 */
	private String divisionName = "";
	/**	 * myPage.	 */
	private String myPage = null;
	/**	 * modeLength.	 */
	private String modeLength="false";
	/**	 * totalRecords.	 */
	private String totalRecords="";
	  /**	 * iteratorlist.	 */
	private List<Object> iteratorlist;
	/**
	 * month.
	 */
	private String month = "";
	/**
	 * year.
	 */
	private String year = "";
	/**
	 * centerId.
	 */
	private String centerId = "";
	/**
	 * centerName.
	 */
	private String centerName = "";
	/**
	 * deptCode.
	 */
	private String deptCode = "";
	/**
	 * deptName.
	 */
	private String deptName = "";
	/**
	 * desgCode.
	 */
	private String desgCode = "";
	/**
	 * desgName.
	 */
	private String desgName = "";
	/**
	 * paybillCode.
	 */
	private String paybillCode = "";
	/**
	 * paybillName.
	 */
	private String paybillName = "";
	/**
	 * costCenterCode.
	 */
	private String costCenterCode = "";
	/**
	 * costCenterName.
	 */
	private String costCenterName = "";
	/**
	 * shiftCode.
	 */
	private String shiftCode = "";
	/**
	 * shiftName.
	 */
	private String shiftName = "";
	/**
	 * paidMonth.
	 */
	private String paidMonth = "";
	/**
	 * paidYear.
	 */
	private String paidYear = "";
	/**
	 * creditCode.
	 */
	private String creditCode = "";
	/**
	 * creditName.
	 */
	private String creditName = "";
	/**
	 * payInSalaryFlag.
	 */
	private String payInSalaryFlag = "";
	/**
	 * deductInconeTaxFlag.
	 */
	private String deductInconeTaxFlag = "";
	/**
	 * otCalculationId.
	 */
	private String otCalculationId = "";
	/**
	 * totalEmpCount.
	 */
	private String totalEmpCount = "";
	/**
	 * totalOtAmount.
	 */
	private String totalOtAmount = "";
	/**
	 * hiddenOtCalId.
	 */
	private String hiddenOtCalId = "";
	/**
	 * processDate.
	 */
	private String processDate = "";
	/**
	 * paidInSalMonth.
	 */
	private String paidInSalMonth = "";
	/**
	 * status.
	 */
	private String status = "";
	/**
	 * report.
	 */
	private String report = "";
	/** processedRecordFlag. * */
	private boolean processedRecordFlag;
	/**
	 * doubleOTflag.
	 */
	private String doubleOTflag = "";
	/**
	 * configOTflag.
	 */
	private String configOTflag = "";
	/**
	 * backAction.
	 */
	private String backAction = "";
	// for view calculation page
	/**
	 * ittEmpID.
	 */
	private String ittEmpID = "";
	/**
	 * ittEmpToken.
	 */
	private String ittEmpToken = "";
	/**
	 * ittEmployeeName.
	 */
	private String ittEmployeeName ="";
	/**
	 * ittSingleOtHours.
	 */
	private String ittSingleOtHours = "";
	/**
	 * ittDoubleOtHours.
	 */
	private String ittDoubleOtHours = "";
	/**
	 * ittTotalOtHours.
	 */
	private String ittTotalOtHours = "";
	/**
	 * ittSingleOtAmount.
	 */
	private String ittSingleOtAmount ="";
	/**
	 * ittDoubleOtAmount.
	 */
	private String ittDoubleOtAmount = "";
	/**
	 * ittTotalOt.
	 */
	private String ittTotalOt = "";
	/**
	 * ittTds.
	 */
	private String ittTds = "";
	/**
	 * ittNetOtAmount.
	 */
	private String ittNetOtAmount = "";
	/**
	 * ittOTCalID.
	 */
	private String ittOTCalID = "";
	/**
	 * ittMonth.
	 */
	private String ittMonth = "";
	/**
	 * ittYear.
	 */
	private String ittYear = "";
	/**
	 * calOtEmpIteratorList.
	 */
	ArrayList calOtEmpIteratorList = null;
	/**
	 * calOtEmpListLength.
	 */
	private boolean calOtEmpListLength = false;
	private String paySalFlag = "";
	private String deductTaxFlag = "";
	private String totalTdsAmt = "";
	private String netOtAmt = "";
	private String totalOtAmt = "";
	private String otPeriod = "";
	/**
	 * @return the otPeriod
	 */
	public String getOtPeriod() {
		return otPeriod;
	}
	/**
	 * @param otPeriod the otPeriod to set
	 */
	public void setOtPeriod(String otPeriod) {
		this.otPeriod = otPeriod;
	}
	/**
	 * @return the totalTdsAmt
	 */
	public String getTotalTdsAmt() {
		return totalTdsAmt;
	}
	/**
	 * @param totalTdsAmt the totalTdsAmt to set
	 */
	public void setTotalTdsAmt(String totalTdsAmt) {
		this.totalTdsAmt = totalTdsAmt;
	}
	/**
	 * @return the netOtAmt
	 */
	public String getNetOtAmt() {
		return netOtAmt;
	}
	/**
	 * @param netOtAmt the netOtAmt to set
	 */
	public void setNetOtAmt(String netOtAmt) {
		this.netOtAmt = netOtAmt;
	}
	/**
	 * @return the totalOtAmt
	 */
	public String getTotalOtAmt() {
		return totalOtAmt;
	}
	/**
	 * @param totalOtAmt the totalOtAmt to set
	 */
	public void setTotalOtAmt(String totalOtAmt) {
		this.totalOtAmt = totalOtAmt;
	}
	/**
	 * @return the paySalFlag
	 */
	public String getPaySalFlag() {
		return paySalFlag;
	}
	/**
	 * @param paySalFlag the paySalFlag to set
	 */
	public void setPaySalFlag(String paySalFlag) {
		this.paySalFlag = paySalFlag;
	}
	/**
	 * @return the deductTaxFlag
	 */
	public String getDeductTaxFlag() {
		return deductTaxFlag;
	}
	/**
	 * @param deductTaxFlag the deductTaxFlag to set
	 */
	public void setDeductTaxFlag(String deductTaxFlag) {
		this.deductTaxFlag = deductTaxFlag;
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
	 * @return the iteratorlist
	 */
	public final List<Object> getIteratorlist() {
		return iteratorlist;
	}
	/**
	 * @param iteratorlist the iteratorlist to set
	 */
	public final void setIteratorlist(final List<Object> iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	/**
	 * @return the month
	 */
	public final String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public final void setMonth(final String month) {
		this.month = month;
	}
	/**
	 * @return the year
	 */
	public final String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public final void setYear(final String year) {
		this.year = year;
	}
	/**
	 * @return the centerId
	 */
	public final String getCenterId() {
		return centerId;
	}
	/**
	 * @param centerId the centerId to set
	 */
	public final void setCenterId(final String centerId) {
		this.centerId = centerId;
	}
	/**
	 * @return the centerName
	 */
	public final String getCenterName() {
		return centerName;
	}
	/**
	 * @param centerName the centerName to set
	 */
	public final void setCenterName(final String centerName) {
		this.centerName = centerName;
	}
	/**
	 * @return the deptCode
	 */
	public final String getDeptCode() {
		return deptCode;
	}
	/**
	 * @param deptCode the deptCode to set
	 */
	public final void setDeptCode(final String deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * @return the deptName
	 */
	public final String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public final void setDeptName(final String deptName) {
		this.deptName = deptName;
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
	 * @return the paybillCode
	 */
	public final String getPaybillCode() {
		return paybillCode;
	}
	/**
	 * @param paybillCode the paybillCode to set
	 */
	public final void setPaybillCode(final String paybillCode) {
		this.paybillCode = paybillCode;
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
	 * @return the costCenterCode
	 */
	public final String getCostCenterCode() {
		return costCenterCode;
	}
	/**
	 * @param costCenterCode the costCenterCode to set
	 */
	public final void setCostCenterCode(final String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}
	/**
	 * @return the costCenterName
	 */
	public final String getCostCenterName() {
		return costCenterName;
	}
	/**
	 * @param costCenterName the costCenterName to set
	 */
	public final void setCostCenterName(final String costCenterName) {
		this.costCenterName = costCenterName;
	}
	/**
	 * @return the shiftCode
	 */
	public final String getShiftCode() {
		return shiftCode;
	}
	/**
	 * @param shiftCode the shiftCode to set
	 */
	public final void setShiftCode(final String shiftCode) {
		this.shiftCode = shiftCode;
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
	 * @return the paidMonth
	 */
	public final String getPaidMonth() {
		return paidMonth;
	}
	/**
	 * @param paidMonth the paidMonth to set
	 */
	public final void setPaidMonth(final String paidMonth) {
		this.paidMonth = paidMonth;
	}
	/**
	 * @return the paidYear
	 */
	public final String getPaidYear() {
		return paidYear;
	}
	/**
	 * @param paidYear the paidYear to set
	 */
	public final void setPaidYear(final String paidYear) {
		this.paidYear = paidYear;
	}
	/**
	 * @return the creditCode
	 */
	public final String getCreditCode() {
		return creditCode;
	}
	/**
	 * @param creditCode the creditCode to set
	 */
	public final void setCreditCode(final String creditCode) {
		this.creditCode = creditCode;
	}
	/**
	 * @return the creditName
	 */
	public final String getCreditName() {
		return creditName;
	}
	/**
	 * @param creditName the creditName to set
	 */
	public final void setCreditName(final String creditName) {
		this.creditName = creditName;
	}
	/**
	 * @return the payInSalaryFlag
	 */
	public final String getPayInSalaryFlag() {
		return payInSalaryFlag;
	}
	/**
	 * @param payInSalaryFlag the payInSalaryFlag to set
	 */
	public final void setPayInSalaryFlag(final String payInSalaryFlag) {
		this.payInSalaryFlag = payInSalaryFlag;
	}
	/**
	 * @return the deductInconeTaxFlag
	 */
	public final String getDeductInconeTaxFlag() {
		return deductInconeTaxFlag;
	}
	/**
	 * @param deductInconeTaxFlag the deductInconeTaxFlag to set
	 */
	public final void setDeductInconeTaxFlag(final String deductInconeTaxFlag) {
		this.deductInconeTaxFlag = deductInconeTaxFlag;
	}
	/**
	 * @return the otCalculationId
	 */
	public final String getOtCalculationId() {
		return otCalculationId;
	}
	/**
	 * @param otCalculationId the otCalculationId to set
	 */
	public final void setOtCalculationId(final String otCalculationId) {
		this.otCalculationId = otCalculationId;
	}
	/**
	 * @return the totalEmpCount
	 */
	public final String getTotalEmpCount() {
		return totalEmpCount;
	}
	/**
	 * @param totalEmpCount the totalEmpCount to set
	 */
	public final void setTotalEmpCount(final String totalEmpCount) {
		this.totalEmpCount = totalEmpCount;
	}
	/**
	 * @return the totalOtAmount
	 */
	public final String getTotalOtAmount() {
		return totalOtAmount;
	}
	/**
	 * @param totalOtAmount the totalOtAmount to set
	 */
	public final void setTotalOtAmount(final String totalOtAmount) {
		this.totalOtAmount = totalOtAmount;
	}
	/**
	 * @return the hiddenOtCalId
	 */
	public final String getHiddenOtCalId() {
		return hiddenOtCalId;
	}
	/**
	 * @param hiddenOtCalId the hiddenOtCalId to set
	 */
	public final void setHiddenOtCalId(final String hiddenOtCalId) {
		this.hiddenOtCalId = hiddenOtCalId;
	}
	/**
	 * @return the processDate
	 */
	public final String getProcessDate() {
		return processDate;
	}
	/**
	 * @param processDate the processDate to set
	 */
	public final void setProcessDate(final String processDate) {
		this.processDate = processDate;
	}
	/**
	 * @return the paidInSalMonth
	 */
	public final String getPaidInSalMonth() {
		return paidInSalMonth;
	}
	/**
	 * @param paidInSalMonth the paidInSalMonth to set
	 */
	public final void setPaidInSalMonth(final String paidInSalMonth) {
		this.paidInSalMonth = paidInSalMonth;
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
	 * @return the processedRecordFlag
	 */
	public final boolean isProcessedRecordFlag() {
		return processedRecordFlag;
	}
	/**
	 * @param processedRecordFlag the processedRecordFlag to set
	 */
	public final void setProcessedRecordFlag(final boolean processedRecordFlag) {
		this.processedRecordFlag = processedRecordFlag;
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
	 * @return the backAction
	 */
	public final String getBackAction() {
		return backAction;
	}
	/**
	 * @param backAction the backAction to set
	 */
	public final void setBackAction(final String backAction) {
		this.backAction = backAction;
	}
	/**
	 * @return the ittEmpID
	 */
	public final String getIttEmpID() {
		return ittEmpID;
	}
	/**
	 * @param ittEmpID the ittEmpID to set
	 */
	public final void setIttEmpID(final String ittEmpID) {
		this.ittEmpID = ittEmpID;
	}
	/**
	 * @return the ittEmpToken
	 */
	public final String getIttEmpToken() {
		return ittEmpToken;
	}
	/**
	 * @param ittEmpToken the ittEmpToken to set
	 */
	public final void setIttEmpToken(final String ittEmpToken) {
		this.ittEmpToken = ittEmpToken;
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
	 * @return the ittSingleOtHours
	 */
	public final String getIttSingleOtHours() {
		return ittSingleOtHours;
	}
	/**
	 * @param ittSingleOtHours the ittSingleOtHours to set
	 */
	public final void setIttSingleOtHours(final String ittSingleOtHours) {
		this.ittSingleOtHours = ittSingleOtHours;
	}
	/**
	 * @return the ittDoubleOtHours
	 */
	public final String getIttDoubleOtHours() {
		return ittDoubleOtHours;
	}
	/**
	 * @param ittDoubleOtHours the ittDoubleOtHours to set
	 */
	public final void setIttDoubleOtHours(final String ittDoubleOtHours) {
		this.ittDoubleOtHours = ittDoubleOtHours;
	}
	/**
	 * @return the ittTotalOtHours
	 */
	public final String getIttTotalOtHours() {
		return ittTotalOtHours;
	}
	/**
	 * @param ittTotalOtHours the ittTotalOtHours to set
	 */
	public final void setIttTotalOtHours(final String ittTotalOtHours) {
		this.ittTotalOtHours = ittTotalOtHours;
	}
	/**
	 * @return the ittSingleOtAmount
	 */
	public final String getIttSingleOtAmount() {
		return ittSingleOtAmount;
	}
	/**
	 * @param ittSingleOtAmount the ittSingleOtAmount to set
	 */
	public final void setIttSingleOtAmount(final String ittSingleOtAmount) {
		this.ittSingleOtAmount = ittSingleOtAmount;
	}
	/**
	 * @return the ittDoubleOtAmount
	 */
	public final String getIttDoubleOtAmount() {
		return ittDoubleOtAmount;
	}
	/**
	 * @param ittDoubleOtAmount the ittDoubleOtAmount to set
	 */
	public final void setIttDoubleOtAmount(final String ittDoubleOtAmount) {
		this.ittDoubleOtAmount = ittDoubleOtAmount;
	}
	/**
	 * @return the ittTotalOt
	 */
	public final String getIttTotalOt() {
		return ittTotalOt;
	}
	/**
	 * @param ittTotalOt the ittTotalOt to set
	 */
	public final void setIttTotalOt(final String ittTotalOt) {
		this.ittTotalOt = ittTotalOt;
	}
	/**
	 * @return the ittTds
	 */
	public final String getIttTds() {
		return ittTds;
	}
	/**
	 * @param ittTds the ittTds to set
	 */
	public final void setIttTds(final String ittTds) {
		this.ittTds = ittTds;
	}
	/**
	 * @return the ittNetOtAmount
	 */
	public final String getIttNetOtAmount() {
		return ittNetOtAmount;
	}
	/**
	 * @param ittNetOtAmount the ittNetOtAmount to set
	 */
	public final void setIttNetOtAmount(final String ittNetOtAmount) {
		this.ittNetOtAmount = ittNetOtAmount;
	}
	/**
	 * @return the ittOTCalID
	 */
	public final String getIttOTCalID() {
		return ittOTCalID;
	}
	/**
	 * @param ittOTCalID the ittOTCalID to set
	 */
	public final void setIttOTCalID(final String ittOTCalID) {
		this.ittOTCalID = ittOTCalID;
	}
	/**
	 * @return the ittMonth
	 */
	public final String getIttMonth() {
		return ittMonth;
	}
	/**
	 * @param ittMonth the ittMonth to set
	 */
	public final void setIttMonth(final String ittMonth) {
		this.ittMonth = ittMonth;
	}
	/**
	 * @return the ittYear
	 */
	public final String getIttYear() {
		return ittYear;
	}
	/**
	 * @param ittYear the ittYear to set
	 */
	public final void setIttYear(final String ittYear) {
		this.ittYear = ittYear;
	}
	/**
	 * @return the calOtEmpIteratorList
	 */
	public final ArrayList getCalOtEmpIteratorList() {
		return calOtEmpIteratorList;
	}
	/**
	 * @param calOtEmpIteratorList the calOtEmpIteratorList to set
	 */
	public final void setCalOtEmpIteratorList(final ArrayList calOtEmpIteratorList) {
		this.calOtEmpIteratorList = calOtEmpIteratorList;
	}
	/**
	 * @return the calOtEmpListLength
	 */
	public final boolean isCalOtEmpListLength() {
		return calOtEmpListLength;
	}
	/**
	 * @param calOtEmpListLength the calOtEmpListLength to set
	 */
	public final void setCalOtEmpListLength(final boolean calOtEmpListLength) {
		this.calOtEmpListLength = calOtEmpListLength;
	}
}
