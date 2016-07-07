/**
 * 
 */
package org.paradyne.bean.payroll;


import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author Pankaj_Jain,Venkatesh 
 * 
 */
public class MonthlyArrears extends BeanBase {

	//Employee Info Variables
	private String empId;
	private String empToken;
	private String empName;
	private String empBranch;
	private String empDept;
	private String empDesg;
	//private String divEsicZone;
	
	//Arrears Info Variables
	private String arrCode;
	private String arrMonth;
	private String arrYear;
	private String arrRefMonth;
	private String arrRefYear;
	private String arrDays;
	private String arrearPayType;
	private String empChkFlag = "false";
	private String totalEmpCount = "";
	private String totalNetAmount = "";
	
	//Filters Info Members
	private String divFlag = "";
	private String divCode = "";
	private String divName = "";
	private String typeFlag;
	private String payinSalFlag;
	private String deductTaxFlag;
	private String taxWorkFlowFlag;
	private String typeCode = "";
	private String typeName = "";
	private String payBillFlag;
	private String payBillNo = "";
	private String payBillName = "";
	private String brnFlag;
	private String brnCode = "";
	private String brnName = "";
	private String deptFlag;
	private String deptCode = "";
	private String deptName = "";
	private String joiningDaysFlag="";
	
	//Arrears Array List for Debit & credit
	private ArrayList<Object> debitHeader;
	private ArrayList<Object> creditHeader; 
	
	//Table Variables credit and Debit
	private String creditCode;
	private String creditName;
	private String debitCode;
	private String debitName;
	private Object[] totalCredit;
	private String totalDebit;
	private String grossamount;
	private String netAmount;
	
	//Objects for debit and tax
	private Object[][] ptaxLoc;
	private Object[][] pfBeanData;
	private Object[][] esiBeanData;
	private Object[][] empTypeBeanData;
	private Object[][] empBranchBeanData;
	private String salDays=""; 
	private String orgArrDays="";
	private String totalCreditAmonut="";
	private String ledgerCode="";
	private String refMonth;
	private String salStatus;
	private String joinDate;
	
	//ADDED BY REEBA
	private String myPage ;
	private String hiddencode;
	private String show;
	private String totalRecords;
	private String listLength="false";
	private String hdeleteCode;
	ArrayList iteratorlist;
	
	private String listArrearCode = "";
	private String listReflectingMonth = "";
	private String listReflectingMonthCode = "";
	private String listReflectingYear = "";
	private String listDivName = "";
	private String listDivId = "";
	private String listBranch = "";
	private String listBranchId = "";
	private String listDepartment = "";
	private String listDeptId = "";
	private String listEmpType = "";
	private String listEmpTypeId = "";
	private String listPaybill = "";
	private String listPaybillId = "";
	private String listPayType = "";
	private String listPayTypeName = "";
	private String listPayinSalFlag="";
	private String listDeductTaxFlag="";
	
	private boolean filterFlag = false;
	private boolean addFlag = false;
	private String branchViewId="";
	private String branchViewName="";
	private String departmentViewId="";
	private String departmentViewName="";
	private String employeeTypeViewId="";
	private String employeeTypeViewName="";
	private String payBillViewId="";
	private String payBillViewName="";
	private String empViewId;
	private String empViewName;
	private String empStatusView;
	private String empTokenView;
	
	private String gridLength="false";
	private String totalGridRecords="";
	private String myGridPage ;
	
	private String arrearPayTypeName;
	private String monthRef;
	
	//ADDED BY REEBA ENDS

	
	// Flag
	private String flag = "false";
	
	private Object[][] jspRows;

	public Object[][] getJspRows() {
		return jspRows;
	}

	public void setJspRows(Object[][] jspRows) {
		this.jspRows = jspRows;
	}

	public Object[][] getPtaxLoc() {
		return ptaxLoc;
	}

	public void setPtaxLoc(Object[][] ptaxLoc) {
		this.ptaxLoc = ptaxLoc;
	}

	public Object[][] getPfBeanData() {
		return pfBeanData;
	}

	public void setPfBeanData(Object[][] pfBeanData) {
		this.pfBeanData = pfBeanData;
	}

	public Object[][] getEsiBeanData() {
		return esiBeanData;
	}

	public void setEsiBeanData(Object[][] esiBeanData) {
		this.esiBeanData = esiBeanData;
	}

	public Object[][] getEmpTypeBeanData() {
		return empTypeBeanData;
	}

	public void setEmpTypeBeanData(Object[][] empTypeBeanData) {
		this.empTypeBeanData = empTypeBeanData;
	}

	public Object[][] getEmpBranchBeanData() {
		return empBranchBeanData;
	}

	public void setEmpBranchBeanData(Object[][] empBranchBeanData) {
		this.empBranchBeanData = empBranchBeanData;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getDebitCode() {
		return debitCode;
	}

	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}

