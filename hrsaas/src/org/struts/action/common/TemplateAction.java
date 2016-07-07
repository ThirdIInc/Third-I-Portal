package org.struts.action.common;

import org.paradyne.bean.common.TemplateMaster;
import org.paradyne.model.common.TemplateModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author KRISHNA
 * 
 * Date:06-11-2008
 * 
 */
public class TemplateAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TemplateAction.class);
	TemplateMaster tempBean;
	TemplateModel model;

	/**
	 * @return the tempBean
	 */
	public TemplateMaster getTempBean() {
		return tempBean;
	}

	/**
	 * @param tempBean
	 *            the tempBean to set
	 */
	public void setTempBean(TemplateMaster tempBean) {
		this.tempBean = tempBean;
	}

	public void setModel(TemplateModel model) {
		this.model = model;
	}

	public void prepare_local() throws Exception {

		tempBean = new TemplateMaster();
		System.out
				.println("prepare --------------------------------------------");
		tempBean.setMenuCode(456);

	}

	public Object getModel() {

		return tempBean;
	}

	public String iterate() {

		model = new TemplateModel();
		model.initiate(context, session);
		model.iterate(tempBean);
		tempBean.setTempContent(tempBean.getHtmlcode());
		model.terminate();
		return "success";
	}

	public String Report() {
		return "Report";
	}

	public String ReportGenerate() throws Exception {
		model = new TemplateModel();
		model.initiate(context, session);
		// call method to create filename
		String dirName = getDirName();
		// call model method to initialize the file name
		model.setDirName(dirName);
		boolean result = model.reportGenerate(tempBean, response, request);
		model.terminate();
		return null;
	}

	/**
	 * method to save the Template
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String Save() throws Exception {
		model = new TemplateModel();
		model.initiate(context, session);
		// call method to create filename
		String dirName = getDirName();
		// call model method to initialize the file name
		model.setDirName(dirName);
		boolean result = false;

		if (tempBean.getTempId() == "" || tempBean.getTempId().equals(null)
				|| tempBean.getTempId().equals("")) {
			result = model.save(tempBean);

		} else {

			result = model.update(tempBean);
		}

		model.terminate();
		if (result) {
			try {
				// f9set();
			}

			catch (Exception e) {
				logger.error(e);
			}
			addActionMessage("Record Saved Successfully");
		} else {
			addActionMessage("Record not Saved");
		}
		reset();
		return "success";

	}

	public String delete() throws Exception {
		model = new TemplateModel();
		model.initiate(context, session);
		boolean result = model.delete(tempBean);
		if (result) {
			addActionMessage("Record Deleted Successfully");
		}
		model.terminate();
		reset();
		return "success";
	}

	/*
	 * public String wordReport() throws Exception { model = new
	 * TemplateModel(); model.initiate(context, session); //boolean result =
	 * model.report(tempBean, response, request); model.terminate();
	 * 
	 * return null; }
	 * 
	 * public String generateTable() throws Exception { model = new
	 * TemplateModel(); model.initiate(context, session); PrintWriter writer =
	 * response.getWriter(); writer.print("<table border=1>" + "<tr><td>
	 * werwrwerwerwrwe</td></tr></table>"); model.terminate();
	 * 
	 * return null; }
	 */
	public String f9EmpName() throws Exception {

		String query = " SELECT  EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME ,EMP_ID  FROM HRMS_EMP_OFFC ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Name" };

		String[] headerWidth = { "10" };

		String[] fieldNames = { "EmpName", "EmpId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9ParamPage";
	}

	public String f9set() throws Exception {
		String query = " SELECT  TEMPLATE_ID,TEMPLATE_CONTENT "
				+ " FROM HRMS_TEMPLATE where TEMPLATE_ID="
				+ tempBean.getTempId();
		Object data[][] = model.getSqlModel().getSingleResult(query);
		tempBean.setTempContent(String.valueOf(data[0][1]));
		tempBean.setTempId(String.valueOf(data[0][0]));
		return "success";
	}

	public String fals() throws Exception {
		model = new TemplateModel();
		model.initiate(context, session);
		// call method to create filename
		String dirName = getDirName();
		// call model method to initialize the file name
		model.setDirName(dirName);
		model.setData(tempBean);
		model.terminate();
		return "success";
	}

	public String f9tempId() throws Exception {

		String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_TEMPLATE ORDER BY TEMPLATE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Template Code", "Template Name" };
		String[] headerWidth = { "20", "50" };
		String[] fieldNames = { "tempId", "tempName" };
		/**
		 * ,"tempContent",TEMPLATE_CONTENT SET THE COLUMN INDEX E.G. SUPPOSE THE
		 * POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH
		 * COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE
		 * COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		// String submitFlag = "true";
		String submitFlag = "true";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		// String submitToMethod="TemplateAction_iterate.action";
		// String submitToMethod = "TemplateAction_iterate.action";
		String submitToMethod = "TemplateAction_fals.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * method to get the Template details
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9Action() throws Exception {

		String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_TEMPLATE ORDER BY TEMPLATE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Template Code", "Template Name" };
		String[] headerWidth = { "20", "50" };
		String[] fieldNames = { "tempId", "tempName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "TemplateAction_getQuery.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/*
	 * f9 for Query
	 */

	public String f9query() throws Exception {

		String query = "SELECT QUERY_ID,QUERY_NAME FROM HRMS_TEMPLATE_QUERYHDR ORDER BY QUERY_ID";

		String[] headers = { "Query Id", "Query Name" };

		String[] headerWidth = { "30", "50" };
		String[] fieldNames = { "qId", "qName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "TemplateAction_iterate.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * method to get the Template details
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getQuery() throws Exception {

		model = new TemplateModel();
		model.initiate(context, session);
		model.getQueryResult(tempBean);
		model.terminate();

		return "Report";
	}

	public String reset() throws Exception {
		try {
			tempBean.setHtmlcode("");
			tempBean.setTempId("");
			tempBean.setTempContent("");
			tempBean.setField("");
			tempBean.setEmpName("");
			tempBean.setEmpId("");
			tempBean.setQId("");
			tempBean.setQName("");
			tempBean.setQueryContent("");
			tempBean.setTemplateId("");
			tempBean.setTempName("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * method to get the Directory Name to save the file containing the text
	 * ,which has to be saved in database as CLOB.
	 * 
	 * @return String
	 */
	public String getDirName() {
		String poolDir = String.valueOf(session.getAttribute("session_pool"));

		String dirName = getText("data_path") + "/datafiles/" + poolDir
				+ "/Template/docs";
		return dirName;
	}
}
