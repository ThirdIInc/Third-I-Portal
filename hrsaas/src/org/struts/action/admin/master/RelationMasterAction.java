package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.RelationMaster;

import org.paradyne.model.admin.master.EmpTypeModel;
import org.paradyne.model.admin.master.RelationModel;
import org.paradyne.model.payroll.PayrollZoneMasterModel;

/**
 * @author sunil 
 *
 */
public class RelationMasterAction extends org.struts.lib.ParaActionSupport {

	//private static final long serialVersionUID = 7047317819789938757L;

	RelationMaster relMaster;

	/**
	 * @return the relMaster
	 */
	public org.paradyne.bean.admin.master.RelationMaster getRelMaster() {
		return relMaster;
	}

	public Object getModel() {

		return relMaster;
	}

	/**
	 * @param relMaster the relMaster to set
	 */
	public void setRelMaster(
			org.paradyne.bean.admin.master.RelationMaster relMaster) {
		this.relMaster = relMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		relMaster = new RelationMaster();
		relMaster.setMenuCode(38);
	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {

		RelationModel model = new RelationModel();

		model.initiate(context, session);

		model.show1(relMaster, request);
		model.terminate();

	}

	public String input() throws Exception {
		relMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "relationData";
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

		RelationModel model = new RelationModel();
		model.initiate(context, session);
		model.show1(relMaster, request);
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
		RelationModel model = new RelationModel();
		model.initiate(context, session);
		model.calforedit(relMaster);

		model.show1(relMaster, request);
		getNavigationPanel(3);
		relMaster.setEnableAll("N");
		model.terminate();
		return "relationData";
	}

	/*public String calfordelete()
	 {
	 RelationModel  model = new 	RelationModel();
	 model.initiate(context,session);
	 boolean result;
	 result = model.calfordelete(relMaster);
	 if(result){
	 addActionMessage(getText("record  deleted Successfully"));
	 relMaster.setRelationCode("");
	 relMaster.setRelationName("");		
	 }
	 else{
	 addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
	 }
	
	 model.show1(relMaster,request);
	 model.terminate();
	
	 return "success";
	 }
	 */

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String save() throws Exception {
		
		RelationModel model = new RelationModel();

		model.initiate(context, session);

		boolean result;

		if (relMaster.getRelationCode().equals("")) {
			result = model.addRelation(relMaster);
			if (result) {
				addActionMessage("Record saved successfully");
			//	relMaster.setRelationCode("");
				//relMaster.setRelationName("");
				getNavigationPanel(3);
				return "relationData";
			}//end of if 
			else {
				addActionMessage("Duplicate entry found");
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of nested if
		else {
			result = model.modRelation(relMaster);
			if (result) {
				addActionMessage("Record modified successfully");
				//relMaster.setRelationCode("");
			//	relMaster.setRelationName("");
				getNavigationPanel(3);
				return "relationData";
			}
			//end of if
			else {
				addActionMessage("Duplicate entry found");
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of else
		//logger.info("4");

		//model.show1(relMaster, request);
		//model.terminate();
		//logger.info("5");
		//return reset();
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {

		RelationModel model = new RelationModel();
		model.initiate(context, session);
		boolean result = model.deleteRelation(relMaster);
		model.show1(relMaster, request);
		model.terminate();
		if (result) {
			addActionMessage(getText("delMessage", ""));
		} //end of if
		else {
			addActionMessage("Record can't be deleted as \n it is associated with some other record");
		}//end of else
		relMaster.setRelationCode("");
		relMaster.setRelationName("");
		relMaster.setIsActive("");
		getNavigationPanel(1);
		return "success";

	}


	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		RelationModel model = new RelationModel();

		model.initiate(context, session);
		boolean  result = model.deletecheckedRecords(relMaster, code);
		System.out.println("result is"+result);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} //end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}//end of else
		reset();
		model.show1(relMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
	//	return reset();

	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		relMaster.setRelationCode("");
		relMaster.setRelationName("");
		relMaster.setIsActive("");
		getNavigationPanel(2);
		return "relationData";

	}

	public String report() throws Exception {
		RelationModel model = new RelationModel();
		model.initiate(context, session);
		String [] label={"Sr.No",getMessage("relationname"),getMessage("is.active")};
		model.getReport(relMaster, request, response, context, session,label);
		model.terminate();
		return null;
	}

	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String RelationRecord() throws Exception {
		RelationModel model = new RelationModel();
		model.initiate(context, session);
		model.getRelationRecord(relMaster);
		getNavigationPanel(3);
		model.terminate();
		return "relationData";
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT RELATION_NAME ,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'), RELATION_CODE FROM HRMS_RELATION ORDER BY upper(RELATION_NAME)";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = {  getMessage("relationname"), getMessage("is.active"), "Relation Code" };

		String[] headerWidth = { "50" , "20" , "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "relationName", "isActive", "relationCode" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "RelationMaster_RelationRecord.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}