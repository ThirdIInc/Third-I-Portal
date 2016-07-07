package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.paradyne.bean.D1.CapitalExpenditure;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CapitalExpenditureModel;
import org.struts.lib.ParaActionSupport;
import org.apache.log4j.Logger;

/**
 * @author aa1380.
 */
public class CapitalExpenditureAction extends ParaActionSupport {
	/** * logger. */
	private final Logger logger = Logger.getLogger(CapitalExpenditureAction.class);
	/** * capitalBean. */
	private CapitalExpenditure capitalBean;
	/** * successStr. */
	private final String successStr = "success";
	/** * inputStr. */
	private final String inputStr = "input";
	/** * falseStr. */
	private final String falseStr = "false";
	/** * f9Str. */
	private final String f9Str = "f9page";
	/** * capitalExpStr. */
	private final String capitalExpStr = "/CapitalExpenditure/";
	/** * dataPathStr. */
	private final String dataPathStr = "data_path";
	/** * sessionPoolStr. */
	private final String sessionPoolStr = "session_pool";
	/** * uploadStr. */
	private final String uploadStr = "/upload";
	
	/** * aStr. */
	private final String aStr = "A";
	/** * bStr. */
	private final String bStr = "B";
	/** * cStr. */
	private final String cStr = "C";
	/** * dStr. */
	private final String dStr = "D";
	/** * pStr. */
	private final String pStr = "P";
	/** * nStr. */
	private final String nStr = "N";
	/** * rStr. */
	private final String rStr = "R";
	/** * xStr. */
	private final String xStr = "X";
	/** * yStr. */
	private final String yStr = "Y";
	
	/** * pdfStr. */
	private final String pdfStr = "pdf";
	/** * msexcelStr. */
	private final String msexcelStr = "msexcel";
	/** * jpgStr. */
	private final String jpgStr = "jpg";
	/** * txtStr. */
	private final String txtStr = "txt";
	/** * gifStr. */
	private final String gifStr = "gif";
	/** * dotStr. */
	private final String dotStr = ".";
	/** * hashStr. */
	private final String hashStr = "#";
	/** * forwardSlashStr. */
	private final String forwardSlashStr = "/";
	/** * uploadFileNameStr. */
	private final String uploadFileNameStr = "uploadFileName";
	
	/**
	 * Method : prepare_local. 
	 * Purpose : Used to set menu code and create bean instance
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.capitalBean = new CapitalExpenditure();
		this.capitalBean.setMenuCode(2029);
	}

	/**
	 * Method : getModel. 
	 * Purpose : Used to return bean instance
	 * @return Object
	 */
	public Object getModel() {
		return this.capitalBean;
	}

	/**
	 * @return the bean
	 */
	public CapitalExpenditure getCapitalBean() {
		return this.capitalBean;
	}

	/**
	 * @param capitalBean the bean to set.
	 */
	public void setCapitalBean(final CapitalExpenditure capitalBean) {
		this.capitalBean = capitalBean;
	}

	@Override
	/** 
	 * Used to set drafted, in-process, sentback application list.
	 * @return String
	 */
	public String input() {
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			final boolean isCurrentUser = model.isCurrentUser(this.capitalBean.getUserEmpId());
			if (isCurrentUser) {
				model.getPendingList(this.capitalBean, request, this.capitalBean.getUserEmpId());
			}
			this.capitalBean.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.inputStr;
	}

	/**
	 * Method : addnew.
	 * Purpose : Used to add new application. Set manager defined in official details 
	 * and path on which file to upload
	 * @return String
	 */
	public String addnew() {
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			model.getcurrentUserInformation(this.capitalBean);
			model.getReportingManager(this.capitalBean);
			String poolName = String.valueOf(session.getAttribute(this.sessionPoolStr));
			if (!("".equals(poolName) || poolName == null)) {
				poolName = this.forwardSlashStr + poolName;
			}
			final String dataPath = this.getText(this.dataPathStr) + this.uploadStr + poolName + this.capitalExpStr;
			this.capitalBean.setDataPath(dataPath);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(1);
		this.capitalBean.setApplicantFlag(true);
		return this.successStr;
	}

