package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * @author pranali 
 * Date 25-04-07
 */
public class CreditMaster extends BeanBase implements Serializable{
	
	String creditCode=" ";
	String creditName=" ";
	String creditAbbr=" ";
	ArrayList att=null;
	
	
	public CreditMaster()
	{	
	}
	
	public CreditMaster(String creditCode,String creditName,String creditAbbr)
	{
		this.creditCode=creditCode;
		this.creditName=creditName;
		this.creditAbbr=creditAbbr;
	}
	
	
	public String getCreditCode() 
	{		
		return creditCode;
	}

	
	public void setCreditCode(String creditCode)
	{
		this.creditCode = creditCode;
	}
	
	public String getCreditName()
	{
		return creditName;
	}
	
	public void setCreditName(String creditName)
	{
		this.creditName=creditName;
	}
	
	public String getCreditAbbr()
	{
		return creditAbbr;
	}
	
	
	public void setCreditAbbr(String creditAbbr)
	{
		this.creditAbbr=creditAbbr;
	}
	
	public void setAtt(ArrayList att)
	{
		this.att	=	att;
	}
	
	public ArrayList getAtt()
	{
		return att;
	}

}
