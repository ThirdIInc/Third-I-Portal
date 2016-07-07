package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.paradyne.bean.D1.CapitalExpenditureApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CapitalExpenditureApprovalModel;
import org.struts.lib.ParaActionSupport;
import org.apache.log4j.Logger;

/**
 * @author aa1380.
 */
public class CapitalExpenditureApprovalAction extends ParaActionSupport {
	/** * logger. */
	private Logger logger = Logger.getLogger(CapitalExpenditureApprovalAction.class);
	/** * capApprBean. */
	private CapitalExpenditureApproval capApprBean;
	/** inputStr. * */
	private final String inputStr = "input";
	/** successStr. * */
	private final String successStr = "success";
	/** approvedStr. * */
	private final String approvedStr = "approved";
	/** approvedStr. * */
	private final String rejectedStr = "rejected";
	/** * capitalExpStr. */
	private final String capitalExpStr = "/CapitalExpenditure/";
	/** * dataPathStr. */
	private final String dataPathStr = "data_path";
	/** * sessionPoolStr. */
	private final String sessionPoolStr = "session_pool";
	/** * uploadStr. */
	private final String uploadStr = "/upload";
	/** * forwardSlashStr. */
	private final String forwardSlashStr = "/";
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
	
	/** * bStr. */
	private final String bStr = "B";
	/** * cStr. */
	private final String cStr = "C";
	/** * fStr. */
	private final String fStr = "F";
	/** * nStr. */
	private final String nStr = "N";
	/** * pStr. */
	private final String pStr = "P";
	/** * rStr. */
	private final String rStr = "R";
	/** * sStr. */
	private final String sStr = "S";
	/** * ceoStr. */
	private final String ceoStr = "CEO";
	
	/**
	 * Method : prepare_local. 
	 * Purpose : Used to set menu code and create bean instance
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.capApprBean = new CapitalExpenditureApproval();
		this.capApprBean.setMenuCode(2044);
	}

	/**
	 * @return the bean
	 */
	public CapitalExpenditureApproval getCapApprBean() {
		return this.capApprBean;
	}

	/**
	 * @param capApprBean the bean to set.
	 */
	public void setCapApprBean(final CapitalExpenditureApproval capApprBean) {
		this.capApprBean = capApprBean;
	}

	/**
	 * Method : getModel. 
	 * Purpose : Used to return bean instance
	 * @return Object
	 */
	public Object getModel() {
		return this.capApprBean;
	}

	@Override
	/** 
	 * Used to set pending list.
	 * @return String
	 */
	public String input() {
		try {
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			final String financeUser = this.capApprBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceDeptPerson(financeUser, this.capApprBean);
			if (isFinanceApprover) {
				model.getPendingAuthorizedList(this.capApprBean, request);
			} else {
				model.getPendingList(this.capApprBean, request);
			}
			this.capApprBean.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception is occured in input() : " + e);
		}
		return this.inputStr;
	}

	
	/**Used to get approved list of application.
	 * @return String
	 */
	public String getApprovedList() {
		try {
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			final String userId = this.capApprBean.getUserEmpId();
			model.getApprovedList(this.capApprBean, request, userId);
			this.capApprBean.setListType(this.approvedStr);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in getApprovedList() : " + e);
		}
		return this.inputStr;
	}

	/**Used to get rejected list of application.
	 * @return String
	 */
	public String getRejectedList() {
		try {
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			final String financeUser = this.capApprBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceDeptPerson(financeUser, this.capApprBean);
			if (isFinanceApprover) {
				model.getRejectedList(this.capApprBean, request);
			} else {
				model.getRejectedList(this.capApprBean, request);
			}
			this.capApprBean.setListType(this.rejectedStr);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in getRejectedList() : " + e);
		}
		return this.inputStr;
	}

	/**Used to get view application details.
	 * @return String
	 */
	public String viewApplicationFunctionApproved() {
		String poolName = String.valueOf(session.getAttribute(this.sessionPoolStr));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = this.forwardSlashStr + poolName;
		}
		// for getting server path where configuration files are saved.
		final String dataPath = this.getText(this.dataPathStr) + this.uploadStr + poolName + this.capitalExpStr;
		this.capApprBean.setDataPath(dataPath);
		final String capitalHiddenID = request.getParameter("capitalHiddenID");
		final String hiddenStatus = request.getParameter("hiddenStatus");
		final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
		try {
			model.initiate(context, session);
			if ("A".equals(hiddenStatus) || hiddenStatus.equals(this.fStr) || hiddenStatus.equals(this.sStr)) {
				this.capApprBean.setF9Flag(true);
			}
			model.viewApplicationApproved(this.capApprBean, capitalHiddenID, hiddenStatus, request);
			model.getApproverCommentList(this.capApprBean, capitalHiddenID);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in viewApplicationFunctionApproved() : " + e);
		}

