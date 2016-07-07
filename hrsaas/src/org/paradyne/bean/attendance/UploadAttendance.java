package org.paradyne.bean.attendance;

import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.lib.BeanBase;

public class UploadAttendance extends BeanBase {
	private String empIdFlag;
	private String empNameFlag;
	private String dateFlag;
	private String inTimeFlag;
	private String outTimeFlag;
	private String oneTimeFlag;
	private String workHrsFlag;
	private String ioFlag;
	private String noDataFlag;
	private String shiftFlag;
	
	private String fromDate;
	private String toDate;
	private String empTypeName;
	private String empTypeCode;
	private String payBillNo;
	private String payBillCode;
	private String branchName;
	private String branchCode;
	private String deptName;
	private String deptCode;
	private String divName;
	private String divCode;
	private String uploadFileName;
	
	private String empCode;
	private String empName;
	private String date;
	private String inTime;
	private String outTime;
	private String oneTime;
	private String ioSetting;
	private String workHrs;
	private String shift;
	
	private boolean outDoorFlag = false;
	private String outDoorStartTime;
	private String outDoorEndTime;
	private String nightShifFlag=""; 
	private String searchEmpId="";
	private String searchEmpName="";
	private String searchEmpToken="";	
	 
	private ArrayList<Object> attendanceDataList = new ArrayList<Object>();
	
	private String fromDateAuto;
	private String toDateAuto;
	private String server;
	private HashMap<Integer, String> serverList = new HashMap<Integer, String>();

	/**
	 * @return the empIdFlag
	 */
	public String getEmpIdFlag() {
		return empIdFlag;
	}

	/**
	 * @param empIdFlag the empIdFlag to set
	 */
	public void setEmpIdFlag(String empIdFlag) {
		this.empIdFlag = empIdFlag;
	}

	/**
	 * @return the empNameFlag
	 */
	public String getEmpNameFlag() {
		return empNameFlag;
	}

	/**
	 * @param empNameFlag the empNameFlag to set
	 */
	public void setEmpNameFlag(String empNameFlag) {
		this.empNameFlag = empNameFlag;
	}

	/**
	 * @return the dateFlag
	 */
	public String getDateFlag() {
		return dateFlag;
	}

	/**
	 * @param dateFlag the dateFlag to set
	 */
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	/**
	 * @return the inTimeFlag
	 */
	public String getInTimeFlag() {
		return inTimeFlag;
	}

	/**
	 * @param inTimeFlag the inTimeFlag to set
	 */
	public void setInTimeFlag(String inTimeFlag) {
		this.inTimeFlag = inTimeFlag;
	}

	/**
	 * @return the outTimeFlag
	 */
	public String getOutTimeFlag() {
		return outTimeFlag;
	}

	/**
	 * @param outTimeFlag the outTimeFlag to set
	 */
	public void setOutTimeFlag(String outTimeFlag) {
		this.outTimeFlag = outTimeFlag;
	}

	/**
	 * @return the oneTimeFlag
	 */
	public String getOneTimeFlag() {
		return oneTimeFlag;
	}

	/**
	 * @param oneTimeFlag the oneTimeFlag to set
	 */
	public void setOneTimeFlag(String oneTimeFlag) {
		this.oneTimeFlag = oneTimeFlag;
	}

	/**
	 * @return the workHrsFlag
	 */
	public String getWorkHrsFlag() {
		return workHrsFlag;
	}

	/**
	 * @param workHrsFlag the workHrsFlag to set
	 */
	public void setWorkHrsFlag(String workHrsFlag) {
		this.workHrsFlag = workHrsFlag;
	}

	/**
	 * @return the ioFlag
	 */
	public String getIoFlag() {
		return ioFlag;
	}

	/**
	 * @param ioFlag the ioFlag to set
	 */
	public void setIoFlag(String ioFlag) {
		this.ioFlag = ioFlag;
	}

	/**
	 * @return the noDataFlag
	 */
	public String getNoDataFlag() {
		return noDataFlag;
	}

	/**
	 * @param noDataFlag the noDataFlag to set
	 */
	public void setNoDataFlag(String noDataFlag) {
		this.noDataFlag = noDataFlag;
	}

	/**
	 * @return the shiftFlag
	 */
	public String getShiftFlag() {
		return shiftFlag;
	}

	/**
	 * @param shiftFlag the shiftFlag to set
	 */
	public void setShiftFlag(String shiftFlag) {
		this.shiftFlag = shiftFlag;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the empTypeName
	 */
	public String getEmpTypeName() {
		return empTypeName;
	}

	/**
	 * @param empTypeName the empTypeName to set
	 */
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}

	/**
	 * @return the empTypeCode
	 */
	public String getEmpTypeCode() {
		return empTypeCode;
	}

