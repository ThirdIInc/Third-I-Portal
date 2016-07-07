package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.EntranceTestMaster;

import org.paradyne.model.recruitment.EntranceTestMasterModel;
import org.struts.lib.ParaActionSupport;

public class EntranceTestMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	EntranceTestMaster entrance;

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		entrance = new EntranceTestMaster();
		entrance.setMenuCode(391);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return entrance;
	}

	public EntranceTestMaster getEntrance() {
		return entrance;
	}

	public void setEntrance(EntranceTestMaster entrance) {
		this.entrance = entrance;
	}

	/**
	 * called on load to set the list
	 */
	public void prepare_withLoginProfileDetails() throws Exception {

		EntranceTestMasterModel model = new EntranceTestMasterModel();

		model.initiate(context, session);

		model.entData(entrance, request);
		model.terminate();
	}

	public String callPage() throws Exception {

		EntranceTestMasterModel model = new EntranceTestMasterModel();
		model.initiate(context, session);
		model.entData(entrance, request);
		model.terminate();
		return SUCCESS;

	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		EntranceTestMasterModel model = new EntranceTestMasterModel();
		model.initiate(context, session);
		model.calforedit(entrance);

		model.entData(entrance, request);
		model.terminate();
		return "success";
	}

	/*	
	public String calfordelete()
		{
		EntranceTestMasterModel model= new EntranceTestMasterModel();
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(entrance);
			if(result){
				addActionMessage(getText("Record deleted Successfully"));
				entrance.setExamCode("");
				entrance.setExamName("");
				entrance.setTotalMarks("");
				entrance.setPassMarks("");		}
			else{
				addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
			}
			
			model.entData(entrance,request);
			model.terminate();
			
		return "success";
		}
	 */
	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		EntranceTestMasterModel model = new EntranceTestMasterModel();

		model.initiate(context, session);
		boolean result = model.deleteTest(entrance, code);
		if (result) {
			addActionMessage("Record deleted successfully");
			reset();

		}//end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}//end of else

		model.entData(entrance, request);
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
		//boolean flag;
		EntranceTestMasterModel model = new EntranceTestMasterModel();
		/*model.initiate(context,session);
		if(entrance.getExamCode()==null||entrance.getExamCode().equals("")) //Insert Data
		{
		  flag = model.saveData(entrance);
		if(flag)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Already exist");
		}
		else // Update Existing Data
		{
			flag = model.update(entrance);
			if(flag)
				addActionMessage("Record Modified Successfully");
			else
				addActionMessage("Record Cann't Modified");
		}
		model.terminate();*/
		model.initiate(context, session);
		logger.info("Context initiated");
		boolean result;

		if (entrance.getExamCode().equals("")) {
			logger.info("else addDept");
			result = model.saveData(entrance);
			if (result) {
				addActionMessage(getText("addMessage", ""));
			}//end of if	
			else {
				addActionMessage("Duplicate entry found");
			}//end of else
		}//end of nested if
		else {
			logger.info("else modCity");

			result = model.update(entrance);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}//end of if
			else {
				addActionMessage("Duplicate entry found");
			}//end of else
		}//end of else
		model.entData(entrance, request);
		logger.info("Model Terminated");
		model.terminate();

		return reset();
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {
		EntranceTestMasterModel model = new EntranceTestMasterModel();
		model.initiate(context, session);
		model.delete(entrance);
		model.terminate();
		addActionMessage("Record Deleted Succcesfully");
		model.entData(entrance, request);
		reset();
		return SUCCESS;
	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() {
		entrance.setExamCode("");
		entrance.setExamName("");
		entrance.setTotalMarks("");
		entrance.setPassMarks("");
		return SUCCESS;
	}

	/*public String report() throws Exception {
		
		EntranceTestMasterModel model = new EntranceTestMasterModel();
		model.initiate(context,session);
		model.getdistReport(entrance);
		model.terminate();
		return "report";

	}*/

	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {

		EntranceTestMasterModel model = new EntranceTestMasterModel();
		model.initiate(context, session);

		//model.getdistReport(entrance);
		model.getReport(entrance, request, response, context);
		model.terminate();
		return null;
	}

	public String f9actionEntranceTest() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EXAM_CODE,EXAM_NAME,EXAM_TOTAL_MARKS,EXAM_PASS_MARKS FROM HRMS_ENTR_EXAM ORDER BY EXAM_CODE";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { "Exam Code", "Exam Name" };

		String[] headerWidth = { "20", "40" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "entrance.examCode", "entrance.examName",
				"entrance.totalMarks", "entrance.passMarks" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "flase";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

}
