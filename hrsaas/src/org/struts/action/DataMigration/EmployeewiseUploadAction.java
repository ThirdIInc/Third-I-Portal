/* Bhushan Dasare Apr 27, 2010 */

package org.struts.action.DataMigration;

import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.model.DataMigration.EmployeewiseUploadModel;
import org.struts.lib.ParaActionSupport;

public class EmployeewiseUploadAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeewiseUploadAction.class);
	EmpDetailsUpload bean;
	
	public String downloadTemplate() {
		try {
			String divisionId = request.getParameter("divisionId");
			String branchId = request.getParameter("branchId");
			String departmentId = request.getParameter("departmentId");
			String designationId = request.getParameter("designationId");
			String employeeTypeId = request.getParameter("employeeTypeId");
			String payBillId = request.getParameter("payBillId");
			String downloadType = request.getParameter("downloadType");
			String invCode = request.getParameter("invCode");
			String perCode = request.getParameter("perCode");
			
			// Set list of filters
			String[] listOfFilters = new String[6];
			listOfFilters[0] = divisionId;
			listOfFilters[1] = branchId;
			listOfFilters[2] = departmentId;
			listOfFilters[3] = designationId;
			listOfFilters[4] = employeeTypeId;
			listOfFilters[5] = payBillId;
			
			boolean dataExists = false;
			String dataPath = getText("data_path");
			String client = String.valueOf(session.getAttribute("session_pool"));
			String rangeValue = request.getParameter("rangeValue");			
			
			EmployeewiseUploadModel model = new EmployeewiseUploadModel();
			model.initiate(context, session);
			
			/**
			 * Download perquisites
			 */
			if(downloadType.equalsIgnoreCase("PER")) {
				String[] listOfColumns = {"empId", "empName", "perqId", "perqName", "perqPeriod", "fromYear", "toYear", "amount"};
				dataExists = model.downloadPerquisiteTemplate(request, response, listOfFilters, dataPath, listOfColumns, client, rangeValue,perCode);
			}
			
			/**
			 * Download investments
			 */
			else {
				String[] listOfColumns = {"empId", "empName", "invstId", "invstName", "invstSection", "fromYear", "toYear", "amount"};
				dataExists = model.downloadInvestmentTemplate(request, response, listOfFilters, dataPath, listOfColumns, client, rangeValue,invCode);
			}
			
			model.terminate();
			bean.setDataExists(dataExists);
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
		return null;
	}
	
	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {"Branch"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:" + e);
			return "";
		}
	}
	
	public String f9invaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "select NVL(INV_NAME,''),NVL(INV_CHAPTER,''),NVL(INV_SECTION,''),INV_CODE from HRMS_TAX_INVESTMENT WHERE INV_TYPE = 'I' ORDER BY INV_CODE";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */

		String[] headers = { "Investment", "Investment Chapter",
				"Investment Section" };
		String[] headerWidth = { "20", "20", "20" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = {	"invName", "invChapter",
				"invSection", "invCode" };

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
	}//end of f9invaction method

	public String f9Department() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {"Department"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"departmentName", "departmentId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Department:" + e);
			return "";
		}
	}

	public String f9Designation() {
		try {
			String query = " SELECT RANK_NAME, RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

			String[] headers = {"Designation"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"designationName", "designationId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Designation:" + e);
			return "";
		}
	}

	public String f9Division() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			if(bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + bean.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {"Division"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divisionName", "divisionId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Division:" + e);
			return "";
		}
	}

	public String f9EmployeeType() {
		try {
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";

			String[] headers = {"Employee Type"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"employeeTypeName", "employeeTypeId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9EmployeeType:" + e);
			return null;
		}
	}
	
	public String f9PayBill() {
		try {
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " + 
			" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query += getprofilePaybillQuery(bean);

			String[] headers = {"Paybill Group"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"payBillName", "payBillId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9PayBill:" + e);
			return null;
		}
	}

	public EmpDetailsUpload getBean() {
		return bean;
	}

	public Object getModel() {
		return bean;
	}
	
	public String getTemplateLinks() {
		try {
			String divisionId = request.getParameter("divisionId");
			String branchId = request.getParameter("branchId");
			String departmentId = request.getParameter("departmentId");
			String designationId = request.getParameter("designationId");
			String employeeTypeId = request.getParameter("employeeTypeId");
			String payBillId = request.getParameter("payBillId");
			String downloadType = request.getParameter("downloadType");
			
			// Set list of filters
			String[] listOfFilters = new String[6];
			listOfFilters[0] = divisionId;
			listOfFilters[1] = branchId;
			listOfFilters[2] = departmentId;
			listOfFilters[3] = designationId;
			listOfFilters[4] = employeeTypeId;
			listOfFilters[5] = payBillId;
			
			EmployeewiseUploadModel model = new EmployeewiseUploadModel();
			model.initiate(context, session);
			
			Object[][] listOfEmployees = model.getTemplateLinks(listOfFilters, downloadType);
			
			if(listOfEmployees != null && listOfEmployees.length > 0) {
    			request.setAttribute("totalRecords", listOfEmployees.length);
				request.setAttribute("recPerPage", model.EMPLOYEE_COUNT);
				bean.setDataExists(true);
    		} else {
    			request.setAttribute("totalRecords", 0);
    			addActionMessage("No records found for selected search criteria.");
    		}
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in getTemplateLinks in action:" + e);
		}
		return SUCCESS;
	}

	public String input() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		bean.setDataPath(dataPath);
		
		request.setAttribute("totalPage", 0);
		request.setAttribute("pageNo", 0);
		return SUCCESS;
	}

	public void prepare_local() throws Exception {
		bean = new EmpDetailsUpload();
		bean.setMenuCode(1036);
	}

	public String reset() {
		try {
			bean.setDivisionId("");
			bean.setDivisionName("");
			bean.setBranchId("");
			bean.setBranchName("");
			bean.setDepartmentId("");
			bean.setDepartmentName("");
			bean.setDesignationId("");
			bean.setDesignationName("");
			bean.setEmployeeTypeId("");
			bean.setEmployeeTypeName("");
			bean.setPayBillId("");
			bean.setPayBillName("");
			bean.setDownloadType("");
			bean.setUploadType("");
			bean.setUploadFileName("");
			bean.setStatus("");
			bean.setNote("");
			bean.setDataExists(false);
			bean.setFileUploaded(false);
			bean.setInvCode("");
			bean.setInvName("");
			bean.setPerCode("");
			bean.setPerName("");
			
		} catch(Exception e) {
			logger.error("Exception in reset:" + e);
		}
		return SUCCESS;
	}

	public void setBean(EmpDetailsUpload bean) {
		this.bean = bean;
	}

	public String uploadExcel() {
		try {
			EmployeewiseUploadModel model = new EmployeewiseUploadModel();
			model.initiate(context, session);
			
			String dataPath = request.getParameter("dataPath");
			String uploadFileName = request.getParameter("uploadFileName");
			String uploadType = request.getParameter("uploadType");
			boolean dataExists = Boolean.parseBoolean(request.getParameter("dataExists"));
			
			String[] statusAndNote = null;
			
			/**
			 * Upload perquisites
			 */
			if(uploadType.equals("PER")) {
				statusAndNote = model.uploadPerquisites(dataPath, uploadFileName);
			}
			
			/**
			 * Upload investments
			 */
			else if(uploadType.equals("INV")) {
				statusAndNote = model.uploadInvestments(dataPath, uploadFileName);
			}
			
			bean.setStatus(statusAndNote[0]);
			bean.setNote(statusAndNote[1]);
			bean.setFileUploaded(true);
			
			model.terminate();
			
			if(dataExists) {
				getTemplateLinks();
			}
		} catch(Exception e) {
			logger.error("Exception in uploadExcel in action:" + e);
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
	
	public String f9perqaction() throws Exception {
		String query = " SELECT  NVL(PERQ_NAME,'') , PERQ_CODE FROM  HRMS_PERQUISITE_HEAD WHERE PERQ_TAXABLE_FLAG ='Y' ORDER BY PERQ_CODE ";
		String[] headers = { "Perquisites Name"};
		String[] headerWidth = { "100" };
		String[] fieldNames = {	"perName","perCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9invaction method
}