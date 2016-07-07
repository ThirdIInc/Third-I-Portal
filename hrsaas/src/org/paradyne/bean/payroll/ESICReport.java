package org.paradyne.bean.payroll;

import java.io.Serializable;

import org.paradyne.lib.BeanBase;

public class ESICReport extends BeanBase implements Serializable {

	private Object[][] pfDataObj=null;
	private String typeCode="";
	private String typeName="";
	private String month="";
	private String year="";
	private String payBillNo="";
	private String payBillName="";
	private String divisionAbbrevation = "";
	//Added on Date 25.03.2008 
	private String divCode="";
	private String divName="";
	private String brnCode="";
	private String brnName="";
	private String deptCode="";
	private String deptName="";
	private String onHold="";
	private String reportType="";
	private String checkFlag="N";
	private String checkEdliSal="N";
	private String reportOption="";
	private String pfSetFlag="false";
	private String pfArrFlag="false";
	private String pfSalFlag="false";
	private String pfSalWithArrFlag="false";
	private String pfWithoutSal="false";
	private String tdsSalFlag="false";
	private String tdsArrFlag="false";
	private String tdsSetFlag="false";
	private String tdsSalWithArrear="false";
	private String tdsWithoutSal="false";
	private String esiSalFlag="false";
	private String esiSetFlag="false";
	private String esiSalWithArrearFlag="false";
	private String esiSalWithoutArrearFlag="false";
	private String esiArrearFlag="false";
	private String edliSalFlag="false";
	private String edliArrear="false";
	private String edliSetFlag="false";
	private String edliSalWithArrFlag="false";
	private String edliSalWithoutArrFlag="false";
	private String chkBrnDept="N";
	
	
	private String branchFlag="";
	private String deptFlag="";
	
	private Object[][] salTotal;
	private Object[][] arrTotal;
	private Object[][] settTotal;
	
	private String reportAction = "";
	private String cadreCode = "";
	private String cadreName = "";
	
	
	//  added on 06/08/2010
	 
