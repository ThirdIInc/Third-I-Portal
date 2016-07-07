package org.struts.action.DataMigration;

import org.paradyne.bean.DataMigration.OfficalPartialUpload;
import org.paradyne.model.DataMigration.OfficialPartialUploadModel;
import org.struts.lib.ParaActionSupport;


/**
 * 
 * @author aa0491  Vishwambhar Deshpande
 *
 */
public class OfficialPartialUploadAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OfficialPartialUploadAction.class);

	OfficalPartialUpload bean = null;

	public void prepare_local() throws Exception {
		bean = new OfficalPartialUpload();
		bean.setMenuCode(1029);
	}

	public Object getModel() {
		return bean;
	}

	public OfficalPartialUpload getBean() {
		return bean;
	}

	public void setBean(OfficalPartialUpload bean) {
		this.bean = bean;
	}

	public String input() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		bean.setDataPath(dataPath);
		return SUCCESS;
	}

	public String f9desgAction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT RANK_NAME,RANK_ID FROM  HRMS_RANK  "
				+ " ORDER BY  RANK_NAME ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("designation") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "desgName", "desgCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9divAction() throws Exception {
		String query = " SELECT DISTINCT DIV_NAME,DIV_ID FROM  HRMS_DIVISION ";

		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN (" + bean.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_NAME ";
		String[] headers = { getMessage("division") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "divName", "divCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9empTypeAction() throws Exception {
		String query = " SELECT  TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE";

		String[] headers = { getMessage("employee.type") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "empType", "empTypeCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9deptAction() throws Exception {
		String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_NAME ";

		String[] headers = { getMessage("department") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "deptName", "deptCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * Following function is called in jsp page to show the branch code and name
	 */
	public String f9branchAction() throws Exception {

		String query = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER "
				+ " ORDER BY CENTER_NAME ";

		String[] headers = { getMessage("branch") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "branchName", "branchCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9gradeAction() throws Exception {
		String query = " SELECT CADRE_NAME,CADRE_ID FROM HRMS_CADRE "
				+ " ORDER BY CADRE_NAME ";

		String[] headers = { getMessage("grade") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "gradeName", "gradeCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	 public String reset()
	 {
		 try {
				 
				 bean.setDivName("");
				 bean.setDivCode("");
				 bean.setBranchName("");
				 bean.setBranchCode("");
				 bean.setDeptName("");
				 bean.setDeptCode("");
				 bean.setDesgName("");
				 bean.setDesgCode("");
				 bean.setEmpType("");
				 bean.setEmpTypeCode("");
				 bean.setGradeName("");
				 bean.setGradeCode("");
				 bean.setFirstName("");
				 bean.setMiddleName("");
				 bean.setLastName("");
				 bean.setDivision("");
				 bean.setBranch("");
				 bean.setDepartment("");
				 bean.setDesignation("");
				 bean.setEmployeeTitle("");
				 bean.setEmployeeType("");
				 bean.setReportingTo("");
				 bean.setDateofconfirm("");
				 bean.setShift("");
				 bean.setPaybill("");
				 bean.setGender("");
				 bean.setBirthDate("");
				 bean.setGrade("");
				 bean.setJoiningDate("");
				 bean.setLeaving("");
				 bean.setGroupJoinDateCheck("");
				 bean.setTrade("");
				 bean.setRole("");
				 
				 bean.setStatus("");
				 bean.setUploadFileName("");
				 bean.setNote("");
				 bean.setFileUploaded(false);
				 bean.setDataExists(false);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS ;
	 }
	
	public String downloadTemplate(){
		try {
			OfficialPartialUploadModel model = new OfficialPartialUploadModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String dataPath = getText("data_path");
			String application = "Official Details";
			String rangeValue = request.getParameter("rangeValue");	
			
			System.out.println("rangeValue           "+rangeValue);
			model.downloadTemplate(bean, request, response, poolName, dataPath,
					application,rangeValue);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	public String uploadTemplate(){
		try {
			OfficialPartialUploadModel model = new OfficialPartialUploadModel();
			model.initiate(context, session);
			String dataPath = bean.getDataPath();
			dataPath += bean.getUploadFileName();
			model.uploadOfficialDataTemplate(dataPath, bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	 
	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");
			MigrateExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
	
	public String getURL(){
		try {
			OfficialPartialUploadModel model = new OfficialPartialUploadModel();
			model.initiate(context, session);
			boolean result = model.generateUrlList(request, response,
					bean);
			if (!result)
				addActionMessage("No records found for selected search criteria.");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
