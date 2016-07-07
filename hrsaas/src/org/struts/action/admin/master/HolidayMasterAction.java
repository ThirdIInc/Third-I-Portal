package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.HolidayMaster;
import org.paradyne.model.admin.master.HolidayMasterModel;

/*
 * author:Pradeep Kumar Sahoo
 * Date:17.07.2007
 */

/*To view  date  and  description */
public class HolidayMasterAction extends org.struts.lib.ParaActionSupport {

	HolidayMaster hm = null;

	public HolidayMaster getHm() {
		return hm;
	}

	public void setHm(HolidayMaster hm) {
		this.hm = hm;
	}

	public Object getModel() {

		return hm;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		hm = new HolidayMaster();
		hm.setMenuCode(249);

	}

	// public String save()throws Exception {

	// logger.info("1");
	// HolidayMasterModel model = new HolidayMasterModel();
	// logger.info("2");
	// model.initiate(context,session);
	// logger.info("3");
	// boolean result=false,
	// boolean result1 ;
	// String str="";
	// result1=model.checkDate(hm);
	// if(result1){
	// str=model.modHoliday(hm);
	// }
	// else{
	// str=model.addHoliday(hm);
	// }
	// model.terminate();

	// addActionMessage(getText(str));
	//		
	// return SUCCESS;
	// }
	
	
	public String input() throws Exception {
		hm.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
//  to display the save mode
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "holidayData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
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
	/**
	 * To delete any record after selecting
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		HolidayMasterModel model = new HolidayMasterModel();

		model.initiate(context, session);
		boolean result;
		
		result = model.deleteHoliday(hm);
		
		if (result) {
			addActionMessage(getMessage("delete"));
			hm.setDesc("");
			hm.setHoliDate("");
			hm.setHidedate("");

		}//end of if
		else {
			addActionMessage(getMessage("del.error"));
		}//end of else
		model.Data(hm, request);
		getNavigationPanel(1);

		model.terminate();
		return "success";
	}

	/**
	 * to insert record in database
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		HolidayMasterModel model = new HolidayMasterModel();
		model.initiate(context, session);
		boolean result;
		//System.out.println("HoliDate --->  "+hm.getHoliDate());
		//System.out.println("HideDate --->  "+hm.getHidedate());
		if (hm.getHidedate().equals("")) {
			//logger.info("begin to call add method");
			result = model.addHoliday(hm);
			//logger.info("return from   add method");

			if (result) {
				hm.setHidedate(hm.getHoliDate());
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "holidayData";

			}//end of if
			else {
				addActionMessage("Holiday Already Exist for this Date");
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}
		else{
			result = model.modHoliday(hm);
			model.Data(hm, request);
			if (result) {
				addActionMessage(getMessage("update"));
				//model.Data(hm, request);
				//model.terminate();
				getNavigationPanel(3);
				return "holidayData";
				//return reset();
			} //end of if
			else {
				addActionMessage("Holiday can't be Modified for this Date..!this Holiday Date Already Exist");
				reset();
				getNavigationPanel(1);
				return "success";

			}
			
			
		}//end of nested if
		/*model.Data(hm, request);
		model.terminate();
		//hm.setFlag("false");
		return "success";
*/
	}

	/**
	 * to modify record in database
	 * 
	 * @return String
	 * @throws Exception
	 */
	/*public String update() throws Exception {
		logger.info("in delete");
		HolidayMasterModel model = new HolidayMasterModel();

		model.initiate(context, session);
		boolean result;

		result = model.modHoliday(hm);
		model.Data(hm, request);
		if (result) {
			addActionMessage(getMessage("update"));
			model.Data(hm, request);
			//model.terminate();
			getNavigationPanel(3);
			return "holidayData";
			//return reset();
		} //end of if
		else {
			addActionMessage("Holiday can't be Modified for this Date..!this Holiday Date Already Exist");
			reset();
			getNavigationPanel(1);
			//return "success";

		}//end of else

		model.terminate();
		//hm.setFlag("false");
		return "success";
		//return reset();
	}
*/
	/**
	 * to generate report
	 * 
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {
		/*
		 * HolidayMasterModel model = new HolidayMasterModel();
		 * model.initiate(context,session); //model.getReport(hm);
		 * model.getReport(hm,request,response,context,session);
		 * model.terminate(); //return "report"; return null;
		 */
		// HolidayModel model = new HolidayModel();
		HolidayMasterModel model = new HolidayMasterModel();
		model.initiate(context, session);
		// model.getReport(hm,response);
		String [] label={"Sr.No",getMessage("date"),getMessage("description"),getMessage("hldType"),getMessage("is.active")};
		model.getReport1(hm, request, response, context, session,label);
		model.terminate();
		return null;
	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			hm.setDesc("");
			hm.setHoliDate("");
			hm.setHidedate("");
			hm.setHolidayType("");
			hm.setIsActive("");
			getNavigationPanel(2);
			
		} catch (Exception e) {

			logger.error(" Exception in reset :" + e);
		}
		return "holidayData";
	}

	public String setVal() {
		hm.setFlag("true");
		hm.setHidedate(hm.getHoliDate());
		getNavigationPanel(3);
		return "holidayData";
		//return "success";
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		String query = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY'),NVL(HOLI_DESC,' '),DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),HOLI_TYPE FROM HRMS_HOLIDAY ORDER BY HOLI_DATE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("date"), getMessage("description"),getMessage("is.active") };

		String[] headerWidth = { "25", "40", "25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "holiDate", "desc","isActive", "holidayType" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 ,2,3};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "HolidayMaster_setVal.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * called on load to set the list
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		HolidayMasterModel model = new HolidayMasterModel();
		model.initiate(context, session);

		model.Data(hm, request);
		model.terminate();
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		HolidayMasterModel model = new HolidayMasterModel();
		model.initiate(context, session);
		model.Data(hm, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		HolidayMasterModel model = new HolidayMasterModel();
		model.initiate(context, session);
		model.calforedit(hm);
		// getRecord();

		model.Data(hm, request);
		getNavigationPanel(3);
		hm.setEnableAll("N");
		model.terminate();
		return "holidayData";
	}

	/*
	 * public String calfordelete() { HolidayMasterModel model = new
	 * HolidayMasterModel(); model.initiate(context, session); boolean result;
	 * result = model.calfordelete(hm); if (result) {
	 * addActionMessage(getText("Record Deleted Successfully")); //reseting all
	 * bean varibles hm.setDesc(""); hm.setHoliDate(""); hm.setHidedate(""); }
	 * else { addActionMessage("This record is referenced in other resources.So
	 * cannot delete."); }
	 * 
	 * model.Data(hm, request); model.terminate();
	 * 
	 * return "success"; }
	 */
	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		HolidayMasterModel model = new HolidayMasterModel();

		model.initiate(context, session);
		boolean result = model.deletecheckedRecords(hm, code);

		if (result) {
			addActionMessage(getMessage("delete"));
		}// end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
		reset();
		model.Data(hm, request);
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;

	}

}