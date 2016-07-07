package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.CategoryMaster;

import org.paradyne.model.admin.master.CategoryModel;
import org.paradyne.model.admin.master.EmpTypeModel;

/**
 * @author Hemant Date 26-04-07
 */

public class CategoryMasterAction extends org.struts.lib.ParaActionSupport {

	CategoryMaster catgMaster;

	public org.paradyne.bean.admin.master.CategoryMaster getCatgMaster() {
		return catgMaster;
	}

	public Object getModel() {
		return catgMaster;
	}

	public void setCatgMaster(
			org.paradyne.bean.admin.master.CategoryMaster catgMaster) {
		this.catgMaster = catgMaster;
	}

	public String execute() throws Exception {
		return "success";
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {

		catgMaster = new CategoryMaster();
		catgMaster.setMenuCode(33);
	}

	/** called on load to set the list* */
	public void prepare_withLoginProfileDetails() throws Exception {

		CategoryModel model = new CategoryModel();

		model.initiate(context, session);

		model.categoryData(catgMaster, request);
		model.terminate();
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
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String input() throws Exception {
		catgMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
//  to display the save mode
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "categoryData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String callPage() throws Exception {

		CategoryModel model = new CategoryModel();
		model.initiate(context, session);
		model.categoryData(catgMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";

	}

	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		CategoryModel model = new CategoryModel();
		model.initiate(context, session);
		model.calforedit(catgMaster);

		model.categoryData(catgMaster, request);
		getNavigationPanel(3);
		catgMaster.setEnableAll("N");
		model.terminate();
		return "categoryData";

	}

	/*
	 * public String calfordelete() { CategoryModel model = new CategoryModel();
	 * model.initiate(context,session); boolean result; result =
	 * model.calfordelete(catgMaster); if(result){
	 * addActionMessage(getText("Record deleted Successfully"));
	 * catgMaster.setCatgID(""); catgMaster.setCatgName("");
	 * catgMaster.setCatgDesc(""); catgMaster.setCatgAge(""); } else{
	 * addActionMessage("One or more records can't be deleted \n as they are
	 * associated with some other record(s) ."); }
	 * 
	 * model.categoryData(catgMaster,request); model.terminate();
	 * 
	 * return "success"; }
	 */

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String save() throws Exception {
		CategoryModel model = new CategoryModel();
		model.initiate(context, session);
		boolean result = false;
		if (catgMaster.getCatgID().equals("")) {
			result = model.addCategory(catgMaster);
			if (result) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "categoryData";
			}// end of if
			else {
				addActionMessage(getMessage("duplicate"));
			reset();
				getNavigationPanel(1);
				return "success";
			}// end of else

			//clear();
		}// end of nested if
		else {
			result = model.modCategory(catgMaster);
			if (result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "categoryData";
			}// end of if
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}// end of else
		}
			/*clear();

		}// end of else
		model.categoryData(catgMaster, request);
		model.terminate();

		return "success";*/
	}

	/**
	 * To clear the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public void clear() {
		catgMaster.setCatgID("");
		catgMaster.setCatgName("");
		catgMaster.setCatgDesc("");
		catgMaster.setCatgAge("");
		catgMaster.setIsActive("");
		getNavigationPanel(1);
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {
		CategoryModel model = new CategoryModel();
		model.initiate(context, session);
		boolean result;
		result = model.deleteCategory(catgMaster);
		if (result) {
			addActionMessage(getMessage("delete"));
			catgMaster.setCatgID("");
			catgMaster.setCatgName("");
			catgMaster.setCatgDesc("");
			catgMaster.setCatgAge("");
			catgMaster.setIsActive("");
		}// end of if
		else {
			addActionMessage(getMessage("del.error"));
			catgMaster.setCatgID("");
			catgMaster.setCatgName("");
			catgMaster.setCatgDesc("");
			catgMaster.setCatgAge("");
			catgMaster.setIsActive("");
		}// end of else
		model.categoryData(catgMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
	}

	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		CategoryModel model = new CategoryModel();

		model.initiate(context, session);
		String result = model.deletecheckedRecords(catgMaster, code);

		if (result.equals("true")) {
			addActionMessage(getMessage("delete"));
		}// end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.categoryData(catgMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
		//return reset();

	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		try {
			logger.info("in reset");
			catgMaster.setCatgID("");
			catgMaster.setCatgName("");
			catgMaster.setCatgDesc("");
			catgMaster.setCatgAge("");
			catgMaster.setIsActive("");
			getNavigationPanel(2);
			//return "success";
		} catch (Exception e) {

			logger.error("Error in reset" + e);
		}
		return "categoryData";
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CATG_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),CATG_DESC,CATG_AGE,CATG_ID FROM HRMS_CATG ORDER BY upper(CATG_NAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("categoryname"), getMessage("is.active") };

		String[] headerWidth = { "50" , "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "catgMaster.catgName","catgMaster.isActive","catgMaster.catgDesc","catgMaster.catgAge","catgMaster.catgID"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1,2,3,4 };

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
		String submitToMethod = "CategoryMaster_categoryRecord.action";// CategoryMaster_categoryRecord.action

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String categoryRecord() throws Exception {
		CategoryModel model = new CategoryModel();
		model.initiate(context, session);
		model.getCategoryRecord(catgMaster);
		getNavigationPanel(3);
		model.terminate();
		return "categoryData";
	}

	/**
	 * to generate report
	 * 
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {
		CategoryModel model = new CategoryModel();
		model.initiate(context, session);
		String []label={"Sr.No",getMessage("categoryname"),getMessage("categorydescription"),getMessage("categoryage"),getMessage("is.active")};
		model.getReport(catgMaster, request, response, context,label);
		model.terminate();
		return null;

	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

}