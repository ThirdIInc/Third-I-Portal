package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.PayBillMaster;

import org.paradyne.model.admin.master.ESIMasterModel;
import org.paradyne.model.admin.master.PayBillModel;

/**@modified by priyanka
 * @author Hemant 
 * Date 24-04-07
 */

public class PayBillMasterAction extends org.struts.lib.ParaActionSupport 
{

	PayBillMaster payMaster;
	
	public org.paradyne.bean.admin.master.PayBillMaster getpayMaster() 
	{
		logger.info("getpaybillMaster");	
		return payMaster;   
	}     
	
	
	public Object getModel()
	{
		logger.info("In getModel");	
		return payMaster;
	}
	
	public void setPayMaster(org.paradyne.bean.admin.master.PayBillMaster payMaster) 
	{
		logger.info("In setPayMaster");	
		this.payMaster = payMaster;
	}
	
	public String execute()throws Exception 
	{		
		logger.info("In execute****");	
		return "success";
	}     
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		payMaster=new PayBillMaster();
		payMaster.setMenuCode(214);
		
	}

	public String prepareInput() throws Exception
	{
		logger.info("In prepare");	
		return "success";
	}
	
	
	public String save()throws Exception 
	{
		logger.info("In Save Method");	
		PayBillModel model = new PayBillModel();
		logger.info("before initiate context");
		model.initiate(context,session);
		logger.info("after initiate context");
		boolean result=false ;
		if(payMaster.getPayID().equals(""))
		{
			logger.info("before addition");
			 result = model.addPayBill(payMaster);
			 if(result){
				 addActionMessage(getText("addMessage",""));
			 }		
			 if(!result) 
				{
					addActionMessage("Pay Bill can not be added.");
				}
		}
		else
		{			
			result = model.modPayBill(payMaster);
			if(result){
				 addActionMessage("Record Updated Successfully");
			}
			if(!result) 
			{
				addActionMessage("Record can not be updated.");
			}
		}
		
		model.Data(payMaster,request);
		model.terminate();
		clear();	
		return "success";
	}
	public void clear(){
		payMaster.setPayID("");
		payMaster.setPayGrp("");
	}
	
	public String delete() throws Exception
	{
		logger.info("in delete");
		PayBillModel model = new PayBillModel();
		
		model.initiate(context,session);
		boolean result ;
		logger.info("Before deletequalification");
		result = model.deletePayBill(payMaster);  
		logger.info("After deletepayBill");
		if(result)
		{
			payMaster.setPayID("");
			payMaster.setPayGrp("");
			addActionMessage(getText("delMessage",""));
		}
		else {
			addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
		}
		model.Data(payMaster,request);
		model.terminate();
		return "success";
	}
	
	public String reset() throws Exception
	{
		logger.info("In reset method");
		try
		{
			logger.info("in reset");
			payMaster.setPayID("");
			payMaster.setPayGrp("");
			
			return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}
	public String paybillRecord(){
		logger.info("Inside paybillRecord()....");
		PayBillModel model=new PayBillModel();
		model.initiate(context,session);
		//model.getPayBillRecord(payMaster);
		model.terminate();
		return "success";
	}
	public String f9action() throws Exception 
	{
		
		logger.info("in f9 action");
		
		String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY PAYBILL_ID";		
		
		
		String[] headers={"Pay Bill Code", getMessage("paybill.group")};
		
		String[] headerWidth={"20", "40"};
		
		
		String[] fieldNames={"payID","payGrp"};
		
		int[] columnIndex={0,1};
		
		String submitFlag = "true";
		
		
		String submitToMethod="PayBillMaster_paybillRecord.action";
		
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}	
	
	
	
	/*public String report()throws Exception 
	{
		logger.info("in report");
		PayBillModel model = new PayBillModel();
		logger.info("before initiate");
		model.initiate(context,session);
		logger.info("before getQualificationReport");
		model.getPayBillReport(payMaster);
		logger.info("before terminate");
		model.terminate();	
		return "report";    
	}*/
	
	public String report()throws Exception 
	{
		PayBillModel model = new PayBillModel();
	 model.initiate(context,session);			
	 model.getReport(payMaster,request,response,context,session);
	 model.terminate();	
	 return null;    
	}
	
	private boolean isInvalid(String value)
	{
		logger.info("in isinvalid");
        return (value == null || value.length() == 0);
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		PayBillModel model = new PayBillModel();	
				model.initiate(context,session);
		        payMaster.setIsNotGeneralUser("false");
				model.Data(payMaster,request);
				model.terminate();
		}
	
	
	
		public String callPage() throws Exception {
			
			PayBillModel model = new PayBillModel();
				model.initiate(context,session);
				model.Data(payMaster,request);
				model.terminate();
			 return SUCCESS;
			
			}
		
		public String calforedit() throws Exception{
			PayBillModel model = new PayBillModel();
			model.initiate(context,session);
			model.calforedit(payMaster);
			//getRecord();
			
		
			model.Data(payMaster,request);
			model.terminate();
			return "success";
		}
		
	public String calfordelete()
		{
		PayBillModel model = new PayBillModel();
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(payMaster);
			if(result){
				addActionMessage(getText("Record  Deleted Successfully"));
					//reseting all bean varibles	
				
				
				payMaster.setPayID("");
				payMaster.setPayGrp("");
				payMaster.setHiddencode("");
				
			}
			else {
				addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
			}
			
			model.Data(payMaster,request);
			model.terminate();
			
		return "success";
		}



	public String delete1()throws Exception {
			String code[]=request.getParameterValues("hdeleteCode");
			
			PayBillModel model = new PayBillModel();
			
			model.initiate(context,session);
			boolean result =model.deletecheckedRecords(payMaster,code);
			
				if(result) {
					addActionMessage(getText("delMessage",""));
				} else {
					addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
				}
			

			
			model.Data(payMaster,request);
			model.terminate();
			
			return reset();

		}


	//Added by Nilesh D on 6th Feb 2012.
	public String generateReport() throws Exception {
		PayBillModel model = new PayBillModel();
		model.initiate(context, session);
		 String reportPath="";			
		model.generateReport(payMaster, request, response,reportPath);
		model.terminate();
		return null;
	}
	
    public final String mailReport(){
		try {
			final PayBillModel model = new PayBillModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Master" + poolName + "/";
			model.generateReport(payMaster, request,response,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

}