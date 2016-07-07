package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DaParaMaster extends BeanBase implements Serializable  
{
	private String daCode="";
	private String daRate="";
	private String daEffDate="";
	private ArrayList daArray=null;
	
	
	public DaParaMaster()
	{
		
	}
	
	public DaParaMaster(String daCode,String daRate,String daEffDate)
	{
		this.daCode=daCode;
		this.daRate=daRate;
		this.daEffDate=daEffDate;
	}

	public ArrayList getDaArray() {
		return daArray;
	}

	public void setDaArray(ArrayList daArray) {
		this.daArray = daArray;
	}

	public String getDaCode() {
		return daCode;
	}

	public void setDaCode(String daCode) {
		this.daCode = daCode;
	}

	public String getDaEffDate() {
		return daEffDate;
	}

	public void setDaEffDate(String daEffDate) {
		this.daEffDate = daEffDate;
	}

	public String getDaRate() {
		return daRate;
	}

	public void setDaRate(String daRate) {
		this.daRate = daRate;
	}
	
}