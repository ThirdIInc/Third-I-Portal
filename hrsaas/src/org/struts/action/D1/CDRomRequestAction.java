/**
 * 
 */
package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.paradyne.bean.D1.CDRomRequestBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CDRomApprovalModel;
import org.paradyne.model.D1.CDRomRequestModel;
import org.paradyne.model.D1.DepartmentandLocationChangeModel;
import org.struts.action.DataMigration.MigrateExcelData;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1439/aa1381
 * 
 */
public class CDRomRequestAction extends ParaActionSupport {
	/**
	 * Set return type of JSP.
	 */
	private static final  String RETURN_TYPE = "input";
	/**
	 * Set Return type to success.
	 */
	private static final  String RETURN_TYPE_SUCCESS = "success";
	/**
	 * Set Return type to approve.
	 */
	private static final  String RETURN_TYPE_APPROVE = "approve";
	
	
	/**
	 * Set Return type to f9 page.
	 */
	private static final  String F9_PAGE = "f9page";
	/**
	 * Set Y.
	 */
	private static final  String FLAG_TYPE_Y = "Y";
	/**
	 * Set N.
	 */
	private static final  String FLAG_TYPE_N = "N";
	/**
	 * Set Hash specl char.
	 */
	private static final  String SPECL_CHAR = "#";
	/**
	 * Set 0.
	 */
	private static final  String ZERO_CHAR = "0";
	/**
	 * Set Session Pool.
	 */
	private static final  String SESSION_POOL = "session_pool";
	/**
	 * Set Data Path.
	 */
	private static final  String DATA_PATH = "data_path";
	/**
	 * Set Filr Type to PDF.
	 */
	private static final  String FILE_PDF = "pdf";
	/**
	 * Set File type to GPG.
	 */
	private static final  String FILE_JPG = "jpg";
	/**
	 * Set File Type to XLSX.
	 */
	private static final  String FILE_XLSX = "xlsx";
	/**
	 * Set File Type to DOC.
	 */
	private static final  String FILE_DOC = "doc";
	/**
	 * Set File Type to XLS.
	 */
	private static final  String FILE_XLS = "xls";
	/**
	 * Set File Type to TXT.
	 */
	private static final  String FILE_TXT = "txt";
	/**
	 * Set File Type to GIF.
	 */
	private static final  String FILE_GIF = "gif";
	
	/**
	 * Set File Type to Acrobat.
	 */
	private static final  String MIME_TYPE_PDF = "acrobat";
	/**
	 * Set File Type to Ms-Word.
	 */
	private static final  String MIME_TYPE_DOC = "msword";   
	/**
	 * Set File Type to MS-Excel.
	 */
	private static final  String MIME_TYPE_XLS = "msexcel";
	/**
	 * Set Duplicate Message.
	 */
	private static final String MESSAGE = "duplicate record found";
	/**
	 * Set Status - A.
	 */
	private static final String STATUS_APPROVED = "A";
	/**
	 * Set Status - F.
	 */
	private static final String STATUS_FORWORED = "F";
	/**
	 * Set String.
	 */ 
	private static final String STRING = ".";
	/**
	 * Set Approved.
	 */
	private static final String APPROVED = "approved";
    /**
     * Set Reject.
     */
	private static final String REJECTED = "reject";
    /**
     * Set D1.
     */
	private static final String NAME_D1 = "D1";
    /**
     * Set False.
     */
	private static final String FLAG_FALSE = "false";
    /**
     * Set Back Slash.
     */
	private static final String QUOTE_STRING = "/";
    /**
     * Set Upload.
     */
	private static final String QUOTE_UPLOAD = "/upload";
    /**
     * Set Send Back.
     */
	private static final String SEND_BACK = "sendBack";
    /**
     * Set Draft.
     */
	private static final String DRAFT = "draft";
	/**
	 * Set Employee.
	 */
	private static final String EMP_NAME = "employee";
	
	
    
   /**	 * Logger.	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CDRomRequestAction.class);
	
	/**
	 * Object of  CDRomRequestBean.
	 */
	private CDRomRequestBean bean;

	/**
	 * @return bean object.
	 */
	public CDRomRequestBean getBean() {
		return this.bean;
	}

	/**
	 * @param bean - bean object.
	 */
	public void setBean(final CDRomRequestBean bean) {
		this.bean = bean;
	}
	/**
	 * (non-Javadoc).
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel().
	 * @return Object.
	 */
	public Object getModel() {
		return this.bean;
	}
	
	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local()  {
		this.bean = new CDRomRequestBean();
		this.bean.setMenuCode(1181);
	}

	/**
	 * (non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input().
	 * @return String.
	 */
	public String input() {
		try {
			final CDRomRequestModel model = new CDRomRequestModel();
			model.initiate(context, session);
			final String userId = this.bean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getPendingList(this.bean, request, userId);
			}
			this.bean.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(1);
		return CDRomRequestAction.RETURN_TYPE;
	}
	
