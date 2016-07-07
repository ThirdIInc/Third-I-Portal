package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class BonusParaMaster extends BeanBase implements Serializable  
{
	private String bonusCode="";
	private String bonusType="";
	private String bonDaysDec="";
	private String bonPrdFrom="";
	private String bonPrdTo="";
	private String bonEmpType;
	private String typeCode="";
	private String bonFormula="";
	private ArrayList bonusArray=null;
	
	
	public BonusParaMaster()
	{
		
	}
	
	public BonusParaMaster(String bonusType,String bonusCode,String bonDaysDec,String bonPrdFrom,String bonPrdTo,String bonEmpType)
	{
		this.bonusType=bonusType;
		this.bonDaysDec=bonDaysDec;
		this.bonPrdFrom=bonPrdFrom;
		this.bonPrdTo=bonPrdTo;
		this.bonEmpType=bonEmpType;
	}

	
	
	public String getBonusCode() {
		return bonusCode;
	}

	public void setBonusCode(String bonusCode) {
		this.bonusCode = bonusCode;
	}

	public String getBonDaysDec() {
		return bonDaysDec;
	}

	public void setBonDaysDec(String bonDaysDec) {
		this.bonDaysDec = bonDaysDec;
	}

	public String getBonEmpType() {
		return bonEmpType;
	}

	public void setBonEmpType(String bonEmpType) {
		this.bonEmpType = bonEmpType;
	}

	public String getBonPrdFrom() {
		return bonPrdFrom;
	}

	public void setBonPrdFrom(String bonPrdFrom) {
		this.bonPrdFrom = bonPrdFrom;
	}

	public String getBonPrdTo() {
		return bonPrdTo;
	}

	public void setBonPrdTo(String bonPrdTo) {
		this.bonPrdTo = bonPrdTo;
	}

	public ArrayList getBonusArray() {
		return bonusArray;
	}

	public void setBonusArray(ArrayList bonusArray) {
		this.bonusArray = bonusArray;
	}

	public String getBonusType() {
		return bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getBonFormula() {
		return bonFormula;
	}

	public void setBonFormula(String bonFormula) {
		this.bonFormula = bonFormula;
	}
	
}
	