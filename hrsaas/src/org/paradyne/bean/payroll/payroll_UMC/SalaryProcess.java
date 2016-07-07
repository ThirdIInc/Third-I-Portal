package org.paradyne.bean.payroll.payroll_UMC;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author : AA0418 Prakash Shetkar
 *  Date   : May 11, 2010
 *
 */
public class SalaryProcess extends BeanBase {
	
	private String month="";
	private String monthView="";
	private String year="";
	private boolean divisionFlag=false;
	private String divisionId="";
	private String divisionName="";
	private boolean branchFlag=false;
	private String branchId="";
	private String branchName="";
	private String branchViewId="";
	private String branchViewName="";
	private boolean departmentFlag=false;
	private String departmentViewId="";
	private String departmentViewName="";
	private String departmentId="";
	private String departmentName="";
	private boolean employeeTypeFlag=false;
	private String employeeTypeViewId="";
	private String employeeTypeViewName="";
	private String employeeTypeId="";
	private String employeeTypeName="";
	private boolean payBillFlag=false;
	private String payBillViewId="";
	private String payBillViewName="";
	private String payBillId="";
	private String payBillName="";
	private String ledgerCode="";
	private String ledgerStatus="";
	private boolean showFlag=false;
	private String recordsPerPage="";
	private String joinDaysFlag="N";
	private String recoveryFlag="N";
	private String profHandiFLag="N";
	private String incomeTaxFlag="N";
	private String vpfFlag="";
	private String lwfFlag="";
	private String lwfDebitCode="";
	private String recoveryDebitCode="";
	private String lwfCreditCode="";
	private String creditRound="";
	private String totalCreditRound="";
	private String totalDebitRound="";
	private String netPayRound="";
	private String empid;
	private String empName;
	private String empViewId;
	private String empViewName;
	private String creditCode="";
	private String creditName="";
	private String debitCode="";
	private String debitName="";
	private ArrayList<SalaryProcess> creditHeader=null;
	private ArrayList<SalaryProcess> debitHeader=null;
	
	private String uploadCreditName;
	private String uploadCreditCode;
	private String uploadFileNameCredit;
	
	private String uploadDebitName;
	private String uploadDebitCode;
	private String uploadFileNameDebit;
	
	private String ptaxCode;
	private String esiCode;
	private String pfCode;
	private boolean dataFlag=false;
	private boolean otherIncomeFlag=false;
	private boolean leaveEncashFlag=false;
	private boolean extraWorkFlag=false;
	private boolean allowanceFlag=false;
	// processed salary list variables
	private String listLength = "false";
	private String myPage = "";
	ArrayList iteratorList;
	private String totalRecords = "";
	
	private String listLedgerCode = "";
	private String listMonthName = "";
	private String listMonthCode = "";
	private String listYear = "";
	private String listEmpTypeName = "";
	private String listEmpTypeId = "";
	private String listPayBillName = "";
	private String listPayBillId = "";
	private String listDeptName = "";
	private String listDeptId = "";
	private String listBranchName = "";
	private String listBranchId = "";
	private String listDivName = "";
	private String listDivId = "";
	private String listLedgerStatus = "";
	
