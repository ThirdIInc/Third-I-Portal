package org.struts.action.extraWorkBenefits;

import org.paradyne.bean.extraWorkBenefits.ExtraWorkBenefitMisBean;
import org.paradyne.model.extraWorkBenefits.ExtraWorkBenefitMisModel;
import org.struts.lib.ParaActionSupport;

public class ExtraWorkBenefitMisAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ExtraWorkBenefitMisAction.class);
	
	ExtraWorkBenefitMisBean misreport;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		misreport = new ExtraWorkBenefitMisBean();
		misreport.setMenuCode(1010);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return misreport;
	}

	public ExtraWorkBenefitMisBean getExtraWorkMisBean() {
		return misreport;
	}

	public void setExtraWorkMisBean(ExtraWorkBenefitMisBean extraWorkMisBean) {
		this.misreport = extraWorkMisBean;
	}

	public String reset() {
		try {
			misreport.setDivCode("");
			misreport.setDivName("");
			misreport.setBenefitsFor("");
			misreport.setBenefitsType("");
			misreport.setExtraWorkType("");
			misreport.setEmployeeName("");
			misreport.setEmployeeCode("");
			misreport.setEmployeeToken("");
			misreport.setApproverCode("");
			misreport.setApproverName("");
			misreport.setApproverToken("");
			misreport.setStatus("");
			misreport.setMonth("");
			misreport.setYear("");
			misreport.setSavedReport("");
			//misreport.setReportId("");
			//misreport.setReportTitle("");
 			 
			misreport.setReportView("");
		 	
			misreport.setSortBy("");
			misreport.setSortByAsc("checked");
			misreport.setSortByDsc("");
			misreport.setSortByOrder("");
			misreport.setThenBy1("");
			misreport.setThenByOrder1Asc("checked");
			misreport.setThenByOrder1Dsc("");
			misreport.setThenByOrder1("");
			misreport.setThenBy2("");
			misreport.setThenByOrder2Asc("checked");
			misreport.setThenByOrder2("");
			misreport.setThenByOrder2Dsc("");
			misreport.setHiddenSortBy("");
			misreport.setHiddenThenBy1("");
			misreport.setHiddenThenBy2("");
			misreport.setHiddenColumns("");
			misreport.setSortByAsc("checked");
			misreport.setHidReportView("checked");
			
			misreport.setHidReportRadio("");
			misreport.setReportType("");
			//misreport.setSettingName("");
			
			misreport.setBackFlag("");
			
			misreport.setEwdSelect("");
			misreport.setExtraWorkFromDate("");
			misreport.setExtraWorkToDate("");
			misreport.setAmountSelect("");
			misreport.setAmountFrom("");
			misreport.setAmountTo("");
			
			//misreport.setEmpTokenFlag("");
			misreport.setEmpNameFlag("");
			misreport.setDivFlag("");
			misreport.setDeptFlag("");
			misreport.setCenterFlag("");
			misreport.setRankFlag("");
			misreport.setExtraWorkDateFlag("");
			misreport.setBenefitTypeFlag("");
			misreport.setBenefitForFlag("");
			misreport.setApprovedByFlag("");
			misreport.setExtraworkAmountFlag("");
			misreport.setEmpStatusFlag("");
			misreport.setReportTypeStr("");
			misreport.setExtraworkStartTime("");
			misreport.setExtraworkEndTime("");
			misreport.setExtraworkApprovalDate("");
		} catch (Exception e) {
			logger.error("Exception in reset-------------"+e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveReport() throws Exception {
		try {
			ExtraWorkBenefitMisModel model = new ExtraWorkBenefitMisModel();
			model.initiate(context, session);
			logger.info("Report id........." + misreport.getReportId());
			if(misreport.getReportId()==null || misreport.getReportId().equals("")|| misreport.getReportId().equals("null"))
			{
				boolean result = model.saveReportCriteria(misreport);
				if (result)
					addActionMessage(getMessage("save"));
				else
					addActionMessage(getMessage("duplicate"));
			}
			else
			{
				model.deleteSavedReport(misreport);
				boolean result = model.saveReportCriteria(misreport);
				if (result)
					addActionMessage(getMessage("save"));
				else
					addActionMessage(getMessage("duplicate"));
			}
			
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in saveReport-------------"+e);
			}
		return SUCCESS;
	}
	public String setDetails() throws Exception {
		ExtraWorkBenefitMisModel model = new ExtraWorkBenefitMisModel();
		try {
			model.initiate(context, session);
			reset();
			model.setDetailOnScreen(misreport);
			 			if (misreport.getSortByOrder().trim().equals("D"))
				misreport.setSortByDsc("checked");
			if (misreport.getSortByOrder().trim().equals("A"))
				misreport.setSortByAsc("checked");
			if (misreport.getThenByOrder1().trim().equals("D"))
				misreport.setThenByOrder1Dsc("checked");
			if (misreport.getThenByOrder1().trim().equals("A"))
				misreport.setThenByOrder1Asc("checked");
			if (misreport.getThenByOrder2().trim().equals("D"))
				misreport.setThenByOrder2Dsc("checked");
			if (misreport.getThenByOrder2().trim().equals("A"))
				misreport.setThenByOrder2Asc("checked");
		} catch (Exception e) {
			logger.error("Exception in setDetails-------------"+e);
		}
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * Display the report on screen
	 * @return String
	 */
	public String viewOnScreen() {
		misreport.setBackFlag("");
			
		try {
			ExtraWorkBenefitMisModel model = new ExtraWorkBenefitMisModel();
			model.initiate(context, session);
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"),getMessage("extrawork.date"),
					 getMessage("benefit.for"),
					getMessage("approvedBy"),getMessage("extraworkAmount"),
					getMessage("application.status"),getMessage("extrawork.starttime"),
					getMessage("extrawork.endtime"),getMessage("extrawork.approvalDate")	};
			model.callViewScreen(misreport, request, labelNames);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
			e.printStackTrace();
		}
		return "viewOnScreen";
	}
	
	/**
	 * Called on Back button in view screen
	 * @return String
	 * returns to the mis filter page
	 */
	public String callBack() {
		ExtraWorkBenefitMisModel model = new ExtraWorkBenefitMisModel();
		model.initiate(context, session);
		try {
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}//end of try-catch block
		model.terminate();
		misreport.setBackFlag("true");
		logger.info("getSortByOrder..."+misreport.getSortByOrder());
		if(misreport.getSortByOrder().trim().equals("D"))
			misreport.setSortByDsc("checked");
		if(misreport.getSortByOrder().trim().equals("A"))
			misreport.setSortByAsc("checked");
		if(misreport.getThenByOrder1().trim().equals("D"))
			misreport.setThenByOrder1Dsc("checked");
		if(misreport.getThenByOrder1().trim().equals("A"))
			misreport.setThenByOrder1Asc("checked");
		if(misreport.getThenByOrder2().trim().equals("D"))
			misreport.setThenByOrder2Dsc("checked");
		if(misreport.getThenByOrder2().trim().equals("A"))
			misreport.setThenByOrder2Asc("checked");
		
		misreport.setHiddenSortBy(misreport.getSortBy());
		misreport.setHiddenThenBy1(misreport.getThenBy1());
		misreport.setHiddenThenBy2(misreport.getThenBy2());
		return SUCCESS;
	}
	
	public String report() throws Exception {
		
		ExtraWorkBenefitMisModel model = new ExtraWorkBenefitMisModel();
		model.initiate(context, session);
		try {
			misreport.setBackFlag("");
			/*String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"),getMessage("extrawork.date"),
					getMessage("benefit.type"),getMessage("benefit.for"),
					getMessage("extrawork.month"),getMessage("approvedBy"),
					getMessage("extrawork.year"),getMessage("extraWork.Type")};*/
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"),getMessage("extrawork.date"),
					getMessage("benefit.for"),
					getMessage("approvedBy"),getMessage("extraworkAmount"),getMessage("application.status")
			,getMessage("extrawork.starttime"),getMessage("extrawork.endtime"),
			getMessage("extrawork.approvalDate")};
			model.getReport(misreport, response, labelNames, request);
		} catch (Exception e) {
			logger.error("Exception in report------------"+e);
		}
		model.terminate();
		return null;
		
	}
	public String searchSavedReport() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '),REPORT_ID,REPORT_TYPE FROM  HRMS_MISREPORT_HDR "
				+ " WHERE REPORT_TYPE='Extraworkbenefit' ORDER BY  REPORT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("report.criteria"),getMessage("report.title") };

		String[] headerWidth = { "50","50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "settingName", "misreport.reportTitle","misreport.reportId","reportTypeStr" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2,3 };

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
		String submitToMethod = "ExtraWorkBenefitMisBean_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Search division details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9divaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT DISTINCT NVL(DIV_NAME,' '),DIV_ID FROM  HRMS_DIVISION  ";
				 
		if(misreport.getUserProfileDivision() !=null && misreport.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+misreport.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divName", "divCode" };

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

		return "f9page";
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9employee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID "
				+ "	FROM HRMS_EMP_OFFC ";
				query += getprofileQuery(misreport);
		 		//query +=  " AND EMP_STATUS='S' ";
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "25", "75" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "employeeToken", "employeeName", "employeeCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };
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

		return "f9page";
	}// end of f9action

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9approver() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID "
				+ "	FROM HRMS_EMP_OFFC ";
		
		query += getprofileQuery(misreport);
		 //query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		 

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "25", "75" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "approverToken", "approverName", "approverCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };
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

		return "f9page";
	}// end of f9action

	public ExtraWorkBenefitMisBean getMisreport() {
		return misreport;
	}

	public void setMisreport(ExtraWorkBenefitMisBean misreport) {
		this.misreport = misreport;
	}

}
