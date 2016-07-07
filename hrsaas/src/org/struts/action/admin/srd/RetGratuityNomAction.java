/**
 * author:PK Sahoo
 */

package org.struts.action.admin.srd;
import org.paradyne.bean.admin.srd.RetGratuityNom;

import org.paradyne.model.admin.srd.RetGratuityNomModel;

 import org.struts.lib.ParaActionSupport;


public class RetGratuityNomAction extends ParaActionSupport  {
	private RetGratuityNom rgn;

	
	
	 public Object getModel() {
		logger.info("Inside-->getModel()");
		return this.rgn;
	}
	/**
	 * @return the rgn
	 */
	public RetGratuityNom getRgn() {
		return rgn;
	}


	/**
	 * @param rgn the rgn to set
	 */
	public void setRgn(RetGratuityNom rgn) {
		this.rgn = rgn;
	}

	

    
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		logger.info("Inside-->prepare()");
		rgn=new RetGratuityNom();
		rgn.setMenuCode(121);
		
		}
	
	public String report() throws Exception
	{		
	logger.info("Inside-->report()");
	
		try
		{			 
			try {
				RetGratuityNomModel model = new RetGratuityNomModel();
				model.initiate(context,session);
						
				model.generateReport(rgn,response);
								
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		catch(Exception e)
		{
			 logger.info("eXCEPTION IN PROCESS OF OT "+e);
		}		
		return null;
}
	
public String f9type() throws Exception {
	//
	// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	// OUTPUT ALONG WITH PROFILES
	//
logger.info("Inside-->report()");
	String query = " SELECT   EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) ORDER BY EMP_ID ";
	
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
	  
	 
	int[] columnIndex = { 0,1,2 };

	
	 // WHEN SET TO 'true' WILL SUBMIT THE FORM
	  
	 
	String submitFlag = "false";

	
	 // IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
	 // FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
	 // ACTION>_<METHOD TO CALL>.action
	 
	String submitToMethod = "";

	
	 // CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
	 

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}




	
	

}