	/**
	 * Used to show approved list of applications.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getApprovedList() throws Exception {
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			final boolean isCurrentUser = model.isCurrentUser(this.capitalBean.getUserEmpId());
			if (isCurrentUser) {
				model.getApprovedList(this.capitalBean, request, this.capitalBean.getUserEmpId());
			}
			this.capitalBean.setListType("approved");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.inputStr;
	}

	/**
	 * Used to show all cancel applications. 
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getCancelledList() throws Exception {
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			final boolean isCurrentUser = model.isCurrentUser(this.capitalBean.getUserEmpId());
			if (isCurrentUser) {
				model.getCancelledList(this.capitalBean, request, this.capitalBean.getUserEmpId());
			}
			this.capitalBean.setListType("cancelled");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.inputStr;
	}

	/**
	 * Used to show all rejected applications. 
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getRejectedList() throws Exception {
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			final boolean isCurrentUser = model.isCurrentUser(this.capitalBean.getUserEmpId());
			if (isCurrentUser) {
				model.getRejectedList(this.capitalBean, request, this.capitalBean.getUserEmpId());
			}
			this.capitalBean.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.inputStr;
	}
	
	/**
	 * Method : backToList.
	 * Purpose : Used to return back to list page
	 * @return String
	 */
	public String backToList() {
		this.input();
		return this.inputStr;
	}

