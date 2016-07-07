package org.struts.action.helpdesk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.paradyne.bean.helpdesk.HelpDesk;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.helpdesk.HelpdeskModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph 16-04-2010 HelpDeskAction class to save, update, delete
 *         and view any help desk application
 */
public class HelpDeskAction extends ParaActionSupport {
	HelpDesk helpdesk;
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HelpDeskAction.class);

	public void prepare_local() throws Exception {
		helpdesk = new HelpDesk();
		helpdesk.setMenuCode(382);
	}

	public Object getModel() {
		return helpdesk;
	}

	public HelpDesk getHelpdesk() {
		return helpdesk;
	}

	public void setHelpdesk(HelpDesk helpdesk) {
		this.helpdesk = helpdesk;
	}

	/*
	 * method name : prepare_withLoginProfileDetails purpose : to retrieve
	 * application date and employee details for general user return type : void
	 * parameter : none
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "\\HelpDesk\\" + poolName
				+ "\\";
		helpdesk.setDataPath(dataPath);

	}

	@Override
	/**
	 * SETS ALL PENDING APPLICATIONS LIST
	 */
	public String input() {
		try {
			reset();

			HelpdeskModel model = new HelpdeskModel();
			String listtype = request.getParameter("listType");
			model.initiate(context, session);
			// CALLS LIST OF PENDING APPLICATIONS
			if (listtype != null && listtype.equals("closed")) {
				model.getClosedList(helpdesk, request, helpdesk.getUserEmpId());
				helpdesk.setListType("closed");

			} else {
				model.getPendingRequests(helpdesk, request, helpdesk
						.getUserEmpId());
				helpdesk.setListType("pending");
			}
			helpdesk.setAppFor("");
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			logger.error("Exception in input method : " + e);
		}// END TRY-CATCH BLOCK
		if (helpdesk.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (helpdesk.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return INPUT;
		}
	}

	public String viewUploadedFile() {
		try {

			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				} // end of if
				fileName = request.getParameter("templateName");
				logger.info("fileName--->>>" + fileName);
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				logger.info("extext--->>>" + ext);
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} // end of if
				else if (ext.equals("doc")) {
					mimeType = "msword";
				} // end of else if
				else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} // end of else if
				else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} // end of else
				else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} // end of else if
				else if (ext.equals("txt")) {
					mimeType = "txt";
				} // end of else if
				else if (ext.equals("gif")) {
					mimeType = "gif";
				} // end of else if
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					} // end of if
				} // end of if

				// for getting server path where configuration files are saved.
				String path = getText("data_path") + "/HelpDesk/" + poolName
						+ "/" + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					// response.setHeader("Content-type",
					// "application/"+mimeType+"");
				} // end of if
				else {
					response.setHeader("Content-type", "application/"
							+ mimeType + "");
				}

				response.setHeader("Content-disposition",
						"attachment;filename=\"" + fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				} // end of while

			} catch (FileNotFoundException e) {

				logger.error("-----in file not found catch", e);
				addActionMessage("proof document not found");
			} // end of catch
			catch (Exception e) {
				e.printStackTrace();
			} // end of catch
			finally {
				logger.info("in finally for closing");
				if (fsStream != null) {
					fsStream.close();
				} // end of if
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				} // end of if
			} // end of finally
			// return null;

		} catch (Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
		return null;
	}

	public String removeUploadFile() {

		try {
			getNavigationPanel(2);
			String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");// keep
			// informed
			// empid
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			model.removeUploadFile(srNo, proofName, helpdesk);
			setDetails();
			getDataOnRefresh();
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile--------" + e);
		}
		return SUCCESS;
	}

	/**
	 * CALLS LIST OF APPROVED APPLICATIONS
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getInProcessList() throws Exception {
		try {
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			// model.getInProcessList(helpdesk, request,
			// helpdesk.getUserEmpId());
			helpdesk.setListType("inProcess");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList : " + e);
		}
		getNavigationPanel(1);
		return INPUT;
	}

	/**
	 * CALLS LIST OF APPROVED APPLICATIONS
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getResolvedList() throws Exception {
		try {
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			// model.getResolvedList(helpdesk, request,
			// helpdesk.getUserEmpId());
			helpdesk.setListType("resolved");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList : " + e);
		}
		getNavigationPanel(1);
		return INPUT;
	}

	/**
	 * CALLS LIST OF REJECTED APPLICATIONS
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getClosedList() throws Exception {
		try {
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			model.getClosedList(helpdesk, request, helpdesk.getUserEmpId());
			helpdesk.setListType("closed");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getRejectedList : " + e);
		}// END TRY CATCH BLOCK
		getNavigationPanel(1);
		return INPUT;
	}

	/**
	 * CALLED ON ADD NEW APPLICATION
	 * 
	 * @return String
	 */
	public String addNew() {
		try {
			String source = request.getParameter("src");
			helpdesk.setSource(source);
			// prepare_withLoginProfileDetails();
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			helpdesk.setAppDate(sysDate);
			request.setAttribute("radioStatus", "S");
			model.getInitiatorDetails(helpdesk, helpdesk.getUserEmpId());
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in addNew method : " + e);
		}// END TRY-CATCH BLOCK
		getNavigationPanel(2);
		// RETURNS APPLICATION JSP
		return SUCCESS;
	}

	/**
	 * This method is used to set the travel_application_for flag in the request
	 * to make sections Travel Arrangement/Lodging Details/Local Conveyance
	 * Details visible
	 */
	public void setAppForInRequest() {
		if (helpdesk.getHAppFor().trim().equals("S")) {
			try {
				prepare_withLoginProfileDetails();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("appFor", "S");
		}
		if (helpdesk.getHAppFor().trim().equals("C")) {
			request.setAttribute("appFor", "C");
			helpdesk.setEmpId("");
			helpdesk.setEmpName("");
			helpdesk.setBranchCode("");
			helpdesk.setBranchName("");
			helpdesk.setDesgCode("");
			helpdesk.setDesgName("");
			helpdesk.setDeptCode("");
			helpdesk.setDeptName("");
			helpdesk.setEmpToken("");
		}
		if (helpdesk.getHAppFor().trim().equals("O")) {
			request.setAttribute("appFor", "O");
			helpdesk.setEmpId("");
			helpdesk.setEmpName("");
			helpdesk.setBranchCode("");
			helpdesk.setBranchName("");
			helpdesk.setDesgCode("");
			helpdesk.setDesgName("");
			helpdesk.setDeptCode("");
			helpdesk.setDeptName("");
			helpdesk.setEmpToken("");
		}
	}

	public String setAppFor() throws Exception {
		getNavigationPanel(2);
		String flag = request.getParameter("flag");
		logger.info("Flag   : " + flag);
		if (flag.equals("S"))
			helpdesk.setAppType("self");
		else if (flag.equals("O"))
			helpdesk.setAppType("others");
		else
			helpdesk.setAppType("client");
		setAppForInRequest();
		return SUCCESS;
	}

	public String f9Employee() {
		try {
			String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , EMP_TOKEN , "
					+ "	CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID  FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
					+ "	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
					+ "	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
					+ "	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
					+ " WHERE EMP_STATUS='S'";

			query += "	ORDER BY EMP_ID";

			String[] headers = { getMessage("employee"),
					getMessage("employee.id") };

			String[] headerWidth = { "80", "20" };

			String[] fieldNames = { "helpdesk.empName", "helpdesk.empToken",
					"helpdesk.branchName", "helpdesk.deptName",
					"helpdesk.desigName", "helpdesk.empId" };

			int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

			String submitFlag = "true";

			String submitToMethod = "HelpDesk_setApplicationDate.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return null;
		}// END TRY-CATCH BLOCK
	}

	public String setApplicationDate() {
		try {
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			helpdesk.setAppDate(sysDate);
			setDetails();
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Exception in setApplicationDate" + e);
			e.printStackTrace();
		}// END TRY-CATCH BLOCK
		return SUCCESS;
	}// end of setApplicationDate

	public void setDetails() {
		logger.info("helpdesk.getHAppFor()   : " + helpdesk.getHAppFor());
		if (helpdesk.getHAppFor().equals("S"))
			helpdesk.setAppType("self");
		else if (helpdesk.getHAppFor().equals("O"))
			helpdesk.setAppType("others");
		else
			helpdesk.setAppType("client");
		if (helpdesk.getHAppFor().trim().equals("S")) {
			try {
				prepare_withLoginProfileDetails();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("appFor", "S");
		}
		if (helpdesk.getHAppFor().trim().equals("C")) {
			request.setAttribute("appFor", "C");
		}
		if (helpdesk.getHAppFor().trim().equals("O")) {
			request.setAttribute("appFor", "O");
		}

	}

	/**
	 * Method to select the department. *
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT NVL(HELPDESK_DEPT.DEPT_NAME,' '), HELPDESK_DEPT.DEPT_CODE FROM HELPDESK_DEPT "
				+ " WHERE  HELPDESK_DEPT.IS_ACTIVE='Y' "
				+ " ORDER BY  HELPDESK_DEPT.DEPT_CODE";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("req.dept") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "reqDeptName", "reqDeptCode" };

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
		helpdesk.setMasterMenuCode(1008);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9reqType() {

		try {
			String query = " SELECT NVL(REQUEST_TYPE_NAME,' '), REQUEST_TYPE_ID "
					+ " FROM HELPDESK_REQUEST_TYPE "
					+ " WHERE REQUEST_TYPE_DEPT="
					+ helpdesk.getReqDeptCode()
					+ " ORDER BY REQUEST_TYPE_ID ";
			String[] headers = { getMessage("req.type") };
			String[] headerwidth = { "100" };
			String fieldNames[] = { "reqType", "reqTypeCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlage = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String f9subReqType() {

		try {
			String query = " SELECT NVL(REQUEST_SUBTYPE_NAME,' '),  REQUEST_SUBTYPE_ID , IS_MANAGER_APPROVAL"
					+ " FROM HELPDESK_REQUEST_SUBTYPE "
					+ " WHERE REQUEST_TYPE_ID = " + helpdesk.getReqTypeCode();
			String[] headers = { "Problem/Request Type" };
			String[] headerwidth = { "50" };
			String fieldNames[] = { "subReqType", "subReqTypeCode",
					"isManagerApproval" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlage = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String getAssetTypeByRequestTypeId() {
		try {
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);

			String query = " SELECT ASSET_CATEGORY_CODE, NVL(ASSET_CATEGORY_NAME,'') FROM HRMS_ASSET_CATEGORY "
					+ " INNER JOIN HELPDESK_REQUEST_SUBTYPE ON (HELPDESK_REQUEST_SUBTYPE.ASSET_TYPE_CODE = HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE)"
					+ " WHERE REQUEST_TYPE_ID = "
					+ helpdesk.getReqTypeCode()
					+ "  ORDER BY ASSET_CATEGORY_CODE ";
			Object[][] assetObj = model.getSqlModel().getSingleResult(query);
			if (assetObj != null && assetObj.length > 0) {
				helpdesk.setAssetTypeCode(String.valueOf(assetObj[0][0]));// asset
				// code
				helpdesk.setAssetType(String.valueOf(assetObj[0][1]));// asset
				// name
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDataOnRefresh();
		getNavigationPanel(2);
		helpdesk.setEnableAll("Y");
		return "success";
	}

	public String f9SubAssetTypes() throws Exception {

		try {
			String query = " SELECT ASSET_SUBTYPE_CODE, NVL(ASSET_SUBTYPE_NAME,' ') FROM HRMS_ASSET_SUBTYPE "
					+ " WHERE ASSET_CATEGORY_CODE ="
					+ helpdesk.getAssetTypeCode()
					+ " ORDER BY ASSET_SUBTYPE_CODE ";
			String[] headers = { getMessage("req.type"), "Asset Subtype Name" };
			String[] headerwidth = { "20", "80" };
			String fieldNames[] = { "assetSubTypeCode", "assetSubType" };
			int[] columnIndex = { 0, 1 };
			String submitFlage = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String addMultipleFiles() {

		try {
			String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			model.displayIteratorValueForUploadProof(srNo, proofName, helpdesk);
			model.setProofList(srNo, proofName, helpdesk);
			String emploId = request.getParameter("emploId");
			logger.info("emploId  :: " + emploId);
			// model.getEmployeeDetails(emploId, helpdesk);
			setDetails();
			helpdesk.setUploadFileName("");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in addMultipleProof--------" + e);
			e.printStackTrace();
		}
		getDataOnRefresh();
		getNavigationPanel(2);
		return SUCCESS;
	}

	/*
	 * method name : save purpose : to add new record or to modify the existing
	 * record return type : String parameter : none
	 */

	public String save() {
		try {
			boolean result = false;
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			String status = request.getParameter("checkStatus");
			String[] proofName = request.getParameterValues("proofName");
			Object[][] empFlow = null;
			String message = "";
			if (helpdesk.getIsManagerApproval().equals("Y")) {//helpdesk.getIsManagerApproval()
				empFlow = generateEmpFlow(helpdesk.getEmpId(), "Help", 1);
			} else {
				String ownerQuery = " SELECT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR "
						+ " WHERE DEPT_CODE = " + helpdesk.getReqDeptCode() + " AND REQ_TYPE_CODE = "
						+ helpdesk.getReqTypeCode() + " AND BRANCH_CODE = ( " + " SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "	+ helpdesk.getUserEmpId() + ")";
				empFlow = model.getSqlModel().getSingleResult(ownerQuery);
			}
			if (empFlow != null && empFlow.length > 0) {
				if (helpdesk.getHelpcode().equals("")) {
					// IF CODE IS BLANK, SAVE RECORD
					result = model.save(helpdesk, empFlow, status, proofName);
					if (result) {
						String maxQuery = " SELECT MAX(REQUEST_ID) FROM HELPDESK_REQUEST_HDR";
						Object[][] maxObj = model.getSqlModel()
								.getSingleResult(maxQuery);
						helpdesk.setHelpcode(String.valueOf(maxObj[0][0]));
						String str = (empFlow != null && empFlow.length > 0) ? String
								.valueOf(empFlow[0][0])
								: "0";
						if (status.equals("O")) {
							model.saveDetails(helpdesk, str, status);
						}
						getNavigationPanel(1);
						try {
							if (status.equals("O")) {

								/**
								 * Remove Process manager alert entry from
								 * mypage
								 */
								
								  try { MypageProcessManagerAlertsModel
								  processAlerts = new
								  MypageProcessManagerAlertsModel();
								  processAlerts.initiate(context, session);
								  processAlerts.removeProcessAlert(helpdesk
								 .getHelpcode(), "HelpDesk");
								  processAlerts.terminate(); } 
								  catch (Exception  e) 
								  { e.printStackTrace(); }
								 

								String module = "HelpDesk";

								try {
									String applnID = helpdesk.getHelpcode();
									String empId = helpdesk.getEmpId();
									String requesterId = helpdesk
											.getRequestForEmpId();
									String managerApproval = helpdesk
											.getIsManagerApproval();
									String deptCode = helpdesk.getReqDeptCode();
									String typeCode = helpdesk.getReqTypeCode();
									String userId = helpdesk.getUserEmpId();

									sendRequestMail(applnID, empId,
											requesterId, str, managerApproval,
											deptCode, typeCode, userId, module);
									
									addActionMessage("Request sent for approval successfully.\nRequest ID:-"
											+ helpdesk.getReqToken().trim());
								} catch (Exception e) {
									e.printStackTrace();
								}

							} else {
								  try { 
									  sendProcessManagerAlertDraft(); 
								  } catch(Exception e)
								  {
									  e.printStackTrace();
								  }
								  
								  

								addActionMessage("Your request has been saved successfully.");
							}
							// RETURNS TO LIST IF MAIL SENT
							//prepare_withLoginProfileDetails();
							//input();
							if (helpdesk.getSource().equals("mymessages")) {
								return "mymessages";
							} else if (helpdesk.getSource().equals("myservices")) {
								return "serviceJsp";
							}
							else{
							return input();
							}

						} catch (Exception e) {
							logger.error("Exception in save");
						}

					} // END IF RESULT
					else {
						addActionMessage("Your request could not be saved.");
						getNavigationPanel(2);
						reset();
					}// END ELSE (IF NOT RESULT)

				} else {// UPDATE RECORD

					String applnID = helpdesk.getHelpcode();

					// Updated by Anantha lakshmi

					String mgrAppr = "SELECT IS_MANAGER_APPROVAL FROM HELPDESK_REQUEST_SUBTYPE "
							+ " INNER JOIN HELPDESK_REQUEST_HDR ON (HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID=HELPDESK_REQUEST_HDR.REQUEST_SUBTYPE)"
							+ " WHERE REQUEST_ID=" + applnID;

					Object[][] mgrApprObj = model.getSqlModel()
							.getSingleResult(mgrAppr);
					if (mgrApprObj != null && mgrApprObj.length > 0) {
						helpdesk.setIsManagerApproval(String
								.valueOf(mgrApprObj[0][0]));
					}

					if (helpdesk.getIsManagerApproval().equals("Y")) {
						empFlow = generateEmpFlow(helpdesk.getEmpId(), "Help",
								1);
					} else {
						String ownerQuery = " SELECT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR "
								+ " WHERE DEPT_CODE = "
								+ helpdesk.getReqDeptCode()
								+ " AND REQ_TYPE_CODE = "
								+ helpdesk.getReqTypeCode()
								+ " AND BRANCH_CODE = ( "
								+ " SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "
								+ helpdesk.getUserEmpId() + ")";
						empFlow = model.getSqlModel().getSingleResult(
								ownerQuery);
					}
					String forwardedTo = (empFlow != null && empFlow.length > 0) ? String
							.valueOf(empFlow[0][0])
							: "0";
					result = model.update(helpdesk, forwardedTo, status,
							proofName);
					if (result) {
						getNavigationPanel(1);
						try {
							if (status.equals("O")) {
								model
										.saveDetails(helpdesk, forwardedTo,
												status);
								String module = "HelpDesk";
								/**
								 * Remove Process manager alert entry from
								 * mypage
								 */
								
								  try { MypageProcessManagerAlertsModel
								  processAlerts = new
								  MypageProcessManagerAlertsModel();
								  processAlerts.initiate(context, session);
								  processAlerts.removeProcessAlert(helpdesk
								  .getHelpcode(), "HelpDesk");
								  processAlerts.terminate(); }
								  catch (Exception  e) 
								  { e.printStackTrace(); }
								 

								try {

									String empId = helpdesk.getEmpId();

									String requesterId = helpdesk
											.getRequestForEmpId();

									String managerApproval = helpdesk
											.getIsManagerApproval();

									String deptCode = helpdesk.getReqDeptCode();

									String typeCode = helpdesk.getReqTypeCode();

									String userId = helpdesk.getUserEmpId();


									sendRequestMail(applnID, empId,
											requesterId, forwardedTo,
											managerApproval, deptCode,
											typeCode, userId, module);
									

									addActionMessage("Request sent for approval successfully.\nRequest ID:- "
											+ helpdesk.getReqToken().trim());

								} catch (Exception e) {
									e.printStackTrace();
								}

							} else {

								sendProcessManagerAlertDraft();

								addActionMessage("Your request has been updated successfully.");
							}
							// RETURNS TO LIST IF MAIL SENT
							//prepare_withLoginProfileDetails();
							//input();
							if (helpdesk.getSource().equals("mymessages")) {
								return "mymessages";
							} else if (helpdesk.getSource().equals("myservices")) {
								return "serviceJsp";
							}
							else{
							return input();
							}

						} catch (Exception e) {
							logger.error("Exception in save");
						}
					}
				}
			} else {
				getNavigationPanel(1);
				addActionMessage("Reporting structure not defined for the employee\n"
						+ helpdesk.getEmpName()
						+ "\nPlease contact HR manager !");
				return input();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (helpdesk.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (helpdesk.getSource().equals("myservices")) {
			return "serviceJsp";
		}
		else{
		return INPUT;
		}

	}


	public String back() {
		try {
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			String listtype = request.getParameter("listType");

			// SETS LIST OF ALL PENDING APPLICATIONS
			if (listtype != null && listtype.equals("closed")) {
				model.getClosedList(helpdesk, request, helpdesk.getUserEmpId());
				helpdesk.setListType("closed");

			} else {
				model.getPendingRequests(helpdesk, request, helpdesk
						.getUserEmpId());
				helpdesk.setListType("pending");
			}

			model.terminate();
			getNavigationPanel(1);
			helpdesk.setEnableAll("Y");
		} catch (Exception e) {
			logger.error("Exception in back method : " + e);
		}// END TRY-CATCH BLOCK
		// RETURNS APPLICATIONS LIST JSP
		return INPUT;
	}



	public String reopenCloseRequest() {
		HelpdeskModel model = new HelpdeskModel();
		model.initiate(context, session);
		try {
			helpdesk.setReopenFlag(true);
			String reqAppCode = request.getParameter("reqAppCode");
			String openClose = request.getParameter("reopenCloseStatus");
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			helpdesk.setAppDate(sysDate);
			model.getEmployeeDetails(reqAppCode, helpdesk, request);
			model.setActivityLogDetails(helpdesk);
			if (openClose.equals("Open")) {
				getNavigationPanel(4);
			} else {
				getNavigationPanel(5);
			}
			helpdesk.setIsReqApp("false");
			helpdesk.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String reopenRequest() {
		boolean result = false;
		try {
			String status = "D";
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			Object[][] empFlow = null;
			String message = "";
			System.out.println("IS MANANGER FLAG IS ++++++++++++>>>>>>>>>>"
					+ helpdesk.getIsManagerApproval());

			String ownerQuery = "SELECT REQUEST_PENDING_WITH FROM  HELPDESK_REQUEST_HDR "
					+ " WHERE  REQUEST_ID=" + helpdesk.getHelpcode();
			empFlow = model.getSqlModel().getSingleResult(ownerQuery);
			String forwardedTo = (empFlow != null && empFlow.length > 0) ? String
					.valueOf(empFlow[0][0])
					: "0";
			result = model.updateHdrWhileProcess(helpdesk, forwardedTo, status);
			sendRequestReopenMail(helpdesk.getHelpcode(), helpdesk.getEmpId(),
					helpdesk.getRequestForEmpId(), forwardedTo, helpdesk
							.getIsManagerApproval(), helpdesk.getReqDeptCode(),
					helpdesk.getReqTypeCode(), helpdesk.getUserEmpId());
			if (result) {
				addActionMessage("Your helpdesk request has been reopened.");
			} else {
				addActionMessage("Your helpdesk request could not be reopened.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (helpdesk.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (helpdesk.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return input();
		}
	}

	public String closeRequest() {
		boolean result = false;
		try {
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			result = model.closeHelpdeskRequest(helpdesk);
			
						
			if (result) {
				
				String link = "/help/HelpDesk_retrieveDetails.action";
				String linkParam = "reqAppCode="+helpdesk.getRequestId(); 
				String message ="";
				Properties alertProp;
				FileInputStream alertFin;
				alertFin = new FileInputStream(getText("data_path") + "\\Alerts\\alertLinks.properties");
				alertProp = new Properties();
				alertProp.load(alertFin);
				System.out.println("Employee--------------------->"+helpdesk.getEmpName());
				 message = alertProp.getProperty("closeAlertMessage");
					message = message.replace("<#EMP_NAME#>", helpdesk.getEmpName());
					message = message.replace("<#REQ_TOKEN#>", helpdesk.getReqToken());
					
					
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				Object[][] approvarObj = model.getSqlModel().getSingleResult("SELECT HELPDESK_REQUEST_HDR.REQUEST_PENDING_WITH FROM HELPDESK_REQUEST_HDR WHERE REQUEST_ID="+helpdesk.getRequestId());
				if(approvarObj!=null && approvarObj.length >0){ 
				template.sendProcessManagerAlertWithdraw(String.valueOf(approvarObj[0][0]), "HelpDesk", "I",
						helpdesk.getRequestId(), "1", linkParam, link, message,
						"Processed", helpdesk.getEmpId(), String.valueOf(approvarObj[0][0]), "", helpdesk.getEmpId());
				}
				template.terminate();
				addActionMessage("Your helpdesk request has been closed.");
				helpdesk.setListType("closed");
			} else {
				addActionMessage("Your helpdesk request could not be closed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		if (helpdesk.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (helpdesk.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return input();
		}
	}

	public void setSavedRequests() {
		try {
			String reqId = helpdesk.getHelpcode();
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			model.setSavedRequests(helpdesk, reqId);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setSavedRequests------" + e);
			e.printStackTrace();
		}// END TRY CATCH BLOCK
	}

	public String retrieveDetails() {

		String source = request.getParameter("src");
		helpdesk.setSource(source);
		
		HelpdeskModel model = new HelpdeskModel();
		model.initiate(context, session);
		try {
			String reqAppCode = request.getParameter("reqAppCode");
			
			
			System.out.println("reqAppCode  " + reqAppCode);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			helpdesk.setAppDate(sysDate);
			model.getEmployeeDetails(reqAppCode, helpdesk, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (helpdesk.getHiddenStatus().equals("D")
				|| helpdesk.getReqManagerStatus()
						.equals("Sent back by manager")) {
			getNavigationPanel(2);
		} else if (helpdesk.getHiddenStatus().equals("O")
				|| helpdesk.getHiddenStatus().equals("I")
				|| helpdesk.getHiddenStatus().equals("N")) {
			model.setActivityLogDetails(helpdesk);
			getNavigationPanel(3);
			helpdesk.setIsReqApp("false");
			helpdesk.setEnableAll("N");
		} else if (helpdesk.getHiddenStatus().equals("R")) {
			model.setActivityLogDetails(helpdesk);
			getNavigationPanel(4);
			helpdesk.setReopenFlag(true);
			helpdesk.setIsReqApp("false");
			helpdesk.setEnableAll("N");
		} else {
			model.setActivityLogDetails(helpdesk);
			getNavigationPanel(3);
			helpdesk.setIsReqApp("false");
			helpdesk.setEnableAll("N");
		}
		model.terminate();

		return SUCCESS;

	}
	
	public String retrieveForApproval() {
		try {
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			// APPLICATION CODE
			String reqAppNo = request.getParameter("reqAppNo");
			// STATUS
			String reqStatus = request.getParameter("reqStatus");
			logger.info("reqStatus   : " + reqStatus);
			// SETS APPLICATION CODE
			helpdesk.setHelpcode(reqAppNo);
			helpdesk.setIsApprove("true");
			// APPLICANT NAME F9 WINDOW FLAG
			helpdesk.setIsReqApp("false");
			// GETTING SAVED EXTRA WORK DETAILS
			setSavedRequests();
			setDetails();

			// SETS EMPLOYEE CODE
			String empCode = " SELECT REQUEST_POSTED_FOR FROM HELPDESK_REQUEST_HDR "
					+ " WHERE REQUEST_ID= '" + helpdesk.getHelpcode() + "'";
			Object empCodeObj[][] = model.getSqlModel()
					.getSingleResult(empCode);
			if (empCodeObj != null && empCodeObj.length > 0) {
				helpdesk.setEmpId(String.valueOf(empCodeObj[0][0]));
			}
			logger.info("helpdesk.getEmpId()   : " + helpdesk.getEmpId());
			// FOR SETTING LEVEL AND STATUS
			model.getRecord(helpdesk);
			// GETTING EMPLOYEE INFO
			model.showEmp(helpdesk);

			// SETS SLA TABLE
			model.setSLAList(helpdesk);

			// SETS REQUEST DTL TABLE
			model.setReqList(helpdesk);

			// SET STATUS LIST
			model.setStatus(helpdesk);

			logger.info("listtype     :: " + helpdesk.getListType());
			if (helpdesk.getListType().equals("assigned")) {
				getNavigationPanel(9);
			} else {
				getNavigationPanel(8);
			}
			logger.info("emp name in action  : " + helpdesk.getEmpName());
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in retriveForApproval : " + e);
			e.printStackTrace();
		}
		
		return "VIEW_TRUE";
	}// end of retrieveForApproval

	/*
	 * method name : delete purpose : to delete the selected record return type :
	 * String parameter : none
	 */
	public String delete() throws Exception {
		HelpdeskModel model = new HelpdeskModel();
		model.initiate(context, session);
		boolean result;
		result = model.delete(helpdesk);
		model.terminate();
		if (result) {
			addActionMessage(getText("delMessage", ""));
		} // end of if
		else {
			addActionMessage("Record can not be deleted");
		} // end of else
		reset();
		back();
		return SUCCESS;
	}

	/*
	 * method name : reset purpose : to reset all the form fields and set all
	 * values to empty strings return type : String parameter : none
	 */
	public String reset() {
		helpdesk.setHAppFor("S");
		setAppForInRequest();
		helpdesk.setAppType("self");

		helpdesk.setHelpcode("");
		helpdesk.setSubject("");
		helpdesk.setComments("");
		helpdesk.setReqType("");
		helpdesk.setReqTypeCode("");
		helpdesk.setSubReqType("");
		helpdesk.setSubReqTypeCode("");
		helpdesk.setReqDeptCode("");
		helpdesk.setReqDeptName("");
		try {
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			logger.error(e);
		}
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String process() {
		boolean result = false;
		HelpdeskModel model = new HelpdeskModel();
		model.initiate(context, session);
		String status = helpdesk.getNewStatus();
		logger.info("status in process   : " + status);
		String forwardedTo = "";
		if (helpdesk.getAssToEmpCode().equals("")) {
			forwardedTo = helpdesk.getUserEmpId();
		} else
			forwardedTo = helpdesk.getAssToEmpCode();
		result = model.updateHdrWhileProcess(helpdesk, forwardedTo, status);
		if (result) {
			String appFor = " SELECT REQUEST_APPLIED_FOR,REQUEST_PENDING_WITH,REQUEST_STATUS,REQUEST_POSTED_BY FROM HELPDESK_REQUEST_HDR WHERE REQUEST_ID="
					+ "'" + helpdesk.getHelpcode() + "'";
			Object[][] appObj = model.getSqlModel().getSingleResult(appFor);
			helpdesk.setHAppFor(String.valueOf(appObj[0][0]));
			helpdesk.setHiddenStatus(String.valueOf(appObj[0][2]));
			try {
				logger.info("before actionmesg");
				addActionMessage("Your helpdesk request has been sent for processing.");
				logger.info("after actionmesg");
				result = model.saveDetails(helpdesk, String
						.valueOf(appObj[0][1]), String.valueOf(appObj[0][2]));
				if (!helpdesk.getAssToEmpCode().equals("")) {
					EmailTemplateBody template_applicant = new EmailTemplateBody();
					template_applicant.initiate(context, session);

					template_applicant
							.setEmailTemplate("HELPDESK_3_Request Forwarded to Request Owner Mail");
					template_applicant.getTemplateQueries();

					EmailTemplateQuery templateQueryApp1 = template_applicant
							.getTemplateQuery(1); // FROM EMAIL
					templateQueryApp1.setParameter(1, String
							.valueOf(appObj[0][1]));

					EmailTemplateQuery templateQueryApp2 = template_applicant
							.getTemplateQuery(2); // TO EMAIL
					templateQueryApp2.setParameter(1, helpdesk
							.getAssToEmpCode());

					// Subject + Body
					EmailTemplateQuery templateQueryApp3 = template_applicant
							.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, helpdesk.getHelpcode());

					EmailTemplateQuery templateQueryApp4 = template_applicant
							.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, helpdesk.getHelpcode());

					EmailTemplateQuery templateQueryApp5 = template_applicant
							.getTemplateQuery(5);
					templateQueryApp5.setParameter(1, helpdesk.getHelpcode());

					template_applicant.configMailAlert();

					// create process alerts
					/*
					 * template_applicant.sendProcessManagerAlert(String
					 * .valueOf(appObj[0][3]), "Administration", "I",
					 * helpdesk.getHelpcode(), "1");
					 */
					// send mail
					template_applicant.sendApplicationMail();
					template_applicant.clearParameters();
					template_applicant.terminate();
				} else {
					// REQUEST RESOLVED/ACKNOWLEDGED TO REQUESTER MAIL
					EmailTemplateBody template_applicant = new EmailTemplateBody();
					template_applicant.initiate(context, session);

					template_applicant
							.setEmailTemplate("HELPDESK_4_Request Resolved/Acknowledged to Requester Mail");
					template_applicant.getTemplateQueries();

					EmailTemplateQuery templateQueryApp1 = template_applicant
							.getTemplateQuery(1); // FROM EMAIL
					templateQueryApp1.setParameter(1, String
							.valueOf(appObj[0][1]));

					EmailTemplateQuery templateQueryApp2 = template_applicant
							.getTemplateQuery(2); // TO EMAIL
					templateQueryApp2.setParameter(1, String
							.valueOf(appObj[0][3]));

					// Subject + Body
					EmailTemplateQuery templateQueryApp3 = template_applicant
							.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, helpdesk.getHelpcode());

					EmailTemplateQuery templateQueryApp4 = template_applicant
							.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, helpdesk.getHelpcode());

					template_applicant.configMailAlert();

					// create process alerts
					/*
					 * template_applicant.sendProcessManagerAlert(String
					 * .valueOf(appObj[0][3]), "Administration", "I",
					 * helpdesk.getHelpcode(), "1");
					 */
					// send mail
					template_applicant.sendApplicationMail();
					template_applicant.clearParameters();
					template_applicant.terminate();
				}
				model.terminate();
				return "processJsp";
			} catch (Exception e) {
				addActionMessage("Your helpdesk request could not be sent for processing.");
				logger.error("Exception in saving details process() : ");
				return "processJsp";
			}
		} else {
			addActionMessage("Your helpdesk request could not be sent for processing.");
			return "processJsp";
		}

	}

	/**
	 * CALLED ON BACK TO LIST
	 * 
	 * @return String
	 */
	public String backToApprovalList() {
		logger.info("in backToApprovalList  : ");
		HelpdeskModel model = new HelpdeskModel();
		model.initiate(context, session);
		String statQuery = "SELECT REQUEST_STATUS FROM HELPDESK_REQUEST_HDR "
				+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
				+ " WHERE REQUEST_ID= '" + helpdesk.getHelpcode() + "'";
		Object[][] statObj = model.getSqlModel().getSingleResult(statQuery);
		model.terminate();
		logger.info("status in back to approval  : " + statObj[0][0]);
		if (String.valueOf(statObj[0][0]).equals("R")) {
			try {
				// RETURNS TO LIST
				prepare_withLoginProfileDetails();
				input();
				return INPUT;
			} catch (Exception e) {
				logger.error("Exception in save");
				return INPUT;
			}
		} else
			return "processJsp";
	}

	/*
	 * method name : f9empaction purpose : to select an employee name return
	 * type : String parameter : none
	 */
	public String f9AssignEmployee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME, EMP_TOKEN,"
				+ " HRMS_EMP_OFFC.EMP_ID "
				+ " FROM HRMS_EMP_OFFC  "
				+ " WHERE EMP_STATUS ='S' AND EMP_ID NOT IN("
				+ helpdesk.getUserEmpId() + ") ORDER BY EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee"), getMessage("employee.id") };

		String[] headerWidth = { "80", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "assToEmpName", "assToEmpToken", "assToEmpCode" };
		//
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
	}

	/*
	 * method name : f9empaction purpose : to select an employee name return
	 * type : String parameter : none
	 */
	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME, "
				+ "HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,  NVL(HRMS_DEPT.DEPT_NAME, ' '), "
				+ "HRMS_EMP_OFFC.EMP_ID, EMP_CENTER, EMP_RANK, EMP_DEPT, NVL(HRMS_EMP_ADDRESS.ADD_PH1, ' ') "
				+ "FROM HRMS_EMP_OFFC  "
				+ "INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ "INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ "LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID AND ADD_TYPE = 'L') "
				+ "LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				// +"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE =
				// HRMS_TITLE.TITLE_CODE) "
				+ " WHERE EMP_STATUS ='S' ORDER BY EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Id", "Employee Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "helpdesk.empToken", "helpdesk.empName",
				"helpdesk.branchName", "helpdesk.desigName",
				"helpdesk.deptName", "helpdesk.empCode", "helpdesk.branchCode",
				"helpdesk.desigCode", "helpdesk.deptCode", "helpdesk.contactNo" };
		//
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

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

	public String f9otherEmployee() throws Exception {

		String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,"
				+ " EMP_ID, EMP_ID "
				+ " FROM HRMS_EMP_OFFC  "
				+ " WHERE EMP_STATUS ='S' ";
		if (helpdesk.getUserProfileDivision() != null
				&& helpdesk.getUserProfileDivision().length() > 0)
			query += "AND EMP_DIV IN (" + helpdesk.getUserProfileDivision()
					+ ")";
		query += " ORDER BY UPPER (EMP_FNAME||' '||EMP_LNAME)";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "otherEmpToken", "otherEmpName", "otherEmpId",
				"requestForEmpId" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String getDataOnRefresh() {
		try {
			logger.info("############# radio status #############"
					+ helpdesk.getReqRadio());
			request.setAttribute("radioStatus", helpdesk.getReqRadio());
			if (helpdesk.getReqRadio().equals("S")) {
				helpdesk.setInitiatorId(helpdesk.getInitiatorId());
				helpdesk.setInitiatorToken(helpdesk.getInitiatorToken());
				helpdesk.setInitiatorName(helpdesk.getInitiatorName());

			} else if (helpdesk.getReqRadio().equals("O")) {
				helpdesk.setOtherEmpId(helpdesk.getOtherEmpId());
				helpdesk.setOtherEmpToken(helpdesk.getOtherEmpToken());
				helpdesk.setOtherEmpName(helpdesk.getOtherEmpName());
			} else {
				helpdesk.setClientName(helpdesk.getClientName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		helpdesk.setEnableAll("Y");
		return "success";
	}

	public void checkReportingStructure() {
		try {
			Object[][] empFlow = null;
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);
			if (helpdesk.getIsManagerApproval().equals("Y")) {
				empFlow = generateEmpFlow(helpdesk.getUserEmpId(), "Help", 1);
				if (empFlow != null && empFlow.length > 0) {
					helpdesk.setCheckReportingStructure(String
							.valueOf(empFlow[0][0]));
				} else {
					helpdesk.setCheckReportingStructure("0");
				}
			} else {
				String ownerQuery = " SELECT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR "
						+ " WHERE DEPT_CODE = "
						+ helpdesk.getReqDeptCode()
						+ " AND REQ_TYPE_CODE = "
						+ helpdesk.getReqTypeCode()
						+ " AND BRANCH_CODE = ( "
						+ " SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "
						+ helpdesk.getUserEmpId() + ") ";
				empFlow = model.getSqlModel().getSingleResult(ownerQuery);
				if (empFlow != null && empFlow.length > 0) {
					helpdesk.setCheckReportingStructure(String
							.valueOf(empFlow[0][0]));
				} else {
					helpdesk.setCheckReportingStructure("0");
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// appStatus, level, initiatorId, isManagerAppr,
	// deptId, reqTypeId, requestId, approverComments

	public void sendRequestMail(String requestId, String initiatorId,
			String requestForId, String managerId, String isManagerAppr,
			String deptId, String reqTypeId, String userEmpId, String module) {
		try {
			
			String msgType = "A";
			String approverID =managerId;
			String applicantID =userEmpId; 
			String applnID = requestId;
			String level = "1";
			
			HelpdeskModel model = new HelpdeskModel();
			model.initiate(context, session);

			// 1. Request Submit Notification Mail to Applicant 

			EmailTemplateBody templateForRequest = new EmailTemplateBody();
			templateForRequest.initiate(context, session);
			templateForRequest
					.setEmailTemplate("Helpdesk Request Submit Notification to Applicant");
			templateForRequest.getTemplateQueries();
			try {
				// get the query as per number
				EmailTemplateQuery templateQueryForRequest1 = templateForRequest
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				// templateQueryForRequest1.setParameter(1, initiatorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForRequest2 = templateForRequest
						.getTemplateQuery(2);// To EMAIL
				templateQueryForRequest2.setParameter(1, initiatorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForRequest3 = templateForRequest
						.getTemplateQuery(3);
				templateQueryForRequest3.setParameter(1, requestId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForRequest4 = templateForRequest
						.getTemplateQuery(4);
				templateQueryForRequest4.setParameter(1, requestId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			templateForRequest.configMailAlert();
			
			String  alertlink = "/help/HelpDesk_retrieveDetails.action";
			
			String alertlinkParam = "reqAppCode=" + requestId+ "&"+"mgrStatus=A";
			
			 
			try {
				if(!helpdesk.getRadioCheck().equals("C")){
					
					System.out.println("Inside if of alert");
				templateForRequest.sendProcessManagerAlert("", module, "I",
						applnID, level, alertlinkParam, alertlink, "Pending",
						helpdesk.getRequestForEmpId(), "", "", helpdesk
								.getRequestForEmpId(), helpdesk
								.getRequestForEmpId());
			    }else{
			    	System.out.println("Inside else of alert");
			    	templateForRequest.sendProcessManagerAlert(applicantID, module, "I",
							applnID, level, alertlinkParam, alertlink, "Pending",
							helpdesk.getRequestForEmpId(), "", "","", applicantID);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			templateForRequest.sendApplicationMail();
			templateForRequest.clearParameters();
			templateForRequest.terminate();

			if (!managerId.equals("0")) {
				// 2. Request Submit Notification to Request Owner 

				EmailTemplateBody templateForRequestApproval = new EmailTemplateBody();
				templateForRequestApproval.initiate(context, session);
				templateForRequestApproval
						.setEmailTemplate("Helpdesk Request Submit Notification to Request Owner");
				templateForRequestApproval.getTemplateQueries();
				try {
					// get the query as per number
					EmailTemplateQuery templateQueryForApproval1 = templateForRequestApproval
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQueryForApproval1.setParameter(1, initiatorId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval2 = templateForRequestApproval
							.getTemplateQuery(2);// To EMAIL
					templateQueryForApproval2.setParameter(1, managerId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval3 = templateForRequestApproval
							.getTemplateQuery(3);
					templateQueryForApproval3.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval4 = templateForRequestApproval
							.getTemplateQuery(4);
					templateQueryForApproval4.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Add approval link -pass parameters to the link
			 level = "1";
				String[] link_param = new String[3];
				String[] link_label = new String[3];
				String applicationType = "HelpdeskSubmission";

				link_param[0] = applicationType + "#" + "A" + "#" + level + "#"
						+ initiatorId +"#"+requestForId+ "#" + isManagerAppr + "#" + deptId
						+ "#" + reqTypeId + "#" + requestId + "#" + "...#"
						+ userEmpId + "#" + managerId;
				link_param[1] = applicationType + "#" + "R" + "#" + level + "#"
						+ initiatorId +"#"+requestForId+"#" + isManagerAppr + "#" + deptId
						+ "#" + reqTypeId + "#" + requestId + "#" + "...#"
						+ userEmpId + "#" + managerId;
				link_param[2] = applicationType + "#" + "B" + "#" + level + "#"
						+ initiatorId +"#"+requestForId+"#" + isManagerAppr + "#" + deptId
						+ "#" + reqTypeId + "#" + requestId + "#" + "...#"
						+ userEmpId + "#" + managerId;

				link_label[0] = "Approve";
				link_label[1] = "Reject";
				link_label[2] = "Send Back";

				try {
					templateForRequestApproval.configMailAlert();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (isManagerAppr.equals("Y")) {
					templateForRequestApproval.addOnlineLink(request,
							link_param, link_label);
				}
				applnID = requestId;
				String alertLink="";
				String  linkParam="";
				
				
			 	if(isManagerAppr.equals("Y")){
				alertLink = "/help/HelpDeskApproval_retrieveDetails.action";
				linkParam = "reqAppCode=" + requestId+"&mgrStatus=P" ;
			 	}else{
			 		alertLink = "/help/HelpDeskProcess_retrieveDetails.action";
			 		linkParam = "reqAppCode=" + requestId+"&reqStatus=P"+"&listStatus=PD";
			 		
			 	}
				
				try {
					templateForRequestApproval.sendProcessManagerAlert(approverID, "HelpDesk", "A",
							applnID, level, linkParam, alertLink, "Pending",
							applicantID, "0", "", "",
							applicantID);
			      
											
				} catch (Exception e) {
					e.printStackTrace();
				}
							
				templateForRequestApproval.sendApplicationMail();
				templateForRequestApproval.clearParameters();
				templateForRequestApproval.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void sendRequestReopenMail(String requestId, String initiatorId,
			String requestForId, String managerId, String isManagerAppr,
			String deptId, String reqTypeId, String userEmpId) {
		try {
			logger.info("############# REQUEST ID ##################"
					+ requestId);
			logger.info("############# INITIATOR ID ##################"
					+ initiatorId);
			logger.info("############# REQUEST FOR ID ##################"
					+ requestForId);
			logger.info("############# MANAGER ID ##################"
					+ managerId);
			EmailTemplateBody templateForReopen = new EmailTemplateBody();
			templateForReopen.initiate(context, session);
			templateForReopen
					.setEmailTemplate("Helpdesk Request Reopen Notification to Owner");
			templateForReopen.getTemplateQueries();
			try {
				// get the query as per number
				EmailTemplateQuery templateQueryForReopen1 = templateForReopen
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQueryForReopen1.setParameter(1, initiatorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForReopen2 = templateForReopen
						.getTemplateQuery(2);// To EMAIL
				templateQueryForReopen2.setParameter(1, managerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForReopen3 = templateForReopen
						.getTemplateQuery(3);
				templateQueryForReopen3.setParameter(1, requestId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForReopen4 = templateForReopen
						.getTemplateQuery(4);
				templateQueryForReopen4.setParameter(1, requestId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForReopen5 = templateForReopen
						.getTemplateQuery(5);
				templateQueryForReopen5.setParameter(1, requestId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			MypageProcessManagerAlertsModel	  processAlerts = new  MypageProcessManagerAlertsModel();
			  processAlerts.initiate(context, session);
			  processAlerts.removeProcessAlert(helpdesk.getHelpcode(), "HelpDesk");
			  processAlerts.terminate();
			  
			templateForReopen.configMailAlert();
			
			String link = "/help/HelpDesk_retrieveDetails.action";
			String linkParam = "reqAppCode="+requestId; 
			templateForReopen.sendProcessManagerAlert(initiatorId, "HelpDesk", "A", requestId,
					"1", linkParam, link, "Reopened", initiatorId,
					"", "", "", initiatorId);
			templateForReopen.sendApplicationMail();
			templateForReopen.clearParameters();
			templateForReopen.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Added by Nilesh for My Page on 26th Dec 2011

	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "\\Alerts\\alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String applicantID=helpdesk.getEmpId();
			String module = "HelpDesk";
			String applnID = helpdesk.getHelpcode();
			String level = "1";
			String link = "/help/HelpDesk_retrieveDetails.action";
			String linkParam = "reqAppCode=" + applnID;
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", helpdesk
					.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "HelpDesk");
			
			System.out.println("........type  "+helpdesk.getRadioCheck());
			if(helpdesk.getRadioCheck().equals("O")){
				System.out.println(".andar ");
				template.sendProcessManagerAlertWithdraw
				(helpdesk.getUserEmpId(), module, "A",
						applnID, level, linkParam, link, message, "Draft",
						helpdesk.getUserEmpId(), helpdesk.getUserEmpId(),helpdesk.getRequestForEmpId(),"");
			}
			else if(helpdesk.getRadioCheck().equals("S")){
				template.sendProcessManagerAlertDraft(helpdesk.getRequestForEmpId(), module, "A",
						applnID, level, linkParam, link, message, "Draft",
						helpdesk.getRequestForEmpId(), helpdesk.getRequestForEmpId());
			}else{
				template.sendProcessManagerAlertDraft(helpdesk.getUserEmpId(), module, "A",
						applnID, level, linkParam, link, message, "Draft",
						helpdesk.getUserEmpId(), helpdesk.getUserEmpId());
			}
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

