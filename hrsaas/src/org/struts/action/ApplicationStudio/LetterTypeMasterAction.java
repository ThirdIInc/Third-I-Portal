package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.LetterTypeMaster;
import org.paradyne.model.ApplicationStudio.LetterTypeMasterModel;
import org.struts.lib.ParaActionSupport;

public class LetterTypeMasterAction extends ParaActionSupport{

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.action.ApplicationStudio.LetterTypeMasterAction.class);

	
	LetterTypeMaster letterType;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		letterType =new LetterTypeMaster();
		letterType.setMenuCode(917);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return letterType;
	}

	public LetterTypeMaster getLetterType() {
		return letterType;
	}

	public void setLetterType(LetterTypeMaster letterType) {
		this.letterType = letterType;
	}
	
	
	
	public String save()
	{
		LetterTypeMasterModel model=new LetterTypeMasterModel();
		model.initiate(context, session);
		boolean result=false;
		if(letterType.getLetterTypeId().equals(""))
		{  
		result=model.save(letterType.getLetterTypeId(),letterType.getLetterTypeName());
		if(result)
		{
			addActionMessage("Record saved successfully");
		}
		else
		{
			addActionMessage("Error while saving the record");
		}
		}
		else
		{
			result=model.update(letterType.getLetterTypeId(),letterType.getLetterTypeName());
			if(result)
			{
				addActionMessage("Record updated successfully");
			}
			else
			{
				addActionMessage("Error while updating the record");
			}
		}
		reset();
		model.terminate();
		return SUCCESS;
	}
	
	public String delete()
	{
		boolean result=false;
		LetterTypeMasterModel model=new LetterTypeMasterModel();
		model.initiate(context, session);
		result=model.delete(letterType.getLetterTypeId()); 
		if(result)
		{
			addActionMessage("Record deleted successfully");
		}
		else
		{
			addActionMessage("Record cannot be deleted \n as they are associated with some other records");
		}
		reset();
		model.terminate();
		return SUCCESS;
	}
	
	public String reset()
	{
		letterType.setLetterTypeId("");
		letterType.setLetterTypeName("");
		
		return SUCCESS;
	}
	
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		
		String query = " SELECT LETTERTYPE_ID,LETTERTYPE FROM HRMS_LETTERTYPE "
						+" ORDER BY LETTERTYPE_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = {"Letter Type Id","Letter Type Name" }; 
	

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"20","80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"letterTypeId","letterTypeName"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0,1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		letterType.setMasterMenuCode(917);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		
		return "f9page";
	}

}
