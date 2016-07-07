/**
 * 
 */
package org.paradyne.bean.payroll.pension;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author ritac
 *
 */
public class FormulaBuilderPension extends BeanBase {

	private String frmbuildCode;
	private String frmbuildName;
	private String salHead;
	private String salCode;
	private String salType;
	private String salAmt;
	private String salCode1;
	private String salHead1;
	private String salType1;
	private String salAmt1;
	private String frmdtlCode;
	private String salOrder;
	private String srNo;
	private String salTy1;
	private String salprior;
	private String salPrior1;

	private String chkEdit;
	private String paraID;
	private String isFromEdit;
	private String salDetail = "0";
	private String lengthp;
	private String priorCode;

	// formula calculation fields

	private String frmCalc;
	private String salAbr;
	private String salHeadCalc;
	private String salAbbrCalc;
	private String salCodeCalc;
	private String salCd;
	private String notInList = "0";
	//private String tableLength="";

	private String frmId;

	ArrayList<Object> frmList = null;

	public String getFrmbuildCode() {
		return frmbuildCode;
	}

	public void setFrmbuildCode(String frmbuildCode) {
		this.frmbuildCode = frmbuildCode;
	}

	public String getFrmbuildName() {
		return frmbuildName;
	}

	public void setFrmbuildName(String frmbuildName) {
		this.frmbuildName = frmbuildName;
	}

	public String getSalHead() {
		return salHead;
	}

	public void setSalHead(String salHead) {
		this.salHead = salHead;
	}

	public String getSalCode() {
		return salCode;
	}

	public void setSalCode(String salCode) {
		this.salCode = salCode;
	}

	public String getSalType() {
		return salType;
	}

	public void setSalType(String salType) {
		this.salType = salType;
	}

	public String getSalAmt() {
		return salAmt;
	}

	public void setSalAmt(String salAmt) {
		this.salAmt = salAmt;
	}

	public String getSalCode1() {
		return salCode1;
	}

	public void setSalCode1(String salCode1) {
		this.salCode1 = salCode1;
	}

	public String getSalHead1() {
		return salHead1;
	}

	public void setSalHead1(String salHead1) {
		this.salHead1 = salHead1;
	}

	public String getSalType1() {
		return salType1;
	}

	public void setSalType1(String salType1) {
		this.salType1 = salType1;
	}

	public String getSalAmt1() {
		return salAmt1;
	}

	public void setSalAmt1(String salAmt1) {
		this.salAmt1 = salAmt1;
	}

	public ArrayList<Object> getFrmList() {
		return frmList;
	}

	public void setFrmList(ArrayList<Object> frmList) {
		this.frmList = frmList;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getFrmdtlCode() {
		return frmdtlCode;
	}

	public void setFrmdtlCode(String frmdtlCode) {
		this.frmdtlCode = frmdtlCode;
	}

	public String getSalOrder() {
		return salOrder;
	}

	public void setSalOrder(String salOrder) {
		this.salOrder = salOrder;
	}

	public String getChkEdit() {
		return chkEdit;
	}

	public void setChkEdit(String chkEdit) {
		this.chkEdit = chkEdit;
	}

	public String getParaID() {
		return paraID;
	}

	public void setParaID(String paraID) {
		this.paraID = paraID;
	}

	public String getIsFromEdit() {
		return isFromEdit;
	}

	public void setIsFromEdit(String isFromEdit) {
		this.isFromEdit = isFromEdit;
	}

	public String getFrmCalc() {
		return frmCalc;
	}

	public void setFrmCalc(String frmCalc) {
		this.frmCalc = frmCalc;
	}

	public String getSalAbr() {
		return salAbr;
	}

	public void setSalAbr(String salAbr) {
		this.salAbr = salAbr;
	}

	public String getSalHeadCalc() {
		return salHeadCalc;
	}

	public void setSalHeadCalc(String salHeadCalc) {
		this.salHeadCalc = salHeadCalc;
	}

	public String getSalAbbrCalc() {
		return salAbbrCalc;
	}

	public void setSalAbbrCalc(String salAbbrCalc) {
		this.salAbbrCalc = salAbbrCalc;
	}

	public String getSalCodeCalc() {
		return salCodeCalc;
	}

	public void setSalCodeCalc(String salCodeCalc) {
		this.salCodeCalc = salCodeCalc;
	}

	public String getSalCd() {
		return salCd;
	}

	public void setSalCd(String salCd) {
		this.salCd = salCd;
	}

	public String getNotInList() {
		return notInList;
	}

	public void setNotInList(String notInList) {
		this.notInList = notInList;
	}

	public String getSalTy1() {
		return salTy1;
	}

	public void setSalTy1(String salTy1) {
		this.salTy1 = salTy1;
	}

	public String getSalDetail() {
		return salDetail;
	}

	public void setSalDetail(String salDetail) {
		this.salDetail = salDetail;
	}

	public String getSalprior() {
		return salprior;
	}

	public void setSalprior(String salprior) {
		this.salprior = salprior;
	}

	public String getSalPrior1() {
		return salPrior1;
	}

	public void setSalPrior1(String salPrior1) {
		this.salPrior1 = salPrior1;
	}

	public String getLengthp() {
		return lengthp;
	}

	public void setLengthp(String lengthp) {
		this.lengthp = lengthp;
	}

	public String getPriorCode() {
		return priorCode;
	}

	public void setPriorCode(String priorCode) {
		this.priorCode = priorCode;
	}

	public String getFrmId() {
		return frmId;
	}

	public void setFrmId(String frmId) {
		this.frmId = frmId;
	}

	/*public String getTableLength() {
		return tableLength;
	}

	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}*/
}
