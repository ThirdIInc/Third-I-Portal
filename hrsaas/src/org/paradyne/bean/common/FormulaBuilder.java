/**
 * 
 */
package org.paradyne.bean.common;

import org.paradyne.lib.BeanBase;

/**
 * @author sunil
 *
 */
public class FormulaBuilder extends BeanBase {
	
	private String  formulaCode = "";
	private String  formulaName = "";
	
	private String creditAbbr = "";
	private String  creditCode = "";
	
	private String debitAbbr = "";
	private String debitCode = "";
	
	private String formula = "";
	private String actualFormula = "";
	
	private String operator = "";
	private String numberValue ="";
	
	private String myPage="";

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getActualFormula() {
		return actualFormula;
	}

	public void setActualFormula(String actualFormula) {
		this.actualFormula = actualFormula;
	}

	public String getCreditAbbr() {
		return creditAbbr;
	}

	public void setCreditAbbr(String creditAbbr) {
		this.creditAbbr = creditAbbr;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getDebitAbbr() {
		return debitAbbr;
	}

	public void setDebitAbbr(String debitAbbr) {
		this.debitAbbr = debitAbbr;
	}

	public String getDebitCode() {
		return debitCode;
	}

	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getFormulaCode() {
		return formulaCode;
	}

	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(String numberValue) {
		this.numberValue = numberValue;
	}
		

}
