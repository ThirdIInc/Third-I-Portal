package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;
import java.util.ArrayList;

import java.io.Serializable;

public class EmpDebit extends BeanBase implements Serializable{

	private String debitCode		=	"";
	private String TotalAmt=	"";
	private String TotalPreAmt=	"";
	private String PreCommisionamount=	"";
	private String empId			=	"";
	private String empName			=	"";
	private String department		=	"";
	private String center			=	"";
	private String rank				=	"";
	private String trade			=	"";	
	private String amount			=	"";	
	private String headerId			=	"";
	private String headName			=	"";
	private String amountText		=	"";
	private String chkBox			=	"";
	private String empToken =	"";
	private String empDebit =	"";
	private String empCenter =	"";
	private String empRank =	"";
	private ArrayList att = null;
	private String salHead =	"";
	private String chkFlag =	"";
	private String chkSubmit =	"";
	private String chkdata =	"";
	private String flag = "";
	private String period="";
	private String annuallAmt="";
	
	
	private ArrayList	arrayList	=	null;
	
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
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
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
	public String getEmpDebit() {
		return empDebit;
	}
	public void setEmpDebit(String empDebit) {
		this.empDebit = empDebit;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getPreCommisionamount() {
		return PreCommisionamount;
	}
	public void setPreCommisionamount(String preCommisionamount) {
		PreCommisionamount = preCommisionamount;
	}
	public String getTotalAmt() {
		return TotalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		TotalAmt = totalAmt;
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
	public String getAnnuallAmt() {
		return annuallAmt;
	}
	public void setAnnuallAmt(String annuallAmt) {
		this.annuallAmt = annuallAmt;
	}	
	
}
