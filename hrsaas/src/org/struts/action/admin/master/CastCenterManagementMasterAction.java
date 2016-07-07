package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.CastCenterManagementMaster;

import org.paradyne.model.admin.master.CastCenterManagementModel;

import org.struts.lib.ParaActionSupport;


/**
 * 
 * @author Dilip Kumar
 * Date:23/09/09
 */
public class CastCenterManagementMasterAction extends ParaActionSupport{

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	CastCenterManagementMaster castMaster;
		public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public CastCenterManagementMaster getCastMaster() {
		return castMaster;
	}

	public void setCastMaster(CastCenterManagementMaster castMaster) {
		this.castMaster = castMaster;
	}

		@Override
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
			castMaster = new CastCenterManagementMaster();
			castMaster.setMenuCode(943);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return castMaster;
	}

	/**
	 *  to  disply the navigation pannel( Buttons) in  on load
	 */
	public String input() throws Exception {
		castMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	/**
	 *   to cancel the  mode in page
	 * @return
	 */
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

//  to display the save mode
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "castCenterData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	/**
	 *  to reset  all the fields
	 * @return
	 * @throws Exception
	 */
public String reset() throws Exception{
		
		getNavigationPanel(2);
		return "castCenterData";
	}


/**
 *  to save the record in  database
 * @return
 * @throws Exception
 */
public String save()throws Exception
{
	CastCenterManagementModel model= new CastCenterManagementModel();
	model.initiate(context,session);
	String [] abbr=request.getParameterValues("subCAbbr");
	String [] name=request.getParameterValues("subCName");
	boolean result=false;
		
	if(castMaster.getCastCode().equals("")){
		result=model.addCast(castMaster,abbr,name);
		if (result) {
			addActionMessage(getText("addMessage", ""));
			model.showRecord(castMaster);
			getNavigationPanel(3);
			return "castCenterData";
		} else {
			addActionMessage("Duplicate entry found.");
			reset();
			getNavigationPanel(1);
			return "success";
			
		}
	} else {
	result=model.modCast(castMaster,abbr,name);
	if (result) {
		addActionMessage(getText("modMessage", ""));
		model.showRecord(castMaster);
		//addItem();
		getNavigationPanel(3);
		return "castCenterData";
	} else {
		addActionMessage("Duplicate entry found.");
		reset();
		getNavigationPanel(1);
		return "success";
	}
	
	}
	
}


/** to display the list / Recrds in onload
 * 
 */
public void prepare_withLoginProfileDetails() throws Exception {
	
	CastCenterManagementModel model= new CastCenterManagementModel();
		
		model.initiate(context,session);
		
		model.castCenterListData(castMaster,request);
		model.terminate();
}
/**
 *  this is used to display pagination
 * @return
 * @throws Exception
 */
public String callPage() throws Exception {
	
	CastCenterManagementModel model= new CastCenterManagementModel();
		model.initiate(context,session);
		model.castCenterListData(castMaster,request);
		getNavigationPanel(1);
		model.terminate();
	 return SUCCESS;
	
	}
/**
 * To edit any record in the list by double clicking on it
 * @return String
 * @throws Exception
 */
public String calforedit() throws Exception {
	CastCenterManagementModel model = new CastCenterManagementModel();

	model.initiate(context, session);
	castMaster.setCastCode(castMaster.getHiddencode());
	model.calforedit(castMaster);
	showRecord();	
	model.castCenterListData(castMaster,request);
	getNavigationPanel(2);
	model.terminate();
	return "castCenterData";
}

/**
 *  to modify the subcast list in double click 
 * @return
 * @throws Exception
 */
public String edit()throws Exception {	
	logger.info("inside edit");
	String [] srNo=request.getParameterValues("srNo");
	String [] abbr=request.getParameterValues("castCAbbr");
	String [] name=request.getParameterValues("castCName");
	
	CastCenterManagementModel model = new CastCenterManagementModel();
	model.initiate(context,session);
	
	model.addItem(castMaster, srNo, abbr, name,0);
	getNavigationPanel(3);
	model.terminate();
	return "castCenterDatas";
	//	return SUCCESS;
}


/**
 * To delete one or more records from the list
 * @return String
 * @throws Exception
 */
public String delete1() throws Exception {
	String code[] = request.getParameterValues("hdeleteCode");

	CastCenterManagementModel model = new CastCenterManagementModel();

	model.initiate(context, session);
	boolean result = model.deleteList(castMaster, code);
	
	if(result) {
		addActionMessage(getMessage("delete"));
		reset();
	} else {
		addActionMessage(getMessage("multiple.del.error"));
	}// end of else

	model.castCenterListData(castMaster,request);
	getNavigationPanel(1);
	model.terminate();
	
	return "success";
}


/**
 *  to delete the record 
 * @return
 * @throws Exception
 */
public String delete () throws Exception{
	boolean result;
	CastCenterManagementModel model = new CastCenterManagementModel();
	System.out.println("hi........in delete");
	model.initiate(context,session);
	result=model.deleteCast(castMaster);
	if(result){
		addActionMessage("Record deleted successfully !");
		reset();
	}
	else 
		addActionMessage("This record is referenced in other resources.So cannot be deleted. !");
	model.castCenterListData(castMaster,request);
	getNavigationPanel(1);
	model.terminate();
	
	return SUCCESS;
}


/**
 * following function is called to get the Pdf report for list of  Onsite Posting Master  records
 * 
 */
public String report(){
	getNavigationPanel(3);
	CastCenterManagementModel model = new CastCenterManagementModel();
	model.initiate(context,session);
	String[]label={"Sr.No",getMessage("cast.abbr"),getMessage("cast.name")};
	model.generateReport(castMaster,response,label);
	model.terminate();
	return null;
}

/**
 *  to delete the  one subcast data  in the  records
 * @return
 * @throws Exception
 */
public String deleteDtl()throws Exception {
	String code[]=request.getParameterValues("hdeleteOp");
	String [] abbr=request.getParameterValues("subCAbbr");
	String [] name=request.getParameterValues("subCName");
	CastCenterManagementModel model = new CastCenterManagementModel();
	model.initiate(context,session);
	model.delDtl(castMaster,code,abbr,name);
	getNavigationPanel(2);
	model.terminate();
	return "castCenterData";

}


/**
 *  to insert the records in subcast  table list
 * @return
 * @throws Exception
 */
public String addItem()throws Exception{
	
	String [] srNo=request.getParameterValues("srNo");
	String [] abbr=request.getParameterValues("subCAbbr");
	String [] name=request.getParameterValues("subCName");
	CastCenterManagementModel model= new CastCenterManagementModel();
	model.initiate(context, session);
	if(abbr !=null){
		//model.getDuplicate(castMaster, srNo, abbr, name,1);
		for (int i = 0; i < name.length; i++) {
			if(castMaster.getSubcastAbbr().toUpperCase().equals(""+abbr[i].toUpperCase()+"")){
				addActionMessage("Duplicate entry found.");
				castMaster.setSubCAbbr("");
				castMaster.setSubCName("");
				castMaster.setSubcode("");
				castMaster.setHiddenEdit("");
				getNavigationPanel(2);
				return "castCenterData";
			}
		}
		
	}
	
	if (castMaster.getHiddenEdit().equals("")){
		logger.info("in if cond of edit");
		model.addItem(castMaster, srNo, abbr, name,1);
	}
	else{
		logger.info("in else cond of edit");
		model.moditem(castMaster, srNo, abbr, name,1);
	}
	model.terminate();
	castMaster.setSubcastAbbr("");
	castMaster.setSubcastName("");
	castMaster.setSubcode("");
	castMaster.setHiddenEdit("");
	getNavigationPanel(2);
	return "castCenterData";
}

 /**
  *  to display the subcast recods in the list 
  * @return
  * @throws Exception
  */
public String showRecord () throws Exception{
	
	CastCenterManagementModel model= new CastCenterManagementModel();
	model.initiate(context,session);
	model.showRecord(castMaster);
	getNavigationPanel(3);
	model.terminate();
	return "castCenterData";
}

/**
 *  to seach the record from list
 * @return
 * @throws Exception
 */
public String f9action() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = " SELECT   COST_CENTER_ABBR , COST_CENTER_NAME,COST_CENTER_ID  FROM HRMS_COST_CENTER " 
				+" ORDER BY COST_CENTER_NAME ";		
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={ getMessage("cast.name")};
	
	String[] headerWidth={ "100"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"castAbbr","castName","castCode"};
	
	/**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */ 
	int[] columnIndex={0,1,2};
	
	/**
	 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	String submitFlag = "true";
	
	/**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod="CastCenterManagementMaster_showRecord.action";
	
	/**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}



}
