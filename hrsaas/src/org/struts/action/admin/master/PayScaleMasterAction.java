package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.PayScaleMaster;
import org.paradyne.model.admin.master.PayScaleModel;


/**
 * @author pranali 
 * Date 26-04-07
 */
public class PayScaleMasterAction extends org.struts.lib.ParaActionSupport {
	
	PayScaleMaster payScaleMaster;
	
	
	public org.paradyne.bean.admin.master.PayScaleMaster getPayScaleMaster()
	{
		return payScaleMaster;
	}
	
	
	public Object getModel()
	{
		return payScaleMaster;
	}
	
	public void setPayScaleMaster(org.paradyne.bean.admin.master.PayScaleMaster payScaleMaster) 
	{
		this.payScaleMaster = payScaleMaster;
	}
	
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
	
		payScaleMaster=new PayScaleMaster();
		payScaleMaster.setMenuCode(24);
	}
	
	
	public String save()throws Exception 
	{
		PayScaleModel model = new PayScaleModel();
		model.initiate(context,session);
		boolean result ;
		if(payScaleMaster.getPayID().equals(""))
		{
			 result = model.addPayScale(payScaleMaster);	
			 addActionMessage(getText("addMessage",""));
		}
		else
		{
			 result = model.modPayScale(payScaleMaster);	
			 addActionMessage(getText("modMessage",""));
		}
		model.terminate();		
		
		return reset();
	}


	public String reset() throws Exception
	{
		try
		{
			payScaleMaster.setPayID("");
			payScaleMaster.setPayName("");
			payScaleMaster.setPayStartAmt("");
			payScaleMaster.setPayIncrAmt1("");
			payScaleMaster.setPayFinalAmt1("");
			payScaleMaster.setPayIncrAmt2("");
			payScaleMaster.setPayFinalAmt2("");
			payScaleMaster.setPayIncrAmt3("");
			payScaleMaster.setPayFinalAmt3("");
			payScaleMaster.setPayCommission("");
			payScaleMaster.setPayIncrAmt4("");
			payScaleMaster.setPayFinalAmt4("");
			payScaleMaster.setPayIncrAmt5("");
			payScaleMaster.setPayFinalAmt5("");
			payScaleMaster.setPayID("");
			payScaleMaster.setPayID("");
			payScaleMaster.setPayName("");
			payScaleMaster.setPayStartAmt("");
			payScaleMaster.setPayIncrAmt1("");
			payScaleMaster.setPayFinalAmt1("");
			payScaleMaster.setPayIncrAmt2("");
			payScaleMaster.setPayFinalAmt2("");
			payScaleMaster.setPayIncrAmt3("");
			payScaleMaster.setPayFinalAmt3("");
			payScaleMaster.setPayCommission("");
			payScaleMaster.setPayIncrAmt4("");
			payScaleMaster.setPayFinalAmt4("");
			payScaleMaster.setPayIncrAmt4("");
			payScaleMaster.setPayIncrAmt5("");
			payScaleMaster.setPayFinalAmt5("");
		
			return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}
	
	public String delete() throws Exception
	{
		PayScaleModel model = new PayScaleModel();
		model.initiate(context,session);
		boolean result ;
		result = model.deletePayScale(payScaleMaster);  
		if(result)
		{
			payScaleMaster.setPayID("");
			payScaleMaster.setPayName("");
			payScaleMaster.setPayStartAmt("");
			payScaleMaster.setPayIncrAmt1("");
			payScaleMaster.setPayFinalAmt1("");
			payScaleMaster.setPayIncrAmt2("");
			payScaleMaster.setPayFinalAmt2("");
			payScaleMaster.setPayIncrAmt3("");
			payScaleMaster.setPayFinalAmt3("");
			payScaleMaster.setPayCommission("");
			payScaleMaster.setPayIncrAmt4("");
			payScaleMaster.setPayFinalAmt4("");
			payScaleMaster.setPayIncrAmt4("");
			payScaleMaster.setPayIncrAmt5("");
			payScaleMaster.setPayFinalAmt5("");
		}
		if(result)
		{
			addActionMessage(getText("delMessage",""));
		} 
		else 
		{
			addActionMessage("PayScale can not be Deleted");
		}
		model.terminate();
		return "success";
	}
	
	/*public String report()throws Exception 
	{
		PayScaleModel model = new PayScaleModel();
		model.initiate(context,session);
		model.getPayScaleReport(payScaleMaster);
		model.terminate();	
		return "report";    
	}*/
	
	
	public String report()throws Exception 
	{
		PayScaleModel model = new PayScaleModel();
	 model.initiate(context,session);			
	 model.getReport(payScaleMaster,request,response,context,session);
	 model.terminate();	
	 return null;    
	}

	public String payScaleRecord()throws Exception {	
		
		PayScaleModel model = new PayScaleModel();
		model.initiate(context,session);
		model.getPayScaleRecord(payScaleMaster);
		model.terminate();
		return "success";
	}
	
	public String f9action() throws Exception 
	{
		
 
		
		String query = " SELECT PAYSC_ID, PAYSC_NAME, PAYSC_START_AMT FROM HRMS_PAYSCALE ORDER BY PAYSC_ID";		
		
		
		String[] headers={"Pay Scale Code", "Pay Scale Name","Pay Scale Start Amt"};
		
		String[] headerWidth={"20", "60","20"};
		
		
		String[] fieldNames={"payScaleMaster.payID","payScaleMaster.payName","payScaleMaster.payStartAmt"};
		
		int[] columnIndex={0,1,2};
		
		String submitFlag = "true";
		
		String submitToMethod="PayScaleMaster_payScaleRecord.action";
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	
	

}
