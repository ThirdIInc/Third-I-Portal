package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.LangMaster;

import org.paradyne.model.admin.master.LangMasterModel;
/*
 * Anantha lakshmi
 */
import org.paradyne.model.admin.master.EmpTypeModel;

public class LangMasterAction extends org.struts.lib.ParaActionSupport {

	LangMaster am = null;
	public LangMaster getAm() {
		return am;
	}
	public void setAm(LangMaster am) {
		this.am = am;
	}
	public Object getModel() {
		return am;
	}
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void prepare_local() throws Exception {
		am = new LangMaster();
		am.setMenuCode(2228);
	}
	public String input() throws Exception {
		am.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "langData";
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
	public void prepare_withLoginProfileDetails() throws Exception {
		System.out.println("====1====");
		LangMasterModel model = new LangMasterModel();
		model.initiate(context, session);
		model.langData(am, request);
		model.terminate();
	}
	public String callPage() throws Exception {
		System.out.println("====2====");
		LangMasterModel model = new LangMasterModel();
		model.initiate(context, session);
		model.langData(am, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String calforedit() throws Exception {
		LangMasterModel model = new LangMasterModel();
		model.initiate(context, session);
		model.calforedit(am);
		model.langData(am, request);
		getNavigationPanel(3);
		am.setEnableAll("N");
		model.terminate();
		return "langData";
	}
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		LangMasterModel model = new LangMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteEmptype(am, code);
		if (result) {
			addActionMessage(getMessage("delete"));
			am.setLangType("");
			am.setCountryType("") ;
			am.setLangCode("");	
			am.setIsActive("");

		}//end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}//end of else
		model.langData(am, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
	}

	public String delete() throws Exception {
		LangMasterModel model = new LangMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteLang(am);
		model.langData(am, request);
		model.terminate();
		if (result) {
			addActionMessage(getMessage("delete"));
		}
		else {
			addActionMessage(getMessage("del.error"));
		}
		am.setLangType("");
		am.setCountryType("") ;
		am.setLangCode("");	
		getNavigationPanel(1);
		return "success";
	}
	public String report() throws Exception {
		LangMasterModel model = new LangMasterModel();
		model.initiate(context, session);
		String [] label={"Sr.No", getMessage("langtype"),getMessage("countrytype"),getMessage("is.active")};
		model.getReport(am, request, response, context, session,label);
		model.terminate();
		return null;
	}

	public String save() throws Exception {
		LangMasterModel model = new LangMasterModel();
		model.initiate(context, session);
		boolean result;
			if (am.getLangCode().equals("")) {
				result = model.addLang(am);
				if (result) {
					addActionMessage(getMessage("save"));
					getNavigationPanel(3);
					return "langData";
				}
				else {
					addActionMessage(getMessage("duplicate"));
					reset();
					getNavigationPanel(1);
					return "success";
				}
			}
			else {
				result = model.modLang(am);
				if (result) {
					model.getLangDetails(am);
					addActionMessage(getMessage("update"));
					getNavigationPanel(3);
					return "langData";
				}
				else {
					addActionMessage(getMessage("duplicate"));
					reset();
					getNavigationPanel(1);
					return "success";
				} 
			}
	}

	public String callEdit1() throws Exception {
		LangMasterModel model = new LangMasterModel();
		model.initiate(context, session);
		model.getLangDetails(am);
		getNavigationPanel(2);
		model.terminate();
		return "langData";
	}
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT   LANGUAGE_NAME, COUNTRY_NAME, DECODE(LANGUAGE_IS_ACTIVE,'Y','YES','N','NO','NO'),LANGUAGE_IS_ACTIVE,LANGUAGE_CODE  FROM HRMS_LANGUAGE ORDER BY upper(LANGUAGE_NAME) ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("langtype"),getMessage("countrytype"), getMessage("is.active") };

		String[] headerWidth = {  "40" , "40" ,"20" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "langType","countryType","hiddenIsactive","isActive","langCode" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3,4 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LangMaster_langDetails.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String langDetails() throws Exception {
		LangMasterModel model = new LangMasterModel();
		model.initiate(context, session);
		boolean b = model.getLangdata(am);
		getNavigationPanel(3);
		model.terminate();
		return "langData";
	}

	/**
	 * To reset the fields
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		try {
			am.setLangType("");
			am.setCountryType("") ;
			am.setLangCode("");
			am.setHiddencode("");
			am.setIsActive("");
			getNavigationPanel(2);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in reset");
		}
		return "langData";
	}
}
