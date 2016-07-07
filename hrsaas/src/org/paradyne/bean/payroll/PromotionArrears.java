/**
 * Bhushan
 * Jun 12, 2008
 **/

package org.paradyne.bean.payroll;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class PromotionArrears extends BeanBase {
	//List Page Variales -- BEGINS
	/** String. * */
	private String totalEmployee = "";
	/** String. * */
	private String onholdFlag = "";
	/** String. * */
	private String hPromCode = "";
	/** String. * */
	private String listEmpCode = "";
	/** String. * */
	private String listFromMonth = "";
	/** String. * */
	private String listFromYear = "";
	/** String. * */
	private String listToMonth = "";
	/** String. * */
	private String editEmpToken = "";
	/** String. * */
	private String editEmpName = "";
	/** String. * */
	private String editEmpCode = "";
	/** String. * */
	private String addEmpToken = "";
	/** String. * */
	private String addEmpName = "";
	/** String. * */
	private String addEmpCode = "";
	/** String. * */
	private String listToYear = "";
	/** String. * */
	private String payinSalCheckBox = "";
	/** String. * */
	private String arrCode = "";
	/** String. * */
	private String eArrMonth = "";
	/** String. * */
	private String monthName = "";
	/** String. * */
	private String eArrYear = "";
	/** String. * */
	private String divFlag = "";
	/** String. * */
	private String divCode = "";
	/** String. * */
	private String divName = "";
	/** String. * */
	private String typeFlag = "";
	/** String. * */
	private String typeCode = "";
	/** String. * */
	private String typeName = "";
	/** String. * */
	private String payBillFlag = "";
	/** String. * */
	private String payBillNo = "";
	/** String. * */
	private String payBillName = "";
	/** String. * */
	private String brnFlag = "";
	/** String. * */
	private String brnCode = "";
	/** String. * */
	private String brnName = "";
	/** String. * */
	private String deptFlag = "";
	/** String. * */
	private String deptCode = "";
	/** String. * */
	private String deptName = "";
	/** String. * */
	private String eId = "";
	/** String. * */
	private String eToken = "";
	/** String. * */
	private String eName = "";
	/** String. * */
	private String month = "";
	/** String. * */
	private String year = "";
	/** String. * */
	private String salDays = "";
	/** String. * */
	private String creditCode = "";
	/** String. * */
	private String creditName = "";
	/** ArrayList. * */
	private ArrayList crHDList;
	/** String. * */
	private String creditVal = "";
	/** ArrayList. * */
	private ArrayList crValList;
	/** String. * */
	private String totalCr = "";
	/** String. * */
	private String debitCode = "";
	/** String. * */
	private String debitName = "";
	/** ArrayList. * */
	private ArrayList drHDList;
	/** String. * */
	private String debitVal = "";
	/** ArrayList. * */
	private ArrayList drValList;
	/** String. * */
	private String totalDr = "";
	/** String. * */
	private String amtPay = "";
	/** ArrayList. * */
	private ArrayList arrList;
	/** boolean. * */
	private boolean flag = false;
	/** Object. * */
	private Object[][] pfData = null;
	/** Object. * */
	private Object[][] ptData = null;
	/** Object. * */
	private Object[][] esiData = null;
	/** Object. * */
	private Object[][] creditCodeObj;
	/** Object. * */
	private Object[][] debitCodeObj;
	/** String. * */
	private String fromTotRec = "";
	/** String. * */
	private String toTotRec = "";
	/** String. * */
	private String hdPage = "";
	/** String. * */
	private String hdProcess = "";
	/** String. * */
	private String arrDays = "";
	/** String. * */
	private String hMonth = "";
	/** String. * */
	private String promotionCode = "";
	/** String. * */
	private String monthRef = "";
	/** String. * */
	private String status = "";
	/** String. * */
	private String proccessDate = "";
	/** String. * */
	private String empChkFlag = "false";
	/** String. * */
	private String empOnHoldFlag = "";
	/** String. * */
	private String payinSalFlag = "";
	/** String. * */
	private String savedFlag = "";
	/** String. * */
	private String paidArrMonth = "";
	/** String. * */
	private String paidMonthName = "";
	/** String. * */
	private String paidYear = "";
	/** String. * */
	private String arrToMonth = "";
	/** String. * */
	private String arrToYear = "";
	/** String. * */
	private String arrPaidMonth = "";
	/** String. * */
	private String arrPaidYear = "";
	/** String. * */
	private String deductTaxCheckBox = "";
	/** String. * */
	private String deductTaxFlag = "";
	/** String. * */
	private String totalEmpCount = "";
	/** String. * */
	private String totalNetAmount = "";
	/** String. * */
	private String empProcessToken = "";
	/** String. * */
	private String empProcessName = "";
	/** String. * */
	private String empProcessId = "";
	/** String. * */
	private String empUpdateToken = "";
	/** String. * */
	private String empUpdateName = "";
	/** String. * */
	private String empUpdateId = "";
	/** String. * */
	private String listPaidMonth = "";
	/** String. * */
	private String listPaidYear = "";
	/** String. * */
	private String listDivName = "";
	/** String. * */
	private String listBranch = "";
	
	private String listArrearCode = "";
	/** String. * */
	private String listDivId = "";
	/** String. * */
	private String listBranchId = "";
	/** String. * */
	private String listPayinSalFlag = "";
	/** String. * */
	private String listStatus = "";
	/** String. * */
	private String listReflectingMonthCode = "";
	/** String. * */
	private String myPage = "";
	/** String. * */
	private String listLength = "";
	/** ArrayList. * */
	private ArrayList iteratorlist = null;
	/** String. * */
	private String suppressCreditFlag = "";
	/** String. * */
	private String suppressDebitCode = "";
	/** String. * */
	private String suppressDebitName = "";

	/**
	 * @return the suppressCreditFlag
	 */
	public String getSuppressCreditFlag() {
		return suppressCreditFlag;
	}

	/**
	 * @param suppressCreditFlag
	 *            the suppressCreditFlag to set
	 */
	public void setSuppressCreditFlag(String suppressCreditFlag) {
		this.suppressCreditFlag = suppressCreditFlag;
	}

	/**
	 * @return the suppressDebitCode
	 */
	public String getSuppressDebitCode() {
		return suppressDebitCode;
	}

	/**
	 * @param suppressDebitCode
	 *            the suppressDebitCode to set
	 */
	public void setSuppressDebitCode(String suppressDebitCode) {
		this.suppressDebitCode = suppressDebitCode;
	}

	/**
	 * @return the suppressDebitName
	 */
	public String getSuppressDebitName() {
		return suppressDebitName;
	}

	/**
	 * @param suppressDebitName
	 *            the suppressDebitName to set
	 */
	public void setSuppressDebitName(String suppressDebitName) {
		this.suppressDebitName = suppressDebitName;
	}

	public String getSavedFlag() {
		return savedFlag;
	}

	public void setSavedFlag(String savedFlag) {
		this.savedFlag = savedFlag;
	}

	public String getProccessDate() {
		return proccessDate;
	}

	public void setProccessDate(String proccessDate) {
		this.proccessDate = proccessDate;
	}

	public String getMonthRef() {
		return monthRef;
	}

	public void setMonthRef(String monthRef) {
		this.monthRef = monthRef;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getArrDays() {
		return arrDays;
	}

	public void setArrDays(String arrDays) {
		this.arrDays = arrDays;
	}

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

	public String getArrCode() {
		return arrCode;
	}

	public void setArrCode(String arrCode) {
		this.arrCode = arrCode;
	}

	public String getEArrMonth() {
		return eArrMonth;
	}

	public void setEArrMonth(String arrMonth) {
		eArrMonth = arrMonth;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getEArrYear() {
		return eArrYear;
	}

	public void setEArrYear(String arrYear) {
		eArrYear = arrYear;
	}

	public String getDivFlag() {
		return divFlag;
	}

	public void setDivFlag(String divFlag) {
		this.divFlag = divFlag;
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

	public String getEId() {
		return eId;
	}

	public void setEId(String id) {
		eId = id;
	}

	public String getEToken() {
		return eToken;
	}

	public void setEToken(String token) {
		eToken = token;
	}

	public String getEName() {
		return eName;
	}

	public void setEName(String name) {
		eName = name;
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

	public String getSalDays() {
		return salDays;
	}

	public void setSalDays(String salDays) {
		this.salDays = salDays;
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

	public ArrayList getCrHDList() {
		return crHDList;
	}

	public void setCrHDList(ArrayList crHDList) {
		this.crHDList = crHDList;
	}

	public String getCreditVal() {
		return creditVal;
	}

	public void setCreditVal(String creditVal) {
		this.creditVal = creditVal;
	}

	public ArrayList getCrValList() {
		return crValList;
	}

	public void setCrValList(ArrayList crValList) {
		this.crValList = crValList;
	}

	public String getTotalCr() {
		return totalCr;
	}

	public void setTotalCr(String totalCr) {
		this.totalCr = totalCr;
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

	public ArrayList getDrHDList() {
		return drHDList;
	}

	public void setDrHDList(ArrayList drHDList) {
		this.drHDList = drHDList;
	}

	public String getDebitVal() {
		return debitVal;
	}

	public void setDebitVal(String debitVal) {
		this.debitVal = debitVal;
	}

	public ArrayList getDrValList() {
		return drValList;
	}

	public void setDrValList(ArrayList drValList) {
		this.drValList = drValList;
	}

	public String getTotalDr() {
		return totalDr;
	}

	public void setTotalDr(String totalDr) {
		this.totalDr = totalDr;
	}

	public String getAmtPay() {
		return amtPay;
	}

	public void setAmtPay(String amtPay) {
		this.amtPay = amtPay;
	}

	public ArrayList getArrList() {
		return arrList;
	}

	public void setArrList(ArrayList arrList) {
		this.arrList = arrList;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object[][] getPfData() {
		return pfData;
	}

	public void setPfData(Object[][] pfData) {
		this.pfData = pfData;
	}

	public Object[][] getPtData() {
		return ptData;
	}

	public void setPtData(Object[][] ptData) {
		this.ptData = ptData;
	}

	public Object[][] getEsiData() {
		return esiData;
	}

	public void setEsiData(Object[][] esiData) {
		this.esiData = esiData;
	}

	public Object[][] getCreditCodeObj() {
		return creditCodeObj;
	}

	public void setCreditCodeObj(Object[][] creditCodeObj) {
		this.creditCodeObj = creditCodeObj;
	}

	public Object[][] getDebitCodeObj() {
		return debitCodeObj;
	}

	public void setDebitCodeObj(Object[][] debitCodeObj) {
		this.debitCodeObj = debitCodeObj;
	}

	public String getHMonth() {
		return hMonth;
	}

	public void setHMonth(String month) {
		hMonth = month;
	}

	public String getEmpChkFlag() {
		return empChkFlag;
	}

	public void setEmpChkFlag(String empChkFlag) {
		this.empChkFlag = empChkFlag;
	}

	public String getEmpOnHoldFlag() {
		return empOnHoldFlag;
	}

	public void setEmpOnHoldFlag(String empOnHoldFlag) {
		this.empOnHoldFlag = empOnHoldFlag;
	}

	public String getPayinSalFlag() {
		return payinSalFlag;
	}

	public void setPayinSalFlag(String payinSalFlag) {
		this.payinSalFlag = payinSalFlag;
	}

	public String getPaidArrMonth() {
		return paidArrMonth;
	}

	public void setPaidArrMonth(String paidArrMonth) {
		this.paidArrMonth = paidArrMonth;
	}

	public String getPaidMonthName() {
		return paidMonthName;
	}

	public void setPaidMonthName(String paidMonthName) {
		this.paidMonthName = paidMonthName;
	}

	public String getPaidYear() {
		return paidYear;
	}

	public void setPaidYear(String paidYear) {
		this.paidYear = paidYear;
	}

	public String getListPaidMonth() {
		return listPaidMonth;
	}

	public void setListPaidMonth(String listPaidMonth) {
		this.listPaidMonth = listPaidMonth;
	}

	public String getListPaidYear() {
		return listPaidYear;
	}

	public void setListPaidYear(String listPaidYear) {
		this.listPaidYear = listPaidYear;
	}

	public String getListDivName() {
		return listDivName;
	}

	public void setListDivName(String listDivName) {
		this.listDivName = listDivName;
	}

	public String getListBranch() {
		return listBranch;
	}

	public void setListBranch(String listBranch) {
		this.listBranch = listBranch;
	}

	public String getListArrearCode() {
		return listArrearCode;
	}

	public void setListArrearCode(String listArrearCode) {
		this.listArrearCode = listArrearCode;
	}

	public String getListDivId() {
		return listDivId;
	}

	public void setListDivId(String listDivId) {
		this.listDivId = listDivId;
	}

	public String getListBranchId() {
		return listBranchId;
	}

	public void setListBranchId(String listBranchId) {
		this.listBranchId = listBranchId;
	}

	public String getListPayinSalFlag() {
		return listPayinSalFlag;
	}

	public void setListPayinSalFlag(String listPayinSalFlag) {
		this.listPayinSalFlag = listPayinSalFlag;
	}

	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getListStatus() {
		return listStatus;
	}

	public void setListStatus(String listStatus) {
		this.listStatus = listStatus;
	}

	public String getListLength() {
		return listLength;
	}

	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	public String getListReflectingMonthCode() {
		return listReflectingMonthCode;
	}

	public void setListReflectingMonthCode(String listReflectingMonthCode) {
		this.listReflectingMonthCode = listReflectingMonthCode;
	}

	public String getArrToMonth() {
		return arrToMonth;
	}

	public void setArrToMonth(String arrToMonth) {
		this.arrToMonth = arrToMonth;
	}

	public String getArrToYear() {
		return arrToYear;
	}

	public void setArrToYear(String arrToYear) {
		this.arrToYear = arrToYear;
	}

	public String getDeductTaxCheckBox() {
		return deductTaxCheckBox;
	}

	public void setDeductTaxCheckBox(String deductTaxCheckBox) {
		this.deductTaxCheckBox = deductTaxCheckBox;
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

	public String getEmpProcessToken() {
		return empProcessToken;
	}

	public void setEmpProcessToken(String empProcessToken) {
		this.empProcessToken = empProcessToken;
	}

	public String getEmpProcessName() {
		return empProcessName;
	}

	public void setEmpProcessName(String empProcessName) {
		this.empProcessName = empProcessName;
	}

	public String getEmpProcessId() {
		return empProcessId;
	}

	public void setEmpProcessId(String empProcessId) {
		this.empProcessId = empProcessId;
	}

	public String getEmpUpdateToken() {
		return empUpdateToken;
	}

	public void setEmpUpdateToken(String empUpdateToken) {
		this.empUpdateToken = empUpdateToken;
	}

	public String getEmpUpdateName() {
		return empUpdateName;
	}

	public void setEmpUpdateName(String empUpdateName) {
		this.empUpdateName = empUpdateName;
	}

	public String getEmpUpdateId() {
		return empUpdateId;
	}

	public void setEmpUpdateId(String empUpdateId) {
		this.empUpdateId = empUpdateId;
	}

	public String getArrPaidMonth() {
		return arrPaidMonth;
	}

	public void setArrPaidMonth(String arrPaidMonth) {
		this.arrPaidMonth = arrPaidMonth;
	}

	public String getArrPaidYear() {
		return arrPaidYear;
	}

	public void setArrPaidYear(String arrPaidYear) {
		this.arrPaidYear = arrPaidYear;
	}

	public String getDeductTaxFlag() {
		return deductTaxFlag;
	}

	public void setDeductTaxFlag(String deductTaxFlag) {
		this.deductTaxFlag = deductTaxFlag;
	}

	public String getListFromMonth() {
		return listFromMonth;
	}

	public void setListFromMonth(String listFromMonth) {
		this.listFromMonth = listFromMonth;
	}

	public String getListFromYear() {
		return listFromYear;
	}

	public void setListFromYear(String listFromYear) {
		this.listFromYear = listFromYear;
	}

	public String getListToMonth() {
		return listToMonth;
	}

	public void setListToMonth(String listToMonth) {
		this.listToMonth = listToMonth;
	}

	public String getListToYear() {
		return listToYear;
	}

	public void setListToYear(String listToYear) {
		this.listToYear = listToYear;
	}

	public String getPayinSalCheckBox() {
		return payinSalCheckBox;
	}

	public void setPayinSalCheckBox(String payinSalCheckBox) {
		this.payinSalCheckBox = payinSalCheckBox;
	}

	public String getEditEmpToken() {
		return editEmpToken;
	}

	public void setEditEmpToken(String editEmpToken) {
		this.editEmpToken = editEmpToken;
	}

	public String getEditEmpName() {
		return editEmpName;
	}

	public void setEditEmpName(String editEmpName) {
		this.editEmpName = editEmpName;
	}

	public String getEditEmpCode() {
		return editEmpCode;
	}

	public void setEditEmpCode(String editEmpCode) {
		this.editEmpCode = editEmpCode;
	}

	public String getAddEmpToken() {
		return addEmpToken;
	}

	public void setAddEmpToken(String addEmpToken) {
		this.addEmpToken = addEmpToken;
	}

	public String getAddEmpName() {
		return addEmpName;
	}

	public void setAddEmpName(String addEmpName) {
		this.addEmpName = addEmpName;
	}

	public String getAddEmpCode() {
		return addEmpCode;
	}

	public void setAddEmpCode(String addEmpCode) {
		this.addEmpCode = addEmpCode;
	}

	public String getListEmpCode() {
		return listEmpCode;
	}

	public void setListEmpCode(String listEmpCode) {
		this.listEmpCode = listEmpCode;
	}

	public String getOnholdFlag() {
		return onholdFlag;
	}

	public void setOnholdFlag(String onholdFlag) {
		this.onholdFlag = onholdFlag;
	}

	public String getHPromCode() {
		return hPromCode;
	}

	public void setHPromCode(String promCode) {
		hPromCode = promCode;
	}

	public String getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(String totalEmployee) {
		this.totalEmployee = totalEmployee;
	}

}