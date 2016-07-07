package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.CheckListMaster;
import org.paradyne.model.admin.master.CheckListModel;
//import org.paradyne.model.admin.master.IndusModel;
import org.paradyne.model.admin.master.RankModel;

/**
 * @author pranali 
 * Date 26-04-07
 * modified by Prasad
 */

public class CheckListMasterAction extends org.struts.lib.ParaActionSupport {

	CheckListMaster checkListMaster;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public org.paradyne.bean.admin.master.CheckListMaster getCheckListMaster() {
		return checkListMaster;
	}

	public Object getModel() {
		return checkListMaster;
	}

	public void setCheckListMaster(
			org.paradyne.bean.admin.master.CheckListMaster checkListMaster) {
		this.checkListMaster = checkListMaster;
	}

	public void prepare_local() throws Exception {

		checkListMaster = new CheckListMaster();
		checkListMaster.setMenuCode(211);
	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {

		CheckListModel model = new CheckListModel();
        model.initiate(context, session);
        
        model.getCheckListType(checkListMaster);
		//model.checkData(checkListMaster, request);
		model.terminate();
	}
	
	/**
	 * following function is called when the page is loaded.
	 */
	public String input() throws Exception {
		checkListMaster.setEnableAll("N");
		CheckListModel model=new CheckListModel();
		model.initiate(context, session);
		model.checkData(checkListMaster, request);
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;
	}

	
	
	/*
	 * following function is called when Edit button is clicked
	 */
	public String edit() throws Exception{
		getNavigationPanel(2);
		CheckListModel model=new CheckListModel();
		model.initiate(context, session);
		model.checkData(checkListMaster, request);
		checkListMaster.setEditFlag("true");
		 model.getCall(checkListMaster);
		model.terminate();
		return "success";
	}
	/*
	 *followingf function is used for cancel 
	 */
	public String cancelSecond() throws Exception{
		getNavigationPanel(1);
		CheckListModel model=new CheckListModel();
		model.initiate(context, session);
		model.checkData(checkListMaster, request);
		reset();
		model.terminate();
		return "view";
	}

	public String cancelFirst() throws Exception{
		String str;
		CheckListModel model=new CheckListModel();
		model.initiate(context, session);
		if(checkListMaster.getEditFlag().equals("true")){
			model.getCall(checkListMaster);
			model.checkData(checkListMaster, request);
			getNavigationPanel(3);
			checkListMaster.setCheckListView(true);
			str="view";
		}else{
			getNavigationPanel(1);
			model.checkData(checkListMaster, request);
			reset();
			str="view";
		}
		return str;
	}

	
	/*
	 * following function is called when Add New button is clicked
	 */
	public String addNew(){
		/*getNavigationPanel(2);
		CheckListModel model=new CheckListModel();
		model.initiate(context, session);
		model.checkData(checkListMaster, request);
		checkListMaster.setEditFlag("false");
		model.terminate();
		return "success";*/
		try {
			getNavigationPanel(2);
			return "checkListData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	
	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancel() {
		CheckListModel model=new CheckListModel();
		model.initiate(context, session);
		try {
			reset();
			prepare_withLoginProfileDetails();
			model.checkData(checkListMaster, request);
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public String callPage() throws Exception {
		getNavigationPanel(1);	
		CheckListModel model = new CheckListModel();
		model.initiate(context, session);
		model.checkData(checkListMaster, request);
		getNavigationPanel(1);
	//	reset();
		model.terminate();
		return "success";
	//	return "view";
	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String calforedit() throws Exception {
		//getNavigationPanel(3);
		CheckListModel model = new CheckListModel();
		model.initiate(context, session);
		checkListMaster.setCheckCode(checkListMaster.getHiddencode());
		model.calforedit(checkListMaster);
		//checkListMaster.setCheckListView(true);
		model.checkData(checkListMaster, request);
		getNavigationPanel(3);
		checkListMaster.setEnableAll("N");
		//checkListMaster.setEditFlag("false");
		model.terminate();
		return "checkListData";
	}

	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete1() throws Exception {
	//	getNavigationPanel(1);
		String code[] = request.getParameterValues("hdeleteCode");
		CheckListModel model = new CheckListModel();
		model.initiate(context, session);
		String result = model.deletecheckedRecords(checkListMaster,code);
		if (result.equals("true")) {
			addActionMessage(getText("delMessage", ""));
			reset();
		}//end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}//end of else
		model.checkData(checkListMaster, request);
		getNavigationPanel(1);
	//	reset();
		model.terminate();
		return "success";
		//return "view";
	}

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		String result = "";
		String page="";
		CheckListModel model = new CheckListModel();
		model.initiate(context, session);
		if(checkListMaster.getCheckCode().equals(""))
		{
			result = model.addCheckList(checkListMaster);
			if (result.equals("saved")) {
				//getNavigationPanel(3);
			//	checkListMaster.setCheckListView(true);
				addActionMessage("Record saved successfully!");
				getNavigationPanel(3);
				page="checkListData";
			}//end of if
			else if(result.equals("duplicate")) {
				//getNavigationPanel(2);
				addActionMessage("Duplicate entry found!");
				//reset();
				getNavigationPanel(1);
				//checkListMaster.setEditFlag("false");
				page="success";
			}//end of else
			else{
			//	getNavigationPanel(2);
				addActionMessage("Record can not be added!");
				getNavigationPanel(1);
				//checkListMaster.setEditFlag("false");
				page="success";
			}
		}
		else
		{
			result=model.modCheckList(checkListMaster);
			if (result.equals("modified")) {
			//	getNavigationPanel(3);
				//checkListMaster.setCheckListView(true);
				addActionMessage("Record updated successfully!");
				getNavigationPanel(3);
				page="checkListData";
				//page="view";
			}//end of if
			else if(result.equals("duplicate")) {
				//getNavigationPanel(2);
				addActionMessage("Duplicate entry found!");
				//checkListMaster.setEditFlag("false");
				//reset();
				getNavigationPanel(1);
				page="success";
			}//end of else
			else{
				//getNavigationPanel(2);
				addActionMessage("Duplicate entry found!");
				//checkListMaster.setEditFlag("false");
				//reset();
				getNavigationPanel(1);
				page="success";
			}
		}
		
		/*model.checkData(checkListMaster, request);
		//checkListMaster.setFlag("true");
		logger.info("Model Terminated");*/
		model.terminate();
		return page;
	}
	
/*	
	
	public String update(){
		String result = "";
		CheckListModel model = new CheckListModel();
		model.initiate(context, session);
		logger.info("Context initiated");
	
		result = model.modCheckList(checkListMaster);
		logger.info("result in update method of checklist model--->"+result);
		if (result.equals("modified")) {
			addActionMessage("Record updated successfully.");
		} //end of if
		else if(result.equals("duplicate")) {
			addActionMessage("Duplicate entry found.");
		}//end of else
		else{
			addActionMessage("Record can not be added.");
		}
		model.checkData(checkListMaster, request);
		model.terminate();
		return "success";
		
	}
*/
	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		try {
			checkListMaster.setCheckCode("");
			checkListMaster.setCheckName("");
			checkListMaster.setCheckType("");
			checkListMaster.setDesciption("");
			checkListMaster.setStatus("");
			getNavigationPanel(2);
			//return "success";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in reset");
		}
		return "checkListData";
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {
		getNavigationPanel(1);
		CheckListModel model = new CheckListModel();
		model.initiate(context, session);
		boolean result;
		result = model.deleteCheckList(checkListMaster);
		if (result) {
			addActionMessage("Record deleted successfully !");
			
		}//end of if
		else {
			addActionMessage("Record can't be deleted as \n it is associated with some other record");
		}//end of else
		model.checkData(checkListMaster, request);
	getNavigationPanel(1);
		//reset();
	
	checkListMaster.setCheckCode("");
	checkListMaster.setCheckName("");
	checkListMaster.setCheckType("");
	checkListMaster.setDesciption("");
	checkListMaster.setStatus("");
		model.terminate();
		return "success";
		//return "view";
	}

	public String call()throws Exception 
	 {
	 CheckListModel model = new CheckListModel();
	 model.initiate(context,session);
	 model.getCall(checkListMaster);
	 //checkListMaster.setCheckListView(true);
	 model.checkData(checkListMaster, request);
	 getNavigationPanel(3);
	 model.terminate();	
	 return "checkListData"; 
	// return "view";    
	 }

	public String f9action() throws Exception {

		String query = " SELECT CHECK_NAME,DECODE(CHECK_TYPE,'J','Joining CheckList','M','Medical CheckList','T','Transfer CheckList','I','Interview CheckList','B','Background Verification CheckList','P','Prejoining CheckList'),decode(CHECK_STATUS,'A','Active','D','Deactive'),CHECK_CODE FROM HRMS_CHECKLIST  ORDER BY upper(CHECK_NAME)";

		String[] headers = {getMessage("clist.name"),getMessage("clist.type"),getMessage("clist.stat") };

		String[] headerWidth = { "20", "20", "10" };

		String[] fieldNames = { "checkName", "checkType","status","checkCode" };
		
		int[] columnIndex = {0,1,2,3};

		String submitFlag = "true";

		String submitToMethod = "CheckListMaster_call.action";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * following function is called to get the Pdf report for list of CheckList Master records
	 * 
	 */
	public String report(){
		getNavigationPanel(3);
		CheckListModel model = new CheckListModel();	
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("clist.name"),getMessage("clist.type"),getMessage("clist.stat")};
		model.generateReport(checkListMaster,response,label);
		model.terminate();
		return null;
	}
}