	public String getDebitName() {
		return debitName;
	}

	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}

	public ArrayList<Object> getDebitHeader() {
		return debitHeader;
	}

	public ArrayList<Object> getDebitNames() {
		return debitHeader;
	}

	public void setDebitHeader(ArrayList<Object> debitHeader) {
		this.debitHeader = debitHeader;
	}

	public ArrayList<Object> getCreditHeader() {
		return creditHeader;
	}

	public void setCreditHeader(ArrayList<Object> creditHeader) {
		this.creditHeader = creditHeader;
	}

	public String getArrDays() {
		return arrDays;
	}

	public void setArrDays(String arrDays) {
		this.arrDays = arrDays;
	}

	public String getArrMonth() {
		return arrMonth;
	}

	public void setArrMonth(String arrMonth) {
		this.arrMonth = arrMonth;
	}

	public String getArrYear() {
		return arrYear;
	}

	public void setArrYear(String arrYear) {
		this.arrYear = arrYear;
	}

	public String getArrRefMonth() {
		return arrRefMonth;
	}

	public void setArrRefMonth(String arrRefMonth) {
		this.arrRefMonth = arrRefMonth;
	}

	public String getArrRefYear() {
		return arrRefYear;
	}

	public void setArrRefYear(String arrRefYear) {
		this.arrRefYear = arrRefYear;
	}

	public String getEmpBranch() {
		return empBranch;
	}

	public void setEmpBranch(String empBranch) {
		this.empBranch = empBranch;
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getArrCode() {
		return arrCode;
	}

	public void setArrCode(String arrCode) {
		this.arrCode = arrCode;
	}

	
	public Object[] getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(Object[] totalCredit) {
		this.totalCredit = totalCredit;
	}

	public String getTotalDebit() {
		return totalDebit;
	}

	public void setTotalDebit(String totalDebit) {
		this.totalDebit = totalDebit;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getGrossamount() {
		return grossamount;
	}

	public void setGrossamount(String grossamount) {
		this.grossamount = grossamount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSalDays() {
		return salDays;
	}

	public void setSalDays(String salDays) {
		this.salDays = salDays;
	}

	public String getOrgArrDays() {
		return orgArrDays;
	}

	public void setOrgArrDays(String orgArrDays) {
		this.orgArrDays = orgArrDays;
	}

	public String getTotalCreditAmonut() {
		return totalCreditAmonut;
	}

	public void setTotalCreditAmonut(String totalCreditAmonut) {
		this.totalCreditAmonut = totalCreditAmonut;
	}

	public String getLedgerCode() {
		return ledgerCode;
	}

	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPayBillFlag() {
		return payBillFlag;
	}

	public void setPayBillFlag(String payBillFlag) {
		this.payBillFlag = payBillFlag;
	}

	public String getPayBillNo() {
		return payBillNo;
	}

	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}

	public String getPayBillName() {
		return payBillName;
	}

	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}

	public String getBrnFlag() {
		return brnFlag;
	}

	public void setBrnFlag(String brnFlag) {
		this.brnFlag = brnFlag;
	}

	public String getBrnCode() {
		return brnCode;
	}

	public void setBrnCode(String brnCode) {
		this.brnCode = brnCode;
	}

	public String getBrnName() {
		return brnName;
	}

	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}

	public String getDeptFlag() {
		return deptFlag;
	}

	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
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

	public String getEmpChkFlag() {
		return empChkFlag;
	}

	public void setEmpChkFlag(String empChkFlag) {
		this.empChkFlag = empChkFlag;
	}

	public String getDivFlag() {
		return divFlag;
	}

	public void setDivFlag(String divFlag) {
		this.divFlag = divFlag;
	}

	public String getRefMonth() {
		return refMonth;
	}

	public void setRefMonth(String refMonth) {
		this.refMonth = refMonth;
	}

	public String getSalStatus() {
		return salStatus;
	}

	public void setSalStatus(String salStatus) {
		this.salStatus = salStatus;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getJoiningDaysFlag() {
		return joiningDaysFlag;
	}

	public void setJoiningDaysFlag(String joiningDaysFlag) {
		this.joiningDaysFlag = joiningDaysFlag;
	}

	public String getArrearPayType() {
		return arrearPayType;
	}

	public void setArrearPayType(String arrearPayType) {
		this.arrearPayType = arrearPayType;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getListLength() {
		return listLength;
	}

	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}

	public String getListArrearCode() {
		return listArrearCode;
	}

	public void setListArrearCode(String listArrearCode) {
		this.listArrearCode = listArrearCode;
	}

	public String getListReflectingMonth() {
		return listReflectingMonth;
	}

	public void setListReflectingMonth(String listReflectingMonth) {
		this.listReflectingMonth = listReflectingMonth;
	}

	public String getListReflectingMonthCode() {
		return listReflectingMonthCode;
	}

	public void setListReflectingMonthCode(String listReflectingMonthCode) {
		this.listReflectingMonthCode = listReflectingMonthCode;
	}

	public String getListReflectingYear() {
		return listReflectingYear;
	}

	public void setListReflectingYear(String listReflectingYear) {
		this.listReflectingYear = listReflectingYear;
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

	public String getListBranch() {
		return listBranch;
	}

	public void setListBranch(String listBranch) {
		this.listBranch = listBranch;
	}

	public String getListBranchId() {
		return listBranchId;
	}

	public void setListBranchId(String listBranchId) {
		this.listBranchId = listBranchId;
	}

	public String getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(String listDepartment) {
		this.listDepartment = listDepartment;
	}

	public String getListDeptId() {
		return listDeptId;
	}

	public void setListDeptId(String listDeptId) {
		this.listDeptId = listDeptId;
	}

	public String getListEmpType() {
		return listEmpType;
	}

	public void setListEmpType(String listEmpType) {
		this.listEmpType = listEmpType;
	}

	public String getListEmpTypeId() {
		return listEmpTypeId;
	}

	public void setListEmpTypeId(String listEmpTypeId) {
		this.listEmpTypeId = listEmpTypeId;
	}

	public String getListPaybill() {
		return listPaybill;
	}

	public void setListPaybill(String listPaybill) {
		this.listPaybill = listPaybill;
	}

	public String getListPaybillId() {
		return listPaybillId;
	}

	public void setListPaybillId(String listPaybillId) {
		this.listPaybillId = listPaybillId;
	}

	public boolean isFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(boolean filterFlag) {
		this.filterFlag = filterFlag;
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

	public String getEmpStatusView() {
		return empStatusView;
	}

	public void setEmpStatusView(String empStatusView) {
		this.empStatusView = empStatusView;
	}

	public String getEmpTokenView() {
		return empTokenView;
	}

	public void setEmpTokenView(String empTokenView) {
		this.empTokenView = empTokenView;
	}

	public String getGridLength() {
		return gridLength;
	}

	public void setGridLength(String gridLength) {
		this.gridLength = gridLength;
	}

	public String getTotalGridRecords() {
		return totalGridRecords;
	}

	public void setTotalGridRecords(String totalGridRecords) {
		this.totalGridRecords = totalGridRecords;
	}

	public String getMyGridPage() {
		return myGridPage;
	}

	public void setMyGridPage(String myGridPage) {
		this.myGridPage = myGridPage;
	}

	public String getListPayType() {
		return listPayType;
	}

	public void setListPayType(String listPayType) {
		this.listPayType = listPayType;
	}

	public String getListPayTypeName() {
		return listPayTypeName;
	}

	public void setListPayTypeName(String listPayTypeName) {
		this.listPayTypeName = listPayTypeName;
	}

	public boolean isAddFlag() {
		return addFlag;
	}

	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	public String getArrearPayTypeName() {
		return arrearPayTypeName;
	}

	public void setArrearPayTypeName(String arrearPayTypeName) {
		this.arrearPayTypeName = arrearPayTypeName;
	}

	public String getMonthRef() {
		return monthRef;
	}

	public void setMonthRef(String monthRef) {
		this.monthRef = monthRef;
	}

	public String getPayinSalFlag() {
		return payinSalFlag;
	}

	public void setPayinSalFlag(String payinSalFlag) {
		this.payinSalFlag = payinSalFlag;
	}

	public String getListPayinSalFlag() {
		return listPayinSalFlag;
	}

	public void setListPayinSalFlag(String listPayinSalFlag) {
		this.listPayinSalFlag = listPayinSalFlag;
	}

	public String getDeductTaxFlag() {
		return deductTaxFlag;
	}

	public void setDeductTaxFlag(String deductTaxFlag) {
		this.deductTaxFlag = deductTaxFlag;
	}

	public String getListDeductTaxFlag() {
		return listDeductTaxFlag;
	}

	public void setListDeductTaxFlag(String listDeductTaxFlag) {
		this.listDeductTaxFlag = listDeductTaxFlag;
	}

	public String getTaxWorkFlowFlag() {
		return taxWorkFlowFlag;
	}

	public void setTaxWorkFlowFlag(String taxWorkFlowFlag) {
		this.taxWorkFlowFlag = taxWorkFlowFlag;
	}

	public String getTotalEmpCount() {
		return totalEmpCount;
	}

	public void setTotalEmpCount(String totalEmpCount) {
		this.totalEmpCount = totalEmpCount;
	}

	public String getTotalNetAmount() {
		return totalNetAmount;
	}

	public void setTotalNetAmount(String totalNetAmount) {
		this.totalNetAmount = totalNetAmount;
	}

	/*public String getDivEsicZone() {
		return divEsicZone;
	}

	public void setDivEsicZone(String divEsicZone) {
		this.divEsicZone = divEsicZone;
	}*/
}
