package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CcaParaMaster extends BeanBase implements Serializable  
{
	private String ccaCode="";
	private String equiSalFrom="";
	private String equiSalTo="";
	private String ccaAmt="";
	private ArrayList ccaArray=null;
	
	
	public CcaParaMaster()
	{
		
	}
	
	public CcaParaMaster(String ccaCode,String equiSalFrom,String equiSalTo,String ccaAmt)
	{
		this.ccaCode=ccaCode;
		this.equiSalFrom=equiSalFrom;
		this.equiSalTo=equiSalTo;
		this.ccaAmt=ccaAmt;
	}
	
	public String getCcaAmt() {
		return ccaAmt;
	}
	public void setCcaAmt(String ccaAmt) {
		this.ccaAmt = ccaAmt;
	}
	public ArrayList getCcaArray() {
		return ccaArray;
	}
	public void setCcaArray(ArrayList ccaArray) {
		this.ccaArray = ccaArray;
	}
	public String getCcaCode() {
		return ccaCode;
	}
	public void setCcaCode(String ccaCode) {
		this.ccaCode = ccaCode;
	}
	public String getEquiSalFrom() {
		return equiSalFrom;
	}
	public void setEquiSalFrom(String equiSalFrom) {
		this.equiSalFrom = equiSalFrom;
	}
	public String getEquiSalTo() {
		return equiSalTo;
	}
	public void setEquiSalTo(String equiSalTo) {
		this.equiSalTo = equiSalTo;
	}
	
	
}
	