	/**
	 * Method : delete.
	 * Purpose : Used to delete application
	 * @return String
	 */
	public String delete() {
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			final boolean result = model.deleteRecord(this.capitalBean);
			model.terminate();
			if (result) {
				this.addActionMessage(this.getMessage("delete"));
			} else {
				this.addActionMessage(this.getMessage("no result"));
			}
		} catch (final Exception e) {
			this.logger.error("Exception occure in delete() : " + e);
		}
		this.input();
		return this.inputStr;
	}

	/**
	 * Method : reset.
	 * Purpose : Used to reset form fields
	 * @return String
	 */
	public String reset() {
		this.capitalBean.setOriginalCheckbox("");
		this.capitalBean.setLocalPurchaseCheckbox("");
		this.capitalBean.setPurchaseDeptCheckbox("");
		this.capitalBean.setComputerITCheckbox("");
		this.capitalBean.setBusinessJustification("");
		this.capitalBean.setReasonForLocalPurchase("");
		this.capitalBean.setDepartmentID("");
		this.capitalBean.setDepartmentNumber("");
		this.capitalBean.setDateRequired("");
		this.capitalBean.setLocationID("");
		this.capitalBean.setLocation("");
		this.capitalBean.setShipToCompany("");
		this.capitalBean.setCityID("");
		this.capitalBean.setCity("");
		this.capitalBean.setState("");
		this.capitalBean.setStreetAddress("");
		this.capitalBean.setZipCode("");
		this.capitalBean.setAttention("");
		this.capitalBean.setTelePhoneNumber("");
		this.capitalBean.setQuantity("");
		this.capitalBean.setDescription("");
		this.capitalBean.setVendorID("");
		this.capitalBean.setVendorName("");
		this.capitalBean.setUnitPrice("");
		this.capitalBean.setTotalCost("");
		this.capitalBean.setTagNumber("");
		this.capitalBean.setSerialNumber("");
		this.capitalBean.setCEARPrice("");
		this.capitalBean.setQuotesAttached("");
		this.capitalBean.setQuotesReason("");
		this.capitalBean.setUploadFileName("");
		this.capitalBean.setComments("");
		this.capitalBean.setCostInstallation("");
		this.capitalBean.setCostMaterial("");
		this.capitalBean.setCostFreight("");
		this.capitalBean.setCostTax("");
		this.capitalBean.setCostTotal("");
		this.getNavigationPanel(1);
		this.capitalBean.setEnableAll(this.yStr);
		return this.successStr;
	}

	/**
	 * Method : view Attached proof.
	 * Purpose : Used to reset form fields
	 * @throws Exception : Exception
	 */
	public void viewAttachedProof() throws Exception {
		try {
			final String uploadFileName = request.getParameter(this.uploadFileNameStr);
			this.viewUploadedDocument(uploadFileName);  
		} catch (final Exception e) {
			this.logger.error("Exception in viewAttachedProof in action:" + e);
		}
	}

	/**
	 * Method : f9InvoiceVendor.
	 * Purpose : Used to select vendors defined in the HRMS_D1_VENDOR  
	 * @return String
	 */
	public String f9InvoiceVendor() {
		final String query = "SELECT  VENDOR_ID, VENDOR_NAME FROM HRMS_D1_VENDOR";
		final String[] headers = {"Vendor Code", "Vendor Name"};
		final String[] headerWidth = {"25", "75"};
		final String[] fieldNames = {"invoiceVendorID", "invoiceVendorNumber"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Method : f9DetailVendor.
	 * Purpose : Used to select vendors defined in the HRMS_D1_VENDOR  
	 * @return String
	 */
	public String f9DetailVendor() {
		final String vendorNumber = request.getParameter("vendorNumber");
		final String vendor = request.getParameter("vendor");
		final String query = "SELECT VENDOR_ID, VENDOR_NAME FROM HRMS_D1_VENDOR";
		final String[] headers = {"Vendor ID", "Vendor"};
		final String[] headerWidth = {"22", "78" };
		final String[] fieldNames = {"vendorID" + vendorNumber, "vendorName" + vendor };
		final int[] columnIndex = {0, 1};
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Method : f9DepartmentNum.
	 * Purpose : Used to select department defined in the HRMS_DEPT  
	 * @return String
	 * @throws Exception : Exception
	 */
	public String f9DepartmentNum() throws Exception {
		final String query = " SELECT DEPT_ID,DEPT_NAME||' - '||DEPT_ABBR FROM HRMS_DEPT ORDER BY DEPT_NAME DESC";
		final String[] headers = {"Department ID", "Department"};
		final String[] headerWidth = {"23", "77"};
		final String[] fieldNames = {"departmentID", "departmentNumber"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Method : f9Location.
	 * Purpose : Used to select location.
	 * branch information get set  
	 * @return String
	 * @throws Exception : Exception
	 */
	public String f9Location() throws Exception {
		final String query = " SELECT CENTER_NAME,CENTER_CITY,CENTER_ID FROM HRMS_CENTER ORDER BY  CENTER_ID";
		final String[] headers = {"Location", "Location City"};
		final String[] headerWidth = {"30", "35"};
		final String[] fieldNames = {"location", "cityName", "locationID"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = "true";
		final String submitToMethod = "CapitalExpenditure_setBranchInformation.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Method : setBranchInformation.
	 * Purpose : Based on selected location set branch information
	 * @return String
	 */
	public String setBranchInformation() {
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			model.getBranchRelatedInformation(this.capitalBean);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(1);
		this.capitalBean.setEnableAll(this.yStr);
		this.capitalBean.setApplicantFlag(true);
		return this.successStr;
	}

	/**
	 * Method : draftFunction.
	 * Purpose : Used to save and update form data
	 * @return String
	 */
	public String draftFunction() {
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			boolean result;
			if ("".equals(this.capitalBean.getCapitalExpID())) {
				result = model.draftFunction(this.capitalBean, request);
				if (result) {
					this.addActionMessage("Record saved successfully.");
				} else {
					this.addActionMessage("Error occur during saving the records.");
				}
			} else {
				result = model.updateRecords(this.capitalBean, request);
				if (result) {
					this.addActionMessage("Record modified successfully.");
				} else {
					this.addActionMessage("Error occur during updating the records.");
				}
			}
		} catch (final Exception e) {
			this.logger.error("Exception occured in draftFunction() : " + e);
			e.printStackTrace();
		}
		this.input();
		return this.inputStr;
	}

	/**
	 * Method : f9City.
	 * Purpose : Used to select city.
	 * @return String
	 */
	public String f9City() {
		final String query = " SELECT CITY.LOCATION_NAME AS CITY, STATE.LOCATION_NAME AS STATE, CITY.LOCATION_CODE AS CITY_CODE " + 
					   " FROM HRMS_LOCATION STATE " + 
					   " LEFT JOIN HRMS_LOCATION CITY ON(STATE.LOCATION_CODE = CITY.LOCATION_PARENT_CODE) WHERE CITY.LOCATION_LEVEL_CODE = 2 " + 
					   " ORDER BY UPPER(CITY)";
		final String[] headers = {"City", "State"};
		final String[] headerWidth = {"45", "55"};
		final String[] fieldNames = {"city", "state", "cityID"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Method : f9Approver.
	 * Purpose : Used to select approver.
	 * @return String
	 */
	public String f9Approver() {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC ";
		if (this.capitalBean.getUserProfileDivision() != null && this.capitalBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.capitalBean.getUserProfileDivision() + ") ";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + this.capitalBean.getSubmittedByID() + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		final String[] headers = {"Employee ID", "Employee"};
		final String[] headerWidth = {"20", "80"};
		final String[] fieldNames = {"forwardedManagerToken", "forwardedManagerName", "forwardedManagerID"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Method : viewApplicationFunction.
	 * Purpose : Used to view application details.
	 * @return String
	 */
	public String viewApplicationFunction() {
		String poolName = String.valueOf(session.getAttribute(this.sessionPoolStr));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = this.forwardSlashStr + poolName;
		}
		final String dataPath = this.getText(this.dataPathStr) + this.uploadStr + poolName + this.capitalExpStr;
		this.capitalBean.setDataPath(dataPath);
		String capitalHiddenID = request.getParameter("capitalHiddenID");
		final String hiddenStatus = request.getParameter("hiddenStatus");
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			// FOR SUPER USER. 
			final String applCode = request.getParameter("applCode");
			if (applCode != null && applCode.length() > 0) {
				capitalHiddenID = applCode;
			}
			model.viewApplication(this.capitalBean, capitalHiddenID, hiddenStatus, request);
			model.getApproverCommentList(this.capitalBean, capitalHiddenID);
			model.terminate();
			if (hiddenStatus.equals(this.dStr)) {
				this.getNavigationPanel(2);
				this.capitalBean.setEnableAll(this.yStr);
				this.capitalBean.setApplicationStatus(this.dStr);
				this.capitalBean.setApplicantFlag(true);
				this.capitalBean.setAccountantFlag(false);
			} else if (hiddenStatus.equals(this.pStr)) {
				this.getNavigationPanel(3);
				this.capitalBean.setEnableAll(this.nStr);
				this.capitalBean.setApplicationStatus(this.pStr);
				this.capitalBean.setApplicantFlag(false);
				this.capitalBean.setAccountantFlag(true);
			} else if (hiddenStatus.equals(this.bStr)) {
				this.getNavigationPanel(2);
				this.capitalBean.setEnableAll(this.yStr);
				this.capitalBean.setApplicationStatus(this.bStr);
				this.capitalBean.setApplicantFlag(true);
				this.capitalBean.setAccountantFlag(false);
			} else if (hiddenStatus.equals(this.aStr)) {
				this.getNavigationPanel(4);
				this.capitalBean.setEnableAll(this.nStr);
				this.capitalBean.setApplicationStatus(this.aStr);
				this.capitalBean.setApplicantFlag(false);
				this.capitalBean.setAccountantFlag(true);
				this.capitalBean.setPpoFlag(true);
			} else if (hiddenStatus.equals(this.xStr)) {
				this.getNavigationPanel(3);
				this.capitalBean.setEnableAll(this.nStr);
				this.capitalBean.setApplicationStatus(this.xStr);
				this.capitalBean.setApplicantFlag(false);
				this.capitalBean.setAccountantFlag(true);
			} else if (hiddenStatus.equals(this.cStr)) {
				this.getNavigationPanel(3);
				this.capitalBean.setEnableAll(this.nStr);
				this.capitalBean.setApplicationStatus(this.cStr);
				this.capitalBean.setApplicantFlag(false);
				this.capitalBean.setAccountantFlag(true);
			} else if (hiddenStatus.equals(this.rStr)) {
				this.getNavigationPanel(3);
				this.capitalBean.setEnableAll(this.nStr);
				this.capitalBean.setApplicationStatus(this.rStr);
				this.capitalBean.setApplicantFlag(false);
				this.capitalBean.setAccountantFlag(true);
			} else {
				this.getNavigationPanel(3);
				this.capitalBean.setEnableAll(this.nStr);
				this.capitalBean.setApplicationStatus("Z");
				this.capitalBean.setApplicantFlag(false);
				this.capitalBean.setAccountantFlag(true);
			}
			if (applCode != null && applCode.length() > 0) {
				this.capitalBean.setEnableAll(this.nStr);
			}
		} catch (final Exception e) {
			this.capitalBean.setEnableAll(this.nStr);
			this.logger.error("Exception ovvured in viewApplicationFunction() : " + e);
		}
		// this.capitalBean.setApplicantFlag(true);
		return this.successStr;
	}

	/**
	 * Method : sendForApprovalFunction.
	 * Purpose : Used to send application for approval
	 * @return String
	 */
	public String sendForApprovalFunction() {
		try {
			final CapitalExpenditureModel model = new CapitalExpenditureModel();
			model.initiate(context, session);
			final boolean result = model.sendForApprovalFunction(this.capitalBean, request);
			final String applicationId = this.capitalBean.getCapitalExpID();
			final String userID = this.capitalBean.getUserEmpId();
			final String forwardedManagetID = this.capitalBean.getForwardedManagerID();
			final String status = this.capitalBean.getStatus();
			model.terminate();
			if (result) {
				final String path = this.capitalBean.getDataPath();
				final String fileanme = this.capitalBean.getUploadFileName();
				this.addActionMessage("Application Successfully Sent For Approval.\n Tracking Number : " +
						 this.capitalBean.getTrackingNumber());
				this.sendMailFromApplicantToApprovalMail(applicationId, userID, forwardedManagetID, status, path, fileanme);
			} else {
				this.addActionMessage("Error occured sending application.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return this.inputStr;
	}

	/**
	 * Used to send Mail from applicant to approver begins.
	 * @param applicationId : application id
	 * @param userID : Current user id
	 * @param forwardedManagetID : forward to employee id
	 * @param status : application status
	 * @param path : attached file path
	 * @param fileanme : attached file name
	 */
	private void sendMailFromApplicantToApprovalMail(final String applicationId,
			final String userID, final String forwardedManagetID, final String status,
			final String path, final String fileanme) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CAPITAL EXPENDITURE AUTHORIZATION MAIL FROM APPLICANT TO MANAGER");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery tempQuery1 = templateApp.getTemplateQuery(1);
			tempQuery1.setParameter(1, userID);
			final EmailTemplateQuery tempQuery2 = templateApp.getTemplateQuery(2);
			tempQuery2.setParameter(1, forwardedManagetID);
			final EmailTemplateQuery tempQuery3 = templateApp.getTemplateQuery(3);
			tempQuery3.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery4 = templateApp.getTemplateQuery(4);
			tempQuery4.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery5 = templateApp.getTemplateQuery(5);
			tempQuery5.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery6 = templateApp.getTemplateQuery(6);
			tempQuery6.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery7 = templateApp.getTemplateQuery(7);
			tempQuery7.setParameter(1, applicationId);

			templateApp.configMailAlert();
			if (!"".equals(fileanme)) {
				final String[] attachment = new String[1];
				attachment[0] = path + fileanme;
				templateApp.sendApplMailWithAttachment(attachment);
			} else {
				templateApp.sendApplicationMail();
			}
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used to view uploaded file.
	 */
	public void viewUploadedFile() {
		try {
			final String uploadFileName = request.getParameter(this.uploadFileNameStr);
			this.viewUploadedDocument(uploadFileName); 
		} catch (final Exception e) {
			this.logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
	
	/**
	 * Used to view uploaded file.
	 * @param uploadFileName : Uploaded file name
	 */
	public void viewUploadedDocument(final String uploadFileName) {
		try {
			final String dataPath = request.getParameter("dataPath");
			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session.getAttribute(this.sessionPoolStr));
				if (!("".equals(poolName) || poolName == null)) {
					poolName = this.forwardSlashStr + poolName;
				}
				fileName = uploadFileName;
				fileName = fileName.replace(this.dotStr, this.hashStr);
				final String[] extension = fileName.split(this.hashStr);
				final String ext = extension[extension.length - 1];
				fileName = fileName.replace(this.hashStr, this.dotStr);
				if (this.pdfStr.equals(ext)) {
					mimeType = "acrobat";
				} else if ("doc".equals(ext)) {
					mimeType = "msword";
				} else if ("xls".equals(ext)) {
					mimeType = this.msexcelStr;
				} else if ("xlsx".equals(ext)) {
					mimeType = this.msexcelStr;
				} else if (this.jpgStr.equals(ext)) {
					mimeType = this.jpgStr;
				} else if (this.txtStr.equals(ext)) {
					mimeType = this.txtStr;
				} else if (this.gifStr.equals(ext)) {
					mimeType = this.gifStr;
				}

				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					}
				}

				final String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (!this.pdfStr.equals(ext)) {
					response.setHeader("Content-type", "application/" + mimeType + "");
				} 
				response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");
				int iChar;
				fsStream = new FileInputStream(path);
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch (final FileNotFoundException e) {
				this.addActionMessage("proof document not found");
			} finally {
				if (fsStream != null) {
					fsStream.close();
				}
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				}
			}
		} catch (final Exception e) {
			this.logger.error("Exception in viewUploadedDocument in action:" + e);
		}
	}
	
	
	
}
