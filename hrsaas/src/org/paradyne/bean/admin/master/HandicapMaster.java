package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * @author pranali 
 * Date 24-04-07
 */
public class HandicapMaster extends BeanBase implements Serializable{
	
	String catagoryID="";
	String catagoryName="";
	ArrayList att=null;  

	
	public HandicapMaster()
	{
		
	}
	
	
	public HandicapMaster(String catagoryID,String catagoryName)
	{
				
		this.catagoryID= catagoryID;
		this.catagoryName=catagoryName;
		
	}
	
	public String getCatagoryID() 
	{
		
		return catagoryID;
	}

	
	public void setCatagoryID(String catagoryID)
	{
		
		this.catagoryID = catagoryID;
	}
	
	
	public String getCatagoryName()
	{
	
		return catagoryName;
	}

	
	
	public void setCatagoryName(String catagoryName)
	{
		this.catagoryName = catagoryName;
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