		if (this.capApprBean.getListType().equals(this.approvedStr) || this.capApprBean.getListType().equals(this.rejectedStr)) {
			this.getNavigationPanel(3);
			this.capApprBean.setFinanceApprovalFlag(false);
			this.capApprBean.setOtherApproverFlag(true);
			this.capApprBean.setApproverCommentsFlag(false);
			this.capApprBean.setPpoFlag(true);
		} else {
			final String financeUser = this.capApprBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceDeptPerson(financeUser, this.capApprBean);
			if (isFinanceApprover) {
				if (this.capApprBean.getHiddenForwardedType().equals(this.ceoStr)) {
					this.getNavigationPanel(4);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else if (hiddenStatus.equals(this.cStr) || hiddenStatus.equals(this.sStr)) {
					this.getNavigationPanel(2);
					this.capApprBean.setFinanceApprovalFlag(true);
					this.capApprBean.setOtherApproverFlag(false);
				} else if (hiddenStatus.equals(this.fStr)) {
					this.getNavigationPanel(1);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else if (hiddenStatus.equals(this.pStr)) {
					this.getNavigationPanel(5);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else {
					this.getNavigationPanel(3);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				}
			} else {
				if (hiddenStatus.equals(this.pStr) || hiddenStatus.equals(this.cStr) || hiddenStatus.equals(this.fStr)) {
					if (this.capApprBean.getHiddenForwardedType().equals(this.ceoStr)) {
						this.getNavigationPanel(4);
					} else if (hiddenStatus.equals(this.pStr)) {  
						this.getNavigationPanel(5);
					} else if (hiddenStatus.equals(this.fStr)) {
						this.getNavigationPanel(1);
					}
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else {
					this.getNavigationPanel(3);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				}
			}
			this.capApprBean.setApproverCommentsFlag(true);
		}
		this.capApprBean.setEnableAll(this.nStr);
		return this.successStr;
	}

	/**View application details after from rejected list.
	 * @return String
	 */
	public String viewApplicationFunctionRejected() {
		String poolName = String.valueOf(session.getAttribute(this.sessionPoolStr));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = this.forwardSlashStr + poolName;
		}
		// for getting server path where configuration files are saved.
		final String dataPath = this.getText(this.dataPathStr) + this.uploadStr + poolName + this.capitalExpStr;
		this.capApprBean.setDataPath(dataPath);
		final String capitalHiddenID = request.getParameter("capitalHiddenID");
		final String hiddenStatus = request.getParameter("hiddenStatus");
		final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
		try {
			model.initiate(context, session);
			if (hiddenStatus.equals(this.rStr)) {
				this.capApprBean.setF9Flag(true);
			}

			model.viewApplicationRejected(this.capApprBean, capitalHiddenID, hiddenStatus, request);
			model.getApproverCommentList(this.capApprBean, capitalHiddenID);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in viewApplicationFunctionRejected() : " + e);
		}

		if (this.capApprBean.getListType().equals(this.approvedStr) || this.capApprBean.getListType().equals(this.rejectedStr)) {
			this.getNavigationPanel(3);
			this.capApprBean.setFinanceApprovalFlag(false);
			this.capApprBean.setOtherApproverFlag(true);
			this.capApprBean.setApproverCommentsFlag(false);
			this.capApprBean.setPpoFlag(true);
		} else {
			final String financeUser = this.capApprBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceDeptPerson(financeUser, this.capApprBean);
			if (isFinanceApprover) {
				if (this.capApprBean.getHiddenForwardedType().equals(this.ceoStr)) {
					this.getNavigationPanel(4);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else if (hiddenStatus.equals(this.cStr) || hiddenStatus.equals(this.sStr)) {
					this.getNavigationPanel(2);
					this.capApprBean.setFinanceApprovalFlag(true);
					this.capApprBean.setOtherApproverFlag(false);
				} else if (hiddenStatus.equals(this.fStr)) {
					this.getNavigationPanel(1);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else if (hiddenStatus.equals(this.pStr)) {
					this.getNavigationPanel(5);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else {
					this.getNavigationPanel(3);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				}
			} else {
				if (hiddenStatus.equals(this.pStr) || hiddenStatus.equals(this.cStr) || hiddenStatus.equals(this.fStr)) {
					if (this.capApprBean.getHiddenForwardedType().equals(this.ceoStr)) {
						this.getNavigationPanel(4);
					} else if (hiddenStatus.equals(this.pStr)) {  
						this.getNavigationPanel(5);
					} else if (hiddenStatus.equals(this.fStr)) {
						this.getNavigationPanel(1);
					}
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else {
					this.getNavigationPanel(3);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				}
			}
			this.capApprBean.setApproverCommentsFlag(true);
		}
		this.capApprBean.setEnableAll(this.nStr);
		return this.successStr;
	}

	/**Used to view application details.
	 * @return String
	 */
	public String viewApplicationFunction() {
		String poolName = String.valueOf(session.getAttribute(this.sessionPoolStr));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = this.forwardSlashStr + poolName;
		}
		// for getting server path where configuration files are saved.
		final String dataPath = this.getText(this.dataPathStr) + this.uploadStr + poolName + this.capitalExpStr;
		this.capApprBean.setDataPath(dataPath);
		final String capitalHiddenID = request.getParameter("capitalHiddenID");
		final String hiddenStatus = request.getParameter("hiddenStatus");
		final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
		try {
			model.initiate(context, session);
			if (hiddenStatus.equals(this.fStr) || hiddenStatus.equals(this.pStr)) {
				this.capApprBean.setDisplayFlag(true);
			}
			model.viewApplication(this.capApprBean, capitalHiddenID, hiddenStatus, request);
			model.getApproverCommentList(this.capApprBean, capitalHiddenID);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in viewApplicationFunction() : " + e);
		}

		if (this.capApprBean.getListType().equals(this.approvedStr) || this.capApprBean.getListType().equals(this.rejectedStr)) {
			this.getNavigationPanel(3);
			this.capApprBean.setFinanceApprovalFlag(false);
			this.capApprBean.setOtherApproverFlag(true);
			this.capApprBean.setApproverCommentsFlag(false);
			this.capApprBean.setPpoFlag(true);
		} else {
			final String financeUser = this.capApprBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceDeptPerson(financeUser, this.capApprBean);
			if (isFinanceApprover) {
				if (this.capApprBean.getHiddenForwardedType().equals(this.ceoStr)) {
					this.getNavigationPanel(4);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else if (hiddenStatus.equals(this.cStr) || hiddenStatus.equals(this.sStr)) {
					this.getNavigationPanel(2);
					this.capApprBean.setFinanceApprovalFlag(true);
					this.capApprBean.setOtherApproverFlag(false);
				} else if (hiddenStatus.equals(this.fStr)) {
					this.getNavigationPanel(1);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else if (hiddenStatus.equals(this.pStr)) {
					this.getNavigationPanel(5);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else {
					this.getNavigationPanel(3);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				}
			} else {
				if (hiddenStatus.equals(this.pStr) || hiddenStatus.equals(this.cStr) || hiddenStatus.equals(this.fStr)) {
					if (this.capApprBean.getHiddenForwardedType().equals(this.ceoStr)) {
						this.getNavigationPanel(4);
					} else if (hiddenStatus.equals(this.pStr)) {  
						this.getNavigationPanel(5);
					} else if (hiddenStatus.equals(this.fStr)) {
						this.getNavigationPanel(1);
					}
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				} else {
					this.getNavigationPanel(3);
					this.capApprBean.setFinanceApprovalFlag(false);
					this.capApprBean.setOtherApproverFlag(true);
				}
			}
			this.capApprBean.setApproverCommentsFlag(true);
		}

		this.capApprBean.setEnableAll(this.nStr);
		return this.successStr;
	}

	/**Used to select approver.
	 * @return String
	 */
	public String f9Approver() {
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC ";
		if (this.capApprBean.getUserProfileDivision() != null && this.capApprBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.capApprBean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + this.capApprBean.getUserEmpId() + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = {"Employee ID", "Employee"};
		final String[] headerWidth = {"20", "80"};
		final String[] fieldNames = {"forwardedApproverToken", "forwardedApproverName", "forwardedApproverID"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	/**Used to approve application.
	 * @return String
	 */
	public String approveApplication() {
		final String applicationId = this.capApprBean.getCapitalExpID();
		final String initiatorID = this.capApprBean.getSubmittedByID();
		final String userID = this.capApprBean.getUserEmpId();
		final String status = this.capApprBean.getStatus();
		final String poNumber = this.capApprBean.getPpoNumber();
		final String poAttachment = this.capApprBean.getPpoAttachement();
		final String path = this.capApprBean.getDataPath();
		final String fileanme = this.capApprBean.getUploadFileName();
		try {
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			final boolean result = model.updateDetailsTable(this.capApprBean, status, applicationId, request, poNumber, poAttachment);
			model.terminate();
			if (result) {
				this.addActionMessage("Application approved successfully.");
				this.sendMailToInitiator(applicationId, initiatorID, userID, status, path, fileanme, poAttachment);
			} else {
				this.addActionMessage("Error occured while approving application.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return this.inputStr;
	}

	/**Used to authorized sign off application.
	 * @return String
	 */
	public String authorizedSignOffApplication() {
		final String applicationId = this.capApprBean.getCapitalExpID();
		final String initiatorID = this.capApprBean.getSubmittedByID();
		final String userID = this.capApprBean.getUserEmpId();
		final String status = this.capApprBean.getStatus();
		final String poNumber = this.capApprBean.getPpoNumber();
		final String poAttachment = this.capApprBean.getPpoAttachement();
		final String path = this.capApprBean.getDataPath();
		final String fileanme = this.capApprBean.getUploadFileName();
		try {
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			final boolean financeApproverAvailable = model.isFinancePersonAvailable();
			if (financeApproverAvailable) {
				final boolean result = model.UpdateCapitalExpTable(this.capApprBean, status, applicationId, poNumber, poAttachment);
				model.terminate();
				if (result) {
					this.addActionMessage("Authorized Sign Off Successfully.");
					this.sendAuthorizedMailToAllFinancePerson(applicationId, initiatorID, userID, status, path, fileanme, poAttachment);
				} else {
					this.addActionMessage("Error occured while authorizing application.");
				}
			} else {
				this.addActionMessage("Finance authority approver is not available, Please contact to HR Department.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return this.inputStr;
	}

	/**Send sign-off mail to Mail to finance department and CC to Initiator and previous approver.
	 * @param applicationId : Application ID
	 * @param initiatorID : Initiator Id
	 * @param userID : current login user id
	 * @param status : application status
	 * @param path : File path
	 * @param fileanme : uploaded file name
	 * * @param poAttachment : PO attached file
	 */
	private void sendAuthorizedMailToAllFinancePerson(final String applicationId,
			final String initiatorID, final String userID, final String status, final String path,
			final String fileanme, final String poAttachment) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CAPITAL EXPENDITURE AUTHORIZATION MAIL TO CEAR GROUP");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery tempQuery1 = templateApp.getTemplateQuery(1);
			tempQuery1.setParameter(1, userID);
			final EmailTemplateQuery tempQuery2 = templateApp.getTemplateQuery(2);
			tempQuery2.setParameter(1, "0");
			final EmailTemplateQuery tempQuery3 = templateApp.getTemplateQuery(3);
			tempQuery3.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery4 = templateApp.getTemplateQuery(4);
			tempQuery4.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery5 = templateApp.getTemplateQuery(5);
			tempQuery5.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery6 = templateApp.getTemplateQuery(6);
			tempQuery6.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery7 = templateApp.getTemplateQuery(7);
			tempQuery7.setParameter(1, userID);
			final EmailTemplateQuery tempQuery8 = templateApp.getTemplateQuery(8);
			tempQuery8.setParameter(1, applicationId);
			templateApp.configMailAlert();

			// mail to CEAR Group Begins
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			final String query = " SELECT NVL(APP_EMAIL_ID,'') AS APPROVER_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'S' AND APP_EMAIL_ID IS NOT NULL";
			final Object[][] dataObj = model.getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0) {
				final String[] attachment = new String[2];
				attachment[0] = path + fileanme;
				attachment[1] = path + poAttachment;
				templateApp.sendApplicationMailToGroups(dataObj);
			}
			// mail to CEAR Group Ends

			final List<String> l1 = new ArrayList<String>();
			l1.add(initiatorID);
			// Previous Approver
			final String otherApproverQuery = "SELECT DISTINCT EXP_APPROVER_CODE FROM HRMS_D1_CAPITALEXP_PATH " + 
										" WHERE EXP_APPROVER_CODE NOT IN (" + this.capApprBean.getUserEmpId() + ") " + 
										" AND EXP_CODE  = " + applicationId;
			final Object[][] otherApproverQueryObj = model.getSqlModel().getSingleResult(otherApproverQuery);
			if (otherApproverQueryObj != null && otherApproverQueryObj.length > 0) {
				for (int i = 0; i < otherApproverQueryObj.length; i++) {
					l1.add(String.valueOf(otherApproverQueryObj[i][0]));
				}
			}

			final String[] keepInformToData = new String[l1.size()];
			int count = 0;
			for (int i = 0; i < l1.size(); i++) {
				keepInformToData[count] = l1.get(i);
				count++;
			}

			final String[] attachment = new String[1];
			attachment[0] = path + fileanme;

			// templateApp.sendApplicationMailToKeepInfo(keepInformToData);
			templateApp.sendApplMailWithAttachmentToKeepInf(keepInformToData, attachment);
			// templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to reject application.
	 * @return String
	 */
	public String rejectApplication() {
		final String applicationId = this.capApprBean.getCapitalExpID();
		final String initiatorID = this.capApprBean.getSubmittedByID();
		final String userID = this.capApprBean.getUserEmpId();
		final String status = this.capApprBean.getStatus();
		final String poNumber = this.capApprBean.getPpoNumber();
		final String poAttachment = this.capApprBean.getPpoAttachement();

		final String path = this.capApprBean.getDataPath();
		final String fileanme = this.capApprBean.getUploadFileName();

		try {
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			final boolean result = model.UpdateCapitalExpTable(this.capApprBean, status, applicationId, poNumber, poAttachment);
			model.terminate();
			if (result) {
				this.addActionMessage("Application rejected successfully.");
				final String financeUser = this.capApprBean.getUserEmpId();
				final boolean isFinanceApprover = model.isFinanceDeptPerson(financeUser, this.capApprBean);
				if (isFinanceApprover) {
					this.sendFinanceRejectMailToInitiator(applicationId, initiatorID, userID, status, path, fileanme);
				} else {
					this.sendOtherApproverRejectMailToInitiator(applicationId, initiatorID, userID, status, path, fileanme);
				}
			} else {
				this.addActionMessage("Error occured while rejecting application.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return this.inputStr;
	}

	/**Reject Mail to Initiator From Other Approver KeepInformTo : previous approver BEGINS.
	 * @param applicationId : Application ID
	 * @param initiatorID : Initiator Id
	 * @param userID : current login user id
	 * @param status : application status
	 * @param path : File path
	 * @param fileanme : uploaded file name
	 */
	private void sendOtherApproverRejectMailToInitiator(final String applicationId,
			final String initiatorID, final String userID, final String status, final String path,
			final String fileanme) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CAPITAL EXPENDITURE AUTHORIZATION MAIL APPROVE/REJECT/SENT-BACK");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery tempQuery1 = templateApp.getTemplateQuery(1);
			tempQuery1.setParameter(1, userID);
			final EmailTemplateQuery tempQuery2 = templateApp.getTemplateQuery(2);
			tempQuery2.setParameter(1, initiatorID);
			final EmailTemplateQuery tempQuery3 = templateApp.getTemplateQuery(3);
			tempQuery3.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery4 = templateApp.getTemplateQuery(4);
			tempQuery4.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery5 = templateApp.getTemplateQuery(5);
			tempQuery5.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery6 = templateApp.getTemplateQuery(6);
			tempQuery6.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery7 = templateApp.getTemplateQuery(7);
			tempQuery7.setParameter(1, userID);
			final EmailTemplateQuery tempQuery8 = templateApp.getTemplateQuery(8);
			tempQuery8.setParameter(1, applicationId);

			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			String[] keepInformToData = null;

			// Previous Approver
			final Object[][] otherApproverQueryObj = model.getSqlModel().getSingleResult("SELECT DISTINCT EXP_APPROVER_CODE FROM HRMS_D1_CAPITALEXP_PATH WHERE EXP_APPROVER_CODE NOT IN (" + this.capApprBean.getUserEmpId() + ") AND EXP_CODE = " + applicationId);
			if (otherApproverQueryObj != null && otherApproverQueryObj.length > 0) {
				keepInformToData = new String[otherApproverQueryObj.length];
				for (int i = 0; i < otherApproverQueryObj.length; i++) {
					keepInformToData[i] = String.valueOf(otherApproverQueryObj[i][0]);
				}
			}

			templateApp.configMailAlert();
			if (otherApproverQueryObj != null && otherApproverQueryObj.length > 0) {
				// templateApp.sendApplicationMailToKeepInfo(keepInformToData);
				final String[] attachment = new String[1];
				attachment[0] = path + fileanme;
				// templateApp.sendApplicationMailToKeepInfo(keepInformToData);

				templateApp.sendApplMailWithAttachmentToKeepInf(keepInformToData, attachment);

			}
			if (!"".equals(fileanme)) {
				final String[] attachment = new String[1];
				attachment[0] = path + fileanme;
				templateApp.sendApplMailWithAttachment(attachment);
			} else {
				templateApp.sendApplicationMail();
			}
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	// Other Approver Reject Mail To ENDS

	/**
	 * Send Reject Mail to Initiator From Finance KeepInformTo : previous approver. 
	 * and other finance approver BEGINS
	 * @param applicationId : Application ID
	 * @param initiatorID : Initiator Id
	 * @param userID : current login user id
	 * @param status : application status
	 * @param path : uploaded file path
	 * @param fileanme : uploaded file name
	 */
	private void sendFinanceRejectMailToInitiator(final String applicationId,
			final String initiatorID, final String userID, final String status, final String path,
			final String fileanme) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CAPITAL EXPENDITURE AUTHORIZATION MAIL APPROVE/REJECT/SENT-BACK");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery tempQuery1 = templateApp.getTemplateQuery(1);
			tempQuery1.setParameter(1, userID);
			final EmailTemplateQuery tempQuery2 = templateApp.getTemplateQuery(2);
			tempQuery2.setParameter(1, initiatorID);
			final EmailTemplateQuery tempQuery3 = templateApp.getTemplateQuery(3);
			tempQuery3.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery4 = templateApp.getTemplateQuery(4);
			tempQuery4.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery5 = templateApp.getTemplateQuery(5);
			tempQuery5.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery6 = templateApp.getTemplateQuery(6);
			tempQuery6.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery7 = templateApp.getTemplateQuery(7);
			tempQuery7.setParameter(1, userID);
			final EmailTemplateQuery tempQuery8 = templateApp.getTemplateQuery(8);
			tempQuery8.setParameter(1, applicationId);
			templateApp.configMailAlert();

			// Mail to CEAR Group Begins
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			String[] keepInformToData = null;
			final Object[][] cearObj = model.getSqlModel().getSingleResult(" SELECT  NVL(APP_EMAIL_ID,'') AS APPROVER_ID FROM HRMS_D1_APPROVAL_SETTINGS " + 
										  " WHERE APP_SETTINGS_TYPE = 'S'  AND APP_EMAIL_ID IS NOT NULL");
			final String[] attachment = new String[1];
			attachment[0] = path + fileanme;

			if (cearObj != null && cearObj.length > 0) {
				templateApp.sendApplicationMailToGroups(cearObj, attachment);
			}
			// Mail to CEAR Group Ends

			final List<String> addToList = new ArrayList<String>();
			// Previous Approver
			final String otherApproverQuery = "SELECT DISTINCT EXP_APPROVER_CODE FROM HRMS_D1_CAPITALEXP_PATH  WHERE EXP_APPROVER_CODE NOT IN (" + this.capApprBean.getUserEmpId() + ")" + 
											  " EXP_CODE = " + applicationId;
			final Object[][] otherApproverQueryObj = model.getSqlModel().getSingleResult(otherApproverQuery);

			if (otherApproverQueryObj != null && otherApproverQueryObj.length > 0) {
				for (int i = 0; i < otherApproverQueryObj.length; i++) {
					addToList.add(String.valueOf(otherApproverQueryObj[i][0]));
				}
			}

			keepInformToData = new String[addToList.size()];
			int count = 0;
			for (int i = 0; i < addToList.size(); i++) {
				keepInformToData[count] = addToList.get(i);
				count++;
			}
			templateApp.sendApplMailWithAttachmentToKeepInf(keepInformToData, attachment);
			if (!"".equals(fileanme)) {
				templateApp.sendApplMailWithAttachment(attachment);
			} else {
				templateApp.sendApplicationMail();
			}
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to send back application.
	 * @return String
	 */
	public String sendBackApplication() {
		final String applicationId = this.capApprBean.getCapitalExpID();
		final String initiatorID = this.capApprBean.getSubmittedByID();
		final String userID = this.capApprBean.getUserEmpId();
		final String status = this.capApprBean.getStatus();
		final String poNumber = this.capApprBean.getPpoNumber();
		final String poAttachment = this.capApprBean.getPpoAttachement();
		final String path = this.capApprBean.getDataPath();
		final String fileanme = this.capApprBean.getUploadFileName();
		try {
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			final boolean result = model.UpdateCapitalExpTable(this.capApprBean, status, applicationId, poNumber, poAttachment);
			model.terminate();
			if (result) {
				this.addActionMessage("Application sent back successfully.");
				this.sendOtherApproverRejectMailToInitiator(applicationId, initiatorID, userID, status, path, fileanme);
			} else {
				this.addActionMessage("Error occured while rejecting application.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return this.inputStr;
	}

	/**Used to forward application.
	 * @return String
	 */
	public String forwardApplication() {
		final String applicationId = this.capApprBean.getCapitalExpID();
		final String previousApproverID = this.capApprBean.getUserEmpId();
		final String forwardToApproverID = this.capApprBean.getForwardedApproverID();
		final String status = this.capApprBean.getStatus();
		try {
			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			final boolean result = model.updateApproverAndStatus(this.capApprBean, status, applicationId);
			model.terminate();
			if (result) {
				this.addActionMessage("Application forwarded successfully.");
				final String path = this.capApprBean.getDataPath();
				final String fileanme = this.capApprBean.getUploadFileName();
				this.sendMailToNextApprover(applicationId, previousApproverID, forwardToApproverID, status, path, fileanme);
			} else {
				this.addActionMessage("Error occured while forwarding application.");
			}
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in input() : " + e);
		}
		this.input();
		return this.inputStr;
	}

	/**Return back to list page.
	 * @return String
	 */
	public String backToList() {
		this.input();
		return this.inputStr;
	}

	// 
	/**MAIL From Previous Approver To Next Approver Begins.
	 * @param applicationId : Application ID
	 * @param forwardToApproverID : forward to employee Id
	 * @param previousApproverID : previous approver id
	 * @param status : application status
	 * @param path : uploaded file path
	 * @param fileanme : uploaded file name
	 */
	private void sendMailToNextApprover(final String applicationId,
			final String previousApproverID, final String forwardToApproverID,
			final String status, final String path, final String fileanme) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CAPITAL EXPENDITURE AUTHORIZATION MAIL FORWARD TO NEXT APPROVER");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery tempQuery1 = templateApp.getTemplateQuery(1);
			tempQuery1.setParameter(1, previousApproverID);
			final EmailTemplateQuery tempQuery2 = templateApp.getTemplateQuery(2);
			tempQuery2.setParameter(1, forwardToApproverID);
			final EmailTemplateQuery tempQuery3 = templateApp.getTemplateQuery(3);
			tempQuery3.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery4 = templateApp.getTemplateQuery(4);
			tempQuery4.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery5 = templateApp.getTemplateQuery(5);
			tempQuery5.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery6 = templateApp.getTemplateQuery(6);
			tempQuery6.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery7 = templateApp.getTemplateQuery(7);
			tempQuery7.setParameter(1, previousApproverID);
			final EmailTemplateQuery tempQuery8 = templateApp.getTemplateQuery(8);
			tempQuery8.setParameter(1, applicationId);

			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);

			final String query = "SELECT  DISTINCT EXP_APPROVER_CODE  FROM HRMS_D1_CAPITALEXP_PATH WHERE EXP_APPROVER_CODE NOT IN (" + this.capApprBean.getUserEmpId()	+ ") " + 
							" AND EXP_CODE = " + applicationId;
			final Object[][] queryObj = model.getSqlModel().getSingleResult(query);
			String[] empData = null;
			final String[] attachment = new String[1];
			attachment[0] = path + fileanme;
			if (queryObj != null && queryObj.length > 0) {
				empData = new String[queryObj.length + 1];
				for (int i = 0; i < queryObj.length; i++) {
					empData[i] = String.valueOf(queryObj[i][0]);
				}
				empData[queryObj.length] = this.capApprBean.getSubmittedByID();
				templateApp.configMailAlert();
				if ("".equals(fileanme)) {
					templateApp.sendApplMailWithAttachmentToKeepInf(empData, null);
				} else {
					templateApp.sendApplMailWithAttachmentToKeepInf(empData, attachment);
				}

			} else {
				templateApp.configMailAlert();
				if ("".equals(fileanme)) {
					templateApp.sendApplMailWithAttachmentToKeepInf(new String[] {this.capApprBean.getSubmittedByID() }, null);
				} else {
					templateApp.sendApplMailWithAttachmentToKeepInf(new String[] {this.capApprBean.getSubmittedByID() }, attachment);
				}
			}

			if (!"".equals(fileanme)) {

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
	 * a> Mail to Initiator only if Finance approver sent-back.
	 * b> Mail to Initiator KeepInformTo : previous approver, other finance approver if
	 * Finance approver Reject/Approve BEGINS
	 * 
	  * @param applicationId : Application ID
	 * @param initiatorID : initiator employee Id
	 * @param userID : current user id
	 * @param status : application status
	 * @param path : uploaded file path
	 * @param fileanme : uploaded file name
	 * @param poAttachment : PO attached file
	 */
	private void sendMailToInitiator(final String applicationId, final String initiatorID,
			final String userID, final String status, final String path, final String fileanme,
			final String poAttachment) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CAPITAL EXPENDITURE AUTHORIZATION MAIL APPROVE/REJECT/SENT-BACK");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery tempQuery1 = templateApp.getTemplateQuery(1);
			tempQuery1.setParameter(1, userID);
			final EmailTemplateQuery tempQuery2 = templateApp.getTemplateQuery(2);
			tempQuery2.setParameter(1, initiatorID);
			final EmailTemplateQuery tempQuery3 = templateApp.getTemplateQuery(3);
			tempQuery3.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery4 = templateApp.getTemplateQuery(4);
			tempQuery4.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery5 = templateApp.getTemplateQuery(5);
			tempQuery5.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery6 = templateApp.getTemplateQuery(6);
			tempQuery6.setParameter(1, applicationId);
			final EmailTemplateQuery tempQuery7 = templateApp.getTemplateQuery(7);
			tempQuery7.setParameter(1, userID);
			final EmailTemplateQuery tempQuery8 = templateApp.getTemplateQuery(8);
			tempQuery8.setParameter(1, applicationId);
			templateApp.configMailAlert();

			final CapitalExpenditureApprovalModel model = new CapitalExpenditureApprovalModel();
			model.initiate(context, session);
			String[] keepInformToData = null;
			final String[] attachment = new String[2];
			attachment[0] = path + fileanme;
			attachment[1] = path + poAttachment;

			if (!status.equals(this.bStr)) {
				// Mail CC to CEAR Group Begins
				final String query = " SELECT NVL(APP_EMAIL_ID,'') AS APPROVER_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'S' AND APP_EMAIL_ID IS NOT NULL ";
				final Object[][] cearDataObj = model.getSqlModel().getSingleResult(query);
				if (cearDataObj != null && cearDataObj.length > 0) {
					templateApp.sendApplicationMailToGroups(cearDataObj, attachment);
				}
				// Mail CC to CEAR Group Ends

				final List<String> addToList = new ArrayList<String>();
				// Previous Approver
				final String otherApproverQuery = "SELECT  DISTINCT EXP_APPROVER_CODE FROM HRMS_D1_CAPITALEXP_PATH WHERE EXP_APPROVER_CODE NOT IN (" + this.capApprBean.getUserEmpId() + ") " + 
											" AND EXP_CODE = " + applicationId;
				final Object[][] otherApproverQueryObj = model.getSqlModel().getSingleResult(otherApproverQuery);
				if (otherApproverQueryObj != null && otherApproverQueryObj.length > 0) {
					for (int i = 0; i < otherApproverQueryObj.length; i++) {
						addToList.add(String.valueOf(otherApproverQueryObj[i][0]));
					}
				}

				keepInformToData = new String[addToList.size()];
				int count = 0;
				for (int i = 0; i < addToList.size(); i++) {
					keepInformToData[count] = addToList.get(i);
					count++;
				}
			} // End of if status

			if (!status.equals(this.bStr)) {
				templateApp.sendApplMailWithAttachmentToKeepInf(keepInformToData, attachment);
			}
			if (!"".equals(fileanme)) {
				templateApp.sendApplMailWithAttachment(attachment);
			} else {
				templateApp.sendApplicationMail();
			}
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to view attached file.
	 */
	public void viewAttachedProof() {
		try {
			final String uploadFileName = request.getParameter("uploadFileName");
			this.viewUploadedDocument(uploadFileName);
		} catch (final Exception e) {
			this.logger.error("Exception in viewAttachedProof in action:" + e);
		}
	}
	
	/**Used to view uploaded file.
	 */
	public void viewUploadedFile() {
		try {
			final String uploadFileName = request.getParameter("uploadFileName");
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
