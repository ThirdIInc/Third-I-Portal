package org.struts.action.leave;
/*
 * author:Pradeep Kumar Sahoo
 */
import org.paradyne.model.admin.transfer.NoDemandCertModel;
import org.paradyne.model.leave.*;

import org.paradyne.bean.leave.*;

import org.struts.lib.ParaActionSupport;

public class LeaveBalanceReportAction extends ParaActionSupport {
	
	private leaveBalanceReport lbr;

	public leaveBalanceReport getLbr() {
		return lbr;
	}

	public void setLbr(leaveBalanceReport lbr) {
		this.lbr = lbr;
	}
	
	
	 public Object getModel() {
		logger.info("Inside-->getModel()");
		return this.lbr;
	}

	public String execute() throws Exception {
		logger.info("Inside-->execute()");
		return "success";
	}
    
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		logger.info("Inside-->prepare()");
		lbr=new leaveBalanceReport();
		lbr.setMenuCode(202);
	}
	
	
	 public void prepare_withLoginProfileDetails() throws Exception {
			
			if (lbr.isGeneralFlag()) {
				LeaveBalanceReportModel model = new LeaveBalanceReportModel();
				model.initiate(context,session);
			
				model.getGenEmp(lbr,lbr.getUserEmpId());//This function is called when a general user makes login
				
				if(lbr.getRepType().equals("Pdf"))
				{
					model.generateReportPdfGen(lbr,response,lbr.getUserEmpId());//This function is called when report type "Pdf" is selected"
				}
				if(lbr.getRepType().equals("Txt"))
				{
				model.generateReportTextGen(lbr,response,lbr.getUserEmpId());//This function is called when report type "Text is selected"
				}
				
				model.terminate();
			}
		}
	
	
	
	 
	public String report() throws Exception
	{		
	
	
		try
		{			 
			try {
				LeaveBalanceReportModel model = new LeaveBalanceReportModel();
				model.initiate(context,session);
				
				if(lbr.getRepType().equals("Pdf"))
				{
					model.generateReportPdf(lbr,response);
				}
				if(lbr.getRepType().equals("Txt"))
				{
				model.generateReportText(lbr,response);
				}
								
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		catch(Exception e)
		{
			 logger.info("EXCEPTION IN PROCESS OF OT "+e);
		}		
		return null;
}
	
	public String f9type() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//

		String query = 	"   SELECT DISTINCT EMP_TOKEN,TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||HRMS_EMP_OFFC.EMP_LNAME ,HRMS_LEAVE_BALANCE.EMP_ID "
						+" FROM HRMS_LEAVE_BALANCE "	
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_BALANCE.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
                        +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) ";
                        query+=getprofileQuery(lbr);
                		query+=" ORDER BY EMP_ID  ";//ORDER BY HRMS_LEAVE_BALANCE.EMP_ID"	;
		
		 // SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		
		String[] headers = { "Token No.", "Employee Name" };

		
		  //DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 
		String[] headerWidth = { "40", "60" };

		
		 // -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 // ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 // -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 // INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 // FIELDNAMES
		 //

		String[] fieldNames = { "empToken","empName","empId" };

		
		 // SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 // CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 // IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		  
		 //NOTE: COLUMN NUMBERS STARTS WITH 0
		  
		 
		int[] columnIndex = {0,1,2};

		
		 // WHEN SET TO 'true' WILL SUBMIT THE FORM
		  
		 
		String submitFlag = "false";

		
		 // IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 // FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 // ACTION>_<METHOD TO CALL>.action
		 
//		String submitToMethod = "leaveBalanceReport_genEmp.action";
		String submitToMethod = "";

		
		 // CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	

}
