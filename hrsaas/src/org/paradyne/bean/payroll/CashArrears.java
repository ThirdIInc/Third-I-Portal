/**
 * 
 */
package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Pankaj_Jain
 *
 */
public class CashArrears extends BeanBase {

	private String arrCode;
	private String eArrMonth;
	private String monthName;
	private String eArrYear;
	private String divFlag;
	private String divCode = "";
	private String divName = "";
	private String typeFlag;
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
	private String eId;
	private String eToken;
	private String eName;
	private String month;
	private String year;
	private String salDays;
	private String creditCode;
	private String creditName;
	private ArrayList crHDList;	
	private String totalCr;
	private String amtPay;
	private boolean flag = false;
	private Object[][] creditCodeObj;
	private Object[][] debitCodeObj;
	private String fromTotRec="";
	private String toTotRec="";
	private String hdPage;
	private String hdProcess;
	private String arrDays;
	private String hMonth;
	private String promotionCode;
	private String monthRef;
	private String status;
	private String proccessDate;
	private String empChkFlag = "false";
	private String empOnHoldFlag;
	private String payinSalFlag;
	private String savedFlag;
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
	public String getTotalCr() {
		return totalCr;
	}
	public void setTotalCr(String totalCr) {
		this.totalCr = totalCr;
	}
	public String getAmtPay() {
		return amtPay;
	}
	public void setAmtPay(String amtPay) {
		this.amtPay = amtPay;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
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
	public String getArrDays() {
		return arrDays;
	}
	public void setArrDays(String arrDays) {
		this.arrDays = arrDays;
	}
	public String getHMonth() {
		return hMonth;
	}
	public void setHMonth(String month) {
		hMonth = month;
	}
	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
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
	public String getProccessDate() {
		return proccessDate;
	}
	public void setProccessDate(String proccessDate) {
		this.proccessDate = proccessDate;
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
	public String getSavedFlag() {
		return savedFlag;
	}
	public void setSavedFlag(String savedFlag) {
		this.savedFlag = savedFlag;
	}
}
