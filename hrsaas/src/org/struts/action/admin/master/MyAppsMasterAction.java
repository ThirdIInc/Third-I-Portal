package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.MyAppsMaster;
import org.paradyne.model.admin.master.DivisionModel;
import org.paradyne.model.admin.master.MyAppsMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Prajakta.Bhandare
 * @date 11 Feb 2013
 */
public class MyAppsMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	private static final long serialVersionUID = 1L;
	MyAppsMaster myApps;
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return myApps;
	}
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		myApps =new MyAppsMaster();
		myApps.setMenuCode(2335);
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	public String input(){
		myApps.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		MyAppsMasterModel model = new MyAppsMasterModel();
		model.initiate(context, session);
		model.getData(myApps, request);
		model.terminate();
	}
	
	/** Method to save link record
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception{
		MyAppsMasterModel model = new MyAppsMasterModel();
		model.initiate(context, session);
		boolean result;
		if(myApps.getLinkId().equals("")) {
			logger.info("else addDiv");
			result = model.addLink(myApps);
			if(result) {//if result
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "showData";
			}//end of if result 
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}// end of else
		} else {
			result = model.modLink(myApps);
			model.terminate();
			if(result) {//if reult
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "showData";
			}//end if result
			else {
				addActionMessage(getMessage("duplicate"));
				reset();// new added
				getNavigationPanel(1);
				return "success";
			}// end of else
		}// end of else
		
		
	}
	/** Method to add new link record
	 * @return String
	 * @throws Exception
	 */
	public String addNew() throws Exception{
		try {
			getNavigationPanel(2);
			return "showData";
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** Method to edit link record
	 * @return String
	 * @throws Exception
	 */
	public String callEdit() throws Exception{
		MyAppsMasterModel model = new MyAppsMasterModel();
		model.initiate(context, session);
		model.callEdit(myApps);
		model.editRecord(myApps);
		getNavigationPanel(3);
		myApps.setEnableAll("N");
		return "showData";
				
	}

	/** Method to delete link records
	 * @return String
	 * @throws Exception
	 */
	public String callDelete() throws Exception{
		String code[] = request.getParameterValues("hdeleteCode");

		MyAppsMasterModel model = new MyAppsMasterModel();

		model.initiate(context, session);
		boolean result = model.deleteLink(myApps, code);
		
		if(result) {//if result
			addActionMessage(getMessage("delete"));
			reset();
		}//end of if result
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.getData(myApps, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
		
	}
	
	/** Method to reset all fields
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception{
		myApps.setLinkId("");
		myApps.setLinkName("");
		myApps.setLinkUrl("");
		myApps.setLinkImage("");
		myApps.setLinkSeq("");
		myApps.setLinkDiv("");
		myApps.setLinkDivCode("");
		myApps.setIsActive("");
		getNavigationPanel(2);
		return "showData";
	}
	
	/** Method to generate report
	 * @return String 
	 * @throws Exception
	 */
	public String report() throws Exception{
		MyAppsMasterModel model = new MyAppsMasterModel();
		model.initiate(context, session);
		String[]label={"Sr.No",getMessage("Name"),getMessage("url"),getMessage("image"),getMessage("div"),getMessage("sequence"),
				getMessage("is.active")};
		model.getReport(myApps, request, response, context,label);
		model.terminate();
		return null;
	}
	
	/** Method to delete link record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception{
		MyAppsMasterModel model = new MyAppsMasterModel();
		model.initiate(context, session);
		
		boolean result = model.delete(myApps);
		model.getData(myApps, request);
		model.terminate();
		if(result) {//if result
			addActionMessage(getMessage("delete"));

		} //end of if result
		else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		getNavigationPanel(1);
		myApps.setLinkId("");
		myApps.setLinkName("");
		return "success";
	}
	
	public String cancel() throws Exception{
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
	
	public String getRecords() throws Exception{
		MyAppsMasterModel model = new MyAppsMasterModel();
		model.initiate(context, session);
		model.callEdit(myApps);
		model.editRecord(myApps);
		getNavigationPanel(2);
		myApps.setEnableAll("Y");
		return "showData";
	}
	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		MyAppsMasterModel model = new MyAppsMasterModel();
		model.initiate(context, session);
		model.getData(myApps, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	/** Method to search particular link record
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		 String query = " SELECT LINK_NAME, LINK_ID" 
	    + " FROM HRMS_PORTAL_APPS "
		+ " ORDER BY LINK_NAME "; 

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("Name")};

		String[] headerWidth = { "50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"linkName", "linkId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "MyAppsMaster_data.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	public String data(){
		MyAppsMasterModel model = new MyAppsMasterModel();
		model.initiate(context, session);
		model.callEdit(myApps);
		model.editRecord(myApps);
		getNavigationPanel(3);
		model.terminate();
		return "showData";
	}
	/**  Method to search multiple divisions at a time
	 * @return String
	 */
	public String f9applDiv(){
		String query ="SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ";
	 	if(myApps.getUserProfileDivision() !=null && myApps.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+myApps.getUserProfileDivision() +")"; 
			query+= " ORDER BY UPPER (DIV_NAME) ";
		String header =getMessage("division");
		String textAreaId =request.getParameter("linkDiv");
				
		String hiddenFieldId =request.getParameter("linkDivCode");
		
		String submitFlag ="";
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
}
