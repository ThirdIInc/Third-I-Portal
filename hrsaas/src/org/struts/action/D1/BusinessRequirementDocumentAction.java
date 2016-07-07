package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.paradyne.bean.D1.BusinessRequirementDocument;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.BusinessRequirementDocumentModel;
import org.struts.lib.ParaActionSupport;

/**
 * @modified By AA1711
 * @purpose : Change Request  
 */

public class BusinessRequirementDocumentAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String SPECL_CHAR_HASH = "#";
	private static final String EXTENSION_GPG = "jpg";
	private static final String EXTENSION_GIF = "txt";
	private static final String EXTENSION_TXT = "gif";
	private static final String DUPLICATE_MESG = "duplicate";
	private static final String APPL_CODE = "applCode";
	private static final String RETURN_TYPE_SUCCESS = "SUCCESS";
	private static final String SETENABLE_ALL_N = "N";
	private static final String RETURN_FLAG = "true";
	private static final String EMP_ID = "empid";
	private static final String RETURN_FLAG_FALSE = "false";
	private static final String F9_PAGE = "f9page";
	private static final String SERIAL_NO = "serialNo";
	private static final String STAKE_HOLDER = "stakeholderEmpNameItt";
	private BusinessRequirementDocument brdBean;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		this.brdBean = new BusinessRequirementDocument();
		this.brdBean.setMenuCode(2235);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return this.brdBean;
	}

	/**Method Name: 
	 * @purpose :
	 * @return
	 */
	public BusinessRequirementDocument getBrdBean() {
		return this.brdBean;
	}

	/**Method Name: 
	 * @purpose :
	 * @param brdBean
	 */
	public void setBrdBean(BusinessRequirementDocument brdBean) {
		this.brdBean = brdBean;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() {
		String poolName = String.valueOf(this.session
				.getAttribute("session_pool"));
		if ((!"".equals(poolName)) && (poolName != null)) {
			poolName = "/" + poolName;
		}
		String dataPath = getText("data_path") + "/upload " + poolName
				+ "/BusinessRequirementDocument/";
		this.brdBean.setDataPath(dataPath);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	public String input() {
		String userId = this.brdBean.getUserEmpId();
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		model.myAction(this.brdBean, this.request, userId);
		model.myonGoingProj(this.brdBean, this.request, userId);
		this.brdBean.setListType("inProcess");
		getNavigationPanel(1);
		this.brdBean.setEnableAll("Y");
		return "input";
	}

	/**Method Name: callClosedList()
	 * @purpose : Display List of Closed Project
	 * @return String 
	 */
	public String callClosedList() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		model.mycloseProj(this.brdBean, this.request, this.brdBean
				.getUserEmpId());
		this.brdBean.setListType("closed");
		getNavigationPanel(1);
		this.brdBean.setEnableAll("Y");
		model.terminate();
		return "input";
	}

	/**Method Name: callCancelBRDList
	 * @purpose :Display List of Canceled Project
	 * @return String
	 */
	public String callCancelBRDList() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		model.myCancelProject(this.brdBean, this.request, this.brdBean
				.getUserEmpId());
		this.brdBean.setListType("cancelled");
		getNavigationPanel(1);
		this.brdBean.setEnableAll("Y");
		model.terminate();
		return "input";
	}

	/**Method Name: openAttachedFile
	 * @purpose : Show Upload Document
	 * @throws IOException
	 */
	public void openAttachedFile() throws IOException {
		String addedFile = this.request.getParameter("fileName");
		String[] extension = addedFile.replace(".", "#").split("#");
		String ext = extension[(extension.length - 1)];
		String mimeType = "";
		String dataPath = this.brdBean.getDataPath();
		String filePath = dataPath + addedFile;
		OutputStream oStream = null;
		FileInputStream fsStream = null;
		try {
			String applnPdf = "pdf";
			String applnDoc = "doc";
			String applnXls = "xls";
			String applnXlsx = "xlsx";
			String applnJpg = "jpg";
			String applnTxt = "txt";
			String applnGif = "gif";
			String mimeTypeAcrobat = "acrobat";
			String mimeTypeMSWord = "msword";
			String mimeTypeMSExcel = "msexcel";
			String mimeTypeJpg = "jpg";
			String mimeTypeTxt = "txt";
			String mimeTypeGif = "gif";

			if ("pdf".equals(ext))
				mimeType = "acrobat";
			else if ("doc".equals(ext))
				mimeType = "msword";
			else if ("xls".equals(ext))
				mimeType = "msexcel";
			else if ("xlsx".equals(ext))
				mimeType = "msexcel";
			else if ("jpg".equals(ext))
				mimeType = "jpg";
			else if ("txt".equals(ext))
				mimeType = "txt";
			else if ("gif".equals(ext)) {
				mimeType = "gif";
			}
			this.response.setHeader("Content-type", "application/" + mimeType);
			this.response.setHeader("Content-disposition",
					"attachment;filename= \"" + addedFile + "\"");
			fsStream = new FileInputStream(filePath);
			oStream = this.response.getOutputStream();
			int iChar;
			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
		} catch (Exception e) {
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

	/**Method Name: viewAttachedFile()
	 * @purpose : View  uploaded Document
	 * @throws IOException
	 */
	public void viewAttachedFile() throws IOException {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		String fileCode = this.request.getParameter("viewCode11");
		String appCode = this.request.getParameter("appCode11");
		String fileName = this.request.getParameter("logFileName");
		if ((fileName != null) || (!fileName.equals(""))) {
			String addedFile = fileName;
			String[] extension = addedFile.replace(".", "#").split("#");
			String ext = extension[(extension.length - 1)];
			String mimeType = "";
			String dataPath = this.brdBean.getDataPath();
			String filePath = dataPath + addedFile;
			OutputStream oStream = null;
			FileInputStream fsStream = null;
			try {
				String applnPdf = "pdf";
				String applnDoc = "doc";
				String applnXls = "xls";
				String applnXlsx = "xlsx";
				String applnJpg = "jpg";
				String applnTxt = "txt";
				String applnGif = "gif";

				String mimeTypeAcrobat = "acrobat";
				String mimeTypeMSWord = "msword";
				String mimeTypeMSExcel = "msexcel";
				String mimeTypeJpg = "jpg";
				String mimeTypeTxt = "txt";
				String mimeTypeGif = "gif";

				if ("pdf".equals(ext))
					mimeType = "acrobat";
				else if ("doc".equals(ext))
					mimeType = "msword";
				else if ("xls".equals(ext))
					mimeType = "msexcel";
				else if ("xlsx".equals(ext))
					mimeType = "msexcel";
				else if ("jpg".equals(ext))
					mimeType = "jpg";
				else if ("txt".equals(ext))
					mimeType = "txt";
				else if ("gif".equals(ext)) {
					mimeType = "gif";
				}
				this.response.setHeader("Content-type", "application/"
						+ mimeType);
				this.response.setHeader("Content-disposition",
						"attachment;filename= \"" + addedFile + "\"");
				fsStream = new FileInputStream(filePath);
				oStream = this.response.getOutputStream();
				int iChar;
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch (Exception e) {
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
		} else {
			addActionMessage("No File to View");
		}
	}

	/**Method Name: viewNoAttachedFile()
	 * @purpose : View  uploaded Document
	 * @throws IOException
	 */
	public void viewNoAttachedFile() throws IOException {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		String fileCode = this.request.getParameter("viewCode11");
		String appCode = this.request.getParameter("appCode11");
		String filenameQuery = "SELECT BUSINESS_UPLOAD_FILE FROM HRMS_D1_BUSINESS_PATH where BUSINESS_CODE = "
				+ fileCode + " AND BUSINESS_PROJ_CLOSE_BY =" + appCode;
		Object[][] fileObj = model.getSqlModel().getSingleResult(filenameQuery);
		String fileName = String.valueOf(fileObj[0][0]);
		if (!fileObj[0][0].equals("")) {
			String addedFile = String.valueOf(fileObj[0][0]);
			String[] extension = addedFile.replace(".", "#").split("#");
			String ext = extension[(extension.length - 1)];
			String mimeType = "";
			String dataPath = this.brdBean.getDataPath();
			String filePath = dataPath + addedFile;
			OutputStream oStream = null;
			FileInputStream fsStream = null;
			try {
				String applnPdf = "pdf";
				String applnDoc = "doc";
				String applnXls = "xls";
				String applnXlsx = "xlsx";
				String applnJpg = "jpg";
				String applnTxt = "txt";
				String applnGif = "gif";

				String mimeTypeAcrobat = "acrobat";
				String mimeTypeMSWord = "msword";
				String mimeTypeMSExcel = "msexcel";
				String mimeTypeJpg = "jpg";
				String mimeTypeTxt = "txt";
				String mimeTypeGif = "gif";

				if ("pdf".equals(ext))
					mimeType = "acrobat";
				else if ("doc".equals(ext))
					mimeType = "msword";
				else if ("xls".equals(ext))
					mimeType = "msexcel";
				else if ("xlsx".equals(ext))
					mimeType = "msexcel";
				else if ("jpg".equals(ext))
					mimeType = "jpg";
				else if ("txt".equals(ext))
					mimeType = "txt";
				else if ("gif".equals(ext)) {
					mimeType = "gif";
				}
				this.response.setHeader("Content-type", "application/"
						+ mimeType);
				this.response.setHeader("Content-disposition",
						"attachment;filename= \"" + addedFile + "\"");

				fsStream = new FileInputStream(filePath);
				oStream = this.response.getOutputStream();
				int iChar;
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch (Exception e) {
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
		} else {
			addActionMessage("No File to View");
		}
	}

	/**Method Name: viewInitiatorAttachedFile
	 * @purpose : View uploaded Document by Current Owner
	 * @throws IOException
	 */
	public void viewInitiatorAttachedFile() throws IOException {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		String fileCode = this.request.getParameter("viewCode11");
		String appCode = this.request.getParameter("appCode11");
		String filenameQuery = "SELECT PROJ_UPLOAD_DOC FROM HRMS_D1_BUSINESS_REQUIREMENT where BUSINESS_CODE = "
				+ fileCode + " AND PROJ_COMPLETED_BY =" + appCode;
		Object[][] fileObj = model.getSqlModel().getSingleResult(filenameQuery);
		if (!fileObj[0][0].equals("")) {
			String addedFile = String.valueOf(fileObj[0][0]);
			String[] extension = addedFile.replace(".", "#").split("#");
			String ext = extension[(extension.length - 1)];
			String mimeType = "";
			String dataPath = this.brdBean.getDataPath();
			String filePath = dataPath + addedFile;

			OutputStream oStream = null;
			FileInputStream fsStream = null;
			try {
				String applnPdf = "pdf";
				String applnDoc = "doc";
				String applnXls = "xls";
				String applnXlsx = "xlsx";
				String applnJpg = "jpg";
				String applnTxt = "txt";
				String applnGif = "gif";

				String mimeTypeAcrobat = "acrobat";
				String mimeTypeMSWord = "msword";
				String mimeTypeMSExcel = "msexcel";
				String mimeTypeJpg = "jpg";
				String mimeTypeTxt = "txt";
				String mimeTypeGif = "gif";

				if ("pdf".equals(ext))
					mimeType = "acrobat";
				else if ("doc".equals(ext))
					mimeType = "msword";
				else if ("xls".equals(ext))
					mimeType = "msexcel";
				else if ("xlsx".equals(ext))
					mimeType = "msexcel";
				else if ("jpg".equals(ext))
					mimeType = "jpg";
				else if ("txt".equals(ext))
					mimeType = "txt";
				else if ("gif".equals(ext)) {
					mimeType = "gif";
				}

				this.response.setHeader("Content-type", "application/"
						+ mimeType);
				this.response.setHeader("Content-disposition",
						"attachment;filename= \"" + addedFile + "\"");

				fsStream = new FileInputStream(filePath);
				oStream = this.response.getOutputStream();
				int iChar;
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch (Exception e) {
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
		} else {
			addActionMessage("No File to View");
		}
	}

	/**Method Name: addNew()
	 * @purpose : add new BRD Application
	 * @return String 
	 */
	public String addNew() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		this.brdBean.setBusinessFlag("true");
		this.brdBean.setClosureFlag("true");
		this.brdBean.setConstraintsFlag("true");
		this.brdBean.setAssumptionFlag("true");
		this.brdBean.setApplicantCommnetsFlag("true");
		reset();
		String poolName = String.valueOf(this.session
				.getAttribute("session_pool"));
		String dataPath = getText("data_path")
				+ "/BusinessRequirementDocument/" + poolName + "/";
		this.brdBean.setDataPath(dataPath);

		model.getcurrentUserAndSysDate(this.brdBean);
		model.setDocumentType(this.brdBean);
		model.setEmployeeRole(this.brdBean);
		model.setProjectTypeDropDown(this.brdBean);
		model.setProjectClassificationDropDown(this.brdBean);
		model.setBusinessUnitDrop(this.brdBean);
		this.brdBean.setDisplayFlag("true");
		model.setConfiguredStakeHolder(this.brdBean);
		getNavigationPanel(2);
		this.brdBean.setEnableAll("Y");
		return "success";
	}

	/**Method Name: back()
	 * @purpose : Display List of Application
	 * @return String 
	 */
	public String back() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		String listType = model.checkNull(model.checkNull(this.brdBean
				.getListType()));
		if (("".equals(listType)) || ("inProcess".equals(listType)))
			input();
		else if ("closed".equals(listType))
			callClosedList();
		else if ("cancelled".equals(listType)) {
			callCancelBRDList();
		}
		return "input";
	}

	/**Method Name: reset()
	 * @purpose : To reset fields 
	 * @return String
	 */
	public String reset() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		this.brdBean.setBrdCode("");
		this.brdBean.setBrdNumber("");
		this.brdBean.setProjectName("");
		this.brdBean.setStartDate("");
		this.brdBean.setExpectedEndDate("");
		this.brdBean.setBusinessObjective("");
		this.brdBean.setProjectClosureCriteria("");
		this.brdBean.setConstraints("");
		this.brdBean.setAssuptions("");
		this.brdBean.setDocType("");
		this.brdBean.setUploadFileName("");
		this.brdBean.setSelectRole("");
		this.brdBean.setForwardEmpToken("");
		this.brdBean.setForwardEmpName("");
		this.brdBean.setFwdempCode("");
		this.brdBean.setAttachFile("");
		this.brdBean.setAllocatedBudget("");
		this.brdBean.setEmployeeName("");
		this.brdBean.setEmployeeId("");
		this.brdBean.setEmployeeToken("");
		this.brdBean.setProjectType("");
		this.brdBean.setProjectClassification("");
		this.brdBean.setPriority("");
		this.brdBean.setBusinessUnitID("");
		this.brdBean.setProjectAnnualFinancialBenefit("");
		this.brdBean.setRank("");
		this.brdBean.setApplicantsComments("");
		model.setDocumentType(this.brdBean);
		model.setEmployeeRole(this.brdBean);
		model.setState(brdBean);
		model.setProjectTypeDropDown(brdBean);
		model.setProjectClassificationDropDown(brdBean);
		this.brdBean.setDisplayFlag("true");
		model.setConfiguredStakeHolder(this.brdBean);
		model.setSatkeHolderEmp(brdBean);
		model.setBusinessUnitDrop(brdBean);
		getNavigationPanel(2);
		return "success";
	}

	/**Method Name: draft()
	 * @purpose : Used to saved Application into HRMS_D1_BUSINESS_REQUIREMENT
	 * @return String 
	 */
	public String draft() {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String[] attachedFiles = this.request
					.getParameterValues("uploadFileNameItr");
			String uploadedFileName = getUploadedFileName(attachedFiles);
			String userID = this.brdBean.getUserEmpId();
			this.brdBean.setStatus("D");
			if ("".equals(this.brdBean.getBrdCode())) {
				boolean result = model.addInfo(this.brdBean, userID,
						uploadedFileName);
				if (result) {
					addActionMessage(getMessage("save"));
					model.updateKeepInfoData(this.request, this.brdBean);
				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
				}
			} else {
				boolean result = model.modifyInfo(this.brdBean, userID,
						uploadedFileName);
				if (result) {
					addActionMessage(getMessage("update"));
					model.updateKeepInfoData(this.request, this.brdBean);
				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return "input";
	}

	/**Method Name: editApplication()
	 * @purpose : Update Record
	 * @return String 
	 */
	public String editApplication() {
		try {
			String classId = this.request.getParameter("brdCode");
			if ((classId == null) || (classId.equals(""))
					|| (classId.equals("null"))) {
				classId = this.brdBean.getBrdCode();
			}
			this.brdBean.setBrdCode(classId);
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			this.brdBean.setBrdSuperUserFlag("NOTBRDSuperUser");
			String poolName = String.valueOf(this.session
					.getAttribute("session_pool"));
			String dataPath = getText("data_path")
					+ "/BusinessRequirementDocument/" + poolName + "/";
			this.brdBean.setDataPath(dataPath);
			this.brdBean.setDisplayFlag("true");
			model.editApplication(this.brdBean, classId);
			model.setDocumentType(this.brdBean);
			model.setEmployeeRole(this.brdBean);
			model.setProjectTypeDropDown(this.brdBean);
			model.setProjectClassificationDropDown(this.brdBean);
			model.showUploadedDocuments(this.brdBean, classId);
			model.setBusinessUnitDrop(this.brdBean);
			getNavigationPanel(2);
			this.brdBean.setEnableAll("Y");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**Method Name: editSendBackApplication()
	 * @purpose : Update Send Back Application
	 * @return String 
	 */
	public String editSendBackApplication() {
		try {
			String classId = this.request.getParameter("brdCode");
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			this.brdBean.setRoleFlag("true");
			this.brdBean.setStageFlag("true");
			this.brdBean.setDocFlag("true");
			String poolName = String.valueOf(this.session
					.getAttribute("session_pool"));
			String dataPath = getText("data_path")
					+ "/BusinessRequirementDocument/" + poolName + "/";
			this.brdBean.setDataPath(dataPath);
			this.brdBean.setDisplayFlag("true");
			model.setDetails(this.brdBean, classId);
			model.initiatorData(this.brdBean, classId);
			model.viewData(this.brdBean, classId);
			getNavigationPanel(7);
			this.brdBean.setEnableAll("Y");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	/**Method Name: viewApplication()
	 * @purpose : Used to view Application 
	 * @return String
	 */
	public String viewApplication() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		this.brdBean.setBusinessFlag("false");
		this.brdBean.setClosureFlag("false");
		this.brdBean.setConstraintsFlag("false");
		this.brdBean.setAssumptionFlag("false");
		this.brdBean.setApplicantCommnetsFlag("false");
		this.brdBean.setRemoveButtonFlag("true");
		this.brdBean.setStatus("F");
		//this.brdBean.setBrdSuperUserFlag("BRDSuperUser");
		String poolName = String.valueOf(this.session
				.getAttribute("session_pool"));
		String dataPath = getText("data_path")
				+ "/BusinessRequirementDocument/" + poolName + "/";
		this.brdBean.setDataPath(dataPath);
		this.brdBean.setRoleFlag("true");
		this.brdBean.setStageFlag("true");
		this.brdBean.setDocFlag("true");
		this.brdBean.setAttachFile("");
		String code = this.request.getParameter("brdCode");
		if ((code == null) || (code.equals("")) || (code.equals("null"))) {
			code = this.brdBean.getBrdCode();
		}
		this.brdBean.setBrdCode(code);
		model.setDetails(this.brdBean, code);
		model.initiatorData(this.brdBean, code);
		model.viewData(this.brdBean, code);
		currentActivityOwnerDetails(this.brdBean, code);
		getNavigationPanel(6);
		this.brdBean.setEnableAll("Y");
		return "view";
	}

	/**Method Name: deleteApplication()
	 * @purpose :Used to delete record from table HRMS_D1_BUSINESS_REQUIREMENT
	 * @return String
	 */
	public String deleteApplication() {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			boolean result = model.delete(this.brdBean);
			if (result) {
				addActionMessage("Record Deleted successfully.");
			}
			model.terminate();
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	/**Method Name: forward()
	 * @purpose : USed to forward record towards selected Employee 
	 * @return String
	 * @throws Exception
	 */
	public String forward() throws Exception {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String userID = this.brdBean.getUserEmpId();
			String fwdCode = this.brdBean.getFwdempCode();
			String[] keepInformEmpCode = this.request
					.getParameterValues("empid");
			String applicationId = this.brdBean.getBrdCode();
			String[] attachedFiles = this.request
					.getParameterValues("uploadFileNameItr");
			String uploadedFileName = getUploadedFileName(attachedFiles);
			boolean result = model.forward(this.brdBean, userID,
					uploadedFileName);
			if (result) {
				String brdCode = this.brdBean.getBrdNumber();
				if (brdCode != "") {
					addActionMessage("Application forwarded successfully.\n BRD Ticket No : "
							+ brdCode);
				} else {
					String query = "SELECT BRD_TICKET_NO FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE="
							+ applicationId;
					Object[][] brdData = model.getSqlModel().getSingleResult(
							query);
					String brdNumber = String.valueOf(brdData[0][0]);
					addActionMessage("Application forwarded successfully.\n BRD Ticket No : "
							+ brdNumber);
				}
			} else {
				addActionMessage("Error occured forwarding application.");
			}
			model.updateKeepInfoData(this.request, this.brdBean);

			if (!fwdCode.equals(userID)) {
				String brdCodeNew = this.brdBean.getBrdNumber();
				String query = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE  BRD_TICKET_NO='"
						+ brdCodeNew + "' ";
				Object[][] brdCodeData = model.getSqlModel().getSingleResult(
						query);
				String autoBrdId = String.valueOf(brdCodeData[0][0]);

				sendMailToEmployee(this.brdBean, autoBrdId, userID, fwdCode,
						keepInformEmpCode);
			} else {
				String brdCodeNew = this.brdBean.getBrdNumber();
				String query = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE  BRD_TICKET_NO='"
						+ brdCodeNew + "' ";
				Object[][] brdCodeData = model.getSqlModel().getSingleResult(
						query);
				String autoBrdId = String.valueOf(brdCodeData[0][0]);

				sendMailToEmployee(this.brdBean, autoBrdId, userID, fwdCode,
						keepInformEmpCode);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return "input";
	}

	/**Method Name: closeApplication()
	 * @purpose : To Close Application after click on Close button on GUI 
	 * @return String 
	 */
	public String closeApplication() {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String applicationId = this.brdBean.getBrdCode();
			String userId = this.brdBean.getUserEmpId();
			String applStatus = this.brdBean.getApplnStatus();
			String fwdCode = this.brdBean.getFwdempCode();
			String[] attachedFiles = this.request
					.getParameterValues("forwardApproverUploadFileNameItr");
			String uploadedFileName = getUploadedFileName(attachedFiles);
			model.updateCloseAppData(this.brdBean, applicationId, userId);
			model.closed(this.brdBean, applicationId, applStatus, userId,
					uploadedFileName);
			model.terminate();
			addActionMessage("Application Closed successfully.");
			String[] stakeHolderEmpCode = null;
			stakeHolderEmpCode = this.request.getParameterValues("empid");
			if (stakeHolderEmpCode == null) {
				stakeHolderEmpCode = this.request
						.getParameterValues("empIttId");
			}
			String initiatoarquery = "SELECT PROJ_COMPLETED_BY FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE ="
					+ applicationId;
			Object[][] completedObj = model.getSqlModel().getSingleResult(
					initiatoarquery);
			String initiatoarCode = String.valueOf(completedObj[0][0]);

			applicationClosedMailToEmployee(applicationId, userId,
					initiatoarCode, stakeHolderEmpCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		if (this.brdBean.getBrdSuperUserFlag().equals("BRDSuperUser")) {
			return "backToBrdSuperUser";
		} else {
			return "input";
		}
	}

	/**Method Name: sendBackApplication()
	 * @purpose :Send Back Application to initially added Employee
	 * @return String 
	 */
	public String sendBackApplication() {
		try {
			String applicationId = this.brdBean.getBrdCode();
			String userId = this.brdBean.getUserEmpId();
			String fwdCode = this.brdBean.getFwdempCode().trim();
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String status = this.request.getParameter("applnStatus");
			String projApprCode = this.brdBean.getFwdempCode();
			String query = "SELECT PROJ_COMPLETED_BY FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE ="
					+ applicationId;
			Object[][] codeData = model.getSqlModel().getSingleResult(query);
			String completedCode = String.valueOf(codeData[0][0]);
			model.sendBack(applicationId, "B", this.brdBean, userId,
					completedCode);
			model.terminate();
			addActionMessage("Application Send Back successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	/**Method Name: forwardApplication()
	 * @purpose : Forward Application by BRD Super User to selected Employee
	 * @return String
	 * @throws Exception
	 */
	public String forwardApplication() throws Exception {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String userID = this.brdBean.getUserEmpId();
			String applicationId = this.brdBean.getBrdCode();
			String fwdCode = this.brdBean.getFwdempCode().trim();
			String[] keepInformEmpCode = this.request
					.getParameterValues("empIttId");
			String stackEmpID = getStakeHolderName(keepInformEmpCode);
			String[] attachedFiles = this.request
					.getParameterValues("forwardApproverUploadFileNameItr");
			String uploadedFileName = getUploadedFileName(attachedFiles);
			boolean result = model.saveInfo(this.brdBean, userID,
					applicationId, uploadedFileName);
			String empId = this.brdBean.getFwdempCode();
			model.updateStatus(this.brdBean, empId, applicationId, stackEmpID);
			model.updateKeepInfoDataApprover(this.request, this.brdBean);
			String[] stakeHolderEmpCode = this.request
					.getParameterValues("empIttId");

			String initiatorQuery = "SELECT PROJ_COMPLETED_BY FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE ="
					+ applicationId;
			Object[][] data = model.getSqlModel().getSingleResult(
					initiatorQuery);
			String initiatorCode = String.valueOf(data[0][0]);
			if (result) {
				addActionMessage("Application Forwarded Successfully ");
				sendMailToForwardedEmployee(applicationId, userID, fwdCode,
						stakeHolderEmpCode);
				sendMailToInitiator(this.brdBean, applicationId, userID,
						initiatorCode);
			} else {
				addActionMessage("Error occured sending application.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.brdBean.getBrdSuperUserFlag().equals("BRDSuperUser")) {
			return "backToBrdSuperUser";
		}
		input();
		return "input";
	}

	/**Method Name: updateApplication()
	 * @purpose : Update exisiting application
	 * @return String
	 */
	public String updateApplication() {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String applicationId = this.brdBean.getBrdCode();
			String fwdCode = this.brdBean.getFwdempCode().trim();
			model.updateReqStatus(this.brdBean, applicationId, fwdCode);
			String[] attachedFiles = this.request
					.getParameterValues("forwardApproverUploadFileNameItr");
			String uploadedFileName = getUploadedFileName(attachedFiles);
			model.updatePath(this.brdBean, applicationId, uploadedFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return "input";
	}

	/**Method Name: f9stakeHolderEmployee()
	 * @purpose : Display List of Employees
	 * @return String 
	 */
	public String f9stakeHolderEmployee() {
		String[] eId = this.request.getParameterValues("empid");
		String str = "0";
		if (eId != null) {
			for (int i = 0; i < eId.length; i++) {
				str = str + "," + eId[i];
			}
		}
		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID \tFROM HRMS_EMP_OFFC ";
		query = query + " WHERE EMP_STATUS='S' ";
		if ((this.brdBean.getUserProfileDivision() != null)
				&& (this.brdBean.getUserProfileDivision().length() > 0))
			query = query + "AND EMP_DIV IN ("
					+ this.brdBean.getUserProfileDivision() + ")";
		query = query + " AND EMP_ID NOT IN(" + str + ") ";
		query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "employeeToken", "employeeName", "employeeId" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				"false", "");
		return "f9page";
	}

	/**Method Name: f9stakeHolder()
	 * @purpose : Display List of Employees
	 * @return String 
	 */
	public String f9stakeHolder() {
		String[] eId = this.request.getParameterValues("empIttId");
		String str = "0";
		if (eId != null) {
			for (int i = 0; i < eId.length; i++) {
				str = str + "," + eId[i];
			}
		}
		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID \tFROM HRMS_EMP_OFFC ";
		query = query + " WHERE EMP_STATUS='S' ";
		if ((this.brdBean.getUserProfileDivision() != null)
				&& (this.brdBean.getUserProfileDivision().length() > 0))
			query = query + "AND EMP_DIV IN ("
					+ this.brdBean.getUserProfileDivision() + ")";
		query = query + " AND EMP_ID NOT IN(" + str + ") ";
		query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "empToken", "empName", "empCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				"false", "");
		return "f9page";
	}

	/**Method Name: f9forwardToEmp()
	 * @purpose : Display List of Employees
	 * @return String
	 */
	public String f9forwardToEmp() {
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , \tEMP_ID  FROM HRMS_EMP_OFFC WHERE  EMP_STATUS='S'  ";
		if ((this.brdBean.getUserProfileDivision() != null)
				&& (this.brdBean.getUserProfileDivision().length() > 0)) {
			query = query + " AND HRMS_EMP_OFFC.EMP_DIV IN ("
					+ this.brdBean.getUserProfileDivision() + " )";
		}
		query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";
		String[] headers = { "Employee Id", getMessage("employee.name") };
		String[] headerWidth = { "30", "70" };
		String[] fieldNames = { "forwardEmpToken", "forwardEmpName",
				"fwdempCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				"false", "");
		return "f9page";
	}

	/**Method Name: f9employee()
	 * @purpose : Display List of Employees
	 * @return String
	 */
	public String f9employee() {
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , \tEMP_ID  FROM HRMS_EMP_OFFC WHERE  EMP_STATUS='S' ";
		if ((this.brdBean.getUserProfileDivision() != null)
				&& (this.brdBean.getUserProfileDivision().length() > 0)) {
			query = query + " AND   HRMS_EMP_OFFC.EMP_DIV IN ("
					+ this.brdBean.getUserProfileDivision() + " )";
		}
		query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";
		String[] headers = { "Employee Id", getMessage("employee.name") };
		String[] headerWidth = { "30", "70" };
		String[] fieldNames = { "forwardEmpToken", "forwardEmpName",
				"fwdempCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				"false", "");
		return "f9page";
	}

	/**Method Name: addStakeHolderEmpList 
	 * @purpose : Display employee List in StakeHolder Iterator
	 * @return String
	 */
	public String addStakeHolderEmpList() {
		try {
			this.brdBean.setDisplayFlag("true");
			String[] serialNo = this.request
					.getParameterValues("serialNoStake");
			String[] empCode = this.request.getParameterValues("empid");
			String[] empName = this.request
					.getParameterValues("stakeholderEmpNameItt");
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, this.brdBean);
			model.setKeepInformed(serialNo, empCode, empName, this.brdBean);
			model.setDocumentType(this.brdBean);
			model.setEmployeeRole(this.brdBean);
			model.setProjectTypeDropDown(this.brdBean);
			model.setProjectClassificationDropDown(this.brdBean);
			model.setBusinessUnitDrop(this.brdBean);
			this.brdBean.setEmployeeName("");
			this.brdBean.setEmployeeId("");
			this.brdBean.setEmployeeToken("");
			model.setUploadedDocuments(this.brdBean, this.request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("applicantEditableMode".equals(this.brdBean
				.getApplicantEditableFlag()))
			getNavigationPanel(9);
		else {
			getNavigationPanel(2);
		}
		return "success";
	}

	/**Method Name:  addStakeHolderEmployees()
	 * @purpose : Add employee in StakeHolder iterator 
	 * @return String
	 */
	public String addStakeHolderEmployees() {
		try {
			this.brdBean.setStakeHolderFlag("true");
			this.brdBean.setRoleFlag("true");
			this.brdBean.setStageFlag("true");
			this.brdBean.setDocFlag("true");
			String[] serialNum = this.request.getParameterValues("serialNum");
			String[] empIttId = this.request.getParameterValues("empIttId");
			String[] stakeholderAppEmpNameItt = this.request
					.getParameterValues("stakeholderAppEmpNameItt");
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);

			String code = this.brdBean.getBrdCode();
			model.setDetails(this.brdBean, code);
			model.initiatorData(this.brdBean, code);
			model.viewData(this.brdBean, code);
			currentActivityOwnerDetails(brdBean, code);
			model.displayApproverIteratorValueForStake(serialNum, empIttId,
					stakeholderAppEmpNameItt, this.brdBean);
			model.setStakeHolders(serialNum, empIttId,
					stakeholderAppEmpNameItt, this.brdBean);
			model.setDocumentType(this.brdBean);
			model.setEmployeeRole(this.brdBean);
			model.setProjectTypeDropDown(this.brdBean);
			model.setProjectClassificationDropDown(this.brdBean);
			model.setBusinessUnitDrop(this.brdBean);
			model.setState(this.brdBean);
			this.brdBean.setEmpName("");
			this.brdBean.setEmpCode("");
			this.brdBean.setEmpToken("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.brdBean.getBrdSuperUserFlag().equals("BRDSuperUser"))
			getNavigationPanel(10);
		else {
			getNavigationPanel(6);
		}
		this.brdBean.setEnableAll("Y");
		return "view";
	}

	/**Method Name: removeStakeHolders()
	 * @purpose : Used to remove employee from StakeHolder list after click on remove icon 
	 * @return String
	 */
	public String removeStakeHolders() {
		try {
			String[] serialNo = this.request
					.getParameterValues("serialNoStake");
			String[] empCode = this.request.getParameterValues("empid");
			String[] empName = this.request
					.getParameterValues("stakeholderEmpNameItt");
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			this.brdBean.setDisplayFlag("true");
			model.removeKeepInformedData(serialNo, empCode, empName,
					this.brdBean);
			model.setDocumentType(this.brdBean);
			model.setEmployeeRole(this.brdBean);
			model.setState(brdBean);
			model.setProjectTypeDropDown(brdBean);
			model.setProjectClassificationDropDown(brdBean);
			model.setBusinessUnitDrop(this.brdBean);
			this.brdBean.setEmployeeName("");
			this.brdBean.setEmployeeId("");
			this.brdBean.setEmployeeToken("");
			model.setUploadedDocuments(this.brdBean, this.request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("applicantEditableMode".equals(this.brdBean
				.getApplicantEditableFlag()))
			getNavigationPanel(9);
		else {
			getNavigationPanel(2);
		}
		return "success";
	}

	/**Method Name: removeStakeHoldersApprover()
	 * @purpose : Used to remove employee from StakeHolder list 
	 * 				after click on remove icon after application is forwarded 
	 * @return String
	 */
	public String removeStakeHoldersApprover() {
		try {
			String[] serialNum = this.request.getParameterValues("serialNum");
			String[] empIttId = this.request.getParameterValues("empIttId");
			String[] stakeholderAppEmpNameItt = this.request
					.getParameterValues("stakeholderAppEmpNameItt");
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String code = this.brdBean.getBrdCode();
			model.setDetails(this.brdBean, code);
			model.initiatorData(this.brdBean, code);
			model.viewData(this.brdBean, code);
			currentActivityOwnerDetails(brdBean, code);
			this.brdBean.setStakeHolderFlag("true");
			this.brdBean.setRoleFlag("true");
			this.brdBean.setStageFlag("true");
			this.brdBean.setDocFlag("true");
			model.removeStakeHolders(serialNum, empIttId,
					stakeholderAppEmpNameItt, this.brdBean);
			model.setDocumentType(this.brdBean);
			model.setEmployeeRole(this.brdBean);
			model.setProjectTypeDropDown(this.brdBean);
			model.setProjectClassificationDropDown(this.brdBean);
			model.setBusinessUnitDrop(this.brdBean);
			model.setState(this.brdBean);
			this.brdBean.setEmployeeName("");
			this.brdBean.setEmployeeId("");
			this.brdBean.setEmployeeToken("");
			model.setUploadedDocuments(this.brdBean, this.request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.brdBean.getBrdSuperUserFlag().equals("BRDSuperUser"))
			getNavigationPanel(10);
		else {
			getNavigationPanel(6);
		}
		this.brdBean.setEnableAll("Y");
		return "view";
	}

	/**Method Name: retriveDetails()
	 * @purpose : Display details of application after click on View button 
	 * @return String
	 */
	public String retriveDetails() {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String poolName = String.valueOf(this.session
					.getAttribute("session_pool"));
			String dataPath = getText("data_path")
					+ "/BusinessRequirementDocument/" + poolName + "/";
			this.brdBean.setDataPath(dataPath);
			this.brdBean.setBrdSuperUserFlag("NOTBRDSuperUser");
			String applCode = this.request.getParameter("applCode");
			if ((applCode == null) || (applCode.equals(""))
					|| (applCode.equals("null"))) {
				applCode = this.brdBean.getBrdCode();
			}
			this.brdBean.setBrdCode(applCode);
			this.brdBean.setDisplayFlag("false");
			model.view(this.brdBean, applCode);
			currentActivityOwnerDetails(this.brdBean, applCode);

			model.setDetails(this.brdBean, applCode);
			model.initiatorData(this.brdBean, applCode);
			Object[][] checkForApplicant = model.isCurrentUserIsApplicant(
					this.brdBean, applCode);
			model.terminate();
			if (String.valueOf(checkForApplicant[0][1]).equals("F")) {
				getNavigationPanel(9);
				this.brdBean.setProjectCommentsFlag(true);
				this.brdBean.setApplicantEditableFlag("applicantEditableMode");
				this.brdBean.setBusinessFlag("true");
				this.brdBean.setClosureFlag("true");
				this.brdBean.setConstraintsFlag("true");
				this.brdBean.setAssumptionFlag("true");
				this.brdBean.setEnableAll("Y");
			} else {
				getNavigationPanel(5);
				this.brdBean.setEnableAll("N");
			}
			model.showUploadedDocuments(this.brdBean, applCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**Method Name: viewSuperUserData()
	 * @purpose : Display details of application
	 * @return String
	 */
	public String viewSuperUserData() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		String poolName = String.valueOf(this.session
				.getAttribute("session_pool"));
		String dataPath = getText("data_path")
				+ "/BusinessRequirementDocument/" + poolName + "/";
		this.brdBean.setDataPath(dataPath);
		String id = this.request.getParameter("applCode");
		String applCode = this.request.getParameter("applCode");
		if ((applCode != null) && (applCode.length() > 0)) {
			id = applCode;
		}
		String statusQuery = "SELECT PROJ_STATUS FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE ="
				+ id;
		Object[][] statusData = model.getSqlModel()
				.getSingleResult(statusQuery);
		if ((applCode != null) && (applCode.length() > 0)) {
			if (statusData[0][0].equals("D")) {
				model.editApplication(this.brdBean, id);
				model.setDocumentType(this.brdBean);
				model.setEmployeeRole(this.brdBean);
				this.brdBean.setEnableAll("N");
			}
			if ((statusData[0][0].equals("F"))
					|| (statusData[0][0].equals("C"))) {
				this.brdBean.setRemoveButtonFlag("false");
				this.brdBean.setCommentFlag("false");
				this.brdBean.setRoleFlag("false");
				this.brdBean.setStageFlag("flase");
				this.brdBean.setDocFlag("false");
				model.setDetails(this.brdBean, id);
				model.initiatorData(this.brdBean, id);
				model.viewData(this.brdBean, id);
				getNavigationPanel(8);
				this.brdBean.setEnableAll("N");
				return "view";
			}
			if ("B".equals(statusData[0][0])) {
				this.brdBean.setRemoveButtonFlag("false");
				this.brdBean.setCommentFlag("true");
				this.brdBean.setRoleFlag("false");
				this.brdBean.setStageFlag("flase");
				this.brdBean.setDocFlag("false");
				model.setDetails(this.brdBean, id);
				model.initiatorData(this.brdBean, id);
				model.viewData(this.brdBean, id);
				this.brdBean.setEnableAll("N");
				return "view";
			}
		}
		return "success";
	}

	/**Method Name: sendMailToApplicant()
	 * @purpose : Send Email to applicant after record is forwarded
	 * @param brdBean
	 * @param applicationId
	 * @param fwdCode
	 */
	private void sendMailToApplicant(BusinessRequirementDocument brdBean,
			String applicationId, String fwdCode) {
		EmailTemplateBody templateApplicant = new EmailTemplateBody();
		templateApplicant.initiate(this.context, this.session);
		templateApplicant
				.setEmailTemplate("BUSINESS REQUIREMNENT MAIL FROM FORWARDED EMPLOYEE TO APPLICANT");
		templateApplicant.getTemplateQueries();

		EmailTemplateQuery templateQueryApp1 = templateApplicant
				.getTemplateQuery(1);

		EmailTemplateQuery templateQueryApp2 = templateApplicant
				.getTemplateQuery(2);
		templateQueryApp2.setParameter(1, brdBean.getUserEmpId());

		EmailTemplateQuery templateQueryApp3 = templateApplicant
				.getTemplateQuery(3);
		templateQueryApp3.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp4 = templateApplicant
				.getTemplateQuery(4);
		templateQueryApp4.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp5 = templateApplicant
				.getTemplateQuery(5);
		templateQueryApp5.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp6 = templateApplicant
				.getTemplateQuery(6);
		templateQueryApp6.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp7 = templateApplicant
				.getTemplateQuery(7);
		templateQueryApp7.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp8 = templateApplicant
				.getTemplateQuery(8);
		templateQueryApp8.setParameter(1, applicationId);

		templateApplicant.configMailAlert();
		templateApplicant.sendApplicationMail();
		templateApplicant.clearParameters();
		templateApplicant.terminate();
	}

	/**Method Name: sendMailToEmployee
	 * @purpose : Send Email to forwarded employee after record is forwarded
	 * @param brdBean
	 * @param applnID
	 * @param userID
	 * @param fwdCode
	 * @param keepInfromEmpCode
	 */
	public void sendMailToEmployee(BusinessRequirementDocument brdBean,
			String applnID, String userID, String fwdCode,
			String[] keepInfromEmpCode) {
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(this.context, this.session);
			template
					.setEmailTemplate("BUSINESS REQUIREMNENT MAIL FROM APPLICANT TO FORWARDED EMPLOYEE");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, userID);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, fwdCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applnID);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, applnID);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applnID);

			template.configMailAlert();

			if (keepInfromEmpCode != null) {
				template.sendApplicationMailToKeepInfo(keepInfromEmpCode);
			}

			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: applicationClosedMailToEmployee()
	 * @purpose : Send Email to applicant after record is closed
	 * @param applicationId
	 * @param userID
	 * @param initiatoarCode
	 * @param stakeHolderEmpCode
	 */
	public void applicationClosedMailToEmployee(String applicationId,
			String userID, String initiatoarCode, String[] stakeHolderEmpCode) {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String empQuery = "SELECT DISTINCT HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY "
					+ " FROM HRMS_D1_BUSINESS_PATH WHERE BUSINESS_CODE ="
					+ applicationId
					+ " AND HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY != "
					+ userID;
			Object[][] data = model.getSqlModel().getSingleResult(empQuery);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(this.context, this.session);
			template
					.setEmailTemplate("BUSINESS REQUIREMNENT APPROVER MAIL FROM APPROVER TO EMPLOYEE");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, userID);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, initiatoarCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, userID);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery10 = template.getTemplateQuery(10);
			templateQuery10.setParameter(1, applicationId);

			String[] empData = (String[]) null;

			if ((data != null) && (data.length > 0)) {
				empData = new String[data.length];
				for (int i = 0; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
				}
			}
			template.configMailAlert();

			/** Send Email to stakeholders*/
			if (stakeHolderEmpCode != null) {
				template.sendApplicationMailToKeepInfo(stakeHolderEmpCode);
			}

			if ((empData != null) && (empData.length > 0)) {
				template.sendApplicationMailToKeepInfo(empData);
			}
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: sendBackMailToEmployee()
	 * @purpose : Send Email to applicant after send back by approver
	 * @param applicationId
	 * @param userId
	 * @param initiatoarCode
	 * @param fwdCode
	 */
	public void sendBackMailToEmployee(String applicationId, String userId,
			String initiatoarCode, String fwdCode) {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String empQuery = "SELECT DISTINCT HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY"
					+ " FROM HRMS_D1_BUSINESS_PATH WHERE  BUSINESS_CODE ="
					+ applicationId
					+ " HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY != "
					+ userId;
			Object[][] data = model.getSqlModel().getSingleResult(empQuery);

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(this.context, this.session);
			template
					.setEmailTemplate("BUSINESS REQUIREMNENT APPROVER MAIL FROM APPROVER TO FORWARDED EMPLOYEE AND APPLICANT");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, userId);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, initiatoarCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, userId);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, applicationId);

			String[] empData = (String[]) null;
			if ((data != null) && (data.length > 0)) {
				empData = new String[data.length];
				for (int i = 0; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
				}
			}
			template.configMailAlert();
			if ((empData != null) && (empData.length > 0)) {
				template.sendApplicationMailToKeepInfo(empData);
			}
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: sendMailToForwardedEmployee()
	 * @purpose : Send email to forwarded employee from approver 
	 * @param applicationId
	 * @param userID
	 * @param fwdCode
	 * @param stakeHolderEmpCode
	 */
	public void sendMailToForwardedEmployee(String applicationId,
			String userID, String fwdCode, String[] stakeHolderEmpCode) {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String empQuery = "SELECT DISTINCT BUSINESS_PROJ_CLOSE_BY FROM HRMS_D1_BUSINESS_PATH WHERE  BUSINESS_CODE ="
					+ applicationId
					+ " AND BUSINESS_PROJ_CLOSE_BY != "
					+ userID;
			Object[][] data = model.getSqlModel().getSingleResult(empQuery);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(this.context, this.session);
			template
					.setEmailTemplate("BUSINESS REQUIREMNENT APPROVER MAIL FROM APPROVER TO FORWARDED EMPLOYEE");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, userID);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, fwdCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, userID);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery10 = template.getTemplateQuery(10);
			templateQuery10.setParameter(1, applicationId);

			List finalEmpData = new ArrayList();
			if ((data != null) && (data.length > 0)) {
				for (int i = 0; i < data.length; i++) {
					if (!fwdCode.equals(String.valueOf(data[i][0]))) {
						finalEmpData.add(String.valueOf(data[i][0]));
					}
				}

			}

			template.configMailAlert();

			if (stakeHolderEmpCode != null) {
				template.sendApplicationMailToKeepInfo(stakeHolderEmpCode);
			}

			String[] finalDataToSent = (String[]) finalEmpData
					.toArray(new String[0]);
			if ((finalDataToSent != null) && (finalDataToSent.length > 0)) {
				template.sendApplicationMailToKeepInfo(finalDataToSent);
			}
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: sendMailToInitiator
	 * @purpose : Send Email to initiator(Current Activity Owner) from approver
	 * @param brdBean
	 * @param applicationId
	 * @param userID
	 * @param initiatorCode
	 */
	private void sendMailToInitiator(BusinessRequirementDocument brdBean,
			String applicationId, String userID, String initiatorCode) {
		EmailTemplateBody templateApplicant = new EmailTemplateBody();
		templateApplicant.initiate(this.context, this.session);
		templateApplicant
				.setEmailTemplate("BUSINESS REQUIREMNENT MAIL TO INITIATOR EMPLOYEE");
		templateApplicant.getTemplateQueries();

		EmailTemplateQuery templateQuery1 = templateApplicant
				.getTemplateQuery(1);
		templateQuery1.setParameter(1, userID);

		EmailTemplateQuery templateQueryApp2 = templateApplicant
				.getTemplateQuery(2);
		templateQueryApp2.setParameter(1, initiatorCode);

		EmailTemplateQuery templateQueryApp3 = templateApplicant
				.getTemplateQuery(3);
		templateQueryApp3.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp4 = templateApplicant
				.getTemplateQuery(4);
		templateQueryApp4.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp5 = templateApplicant
				.getTemplateQuery(5);
		templateQueryApp5.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp6 = templateApplicant
				.getTemplateQuery(6);
		templateQueryApp6.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp7 = templateApplicant
				.getTemplateQuery(7);
		templateQueryApp7.setParameter(1, userID);

		EmailTemplateQuery templateQueryApp8 = templateApplicant
				.getTemplateQuery(8);
		templateQueryApp8.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp9 = templateApplicant
				.getTemplateQuery(9);
		templateQueryApp9.setParameter(1, applicationId);

		EmailTemplateQuery templateQueryApp10 = templateApplicant
				.getTemplateQuery(10);
		templateQueryApp10.setParameter(1, applicationId);

		templateApplicant.configMailAlert();
		templateApplicant.sendApplicationMail();
		templateApplicant.clearParameters();
		templateApplicant.terminate();
	}

	/**Method Name: saveApplicantRelatedData()
	 * @purpose : Update Application after click on Update Activity Status button 
	 * @return String
	 */
	public String saveApplicantRelatedData() {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String userID = this.brdBean.getUserEmpId();
			String fwdCode = this.brdBean.getFwdempCode();
			String[] keepInformEmpCode = this.request
					.getParameterValues("empid");
			String applicationId = this.brdBean.getBrdCode();
			String[] attachedFiles = this.request
					.getParameterValues("uploadFileNameItr");
			String uploadedFileName = getUploadedFileName(attachedFiles);
			boolean result = model.updateApplicantRelatedData(this.brdBean,
					userID, uploadedFileName);
			model.updateKeepInfoData(this.request, this.brdBean);
			if (result) {
				addActionMessage(getMessage("save"));
				sendUpdatedMailToEmployee(applicationId, userID, fwdCode,
						keepInformEmpCode);
			} else {
				addActionMessage("Unable to save reocrds.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return "input";
	}

	/**Method Name: sendUpdatedMailToEmployee
	 * @purpose : Send Email for update Application after click on Update Activity Status button
	 * @param applnID
	 * @param userID
	 * @param fwdCode
	 * @param keepInfromEmpCode
	 */
	public void sendUpdatedMailToEmployee(String applnID, String userID,
			String fwdCode, String[] keepInfromEmpCode) {
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(this.context, this.session);
			template
					.setEmailTemplate("BUSINESS REQUIREMNENT UPDATED DATA MAIL FROM APPLICANT TO FORWARDED EMPLOYEES");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, userID);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, fwdCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applnID);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, applnID);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applnID);

			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, userID);
			template.configMailAlert();

			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String query = " SELECT DISTINCT HRMS_D1_BUSINESS_PATH.BUSINESS_APPROVER_CODE"
					+ " FROM HRMS_D1_BUSINESS_PATH "
					+ " WHERE HRMS_D1_BUSINESS_PATH.BUSINESS_CODE = "
					+ applnID
					+ " AND HRMS_D1_BUSINESS_PATH.BUSINESS_APPROVER_CODE NOT IN ( "
					+ fwdCode + ")";
			Object[][] processedByApproverObj = model.getSqlModel()
					.getSingleResult(query);
			if ((processedByApproverObj != null)
					&& (processedByApproverObj.length > 0)) {
				String finalProcessedApproverID = "";
				for (int i = 0; i < processedByApproverObj.length; i++) {
					finalProcessedApproverID = finalProcessedApproverID
							+ String.valueOf(processedByApproverObj[i][0])
							+ ",";
				}
				finalProcessedApproverID = finalProcessedApproverID.substring(
						0, finalProcessedApproverID.length() - 1);
				template
						.sendApplicationMailToKeepInfo(finalProcessedApproverID);
			}

			if (keepInfromEmpCode != null) {
				template.sendApplicationMailToKeepInfo(keepInfromEmpCode);
			}
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: getUploadedFileName()
	 * @purpose : Update record into Database with multiple attached file names
	 * @param attachedFiles
	 * @return String 
	 */
	public String getUploadedFileName(String[] attachedFiles) {
		String uploadedFileName = "";
		try {
			if ((attachedFiles != null) && (attachedFiles.length > 0)) {
				for (int i = 0; i < attachedFiles.length; i++) {
					uploadedFileName = uploadedFileName + attachedFiles[i]
							+ ",";
				}
				uploadedFileName = uploadedFileName.substring(0,
						uploadedFileName.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadedFileName;
	}

	/**Method Name: f9SearchRecord
	 * @purpose : Display record list after click on Search button on List page
	 * @return String
	 */
	public String f9SearchRecord() {
		String query = " SELECT DISTINCT HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO,"
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  "
				+ " DECODE(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STATUS, 'D','Draft', 'F','Forwarded', 'A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Cancel'),"
				+ " HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_BY, HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE "
				+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_BY)"
				+ " LEFT JOIN  HRMS_D1_BUSINESS_PATH ON( HRMS_D1_BUSINESS_PATH.BUSINESS_CODE = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE)"
				+ " WHERE 1 = 1 ";
		if (this.brdBean.getListType().equals("inProcess")) {
			query = query
					+ " AND HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STATUS IN('D','F') "
					+ " AND (HRMS_D1_BUSINESS_REQUIREMENT.PROJ_INITIATOR = "
					+ this.brdBean.getUserEmpId()
					+ " OR HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_BY= "
					+ this.brdBean.getUserEmpId()
					+ " OR HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY ="
					+ this.brdBean.getUserEmpId()
					+ " OR ','|| HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STAKE_HOLDER || ',' LIKE ('%,"
					+ this.brdBean.getUserEmpId() + ",%'))";
		}
		if ((this.brdBean.getListType().equals("closed"))
				|| (this.brdBean.getListType().equals("cancelled"))) {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String code = "0";
			String userId = this.brdBean.getUserEmpId();
			String pathQuery = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_PATH WHERE BUSINESS_PROJ_CLOSE_BY ="
					+ userId;
			Object[][] pathData = model.getSqlModel()
					.getSingleResult(pathQuery);
			if ((pathData != null) && (pathData.length > 0)) {
				for (int i = 0; i < pathData.length; i++) {
					code = code + "," + String.valueOf(pathData[i][0]);
				}
			}
			String initiatorQuery = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE PROJ_COMPLETED_BY ="
					+ userId;
			Object[][] initiatorObj = model.getSqlModel().getSingleResult(
					initiatorQuery);
			if ((initiatorObj != null) && (initiatorObj.length > 0)) {
				for (int i = 0; i < initiatorObj.length; i++) {
					code = code + "," + String.valueOf(initiatorObj[i][0]);
				}
			}
			model.terminate();
			String status = "";
			if (this.brdBean.getListType().equals("cancelled"))
				status = "Z";
			else {
				status = "C";
			}
			query = query + " AND HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STATUS = '"
					+ status
					+ "' AND HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE IN("
					+ code + ") ";
		}
		query = query + " ORDER BY BRD_TICKET_NO DESC ";

		String[] headers = { getMessage("brd.ticket.no"),
				getMessage("completedBy"), "Status" };
		String[] headerWidth = { "40", "40", "20" };
		String[] fieldNames = { "brdNumber", "completedBy", "status",
				"completedByID", "brdCode" };
		int[] columnIndex = { 0, 1, 2, 3, 4 };
		String submitFlag = "true";
		String submitToMethod = "BusinessRequirementDocument_fromSearchWindow.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				"true", submitToMethod);
		return "f9page";
	}

	/**Method Name: fromSearchWindow
	 * @purpose : Display record details after click on Search button on List page
	 * @return String
	 */
	public String fromSearchWindow() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		String applCode = this.request.getParameter("brdCode");
		this.brdBean.setBrdCode(applCode);
		Object[][] checkForApplicant = model.isCurrentUserIsApplicant(
				this.brdBean, applCode);
		model.terminate();
		if (this.brdBean.getListType().equals("inProcess")) {
			String status = this.request.getParameter("status");
			if ((status != null) && (status.equals("Draft"))) {
				editApplication();
				getNavigationPanel(2);
				this.brdBean.setEnableAll("Y");
				return "success";
			}
			if (String.valueOf(checkForApplicant[0][1]).equals("F")) {
				if (!(String.valueOf(checkForApplicant[0][2]).equals(brdBean
						.getUserEmpId()))) {
					retriveDetails();
					getNavigationPanel(9);
					this.brdBean
							.setApplicantEditableFlag("applicantEditableMode");
					this.brdBean.setBusinessFlag("true");
					this.brdBean.setClosureFlag("true");
					this.brdBean.setConstraintsFlag("true");
					this.brdBean.setAssumptionFlag("true");
					this.brdBean.setEnableAll("Y");
					return "success";
				} else {
					viewApplication();
					getNavigationPanel(6);
					this.brdBean.setEnableAll("Y");
					return "view";
				}
			}
		}
		retriveDetails();
		getNavigationPanel(5);
		this.brdBean.setEnableAll("N");
		return "success";
	}

	/**Method Name: viewApplicationFromBRDSuperUser()
	 * @purpose : Display record details after click on View button on BRD Super User Page
	 * @return String
	 */
	public String viewApplicationFromBRDSuperUser() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(this.context, this.session);
		String poolName = String.valueOf(this.session
				.getAttribute("session_pool"));
		String dataPath = getText("data_path")
				+ "/BusinessRequirementDocument/" + poolName + "/";
		this.brdBean.setDataPath(dataPath);
		String code = this.request.getParameter("applCode");
		this.brdBean.setBrdSuperUserFlag("BRDSuperUser");
		if ((code == null) || (code.equals("")) || (code.equals("null"))) {
			code = this.brdBean.getBrdCode();
		}
		this.brdBean.setBrdCode(code);
		model.setDetails(this.brdBean, code);
		model.initiatorData(this.brdBean, code);
		model.viewData(this.brdBean, code);
		currentActivityOwnerDetails(this.brdBean, code);
		String statusFromSuperUser = this.request
				.getParameter("statusFromSuperUser");
		if ((statusFromSuperUser != null)
				&& ((statusFromSuperUser.equals("Closed"))
						|| (statusFromSuperUser.equals("Draft")) || (statusFromSuperUser
						.equals("Cancel")))) {
			getNavigationPanel(5);
			this.brdBean.setCommentFlag("true");
			this.brdBean.setEnableAll("N");
		} else {
			if ((statusFromSuperUser != null)
					&& (statusFromSuperUser.equals("Forwarded"))) {
				this.brdBean.setForwardStatus(true);
			}
			this.brdBean.setBusinessFlag("false");
			this.brdBean.setClosureFlag("false");
			this.brdBean.setConstraintsFlag("false");
			this.brdBean.setAssumptionFlag("false");
			this.brdBean.setApplicantCommnetsFlag("false");
			this.brdBean.setRemoveButtonFlag("true");
			this.brdBean.setRoleFlag("true");
			this.brdBean.setStageFlag("true");
			this.brdBean.setDocFlag("true");
			this.brdBean.setAttachFile("");
			getNavigationPanel(11);
			this.brdBean.setEnableAll("Y");
		}
		return "view";
	}

	/**Method Name: currentActivityOwnerDetails()
	 * @purpose :
	 * @param brdBean
	 * @param applicationCode
	 */
	private void currentActivityOwnerDetails(
			BusinessRequirementDocument brdBean, String applicationCode) {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			String query = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID) "
					+ " WHERE HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE = "
					+ applicationCode;
			Object[][] dataObj = model.getSqlModel().getSingleResult(query);
			if ((dataObj != null) && (dataObj.length > 0)) {
				brdBean.setActivityOwnerPresentFlag(true);
				brdBean.setActivityOwnerName(model.checkNull(String
						.valueOf(dataObj[0][0])));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: 
	 * @purpose :
	 * @return
	 */
	public String cancelProject() {
		try {
			String userID = this.brdBean.getUserEmpId();
			String applicationId = this.brdBean.getBrdCode();
			String[] keepInformEmpCode = null;
			keepInformEmpCode = this.request.getParameterValues("empid");
			if (keepInformEmpCode == null) {
				keepInformEmpCode = this.request.getParameterValues("empIttId");
			}
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			boolean result = model.cancelProject(this.brdBean, userID,
					applicationId);
			if (result) {
				addActionMessage("Project cancel successfully.");
				sendCancellationMailToEmployee(applicationId, userID,
						keepInformEmpCode);
			} else {
				addActionMessage("Unable to cancel this project");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		if (this.brdBean.getBrdSuperUserFlag().equals("BRDSuperUser")) {
			return "backToBrdSuperUser";
		} else {
			return "input";
		}
	}

	/**Method Name: 
	 * @purpose :
	 * @param applicationId
	 * @param userID
	 * @param keepInfromEmpCode
	 */
	public void sendCancellationMailToEmployee(String applicationId,
			String userID, String[] keepInfromEmpCode) {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(context, session);
			String initiatorQuery = "SELECT PROJ_COMPLETED_BY FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE="
					+ applicationId;
			Object[][] initObj = model.getSqlModel().getSingleResult(
					initiatorQuery);
			if (initObj == null && initObj.length < 0) {
				initObj[0][0] = "0";
			}
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(this.context, this.session);
			template
					.setEmailTemplate("BUSINESS REQUIREMNENT CANCELLATION MAIL TO ALL EMPLOYEES");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, userID);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, String.valueOf(initObj[0][0]));

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, userID);

			template.configMailAlert();

			String query = " SELECT DISTINCT HRMS_D1_BUSINESS_PATH.BUSINESS_APPROVER_CODE"
					+ " FROM HRMS_D1_BUSINESS_PATH  WHERE HRMS_D1_BUSINESS_PATH.BUSINESS_CODE = "
					+ applicationId;
			Object[][] processedByApproverObj = model.getSqlModel()
					.getSingleResult(query);
			if ((processedByApproverObj != null)
					&& (processedByApproverObj.length > 0)) {
				String finalProcessedApproverID = "";
				for (int i = 0; i < processedByApproverObj.length; i++) {
					finalProcessedApproverID = finalProcessedApproverID
							+ String.valueOf(processedByApproverObj[i][0])
							+ ",";
				}
				finalProcessedApproverID = finalProcessedApproverID.substring(
						0, finalProcessedApproverID.length() - 1);
				template
						.sendApplicationMailToKeepInfo(finalProcessedApproverID);
			}
			if (keepInfromEmpCode != null) {

				template.sendApplicationMailToKeepInfo(keepInfromEmpCode);
			}

			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: 
	 * @purpose :
	 * @return
	 */
	public String saveCurrentActivityStatus() {
		try {
			String applicationId = this.brdBean.getBrdCode();
			String currentActivityStatus = this.brdBean
					.getCurrentActivityStatus();
			String forecastedCompletionDate = this.brdBean
					.getForecastedCompletionDate().trim();
			String comments = this.brdBean.getComments().trim();
			String currentState = this.brdBean.getCurrentState();
			String currentUser = this.brdBean.getUserEmpId();
			String documentType = this.brdBean.getDocumentAttached().trim();
			String[] attachedFiles = this.request
					.getParameterValues("forwardApproverUploadFileNameItr");
			String attachedDocumentName = getUploadedFileName(attachedFiles);
			String userID = this.brdBean.getUserEmpId();
			String fwdCode = this.brdBean.getFwdempCode();
			String[] keepInformEmpCode = this.request
					.getParameterValues("empIttId");
			String stakeHolderEmp = getStakeHolderName(keepInformEmpCode);

			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			boolean result = model.saveCurrentActivityStatus(applicationId,
					currentActivityStatus, forecastedCompletionDate, comments,
					currentState, currentUser, documentType,
					attachedDocumentName, stakeHolderEmp, this.brdBean);
			if (result) {
				addActionMessage("Current activity status updated successfully.");
				sendCurrentActivityMailToEmployee(applicationId, userID,
						fwdCode, keepInformEmpCode);
			} else {
				addActionMessage("Unable to update current activity status");
			}
			model.terminate();
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (brdBean.getBrdSuperUserFlag().equals("BRDSuperUser")) {
			return "backToBrdSuperUser";
		} else {
			return "input";
		}
	}

	/**Method Name : getStakeHolderName()
	 * @purpose : Used to set comma seprated Emplyoee's 
	 * @param keepInformEmpCode
	 * @return String
	 */
	public String getStakeHolderName(String[] keepInformEmpCode) {
		String stakeEmpName = "";
		try {
			if ((keepInformEmpCode != null) && (keepInformEmpCode.length > 0)) {
				for (int i = 0; i < keepInformEmpCode.length; i++) {
					stakeEmpName = stakeEmpName + keepInformEmpCode[i] + ",";
				}
				stakeEmpName = stakeEmpName.substring(0,
						stakeEmpName.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stakeEmpName;
	}

	/**Method Name: 
	 * @purpose :
	 * @param applnID
	 * @param userID
	 * @param fwdCode
	 * @param keepInfromEmpCode
	 */
	public void sendCurrentActivityMailToEmployee(String applnID,
			String userID, String fwdCode, String[] keepInfromEmpCode) {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(this.context, this.session);
			Object[][] completedByCode = model
					.getSqlModel()
					.getSingleResult(
							"SELECT PROJ_COMPLETED_BY FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE = "
									+ applnID);

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(this.context, this.session);
			template
					.setEmailTemplate("BUSINESS REQUIREMNENT CURRENT ACTIVITY STATUS MAIL TO EMPLOYEES");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, userID);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, String
					.valueOf(completedByCode[0][0]));

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, userID);
			templateQuery4.setParameter(2, applnID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applnID);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, applnID);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applnID);

			template.configMailAlert();

			Object[][] processedByApproverObj = model
					.getSqlModel()
					.getSingleResult(
							" SELECT DISTINCT HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY FROM HRMS_D1_BUSINESS_PATH  WHERE HRMS_D1_BUSINESS_PATH.BUSINESS_CODE = "
									+ applnID
									+ " AND HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY NOT IN ( "
									+ userID + ")");
			if ((processedByApproverObj != null)
					&& (processedByApproverObj.length > 0)) {
				String finalProcessedApproverID = "";
				for (int i = 0; i < processedByApproverObj.length; i++) {
					finalProcessedApproverID = finalProcessedApproverID
							+ String.valueOf(processedByApproverObj[i][0])
							+ ",";
				}
				finalProcessedApproverID = finalProcessedApproverID.substring(
						0, finalProcessedApproverID.length() - 1);
				template
						.sendApplicationMailToKeepInfo(finalProcessedApproverID);
			}

			if (keepInfromEmpCode != null) {
				template.sendApplicationMailToKeepInfo(keepInfromEmpCode);
			}

			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: f9BusinessUnit()
	 * @purpose : To Select Business Unit
	 * @return String 
	 *//*
	public String f9BusinessUnit() {
		String brdSuperUsr = request.getParameter("brdSuper");
		this.brdBean.setBrdSuperUserFlag(brdSuperUsr);
		String query = "SELECT BUSS_UNIT_NAME, HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
				+ " BUSS_UNIT_ID,BUSS_EMP_ID,EMP_TOKEN  "
				+ " FROM  HRMS_D1_BUSSINESS_UNIT "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_D1_BUSSINESS_UNIT.BUSS_EMP_ID)"
				+ " ORDER BY BUSS_UNIT_NAME ";
		String[] headers = { getMessage("businessUnitLbl"),
				getMessage("businessUnitHead") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "businessUnit", "forwardEmpName",
				"businessUnitID", "fwdempCode", "forwardEmpToken" };
		int[] columnIndex = { 0, 1, 2, 3, 4 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}*/

	/**Method Name: setEmployee()
	 * Purpose : To set Employee according to Business Unit Master.
	 * @return String 
	 */
	public String setEmployee() {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(context, session);
			String brdCode = this.brdBean.getBrdCode();
			model.setDocumentType(this.brdBean);
			model.setEmployeeRole(this.brdBean);
			model.setState(brdBean);
			model.setProjectTypeDropDown(brdBean);
			model.setProjectClassificationDropDown(brdBean);
			model.setBusinessUnitDrop(this.brdBean);
			//model.setSatkeHolderEmp(brdBean);	     
			if (!(brdCode.equals("") || brdCode == null
					|| brdCode.equals("null") || brdCode.equals(null))) {
				String applCode = brdBean.getBrdCode();
				Object[][] checkForApplicant = model.isCurrentUserIsApplicant(
						this.brdBean, applCode);
				model.terminate();
				if (String.valueOf(checkForApplicant[0][1]).equals("F")) {
					getNavigationPanel(9);
					this.brdBean.setProjectCommentsFlag(true);
					this.brdBean
							.setApplicantEditableFlag("applicantEditableMode");
					this.brdBean.setBusinessFlag("true");
					this.brdBean.setClosureFlag("true");
					this.brdBean.setConstraintsFlag("true");
					this.brdBean.setAssumptionFlag("true");
					this.brdBean.setEnableAll("Y");
				} else if (String.valueOf(checkForApplicant[0][1]).equals("D")) {
					this.brdBean.setDisplayFlag("true");
					getNavigationPanel(2);
				} else {
					getNavigationPanel(5);
					this.brdBean.setEnableAll("N");
				}
				model.savedStakeholder(brdBean);
			} else {
				this.brdBean.setDisplayFlag("true");
				model.setConfiguredStakeHolder(brdBean);
				this.brdBean.setSelectRole("7");
				getNavigationPanel(2);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (brdBean.getBrdSuperUserFlag().equals("BRDSuperUser")) {
			this.brdBean.setForwardStatus(true);
			return "view";
		} else {
			return "success";
		}
	}

	/**Method Name: 
	 * @Purpose  : To set Employee Name according to different Project Type's
	 * @return String 
	 */
	public String projectTypeOwner() {
		BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
		model.initiate(context, session);
		try{
		String brdCode = this.brdBean.getBrdCode();
		model.setDocumentType(this.brdBean);
		model.setEmployeeRole(this.brdBean);
		model.setState(brdBean);
		model.setProjectTypeDropDown(brdBean);
		model.setProjectClassificationDropDown(brdBean);
		model.setSatkeHolderEmp(brdBean);
		model.setBusinessUnitDrop(brdBean);
		model.callProjectTypeOwner(brdBean);
		if (!(brdCode.equals("") || brdCode == null || brdCode.equals("null") || brdCode
				.equals(null))) {
			String applCode = brdBean.getBrdCode();
			Object[][] checkForApplicant = model.isCurrentUserIsApplicant(
					this.brdBean, applCode);
			model.terminate();
			if (String.valueOf(checkForApplicant[0][1]).equals("F")) {
				getNavigationPanel(9);
				this.brdBean.setProjectCommentsFlag(true);
				this.brdBean.setApplicantEditableFlag("applicantEditableMode");
				this.brdBean.setBusinessFlag("true");
				this.brdBean.setClosureFlag("true");
				this.brdBean.setConstraintsFlag("true");
				this.brdBean.setAssumptionFlag("true");
				this.brdBean.setEnableAll("Y");
			} else if (String.valueOf(checkForApplicant[0][1]).equals("D")) {
				this.brdBean.setDisplayFlag("true");
				getNavigationPanel(2);
			} else {
				getNavigationPanel(5);
				this.brdBean.setEnableAll("N");
			}
			model.savedStakeholder(brdBean);
		} else {
			this.brdBean.setDisplayFlag("true");
			model.setConfiguredStakeHolder(brdBean);
			this.brdBean.setSelectRole("7");
			getNavigationPanel(2);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		model.terminate();
		return "success";
	}
	
	/**Method Name: setBusinessUnitEmployee()
	 * @purpose : Set Employee name According to the Business Unit 
	 * @return String
	 */
	public String setBusinessUnitEmployee(){
		try{
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(context, session);
			String brdCode = this.brdBean.getBrdCode();
			model.setDocumentType(this.brdBean);
			model.setEmployeeRole(this.brdBean);
			model.setState(brdBean);
			model.setProjectTypeDropDown(brdBean);
			model.setProjectClassificationDropDown(brdBean);
			model.setSatkeHolderEmp(brdBean);
			model.setBusinessUnitDrop(brdBean);
			model.callBusinessUnitEmp(brdBean);
			if (!(brdCode.equals("") || brdCode == null || brdCode.equals("null") || brdCode
					.equals(null))) {
				String applCode = brdBean.getBrdCode();
				Object[][] checkForApplicant = model.isCurrentUserIsApplicant(
						this.brdBean, applCode);
				model.terminate();
				if (String.valueOf(checkForApplicant[0][1]).equals("F")) {
					getNavigationPanel(9);
					this.brdBean.setProjectCommentsFlag(true);
					this.brdBean.setApplicantEditableFlag("applicantEditableMode");
					this.brdBean.setBusinessFlag("true");
					this.brdBean.setClosureFlag("true");
					this.brdBean.setConstraintsFlag("true");
					this.brdBean.setAssumptionFlag("true");
					this.brdBean.setEnableAll("Y");
				} else if (String.valueOf(checkForApplicant[0][1]).equals("D")) {
					this.brdBean.setDisplayFlag("true");
					getNavigationPanel(2);
				} else {
					getNavigationPanel(5);
					this.brdBean.setEnableAll("N");
				}
				model.savedStakeholder(brdBean);
			} else {
				this.brdBean.setDisplayFlag("true");
				model.setConfiguredStakeHolder(brdBean);
				this.brdBean.setSelectRole("7");
				getNavigationPanel(2);
			}
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
}