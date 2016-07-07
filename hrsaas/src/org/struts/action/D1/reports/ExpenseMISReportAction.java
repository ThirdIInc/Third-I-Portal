package org.struts.action.D1.reports;

import org.paradyne.bean.D1.reports.ExpenseMISReport;
import org.paradyne.model.D1.MISReportUtility;
import org.paradyne.model.D1.reports.ExpenseMISReportModel;
import org.struts.lib.ParaActionSupport;

public class ExpenseMISReportAction extends ParaActionSupport {
	ExpenseMISReport misreport;
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public void prepare_local() throws Exception {
		misreport = new ExpenseMISReport();
		misreport.setMenuCode(2061);
	}
	
	public ExpenseMISReport getModel() {
		return misreport;
	}

	public ExpenseMISReport getMisreport() {
		return misreport;
	}

	public void setMisreport(ExpenseMISReport misreport) {
		this.misreport = misreport;
	}

	/**
	 * Search division details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9division() throws Exception {

		String query = "SELECT DISTINCT NVL(DIV_NAME,' '),DIV_ID FROM  HRMS_DIVISION  ";
			if(misreport.getUserProfileDivision() !=null && misreport.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+misreport.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_ID ";
		String[] headers = { getMessage("division") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "misreport.divName", "misreport.divId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "ExpenseMISReport_chk.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Search department details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9department() throws Exception {

		String query = "SELECT DISTINCT NVL(DEPT_NAME,' '), DEPT_ID FROM  HRMS_DEPT  "
				+ " ORDER BY  DEPT_ID ";
		String[] headers = { getMessage("department") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "misreport.deptName", "misreport.deptId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "ExpenseMISReport_chk.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * Search branch details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9branch() throws Exception {

		String query = " SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";
		String[] headers = { getMessage("branch") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "misreport.branchName", "misreport.branchId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "ExpenseMISReport_chk.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	/**
	 * Search type details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9empType() throws Exception {
		String query = " SELECT DISTINCT TO_CHAR(TYPE_NAME), TYPE_ID FROM  HRMS_EMP_TYPE  "
				+ " ORDER BY  TYPE_ID ";
		String[] headers = { getMessage("type.name") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "misreport.empTypeName", "misreport.empTypeId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "ExpenseMISReport_chk.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	/**
	 * Search type details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9trackingNo() throws Exception {
		String query = " SELECT FILE_HEADER_NAME FROM HRMS_D1_EXPENSE_APPR_REQ ORDER BY  EXPENSE_CODE  ASC ";
		String[] headers = { getMessage("tracking.no") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "misreport.trackingNo", };
		int[] columnIndex = { 0 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	
	
	/**
	 * Search designation details
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9designation() throws Exception {
		
		String query = " SELECT DISTINCT TO_CHAR(RANK_NAME),RANK_ID FROM  HRMS_RANK  "
				+ " ORDER BY  RANK_ID ";
		String[] headers = { getMessage("designation") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "misreport.desigName", "misreport.desigId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "ExpenseMISReport_chk.action";

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

		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID "
				+ "	FROM HRMS_EMP_OFFC INNER JOIN HRMS_D1_EXPENSE_APPR_REQ ON(HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID=HRMS_EMP_OFFC.EMP_ID)   ";
		if (misreport.getUserProfileDivision()!=null && misreport.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ misreport.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}
		 		query +=  " AND EMP_STATUS='S' ";
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "25", "75" };

		String[] fieldNames = { "misreport.empToken", "misreport.empName","misreport.empId" };
		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "ExpenseMISReport_chk1.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action
	
	public String chk() throws Exception {
		
		misreport.setEmpId("");
		misreport.setEmpToken("");
		misreport.setEmpName("");
		
		return SUCCESS;
	}
	
	public String chk1() throws Exception {
		
		misreport.setDivId("");
		misreport.setDivName("");
		misreport.setDeptId("");
		misreport.setDeptName("");
		misreport.setBranchId("");
		misreport.setBranchName("");
		misreport.setEmpTypeId("");
		misreport.setEmpTypeName("");
		misreport.setDesigId("");
		misreport.setDesigName("");
		misreport.setStatus("");
		
		return SUCCESS;
	}	

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveReport() throws Exception {
		ExpenseMISReportModel model = new ExpenseMISReportModel();
		model.initiate(context, session);
		logger.info("Report id........." + misreport.getReportId());
		model.deleteSavedReport(misreport);
		boolean result = model.saveReportCriteria(misreport);
		if (result)
			addActionMessage(getMessage("save"));
		else
			addActionMessage(getMessage("duplicate"));
		model.terminate();
		return SUCCESS;
	}
	
	public String reset() throws Exception {
		
		misreport.setBackFlag("");
		misreport.setSavedReport("");
		//misreport.setReportId("");
		//misreport.setReportTitle("");
		
		misreport.setDivId("");
		misreport.setDivName("");
		misreport.setDeptId("");
		misreport.setDeptName("");
		misreport.setBranchId("");
		misreport.setBranchName("");
		misreport.setEmpTypeId("");
		misreport.setEmpTypeName("");
		misreport.setDesigId("");
		misreport.setDesigName("");
		misreport.setEmpId("");
		misreport.setEmpToken("");
		misreport.setEmpName("");
		misreport.setStatus("");
		
		misreport.setEmpNameFlag("");
		misreport.setDivFlag("");
		misreport.setDeptFlag("");
		misreport.setBranchFlag("");
		misreport.setDesigFlag("");
		misreport.setEmpTypeFlag("");
		misreport.setAppliDateFlag("");
		misreport.setAttDateFlag("");
		misreport.setStatusFlag("");
		misreport.setTrackingNo("");
		misreport.setLogInFlag("");
		misreport.setLateHrsFlag("");
		misreport.setLateHrsDeductFlag("");
		
		misreport.setSortBy("");
		misreport.setSortByAsc("checked");
		misreport.setSortByDsc("");
		misreport.setSortByOrder("");
		misreport.setHiddenSortBy("");
		misreport.setThenBy1("");
		misreport.setThenByOrder1Asc("checked");
		misreport.setThenByOrder1Dsc("");
		misreport.setThenByOrder1("");
		misreport.setHiddenThenBy1("");
		misreport.setThenBy2("");
		misreport.setThenByOrder2Asc("checked");
		misreport.setThenByOrder2Dsc("");
		misreport.setThenByOrder2("");
		misreport.setHiddenThenBy2("");
		
		misreport.setColumnOrdering("");
		misreport.setHiddenColumns("");
		
		misreport.setAppliDateSelect("");
		misreport.setAppliFromDate("");
		misreport.setAppliToDate("");
		misreport.setAttDateSelect("");
		misreport.setAttFromDate("");
		misreport.setAttToDate("");
		
		misreport.setHidReportView("checked");
		misreport.setHidReportRadio("");
		misreport.setReportType("");
		
		misreport.setSettingName("");
		misreport.setReqStatus("");
		
		return SUCCESS;
	}
	
	public String searchSavedReport() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '), REPORT_ID FROM  HRMS_MISREPORT_HDR "
				+ " WHERE REPORT_TYPE = 'ExpenseMIS'  ORDER BY  REPORT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("report.criteria"),
				getMessage("report.title") };

		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "settingName", "misreport.reportTitle",
				"misreport.reportId" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ExpenseMISReport_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	public String setDetails() throws Exception {
		ExpenseMISReportModel model = new ExpenseMISReportModel();
		reset();
		logger.info("report id -- "+misreport.getReportId());
		model.initiate(context, session);
		model.setDetailOnScreen(misreport);
		model.terminate();
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
		return SUCCESS;
	}
	
	/**
	 * Display the report on screen
	 * @return String
	 */
	public String viewOnScreen() {
		misreport.setBackFlag("");
		logger.info("view on screen");
		try{
			ExpenseMISReportModel model = new ExpenseMISReportModel();
			model.initiate(context, session);
			//PASSING LABEL NAMES
			String labelNames[] = {getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"), getMessage("employee.type"),
					getMessage("application.date"), getMessage("tracking.no"),
					getMessage("application.status"),getMessage("manager"), 
					getMessage("business.purpose"),getMessage("total.expense")};
			//CALL TO MODEL
			//model.callViewScreen(misreport, request, labelNames);
			
			
			
			
			String multiSelectValues = misreport.getHiddenColumns();
			String splitComma[] = null;
			if(!multiSelectValues.equals("")){
				String lastComma = multiSelectValues.substring(0, multiSelectValues.length()-1);
				splitComma = lastComma.split(",");
			}//End of if
			int count = 0;
			String queryWithCount = model.selectQuery(misreport,labelNames,count,splitComma,request);
			
			String splitQuery[] = queryWithCount.split("#");
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			String labels = splitQuery[2];
			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);			
			// QUERY APPENDED WITH CONDITIONS
			query += model.conditionQuery(misreport, labelNames); 
			MISReportUtility misModel=new MISReportUtility();
			misModel.initiate(context, session);
			Object[][]finalObj=misModel.callViewScreen(query, misreport.getHiddenColumns(), misreport.getMyPage(), count, request, labelNames);
			if(finalObj !=null && finalObj.length>0){
				misreport.setNoData("false");
				misreport.setDataLength(String.valueOf(finalObj.length));
			}
			else{
				misreport.setNoData("true");
			}
			request.setAttribute("labelValues", labels);
			model.terminate();
			
			
		}catch(Exception e){
			logger.error("Exception in viewOnScreen -- "+e);
		}
		return "viewOnScreen";
	}
	
	/**
	 * Called on Back button in view screen
	 * @return String
	 * returns to the mis filter page
	 */
	public String callBack() {

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
	
	/**
	 * Generate report in PDF/XLS or DOC
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {
		try {
			ExpenseMISReportModel model = new ExpenseMISReportModel();
			model.initiate(context, session);
			misreport.setBackFlag("");
			String labelNames[] = {getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"), getMessage("employee.type"),
					getMessage("application.date"), getMessage("tracking.no"),
					getMessage("application.status"),getMessage("manager"), 
					getMessage("business.purpose"),getMessage("total.expense")};
			
			String multiSelectValues = misreport.getHiddenColumns();
			String splitComma[] = null;
			if(!multiSelectValues.equals("")){
				String lastComma = multiSelectValues.substring(0, multiSelectValues.length()-1);
				splitComma = lastComma.split(",");
			}//End of if
			int count = 0;
			String queryWithCount = model.selectQuery(misreport,labelNames,count,splitComma,request);
			
			String splitQuery[] = queryWithCount.split("#");
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			String labels = splitQuery[2];

			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);
			
			// QUERY APPENDED WITH CONDITIONS
			query += model.conditionQuery(misreport, labelNames); 
			
			MISReportUtility misModel=new MISReportUtility();
			misModel.initiate(context, session);
			misModel.getReport(misreport.getReportType(), misreport.getReportTitle(), misreport.getHiddenColumns(),
					misreport.getReqStatus(), misreport.getMyPage(), 	misreport.getExportAll(), query, count, response, labelNames, request);
			
			//model.getReport(misreport, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
		}
		return null;
	}	
	
}
