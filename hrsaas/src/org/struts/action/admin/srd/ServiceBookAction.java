package org.struts.action.admin.srd;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.srd.ServiceBook;
import org.paradyne.model.admin.srd.ServiceBookModel;

public class ServiceBookAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ServiceBookAction.class);
	ServiceBook bean;

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS ENAME, NVL(CENTER_NAME,' ') AS CENTER_NAME," +
		" NVL(RANK_NAME,' ') AS RANK_NAME, EMP_ID, NVL(TITLE_NAME,' ') || ' ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME " +
		" FROM HRMS_EMP_OFFC " +
		" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) " +
		" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) " +
		" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)	" +
		" ORDER BY UPPER(ENAME), EMP_TOKEN ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"25", "30"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS
		 * 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS
		 * THE LENGTH OF FIELDNAMES
		 */

		String[] fieldNames = {"token", "empName", "center", "rank", "empId", "empName"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO
		 * BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1, 2, 3, 4, 5};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public void getEmpDtls() {}

	public ServiceBook getModel() {
		return bean;
	}

	public ServiceBook getSbk() {
		return bean;
	}

	public ServiceBook getServiceBook() {
		return bean;
	}

	public void prepare_local() throws Exception {
		bean = new ServiceBook();
		bean.setMenuCode(185);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		bean.setEmpId(bean.getUserEmpId());
		
		ServiceBookModel model = new ServiceBookModel();
		model.initiate(context, session);
		
		if(bean.isGeneralFlag()) {
			model.getEmpDetails(bean);
		}

		model.terminate();
	}

	public String report() {
		ServiceBookModel model = new ServiceBookModel();
		model.initiate(context, session);
		
		model.generateReport(bean, request, response);
		
		model.terminate();

		return null;
	}

	public void setSbk(ServiceBook bean) {
		this.bean = bean;
	}

	public void setServiceBook(ServiceBook bean) {
		this.bean = bean;
	}
}