package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.ReligionMaster;

import org.paradyne.model.admin.master.DivisionModel;
import org.paradyne.model.admin.master.ReligionModel;

/**
 * @author pranali 
 * Date 24-04-07
 */
public class ReligionMasterAction extends org.struts.lib.ParaActionSupport {

	ReligionMaster regMaster;

	public org.paradyne.bean.admin.master.ReligionMaster getRegMaster() {
		return regMaster;
	}

	public Object getModel() {
		return regMaster;
	}

	public void setRegMaster(
			org.paradyne.bean.admin.master.ReligionMaster regMaster) {

		this.regMaster = regMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {

		regMaster = new ReligionMaster();
		regMaster.setMenuCode(39);
	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {
		ReligionModel model = new ReligionModel();
		model.initiate(context, session);

		model.religionData(regMaster, request);
		model.terminate();
	}
	
	public String input() throws Exception {
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "religionData";
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
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		ReligionModel model = new ReligionModel();
		model.initiate(context, session);
		model.religionData(regMaster, request);
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
		ReligionModel model = new ReligionModel();
		model.initiate(context, session);
		model.calforedit(regMaster);

		model.religionData(regMaster, request);
		getNavigationPanel(3);
		regMaster.setEnableAll("N");
		model.terminate();
		return "religionData";
	}

	/*public String calfordelete()
		{
		ReligionModel model = new ReligionModel();	
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(regMaster);
			if(result){
				addActionMessage(getText("record  deleted Successfully"));
				regMaster.setReligionID("");
				regMaster.setReligionName("");	
			}
			else{
				addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
			}
			
			model.religionData(regMaster,request);
			model.terminate();
			
		return "success";
		}*/

	/*public String delete1()throws Exception {
		String code[]=request.getParameterValues("hdeleteCode");
		
		ReligionModel model = new ReligionModel();	
		
		model.initiate(context,session);
		boolean result =model.deleteReligion(regMaster,code);
		if(result)
		{
			addActionMessage(getText("record  deleted Successfully"));
			regMaster.setReligionID("");
			regMaster.setReligionName("");	
			
		}
			 else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
			}
		

		
		model.religionData(regMaster,request);
		model.terminate();
		
		return "success";

	}*/
	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		ReligionModel model = new ReligionModel();

		model.initiate(context, session);
		String result = model.deletecheckedRecords(regMaster, code);

		if (result.equals("true")) {
			addActionMessage(getText("delMessage", ""));

		} //end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}//end of else
		reset();
		model.religionData(regMaster, request);
		getNavigationPanel(1);
		model.terminate();

		 return "success";

	}

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String save() throws Exception {
		ReligionModel model = new ReligionModel();

		model.initiate(context, session);

		boolean result;
		if (regMaster.getReligionID().equals("")) {

			result = model.addReligion(regMaster);
			if (result) {
				addActionMessage(getText("addMessage", ""));
				getNavigationPanel(3);
				return "religionData";	
				} //end of if
			else {
				addActionMessage("Duplicate entry found.");
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of nested if
		else {

			result = model.modReligion(regMaster);
			if (result) {
				addActionMessage(getText("modMessage", ""));
				getNavigationPanel(3);
				return "religionData";
			} //end of if
			else {
				addActionMessage("Duplicate entry found.");
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of else
	//	model.religionData(regMaster, request);
	//	model.terminate();

	//	regMaster.setReligionID("");
	//	regMaster.setReligionName("");

	//	return "success";
	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		try {
			regMaster.setReligionID("");
			regMaster.setReligionName("");
			regMaster.setIsActive("");
			getNavigationPanel(2);
			//return "success";
		} catch (Exception e) {

			logger.error("Error in reset" + e);
		}
		return "religionData";
		//return "success";
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {
		ReligionModel model = new ReligionModel();
		model.initiate(context, session);
		boolean result;

		result = model.deleteReligion(regMaster);
		if (result) {
			addActionMessage(getText("delMessage", ""));
		} //end of if
		else {
			addActionMessage("Record can't be deleted as \n it is associated with some other record");
		}//end of else

		regMaster.setReligionID("");
		regMaster.setReligionName("");

		model.religionData(regMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
	}

	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {

		ReligionModel model = new ReligionModel();

		model.initiate(context, session);

		/*model.getReligionReport(regMaster);
		
		 model.terminate();	
		 return "report";  */
		String[]label={"Sr.No",getMessage("religionname"),getMessage("is.active")};
		model.getReport(regMaster, request, response, context,label);
		model.terminate();
		return null;
	}
	public String recordData() throws Exception {
		ReligionModel model = new ReligionModel();
		model.initiate(context, session);
		
		model.data(regMaster);
		getNavigationPanel(3);
		model.terminate();
		return "religionData";
	}

	public String f9action() throws Exception {


		String query = " SELECT RELIGION_NAME, DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'), RELIGION_ID FROM HRMS_RELIGION ORDER BY upper(RELIGION_NAME)";

		String[] headers = { getMessage("religionname"), getMessage("is.active") };

		String[] headerWidth = { "50" , "50" };

		String[] fieldNames = { "regMaster.religionName", "isActive" , "regMaster.religionID" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "ReligionMaster_recordData.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
