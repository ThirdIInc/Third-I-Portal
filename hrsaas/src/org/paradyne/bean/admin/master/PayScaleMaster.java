package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * @author pranali 
 * Date 26-04-07
 */
public class PayScaleMaster extends BeanBase implements Serializable{
	
	String payID="";
	String payName="";
	String payStartAmt="";
	String payIncrAmt1="";	
	String payFinalAmt1="";	
	String payIncrAmt2="";	
	String payFinalAmt2="";	
	String payIncrAmt3="";	
	String payFinalAmt3="";	
	String payCommission="";
	String payIncrAmt4="";	
	String payFinalAmt4="";	
	String payIncrAmt5="";	
	String payFinalAmt5="";	
	
	ArrayList att=null;
	
	public PayScaleMaster()
	{

	}

	public PayScaleMaster(String payID,String payName, String payStartAmt,String payIncrAmt1,String payFinalAmt1,String payIncrAmt2,String payFinalAmt2,String payIncrAmt3,String payFinalAmt3,String payCommission,String payIncrAmt4,String payFinalAmt4,String payIncrAmt5,String payFinalAmt5 )
	{
		this.payID=payID;
		this.payName=payName;
		this.payStartAmt=payStartAmt;
		this.payIncrAmt1=payIncrAmt1;
		this.payFinalAmt1=payFinalAmt1;
		this.payIncrAmt2=payIncrAmt2;
		this.payFinalAmt2=payFinalAmt2;
		this.payIncrAmt3=payIncrAmt3;
		this.payFinalAmt3=payFinalAmt3;
		this.payCommission=payCommission;	
		this.payIncrAmt4=payIncrAmt4;
		this.payFinalAmt4=payFinalAmt4;
		this.payIncrAmt5=payIncrAmt5;
		this.payFinalAmt5=payFinalAmt5;
		
	}
		
		
		public String getPayID()
		{
			return payID;
		}
		public void setPayID(String payID)
		{
			this.payID=payID;
		}
		
		
		
		public String getPayName()
		{
			return payName;
		}
		public void setPayName(String payName)
		{
			this.payName=payName;
		}
		
		
		
		
		public String getPayStartAmt()
		{
			return payStartAmt;
		}
		public void setPayStartAmt(String payStartAmt)
		{
			this.payStartAmt=payStartAmt;
		}
		
		
		
		
		public String getPayIncrAmt1()
		{
			return payIncrAmt1;
		}
		public void setPayIncrAmt1(String payIncrAmt1)
		{
			this.payIncrAmt1=payIncrAmt1;
		}
		
		
		
		
		public String getPayFinalAmt1()
		{
			return payFinalAmt1;
		}
		public void setPayFinalAmt1(String payFinalAmt1)
		{
			this.payFinalAmt1=payFinalAmt1;
		}
		
		
				
		
		
		
		public String getPayIncrAmt2()
		{
			return payIncrAmt2;
		}
		public void setPayIncrAmt2(String payIncrAmt2)
		{
			this.payIncrAmt2=payIncrAmt2;
		}
		
		
		
		
		public String getPayFinalAmt2()
		{
			return payFinalAmt2;
		}
		public void setPayFinalAmt2(String payFinalAmt2)
		{
			this.payFinalAmt2=payFinalAmt2;
		}
		
		
		
		
		public String getPayIncrAmt3()
		{
			return payIncrAmt3;
		}
		public void setPayIncrAmt3(String payIncrAmt3)
		{
			this.payIncrAmt3=payIncrAmt3;
		}
		
		
		
		public String getPayFinalAmt3()
		{
			return payFinalAmt3;
		}
		public void setPayFinalAmt3(String payFinalAmt3)
		{
			this.payFinalAmt3=payFinalAmt3;
		}
		
		
		
		
		public String getPayCommission()
		{
			return payCommission;
		}
		public void setPayCommission(String payCommission)
		{
			this.payCommission=payCommission;
		}
		
		
		
		
		
		public String getPayIncrAmt4()
		{
			return payIncrAmt4;
		}
		public void setPayIncrAmt4(String payIncrAmt4)
		{
			this.payIncrAmt4=payIncrAmt4;
		}
		
		
		
		
		
		public String getPayFinalAmt4()
		{
			return payFinalAmt4;
		}
		public void setPayFinalAmt4(String payFinalAmt4)
		{
			this.payFinalAmt4=payFinalAmt4;
		}
		
		
		
		
		public String getPayIncrAmt5()
		{
			return payIncrAmt5;
		}
		public void setPayIncrAmt5(String payIncrAmt5)
		{
			this.payIncrAmt5=payIncrAmt5;
		}
		
		
		
		
		public String getPayFinalAmt5()
		{
			return payFinalAmt5;
		}
		public void setPayFinalAmt5(String payFinalAmt5)
		{
			this.payFinalAmt5=payFinalAmt5;
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
	
	