	private String hdPage;
	private String hdProcess;
	private String fromTotRec="";
	private String toTotRec="";
	
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public String getHdProcess() {
		return hdProcess;
	}
	public void setHdProcess(String hdProcess) {
		this.hdProcess = hdProcess;
	}
	public String getFromTotRec() {
		return fromTotRec;
	}
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}
	public String getToTotRec() {
		return toTotRec;
	}
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
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
	
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(String employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	public String getEmployeeTypeName() {
		return employeeTypeName;
	}
	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	
	public String getPayBillId() {
		return payBillId;
	}
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}
	public String getPayBillName() {
		return payBillName;
	}
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	public boolean isDivisionFlag() {
		return divisionFlag;
	}
	public void setDivisionFlag(boolean divisionFlag) {
		this.divisionFlag = divisionFlag;
	}
	public boolean isBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}
	public boolean isDepartmentFlag() {
		return departmentFlag;
	}
	public void setDepartmentFlag(boolean departmentFlag) {
		this.departmentFlag = departmentFlag;
	}
	public boolean isEmployeeTypeFlag() {
		return employeeTypeFlag;
	}
	public void setEmployeeTypeFlag(boolean employeeTypeFlag) {
		this.employeeTypeFlag = employeeTypeFlag;
	}
	public boolean isPayBillFlag() {
		return payBillFlag;
	}
	public void setPayBillFlag(boolean payBillFlag) {
		this.payBillFlag = payBillFlag;
	}
	public String getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(String recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public String getJoinDaysFlag() {
		return joinDaysFlag;
	}
	public void setJoinDaysFlag(String joinDaysFlag) {
		this.joinDaysFlag = joinDaysFlag;
	}
	public String getRecoveryFlag() {
		return recoveryFlag;
	}
	public void setRecoveryFlag(String recoveryFlag) {
		this.recoveryFlag = recoveryFlag;
	}
	public String getProfHandiFLag() {
		return profHandiFLag;
	}
	public void setProfHandiFLag(String profHandiFLag) {
		this.profHandiFLag = profHandiFLag;
	}
	public String getIncomeTaxFlag() {
		return incomeTaxFlag;
	}
	public void setIncomeTaxFlag(String incomeTaxFlag) {
		this.incomeTaxFlag = incomeTaxFlag;
	}
	public String getVpfFlag() {
		return vpfFlag;
	}
	public void setVpfFlag(String vpfFlag) {
		this.vpfFlag = vpfFlag;
	}
	public String getCreditRound() {
		return creditRound;
	}
	public void setCreditRound(String creditRound) {
		this.creditRound = creditRound;
	}
	public String getTotalCreditRound() {
		return totalCreditRound;
	}
	public void setTotalCreditRound(String totalCreditRound) {
		this.totalCreditRound = totalCreditRound;
	}
	public String getTotalDebitRound() {
		return totalDebitRound;
	}
	public void setTotalDebitRound(String totalDebitRound) {
		this.totalDebitRound = totalDebitRound;
	}
	public String getNetPayRound() {
		return netPayRound;
	}
	public void setNetPayRound(String netPayRound) {
		this.netPayRound = netPayRound;
	}
	public String getLwfFlag() {
		return lwfFlag;
	}
	public void setLwfFlag(String lwfFlag) {
		this.lwfFlag = lwfFlag;
	}
	public String getLwfDebitCode() {
		return lwfDebitCode;
	}
	public void setLwfDebitCode(String lwfDebitCode) {
		this.lwfDebitCode = lwfDebitCode;
	}
	public String getLwfCreditCode() {
		return lwfCreditCode;
	}
	public void setLwfCreditCode(String lwfCreditCode) {
		this.lwfCreditCode = lwfCreditCode;
	}
	public boolean isShowFlag() {
		return showFlag;
	}
	public void setShowFlag(boolean showFlag) {
		this.showFlag = showFlag;
	}

	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public ArrayList getIteratorList() {
		return iteratorList;
	}
	public void setIteratorList(ArrayList iteratorList) {
		this.iteratorList = iteratorList;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getListLedgerCode() {
		return listLedgerCode;
	}
	public void setListLedgerCode(String listLedgerCode) {
		this.listLedgerCode = listLedgerCode;
	}
	public String getListMonthName() {
		return listMonthName;
	}
	public void setListMonthName(String listMonthName) {
		this.listMonthName = listMonthName;
	}
	public String getListMonthCode() {
		return listMonthCode;
	}
	public void setListMonthCode(String listMonthCode) {
		this.listMonthCode = listMonthCode;
	}
	public String getListYear() {
		return listYear;
	}
	public void setListYear(String listYear) {
		this.listYear = listYear;
	}
	public String getListEmpTypeName() {
		return listEmpTypeName;
	}
	public void setListEmpTypeName(String listEmpTypeName) {
		this.listEmpTypeName = listEmpTypeName;
	}
	public String getListEmpTypeId() {
		return listEmpTypeId;
	}
	public void setListEmpTypeId(String listEmpTypeId) {
		this.listEmpTypeId = listEmpTypeId;
	}
	public String getListPayBillName() {
		return listPayBillName;
	}
	public void setListPayBillName(String listPayBillName) {
		this.listPayBillName = listPayBillName;
	}
	public String getListPayBillId() {
		return listPayBillId;
	}
	public void setListPayBillId(String listPayBillId) {
		this.listPayBillId = listPayBillId;
	}
	public String getListDeptName() {
		return listDeptName;
	}
	public void setListDeptName(String listDeptName) {
		this.listDeptName = listDeptName;
	}
	public String getListDeptId() {
		return listDeptId;
	}
	public void setListDeptId(String listDeptId) {
		this.listDeptId = listDeptId;
	}
	public String getListBranchName() {
		return listBranchName;
	}
	public void setListBranchName(String listBranchName) {
		this.listBranchName = listBranchName;
	}
	public String getListBranchId() {
		return listBranchId;
	}
	public void setListBranchId(String listBranchId) {
		this.listBranchId = listBranchId;
	}
	public String getListDivName() {
		return listDivName;
	}
	public void setListDivName(String listDivName) {
		this.listDivName = listDivName;
	}
	public String getListDivId() {
		return listDivId;
	}
	public void setListDivId(String listDivId) {
		this.listDivId = listDivId;
	}
	public String getListLedgerStatus() {
		return listLedgerStatus;
	}
	public void setListLedgerStatus(String listLedgerStatus) {
		this.listLedgerStatus = listLedgerStatus;
	}
	public String getLedgerStatus() {
		return ledgerStatus;
	}
	public void setLedgerStatus(String ledgerStatus) {
		this.ledgerStatus = ledgerStatus;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public ArrayList<SalaryProcess> getCreditHeader() {
		return creditHeader;
	}
	public void setCreditHeader(ArrayList<SalaryProcess> creditHeader) {
		this.creditHeader = creditHeader;
	}
	public ArrayList<SalaryProcess> getDebitHeader() {
		return debitHeader;
	}
	public void setDebitHeader(ArrayList<SalaryProcess> debitHeader) {
		this.debitHeader = debitHeader;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	public boolean isDataFlag() {
		return dataFlag;
	}
	public void setDataFlag(boolean dataFlag) {
		this.dataFlag = dataFlag;
	}
	public String getUploadCreditName() {
		return uploadCreditName;
	}
	public void setUploadCreditName(String uploadCreditName) {
		this.uploadCreditName = uploadCreditName;
	}
	public String getUploadCreditCode() {
		return uploadCreditCode;
	}
	public void setUploadCreditCode(String uploadCreditCode) {
		this.uploadCreditCode = uploadCreditCode;
	}
	public String getUploadFileNameCredit() {
		return uploadFileNameCredit;
	}
	public void setUploadFileNameCredit(String uploadFileNameCredit) {
		this.uploadFileNameCredit = uploadFileNameCredit;
	}
	public String getUploadDebitName() {
		return uploadDebitName;
	}
	public void setUploadDebitName(String uploadDebitName) {
		this.uploadDebitName = uploadDebitName;
	}
	public String getUploadDebitCode() {
		return uploadDebitCode;
	}
	public void setUploadDebitCode(String uploadDebitCode) {
		this.uploadDebitCode = uploadDebitCode;
	}
	public String getUploadFileNameDebit() {
		return uploadFileNameDebit;
	}
	public void setUploadFileNameDebit(String uploadFileNameDebit) {
		this.uploadFileNameDebit = uploadFileNameDebit;
	}
	public String getPtaxCode() {
		return ptaxCode;
	}
	public void setPtaxCode(String ptaxCode) {
		this.ptaxCode = ptaxCode;
	}
	public String getEsiCode() {
		return esiCode;
	}
	public void setEsiCode(String esiCode) {
		this.esiCode = esiCode;
	}
	public String getPfCode() {
		return pfCode;
	}
	public void setPfCode(String pfCode) {
		this.pfCode = pfCode;
	}
	public String getBranchViewId() {
		return branchViewId;
	}
	public void setBranchViewId(String branchViewId) {
		this.branchViewId = branchViewId;
	}
	public String getBranchViewName() {
		return branchViewName;
	}
	public void setBranchViewName(String branchViewName) {
		this.branchViewName = branchViewName;
	}
	public String getDepartmentViewId() {
		return departmentViewId;
	}
	public void setDepartmentViewId(String departmentViewId) {
		this.departmentViewId = departmentViewId;
	}
	public String getDepartmentViewName() {
		return departmentViewName;
	}
	public void setDepartmentViewName(String departmentViewName) {
		this.departmentViewName = departmentViewName;
	}
	public String getEmployeeTypeViewId() {
		return employeeTypeViewId;
	}
	public void setEmployeeTypeViewId(String employeeTypeViewId) {
		this.employeeTypeViewId = employeeTypeViewId;
	}
	public String getEmployeeTypeViewName() {
		return employeeTypeViewName;
	}
	public void setEmployeeTypeViewName(String employeeTypeViewName) {
		this.employeeTypeViewName = employeeTypeViewName;
	}
	public String getPayBillViewId() {
		return payBillViewId;
	}
	public void setPayBillViewId(String payBillViewId) {
		this.payBillViewId = payBillViewId;
	}
	public String getPayBillViewName() {
		return payBillViewName;
	}
	public void setPayBillViewName(String payBillViewName) {
		this.payBillViewName = payBillViewName;
	}
	public String getEmpViewId() {
		return empViewId;
	}
	public void setEmpViewId(String empViewId) {
		this.empViewId = empViewId;
	}
	public String getEmpViewName() {
		return empViewName;
	}
	public void setEmpViewName(String empViewName) {
		this.empViewName = empViewName;
	}
	public String getRecoveryDebitCode() {
		return recoveryDebitCode;
	}
	public void setRecoveryDebitCode(String recoveryDebitCode) {
		this.recoveryDebitCode = recoveryDebitCode;
	}
	public String getMonthView() {
		return monthView;
	}
	public void setMonthView(String monthView) {
		this.monthView = monthView;
	}
	public boolean isOtherIncomeFlag() {
		return otherIncomeFlag;
	}
	public void setOtherIncomeFlag(boolean otherIncomeFlag) {
		this.otherIncomeFlag = otherIncomeFlag;
	}
	public boolean isLeaveEncashFlag() {
		return leaveEncashFlag;
	}
	public void setLeaveEncashFlag(boolean leaveEncashFlag) {
		this.leaveEncashFlag = leaveEncashFlag;
	}
	public boolean isExtraWorkFlag() {
		return extraWorkFlag;
	}
	public void setExtraWorkFlag(boolean extraWorkFlag) {
		this.extraWorkFlag = extraWorkFlag;
	}
	public boolean isAllowanceFlag() {
		return allowanceFlag;
	}
	public void setAllowanceFlag(boolean allowanceFlag) {
		this.allowanceFlag = allowanceFlag;
	}
	
}
