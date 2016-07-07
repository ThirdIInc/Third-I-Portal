package org.struts.action.TravelManagement.Master;

import org.apache.struts.action.Action;
import org.paradyne.bean.TravelManagement.Master.CityGrade;
import org.paradyne.model.TravelManagement.Master.CityGradeModel;
import org.paradyne.model.TravelManagement.Master.HotelMasterModel;
import org.paradyne.model.recruitment.InterviewAssessMasterModel;
import org.struts.lib.ParaActionSupport;

public class CityGradeAction extends ParaActionSupport {


	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CityGradeAction.class);
	CityGrade cityGrade;
	String poolDir = "";
	String fileName = "";
	
	public void prepare_local() throws Exception {
		cityGrade = new CityGrade();
		cityGrade.setMenuCode(1084);
	}
	public Object getModel() {
		
		return cityGrade ;
	}
	public CityGrade getCityGrade() {
		return cityGrade;
	}
	public void setCityGrade(CityGrade cityGrade) {
		this.cityGrade = cityGrade;
	}
	
/**This input function is get called for displaying Onload List*/
	public String input() throws Exception {

		CityGradeModel model = new CityGradeModel();
		model.initiate(context, session);
		
		model.intData(cityGrade, request);
		
		model.terminate();
		getNavigationPanel(1);
		return "input";
	}
	
	
	
	public String addNew() {
		try {
			
			getNavigationPanel(2);
			return "addnew";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		CityGradeModel  model = new CityGradeModel ();
		model.initiate(context, session);
		model.intData(cityGrade, request);
		
		model.terminate();
	
	}
	
	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		CityGradeModel model = new CityGradeModel();
		model.initiate(context, session);
		model.intData(cityGrade, request);
		getNavigationPanel(1);
		model.terminate();
		
		return "input";
	}
	
/**This method is called on save button for saving records*/
	
	public String save() throws Exception {
		try {
			CityGradeModel model = new CityGradeModel();
			model.initiate(context, session);
			boolean result;
System.out.println(" in add cityGrade.getHiddencode()---"+cityGrade.getHiddencode());
			if (cityGrade.getHiddencode().equals("") && !cityGrade.getHiddencode().equals("null")) {
				result = model.addData(cityGrade);
				if (result) {
					addActionMessage(getMessage("save"));
				} else {
					addActionMessage(getMessage("duplicate"));
					getNavigationPanel(2);
					reset();// new added
				}
			} else {
System.out.println("m in update city method now>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+cityGrade.getHiddencode());
				result = model.update(cityGrade);

				if (result) {
					addActionMessage(getMessage("update"));
					
					 

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();// new added

					 
				}

			}
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		
		getNavigationPanel(3);
		cityGrade.setEnableAll("N");
		return "addnew";

	}
	
/**This method is called on reset button*/
	
	public String reset() throws Exception {
		try {
			cityGrade.setGradeName("");
			cityGrade.setGradeCities("");
			cityGrade.setHiddencode("");			
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Error in reset" + e);
		}
		return "addnew";
	}
	
	
	public String setRecord() throws Exception {
		try{
			CityGradeModel model = new CityGradeModel();
			model.initiate(context, session);
			//model.getRecord(cityGrade);
			model.terminate();
			getNavigationPanel(3);
			
		}catch(Exception e){
			logger.error("Exception in InterviewAssessMaster - setRecord"+e);
		}
		
		getNavigationPanel(3);
		cityGrade.setEnableAll("N");
		return "addnew";
	}
	
	
	/*This function called when edit button clicked on jsp after record get saved*/ 
	public String edit() throws Exception {
		try {
			CityGradeModel model = new CityGradeModel();
			model.initiate(context, session);
			model.calforedit(cityGrade);
			model.terminate();
			getNavigationPanel(2);
			//cityGrade.setEnableAll("N");
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return "addnew";
	}
	
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		try {
			CityGradeModel model = new CityGradeModel();
			model.initiate(context, session);
			model.calforedit(cityGrade);
			getNavigationPanel(3);
			cityGrade.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addnew";
	}
	
	/**This method is called on back button*/
	public String cancel() {
		try {
			
			getNavigationPanel(1);
			
			reset();
			
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			
			return "input";
		}
		catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	
	/**following function is called when delete button is clicked in the jsp page*/
	
	public String deleteChkRecords() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		CityGradeModel model = new CityGradeModel();

		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(cityGrade, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
			reset();
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.intData(cityGrade, request);
		getNavigationPanel(1);
		model.terminate();

		return "input";

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		CityGradeModel model = new CityGradeModel();
		model.initiate(context, session);
		boolean result = model.delRecord(cityGrade);
		model.intData(cityGrade, request);
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));
			
		} else {
			addActionMessage(getMessage("no result"));
		}// end of else
		getNavigationPanel(1);
		cityGrade.setGradeName("");
		cityGrade.setGradeCities("");
		return "input";
	}

	
	
	/**This f9action is for Search pop up window */
	public String f9action() throws Exception {
	
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = "  SELECT GRADE_NAME,GRADE_CITIES,GRADE_ID FROM HRMS_CITY_GRADE ORDER BY GRADE_NAME";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		/** This is used for displaying the field name in the search window when you clicked on search button*/

		String[] headers = { getMessage("grade_name"),getMessage("grade_city")};

		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */

		/** this is used to set the fields after clicking on data populated on clicking search button into respective fields such as 
		 * parameter name,description and is active */

		String[] fieldNames = { "gradeName", "gradeCities","hiddencode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1, 2};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "CityGrade_setRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
		
	}
	
	
	
	
}
