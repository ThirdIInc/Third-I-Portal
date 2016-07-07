package org.paradyne.bean.payroll.incometax;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TaxSlab extends BeanBase {
	private String show;
	private String fromYear="";
	private String toYear="";
	private String frmAmount="";
	private String toAmount="";
	private String taxPercentage="";
	private String type="";
	private String taxId="";
	private String paraID="";
	
	private String chkEdit="";
	private String isFromEdit="";
	
	ArrayList taxSlabList=null;
	ArrayList tdsSlabArray=null;
	
	//Added by Prashant starts
	private String myPage;
	private String taxFromYearItt;
	private String taxToYearItt;
	private String taxEmpTypeItt;
	private String taxEmpGenderItt;
	ArrayList iteratorlist;
	
	//Added by Prashant ends
	
	//Added by Ganesh 19-09-2011
	boolean tdsSlabTableFlag = false;
	ArrayList taxSlabWomenList=null;
	ArrayList taxSlabSeniorList = null;
	
	private String frmAmountWomen="";
	private String toAmountWomen="";
	private String taxPercentageWomen="";
	private String taxSlabWomenListId="";
	private String frmAmountSenior ="";
	private String toAmountSenior ="";
	private String taxPercentageSenior = "";
	private String taxSlabSeniorListId = "";
	private String taxSlabManListId="";
	
	private String myPageInProcess = "";
	boolean reportFlag = false ;
	boolean tdsListLength = false ;
	
	private String modeLength="false";
	private String totalRecords="";
	
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getMyPageInProcess() {
		return myPageInProcess;
	}
	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	public String getTaxSlabWomenListId() {
		return taxSlabWomenListId;
	}
	public void setTaxSlabWomenListId(String taxSlabWomenListId) {
		this.taxSlabWomenListId = taxSlabWomenListId;
	}
	public String getFrmAmountSenior() {
		return frmAmountSenior;
	}
	public void setFrmAmountSenior(String frmAmountSenior) {
		this.frmAmountSenior = frmAmountSenior;
	}
	public String getToAmountSenior() {
		return toAmountSenior;
	}
	public void setToAmountSenior(String toAmountSenior) {
		this.toAmountSenior = toAmountSenior;
	}
	public String getTaxPercentageSenior() {
		return taxPercentageSenior;
	}
	public void setTaxPercentageSenior(String taxPercentageSenior) {
		this.taxPercentageSenior = taxPercentageSenior;
	}
	public String getTaxSlabSeniorListId() {
		return taxSlabSeniorListId;
	}
	public void setTaxSlabSeniorListId(String taxSlabSeniorListId) {
		this.taxSlabSeniorListId = taxSlabSeniorListId;
	}
	public String getFrmAmountWomen() {
		return frmAmountWomen;
	}
	public void setFrmAmountWomen(String frmAmountWomen) {
		this.frmAmountWomen = frmAmountWomen;
	}
	public String getToAmountWomen() {
		return toAmountWomen;
	}
	public void setToAmountWomen(String toAmountWomen) {
		this.toAmountWomen = toAmountWomen;
	}
	public String getTaxPercentageWomen() {
		return taxPercentageWomen;
	}
	public void setTaxPercentageWomen(String taxPercentageWomen) {
		this.taxPercentageWomen = taxPercentageWomen;
	}
	public ArrayList getTaxSlabList() {
		return taxSlabList;
	}
	public void setTaxSlabList(ArrayList taxSlabList) {
		this.taxSlabList = taxSlabList;
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
	public String getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(String taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	
	
	
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
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
	public ArrayList getTdsSlabArray() {
		return tdsSlabArray;
	}
	public void setTdsSlabArray(ArrayList tdsSlabArray) {
		this.tdsSlabArray = tdsSlabArray;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getTaxFromYearItt() {
		return taxFromYearItt;
	}
	public void setTaxFromYearItt(String taxFromYearItt) {
		this.taxFromYearItt = taxFromYearItt;
	}
	public String getTaxToYearItt() {
		return taxToYearItt;
	}
	public void setTaxToYearItt(String taxToYearItt) {
		this.taxToYearItt = taxToYearItt;
	}
	public String getTaxEmpTypeItt() {
		return taxEmpTypeItt;
	}
	public void setTaxEmpTypeItt(String taxEmpTypeItt) {
		this.taxEmpTypeItt = taxEmpTypeItt;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getTaxEmpGenderItt() {
		return taxEmpGenderItt;
	}
	public void setTaxEmpGenderItt(String taxEmpGenderItt) {
		this.taxEmpGenderItt = taxEmpGenderItt;
	}
	public boolean isTdsSlabTableFlag() {
		return tdsSlabTableFlag;
	}
	public void setTdsSlabTableFlag(boolean tdsSlabTableFlag) {
		this.tdsSlabTableFlag = tdsSlabTableFlag;
	}
	public ArrayList getTaxSlabWomenList() {
		return taxSlabWomenList;
	}
	public void setTaxSlabWomenList(ArrayList taxSlabWomenList) {
		this.taxSlabWomenList = taxSlabWomenList;
	}
	public ArrayList getTaxSlabSeniorList() {
		return taxSlabSeniorList;
	}
	public void setTaxSlabSeniorList(ArrayList taxSlabSeniorList) {
		this.taxSlabSeniorList = taxSlabSeniorList;
	}
	public boolean isReportFlag() {
		return reportFlag;
	}
	public void setReportFlag(boolean reportFlag) {
		this.reportFlag = reportFlag;
	}
	public String getTaxSlabManListId() {
		return taxSlabManListId;
	}
	public void setTaxSlabManListId(String taxSlabManListId) {
		this.taxSlabManListId = taxSlabManListId;
	}
	public boolean isTdsListLength() {
		return tdsListLength;
	}
	public void setTdsListLength(boolean tdsListLength) {
		this.tdsListLength = tdsListLength;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	

	

}
