package org.struts.action.TravelManagement.ExpenseClaim;

import org.paradyne.bean.TravelManagement.ExpenseClaim.TravelAdvanceDefaulter;
import org.paradyne.model.TravelManagement.ExpenseClaim.TravelAdvanceDefaulterModel;
import org.struts.lib.ParaActionSupport;

public class TravelAdvanceDefaulterAction extends ParaActionSupport {

	
	TravelAdvanceDefaulter trAdvDft;
	public void prepare_local() throws Exception {
	 
		   trAdvDft = new TravelAdvanceDefaulter();
		trAdvDft.setMenuCode(824); 
	}

	public Object getModel() { 
		return trAdvDft;
	}

	public TravelAdvanceDefaulter getTrAdvDft() {
		return trAdvDft;
	}

	public void setTrAdvDft(TravelAdvanceDefaulter trAdvDft) {
		this.trAdvDft = trAdvDft;
	}
	
	public String goForAdvance()
	{
		TravelAdvanceDefaulterModel model = new TravelAdvanceDefaulterModel();
		model.initiate(context, session); 
		model.goForAdavnce(trAdvDft);
		model.terminate(); 
		return "success";	
	}
	
 	public String process()
	{
		TravelAdvanceDefaulterModel model = new TravelAdvanceDefaulterModel();
		model.initiate(context, session);
		boolean flag =model.process(trAdvDft,request);
		if(flag)
		{
		 addActionMessage("Process Done Succefully.");	
		}else
		{
			 addActionMessage("Process can't be Done.");	
		}
		model.terminate(); 
		reset();
		return "success";	
	} 
 	
 	public String reProcess()
	{
		TravelAdvanceDefaulterModel model = new TravelAdvanceDefaulterModel();
		model.initiate(context, session);
		boolean flag =model.reProcess(trAdvDft,request);
		trAdvDft.setProcessFlag("false");
		if(flag)
		{
		 addActionMessage("Process Done Succefully.");	
		}else
		{
			 addActionMessage("Process can't be Done.");	
		}
		model.terminate(); 
		reset();
		return "success";	
	} 
 	
 	public String callAppDtl()
 	{
 		TravelAdvanceDefaulterModel model = new TravelAdvanceDefaulterModel();
		model.initiate(context, session);
		model.callDtl(trAdvDft);
		model.terminate(); 
 		return "success";
 	}
 	
 	public String reset()
 	{
 		trAdvDft.setFromDate("");
 		trAdvDft.setToDate("");
 		trAdvDft.setProcessDate("");
 		trAdvDft.setProcessFlag("false");
 		trAdvDft.setSalMonth("0");
 		trAdvDft.setSalYear(""); 
 		return "success";
 	}
 	
	public String f9Action()
	{
		try
		{ 
 String query = " SELECT  TO_CHAR( ADVANCE_DFT_HDR_FROM_DATE,'DD-MM-YYYY'), TO_CHAR( ADVANCE_DFT_HDR_TO_DATE,'DD-MM-YYYY'), "
               +" TO_CHAR( ADVANCE_DFT_HDR_SYSDATE ,'DD-MM-YYYY'),ADVANCE_DFT_HDR_SAL_MONTH, ADVANCE_DFT_HDR_SAL_YEAR," 
               +" ADVANCE_DFT_HDR_ID FROM HRMS_TMS_ADVANCE_DFT_HDR ";
			 
		        
			String[] headers = {"Process From Date", "Process To Date","Process Date"};

			String[] headerWidth = {"35" ,"35","30"};

			String[] fieldNames = {"fromDate","toDate","processDate","salMonth","salYear","advHeaderId"};

			int[] columnIndex = {0,1,2,3,4,5};   

			String submitFlag = "true";

			String submitToMethod = "AdvanceDefaulter_callAppDtl.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
		//	logger.error(e.getMessage());
			return null;
		}
	} // end of f9Employee
 	
 	

}
