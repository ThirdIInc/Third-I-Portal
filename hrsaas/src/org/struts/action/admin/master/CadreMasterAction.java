package org.struts.action.admin.master;

import java.util.ArrayList;

import org.paradyne.bean.Recruitment.QuestionnaireMaster;
import org.paradyne.bean.admin.master.CadreMaster;
import org.paradyne.model.admin.master.CadreModel;
import org.paradyne.model.admin.master.EmpTypeModel;
import org.paradyne.model.admin.master.TradeModel;
import org.paradyne.model.recruitment.QuestionnaireMasterModel;

/**         
 * @author sunil 
 *
 */
public class CadreMasterAction extends org.struts.lib.ParaActionSupport {

	//private static final long serialVersionUID = 7047317819789938757L;

	CadreMaster cadMaster;

	/**
	 * @return the cadMaster
	 */

	public Object getModel() {

		return cadMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		cadMaster = new CadreMaster();
		cadMaster.setMenuCode(19);
	}

	/**
	 * @param cadMaster the cadMaster to set
	 */
	/**
	 * @return the cadMaster
	 */
	public CadreMaster getCadMaster() {
		return cadMaster;
	}

	/**
	 * @param cadMaster the cadMaster to set
	 */
	public void setCadMaster(CadreMaster cadMaster) {
		this.cadMaster = cadMaster;
	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {
		CadreModel model = new CadreModel();

		model.initiate(context, session);

		model.gradeData(cadMaster, request);
		model.terminate();
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String input() throws Exception {
		cadMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}

//  to display the save mode
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "cadreData";
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
	public String callPage() throws Exception {
		System.out.println("========ONLOAD================");
		CadreModel model = new CadreModel();

		model.initiate(context, session);
		model.gradeData(cadMaster, request);
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
		CadreModel model = new CadreModel();

		model.initiate(context, session);
		model.calforedit(cadMaster);
		//CadreRecord();

		model.gradeData(cadMaster, request);
		getNavigationPanel(3);
		cadMaster.setEnableAll("N");
		model.terminate();
		return "cadreData";
	}

	/*public String calfordelete()
		{
		CadreModel model = new CadreModel();
		
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(cadMaster);
			if(result){
				addActionMessage(getText("Record deleted Successfully"));
				cadMaster.setCadreID("");
				cadMaster.setCadreName("");
				cadMaster.setCadreAbbr("");
				cadMaster.setCadreDesc("");
				cadMaster.setCadreParID("");
			}
			else{
				addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
			}
			
			model.gradeData(cadMaster,request);
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
		System.out.println("====delete1================");
		String code[] = request.getParameterValues("hdeleteCode");
		CadreModel model = new CadreModel();
		model.initiate(context, session);
		boolean result = model.deleteGrade(cadMaster, code);
		if (result) {
			addActionMessage(getText("delMessage"));
		}//end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}//end of else
		cadMaster.setCadreID("");
		cadMaster.setCadreName("");
		cadMaster.setCadreAbbr("");
		cadMaster.setCadreDesc("");
		cadMaster.setCadreParID("");
		model.gradeData(cadMaster, request);
		getNavigationPanel(1);
		model.terminate();

		return "success";

	}

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 * updated by ANANTHA LAKSHMI
	 */

	public String save() throws Exception {
		boolean result;
		String [] competency=request.getParameterValues("competency");
		CadreModel model = new CadreModel();
		model.initiate(context, session);
		if (cadMaster.getCadreID().equals("")) {
			result = model.addCadre(cadMaster,competency);
			if (result) {
				addActionMessage(getText("addMessage", ""));
				getNavigationPanel(3);
				System.out.println("---SAVE####-addCadre----");
				return "cadreData";
			}//end of if
			else {
				addActionMessage("Duplicate entry found");
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of nested if
		else {	
			result = model.modCadre(cadMaster,competency);
			if (result) {
				addActionMessage(getText("modMessage", ""));
				//model.showRecords(cadMaster);
				getNavigationPanel(3);
				return "cadreData";
			} else {
				addActionMessage("Duplicate entry found");
				reset();
				getNavigationPanel(1);
				return "success";
			}
		}//end of else
	}
	public String deleteDtl()throws Exception {
		System.out.println("====deleteDtl=======");
		String code[]=request.getParameterValues("hdeleteOp");
		String [] competency=request.getParameterValues("competency");
		System.out.println("====competency======="+competency.length);
		CadreModel model= new CadreModel();
		model.initiate(context,session);
		model.delDtl(cadMaster,code,competency);
		getNavigationPanel(2);
		//model.delDtl(quest,request);
		model.terminate();
		cadMaster.setHiddenEdit("");
		//quest.setHdeleteOp("");
		//quest.setConfChkop("");
		return "cadreData";

	}
	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		try {

			cadMaster.setCadreID("");
			cadMaster.setCadreName("");
			cadMaster.setCadreParName("");
			cadMaster.setCadreAbbr("");
			cadMaster.setCadreDesc("");
			cadMaster.setCadreParID("");
			getNavigationPanel(2);

		} catch (Exception e) {

			logger.error("Error in reset" + e);
		}
		return "cadreData";
	}

	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String CadreRecord() throws Exception {
		CadreModel model = new CadreModel();
		model.initiate(context, session);
		model.getCadreRecord(cadMaster);
		//model.showRecords(cadMaster);
		getNavigationPanel(3);
		model.terminate();
		return "cadreData";
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		System.out.println("====delete=========");
		CadreModel model = new CadreModel();
		model.initiate(context, session);
		boolean result = model.deleteCadre(cadMaster);
		model.gradeData(cadMaster, request);
		model.terminate();
		if (result) {
			addActionMessage(getText("delMessage", ""));

		}//end of if
		else {
			addActionMessage("Record can't be deleted \n as it is associated with some other record");
		}//end of else
		cadMaster.setCadreID("");
		cadMaster.setCadreName("");
		cadMaster.setCadreParName("");
		cadMaster.setCadreAbbr("");
		cadMaster.setCadreDesc("");
		cadMaster.setCadreParID("");
		
		getNavigationPanel(1);
		return "success";
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	/*public String report()throws Exception { 
		CadreModel model = new CadreModel();
		model.initiate(context,session);
		model.getReport(cadMaster);
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
		CadreModel model = new CadreModel();
		model.initiate(context, session);
		String []label={"Sr.No",getMessage("gradename"),getMessage("gradedescription"),getMessage("gradeabbrevation"),"Grade Parent Code","Grade Is Active"};
		model.getReport(cadMaster, request, response, context, session,label);
		model.terminate();
		return null;
	}

	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT HRMS_CADRE.CADRE_NAME , C1.CADRE_NAME , DECODE(HRMS_CADRE.CADRE_IS_ACTIVE,'Y','YES','N','NO') ,HRMS_CADRE.CADRE_ID FROM HRMS_CADRE "
				+ " LEFT JOIN HRMS_CADRE C1 ON (C1.CADRE_ID= HRMS_CADRE.CADRE_PARENT_ID)"
				+ " ORDER BY upper(HRMS_CADRE.CADRE_NAME)";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = {  getMessage("gradename"), getMessage("gradeparentname"), "gradeIsActive" };

		//String[] headerWidth = { "50", "50" };
		
		String[] headerWidth = { "40", "40", "20" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = {  "cadMaster.cadreName","cadMaster.cadreParName", "cadMaster.cadreIsActive", "cadMaster.cadreID" };

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
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "CadreMaster_CadreRecord.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Cadreaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT CADRE_NAME , CADRE_ID " +
				" FROM HRMS_CADRE " +
				" ORDER BY CADRE_NAME ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		//String[] headers = {  getMessage("gradeparentname"), "cadreisactive"};
		
		String[] headers = {  getMessage("gradeparentname") };

		String[] headerWidth = { "100" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = {"cadMaster.cadreParName", "cadMaster.cadreParID"  };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

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
public String addItem()throws Exception{
		
		String [] srNo=request.getParameterValues("srNo");
		String [] competency=request.getParameterValues("competency");
		CadreModel model= new CadreModel();
		model.initiate(context, session);
		
		if(competency !=null){
			model.getDuplicate(cadMaster, srNo, competency,1);
		}
		
		if (cadMaster.getHiddenEdit().equals("")){
			System.out.println("addItem---------");
			model.addItem(cadMaster, srNo,competency,1);
		}
		else{
			model.moditem(cadMaster, srNo, competency,1);
		}
		model.terminate();
		cadMaster.setCompetency1("");
		cadMaster.setSubcode("");
		cadMaster.setHiddenEdit("");
		getNavigationPanel(2);
		return "cadreData";
	}
	
}