	private String divAdd;
	private String divEsiCode;
	
	
	public String getBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}
	public String getDeptFlag() {
		return deptFlag;
	}
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}
	public String getPfSetFlag() {
		return pfSetFlag;
	}
	public void setPfSetFlag(String pfSetFlag) {
		this.pfSetFlag = pfSetFlag;
	}
	public String getPfArrFlag() {
		return pfArrFlag;
	}
	public void setPfArrFlag(String pfArrFlag) {
		this.pfArrFlag = pfArrFlag;
	}
	public String getPfSalFlag() {
		return pfSalFlag;
	}
	public void setPfSalFlag(String pfSalFlag) {
		this.pfSalFlag = pfSalFlag;
	}
	public String getPfSalWithArrFlag() {
		return pfSalWithArrFlag;
	}
	public void setPfSalWithArrFlag(String pfSalWithArrFlag) {
		this.pfSalWithArrFlag = pfSalWithArrFlag;
	}
	public String getReportOption() {
		return reportOption;
	}
	public void setReportOption(String reportOption) {
		this.reportOption = reportOption;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
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
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getCheckEdliSal() {
		return checkEdliSal;
	}
	public void setCheckEdliSal(String checkEdliSal) {
		this.checkEdliSal = checkEdliSal;
	}
	public String getPfWithoutSal() {
		return pfWithoutSal;
	}
	public void setPfWithoutSal(String pfWithoutSal) {
		this.pfWithoutSal = pfWithoutSal;
	}
	public String getTdsSalFlag() {
		return tdsSalFlag;
	}
	public void setTdsSalFlag(String tdsSalFlag) {
		this.tdsSalFlag = tdsSalFlag;
	}
	public String getTdsArrFlag() {
		return tdsArrFlag;
	}
	public void setTdsArrFlag(String tdsArrFlag) {
		this.tdsArrFlag = tdsArrFlag;
	}
	public String getTdsSetFlag() {
		return tdsSetFlag;
	}
	public void setTdsSetFlag(String tdsSetFlag) {
		this.tdsSetFlag = tdsSetFlag;
	}
	public String getTdsSalWithArrear() {
		return tdsSalWithArrear;
	}
	public void setTdsSalWithArrear(String tdsSalWithArrear) {
		this.tdsSalWithArrear = tdsSalWithArrear;
	}
	public String getTdsWithoutSal() {
		return tdsWithoutSal;
	}
	public void setTdsWithoutSal(String tdsWithoutSal) {
		this.tdsWithoutSal = tdsWithoutSal;
	}
	public String getEsiSalFlag() {
		return esiSalFlag;
	}
	public void setEsiSalFlag(String esiSalFlag) {
		this.esiSalFlag = esiSalFlag;
	}
	public String getEsiSetFlag() {
		return esiSetFlag;
	}
	public void setEsiSetFlag(String esiSetFlag) {
		this.esiSetFlag = esiSetFlag;
	}
	public String getEsiSalWithArrearFlag() {
		return esiSalWithArrearFlag;
	}
	public void setEsiSalWithArrearFlag(String esiSalWithArrearFlag) {
		this.esiSalWithArrearFlag = esiSalWithArrearFlag;
	}
	public String getEsiSalWithoutArrearFlag() {
		return esiSalWithoutArrearFlag;
	}
	public void setEsiSalWithoutArrearFlag(String esiSalWithoutArrearFlag) {
		this.esiSalWithoutArrearFlag = esiSalWithoutArrearFlag;
	}
	public String getEsiArrearFlag() {
		return esiArrearFlag;
	}
	public void setEsiArrearFlag(String esiArrearFlag) {
		this.esiArrearFlag = esiArrearFlag;
	}
	public String getEdliSalFlag() {
		return edliSalFlag;
	}
	public void setEdliSalFlag(String edliSalFlag) {
		this.edliSalFlag = edliSalFlag;
	}
	public String getEdliArrear() {
		return edliArrear;
	}
	public void setEdliArrear(String edliArrear) {
		this.edliArrear = edliArrear;
	}
	public String getEdliSetFlag() {
		return edliSetFlag;
	}
	public void setEdliSetFlag(String edliSetFlag) {
		this.edliSetFlag = edliSetFlag;
	}
	public String getEdliSalWithArrFlag() {
		return edliSalWithArrFlag;
	}
	public void setEdliSalWithArrFlag(String edliSalWithArrFlag) {
		this.edliSalWithArrFlag = edliSalWithArrFlag;
	}
	public String getEdliSalWithoutArrFlag() {
		return edliSalWithoutArrFlag;
	}
	public void setEdliSalWithoutArrFlag(String edliSalWithoutArrFlag) {
		this.edliSalWithoutArrFlag = edliSalWithoutArrFlag;
	}
	public String getChkBrnDept() {
		return chkBrnDept;
	}
	public void setChkBrnDept(String chkBrnDept) {
		this.chkBrnDept = chkBrnDept;
	}
	public Object[][] getPfDataObj() {
		return pfDataObj;
	}
	public void setPfDataObj(Object[][] pfDataObj) {
		this.pfDataObj = pfDataObj;
	}
	public Object[][] getSalTotal() {
		return salTotal;
	}
	public void setSalTotal(Object[][] salTotal) {
		this.salTotal = salTotal;
	}
	public Object[][] getArrTotal() {
		return arrTotal;
	}
	public void setArrTotal(Object[][] arrTotal) {
		this.arrTotal = arrTotal;
	}
	public Object[][] getSettTotal() {
		return settTotal;
	}
	public void setSettTotal(Object[][] settTotal) {
		this.settTotal = settTotal;
	}
	public String getDivAdd() {
		return divAdd;
	}
	public void setDivAdd(String divAdd) {
		this.divAdd = divAdd;
	}
	public String getDivEsiCode() {
		return divEsiCode;
	}
	public void setDivEsiCode(String divEsiCode) {
		this.divEsiCode = divEsiCode;
	}
	public String getDivisionAbbrevation() {
		return divisionAbbrevation;
	}
	public void setDivisionAbbrevation(String divisionAbbrevation) {
		this.divisionAbbrevation = divisionAbbrevation;
	}
	public String getPayBillName() {
		return payBillName;
	}
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
	public String getCadreCode() {
		return cadreCode;
	}
	public void setCadreCode(String cadreCode) {
		this.cadreCode = cadreCode;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	
	
	
}