	/**
	 * @param empTypeCode the empTypeCode to set
	 */
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}

	/**
	 * @return the payBillNo
	 */
	public String getPayBillNo() {
		return payBillNo;
	}

	/**
	 * @param payBillNo the payBillNo to set
	 */
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}

	/**
	 * @return the payBillCode
	 */
	public String getPayBillCode() {
		return payBillCode;
	}

	/**
	 * @param payBillCode the payBillCode to set
	 */
	public void setPayBillCode(String payBillCode) {
		this.payBillCode = payBillCode;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the divName
	 */
	public String getDivName() {
		return divName;
	}

	/**
	 * @param divName the divName to set
	 */
	public void setDivName(String divName) {
		this.divName = divName;
	}

	/**
	 * @return the divCode
	 */
	public String getDivCode() {
		return divCode;
	}

	/**
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the inTime
	 */
	public String getInTime() {
		return inTime;
	}

	/**
	 * @param inTime the inTime to set
	 */
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	/**
	 * @return the outTime
	 */
	public String getOutTime() {
		return outTime;
	}

	/**
	 * @param outTime the outTime to set
	 */
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	/**
	 * @return the oneTime
	 */
	public String getOneTime() {
		return oneTime;
	}

	/**
	 * @param oneTime the oneTime to set
	 */
	public void setOneTime(String oneTime) {
		this.oneTime = oneTime;
	}

	/**
	 * @return the ioSetting
	 */
	public String getIoSetting() {
		return ioSetting;
	}

	/**
	 * @param ioSetting the ioSetting to set
	 */
	public void setIoSetting(String ioSetting) {
		this.ioSetting = ioSetting;
	}

	/**
	 * @return the workHrs
	 */
	public String getWorkHrs() {
		return workHrs;
	}

	/**
	 * @param workHrs the workHrs to set
	 */
	public void setWorkHrs(String workHrs) {
		this.workHrs = workHrs;
	}

	/**
	 * @return the shift
	 */
	public String getShift() {
		return shift;
	}

	/**
	 * @param shift the shift to set
	 */
	public void setShift(String shift) {
		this.shift = shift;
	}

	/**
	 * @return the outDoorFlag
	 */
	public boolean isOutDoorFlag() {
		return outDoorFlag;
	}

	/**
	 * @param outDoorFlag the outDoorFlag to set
	 */
	public void setOutDoorFlag(boolean outDoorFlag) {
		this.outDoorFlag = outDoorFlag;
	}

	/**
	 * @return the outDoorStartTime
	 */
	public String getOutDoorStartTime() {
		return outDoorStartTime;
	}

	/**
	 * @param outDoorStartTime the outDoorStartTime to set
	 */
	public void setOutDoorStartTime(String outDoorStartTime) {
		this.outDoorStartTime = outDoorStartTime;
	}

	/**
	 * @return the outDoorEndTime
	 */
	public String getOutDoorEndTime() {
		return outDoorEndTime;
	}

	/**
	 * @param outDoorEndTime the outDoorEndTime to set
	 */
	public void setOutDoorEndTime(String outDoorEndTime) {
		this.outDoorEndTime = outDoorEndTime;
	}

	/**
	 * @return the nightShifFlag
	 */
	public String getNightShifFlag() {
		return nightShifFlag;
	}

	/**
	 * @param nightShifFlag the nightShifFlag to set
	 */
	public void setNightShifFlag(String nightShifFlag) {
		this.nightShifFlag = nightShifFlag;
	}

	/**
	 * @return the searchEmpId
	 */
	public String getSearchEmpId() {
		return searchEmpId;
	}

	/**
	 * @param searchEmpId the searchEmpId to set
	 */
	public void setSearchEmpId(String searchEmpId) {
		this.searchEmpId = searchEmpId;
	}

	/**
	 * @return the searchEmpName
	 */
	public String getSearchEmpName() {
		return searchEmpName;
	}

	/**
	 * @param searchEmpName the searchEmpName to set
	 */
	public void setSearchEmpName(String searchEmpName) {
		this.searchEmpName = searchEmpName;
	}

	/**
	 * @return the searchEmpToken
	 */
	public String getSearchEmpToken() {
		return searchEmpToken;
	}

	/**
	 * @param searchEmpToken the searchEmpToken to set
	 */
	public void setSearchEmpToken(String searchEmpToken) {
		this.searchEmpToken = searchEmpToken;
	}

	/**
	 * @return the attendanceDataList
	 */
	public ArrayList<Object> getAttendanceDataList() {
		return attendanceDataList;
	}

	/**
	 * @param attendanceDataList the attendanceDataList to set
	 */
	public void setAttendanceDataList(ArrayList<Object> attendanceDataList) {
		this.attendanceDataList = attendanceDataList;
	}

	/**
	 * @return the fromDateAuto
	 */
	public String getFromDateAuto() {
		return fromDateAuto;
	}
	

	/**
	 * @param fromDateAuto the fromDateAuto to set
	 */
	public void setFromDateAuto(String fromDateAuto) {
		this.fromDateAuto = fromDateAuto;
	}
	

	/**
	 * @return the toDateAuto
	 */
	public String getToDateAuto() {
		return toDateAuto;
	}

	
	/**
	 * @param toDateAuto the toDateAuto to set
	 */
	public void setToDateAuto(String toDateAuto) {
		this.toDateAuto = toDateAuto;
	}
	

	/**
	 * @return the driver
	 */
	public String getServer() {
		return server;
	}

	
	/**
	 * @param driver the driver to set
	 */
	public void setServer(String server) {
		this.server = server;
	}
	

	/**
	 * @return the driverList
	 */
	public HashMap<Integer, String> getServerList() {
		return serverList;
	}

	/**
	 * @param driverList the driverList to set
	 */
	public void setServerList(HashMap<Integer, String> serverList) {
		this.serverList = serverList;
	}
}