	/**
	 * (non-Javadoc).
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 * purpose - Executes first & Used to set Data Path for file attachments..
	 * @throws Exception - Exception.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {

		/**
		 * Set Path.
		 */ 
		String poolName = String.valueOf(session.getAttribute(CDRomRequestAction.SESSION_POOL));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = CDRomRequestAction.QUOTE_STRING + poolName;
		}
		/**
		 *  for getting server path where configuration files are saved..
		 */
		final String dataPath = this.getText(CDRomRequestAction.DATA_PATH) + " /upload" + poolName + " /CDROM/";

		final String dataPath1 = this.getText(CDRomRequestAction.DATA_PATH) + CDRomRequestAction.QUOTE_UPLOAD + poolName + " /CDROMAddressing/";

		final String dataPath2 = this.getText(CDRomRequestAction.DATA_PATH) + CDRomRequestAction.QUOTE_UPLOAD + poolName + " /CDROMAdditional/";
		
		this.bean.setDataPath(dataPath);
		this.bean.setDataPathAddressing(dataPath1);
		this.bean.setDataPathAdditional(dataPath2);

		final CDRomRequestModel model = new CDRomRequestModel();
		model.initiate(context, session);
		final String userId = this.bean.getUserEmpId();
		final boolean isCurrentUser = model.isCurrentUser(userId);
		/*if (isCurrentUser) {
			
		}*/
		model.terminate();
	}

	/**
	 * purpose - Display Approved List.
	 * @return String.
	 * @throws Exception .
	 */
	public String getApprovedList() throws Exception {
		try {
			final CDRomRequestModel model = new CDRomRequestModel();
			model.initiate(context, session);
			final String userId = this.bean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getApprovedList(this.bean, request, userId);
			}
			this.bean.setListType(CDRomRequestAction.APPROVED);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(1);
		return CDRomRequestAction.RETURN_TYPE;
	}

	/**
	 * purpose - Display Rejected List.
	 * @return String.
	 * @throws Exception .
	 */
	public String getRejectedList() throws Exception {
		try {
			final CDRomRequestModel model = new CDRomRequestModel();
			model.initiate(context, session);
			final String userId = this.bean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getRejectedList(this.bean, request, userId);
			}
			this.bean.setListType(CDRomRequestAction.REJECTED);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(1);
		return CDRomRequestAction.RETURN_TYPE;
	}

	/**
	 * purpose - Add new application.
	 * @return String.
	 */
	public String addNew() {
		try {
			final CDRomRequestModel model = new CDRomRequestModel();
			model.initiate(context, session);
			this.reset();
			model.getEmployeeDetails(this.bean.getUserEmpId(), this.bean);

			this.bean.setStatusDraftBack(true);

			/**
			 *  Set Path.
			 */
			String poolName = String.valueOf(session.getAttribute(CDRomRequestAction.SESSION_POOL));
			if (!("".equals(poolName) || poolName == null)) {
				poolName = CDRomRequestAction.QUOTE_STRING + poolName;
			}
			/**
			 * for getting server path where configuration files are saved.
			 */
			final String dataPath = this.getText(CDRomRequestAction.DATA_PATH) + CDRomRequestAction.QUOTE_UPLOAD + poolName + "/CDROM/";

			final String dataPath1 = this.getText(CDRomRequestAction.DATA_PATH) + CDRomRequestAction.QUOTE_UPLOAD + poolName + "/CDROMAddressing/";

			final String dataPath2 = this.getText(CDRomRequestAction.DATA_PATH) + CDRomRequestAction.QUOTE_UPLOAD + poolName + "/CDROMAdditional/";
	
			this.bean.setDataPath(dataPath);
			this.bean.setDataPathAddressing(dataPath1);
			this.bean.setDataPathAdditional(dataPath2);
			/**
			 *  Second Approver.
			 */
			final int level = 1;
			System.out.println("level---" + level);
			
			final String userId = this.bean.getUserEmpId();
			final String employeeCode = this.bean.getEmployeeCode();
			System.out.println("employeeCode--" + employeeCode);
			String nextApprover = userId;
			final Object[][] empFlow = this.generateEmpFlow(employeeCode, CDRomRequestAction.NAME_D1, level + 1);

			if (empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
				this.setApproverName(nextApprover);
				this.bean.setSecondAppFlag(true);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.getNavigationPanel(2);
		return SUCCESS;
	}

	
	/**
	 * @param nextApprover - Used to set who is the next approver.
	 */
	public void setApproverName(final String nextApprover) {
		final DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		try {
			
			model.initiate(context, session);
			final String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id, EMP_TOKEN from hrms_emp_offc " + " where emp_id=" + nextApprover;
			final Object [][] data = model.getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				this.bean.setSecondApproverName(String.valueOf(data[0][0]));
				this.bean.setSecondApproverCode(String.valueOf(data[0][1]));
				this.bean.setSecondApproverToken(String.valueOf(data[0][2]));
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		model.terminate();
	}
	/**
	 * purpose - Used to Download Template for Addressing detail.
	 * 
	 */
	public void downloadTemplate() {
		try {
			final String templateName = request.getParameter("templateName");
			final String dataPath3 = this.getText(CDRomRequestAction.DATA_PATH);
			final String filePath = dataPath3 + "/D1/Templates/" + templateName;
			MigrateExcelData.openTemplate(request, response, templateName, filePath);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	

	/**
	 *Purpose - Used to set employee details.
	 * @return String.
	 */
	public String f9Employee() {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) , " + " HRMS_LOCATION.LOCATION_NAME, HRMS_EMP_ADDRESS.ADD_PH1, HRMS_EMP_OFFC.EMP_ID, EMP_REPORTING_OFFICER " + " FROM HRMS_EMP_OFFC " + " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " + " LEFT JOIN HRMS_LOCATION ON(HRMS_EMP_ADDRESS.ADD_CITY=HRMS_LOCATION.LOCATION_CODE) ";
		query += this.getprofileQuery(this.bean);
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		final String[] headers = {"Employee Token", this.getMessage(CDRomRequestAction.EMP_NAME)};
		final String[] headerWidth = {"25", "75"};

		final String[] fieldNames = {"employeeToken", "employeeName", "officeLocation", "phNo", "employeeCode", "firstApproverCode"};

		final int[] columnIndex = {0, 1, 2, 3, 4, 5};

		final String submitFlag = "true";

		final String submitToMethod = "CDRomRequest_getEmployeeDetailsAction.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return CDRomRequestAction.F9_PAGE;

	}

	/**
	 *Purpose - Used to set Department..
	 * @return String.
	 */
	public String f9Dept()  {

		final String query = "SELECT DEPT_ID,DEPT_NAME from HRMS_DEPT";
		
		final String[] headers = {"Department Id", this.getMessage("dept.name")};

		final String[] headerWidth = {"20", "80"};

		final String[] fieldNames = {"deptId", "deptName"};

		final int[] columnIndex = {0, 1};

		final String submitFlag = CDRomRequestAction.FLAG_FALSE;

		final String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return CDRomRequestAction.F9_PAGE;

	}

	/*public String getManagerAction() {

		CDRomRequestModel model = new CDRomRequestModel();
		model.initiate(context, session); 
		model.getManagerName(this.bean);

		// Second Approver
		int level = 1;
		System.out.println("level---" + level);
		String userId = bean.getUserEmpId();
		String employeeCode = bean.getEmployeeCode();
		System.out.println("employeeCode--" + employeeCode);
		String nextApprover = userId;
		Object[][] empFlow = generateEmpFlow(employeeCode, "D1", level + 1);

		if (empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			setApproverName(nextApprover);
			bean.setSecondAppFlag(true);
		}
		model.terminate(); // terminate the model class

		getNavigationPanel(2);
		return "success";
	}*/


	/**
	 * Purpose - Set Employee Details.
	 * @return String.
	 */
	public String getEmployeeDetailsAction() {

		final CDRomRequestModel model = new CDRomRequestModel();
		model.initiate(context, session); 
		model.getEmployeeDetails(this.bean);
		this.setApproverName();
		model.terminate(); 
		this.getNavigationPanel(2);
		this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_Y);
		return CDRomRequestAction.RETURN_TYPE_SUCCESS;
	}

	/**
	 * Purpose - Used to set Approver Details.
	 * @return String
	 */
	public String f9Approver() {
		String str = CDRomRequestAction.ZERO_CHAR;

		if (this.bean.getFirstApproverCode() != null && !"".equals(this.bean.getFirstApproverCode()) && !"null".equals(this.bean.getFirstApproverCode())) {
			str = this.bean.getFirstApproverCode().trim();
		} else {
			str = CDRomRequestAction.ZERO_CHAR;
		}

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";
		if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.bean.getUserProfileDivision() + " )";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + "," + this.bean.getEmployeeCode() + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = {this.getMessage("employee.id"), this.getMessage(CDRomRequestAction.EMP_NAME)};

		final String[] headerWidth = {"30", "70"};

		final String[] fieldNames = {"approverToken", "approverName", "approverCode"};

		final int[] columnIndex = {0, 1, 2};

		final String submitFlag = CDRomRequestAction.FLAG_FALSE;

		final String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return CDRomRequestAction.F9_PAGE;

	}

	/**
	 * Purpose - Used to Reset fields o JSP.
	 * @return String.
	 */
	public String reset() {
		/**
		 *  Employee Information.
		 */
		this.bean.setDraftFlag(false);
		this.bean.setCdRomID("");
		this.bean.setHiddenCode("");
		this.bean.setEmployeeCode("");
		this.bean.setEmployeeToken("");
		this.bean.setEmployeeName("");
		this.bean.setDeptName("");
		this.bean.setDeptNo("");
		this.bean.setOfficeLocation("");
		this.bean.setPhNo("");

		/**
		 *  Approver Details.
		 */
		this.bean.setFirstApproverCode("");
		this.bean.setFirstApproverToken("");
		this.bean.setFirstApproverName("");
		this.bean.setSecondApproverCode("");
		this.bean.setSecondApproverToken("");
		this.bean.setSecondApproverName("");
		this.bean.setApproverCode("");
		this.bean.setApproverToken("");
		this.bean.setApproverName("");

		/**
		 *  Request Details form fields.
		 */
		this.bean.setRequestName("");
		this.bean.setNoMaster("");
		this.bean.setNoCopy("");
		this.bean.setPakName("");
		this.bean.setAttachementRequestDesc("");
		this.bean.setSourceName("");
		this.bean.setUploadFileName("");

		/**
		 *  Delivery Information form fields.
		 */
		this.bean.setDeliveryDate("");
		this.bean.setDeliveryVia("");

		/**
		 *  Addressing Information form fields.
		 */
		this.bean.setSourceAddress("");
		this.bean.setUploadFileAddress("");
		this.bean.setAddressDocument("");
		this.bean.setProof("");
		this.bean.setAdditionalInfoDoc("");
		this.bean.setUploadFileAdditionalInfoDoc("");

		this.getNavigationPanel(2);
		return CDRomRequestAction.RETURN_TYPE_SUCCESS;

	}

	
	/**
	 * purpose - This method is called on back button.
	 * @return String.
	 */
	public String back() {
		this.input();
		this.getNavigationPanel(1);
		return CDRomRequestAction.RETURN_TYPE;
	}

	/**
	 * Purpose - To delete record.
	 * @return String
	 */
	public String delete() {
		final CDRomRequestModel model = new CDRomRequestModel();
		model.initiate(context, session);
		boolean result;
		result = model.delete(this.bean);
		model.terminate();
		if (result) {
			this.addActionMessage("Record Deleted successfully.");
		} else {
			this.addActionMessage("Record can not be deleted.");
		}
		this.input();
		return CDRomRequestAction.RETURN_TYPE;
	}

	// Draft
	
	
	/**
	 * Purpose - Sued to Save Application Data into DB.
	 * @return String.
	 */
	public String save() {
		final CDRomRequestModel model = new CDRomRequestModel();
		
		final String status = this.bean.getStatus();
		model.initiate(context, session);
		boolean result;
		if ("".equals(this.bean.getCdRomID())) {
			result = model.save(this.bean, request);

			if (result) {
				this.addActionMessage(this.getMessage("save"));

			} else {
				this.addActionMessage(CDRomRequestAction.MESSAGE);
			}
		} else {

			result = model.update(this.bean, request);
			if (result) {
				this.addActionMessage(this.getText("modMessage", ""));
			} else {
				this.addActionMessage(CDRomRequestAction.MESSAGE);
			}
		}
		
		model.terminate();
		try {
			this.input();
		} catch (final Exception e) {
			e.printStackTrace();
		
		}
		this.getNavigationPanel(1);
		this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_Y);
		this.bean.setDataPath(this.bean.getDataPath());
		return CDRomRequestAction.RETURN_TYPE;
	}

	// Send for Approval
	/*public String sendForApproval_janish() {
		CDRomRequestModel model = new CDRomRequestModel();
		System.out.println("in send for approval=======================");
		// String status = request.getParameter("status");
		String status = this.bean.getStatus();
		System.out.println("status update------" + status);
		System.out.println("this.bean.getCdRomID()---" + this.bean.getCdRomID());
		model.initiate(context, session);
		boolean result;
		if (this.bean.getCdRomID().equals("")) {
			result = model.sendForApproval(this.bean);

			if (result) {
				addActionMessage("Application send for approver xxxxxxxx.");
				String applicationId = this.bean.getCdRomID();
				System.out.println("applicationId ====" + applicationId);

				// Mail Setting Here
				Object[][] empFlow = generateEmpFlow(this.bean.getUserEmpId(), "D1",
						1);
				if (empFlow != null && empFlow.length > 0) {
					String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id from hrms_emp_offc "
							+ " where emp_id=" + String.valueOf(empFlow[0][1]);
					Object data[][] = model.getSqlModel()
							.getSingleResult(query);

					if (data != null && data.length > 0) {
						this.bean.setApproverName(String.valueOf(data[0][0]));
						this.bean.setFirstApproverCode(String.valueOf(data[0][1]));

						String approver = String.valueOf(data[0][1]);
						System.out.println("approver = " + approver);
					}

				} else {

				}

				String employeeCode = this.bean.getUserEmpId();

				System.out.println("applicationId is having value is=========="
						+ applicationId);
				System.out.println("sendForApproval++++++++++++++++++++++"
						+ employeeCode);
				sendMail(employeeCode, applicationId);
			} else {
				addActionMessage("Error occured sending application.");
			}
		} else {

			result = model.updateSendForApproval(this.bean);
			if (result) {
				addActionMessage("Application send for approver.");
				// Mail Setting Here
				Object[][] empFlow = generateEmpFlow(this.bean.getUserEmpId(), "D1",
						1);
				if (empFlow != null && empFlow.length > 0) {
					String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id from hrms_emp_offc "
							+ " where emp_id=" + String.valueOf(empFlow[0][1]);
					Object data[][] = model.getSqlModel()
							.getSingleResult(query);

					if (data != null && data.length > 0) {
						this.bean.setApproverName(String.valueOf(data[0][0]));
						this.bean.setFirstApproverCode(String.valueOf(data[0][1]));

						String approver = String.valueOf(data[0][1]);
						System.out.println("approver = " + approver);
					}

				} else {

				}

				String employeeCode = this.bean.getUserEmpId();
				System.out.println("in updateSendForApproval=================="
						+ employeeCode);
				String applicationId = this.bean.getCdRomID();
				System.out
						.println("applicationId is having value in updateSendForApproval=========="
								+ applicationId);
				sendMail(employeeCode, applicationId);
			}// end of if
			else {
				addActionMessage("Error occured sending application.");
			}// end of else
		}
		// model.getList(bean, request);

		model.terminate();
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(1);
		this.bean.setEnableAll("Y");
		input();
		return INPUT;
	}*/

	/*public String addMultipleFiles() {

		try {
			String[] srNo = request.getParameterValues("uploadFileSrNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("uploadFile");
			CDRomRequestModel model = new CDRomRequestModel();
			model.initiate(context, session);
			model.displayIteratorValueForUploadProof(srNo, proofName, this.bean);
			// model.setProofList(srNo, proofName, this.bean);
			String emploId = request.getParameter("emploId");
			logger.info("emploId  :: " + emploId);
			// model.getEmployeeDetails(emploId, helpdesk);
			// setDetails();
			bean.setUploadFileName("");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in addMultipleProof--------" + e);
			e.printStackTrace();
		}
		// getDataOnRefresh();
		getNavigationPanel(2);
		return SUCCESS;
	}*/

	
	
	/**
	 * Purpose - Set Approver Details, call from getEmployeeDetailsAction. 
	 */
	public void setApproverName() {
		final CDRomRequestModel model = new CDRomRequestModel();
		try {
			model.initiate(context, session);
			Object[][] empFlow = null;
			try {
				empFlow = this.generateEmpFlow(this.bean.getEmployeeCode(), CDRomRequestAction.NAME_D1, 1);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			System.out.println("empFlow.length in setApproverName method is ^^^^^^^^^^^^^^^^^^^^^^^^^^" + empFlow.length);

			if (empFlow != null && empFlow.length > 0) {
				final String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME ,EMP_ID FROM HRMS_EMP_OFFC " + " WHERE EMP_ID=" + String.valueOf(empFlow[0][0]);
				final Object [][] data = model.getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {
					this.bean.setFirstApproverToken(String.valueOf(data[0][0]));
					this.bean.setFirstApproverName(String.valueOf(data[0][1]));
					this.bean.setFirstApproverCode(String.valueOf(data[0][2]));
				}
			} else {
				this.bean.setFirstApproverCode("");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		model.terminate();
	}

	/**
	 * Purpose - Used to view application.
	 * @return String.
	 */
	public String callView() {
		final CDRomRequestModel model = new CDRomRequestModel();
		model.initiate(context, session);
		this.bean.setStatusDraftBack(true);
		this.reset();
		/**
		 *  Set Path.
		 */
		String poolName = String.valueOf(session.getAttribute(CDRomRequestAction.SESSION_POOL));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = CDRomRequestAction.QUOTE_STRING + poolName;
		}
		/**
		 *  for getting server path where configuration files are saved.
		 */
		final String dataPath = this.getText(CDRomRequestAction.DATA_PATH) + CDRomRequestAction.QUOTE_UPLOAD + poolName	+ "/CDROM/";

		final String dataPath1 = this.getText(CDRomRequestAction.DATA_PATH) + CDRomRequestAction.QUOTE_UPLOAD + poolName + "/CDROMAddressing/";

		final String dataPath2 = this.getText(CDRomRequestAction.DATA_PATH) + CDRomRequestAction.QUOTE_UPLOAD + poolName + "/CDROMAdditional/";
		
		this.bean.setDataPath(dataPath);
		this.bean.setDataPathAddressing(dataPath1);
		this.bean.setDataPathAdditional(dataPath2);
		
		String cdromID = request.getParameter("cdromId");
		final String status = request.getParameter(CDRomRequestAction.SEND_BACK);
		this.getNavigationPanel(2);

		try {
			if (CDRomRequestAction.DRAFT.equals(status)) {
				this.bean.setApprovalCommentsFlag(false);
			} else {
				this.bean.setApprovalCommentsFlag(true);

			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * FOR SUPER USER
		 */
		final String applCode = request.getParameter("applCode");
		if (applCode != null && applCode.length() > 0) {
			cdromID = applCode;
		}
		final Object[][] apprCommentListObj = model.getApproverCommentList(cdromID);
		this.populateApproverComments(apprCommentListObj);
		model.view(this.bean, request, cdromID);
		this.setApproverName();
		model.terminate();
		try {
			/**
			 * Flag changes added by Nilesh D
			 */ 
			
			if (CDRomRequestAction.DRAFT.equals(status)) {
				this.bean.setStatusDraftBack(true);
				this.bean.setDraftDataDisplyFlag(false);
				this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_Y);
				this.bean.setDraftFlag(true);
				this.getNavigationPanel(2);
			} else if (CDRomRequestAction.SEND_BACK.equals(status)) {
				this.bean.setStatusDraftBack(true);
				this.bean.setDraftDataDisplyFlag(true);
				this.bean.setApprovalCommentsFlag(false);
				this.bean.setDraftDataDisplyFlag(true);
				this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_Y);
				this.bean.setDraftFlag(true);
				this.getNavigationPanel(2);
			} else if (CDRomRequestAction.REJECTED.equals(status)) {
				this.bean.setStatusDraftBack(true);
				this.bean.setDraftDataDisplyFlag(true);
				this.bean.setApprovalCommentsFlag(false);
				this.bean.setDraftDataDisplyFlag(true);
				this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);
				this.bean.setDraftFlag(true);
				this.getNavigationPanel(3);
			} 	else if (CDRomRequestAction.APPROVED.equals(status)) {
				this.bean.setStatusDraftBack(true);
				this.bean.setDraftDataDisplyFlag(true);
				this.bean.setApprovalCommentsFlag(false);
				this.bean.setDraftDataDisplyFlag(true);
				this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);
				this.bean.setDraftFlag(true);
				this.getNavigationPanel(3);
			} 	else if ("process".equals(status)) {
				this.bean.setStatusDraftBack(true);
				this.bean.setDraftDataDisplyFlag(true);
				this.bean.setApprovalCommentsFlag(false);
				this.bean.setDraftDataDisplyFlag(true);
				this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);
				this.bean.setDraftFlag(true);
				this.getNavigationPanel(3);
			}  else {
				this.bean.setStatusDraftBack(false);
				this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);

				this.getNavigationPanel(4);

			}
		
			if (applCode != null && applCode.length() > 0) {
				this.getNavigationPanel(0);
				this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);
			}
		} catch (final Exception e) {
			this.getNavigationPanel(0);
			this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);
		}
		return SUCCESS;
	}

	/**
	 *  Added by Nilesh For File upload functionality. 
	 */
	
	/**
	 * @throws IOException - File not fount error / path not find error .
	 */
	public void viewAttachedProof() throws IOException {
		final String addedFile = this.bean.getRequestDetailFile();
		final String [] extension = addedFile.replace(CDRomRequestAction.STRING, CDRomRequestAction.SPECL_CHAR).split(CDRomRequestAction.SPECL_CHAR);
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.bean.getDataPath();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = CDRomRequestAction.FILE_PDF;
			final String applnDoc = CDRomRequestAction.FILE_DOC;
			final String applnXls = CDRomRequestAction.FILE_XLS;
			final String applnXlsx = CDRomRequestAction.FILE_XLSX;
			final String applnJpg = CDRomRequestAction.FILE_JPG;
			final String applnTxt = CDRomRequestAction.FILE_TXT;
			final String applnGif = CDRomRequestAction.FILE_GIF;
              
			final String mimeTypeAcrobat = CDRomRequestAction.MIME_TYPE_PDF;
			final String mimeTypeMSWord = CDRomRequestAction.MIME_TYPE_DOC;
			final String mimeTypeMSExcel = CDRomRequestAction.MIME_TYPE_XLS;
			final String mimeTypeJpg = CDRomRequestAction.FILE_JPG;
			final String mimeTypeTxt = CDRomRequestAction.FILE_TXT;
			final String mimeTypeGif = CDRomRequestAction.FILE_GIF;
              
			if (applnPdf.equals(ext)) {
				mimeType = mimeTypeAcrobat;
			} else if (applnDoc.equals(ext)) {
				mimeType = mimeTypeMSWord;
			} else if (applnXls.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnXlsx.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnJpg.equals(ext)) {
				mimeType = mimeTypeJpg;
			} else if (applnTxt.equals(ext)) {
				mimeType = mimeTypeTxt;
			} else if (applnGif.equals(ext)) {
				mimeType = mimeTypeGif;
			}

			response.setHeader("Content-type", "application/" + mimeType);
			response.setHeader("Content-disposition", "attachment;filename= \"" + addedFile + "\"");

			int iChar;
			fsStream = new FileInputStream(filePath);
			oStream = response.getOutputStream();

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (fsStream != null) {
				fsStream.close();
			}

			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
	}

	public void viewAttachedProofAddressing() throws IOException {
		final String addedFile = this.bean.getAddressInfoFile1();
		final String[] extension = addedFile.replace(CDRomRequestAction.STRING, CDRomRequestAction.SPECL_CHAR).split(CDRomRequestAction.SPECL_CHAR);
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.bean.getDataPathAddressing();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = CDRomRequestAction.FILE_PDF;
			final String applnDoc = CDRomRequestAction.FILE_DOC;
			final String applnXls = CDRomRequestAction.FILE_XLS;
			final String applnXlsx = CDRomRequestAction.FILE_XLSX;
			final String applnJpg = CDRomRequestAction.FILE_JPG;
			final String applnTxt = CDRomRequestAction.FILE_TXT;
			final String applnGif = CDRomRequestAction.FILE_GIF;
              
			final String mimeTypeAcrobat = CDRomRequestAction.MIME_TYPE_PDF;
			final String mimeTypeMSWord = CDRomRequestAction.MIME_TYPE_DOC;
			final String mimeTypeMSExcel = CDRomRequestAction.MIME_TYPE_XLS;
			final String mimeTypeJpg = CDRomRequestAction.FILE_JPG;
			final String mimeTypeTxt = CDRomRequestAction.FILE_TXT;
			final String mimeTypeGif = CDRomRequestAction.FILE_GIF;
              
			if (applnPdf.equals(ext)) {
				mimeType = mimeTypeAcrobat;
			} else if (applnDoc.equals(ext)) {
				mimeType = mimeTypeMSWord;
			} else if (applnXls.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnXlsx.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnJpg.equals(ext)) {
				mimeType = mimeTypeJpg;
			} else if (applnTxt.equals(ext)) {
				mimeType = mimeTypeTxt;
			} else if (applnGif.equals(ext)) {
				mimeType = mimeTypeGif;
			}
			response.setHeader("Content-type", "application/" + mimeType);
			response.setHeader("Content-disposition", "attachment;filename= \"" + addedFile + "\"");

			int iChar;
			fsStream = new FileInputStream(filePath);
			oStream = response.getOutputStream();

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (fsStream != null) {
				fsStream.close();
			}

			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
	}

	public void viewAttachedProofAdditional() throws IOException {
		final String addedFile = this.bean.getAddressInfoFile2();
		final String[] extension = addedFile.replace(CDRomRequestAction.STRING, CDRomRequestAction.SPECL_CHAR).split(CDRomRequestAction.SPECL_CHAR);
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.bean.getDataPathAdditional();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = CDRomRequestAction.FILE_PDF;
			final String applnDoc = CDRomRequestAction.FILE_DOC;
			final String applnXls = CDRomRequestAction.FILE_XLS;
			final String applnXlsx = CDRomRequestAction.FILE_XLSX;
			final String applnJpg = CDRomRequestAction.FILE_JPG;
			final String applnTxt = CDRomRequestAction.FILE_TXT;
			final String applnGif = CDRomRequestAction.FILE_GIF;
              
			final String mimeTypeAcrobat = CDRomRequestAction.MIME_TYPE_PDF;
			final String mimeTypeMSWord = CDRomRequestAction.MIME_TYPE_DOC;
			final String mimeTypeMSExcel = CDRomRequestAction.MIME_TYPE_XLS;
			final String mimeTypeJpg = CDRomRequestAction.FILE_JPG;
			final String mimeTypeTxt = CDRomRequestAction.FILE_TXT;
			final String mimeTypeGif = CDRomRequestAction.FILE_GIF;
              
			if (applnPdf.equals(ext)) {
				mimeType = mimeTypeAcrobat;
			} else if (applnDoc.equals(ext)) {
				mimeType = mimeTypeMSWord;
			} else if (applnXls.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnXlsx.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnJpg.equals(ext)) {
				mimeType = mimeTypeJpg;
			} else if (applnTxt.equals(ext)) {
				mimeType = mimeTypeTxt;
			} else if (applnGif.equals(ext)) {
				mimeType = mimeTypeGif;
			}

			response.setHeader("Content-type", "application/" + mimeType);
			response.setHeader("Content-disposition", "attachment;filename= \"" + addedFile + "\"");

			int iChar;
			fsStream = new FileInputStream(filePath);
			oStream = response.getOutputStream();

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (fsStream != null) {
				fsStream.close();
			}

			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
	}

	// For Approval
	/**
	 * @param apprCommentListObj - Used to set approver details.
	 */
	private void populateApproverComments(final Object[][] apprCommentListObj) {
		final List<CDRomRequestBean> approverCommentList = new ArrayList<CDRomRequestBean>(apprCommentListObj.length);

		for (int i = 0; i < apprCommentListObj.length; i++) {
			final CDRomRequestBean persDataChange = new CDRomRequestBean();
			persDataChange.setApprName(String.valueOf(apprCommentListObj[i][0]));
			persDataChange.setApprComments(String.valueOf(apprCommentListObj[i][1]));
			persDataChange.setApprDate(String.valueOf(apprCommentListObj[i][2]));
			persDataChange.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
			approverCommentList.add(persDataChange);
		}

		this.bean.setApproverCommentList(approverCommentList);
	}

	/**
	 * Purpose - Used to view application.
	 * @return String.
	 */
	public String viewDetails() {
		try {
			final String cdRomId = request.getParameter("cdRomId");
			this.bean.setForApproval(true);
			this.bean.setCdRomID(cdRomId);

			final CDRomApprovalModel model = new CDRomApprovalModel();
			model.initiate(context, session);
			this.bean.setStatusDraftBack(true);

			final Object[][] persDataObj = model.getPersDataChangeDetails(cdRomId);

			final Object[][] apprCommentListObj = model.getApproverCommentList(cdRomId);
			this.populateApproverComments(apprCommentListObj);

			model.terminate();

			final CDRomRequestModel cdRequestModel = new CDRomRequestModel();
			cdRequestModel.initiate(context, session);
			cdRequestModel.view(this.bean, request, cdRomId);
			cdRequestModel.terminate();
			this.setApproverName();

			final String status = String.valueOf(persDataObj[0][2]);
			final boolean readOnlyDetails = false;

			if ("P".equals(status) || "C".equals(status)) {
				this.bean.setStatusDraftBack(false);
				this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);
				this.bean.setForApproval(true);
				this.getNavigationPanel(5);
			} else if (CDRomRequestAction.STATUS_FORWORED.equals(status)) {
				final String userId = this.bean.getUserEmpId();
				final boolean isUserAHRApprover = model.isUserAHRApprover(userId);

				if (isUserAHRApprover) {
					this.getNavigationPanel(5);
					this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);
				} else {
					
					this.bean.setStatusDraftBack(true);
					this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);
					this.bean.setForApproval(false);

					this.getNavigationPanel(4);
				}
			} else {
				this.bean.setStatusDraftBack(true);
				this.bean.setEnableAll(CDRomRequestAction.FLAG_TYPE_N);
				this.bean.setForApproval(false);
				this.getNavigationPanel(4);
			}

			
			return "viewApprove";
		} catch (final Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * purpose - Approve application.
	 * @return String.
	 */
	public String approve() {
		final String cdromID = this.bean.getCdRomID();
		final String approverComments = this.bean.getApproverComments();
		final String userId = this.bean.getUserEmpId();
		final String employeeCode = this.bean.getEmployeeCode();
		String status = this.bean.getApplnStatus();
		System.out.println("status=======" + status);
		final int level = Integer.parseInt(this.bean.getLevel());

		final CDRomApprovalModel model = new CDRomApprovalModel();
		model.initiate(context, session);

		final String nextApprover = userId;
		status = model.approve(cdromID, approverComments, userId, status, nextApprover, level);

		final Object[][] apprCommentListObj = model.getApproverCommentList(cdromID);
		this.populateApproverComments(apprCommentListObj);
		if (CDRomRequestAction.STATUS_FORWORED.equals(status)) {
			this.addActionMessage("Application Forwarded successfully.");
		} else if (CDRomRequestAction.STATUS_APPROVED.equals(status)) {

			this.addActionMessage("Application approved successfully.");

		} else {
			this.addActionMessage("Application not processed ");

		}

		if (model.isUserAMagaer(cdromID, userId)) {
			this.sendApprovalMail(cdromID, userId, employeeCode, status);
		} else {
			this.sendApprovalMailToApplicantFinal(cdromID, userId, employeeCode, status);
		}

		this.bean.setForApproval(true);
		this.bean.setCdRomID("");
		this.getNavigationPanel(5);

		return CDRomRequestAction.RETURN_TYPE_APPROVE;
	}

	/**
	 * purpose - Used to Reject Application.
	 * @return String.
	 */
	public String reject() {
		final String cdromId = this.bean.getCdRomID();
		final String approverComments = this.bean.getApproverComments();
		final String employeeCode = this.bean.getEmployeeCode();
		final String userId = this.bean.getUserEmpId();
		final String status = this.bean.getApplnStatus();

		final CDRomApprovalModel model = new CDRomApprovalModel();
		model.initiate(context, session);
		model.reject(cdromId, approverComments, userId);

		final Object[][] apprCommentListObj = model.getApproverCommentList(cdromId);
		this.populateApproverComments(apprCommentListObj);

		this.addActionMessage("Application rejected successfully.");

		if (model.isUserAMagaer(cdromId, userId)) {
			this.sendRejectSenBackMailToApplicant(cdromId, userId, employeeCode, status);
		} else {
			this.sendRejectMailToApplicantandIT(cdromId, userId, employeeCode, status);

		}

		this.bean.setForApproval(true);
		this.getNavigationPanel(5);
		
		return CDRomRequestAction.RETURN_TYPE_APPROVE;
	}
	
	/**
	 * purpose - Used for Return Back Applications.
	 * @return String.
	 */
	public String sendBack() {
		final String cdromId = this.bean.getCdRomID();
		final String approverComments = this.bean.getApproverComments();
		final String employeeCode = this.bean.getEmployeeCode();
		final String userId = this.bean.getUserEmpId();

		final CDRomApprovalModel model = new CDRomApprovalModel();
		model.initiate(context, session);
		final String status = model.sendBack(cdromId, approverComments, userId);

		final Object[][] apprCommentListObj = model.getApproverCommentList(cdromId);
		this.populateApproverComments(apprCommentListObj);

		this.bean.setApplnStatus(status);
		this.bean.setReadOnlyDetails(true);
		this.bean.setApproverComments("");

		this.addActionMessage("Application send back successfully.");

		if (model.isUserAMagaer(cdromId, userId)) {
			this.sendRejectSenBackMailToApplicant(cdromId, userId, employeeCode, status);
		} else {
			this.sendSendbackMailToApplicantandIT(cdromId, userId, employeeCode, status);

		}

		this.bean.setForApproval(true);
		this.bean.setCdRomID("");

		this.getNavigationPanel(5);
		
		return CDRomRequestAction.RETURN_TYPE_APPROVE;

	}
	
	/**
	 * purpose - Send Rejected mail to IT & applicant --Added by Nilesh D----.
	 * @param cdromId - Used to get CD_ROM ID.
	 * @param userId - User ID.
	 * @param employeeCode - Used for  sending to_mail.  
	 * @param status - Status (A/P/R/B) to be displayed.
	 */
	private void sendRejectMailToApplicantandIT(final String cdromId, final String userId, final String employeeCode, final String status) {
		try {
			final CDRomRequestModel model = new CDRomRequestModel();
			
			model.initiate(context, session);

			final String query1 = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID " + " FROM HRMS_D1_APPROVAL_SETTINGS " + " WHERE APP_SETTINGS_TYPE = 'I' AND APP_EMAIL_ID IS NOT NULL";
			final Object [][] data1 = model.getSqlModel().getSingleResult(query1);

			final String initiatorQuery = " select NVL(CDROM_EMP_ID,0) from HRMS_D1_CDROM_REQUEST where CDROM_ID = " + cdromId;
			final Object [][] initiatorData = model.getSqlModel().getSingleResult(initiatorQuery);

			final String apprQuery = " SELECT CDROM_APPROVER FROM HRMS_D1_CDROM_DATA_PATH WHERE CDROM_ID =  " + cdromId;
			final Object [][] apprDataObj = model.getSqlModel().getSingleResult(apprQuery);

			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-CDROM REQUEST APPLICATION DETAILS REJECTED FROM  IT-APPROVER");
			template.getTemplateQueries();
			/**
			 * FROM EMAIL.
			 */
			final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); 
			templateQuery1.setParameter(1, userId);
			
			/**
			 * TO EMAIL.
			 */
			final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); 
			templateQuery2.setParameter(1, employeeCode);
			
			final  EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, cdromId);

			final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, cdromId);

			final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, cdromId);
			template.configMailAlert();

			final EmailTemplateQuery templateQueryApp6 = template.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, userId);

			String[] empData = null;

			if (initiatorData != null && initiatorData.length > 0) {
				empData = new String[initiatorData.length];
				for (int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(initiatorData[i][0]);
				}
			}

			String[] approverData = new String[apprDataObj.length];
			if (apprDataObj != null && apprDataObj.length > 1) {
				approverData = new String[apprDataObj.length];
				for (int i = 0; i < approverData.length; i++) {
					approverData[i] = String.valueOf(apprDataObj[i][0]);
				}
			}

			final String []  keepData = new String[1];
			keepData[0] = String.valueOf(apprDataObj[0][0]);

			if (CDRomRequestAction.STATUS_APPROVED.equals(status)) {
				if (!this.bean.getEmployeeCode().equals(this.bean.getCompletedByCode())) {
					template.sendApplicationMailToKeepInfo(String.valueOf(initiatorData[0][0]));
				}

			}

			template.sendApplicationMailToKeepInfo(keepData);
			if (data1 != null && data1.length > 0) {
				template.sendApplicationMailToGroups(data1);
			}
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			template.getTemplateQueries();
			template.clearParameters();
			template.terminate();

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	
/**
 * Purpose - Mail functionality for send back to applicant and It --Nilesh D--. 
 */
	/**
	  * @param cdromId - Used to get CD_ROM ID.
	 * @param userId - User ID.
	 * @param employeeCode - Used for  sending to_mail.  
	 * @param status - Status (A/P/R/B) to be displayed.
	 */
	private void sendSendbackMailToApplicantandIT(final String cdromId, final String userId, final String employeeCode, final String status) {
		try {
			final CDRomRequestModel model = new CDRomRequestModel();
			model.initiate(context, session);
			final String query1 = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID " + " FROM HRMS_D1_APPROVAL_SETTINGS " + " WHERE APP_SETTINGS_TYPE = 'I' AND APP_EMAIL_ID IS NOT NULL";
			final Object [][] data1 = model.getSqlModel().getSingleResult(query1);

			final String initiatorQuery = " select NVL(CDROM_EMP_ID,0) from HRMS_D1_CDROM_REQUEST where CDROM_ID = " + cdromId;
			final Object [][] initiatorData = model.getSqlModel().getSingleResult(initiatorQuery);

			final String apprQuery = " SELECT CDROM_APPROVER FROM HRMS_D1_CDROM_DATA_PATH WHERE CDROM_ID =  " + cdromId;
			final Object [][] apprDataObj  = model.getSqlModel().getSingleResult(apprQuery);

			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-CDROM REQUEST APPLICATION DETAILS SEND-BACK FROM  IT-APPROVER");
			template.getTemplateQueries();
			/**
			 * FROM EMAIL.
			 */
			final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); 
			templateQuery1.setParameter(1, userId);
			/**
			 * TO EMAIL.
			 */
			final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, employeeCode);
			
			final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, cdromId);

			final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, cdromId);

			final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, cdromId);

			final EmailTemplateQuery templateQueryApp6 = template.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, userId);

			template.configMailAlert();

			String[] empData = null;

			if (initiatorData != null && initiatorData.length > 0) {
				empData = new String[initiatorData.length];
				for (int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(initiatorData[i][0]);
				}
			}

			String[] approverData = new String[apprDataObj.length];
			if (apprDataObj != null && apprDataObj.length > 1) {
				approverData = new String[apprDataObj.length];
				for (int i = 0; i < approverData.length; i++) {
					approverData[i] = String.valueOf(apprDataObj[i][0]);
				}
			}

			final String [] keepData = new String[1];
			keepData[0] = String.valueOf(apprDataObj[0][0]);

			if (CDRomRequestAction.STATUS_APPROVED.equals(status)) {
				if (!this.bean.getEmployeeCode().equals(this.bean.getCompletedByCode())) {
					
					template.sendApplicationMailToKeepInfo(String.valueOf(initiatorData[0][0]));
				}

			}

			template.sendApplicationMailToKeepInfo(keepData);
			if (data1 != null && data1.length > 0) {
				template.sendApplicationMailToGroups(data1);
			}

			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			template.getTemplateQueries();
			template.clearParameters();
			template.terminate();

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Purpose -  Request Application to approver for processing.
	 * @param cdromId - CD ROM Id.
	 * @param userId - Current User.
	 * @param employeeCode - Employee ID. 
	 * @param status - Status.
	 */
	private void sendApprovalMail(final String cdromId, final String userId, final String employeeCode, final String status) {

		try {
			System.out.println("SEND APPROVAL MAIL");
		// MAIL FROM APPROVER TO APPLICANT

			try {
				final EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate("D1-CDROM REQUEST APPLICATION DETAILS FROM  APPROVER TO APPLICANT");
				template.getTemplateQueries();
				/**
				 *FROM EMAIL.
				 */
				final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, userId);
				/**
				 * TO EMAIL.
				 */
				final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, employeeCode);
				
				final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, cdromId);
				
				final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, cdromId);

				final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, cdromId);

				template.configMailAlert();
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();

			} catch (final Exception e) {
				e.printStackTrace();
			}

			try {
				final CDRomRequestModel model = new CDRomRequestModel();
				
				model.initiate(context, session);
				final String query1 = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID " + " FROM HRMS_D1_APPROVAL_SETTINGS " + " WHERE APP_SETTINGS_TYPE = 'I' AND APP_EMAIL_ID IS NOT NULL";
				final Object [][] data1 = model.getSqlModel().getSingleResult(query1);

				final EmailTemplateBody templateApp = new EmailTemplateBody();
				templateApp.initiate(context, session);
				templateApp.setEmailTemplate("D1-CDROM REQUEST APPLICATION DETAILS FROM  APPROVER TO MANAGER");
				templateApp.getTemplateQueries();
				/**
				 * FROM EMAIL.
				 */
				final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); 
				templateQueryApp1.setParameter(1, userId);
				/**
				 * TO EMAIL.
				 */
				final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); 
				templateQueryApp2.setParameter(1, CDRomRequestAction.ZERO_CHAR);
				
				final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
				templateQueryApp3.setParameter(1, cdromId);
				
				final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
				templateQueryApp4.setParameter(1, cdromId);

				final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, userId);

				final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
				templateQueryApp6.setParameter(1, cdromId);

				templateApp.configMailAlert();
				if (data1 != null && data1.length > 0) {

					templateApp.sendApplicationMailToGroups(data1);
				}
				
				templateApp.clearParameters();
				templateApp.terminate();

				model.terminate();

			} catch (final Exception e) {
				e.printStackTrace();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param cdromId -CD ROM Id.
	 * @param userId Current User.
	 * @param employeeCode - Employee Id.
	 * @param status -Status.
	 */
	private void sendApprovalMailToApplicantFinal(final String cdromId, final String userId, final String employeeCode, final String status) {

		try {
			final CDRomRequestModel model = new CDRomRequestModel();
		
			model.initiate(context, session);
			final String query = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID " 	+ " FROM HRMS_D1_APPROVAL_SETTINGS " + " WHERE APP_SETTINGS_TYPE = 'I' AND APP_EMAIL_ID IS NOT NULL";
			final Object [][] data = model.getSqlModel().getSingleResult(query);

			final String apprQuery1 = " SELECT DISTINCT CDROM_APPROVER FROM HRMS_D1_CDROM_DATA_PATH WHERE CDROM_ID =  " + cdromId;
			final Object [][] apprData1 = model.getSqlModel().getSingleResult(apprQuery1);

			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CDROM REQUEST APPLICATION DETAILS APPROVE/REJECTED/SENDBACK FROM  HR");
			templateApp.getTemplateQueries();
			/**
			 *FROM EMAIL.
			 */
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); 
			templateQueryApp1.setParameter(1, userId);
			/**
			 * TO EMAIL.
			 */	
			
			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); 
			templateQueryApp2.setParameter(1, employeeCode);

			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, cdromId);

			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, cdromId);

			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userId);
            
			/**
             *Approver Comments.
             */ 
			final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6); 
			templateQueryApp6.setParameter(1, cdromId);

			templateApp.configMailAlert();
			if (data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}

			String[] apprData = new String[apprData1.length];

			if (apprData1 != null && apprData1.length > 1) {
				System.out.println("apprObj.length : " + apprData1.length);
				apprData = new String[apprData1.length];
				for (int i = 0; i < apprData.length; i++) {
					apprData[i] = String.valueOf(apprData1[i][0]);
				}
			}
			templateApp.sendApplicationMailToKeepInfo(apprData);
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	private void sendRejectSenBackMailToApplicant(final String cdromId, final String userId, final String employeeCode, final String status) {
		try {
			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-CDROM REQUEST APPLICATION DETAILS REJECTED/SENDBACK FROM  APPROVER");
			template.getTemplateQueries();
			/**
			 *FROM EMAIL.
			 */
			final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, userId);
			/**
			 *TO EMAIL.
			 */
			final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); 
			templateQuery2.setParameter(1, employeeCode);
			
			final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, cdromId);

			final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, cdromId);

			final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, cdromId);

			template.configMailAlert();
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			template.getTemplateQueries();
			template.clearParameters();
			template.terminate();

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	public void sendMail(final String employeeCode, final String applicationId) {

		try {
			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			template.setEmailTemplate("D1-CDROM REQUEST APPLICATION DETAILS TO APPROVER");

			template.getTemplateQueries();
			/**
			 *FROM EMAIL.
			 */
			final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); 
			templateQuery1.setParameter(1, employeeCode);
			/**
			 * TO EMAIL.
			 */
			final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); 
			
			String approverCode = "";
			if (!"".equals(this.bean.getFirstApproverCode()) && this.bean.getFirstApproverCode() != null) {
				approverCode = this.bean.getFirstApproverCode();
			} else if (!"".equals(this.bean.getApproverCode()) && this.bean.getApproverCode() != null) {
				approverCode = this.bean.getApproverCode();
			}
			templateQuery2.setParameter(1, approverCode);

			final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);

			final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);

			template.configMailAlert();
			// Attach file to mail
			/*
			 * Vector attachFile = new Vector();
			 * System.out.println("this.bean.getUploadFileName()---" +
			 * bean.getUploadFileName());
			 * System.out.println("bean.getUploadFileAdditionalInfoDoc()---" +
			 * bean.getUploadFileAdditionalInfoDoc());
			 * System.out.println("bean.getUploadFileAddress()---" +
			 * bean.getUploadFileAddress()); if
			 * (!bean.getUploadFileName().equals("")) {
			 * attachFile.add(bean.getDataPath() + bean.getUploadFileName());
			 * System.out.println("if1---" + attachFile.get(0)); }
			 * 
			 * if (!bean.getUploadFileAdditionalInfoDoc().equals("")) {
			 * attachFile.add(bean.getDataPathAdditional() +
			 * bean.getUploadFileAdditionalInfoDoc());
			 * System.out.println("if2---" + attachFile.get(1)); }
			 * 
			 * if (!bean.getUploadFileAddress().equals("")) {
			 * attachFile.add(bean.getDataPathAddressing() +
			 * bean.getUploadFileAddress()); System.out.println("if3---" +
			 * attachFile.get(2)); } String[] strFile = new
			 * String[attachFile.size()]; System.out.println("size---" +
			 * attachFile.size()); System.out.println("size1---" +
			 * strFile.length); for (int i = 0; i < strFile.length; i++) {
			 * strFile[i] = (String) attachFile.get(i);
			 * System.out.println("inside for value---" +
			 * String.valueOf(strFile[i])); }
			 * 
			 * template.sendApplMailWithAttachment(strFile);
			 */

			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Purpose - Send for approval function // Added by Nilesh.
	 * @return String.
	 */
	public String sendForApproval() {
		try {
			final CDRomRequestModel model = new CDRomRequestModel();
			model.initiate(context, session);
			final boolean result = model.sendForApprovalFunction(this.bean, request);

			final String trackCOde = this.bean.getAuthorizedToken();
			this.addActionMessage("Application send for approval successfully \n Tracking No: " + trackCOde);
			if (result) {

				final String applicationId = this.bean.getCdRomID();
				System.out.println("applicationId ====" + applicationId);
				final String employeeCode = this.bean.getUserEmpId();

				this.sendMail(employeeCode, applicationId);

			} else {
				this.addActionMessage("Error occured sending application.");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();

		return CDRomRequestAction.RETURN_TYPE;
	}

}
