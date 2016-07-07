package org.struts.action.reporting;

import java.util.HashMap;

import org.paradyne.bean.reporting.ReportingStructure;
import org.paradyne.model.reporting.ReportingStructureModel;
import org.struts.lib.ParaActionSupport;

public class ReportingStructureAction extends ParaActionSupport {

	ReportingStructure ReportingStr;
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		ReportingStr = new ReportingStructure();
		ReportingStr.setMenuCode(401);
		// TODO Auto-generated method stub
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return ReportingStr;
	}

	/**
	 * @return the ReportingStr
	 */
	public ReportingStructure getReportingStr() {
		return ReportingStr;
	}

	/**
	 * @param reportingStr
	 *            the ReportingStr to set
	 */
	public void setReportingStr(ReportingStructure reportingStr) {
		ReportingStr = reportingStr;
	}

	public String addGroup() {
		try {

			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			boolean result = model.addGroup(ReportingStr);
			if (result) {

				addActionMessage("Group saved successfully");
				model.getSavedGroupData(ReportingStr, ReportingStr
						.getModuleAbbrSelect(),
						ReportingStr.getGroupDivision(), ReportingStr
								.getModuleNameSelect());
			} else {
				addActionMessage("Duplicate record found ");
				model.getSavedGroupData(ReportingStr, ReportingStr
						.getModuleAbbrSelect(),
						ReportingStr.getGroupDivision(), ReportingStr
								.getModuleNameSelect());
			}
			ReportingStr.setGroupName("");
			ReportingStr.setGroupFlag("true");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String backToMain() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);

			model.getSavedData(ReportingStr, ReportingStr.getGroupDivision());
			ReportingStr.setShowFlag("true");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String back() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			model.setData(ReportingStr.getModuleAbbrApprover(), ReportingStr
					.getModuleNameApprover(), ReportingStr, ReportingStr
					.getDivCodeApprover());

			ReportingStr.setDefineSelectSource(ReportingStr
					.getDefineSelectSource());

			if (ReportingStr.getDefineSelectSource().equals("Define")) {
				ReportingStr.setModuleFlag("false");
				ReportingStr.setGroupFlag("true");
			}
			if (ReportingStr.getDefineSelectSource().equals("Select")) {
				ReportingStr.setModuleFlag("true");
				ReportingStr.setGroupFlag("false");
			}

			model.getSavedGroupData(ReportingStr, ReportingStr
					.getModuleAbbrApprover(),
					ReportingStr.getDivCodeApprover(), ReportingStr
							.getModuleNameApprover());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String saveManager() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			boolean result = model.saveManager(ReportingStr);
			if (result) {
				addActionMessage("Record saved successfully.");
				model.getManagerData(ReportingStr, ReportingStr
						.getDivCodeApprover(), ReportingStr
						.getModuleAbbrApprover(), ReportingStr
						.getGroupCodeApprover());
			} else {
				addActionMessage("Record can not be saved");
				model.getManagerData(ReportingStr, ReportingStr
						.getDivCodeApprover(), ReportingStr
						.getModuleAbbrApprover(), ReportingStr
						.getGroupCodeApprover());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "managerApproverPage";
	}

	public String defineManagerApprover() {
		try {
			String groupCode = request.getParameter("groupCode");
			String groupName = request.getParameter("groupName");
			String division = request.getParameter("division");

			String moduleAbbrSelectStr = request
					.getParameter("moduleAbbrSelectStr");
			String moduleNameSelectStr = request
					.getParameter("moduleNameSelectStr");

			String defineSelectSourceStr = request
					.getParameter("defineSelectSourceStr");

			System.out.println("defineSelectSourceStr   "
					+ defineSelectSourceStr);

			ReportingStr.setDefineSelectSource(defineSelectSourceStr);

			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			model.setDataForApprover(groupCode, groupName, division,
					ReportingStr, moduleAbbrSelectStr, moduleNameSelectStr);

			model.getManagerData(ReportingStr, division, moduleAbbrSelectStr,
					groupCode);

			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "managerApproverPage";
	}

	public String getDetails() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			boolean res = model.isRecordAvailable(ReportingStr, ReportingStr
					.getDivCode());
			if (res) {
				model.getSavedData(ReportingStr, ReportingStr.getDivCode());
			} else {
				model.getListData(ReportingStr);
			}
			model.setDropDownSaveData(request);
			ReportingStr.setShowFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return INPUT;
	}

	public String viewEmployee() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			model.viewEmployee(ReportingStr);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "manageTeammember";
	}

	public String save() throws Exception {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			String moduleAbbrevation[] = request
					.getParameterValues("moduleAbbrItt");
			String reportingType[] = request
					.getParameterValues("reportingType");

			String reportingLevel[] = request
					.getParameterValues("reportingLevel");

			String reportingSameAs[] = request
					.getParameterValues("selectExistModuleAbbr");

			boolean res = model.save(ReportingStr, moduleAbbrevation,
					reportingType, reportingLevel, reportingSameAs);

			if (res) {
				addActionMessage("Record saved successfully");
				model.getSavedData(ReportingStr, ReportingStr.getDivCode());
			} else {
				addActionMessage("Record can not be  saved ");
				model.getSavedData(ReportingStr, ReportingStr.getDivCode());
			}
			ReportingStr.setShowFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String addMultipleEmployee() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);

			boolean result = model.addEmployeeToList(request, ReportingStr);
			if (result) {
				addActionMessage(getMessage("save"));
				// model.getPendingAppraisee(ReportingStr, request);

			} else {

				addActionMessage(getMessage("save.error"));
				// model.getApprGroupList(ReportingStr);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "manageTeammember";
	}

	public String defineReportingStructure() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);

			String moduleAbbrStr = request.getParameter("moduleAbbrStr");
			String moduleNameStr = request.getParameter("moduleNameStr");

			String division = request.getParameter("divisionStr");

			String sourceStr = request.getParameter("sourceStr");

			System.out.println("sourceStr   " + sourceStr);

			ReportingStr.setDefineSelectSource(sourceStr);

			if (ReportingStr.getDefineSelectSource().equals("Define")) {
				ReportingStr.setModuleFlag("false");
				ReportingStr.setGroupFlag("true");
			}
			if (ReportingStr.getDefineSelectSource().equals("Select")) {
				ReportingStr.setModuleFlag("true");
				ReportingStr.setGroupFlag("false");
			}

			model.setData(moduleAbbrStr, moduleNameStr, ReportingStr, division);
			model.getSavedGroupData(ReportingStr, moduleAbbrStr, division,
					moduleNameStr);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String openMoveToGroupForm() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);

			logger.info("getEmpTypeFlag===" + ReportingStr.getEmpTypeFlag());
			logger.info("getEmpTypeFlag===" + ReportingStr.getSelectGroupId());

			ReportingStr.setMoveEmpId(request.getParameter("empId"));

			// logger.info("apprId===" + appraiserConfig.getApprId());
			String querType = "";
			// if(appraiserConfig.getEmpTypeFlag().equals("false")){
			querType = "   select distinct GROUP_ID,GROUP_NAME from  REPORTING_GROUP "
					+ " where GROUP_DIV="
					+ ReportingStr.getEmpDivCode()
					+ " and UPPER(MODULE_ABBR)='"
					+ ReportingStr.getEmpModuleAbbr().toUpperCase()
					+ "'"
					+ " and GROUP_ID!="
					+ ReportingStr.getEmpGroupCode()
					+ " "
					+ " order by GROUP_ID ";

			/*
			 * "SELECT APPR_APPRAISER_GRP_ID,APPR_APPRAISER_GRP_NAME FROM
			 * PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID=" +
			 * appraiserConfig.getApprId() //+" AND APPR_APPRAISER_GRP_ID
			 * !="+appraiserConfig.getSelectGroupId()+" ORDER BY
			 * APPR_APPRAISER_GRP_ID "; + " AND APPR_APPRAISER_GRP_ID !=" +
			 * appraiserConfig.getGroupId() + " ORDER BY APPR_APPRAISER_GRP_ID ";
			 */
			// }else
			// querType = "SELECT APPR_APPRAISER_GRP_ID,APPR_APPRAISER_GRP_NAME
			// FROM PAS_APPR_APPRAISER_GRP_HDR WHERE
			// APPR_ID="+appraiserConfig.getApprId() +" ORDER BY
			// APPR_APPRAISER_GRP_ID ";
			Object[][] groupObj = model.getSqlModel().getSingleResult(querType);
			HashMap mpGroup = new HashMap();
			for (int i = 0; i < groupObj.length; i++) {
				mpGroup.put(String.valueOf(groupObj[i][0]), String
						.valueOf(groupObj[i][1]));

			}
			ReportingStr.setHashmapGroup(mpGroup);
			ReportingStr.setParaId("");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "moveGroup";
	}

	public String moveToGroup() {
		boolean result = false;
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			logger.info("empId===" + request.getParameter("empId"));
			logger.info("SELECTGROUPID===" + ReportingStr.getSelectGroupId());
			logger.info("moveGroupName==="
					+ request.getParameter("moveGroupName"));

			String groupCode = request.getParameter("moveGroupName");

			result = model.moveToGroup(ReportingStr, request
					.getParameter("empId"), groupCode, request);
			if (result) {
				addActionMessage(getMessage("move"));
				// getEmployees();
			} else {
				addActionMessage(getMessage("move.error"));
			}

			ReportingStr.setDefineSelectSource(ReportingStr
					.getDefineSelectSource());

			model.setEmployeeListData(ReportingStr, ReportingStr
					.getEmpDivCode(), ReportingStr.getEmpGroupCode());

			model.terminate();
			ReportingStr.setParaId("");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "configureEmployeeList";
	}

	public String backToManageApprover() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			model.setEmployeeData(ReportingStr);
			ReportingStr.setDefineSelectSource(ReportingStr
					.getDefineSelectSource());
		} catch (Exception e) {
			// TODO: handle exception
		}
		// return SUCCESS;
		return "configureEmployeeList";
	}

	public String backToGroupScreen() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);

			ReportingStr.setDefineSelectSource(ReportingStr
					.getDefineSelectSource());

			if (ReportingStr.getDefineSelectSource().equals("Define")) {
				ReportingStr.setModuleFlag("false");
				ReportingStr.setGroupFlag("true");
			}
			if (ReportingStr.getDefineSelectSource().equals("Select")) {
				ReportingStr.setModuleFlag("true");
				ReportingStr.setGroupFlag("false");
			}
			model.setData(ReportingStr.getEmpModuleAbbr(), ReportingStr
					.getEmpModuleName(), ReportingStr, ReportingStr
					.getEmpDivCode());
			model.getSavedGroupData(ReportingStr, ReportingStr
					.getEmpModuleAbbr(), ReportingStr.getEmpDivCode(),
					ReportingStr.getEmpModuleName());

		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String manageTeamMember() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			ReportingStr.setTableLength("false");

			String groupCode = request.getParameter("groupCode");
			String groupName = request.getParameter("groupName");
			String division = request.getParameter("divisionStr");
			String moduleAbbrStr = request.getParameter("moduleAbbrSelectStr");
			String moduleNameStr = request.getParameter("moduleNameSelectStr");
			String defineSelectSourceStr = request
					.getParameter("defineSelectSourceStr");

			ReportingStr.setDefineSelectSource(defineSelectSourceStr);

			System.out.println("moduleAbbrStr  " + moduleAbbrStr);
			System.out.println("moduleNameStr  " + moduleNameStr);
			model.setTeamDetails(groupCode, groupName, moduleAbbrStr,
					moduleNameStr, division, ReportingStr);
			model.setEmployeeListData(ReportingStr, division, groupCode);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// return "manageTeammember";

		return "configureEmployeeList";
	}

	public String deleteEmployee() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			boolean result = model.deleteEmployee(ReportingStr);
			if (result) {
				addActionMessage("Record deleted successfully");
				// getEmployees();
			} else {
				addActionMessage("Record can not be deleted.");
			}
			model.setEmployeeListData(ReportingStr, ReportingStr
					.getEmpDivCode(), ReportingStr.getEmpGroupCode());
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "configureEmployeeList";
	}

	public String addTeamEmployee() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);

			String divisionCodeEmpStr = request
					.getParameter("divisionCodeEmpStr");
			String groupCodeEmpStr = request.getParameter("groupCodeEmpStr");
			String groupNameEmpStr = request.getParameter("groupNameEmpStr");
			String moduleNameEmpStr = request.getParameter("moduleNameEmpStr");
			String moduleAbbrStr = request.getParameter("moduleAbbrStr");

			ReportingStr.setDefineSelectSource(ReportingStr
					.getDefineSelectSource());

			model.addEmployee(ReportingStr, groupNameEmpStr, groupCodeEmpStr,
					moduleNameEmpStr, moduleAbbrStr, divisionCodeEmpStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "manageTeammember";
	}

	public String deleteGroup() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			String deleteGroupCodeStr = request
					.getParameter("deleteGroupCodeStr");
			System.out.println("deleteGroupCodeStr " + deleteGroupCodeStr);
			boolean result = model
					.deleteGroup(ReportingStr, deleteGroupCodeStr);
			if (result) {
				addActionMessage("Group deleted successfully.");
			} else {
				addActionMessage("Group can not be deleted ");
			}

			model.setData(ReportingStr.getModuleAbbrSelect(), ReportingStr
					.getModuleNameSelect(), ReportingStr, ReportingStr
					.getGroupDivision());
			model.getSavedGroupData(ReportingStr, ReportingStr
					.getModuleAbbrSelect(), ReportingStr.getGroupDivision(),
					ReportingStr.getModuleNameSelect());

			if (ReportingStr.getDefineSelectSource().equals("Define")) {
				ReportingStr.setModuleFlag("false");
				ReportingStr.setGroupFlag("true");
			}
			if (ReportingStr.getDefineSelectSource().equals("Select")) {
				ReportingStr.setModuleFlag("true");
				ReportingStr.setGroupFlag("false");
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";

		if (ReportingStr.getUserProfileDivision() != null
				&& ReportingStr.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ ReportingStr.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "divName" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ReportingStrNew_getDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String removeModule() {
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			String moduleAbbr = request.getParameter("moduleAbbr");
			boolean res = model.removeModule(moduleAbbr, ReportingStr
					.getDivCode());
			if (res) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record can not be deleted");
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getDetails();
	}

	public String alredySelectedEmp() {
		String notinStr = "0";

		if (!ReportingStr.getManager1EmpCode().equals("")) {
			notinStr += "," + ReportingStr.getManager1EmpCode().trim();
		}
		if (!ReportingStr.getManager1EmpCodeAlter().equals("")) {
			notinStr += "," + ReportingStr.getManager1EmpCodeAlter().trim();
		}
		if (!ReportingStr.getManager2EmpCode().equals("")) {
			notinStr += "," + ReportingStr.getManager2EmpCode().trim();
		}
		if (!ReportingStr.getManager2EmpCodeAlter().equals("")) {
			notinStr += "," + ReportingStr.getManager2EmpCodeAlter().trim();
		}
		if (!ReportingStr.getManager3EmpCode().equals("")) {
			notinStr += "," + ReportingStr.getManager3EmpCode().trim();
		}
		if (!ReportingStr.getManager3EmpCodeAlter().equals("")) {
			notinStr += "," + ReportingStr.getManager3EmpCodeAlter().trim();
		}

		return notinStr;
	}

	public String f9selectEmp() throws Exception {

		String notinStr = alredySelectedEmp();

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	 EMP_ID " + "	FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(ReportingStr);

		query += " AND EMP_STATUS='S' AND emp_ID not in(" + notinStr + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "manager1EmpToken", "manager1EmpName",
				"manager1EmpCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9selectEmpAlterApprover1() throws Exception {

		String notinStr = alredySelectedEmp();

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	 EMP_ID " + "	FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(ReportingStr);

		query += " AND EMP_STATUS='S' AND emp_ID not in(" + notinStr + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "manager1EmpTokenAlter",
				"manager1EmpNameAlter", "manager1EmpCodeAlter" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9selectEmpManager2() throws Exception {

		String notinStr = alredySelectedEmp();

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	 EMP_ID " + "	FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(ReportingStr);

		query += " AND EMP_STATUS='S' AND emp_ID not in(" + notinStr + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "manager2EmpToken", "manager2EmpName",
				"manager2EmpCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9selectEmpAlterApprover2() throws Exception {

		String notinStr = alredySelectedEmp();

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	 EMP_ID " + "	FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(ReportingStr);

		query += " AND EMP_STATUS='S' AND emp_ID not in(" + notinStr + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "manager2EmpTokenAlter",
				"manager2EmpNameAlter", "manager2EmpCodeAlter" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9selectEmpManager3() throws Exception {

		String notinStr = alredySelectedEmp();

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	 EMP_ID " + "	FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(ReportingStr);

		query += " AND EMP_STATUS='S' AND emp_ID not in(" + notinStr + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "manager3EmpToken", "manager3EmpName",
				"manager3EmpCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9selectEmpAlter3() throws Exception {

		String notinStr = alredySelectedEmp();

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	 EMP_ID " + "	FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(ReportingStr);

		query += " AND EMP_STATUS='S' AND emp_ID not in(" + notinStr + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "manager3EmpTokenAlter",
				"manager3EmpNameAlter", "manager3EmpCodeAlter" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9selectdivision() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";

		if (ReportingStr.getUserProfileDivision() != null
				&& ReportingStr.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ ReportingStr.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divisionCode", "division" };

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

	public String f9MultiCenter() {
		try {
			String query = " SELECT DISTINCT CENTER_ID, CENTER_NAME "
					+ " FROM  	HRMS_CENTER    WHERE IS_ACTIVE='Y'  ORDER BY "
					+ " UPPER (CENTER_NAME)	";

			String header = getMessage("branch");
			String textAreaId = "paraFrm_branch";

			String hiddenFieldId = "paraFrm_branchCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiRank() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9MultiDept() {
		try {
			String query = " SELECT " + "		DISTINCT DEPT_ID," + "		DEPT_NAME "
					+ "	FROM " + " 	HRMS_DEPT " + " WHERE "
					+ " IS_ACTIVE = 'Y' " + " ORDER BY "
					+ "		UPPER (DEPT_NAME) ";

			String header = getMessage("dept");
			String textAreaId = "paraFrm_deptName";

			String hiddenFieldId = "paraFrm_deptCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiDept() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9MultiRank() {
		try {
			String query = " SELECT " + "		DISTINCT RANK_ID," + "		RANK_NAME "
					+ "	FROM " + " 	HRMS_RANK  " + " WHERE IS_ACTIVE='Y' "
					+ " ORDER BY " + "		UPPER (RANK_NAME) ";

			String header = getMessage("designation");
			String textAreaId = "paraFrm_desgName";

			String hiddenFieldId = "paraFrm_desgCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiRank() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9MultiGrade() {
		try {
			String query = "SELECT DISTINCT CADRE_ID, CADRE_NAME "
					+ " FROM  	HRMS_CADRE   WHERE CADRE_IS_ACTIVE='Y'  ORDER BY "
					+ " UPPER (CADRE_NAME) ";

			String header = getMessage("grade");
			String textAreaId = "paraFrm_grade";

			String hiddenFieldId = "paraFrm_gradeCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiGrade() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9MultiEmpType() {
		try {
			String query = " SELECT DISTINCT TYPE_ID, TYPE_NAME "
					+ " FROM  	HRMS_EMP_TYPE   WHERE IS_ACTIVE='Y'  ORDER BY  "
					+ " UPPER (TYPE_NAME)	";

			String header = getMessage("empType");
			String textAreaId = "paraFrm_empType";

			String hiddenFieldId = "paraFrm_empTypeCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiEmpType() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9reporting() {
		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	 EMP_ID " + "	FROM HRMS_EMP_OFFC ";

			query += getprofileQuery(ReportingStr);

			query += " AND EMP_STATUS='S'";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };

			/**
			 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
			 */
			String[] headerWidth = { "20", "80" };

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED
			 * AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS
			 * 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS
			 * CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST
			 * BE SAME AS THE LENGTH OF FIELDNAMES
			 */

			String[] fieldNames = { "reportingtotoken", "reportingto",
					"reportingtoCode" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger
					.error("Error in LeaveApplicationMis.f9MultiEmpType() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9module() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		String fieldValueNo = request.getParameter("fieldValueNo");

		System.out.println("fieldValueNo  " + fieldValueNo);

		String query = "   select distinct REPORTING_APPL_TYPE_ABBREV,REPORTING_APPL_TYPE_NAME   from REPORTING_GROUP"
				+ "  inner join HRMS_REPORTING_APPL_TYPE on(HRMS_REPORTING_APPL_TYPE.REPORTING_APPL_TYPE_ABBREV =REPORTING_GROUP.MODULE_ABBR)"
				+ " where GROUP_DIV=" + ReportingStr.getDivCode();

		/*
		 * + " and UPPER(MODULE_ABBR)!='" +
		 * ReportingStr.getModuleAbbrSelect().toUpperCase() + "'";
		 */
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Module Abbrevation", "Module Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "selectExistModuleAbbr" + fieldValueNo,
				"selectExistModule" + fieldValueNo };

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

}