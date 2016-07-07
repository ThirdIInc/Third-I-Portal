/**
 * 
 */
package org.paradyne.bean.payroll;

import java.io.Serializable;

import java.util.ArrayList;

import org.paradyne.lib.*;

/**
 * @author Ibrahim
 *
 */
public class EmpBonus extends BeanBase implements Serializable{

	/**
	 * 
	 */
	private String bonusCode;
	private String bonusMonth	=	"";
	private String bonusFrom	=	"";
	private String bonusTo		=	"";
	private String bonusType	=	"";
	private String bonusDays			=	"";
	private String typeCode;
	private String paybillId="";
	private String bonusEmptype			=	"";
	private String bonusPaybill			=	"";
	private String empId="";
	private String cEmpId			=	"";
	private String cEmpName			=	"";
	private String cEmpBonusDays	=	"";
	private String cAmount			=	"";
	private String cEmpToken="";
	private ArrayList list			=	null;
	private ArrayList view			=	null;
	private String flag; 
	private String empFlag="false";
	
	public EmpBonus() {
		// TODO Auto-generated constructor stub
	}

	
	public String getBonusMonth() {
		return bonusMonth;
	}

	public void setBonusMonth(String bonusMonth) {
		this.bonusMonth = bonusMonth;
	}

	
	public String getBonusFrom() {
		return bonusFrom;
	}



	public void setBonusFrom(String bonusFrom) {
		this.bonusFrom = bonusFrom;
	}



	public String getBonusTo() {
		return bonusTo;
	}



	public void setBonusTo(String bonusTo) {
		this.bonusTo = bonusTo;
	}



	public String getBonusType() {
		return bonusType;
	}



	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}



	public String getBonusDays() {
		return bonusDays;
	}



	public void setBonusDays(String bonusDays) {
		this.bonusDays = bonusDays;
	}



	public String getBonusEmptype() {
		return bonusEmptype;
	}



	public void setBonusEmptype(String bonusEmptype) {
		this.bonusEmptype = bonusEmptype;
	}



	public String getBonusPaybill() {
		return bonusPaybill;
	}



	public void setBonusPaybill(String bonusPaybill) {
		this.bonusPaybill = bonusPaybill;
	}



	public String getCAmount() {
		return cAmount;
	}

	public void setCAmount(String amount) {
		cAmount = amount;
	}

	public String getCEmpBonusDays() {
		return cEmpBonusDays;
	}

	public void setCEmpBonusDays(String empBonusDays) {
		cEmpBonusDays = empBonusDays;
	}

	public String getCEmpId() {
		return cEmpId;
	}

	public void setCEmpId(String empId) {
		cEmpId = empId;
	}

	public String getCEmpName() {
		return cEmpName;
	}

	public void setCEmpName(String empName) {
		cEmpName = empName;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}



	public String getBonusCode() {
		return bonusCode;
	}



	public void setBonusCode(String bonusCode) {
		this.bonusCode = bonusCode;
	}



	public String getCEmpToken() {
		return cEmpToken;
	}



	public void setCEmpToken(String empToken) {
		cEmpToken = empToken;
	}



	public String getEmpId() {
		return empId;
	}



	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getPaybillId() {
		return paybillId;
	}


	public void setPaybillId(String paybillId) {
		this.paybillId = paybillId;
	}


	public String getTypeCode() {
		return typeCode;
	}


	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}


	public ArrayList getView() {
		return view;
	}


	public void setView(ArrayList view) {
		this.view = view;
	}


	public String getEmpFlag() {
		return empFlag;
	}


	public void setEmpFlag(String empFlag) {
		this.empFlag = empFlag;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}

}
