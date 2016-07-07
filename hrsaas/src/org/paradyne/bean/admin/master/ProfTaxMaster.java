package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ProfTaxMaster  extends BeanBase {
	
	private String ptCode="";
	private String effDate="";
	private String locName;
	private String ptLocCode;
	private String ptDebitCode;
	private String ptDebitName;
	private String frmAmount="";
	private String toAmount="";
	private String fixedAmt="";
	private String varAmt="";
	private String varMonth="";
	private String type="";
	private String taxId="";
	private String paraID="";
	private String ptDtlCode="";
	
	private String chkEdit="";
	private String isFromEdit="";
	
	private String srNo;
	private String taxDtlCode;
	private String famt;
	private String tamt;
	private String fdamt;
	private String vamt;
	private String vmth;
	private String tableLength="0";
	private String dupVarmonth;
	
	ArrayList<Object> taxList=null;
	
	//ADDED BY REEBA 
	private String myPage;
	private String hiddencode;
	private String show;
	private String listLength = "false";
	private String totalRecords = "";
	ArrayList iteratorlist;
 	private String hdeleteCode="";
 	
 	private String ptCodeItr="";
	private String effectiveDateItr="";
	private String locationItr = "";
	private String locationCodeItr = "";
	
	private String reportType = "pdf";
	
	
	public void setTaxList(ArrayList<Object> taxList) {
		this.taxList = taxList;
	}
	public String getParaID() {
		return paraID;
	}
	public void setParaID(String paraID) {
		this.paraID = paraID;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getPtCode() {
		return ptCode;
	}
	public void setPtCode(String ptCode) {
		this.ptCode = ptCode;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getPtLocCode() {
		return ptLocCode;
	}
	public void setPtLocCode(String ptLocCode) {
		this.ptLocCode = ptLocCode;
	}
	public String getPtDebitCode() {
		return ptDebitCode;
	}
	public void setPtDebitCode(String ptDebitCode) {
		this.ptDebitCode = ptDebitCode;
	}
	public String getPtDebitName() {
		return ptDebitName;
	}
	public void setPtDebitName(String ptDebitName) {
		this.ptDebitName = ptDebitName;
	}
	public String getFixedAmt() {
		return fixedAmt;
	}
	public void setFixedAmt(String fixedAmt) {
		this.fixedAmt = fixedAmt;
	}
	public String getVarAmt() {
		return varAmt;
	}
	public void setVarAmt(String varAmt) {
		this.varAmt = varAmt;
	}
	public String getVarMonth() {
		return varMonth;
	}
	public void setVarMonth(String varMonth) {
		this.varMonth = varMonth;
	}
	public String getFrmAmount() {
		return frmAmount;
	}
	public void setFrmAmount(String frmAmount) {
		this.frmAmount = frmAmount;
	}
	public String getToAmount() {
		return toAmount;
	}
	public void setToAmount(String toAmount) {
		this.toAmount = toAmount;
	}
	public String getIsFromEdit() {
		return isFromEdit;
	}
	public void setIsFromEdit(String isFromEdit) {
		this.isFromEdit = isFromEdit;
	}
	public String getChkEdit() {
		return chkEdit;
	}
	public void setChkEdit(String chkEdit) {
		this.chkEdit = chkEdit;
	}
	public ArrayList getTaxList() {
		return taxList;
	}
	
	public String getPtDtlCode() {
		return ptDtlCode;
	}
	public void setPtDtlCode(String ptDtlCode) {
		this.ptDtlCode = ptDtlCode;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getTaxDtlCode() {
		return taxDtlCode;
	}
	public void setTaxDtlCode(String taxDtlCode) {
		this.taxDtlCode = taxDtlCode;
	}
	public String getFamt() {
		return famt;
	}
	public void setFamt(String famt) {
		this.famt = famt;
	}
	public String getTamt() {
		return tamt;
	}
	public void setTamt(String tamt) {
		this.tamt = tamt;
	}
	public String getFdamt() {
		return fdamt;
	}
	public void setFdamt(String fdamt) {
		this.fdamt = fdamt;
	}
	public String getVamt() {
		return vamt;
	}
	public void setVamt(String vamt) {
		this.vamt = vamt;
	}
	public String getVmth() {
		return vmth;
	}
	public void setVmth(String vmth) {
		this.vmth = vmth;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getDupVarmonth() {
		return dupVarmonth;
	}
	public void setDupVarmonth(String dupVarmonth) {
		this.dupVarmonth = dupVarmonth;
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
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getPtCodeItr() {
		return ptCodeItr;
	}
	public void setPtCodeItr(String ptCodeItr) {
		this.ptCodeItr = ptCodeItr;
	}
	public String getEffectiveDateItr() {
		return effectiveDateItr;
	}
	public void setEffectiveDateItr(String effectiveDateItr) {
		this.effectiveDateItr = effectiveDateItr;
	}
	public String getLocationItr() {
		return locationItr;
	}
	public void setLocationItr(String locationItr) {
		this.locationItr = locationItr;
	}
	public String getLocationCodeItr() {
		return locationCodeItr;
	}
	public void setLocationCodeItr(String locationCodeItr) {
		this.locationCodeItr = locationCodeItr;
	}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	
	

}
