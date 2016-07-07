package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;
import java.util.ArrayList;
import java.io.Serializable;

public class EmpPerquisites extends BeanBase implements Serializable{

	private String perqCode = ""; 
	private String totalAmt = "";
	private String TotalPreAmt = "";
	private String PreCommisionamount =	"";
	private String empId			= "";
	private String empName			= "";
	private String department		= "";
	private String center			= "";
	private String rank				= "";
	private String trade			= "";	
	private String amount			= "";	
	private String headerId			= "";
	private String headName			= "";
	private String amountText		= "";
	private String chkBox			= "";
	private String empToken =	"";
	private String empperq =	""; 
	private String empCenter =	"";
	private String empRank =	"";
	private ArrayList att = null;
	private String salHead =	"";
	private String chkFlag =	"";
	private String chkSubmit =	"";
	private String chkdata =	"";
	private String period="";
	private String annualAmt="";
	private ArrayList	arrayList	=	null;
	
	//Added by Prashant
	
	private String empAccountNo="";
	private String empPanNo="";
	private String empDeptId="";
	private String empDeptName="";
	private String joiningDate="";
	private String empGradeId="";
	private String empGradeName="";
	ArrayList salHeaderList=null;
	private String perqCodeItt="";
	private String perqNameItt="";
	private String perqPeriodItt="";
	private String perqAmountItt="";
	private String fromYear="";
	private String toYear="";
	private boolean perquisiteFlag=false;
	
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public ArrayList getSalHeaderList() {
		return salHeaderList;
	}
	public void setSalHeaderList(ArrayList salHeaderList) {
		this.salHeaderList = salHeaderList;
	}
	public ArrayList getArrayList() {
		return arrayList;
	}
	public void setArrayList(ArrayList arrayList) {
		this.arrayList = arrayList;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}	
	
	public String getAmountText() {
		return amountText;
	}
	public void setAmountText(String amountText) {
		this.amountText = amountText;
	}
	public String getChkBox() {
		return chkBox;
	}
	public void setChkBox(String chkBox) {
		this.chkBox = chkBox;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getHeaderId() {
		return headerId;
	}
	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}
	public ArrayList getAtt() {
		return att;
	}
	public void setAtt(ArrayList att) {
		this.att = att;
	}
	public String getChkdata() {
		return chkdata;
	}
	public void setChkdata(String chkdata) {
		this.chkdata = chkdata;
	}
	public String getChkFlag() {
		return chkFlag;
	}
	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}
	public String getChkSubmit() {
		return chkSubmit;
	}
	public void setChkSubmit(String chkSubmit) {
		this.chkSubmit = chkSubmit;
	}
	public String getEmpCenter() {
		return empCenter;
	}
	public void setEmpCenter(String empCenter) {
		this.empCenter = empCenter;
	}
	
	public String getEmpRank() {
		return empRank;
	}
	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getSalHead() {
		return salHead;
	}
	public void setSalHead(String salHead) {
		this.salHead = salHead;
	}
	public String getPreCommisionamount() {
		return PreCommisionamount;
	}
	public void setPreCommisionamount(String preCommisionamount) {
		PreCommisionamount = preCommisionamount;
	}
	public String getTotalPreAmt() {
		return TotalPreAmt;
	}
	public void setTotalPreAmt(String totalPreAmt) {
		TotalPreAmt = totalPreAmt;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public String getAnnualAmt() {
		return annualAmt;
	}
	public void setAnnualAmt(String annualAmt) {
		this.annualAmt = annualAmt;
	}
	public String getPerqCode() {
		return perqCode;
	}
	public void setPerqCode(String perqCode) {
		this.perqCode = perqCode;
	}
	public String getEmpperq() {
		return empperq;
	}
	public void setEmpperq(String empperq) {
		this.empperq = empperq;
	}
	public String getEmpAccountNo() {
		return empAccountNo;
	}
	public void setEmpAccountNo(String empAccountNo) {
		this.empAccountNo = empAccountNo;
	}
	public String getEmpPanNo() {
		return empPanNo;
	}
	public void setEmpPanNo(String empPanNo) {
		this.empPanNo = empPanNo;
	}
	public String getEmpDeptId() {
		return empDeptId;
	}
	public void setEmpDeptId(String empDeptId) {
		this.empDeptId = empDeptId;
	}
	public String getEmpDeptName() {
		return empDeptName;
	}
	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getEmpGradeId() {
		return empGradeId;
	}
	public void setEmpGradeId(String empGradeId) {
		this.empGradeId = empGradeId;
	}
	public String getEmpGradeName() {
		return empGradeName;
	}
	public void setEmpGradeName(String empGradeName) {
		this.empGradeName = empGradeName;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public boolean isPerquisiteFlag() {
		return perquisiteFlag;
	}
	public void setPerquisiteFlag(boolean perquisiteFlag) {
		this.perquisiteFlag = perquisiteFlag;
	}
	public String getPerqCodeItt() {
		return perqCodeItt;
	}
	public void setPerqCodeItt(String perqCodeItt) {
		this.perqCodeItt = perqCodeItt;
	}
	public String getPerqNameItt() {
		return perqNameItt;
	}
	public void setPerqNameItt(String perqNameItt) {
		this.perqNameItt = perqNameItt;
	}
	public String getPerqAmountItt() {
		return perqAmountItt;
	}
	public void setPerqAmountItt(String perqAmountItt) {
		this.perqAmountItt = perqAmountItt;
	}
	public String getPerqPeriodItt() {
		return perqPeriodItt;
	}
	public void setPerqPeriodItt(String perqPeriodItt) {
		this.perqPeriodItt = perqPeriodItt;
	}	
	
}
