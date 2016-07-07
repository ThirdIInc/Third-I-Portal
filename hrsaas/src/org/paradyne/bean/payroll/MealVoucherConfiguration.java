package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;
import java.io.Serializable;
import java.util.ArrayList;

public class MealVoucherConfiguration extends BeanBase {
	private String creditHead="";
	private String freqencyOfChange="";
	private String checkValidation ="";
	private String creditCode="";
	private String mealvoucherconfID="";
	private String itrcreditHead="";
	private String itrfreqofChange="";
	private String mealvouconfId="";
	private ArrayList mealvoucherconfList=null;
	private String hdeleteCode="";
	private String ittSrN0="";
	private String coupenCode="";
	private String coupenHead="";
	private String debitCode="";
	private String debitHead="";
	
	private String fromYear="";
	private String toYear = "";
	private String mealVoucherAmtLimit = "";
	private String mealVoucherAmtDeclare = "";
	private String fromPeriod ="";
	private String toPeriod = "";
	
	 
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
	public String getMealVoucherAmtLimit() {
		return mealVoucherAmtLimit;
	}
	public void setMealVoucherAmtLimit(String mealVoucherAmtLimit) {
		this.mealVoucherAmtLimit = mealVoucherAmtLimit;
	}
	public String getMealVoucherAmtDeclare() {
		return mealVoucherAmtDeclare;
	}
	public void setMealVoucherAmtDeclare(String mealVoucherAmtDeclare) {
		this.mealVoucherAmtDeclare = mealVoucherAmtDeclare;
	}
	public String getFromPeriod() {
		return fromPeriod;
	}
	public void setFromPeriod(String fromPeriod) {
		this.fromPeriod = fromPeriod;
	}
	public String getToPeriod() {
		return toPeriod;
	}
	public void setToPeriod(String toPeriod) {
		this.toPeriod = toPeriod;
	}
	public String getCoupenCode() {
		return coupenCode;
	}
	public void setCoupenCode(String coupenCode) {
		this.coupenCode = coupenCode;
	}
	public String getCoupenHead() {
		return coupenHead;
	}
	public void setCoupenHead(String coupenHead) {
		this.coupenHead = coupenHead;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getDebitHead() {
		return debitHead;
	}
	public void setDebitHead(String debitHead) {
		this.debitHead = debitHead;
	}
	public String getIttSrN0() {
		return ittSrN0;
	}
	public void setIttSrN0(String ittSrN0) {
		this.ittSrN0 = ittSrN0;
	}
	public String getItrcreditHead() {
		return itrcreditHead;
	}
	public void setItrcreditHead(String itrcreditHead) {
		this.itrcreditHead = itrcreditHead;
	}
	public String getItrfreqofChange() {
		return itrfreqofChange;
	}
	public void setItrfreqofChange(String itrfreqofChange) {
		this.itrfreqofChange = itrfreqofChange;
	}
	public String getMealvouconfId() {
		return mealvouconfId;
	}
	public void setMealvouconfId(String mealvouconfId) {
		this.mealvouconfId = mealvouconfId;
	}
	public String getMealvoucherconfID() {
		return mealvoucherconfID;
	}
	public void setMealvoucherconfID(String mealvoucherconfID) {
		this.mealvoucherconfID = mealvoucherconfID;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCheckValidation() {
		return checkValidation;
	}
	public void setCheckValidation(String checkValidation) {
		this.checkValidation = checkValidation;
	}
	public String getCreditHead() {
		return creditHead;
	}
	public void setCreditHead(String creditHead) {
		this.creditHead = creditHead;
	}
	public String getFreqencyOfChange() {
		return freqencyOfChange;
	}
	public void setFreqencyOfChange(String freqencyOfChange) {
		this.freqencyOfChange = freqencyOfChange;
	}
	public ArrayList getMealvoucherconfList() {
		return mealvoucherconfList;
	}
	public void setMealvoucherconfList(ArrayList mealvoucherconfList) {
		this.mealvoucherconfList = mealvoucherconfList;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

}
