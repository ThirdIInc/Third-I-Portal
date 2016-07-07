package org.struts.action.admin.master;

import org.apache.poi.hssf.model.Model;
import org.paradyne.bean.admin.master.Requisition;
import org.paradyne.model.admin.master.RequisitionModel;
 import org.struts.lib.ParaActionSupport;

public class RequisitionAction extends ParaActionSupport {

 
	
	Requisition requis;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		requis = new Requisition();
		requis.setMenuCode(331);
	}

	 public Object getModel() {
		// TODO Auto-generated method stub
		return requis;
	}

	public Requisition getRequis() {
		return requis;
	}

	public void setRequis(Requisition requis) {
		this.requis = requis;
	}
	
	public String save()
	{ 
		RequisitionModel model = new  RequisitionModel();
		model.initiate(context,session);
		if(requis.getRequisitionCode().equals(""))
		{  
	       boolean result=	model.save(requis);
		    if(result==true)
		    {
		    	addActionMessage("Record Saved Successfully !");
		    }else
		    {
		    	addActionMessage("Record Cannot be added !");
		    }
		}else{
			
			 boolean result=	model.update(requis);
			    if(result==true)
			    {
			    	addActionMessage("Record Updated Successfully !");
			    }else
			    {
			    	addActionMessage("Record Can not be Updated!");
			    }
			
			
		}
		requis.setRequisitionCode("");
 
		requis.setReuisitionName("");
		model.Data(requis,request);
		model.terminate(); 
		return "success";
	}
	
	
	public String delete()
	{
		RequisitionModel model = new  RequisitionModel();
		model.initiate(context,session);
		boolean result = model.delete(requis);
		if(result==true)
		{
			addActionMessage("Record Deleted Successfully !");
		}else
		{
			addActionMessage("Record Cannot be Deleted !");
		}
		requis.setRequisitionCode("");
		requis.setReuisitionName("");
		model.Data(requis,request);
		model.terminate(); 
		return "success";
		
	}
	
	public String reset()
	{
		requis.setRequisitionCode("");
		requis.setReuisitionName("");
		return "success";
		
	}
	
	 public String report()
	{
		    RequisitionModel model = new  RequisitionModel();
			model.initiate(context,session);
		  /*  model.report(requis);
			model.terminate(); 
		    return "report";*/


			model.getReport(requis,request,response,context);
			model.terminate();
			return null;
			
		
	}
	
	
	
	
	
	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "  SELECT REQUISITION_HEAD_CODE,REQUISITION_HEAD_NAME  FROM HRMS_REQUISITION_HEAD ORDER BY REQUISITION_HEAD_CODE ";	
					
	 
	 
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Requisition ID",getMessage("requisition.name")};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"requis.requisitionCode","requis.reuisitionName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod=" ";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

	
	public void prepare_withLoginProfileDetails() throws Exception {
		RequisitionModel model = new RequisitionModel();	
				model.initiate(context,session);
					
				model.Data(requis,request);
				model.terminate();
		}
		public String callPage() throws Exception {
			
			RequisitionModel model = new RequisitionModel();
				model.initiate(context,session);
				model.Data(requis,request);
				model.terminate();
			 return SUCCESS;
			
			}
		
		public String calforedit() throws Exception{
			RequisitionModel model = new RequisitionModel();
			model.initiate(context,session);
			System.out.println("before");
			model.calforedit(requis);
			System.out.println("after calforedit");
			//getRecord();
			
		
			model.Data(requis,request);
			model.terminate();
			return "success";
		}
		
	public String calfordelete()
		{
		RequisitionModel model = new RequisitionModel();
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(requis);
			if(result){
				addActionMessage(getText("Record  Deleted Successfully"));
					//reseting all bean varibles	
				requis.setRequisitionCode("");
				requis.setReuisitionName("");
				requis.setHiddencode("");
			}
			else{
				addActionMessage("Record can not be deleted");
			}
			
			model.Data(requis,request);
			model.terminate();
			
		return "success";
		}
	

	public String delete1()throws Exception {
			String code[]=request.getParameterValues("hdeleteCode");
			
			RequisitionModel model = new RequisitionModel();
			
			model.initiate(context,session);
			boolean result =model.deletecheckedRecords(requis,code);
			
				if(result) {
					addActionMessage(getText("delMessage",""));
				} else {
					addActionMessage("One or more records can't be deleted \n as they are associated with some other records.");
				}
			

			
			model.Data(requis,request);
			model.terminate();
			
			return reset();

		}
	
	

}
