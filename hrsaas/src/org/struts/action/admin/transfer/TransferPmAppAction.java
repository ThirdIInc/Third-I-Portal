package org.struts.action.admin.transfer;
/*
 * author:Pradeep Kumar Sahoo
 */

 import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.transfer.TransferPmApproval;
import org.paradyne.model.admin.transfer.TransferApprovalModel;
import org.paradyne.model.admin.transfer.TransferPmApprovalModel;


import javax.servlet.http.HttpServletResponse;



public class TransferPmAppAction extends ParaActionSupport{
	TransferPmApproval tpma=null;;
	
	
 public TransferPmApproval getModel(){
	
	 return tpma;
 }
 public TransferPmApproval getTpma() {
		return tpma;
	}

	public void setTpma(TransferPmApproval tpma) {
		this.tpma = tpma;
	}
		
 static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
	
	 tpma=new TransferPmApproval();
	 tpma.setMenuCode(324);
 }
 
 public String input() throws Exception {
	 TransferPmApprovalModel model=new TransferPmApprovalModel();
	 model.initiate(context,session);
	 model.getEmp(tpma,request);
	 model.terminate();	 
	 return "success";
 }
 
 
 public String saveForApprove(){
	 TransferPmApprovalModel model = new TransferPmApprovalModel();
	
		boolean iaApprove=false;
		model.initiate(context,session);
		String[] Ids=(String[])request.getParameterValues("pmChkFlag");
		String[] flags=(String[])request.getParameterValues("trfId");
		 logger.info("Pradeep in action");
		for(int i=0;i<Ids.length;i++)
		{
			logger.info("&&&&&&&&&&&&&&&&&&&After Get San Id Values----------------------@@@@@@@@@"+Ids[i]);
			logger.info("&&&&&&&&&&&&&&&&&&&After Get San proposal code----------------------@@@@@@@@@"+flags[i]);
			
			if (Ids[i].equals("Y")) {
				iaApprove = model.saveByApprover(tpma,flags[i]);
			}
			
		}
		 
		if(iaApprove)
		{
			addActionError("Record Saved Successfully");

			tpma.setDceNo("");
			tpma.setDceDate("");
			
		}
		model.getEmp(tpma,request);
		model.terminate();
	
		return "success";
	}
 
 
 
 
 public void dcelist()throws Exception
	{

	 TransferPmApprovalModel model = new TransferPmApprovalModel();
		String[] Ids=(String[])request.getParameterValues("pmChkFlag");
		String[] trfIds=(String[])request.getParameterValues("trfId");
		model.initiate(context,session);
		int c=0;
		for(int i=0;i<Ids.length;i++)
		{
			
			if (Ids[i].equals("Y")) {
				c++;
				logger.info("Value of trans id"+trfIds[i]);
				
			}
		}

		String [][]empData =new String[c][1];
		 int count=0;
			for(int i=0;i<Ids.length;i++)
			{
				logger.info("&&&&&&&&&&&&&&&&&&&After Get San Id Values----------------------@@@@@@@@@"+Ids[i]);
				if (Ids[i].equals("Y")) {
					empData[count][0]=trfIds[i];
					
					logger.info("Value of transfer code "+trfIds[i]);
					count++;
					
				}
			}
		boolean result=model.dcelist(tpma,request,response,c,empData);
		
		if(result)
		{
		
			for(int i=0;i<Ids.length;i++)
			{
				logger.info("VAlues are++++++++++++++++++++++++++++++++++++++++++++++"+Ids[i]);
				if(Ids[i].equals("Y"))
				{
					boolean result1=model.saveByDCEApprover(tpma,trfIds[i]);
					if(result1)
						addActionMessage("DCE LIST Generated");
				}
			}
		}
		
		model.terminate();
		//return null;
	}

 
 
}

